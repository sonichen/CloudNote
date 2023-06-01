<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="导出"
      placement="bottom-end"
    >
      <el-button
        icon="el-icon-download"
        circle
        type="info"
        plain
        @click="handleTag"
        :disabled="!noteContent.id"
      />
    </el-tooltip>
    <ExportManage ref="export" />
  </div>
</template>

<script>
import { mapState } from 'vuex'
import ExportManage from '@/pages/Export/ExportManage'
export default {
  components: { ExportManage },
  computed: {
    ...mapState(['noteContent'])
  },
  methods: {
    handleTag () {
      this.$refs.export.openDialog()
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
