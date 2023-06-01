<template>
  <div>
    <DialogCard
      ref="dialog"
      title="导入"
    >
      <el-alert
        title="请选择文件导入目录。"
        type="info"
        :closable="false"
        show-icon
      />
      <el-input
        v-model="filterText"
        clearable
        size="small"
        placeholder="请输入关键字过滤"
        style=" width: auto; margin: 15px 15px 0 15px;"
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
        style="margin: 0 15px 15px 15px;"
      />
      <el-alert
        title="提醒：可以上传md,docx,pdf格式的文件。"
        type="info"
        :closable="false"
        show-icon
      />
      <el-upload
        ref="upload"
        class="upload-demo"
        action="#"
        accept=".md, .docx, .pdf"
        :headers="headers"
        :on-change="uploadFile"
        :auto-upload="false"
      >
        <el-button
          size="small"
          type="primary"
          style="margin: 20px 0;"
        >
          导入文件
        </el-button>
      </el-upload>
    </DialogCard>
  </div>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import { importFile } from '@/api/import.js'
import { getNoteBookList } from '@/api/noteBook'
export default {
  components: { DialogCard },
  data () {
    return {
      data: {},
      filterText: '', // 搜索tree的过滤关键词
      treeProps: {
        label: 'name',
        children: 'children'
      },
      head: 0,
      headers: {
        token: localStorage.getItem('token')
      }
    }
  },
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    }
  },
  mounted () {
    this.getMenu()
  },
  methods: {
    getMenu () {
      getNoteBookList().then(res => {
        this.data = [{ id: 0, name: '根目录', children: res.data.data }]
      })
    },
    uploadFile (item) {
      const formData = new FormData()
      const file = item.raw
      formData.append('file', file)
      importFile(formData, this.head).then(({ data }) => {
        this.changeMenu()
        console.log(data)
      })
    },
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
    handleClose () {
      this.closeDialog()
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
::v-deep .el-alert--info.is-light {
  color: #307ec7;
  background-color: #e6f7ff;
  border: 1px solid #bae7ff;
}
</style>
