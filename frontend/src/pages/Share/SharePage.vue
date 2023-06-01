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
      </div>
      <span
        class="note_container_left_translate"
      >划词翻译</span>
      <el-switch
        v-model="ifShowTranslate"
        active-color="#22A1FF"
        inactive-color="#d4d4d4"
      />
      <div class="text_container">
        <div
          class="note_container_left_note"
          v-html="noteText"
          id="printbox"
        />
      </div>
    </div>
    <!-- 阅读模式-右侧区域 -->
    <Translate
      :style="{flex: ifShowTranslate?'1':'0' , margin: '0 20px 0 90px',display:ifShowTranslate?'inline-block':'none'}"
      :select-text="selectText"
    />
  </div>
</template>

<script>
import Translate from './Translate/Translate'
import { getNoteContent } from '@/api/share'
import { marked } from 'marked'
export default {
  components: { Translate },
  data () {
    return {
      selectText: '',
      ifShowTranslate: false,
      noteContent: {}
    }
  },
  computed: {
    noteText () {
      if (this.noteContent.mode === '2') {
        return marked(this.noteContent.content)
      } else {
        return this.noteContent.content
      }
    }
  },
  async mounted () {
    const { data } = await getNoteContent(this.$route.params.id)
    this.noteContent = data.data
    console.log(data)
  },
  methods: {
    selectOn (e) {
      if (window.getSelection().toString() !== '') {
        // console.log(window.getSelection().toString())
        this.selectText = window.getSelection().toString()
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
  padding: 60px;
  background-color: #f4f4f4;

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

.text_container {
  max-width: 100%;
  height: calc(100% - 150px);
  max-height: calc(100% - 150px);
  padding: 20px 30px;
  margin-top: 20px;
  overflow: auto;
  background-color: #fff;
  border: 1px solid #e6e6e6;
}
</style>
