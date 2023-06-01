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
      <el-popover
        placement="right"
        width="200"
        trigger="click"
      >
        <el-dropdown-item @click.native="openSearchName">
          内容检索
        </el-dropdown-item>
        <el-dropdown-item @click.native="openSearchTag">
          标签检索
        </el-dropdown-item>
        <el-menu-item
          slot="reference"
          index="5"
        >
          <i class="el-icon-search" />
          <span slot="title">笔记检索</span>
        </el-menu-item>
      </el-popover>
      <!-- 笔记本标签 -->
      <el-submenu
        index="1"
        @click.stop.native="routeElimite"
      >
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
          <!-- 2:遍历出笔记本 -->
          <el-submenu
            v-for="book2 in book.children"
            :index="'book-'+book2.id"
            :key="'book-'+book2.id"
          >
            <template slot="title">
              <i class="el-icon-document-copy" />
              {{ book2.name }}
            </template>
            <!-- 3:遍历出笔记本 -->
            <el-submenu
              v-for="book3 in book2.children"
              :index="'book-'+book3.id"
              :key="'book-'+book3.id"
            >
              <template slot="title">
                <i class="el-icon-document-copy" />
                {{ book3.name }}
              </template>
              <!-- 4:遍历出笔记本 -->
              <el-submenu
                v-for="book4 in book3.children"
                :index="'book-'+book4.id"
                :key="'book-'+book4.id"
              >
                <template slot="title">
                  <i class="el-icon-document-copy" />
                  {{ book4.name }}
                </template>
                <!-- 5:遍历出笔记本 -->
                <el-submenu
                  v-for="book5 in book4.children"
                  :index="'book-'+book5.id"
                  :key="'book-'+book5.id"
                >
                  <template slot="title">
                    <i class="el-icon-document-copy" />
                    {{ book5.name }}
                  </template>
                  <!-- 6:遍历出笔记本 -->
                  <el-submenu
                    v-for="book6 in book5.children"
                    :index="'book-'+book6.id"
                    :key="'book-'+book6.id"
                  >
                    <template slot="title">
                      <i class="el-icon-document-copy" />
                      {{ book6.name }}
                    </template>
                    <!-- 继续往下的话，从此处加 -->
                    <!-- 6:遍历该目录下的笔记 -->
                    <el-menu-item
                      v-for="note6 in book6.contentList"
                      :key="'note-'+note6.id"
                      :index="'note-'+note6.id"
                      @click.stop.capture.native="findNote(note6.id)"
                    >
                      <i class="el-icon-tickets" />
                      {{ note6.title }}
                    </el-menu-item>
                  </el-submenu>
                  <!-- 5:遍历该目录下的笔记 -->
                  <el-menu-item
                    v-for="note5 in book5.contentList"
                    :key="'note-'+note5.id"
                    :index="'note-'+note5.id"
                    @click.stop.capture.native="findNote(note5.id)"
                  >
                    <i class="el-icon-tickets" />
                    {{ note5.title }}
                  </el-menu-item>
                </el-submenu>
                <!-- 4:遍历该目录下的笔记 -->
                <el-menu-item
                  v-for="note4 in book4.contentList"
                  :key="'note-'+note4.id"
                  :index="'note-'+note4.id"
                  @click.stop.capture.native="findNote(note4.id)"
                >
                  <i class="el-icon-tickets" />
                  {{ note4.title }}
                </el-menu-item>
              </el-submenu>
              <!-- 3:遍历该目录下的笔记 -->
              <el-menu-item
                v-for="note3 in book3.contentList"
                :key="'note-'+note3.id"
                :index="'note-'+note3.id"
                @click.stop.capture.native="findNote(note3.id)"
              >
                <i class="el-icon-tickets" />
                {{ note3.title }}
              </el-menu-item>
            </el-submenu>
            <!-- 2:遍历该目录下的笔记 -->
            <el-menu-item
              v-for="note2 in book2.contentList"
              :key="'note-'+note2.id"
              :index="'note-'+note2.id"
              @click.stop.capture.native="findNote(note2.id)"
            >
              <i class="el-icon-tickets" />
              {{ note2.title }}
            </el-menu-item>
          </el-submenu>
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
      <el-menu-item
        index="3"
        @click.stop.native="turnTo('/collection')"
      >
        <i class="el-icon-star-off" />
        <span slot="title">我的收藏</span>
      </el-menu-item>
      <el-menu-item
        index="4"
        @click.stop.native="turnTo('/bin')"
      >
        <i class="el-icon-document-delete" />
        <span slot="title">回收站</span>
      </el-menu-item>
    </el-menu>
    <CollapseController
      @toggleFold="toggleFold"
      :is-collapse="isCollapse"
    />
    <SearchTag ref="searchTag" />
    <SearchName ref="searchName" />
  </div>
</template>

<script>
import { getNoteBookList } from '@/api/noteBook'
import { getNoteContent } from '@/api/note'
import UserInfo from './UserInfo.vue'
import CollapseController from './CollapseController.vue'
import { mapMutations } from 'vuex'
import SearchTag from '@/pages/Search/SearchTag'
import SearchName from '@/pages/Search/SearchName'
export default {
  components: { UserInfo, CollapseController, SearchTag, SearchName },
  data () {
    return {
      isCollapse: false,
      noteBookList: []
    }
  },
  computed: {
    SeeMenu: {
      get () {
        return this.$store.state.changeMenu
      },
      set (v) {
        this.SeeMenu = v
      }
    }
  },
  watch: {
    SeeMenu () {
      this.getMenu()
    }
  },
  mounted () {
    this.getMenu()
  },
  methods: {
    openSearchTag () {
      this.$refs.searchTag.openDialog()
    },
    openSearchName () {
      this.$refs.searchName.openDialog()
    },
    getMenu () {
      getNoteBookList().then(res => {
        console.log(res)
        this.noteBookList = res.data.data
      })
    },
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
      if (this.$route.path !== route) {
        this.$router.push(route)
      }
    },
    routeElimite () {
      if (this.$route.path !== '/read-mode' && this.$route.path !== '/rich-text' && this.$route.path !== '/mark-down' && this.$route.path !== '/version-manage') {
        this.turnTo('/read-mode')
      }
    },
    ...mapMutations(['setNoteContent']),
    findNote (id) {
      console.log(this.$route.path)
      this.routeElimite()
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
  // z-index: 9999;
  // height: calc(100% - 84px);
  border-right: none;
}

::v-deep .el-menu-item {
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
