<template>
  <div class="note_container">
    <!-- 阅读模式-左侧区域 -->
    <div
      :style="{flex: ifShowTranslate?'2':'3' ,transition: '0.5s'}"
      class="note_container_left"
      @mouseup="selectOn($event)"
    >
      <div
        class="note_container_left_title"
        :style="{width:ifShowTranslate?'78%':'86%'}"
      >
        {{ noteContent.title }}
        <div
          class="note_container_left_tags"
        >
          <el-tag
            v-for="(tag,index) in tags"
            style="margin-right: 5px;"
            :key="index"
          >
            {{ tag.tagsName }}
          </el-tag>
        </div>
      </div>
      <span
        class="note_container_left_translate"
      >划词翻译</span>
      <el-switch
        v-model="ifShowTranslate"
        active-color="#22A1FF"
        inactive-color="#d4d4d4"
      />
      <NoteText />
    </div>
    <!-- 阅读模式-右侧区域 -->
    <Translate
      :style="{flex: ifShowTranslate?'1':'0' , margin: '0 20px 0 90px',display:ifShowTranslate?'inline-block':'none',minWidth:'360px'}"
      :select-text="selectText"
    />
  </div>
</template>

<script>
import Translate from './Translate/Translate'
import NoteText from './NoteText/NoteText.vue'
import { mapState } from 'vuex'
import { getTags } from '@/api/tag'
export default {
  components: { Translate, NoteText },
  data () {
    return {
      selectText: '',
      ifShowTranslate: false,
      tags: []
    }
  },
  computed: {
    ...mapState(['noteContent', 'changeTags'])
  },
  watch: {
    noteContent: {
      handler () {
        this.getTags()
      },
      deep: true
    },
    changeTags () {
      this.getTags()
    }
  },
  mounted () {
    this.getTags()
  },
  methods: {
    selectOn (e) {
      if (window.getSelection().toString() !== '') {
        // console.log(window.getSelection().toString())
        this.selectText = window.getSelection().toString()
      }
    },
    getTags () {
      if (this.noteContent.id) {
        getTags(this.noteContent.id).then(res => {
          this.tags = res.data.data
        })
      }
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
    &_title {
      display: inline-block;
      margin-right: 10px;
      margin-left: 10px;
      font-size: 30px;
      font-weight: 600;
      color: #409eff;
    }

    &_tags {
      display: inline-block;
    }

    &_translate {
      display: inline-block;
      margin-right: 10px;
      font-size: 16px;
      color: #22a1ff;
    }

    &_note {
      font-size: 18px;
    }
  }
}
</style>
