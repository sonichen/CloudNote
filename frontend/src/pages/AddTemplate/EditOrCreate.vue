<template>
  <div>
    <DialogCard
      ref="dialog"
      title="编辑模板"
    >
      <el-form
        label-position="top"
      >
        <el-form-item label="模板名称：">
          <el-input
            v-model="form.title"
            placeholder="模板名称"
          />
        </el-form-item>
        <el-form-item label="模板类型：">
          <el-select
            v-model="form.mode"
            placeholder="模板类型"
          >
            <el-option
              v-for="op in options"
              :key="op.value"
              :label="op.label"
              :value="op.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="内容编辑：">
          <div
            v-if="form.mode==='1'"
          >
            <Toolbar
              style="border-bottom: 1px solid #ccc;"
              :editor="editor"
              :default-config="toolbarConfig"
              :mode="mode"
            />
            <Editor
              style="height: 250px; overflow-y: hidden;"
              v-model="form.content"
              :default-config="editorConfig"
              :mode="mode"
              @onCreated="onCreated"
            />
          </div>
          <mavon-editor
            v-else-if="form.mode==='2'"
            v-model="form.content"
            ref="markdown"
            :subfield="true"
            :preview="false"
            :editable="true"
          />
          <span v-else>您还未选中模板类型</span>
        </el-form-item>
      </el-form>
      <el-row
        type="flex"
        justify="center"
        style="margin-top: 30px;"
      >
        <el-button
          type="primary"
          @click="handleConfirm"
        >
          确认
        </el-button>
        <el-button
          @click="handleCancel"
        >
          取消
        </el-button>
      </el-row>
    </DialogCard>
  </div>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import DialogCard from '@/components/DialogCard'
import { mapState, mapMutations } from 'vuex'
import { updateTemplate, addTemplate } from '@/api/add_template'
export default {
  name: 'TemplateManage',
  components: { DialogCard, Editor, Toolbar },
  props: {
    item: {
      type: Object,
      default: undefined
    }
  },
  data () {
    return {
      editor: null,
      toolbarConfig: { },
      editorConfig: { placeholder: '请输入内容...' },
      mode: 'default', // or 'simple'
      options: [{
        value: '1',
        label: '富文本'
      }, {
        value: '2',
        label: 'MarkDown'
      }],
      form: {
        mode: '',
        content: '',
        title: ''
      }
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    item: {
      handler (newV) {
        if (newV.flag === 1) {
        // 编辑模板
          if (newV.mode === '1') {
            this.form = {
              ...newV, content: '<p>' + newV.content + '</p>'
            }
          } else {
            this.form = newV
          }
        } else {
        // 添加模板
          this.form = newV
        }
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    onCreated (editor) {
      this.editor = Object.seal(editor)
    },
    ...mapMutations(['setNoteContent']),
    openDialog () {
      this.$refs.dialog.dialogVisible = true
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
      this.$emit('refresh')
    },
    async handleConfirm () {
      console.log(this.form)
      if (this.form.flag === 1) {
        await updateTemplate(this.form)
        this.$message({
          type: 'success',
          message: '修改模板成功'
        })
      } else {
        await addTemplate(this.form)
        this.$message({
          type: 'success',
          message: '添加模板成功'
        })
      }
      this.closeDialog()
    },
    async handleCancel () {
      await this.$confirm('确认取消修改？将不会保存任何更改！')
      this.closeDialog()
    },
    beforeDestroy () {
      const editor = this.editor
      if (editor == null) return
      editor.destroy() // 组件销毁时，及时销毁编辑器
    }
  }
}
</script>

<style lang="scss" scoped>
.template_wraper {
  display: flex;
  flex-direction: row;
}

.template_item {
  display: inline-block;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  width: 200px;
  padding: 10px;
  margin: 5px;
  border: 1px solid #f4f4f4;
  border-radius: 16px;

  &:hover {
    background-color: rgb(244, 247, 255);
  }

  &_title {
    overflow: hidden;
    text-align: center;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  &_tools {
    width: fit-content;
    margin: auto;
  }
}
</style>
