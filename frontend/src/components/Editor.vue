<template>
  <div style="border: 1px solid #ccc;">
    <Toolbar
      style="border-bottom: 1px solid #ccc; border-radius: 10px;"
      :editor="editor"
      :default-config="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="height: 450px; overflow-y: hidden;"
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
export default {
  components: { Editor, Toolbar },
  data () {
    return {
      editor: null,
      html: '',
      toolbarConfig: {
        excludeKeys: [
          'headerSelect',
          'italic',
          'group-more-style' // 排除菜单组，写菜单组 key 的值即可
        ]
      },
      editorConfig: { placeholder: '请输入内容...' },
      mode: 'simple', // or 'default'
      timer: null
    }
  },
  mounted () {
    this.html = '队伍介绍'
  },
  methods: {
    onCreated (editor) {
      this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
    },
    onChange (editor) {
    }
  },
  beforeDestroy () {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  }
}
</script>

<style scoped>
.note-container {
  box-sizing: border-box;
}

::v-deep html.dark {
  --w-e-textarea-bg-color: #9c3eb6;
}
</style>
