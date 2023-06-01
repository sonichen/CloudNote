<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="导入"
      placement="bottom-end"
    >
      <el-button
        icon="el-icon-upload2"
        circle
        type="info"
        plain
        @click="handleClick"
      />
    </el-tooltip>
    <ImportManage ref="import" />
  </div>
</template>

<script>
import { mapState } from 'vuex'
import ImportManage from '@/pages/Import/ImportManage'
export default {
  components: { ImportManage },
  computed: {
    ...mapState(['noteContent'])
  },
  methods: {
    handleClick () {
      this.$refs.import.openDialog()
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
