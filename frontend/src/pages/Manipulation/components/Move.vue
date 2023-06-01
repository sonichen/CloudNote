<template>
  <div>
    <DialogCard
      ref="dialog"
      :title="moveConfig.isNoteBook?'移动笔记本':'移动笔记'"
    >
      <div class="tree_select">
        <el-input
          v-model="filterText"
          clearable
          size="small"
          placeholder="请输入关键字过滤"
        />
        <el-tree
          ref="tree"
          class="tree_select_list"
          :data="data"
          :props="treeProps"
          :filter-node-method="filterNode"
          default-expand-all
          highlight-current
          @node-click="handleNodeClick"
        />
        <div class="tree_select_btn">
          <el-row
            type="flex"
            justify="center"
            style="margin-top: 30px;"
          >
            <el-button
              size="small"
              type="primary"
              @click="handleCancel"
            >
              取消
            </el-button>
            <el-button
              size="small"
              type="primary"
              @click="handleConfirm"
            >
              确定
            </el-button>
          </el-row>
        </div>
      </div>
    </DialogCard>
  </div>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import { moveNote } from '@/api/note'
import { updateNoteBook } from '@/api/noteBook'
export default {
  components: { DialogCard },
  props: {
    moveConfig: {
      default: undefined,
      type: Object
    },
    data: {
      default: undefined,
      type: Array
    }
  },
  data () {
    return {
      filterText: '', // 搜索tree的过滤关键词
      treeProps: {
        label: 'name',
        children: 'children'
      },
      head: 0
    }
  },
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    }
  },
  methods: {
    handleNodeClick (data) {
      this.head = data.id
      // console.log(data)
    },
    openDialog () {
      this.$refs.dialog.dialogVisible = true
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
    },
    handleConfirm () {
      if (this.moveConfig.isNoteBook) {
        this.updateNoteBook()
      } else {
        this.updateNote()
      }
      this.closeDialog()
      this.$emit('Refresh')
    },
    handleCancel () {
      this.$confirm('确定关闭？')
        .then(_ => {
          this.closeDialog()
        })
        .catch(_ => {})
    },
    updateNoteBook () {
      updateNoteBook(
        this.head,
        this.moveConfig.id
      ).then(res => {
        this.changeMenu()
        this.$message({
          type: 'success',
          message: '成功移动笔记本'
        })
      })
    },
    updateNote () {
      moveNote(
        this.head,
        this.moveConfig.id
      ).then(res => {
        if (res.data.code === 200) {
          this.changeMenu()
          this.$message({
            type: 'success',
            message: '成功移动笔记'
          })
        }
      })
    },
    /**
     * @event: filter-node-method
     * 根据关键字，过滤信息
     */
    filterNode (value, data, node) {
      if (!value) return true
      return (
        node.label.indexOf(value) !== -1 ||
        node.label.indexOf(value.toUpperCase()) !== -1 ||
        node.label.indexOf(value.toLowerCase()) !== -1
      )
    }
  }
}
</script>

<style lang="scss" scoped>
.tree {
  &_select {
    &_list {
      height: 400px;
      margin: 20px 0;
      overflow: auto;
    }

    &_btn {
      text-align: center;
    }

    ::v-deep .el-input__validateIcon {
      display: none;
    }
  }
}
</style>
