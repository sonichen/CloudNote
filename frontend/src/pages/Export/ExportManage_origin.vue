<template>
  <div>
    <DialogCard
      ref="dialog"
      title="导出"
    >
      <el-alert
        title="提醒：若要导出为PDF格式，可以使用打印机功能中的'导出为PDF'。"
        type="info"
        :closable="false"
        show-icon
      />
      <el-form
        label-position="left"
        style="padding: 30px 10px;"
      >
        <el-form-item label="笔记名称：">
          <el-input
            v-model="noteContent.title"
            disabled
            style="width: 400px;"
          />
        </el-form-item>
      </el-form>
      <el-button
        plain
        class="el-button"
        @click="exportWORD"
      >
        <el-image
          class="el-image"
          :src="require('@/assets/Share/WORD.png')"
          style="width: 30px; height: 30px; margin-bottom: 15px;"
        />
        <div>导出为word</div>
      </el-button>
      <el-row
        type="flex"
        justify="center"
        style="margin-top: 30px;"
      >
        <el-button
          type="primary"
          @click="handleClose"
        >
          确认
        </el-button>
      </el-row>
    </DialogCard>
  </div>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import { mapState } from 'vuex'
import { richWORD, mdWORD } from '@/api/export'
import JSZipUtils from 'jszip-utils'

import JSZip from 'pizzip'

import Docxtemplater from 'docxtemplater'

import { saveAs } from 'file-saver'
export default {
  components: { DialogCard },
  data () {
    return {
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  mounted () {
  },
  methods: {
    exportWORD () {
      if (this.noteContent.mode + '' === '1') {
        richWORD(this.noteContent.id).then(res => {
          console.log(res)
          this.exportWord(res.data)
        })
      } else {
        mdWORD(this.noteContent.id).then(res => {
          console.log(res)
          this.exportWord(res.data)
        })
      }
    },
    exportWord (data) {
      const that = this
      // 读取并获得模板文件的二进制内容
      JSZipUtils.getBinaryContent('/input.docx', function (error, content) {
        // input.docx是模板。我们在导出的时候，会根据此模板来导出对应的数据,但是必须要创建一个word模板，一般放在静态资源文件里面(public)
        // 抛出异常
        if (error) {
          throw error
        }
        // 创建一个JSZip实例，内容为模板的内容
        const zip = new JSZip(content)
        // 创建并加载docxtemplater实例对象
        const doc = new Docxtemplater().loadZip(zip)
        // 设置模板变量的值
        doc.setData({
          data
        })
        try {
          // 用模板变量的值替换所有模板变量
          doc.render()
        } catch (error) {
          // 如果用了 emelent-ui 的话上面那些就不用写，直接写下面两句
          that.$message.error('导出文件失败')
          throw error
        }
        // 生成一个代表docxtemplater对象的zip文件（不是一个真实的文件，而是在内存中的表示）
        const out = doc.getZip().generate({
          type: 'blob',
          mimeType:
          'application/vnd.openxmlformats- officedocument.wordprocessingml.document'
        })
        // 将目标文件对象保存为目标类型的文件，并命名
        saveAs(out, that.noteContent.title + '.docx')
        this.$message({
          type: 'success',
          message: '导出成功'
        })
      })
    },
    openDialog () {
      this.$refs.dialog.dialogVisible = true
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
    },
    handleClose () {
      this.closeDialog()
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-alert--info.is-light {
  color: #307ec7;
  background-color: #e6f7ff;
  border: 1px solid #bae7ff;
}

.el-button {
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  margin-left: 90px;
}
</style>
