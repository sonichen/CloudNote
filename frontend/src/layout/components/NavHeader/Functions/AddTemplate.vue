<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="添加模板"
      placement="bottom-end"
    >
      <!-- :disabled="!noteContent.id" -->
      <el-button
        icon="el-icon-edit-outline"
        circle
        type="info"
        plain
        @click="handleTemplate"
      />
    </el-tooltip>
    <AddTemplate ref="temp" />
  </div>
</template>

<script>
import { mapState } from 'vuex'
import AddTemplate from '@/pages/AddTemplate/index'
export default {
  components: { AddTemplate },
  computed: {
    ...mapState(['noteContent'])
  },
  methods: {
    handleTemplate () {
      this.$refs.temp.openDialog()
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
