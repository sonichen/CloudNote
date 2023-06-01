<template>
  <div class="note-container">
    <mavon-editor
      v-model="mdContent"
      ref="md"
      @change="change"
      @imgAdd="imgAdd"
      :subfield="true"
      :preview="false"
      :editable="true"
      style="height: 800px;"
      id="printbox"
    />
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import { addImg } from '@/api/noteUpload'
import turndown from '@/utils/html2md'
export default {
  data () {
    return {
      mdContent: ''
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    'noteContent.content': {
      handler (newV) {
        if (this.noteContent.mode === 1) {
          this.mdContent = turndown.turndown(newV)
        } else {
          this.mdContent = newV
        }
      },
      immediate: true
    }
  },
  methods: {
    ...mapMutations(['setNoteContent']),
    change (val) {
      this.setNoteContent({ mode: 2, content: val })
      // this.setNoteContent({ ...this.noteContent, content: marked(val) })
    },
    imgAdd (pos, $file) {
      // 新建form表单类型的数据
      const formData = new FormData()
      // 将我们上传的图片地址$file加进表单里面，命名为“file”（参数名字与后端相匹配）
      formData.append('file', $file)
      console.log(formData)
      addImg(formData).then((res) => {
        if (res.data.code === 200) {
          // 将后端返回的url放在md中图片的指定位置
          this.$refs.md.$img2Url(pos, res.data.data)
        } else {
          this.$message.error(res.message)
        }
      })
      // 访问后台服务器方法
    }
  }
}
</script>

<style scoped>
.note-container {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
}

.add-image-link-wrapper {
  z-index: 35000 !important;
}
</style>
