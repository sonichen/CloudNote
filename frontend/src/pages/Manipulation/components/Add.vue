<template>
  <div>
    <DialogCard
      ref="dialog"
      :title="addConfig.isNoteBook?'创建笔记本':'创建笔记'"
    >
      <el-form
        :model="form"
        label-position="top"
      >
        <el-form-item :label="addConfig.isNoteBook?'请输入笔记本名：':'请输入笔记名：'">
          <el-input v-model="form.title" />
        </el-form-item>
        <template v-if="!addConfig.isNoteBook">
          <el-form-item label="请选择创建笔记类型：">
            <el-select
              v-model="form.mode"
              placeholder="笔记类型"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="是否收藏：">
            <el-switch v-model="form.star" />
          </el-form-item>
          <el-form-item label="笔记内容(您可以点击左侧导航，并在富文本或MarkDown编辑器内继续编辑)：">
            <el-input
              type="textarea"
              v-model="form.content"
            />
          </el-form-item>
        </template>
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
import DialogCard from '@/components/DialogCard'
import { addNote } from '@/api/note'
import { addNoteBook } from '@/api/noteBook'
export default {
  components: { DialogCard },
  props: {
    addConfig: {
      default: undefined,
      type: Object
    }
  },
  data () {
    return {
      title: '',
      form: {
        title: ''
      },
      options: [{
        value: '1',
        label: '富文本'
      }, {
        value: '2',
        label: 'MarkDown'
      }]
    }
  },
  methods: {
    openDialog () {
      this.$refs.dialog.dialogVisible = true
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
    },
    async handleConfirm () {
      if (!this.addConfig.isNoteBook && this.form.content === undefined) {
        this.$message({
          message: '请输入笔记内容！',
          type: 'warning'
        })
      } else if (this.addConfig.isNoteBook) {
        await this.addNoteBook()
        this.closeDialog()
        this.$emit('Refresh')
      } else {
        await this.addNote()
        this.closeDialog()
        this.$emit('Refresh')
      }
    },
    handleCancel () {
      if (!this.addConfig.isNoteBook) {
        this.$confirm('确定关闭吗？将不会保留任何更改')
          .then(_ => {
            this.closeDialog()
          })
          .catch(_ => {})
      } else {
        this.closeDialog()
      }
    },
    addNoteBook () {
      addNoteBook({
        head: this.addConfig.head,
        value: this.form.title
      }).then(res => {
        if (res.data.code === 200) {
          this.changeMenu()
          this.$message({
            type: 'success',
            message: '成功创建笔记本' + this.form.title
          })
        }
      })
    },
    addNote () {
      addNote({
        categoriesId: this.addConfig.head,
        content: this.form.content,
        mode: this.form.mode,
        star: this.form.star ? '1' : '0',
        title: this.form.title
      }).then(res => {
        if (res.data.code === 200) {
          this.changeMenu()
          this.$message({
            type: 'success',
            message: '成功创建笔记：' + this.form.title
          })
        }
      })
    }
  }
}
</script>
