<template>
  <div class="container">
    <div class="login_container">
      <img
        src="../assets/note2.png"
        alt=""
        class="logo"
      >
      <div class="demo-input-suffix">
        <el-input
          placeholder="请输入账号（电话号码）"
          v-model="tel"
          clearable
          class="input1"
          @keyup.enter.native="handleSubmit"
        >
          <i
            slot="prefix"
            class="el-input__icon el-icon-user"
          />
        </el-input>
        <el-input
          placeholder="请输入密码"
          v-model="password"
          show-password
          clearable
          class="input2"
          @keyup.enter.native="handleSubmit"
        >
          <i
            slot="prefix"
            class="el-input__icon el-icon-lock"
          />
        </el-input>
      </div>
      <div style="margin: 30px auto;">
        <el-button
          class="login"
          @click="toLogin"
          icon="el-icon-back"
        >
          登录
        </el-button>
        <el-button
          type="primary"
          @click="handleSubmit"
        >
          注册
        </el-button>
      </div>
      <ValidateCode
        :is-show="isShowValidate"
        @success="handleSuccess"
        @close="handleClose"
      />
    </div>
  </div>
</template>

<script>
import { register } from '@/api/login'
import ValidateCode from '@components/ValidateCode'
export default {
  name: 'Register',
  components: { ValidateCode },
  data () {
    return {
      isShowValidate: false,
      tel: '',
      password: ''
    }
  },
  methods: {
    handleSubmit () {
      if (!(/^1[3456789]\d{9}$/.test(this.tel))) {
        this.$notify.warning({
          message: '手机号格式错误'
        })
      } else if (!(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(this.password))) {
        this.$notify.warning({
          message: '密码至少八个字符，至少一个字母和一个数字'
        })
      } else { this.isShowValidate = true }
    },
    handleClose () {
      this.isShowValidate = false
    },
    handleSuccess () {
      this.isShowValidate = false // 通过验证后，需要手动隐藏模态框
      this.register()
    },
    register () {
      register(this.tel, this.password).then(res => {
        if (res.data.code === 200) {
          this.toLogin()
        }
      })
    },
    toLogin () {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  background-color: #215cb0;
}

.login_container {
  display: flex;
  flex-direction: column;
  align-content: center;
  padding-top: 20vh;
  background-color: #bbd3f5;
}

.demo-input-suffix {
  width: 20%;
  min-width: 200px;
  margin: auto;
}

.input1 {
  margin-top: 50px;
  margin-bottom: 20px;
}

.logo {
  width: 15%;
  margin: auto;
}

.login {
  color: #3d94f8;
  background: transparent;
  border: none;
}
</style>
