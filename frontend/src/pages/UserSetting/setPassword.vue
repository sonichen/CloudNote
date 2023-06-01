<template>
  <div>
    <el-form
      label-position="left"
      label-width="80px"
      :model="info"
      :rules="rules"
    >
      <el-form-item
        label="原密码"
        prop="oldpassword"
      >
        <el-input
          placeholder="请输入原密码"
          v-model="info.oldpassword"
          style="width: 300px;"
          show-password
        />
      </el-form-item>
      <el-form-item
        label="新密码"
        prop="password"
      >
        <el-input
          placeholder="请输入新密码"
          v-model="info.password"
          style="width: 300px;"
          show-password
        />
      </el-form-item>
      <el-form-item
        label="确认密码"
        prop="repassword"
      >
        <el-input
          placeholder="请再次输入新密码"
          v-model="info.repassword"
          style="width: 300px;"
          show-password
        />
      </el-form-item>
      <el-button
        type="primary"
        @click="updatePassword"
      >
        提交
      </el-button>
    </el-form>
  </div>
</template>

<script>
import { updatePassword } from '@api/user'
export default {
  data () {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入新密码'))
      } else if (value !== this.info.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      info: {
        oldpassword: '',
        password: '',
        repassword: ''
      },
      rules: {
        oldpassword: [
          { required: true, message: '请输入密码', trigger: ['blur', 'change'] },
          { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, message: '密码至少八个字符，至少一个字母和一个数字', trigger: ['blur', 'change'] }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: ['blur', 'change'] },
          { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, message: '密码至少八个字符，至少一个字母和一个数字', trigger: ['blur', 'change'] }
        ],
        repassword: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    updatePassword () {
      updatePassword(this.info).then(res => {
        if (res.data.code === 200) {
          this.$message({
            message: '密码更新成功',
            type: 'success'
          })
        } else {
          this.$message.error('密码更新失败')
        }
      })
    }
  }
}
</script>
