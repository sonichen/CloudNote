<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="标签管理"
      placement="bottom-end"
    >
      <el-button
        icon="el-icon-collection-tag"
        circle
        type="info"
        plain
        @click="handleTag"
        :disabled="!noteContent.id"
      />
    </el-tooltip>
    <TagManage ref="tag" />
  </div>
</template>

<script>
import { mapState } from 'vuex'
import TagManage from '@/pages/Tag/TagManage'
export default {
  components: { TagManage },
  computed: {
    ...mapState(['noteContent'])
  },
  methods: {
    handleTag () {
      this.$refs.tag.openDialog()
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
