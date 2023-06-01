<template>
  <div class="page_layout">
    <el-page-header
      class="page_layout_header"
      @back="goBack"
      :content="title"
    />
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: null
    }
  },
  methods: {
    goBack () {
      this.$router.go(-1)
    }
  }
}
</script>

<style lang="scss" scoped>
.page_layout {
  &_header {
    height: 50px;
    padding: 0 0 0 20px;
    line-height: 50px;
    background-color: #fff;
    border-bottom: 1px solid #ccc;
  }
}

::v-deep .el-page-header__content {
  color: #6e6e6e;
}
</style>
