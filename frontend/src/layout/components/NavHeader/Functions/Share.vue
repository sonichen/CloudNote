<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="分享"
      placement="bottom-end"
    >
      <el-button
        icon="el-icon-share"
        circle
        type="info"
        plain
        @click="handleTag"
        :disabled="!noteContent.id"
      />
    </el-tooltip>
    <Share ref="share" />
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Share from '@/pages/Share/index'
export default {
  components: { Share },
  computed: {
    ...mapState(['noteContent'])
  },
  methods: {
    handleTag () {
      this.$refs.share.openDialog()
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
