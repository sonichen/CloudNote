<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="版本查询"
      placement="bottom-end"
    >
      <el-button
        icon="el-icon-files"
        circle
        type="info"
        :disabled="!noteContent.id"
        plain
        @click="handleVersion"
      />
    </el-tooltip>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'

export default {
  data () {
    return {
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  methods: {
    ...mapMutations(['setNoteContent']),
    handleVersion () {
      this.$router.push('/version-manage')
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
