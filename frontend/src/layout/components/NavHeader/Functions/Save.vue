<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="保存"
      placement="bottom-end"
    >
      <el-button
        icon="el-icon-document-checked"
        circle
        type="info"
        plain
        @click="handleSave"
        :disabled="!noteContent.id"
      />
    </el-tooltip>
  </div>
</template>

<script>
import { saveNote } from '@/api/note'
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState(['noteContent'])

  },
  methods: {
    handleSave () {
      saveNote(this.noteContent.content, this.noteContent.id, this.noteContent.mode).then(res => {
        this.$message({
          type: 'success',
          message: '成功保存笔记'
        })
      })
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
