<template>
  <!-- 向左叠起 | 向右展开 -->
  <div
    class="control_icon"
    @click="toggleFold"
  >
    <i :class="whetherCollapse ? 'el-icon-arrow-right' : 'el-icon-arrow-left'" />
  </div>
</template>

<script>
export default {
  data () {
    return {
      whetherCollapse: true
    }
  },
  props: {
    isCollapse: {
      type: Boolean,
      default: true
    }
  },
  watch: {
    isCollapse: {
      handler (newV) {
        this.whetherCollapse = newV
      },
      immediate: true
    }
  },
  methods: {
    toggleFold () {
      this.$emit('toggleFold')
    }
  }
}
</script>

<style scoped>
.control_icon {
  position: relative;
  top: 50%;
  right: 37px;
  z-index: 200;
  width: 28px;
  height: 144px;
  line-height: 160px;
  text-align: center;
  cursor: pointer;
  background-image: url("../../assets/navigation/collapseRound.png");
  background-size: cover;
  transform: translateY(-50%);
}
</style>
