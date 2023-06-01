<template>
  <div class="set_info_container">
    <el-form
      label-position="left"
      label-width="80px"
      :model="info"
      :rules="rules"
    >
      <el-form-item
        label="用户名"
        prop="username"
      >
        <el-input v-model="info.username" />
      </el-form-item>
      <el-form-item
        label="电子邮箱"
        prop="email"
      >
        <el-input v-model="info.email" />
      </el-form-item>
      <el-form-item label="个人介绍">
        <el-input v-model="info.introduce" />
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input
          v-model="info.tel"
          disabled
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input
          v-model="info.createTime"
          disabled
        />
      </el-form-item>
      <el-button
        type="primary"
        @click="updateInfo"
      >
        提交
      </el-button>
      <el-button @click="reset">
        重置
      </el-button>
    </el-form>
  </div>
</template>

<script>
import { updateInfo, getUserInfo } from '@api/user'
export default {
  data () {
    return {
      info: {
        createTime: '',
        email: '',
        introduce: '',
        tel: '',
        username: '',
        avatar: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: ['blur', 'change'] },
          { min: 3, max: 14, message: '长度在 3 到 14 个字符', trigger: ['blur', 'change'] }
        ],
        email: [{
          type: 'string',
          required: true,
          message: '请输入邮箱地址',
          trigger: 'blur'
        },
        {
          type: 'email',
          message: '请输入正确的邮箱地址',
          trigger: ['blur', 'change']
        }
        ]
      }
    }
  },
  created () {
    this.reset()
    this.info.tel = JSON.parse(localStorage.getItem('userInfo')).tel
    this.info.id = JSON.parse(localStorage.getItem('userInfo')).id
    this.info.avatar = JSON.parse(localStorage.getItem('userInfo')).avatar
    this.info.createTime = JSON.parse(localStorage.getItem('userInfo')).createTime
  },
  async mounted () {
    const { data } = await getUserInfo()
    this.info = data.data
  },
  methods: {
    updateInfo () {
      updateInfo(this.info).then(res => {
        if (res.data.code === 200) {
          localStorage.setItem('userInfo', JSON.stringify(this.info))
          this.$message({
            message: '信息更新成功',
            type: 'success'
          })
        } else {
          this.$message.error('信息更新失败')
        }
      })
    },
    reset () {
      this.info.email = JSON.parse(localStorage.getItem('userInfo')).email
      this.info.introduce = JSON.parse(localStorage.getItem('userInfo')).introduce
      this.info.username = JSON.parse(localStorage.getItem('userInfo')).username
    }
  }
}
</script>

<style lang="scss">
  .el-input.is-disabled .el-input__inner {
    color: #8a8c8f !important;
  }
</style>
