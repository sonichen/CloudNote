<template>
  <div class="nav-aside_container">
    <UserInfo :is-collapse="isCollapse" />
    <!-- 左侧导航栏 -->
    <el-menu
      default-active="1-4-1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
    >
      <!-- 笔记本标签 -->
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-folder" />
          <span slot="title">我的笔记</span>
        </template>
        <!-- 1:遍历出笔记本 -->
        <el-submenu
          v-for="book in noteBookList"
          :index="'book-'+book.id"
          :key="book.id"
        >
          <template slot="title">
            <i class="el-icon-document-copy" />
            {{ book.name }}
          </template>
          son
          <!-- 1:遍历该目录下的笔记 -->
          <el-menu-item
            v-for="note in book.contentList"
            :key="'note-'+note.id"
            :index="'note-'+note.id"
            @click.stop.capture.native="findNote(note.id)"
          >
            <i class="el-icon-tickets" />
            {{ note.title }}
          </el-menu-item>
        </el-submenu>
      </el-submenu>
      <!-- 其他标签 -->
      <el-menu-item
        index="2"
        @click.stop.native="turnTo('/manipulate')"
      >
        <i class="el-icon-folder-add" />
        <span slot="title">笔记管理</span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-star-off" />
        <span slot="title">我的收藏</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-document-delete" />
        <span slot="title">回收站</span>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-setting" />
        <span slot="title">设置</span>
      </el-menu-item>
    </el-menu>
    <CollapseController
      @toggleFold="toggleFold"
      :is-collapse="isCollapse"
    />
  </div>
</template>

<script>
import { getNoteBookList } from '@/api/noteBook'
import { getNoteContent } from '@/api/note'
import UserInfo from './UserInfo.vue'
import CollapseController from './CollapseController.vue'
import { mapMutations } from 'vuex'
export default {
  components: { UserInfo, CollapseController },
  data () {
    return {
      isCollapse: false,
      noteBookList: []
    }
  },
  mounted () {
    getNoteBookList().then(res => {
      console.log(res)
      this.noteBookList = res.data.data
    })
  },
  methods: {
    handleOpen (key, keyPath) {
      // console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      // console.log(key, keyPath)
    },
    toggleFold () {
      this.isCollapse = !this.isCollapse
    },
    turnTo (route) {
      this.$router.push(route)
    },
    ...mapMutations(['setNoteContent']),
    findNote (id) {
      console.log('点击了id为 ' + id + ' 的笔记本')
      getNoteContent(id).then(res => {
        if (res.data.code === 200) {
          // localStorage.setItem('noteContent', JSON.stringify(res.data.data))
          this.setNoteContent(res.data.data)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.nav-aside_container {
  position: relative;
  width: auto;
  width: calc(100%);
  height: 100%;
  overflow-x: visible;
  background-color: #fff;
  border-right: 1px solid #e6e6e6;
}

/* 调整导航栏height,打开时 */
.el-menu-vertical-demo:not(.el-menu--collapse) {
  position: relative;
  width: 15vw;
  // height: calc(100% - 206px);
  min-height: 400px;
  max-height: calc(100% - 260px);
  overflow: auto;
}

/* 调整导航栏height,闭上时 */
.el-menu {
  position: relative;
  z-index: 19999;
  // height: calc(100% - 84px);
  border-right: none;
}

</style>
