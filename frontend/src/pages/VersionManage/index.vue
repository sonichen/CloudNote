<template>
  <div
    class="note_container"
    v-loading="loading"
  >
    <!-- 阅读内容-左侧区域 -->

    <NoteText
      style="flex: 2;"
      :current-config="currentConfig"
    />
    <!-- 版本导航-右侧区域 -->
    <div
      style="flex: 1; max-height: calc(100% - 50px); overflow: auto;"
      v-if="versionList[0]"
      class="right_container"
    >
      <div
        class="box"
        v-for="version in versionList"
        :key="version.versionId"
        @click="handleClick(version)"
      >
        <div class="version">
          <i class="el-icon-files" />
          版本编号: {{ version.versionId }}
        </div>
        <div class="time">
          <i class="el-icon-time" />
          {{ version.createTime }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NoteText from './NoteText.vue'
import { findHistory } from '@/api/version'
import { mapState } from 'vuex'
export default {
  components: { NoteText },
  data () {
    return {
      versionList: [],
      currentConfig: {},
      loading: false
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    noteContent: {
      handler () {
        this.findHistory()
      },
      deep: true
    }
  },
  mounted () {
    this.findHistory()
  },

  methods: {
    findHistory () {
      this.loading = true
      findHistory(this.noteContent.id).then(res => {
        this.versionList = res.data.data
        this.currentConfig = this.versionList[0]
        // console.log(this.versionList, 'versions')
        this.loading = false
      })
    },
    handleClick (version) {
      this.currentConfig = version
    }
  }
}
</script>

<style lang="scss" scoped>
.note_container {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  width: 100%;
  height: 100%;

  &_left {
    &_note {
      font-size: 18px;
    }
  }

  .right_container {
    margin: 20px 5px 30px 5px;
    background-color: #fff;
    border: 1px solid #eee;
    border-radius: 4px;

    .box {
      padding: 20px 20px 10px;
      cursor: pointer;
      border-bottom: 1px solid #eee;

      &:hover {
        background-color: #edf8fd;
      }

      .version {
        font-size: 16px;
        color: rgb(71, 114, 179);
      }

      .time {
        width: 100%;
        color: #aaa;
        text-align: right;
      }
    }
  }
}
</style>
