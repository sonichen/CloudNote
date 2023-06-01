<template>
  <el-dialog
    v-if="dialogVisible"
    :title="title"
    :visible.sync="dialogVisible"
    :width="width"
    :before-close="handleClose"
    :top="top"
    :close-on-click-modal="false"
    :append-to-body="true"
  >
    <div
      class="dialogbox"
    >
      <slot />
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: '标题'
    },
    width: {
      type: String,
      default: '50%'
    },
    top: {
      type: String,
      default: '8vh'
    }
  },
  data () {
    return {
      dialogVisible: false
    }
  },
  methods: {
    /**
     * 关闭
     */
    handleClose (done) {
      this.dialogVisible = false
    },
    confirmClose () {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.dialogVisible = false
        })
        .catch(_ => {})
    }
  }
}
</script>

<style lang="scss" scoped>
.header {
  margin-bottom: 10px;
}

.dialogbox {
  height: auto;
  padding: 0 20px;
  overflow: auto;
}

::v-deep .el-dialog__wrapper {
  z-index: 1000 !important;
}

::v-deep .el-dialog {
  .el-dialog__title {
    font-size: 16px;
    color: #fff;
  }

  &__header {
    color: #fff;
    background-color: #409eff;
  }

  &__close {
    font-size: 22px !important;
    color: #fff;
  }
}

::v-deep .el-form-item {
  margin-bottom: 0;

  .el-form-item__label {
    color: #8898aa;
  }
}
</style>
