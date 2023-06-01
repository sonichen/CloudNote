<template>
  <div class="text_container">
    <div
      class="note_container_left_note"
      v-html="noteText"
      id="printbox"
    />
  </div>
</template>

<script>
import { marked } from 'marked'
import { mapState } from 'vuex'
export default {
  name: 'NoteText',
  data () {
    return {
    }
  },
  computed: {
    ...mapState(['noteContent']),
    noteText () {
      if (this.noteContent.mode === '2') {
        return marked(this.noteContent.content)
      } else {
        return this.noteContent.content
      }
    }
  },
  methods: {
    readFile () {
      const me = this
      const url = '/static/test.md' // 读取本地.md文件
      this.axios({ url: url, baseURL: '' })
        .then((response) => {
          if (response.data) {
            me.output = marked(response.data) // 解析markdown文件到div显示
          }
        })
        .catch((err) => {
          console.log(err)
          me.output = marked( // 找不到文件则显示一个默认错误，相当于404
            '<h1 class="h1-text">Sorry, this page is under construction</h1>'
          )
        })
    }
  }
}
</script>

<style lang="scss" scoped>
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
