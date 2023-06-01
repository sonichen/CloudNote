<template>
  <div>
    <DialogCard
      ref="dialog"
      title="查看笔记"
    >
      <div
        v-html="htmlContent"
        style="max-height: 400px; overflow: auto;"
      />
      <el-row
        type="flex"
        justify="center"
        style="margin-top: 30px;"
      >
        <el-button
          type="primary"
          @click="handleClick"
          style="margin-top: 30px;"
        >
          确认
        </el-button>
      </el-row>
    </DialogCard>
  </div>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import { marked } from 'marked'
export default {
  components: { DialogCard },
  props: {
    content: {
      default: undefined,
      type: String
    },
    mode: {
      default: undefined,
      type: String
    }
  },
  computed: {
    htmlContent () {
      return this.mode === '0' ? this.content : marked(this.content)
    }
  },
  methods: {
    openDialog () {
      this.$refs.dialog.dialogVisible = true
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
    },
    handleClick () {
      this.closeDialog()
    }
  }
}
</script>
