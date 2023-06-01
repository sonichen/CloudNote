<template>
  <DialogCard
    ref="dialog"
    title="分享"
  >
    <el-alert
      title="点击分享当前笔记"
      type="info"
      :closable="false"
      show-icon
    />
    <el-row style="margin-top: 30px;">
      <!-- 分享到QQ -->
      <el-button
        @click="shareToQQ()"
        class="el-button"
        plain
      >
        <el-image
          class="el-image"
          :src="require('@/assets/Share/qq.png')"
        /> <span>分享到QQ</span>
      </el-button>
      <!-- 分享到QQ空间 -->
      <el-button
        @click="shareToRoom()"
        class="el-button"
        plain
      >
        <el-image
          class="el-image"
          :src="require('@/assets/Share/QQspace.png')"
        /> <span>分享到QQ空间</span>
      </el-button>
      <!-- 分享到微博 -->
      <el-button
        @click="shareToMicroblog()"
        class="el-button"
        plain
      >
        <el-image
          class="el-image"
          :src="require('@/assets/Share/weibo.png')"
        /> <span>分享到微博</span>
      </el-button>
      <!-- 二维码分享 -->
      <el-button
        @click="qrCodeShare()"
        class="el-button"
        plain
      >
        <el-image
          class="el-image"
          :src="require('@/assets/Share/qr.png')"
        /> <span>生成二维码</span>
      </el-button>
    </el-row>
  </DialogCard>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import { mapState } from 'vuex'
import { shareNote, transformLongLink2Short, qrCodeShare } from '@/api/share'
export default {
  components: { DialogCard },
  data () {
    return {
      // 自定义内容
      share: {
        // title: '分享测试',
        desc: 'c笔记--一款你的云上笔记系统',
        image_url: [
          // JSON.parse(localStorage.getItem('userInfo')).avatar
        ]
        // share_url: 'http://localhost:8080/share/' + this.noteContent.id
      },
      shortLink: ''
    }
  },
  computed: {
    ...mapState(['noteContent']),
    title () {
      if (this.noteContent.id) {
        return this.noteContent.title
      } else {
        return 'c笔记'
      }
    },
    share_url () {
      if (this.noteContent.id) {
        // return 'http://localhost:8080/share/' + this.noteContent.id
        return 'http://re.vipgz1.91tunnel.com/share/' + this.noteContent.id
      } else {
        return 'http://re.vipgz1.91tunnel.com/'
      }
    }
  },
  methods: {
    async shareNote () {
      await shareNote(this.noteContent.id)
      this.shortLink = await this.transform(this.share_url)
    },
    async transform (longlink) {
      const { data } = await transformLongLink2Short(longlink)
      console.log(data)
      return data
    },
    openDialog () {
      this.$refs.dialog.dialogVisible = true
    },
    // 分享到QQ好友(PC端可用)
    async shareToQQ () {
      await this.shareNote()
      // 此处分享链接内无法携带图片
      window.open(
        'https://connect.qq.com/widget/shareqq/index.html?url=' +
      encodeURIComponent(this.shortLink) +
      '&title=' +
      this.title +
      '&desc=' +
      this.share.desc
      )
    },
    // 分享到QQ空间(可用)
    async shareToRoom () {
      await this.shareNote()
      const imageURLs = this.share.image_url.map(function (image) {
        return encodeURIComponent(image)
      })
      // 跳转地址
      window.open(
        'https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=' +
      encodeURIComponent(this.shortLink) +
      '&title=' +
      this.title +
      '&pics=' +
      imageURLs.join('|') +
      '&summary=' +
      this.share.desc
      )
    },
    // 分享到微博(可用)
    async shareToMicroblog () {
      await this.shareNote()
      // 跳转地址
      window.open(
        'https://service.weibo.com/share/share.php?url=' +
      encodeURIComponent(this.shortLink) +
      '&title=' +
      this.title +
      '&pic=' +
      this.share.image_url +
      '&searchPic=true'
      )
    },
    async qrCodeShare () {
      await this.shareNote()
      const { data } = await qrCodeShare(this.noteContent.id)
      const qrLink = data.data.qrCode
      window.open(qrLink, '_blank')
    }
  }
}
</script>

<style scoped>
::v-deep .el-alert--info.is-light {
  color: #307ec7;
  background-color: #e6f7ff;
  border: 1px solid #bae7ff;
}

.el-button {
  width: 200px;
  text-align: center;
}

.el-button span {
  display: flex;
  flex-direction: column;
  align-content: center;
}

.el-image {
  width: 50px;
  height: 50px;
  margin-bottom: 15px;
}
</style>
