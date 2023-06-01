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
      <el-submenu
        index="1"
        @click.stop.native="turnTo('/read-mode')"
      >
        <template slot="title">
          <i class="el-icon-folder" />
          <span slot="title">我的笔记</span>
        </template>
        <!-- 笔记本内子标签 -->
        <!-- 笔记本名 -->
        <!-- 第一层 -->
        <template v-for="item in noteBookList">
          <!-- 如果第一层有子菜单，则继续循环 -->
          <template v-if="item.children.length>0">
            <el-submenu
              :index="'1-'+item.id"
              :key="item.id"
              @click.stop.native="findNote(item.id)"
            >
              <template slot="title">
                <i class="el-icon-document-copy" />
                {{ item.name }}
              </template>
              <!-- 第二层 -->
              <template v-for="subItem in item.children">
                <!-- 如果第二层有子菜单，则继续循环 -->
                <template v-if="subItem.children.length>0">
                  <el-submenu
                    :index="'1-'+item.id+'-'+subItem.id"
                    :key="'1-'+item.id+'-'+subItem.id"
                    @click.stop.native="findNote(subItem.id)"
                  >
                    <template slot="title">
                      <i class="el-icon-document-copy" />
                      {{ subItem.name }}
                    </template>
                    <!-- <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">{{ threeItem.title }}</el-menu-item> -->
                    <!-- 第三层 -->
                    <template v-for="subItem2 in subItem.children">
                      <!-- 如果第三层有子菜单，则继续循环 -->
                      <template v-if="subItem2.children.length>0">
                        <el-submenu
                          :index="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id"
                          :key="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id"
                          @click.stop.native="findNote(subItem2.id)"
                        >
                          <template slot="title">
                            <i class="el-icon-document-copy" />
                            <span slot="title">{{ subItem2.name }}</span>
                          </template>
                          <!-- <el-menu-item v-for="(fourItem,i) in subItem2.subs" :key="i" :index="fourItem.index">{{ fourItem.title }}</el-menu-item> -->
                          <!-- 第四层 -->
                          <template v-for="subItem3 in subItem2.children">
                            <!-- 如果第四层有子菜单，则继续循环 -->
                            <template v-if="subItem3.children.length>0">
                              <el-submenu
                                :index="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id+'-'+subItem3.id"
                                :key="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id+'-'+subItem3.id"
                                @click.stop.native="findNote(subItem3.id)"
                              >
                                <template slot="title">
                                  <!-- <i :class="item.icon"></i> -->
                                  <!-- <span slot="title">{{ subItem2.title }}</span> -->
                                  <i class="el-icon-document-copy" />
                                  {{ subItem3.name }}
                                </template>
                                <el-menu-item
                                  v-for="fiveItem in subItem3.children"
                                  :key="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id+'-'+subItem3.id+'-'+fiveItem.id"
                                  :index="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id+'-'+subItem3.id+'-'+fiveItem.id"
                                  @click.stop.capture.native="findNote(fiveItem.id)"
                                >
                                  <i class="el-icon-tickets" />
                                  {{ fiveItem.name }}
                                </el-menu-item>
                              </el-submenu>
                            </template>
                            <!-- 如果第四层没有子菜单 -->
                            <el-menu-item
                              v-else
                              :index="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id+'-'+subItem3.id"
                              :key="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id+'-'+subItem3.id"
                              @click.stop.capture.native="findNote(subItem3.id)"
                            >
                              <i class="el-icon-tickets" />
                              {{ subItem3.name }}
                            </el-menu-item>
                          </template>
                        </el-submenu>
                      </template>
                      <!-- 如果第三层没有子菜单 -->
                      <el-menu-item
                        v-else
                        :index="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id"
                        :key="'1-'+item.id+'-'+subItem.id+'-'+subItem2.id"
                        @click.stop.capture.native="findNote(subItem2.id)"
                      >
                        <i class="el-icon-tickets" />
                        {{ subItem2.name }}
                      </el-menu-item>
                    </template>
                  </el-submenu>
                </template>
                <!-- 如果第二层没有子菜单 -->
                <el-menu-item
                  v-else
                  :index="'1-'+item.id+'-'+subItem.id"
                  :key="'1-'+item.id+'-'+subItem.id"
                  @click.stop.capture.native="findNote(subItem.id)"
                >
                  <i class="el-icon-tickets" />
                  {{ subItem.name }}
                </el-menu-item>
              </template>
            </el-submenu>
          </template>
          <!-- 如果第一层没有子菜单 -->
          <template v-else>
            <el-menu-item
              :index="'1-'+item.id"
              :key="'1-'+item.id"
              @click.stop.capture.native="findNote(item.id)"
            >
              <span slot="title">
                <i class="el-icon-tickets" />
                {{ item.name }}</span>
            </el-menu-item>
          </template>
        </template>
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
