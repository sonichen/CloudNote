<template>
  <div>
    <el-upload
      class="avatar-uploader"
      action="/note/user/upload"
      :headers="headers"
      :show-file-list="true"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
    >
      <img
        v-if="imageUrl"
        :src="imageUrl"
        class="avatar"
      >
      <i
        v-else
        class="el-icon-plus avatar-uploader-icon"
      />
    </el-upload>
  </div>
</template>

<script>
import { getUserInfo } from '@/api/user'
export default {
  data () {
    return {
      imageUrl: '',
      headers: {
        token: localStorage.getItem('token')
      }
    }
  },
  // created () {
  //   if (localStorage.getItem('userInfo')) {
  //     this.imageUrl = JSON.parse(localStorage.getItem('userInfo')).avatar
  //   }
  // },
  async mounted () {
    const { data } = await getUserInfo()
    this.imageUrl = data.data.avatar
  },
  methods: {
    handleAvatarSuccess (res, file) {
      this.$message.success('上传成功!')
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      console.log(file)
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style>
  .avatar-uploader .el-upload {
    position: relative;
    overflow: hidden;
    cursor: pointer;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }

  .avatar-uploader-icon {
    width: 78px;
    height: 78px;
    font-size: 28px;
    line-height: 78px;
    color: #8c939d;
    text-align: center;
  }

  .avatar {
    display: block;
    width: 78px;
    height: 78px;
  }
</style>
