<template>
  <div>
    <DialogCard
      ref="dialog"
      title="标签管理"
    >
      <el-form
        label-position="top"
      >
        <el-form-item label="笔记名称：" />
        <el-input
          v-model="noteContent.title"
          disabled
        />
        <el-form-item
          label="标签管理："
          style="margin-top: 30px;"
        >
          <el-tag
            :key="tag.id"
            v-for="tag in dynamicTags"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)"
          >
            {{ tag.tagsName }}
          </el-tag>
          <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          />
          <el-button
            v-else
            class="button-new-tag"
            size="small"
            @click="showInput"
          >
            + 新增
          </el-button>
        </el-form-item>
      </el-form>
    </DialogCard>
  </div>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import { mapState, mapMutations } from 'vuex'
import { getTags, addTag, deleteTag } from '@/api/tag'
export default {
  name: 'TagManage',
  components: { DialogCard },
  data () {
    return {
      dynamicTags: [],
      inputVisible: false,
      inputValue: ''
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    noteContent: {
      handler (newV) {
        this.getTags()
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    ...mapMutations(['updateTag']),
    getTags () {
      if (this.noteContent.id) {
        getTags(this.noteContent.id).then(res => {
          this.dynamicTags = res.data.data
        })
      }
    },
    openDialog () {
      this.$refs.dialog.dialogVisible = true
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
    },
    handleClose (tag) {
      this.updateTag()
      deleteTag(tag.id)
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },
    showInput () {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },
    handleInputConfirm () {
      const inputValue = this.inputValue
      if (inputValue) {
        this.updateTag()
        this.dynamicTags.push({ tagsName: inputValue })
        addTag(this.noteContent.id, inputValue).then(res => {
          this.getTags()
        })
      }
      this.inputVisible = false
      this.inputValue = ''
    }

  }
}
</script>

<style lang="scss" scoped>
  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .button-new-tag {
    height: 32px;
    padding-top: 0;
    padding-bottom: 0;
    margin-left: 10px;
    line-height: 30px;
  }

  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>
