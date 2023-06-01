<template>
  <Vcode
    :show="isShow"
    @success="success"
    @close="close"
  />
</template>

<script>
import Vcode from 'vue-puzzle-vcode'

export default {
  components: {
    Vcode
  },
  props: {
    // 验证码模态框是否出现
    isShow: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    isShow (newV) {
      this.isShow = newV
    }
  },
  methods: {
    // 用户通过了验证
    success (msg) {
      this.$emit('success')
    },
    // 用户点击遮罩层，应该关闭模态框
    close () {
      this.$emit('close')
    }
  }
}
</script>
