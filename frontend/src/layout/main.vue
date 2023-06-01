<template>
  <el-container class="main_container">
    <!-- 侧边栏 -->
    <el-aside
      width="auto"
      style="overflow: visible;"
    >
      <keep-alive>
        <NavAside style="height: 100%;" />
      </keep-alive>
    </el-aside>
    <el-container style="max-height: 100vh;">
      <!-- 顶栏 -->
      <el-header
        style="width: 100%; padding: 0;"
        v-show="isShowHeader"
      >
        <NavHeader style="min-width: 1200px;" />
      </el-header>
      <!-- 主要内容 -->
      <el-main class="main_content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import NavHeader from './components/NavHeader/NavHeader'
import NavAside from './components/NavAside/NavAside'
// import { getNoteList } from '@/api/note'
export default {
  components: { NavAside, NavHeader },
  computed: {
    isShowHeader () {
      return (this.$route.path === '/read-mode' || this.$route.path === '/rich-text' || this.$route.path === '/mark-down' || this.$route.path === '/version-manage')
    }
  }
}
</script>

<style lang="scss" scoped>
.main_container {
  width: 100%;
  height: 100vh;
  background-color: #f4f4f4;
}

::v-deep .el-main {
  z-index: 100 !important;
  box-sizing: border-box;
  max-height: calc(100% - 53px);
  padding: 0;
  margin: 40px 30px 30px 40px;
  overflow: auto;
}

::v-deep .el-header {
  height: 51px !important;
}
</style>
