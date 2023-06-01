<template>
  <div class="note-container">
    <div class="toc">
      <div class="title">
        文章目录
      </div>
      <!-- 遍历目录 -->
      <div
        class="item"
        v-for="toc in tocs"
        :key="toc.href"
      >
        <i class="el-icon-notebook-2" />
        <a
          @click="scrollToPosition(toc.href)"
          v-text="toc.name"
        />
      </div>
    </div>
    <mavon-editor
      style="width: calc(100% - 240px); height: 800px;"
      v-model="mdContent"
      ref="markdown"
      @change="change"
      @imgAdd="imgAdd"
      :subfield="true"
      :preview="false"
      :editable="true"
      id="printbox"
    />
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import { addImg } from '@/api/noteUpload'
import turndown from '@/utils/html2md'
import { offsetDomTop, scrolltoToc } from '@/utils/mdScroll'

export default {
  data () {
    return {
      mdContent: '',
      tocs: []
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    'noteContent.content': {
      handler (newV) {
        if (this.noteContent.mode === '1') {
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
      this.setNoteContent({ ...this.noteContent, mode: '2', content: val })
      // this.setNoteContent({ ...this.noteContent, content: marked(val) })
      this.getMDList()
    },
    imgAdd (pos, $file) {
      // 新建form表单类型的数据
      const formData = new FormData()
      // 将我们上传的图片地址$file加进表单里面，命名为“file”（参数名字与后端相匹配）
      formData.append('file', $file)
      // console.log(formData)
      addImg(formData).then((res) => {
        if (res.data.code === 200) {
          // 将后端返回的url放在md中图片的指定位置
          this.$refs.markdown.$img2Url(pos, res.data.data)
        } else {
          this.$message.error(res.message)
        }
      })
      // 访问后台服务器方法
    },
    getMDList () {
      this.$nextTick(() => {
        const aArr = this.$refs.markdown.$refs.vShowContent.querySelectorAll('a')
        const tocs = []
        for (var i = 0; i < aArr.length; i++) {
          if (aArr[i].id) {
            const href = aArr[i].id
            const name = aArr[i].parentNode.innerText
            tocs.push({
            // href: "#" + href, jqurey id选择器需要#
              href: href,
              name
            })
          }
        }
        this.tocs = tocs
      })
    },
    scrollToPosition (id) {
      var position = offsetDomTop(document.getElementById(id))
      // 80是因为有着导航条的高度
      position.top = position.top - 80
      scrolltoToc(position.top)
    }
  }
}
</script>

<style lang="scss" scoped>
.note-container {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  width: 100%;
  height: 100%;

  .toc {
    width: 200px;
    height: 780px;
    padding: 10px 10px;
    margin-right: 30px;
    overflow: auto;
    font-size: 14px;
    background-color: #fff;
    border: 1px solid #e7e7e7;
    border-radius: 10px;

    .title {
      margin-bottom: 20px;
      color: #999;
    }

    .item {
      margin-bottom: 5px;
      font-size: 16px;
      color: #4874b0;

      i {
        margin-right: 10px;
      }
    }
  }
}

.add-image-link-wrapper {
  z-index: 35000 !important;
}
</style>
