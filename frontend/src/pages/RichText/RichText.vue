<template>
  <div style="border: 1px solid #ccc;">
    <Toolbar
      style="border-bottom: 1px solid #ccc;"
      :editor="editor"
      :default-config="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="height: 750px; overflow-y: hidden;"
      id="printbox"
      v-model="html"
      :default-config="editorConfig"
      :mode="mode"
      @onCreated="onCreated"
      @onChange="onChange"
    />
  </div>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { mapState, mapMutations } from 'vuex'
import { marked } from 'marked'
export default {
  components: { Editor, Toolbar },
  data () {
    return {
      editor: null,
      html: '123',
      toolbarConfig: { },
      editorConfig: { placeholder: '请输入内容...' },
      mode: 'default', // or 'simple'
      timer: null,
      oldId: 0
    }
  },
  mounted () {

  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    noteContent: {
      handler (newV) {
        // console.log('change!!!', newV.content)
        if (newV.id !== this.oldId) {
          this.oldId = newV.id
          if (this.noteContent.mode === '2') {
            this.html = '<p>' + marked(newV.content) + '</p>'
          } else {
            this.html = '<p>' + newV.content + '</p>'
          // console.log('++', this.html)
          }
        }
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    ...mapMutations(['setNoteContent']),
    onCreated (editor) {
      this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
      // console.log('create', this.html)
      // editor.insertText(this.html)
    },
    onChange (editor) {
      // console.log(editor.children) // 文字内容 包括格式
      // console.log(editor.getText()) // 文字内容
      // console.log(editor.getHtml()) // 得到html
      // console.log(editor)
      // console.log('onchange', this.html)
      this.setNoteContent({ ...this.noteContent, content: this.html })
    }
  },
  beforeDestroy () {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  }
  // 通过路由规则，离开该组件时被调用
  // beforeRouteLeave (to, from, next) {
  //   if (this.noteContent.id) {
  //     this.$confirm('是否保留当前更改')
  //       .then(_ => {
  //         this.setNoteContent({ ...this.noteContent, content: this.html })
  //         next()
  //       })
  //       .catch(_ => {
  //         next()
  //       })
  //   } else {
  //     next()
  //   }
  // }
}
</script>

<style scoped>
.note-container {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
}
</style>
