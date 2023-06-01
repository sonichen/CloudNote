<template>
  <div class="login_container">
    <div class="login_container_left">
      <h1 class="login_container_left_title">
        云享笔记系统
      </h1>
      <Description />
    </div>
    <div class="login_container_layout">
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
      <div
        style=" margin: auto; margin-top: 30px;"
      >
        <el-button
          class="register"
          @click="toRegister"
        >
          注册
        </el-button>
        <el-button
          type="primary"
          @click="handleSubmit"
        >
          登录
        </el-button>
      </div>
    </div>
    <ValidateCode
      :is-show="isShowValidate"
      @success="handleSuccess"
      @close="handleClose"
    />
  </div>
</template>

<script>
import { login } from '@/api/login'
import Description from '@/components/Description'
import ValidateCode from '@components/ValidateCode'
export default {
  name: 'Login',
  components: { ValidateCode, Description },
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
      this.login()
    },
    login () {
      login(this.tel, this.password).then(res => {
        if (res.data.code === 200) {
          localStorage.setItem('token', res.data.data.token)
          localStorage.setItem('userInfo', JSON.stringify(res.data.data.user))
          this.$router.push('/')
        }
      })
    },
    toRegister () {
      this.$router.push('/register')
    }
  }
}
</script>

<style lang="scss" scoped>
.login_container {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100%;
  background-image: url('../assets/bg.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;

  &_left {
    width: 50%;
    padding: 50px 0 50px 160px;

    &_title {
      padding-bottom: 18px;
      padding-left: 40px;
      // font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', Arial, sans-serif;
      color: #6394e8;
    }
  }

  &_layout {
    display: flex;
    flex-direction: column;
    align-content: center;
    justify-content: center;
    width: 50%;
    padding-top: 50%;
    transform: translateY(-45%);
  }
}

.demo-input-suffix {
  width: 40%;
  min-width: 200px;
  margin: auto;
}

.input1 {
  margin-bottom: 20px;
}

.logo {
  width: 40%;
  margin: auto;
}

.register {
  color: #3d94f8;
  text-decoration: underline;
  background: transparent;
  border: none;
}
</style>
