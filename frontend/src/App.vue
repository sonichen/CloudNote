<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  data () {
    return {
      showRouterView: true,
      showload: false
    }
  }
}
</script>

<style lang="scss">

#app {
  height: 100vh;
}

body .el-table th.gutter {
  display: table-cell !important;
}

::-webkit-scrollbar {
  width: 3px;
  height: 3px;
}

::-webkit-scrollbar-thumb {
  background-color: #ccc;
  border-radius: 5px;
}

/* 调整提示的字体大小 */
.w-e-menu-tooltip {
  font-size: 12px;
}

/* 取消盒子阴影 */
.v-note-wrapper {
  border: 1px solid #ddd !important;
  border-radius: 0 !important;
  box-shadow: none !important;
}

// 提示框位置
.el-dropdown-menu {
  max-height: 208px;
  margin-top: -10px !important;
  overflow: auto;
  border-radius: 10px;
  box-shadow: none;
  // box-shadow: 2px 5px 10px 0 rgba(0, 0, 0, 0.1);
}

.el-popover {
  z-index: 30002 !important;
}
</style>
