<template>
  <!-- 上部logo和文字 -->
  <div :class="whetherCollapse ? 'logo_collapse' : 'logo_wrap'">
    <img
      class="logo_wrap_img"
      :src="avatar"
    >
    <p class="logo_wrap_title">
      {{ userName }}
    </p>
    <el-popover
      placement="right"
      width="200"
      trigger="click"
    >
      <el-dropdown-item @click.native="turnTo('/user-info')">
        <i class="el-icon-user" />
        个人信息
      </el-dropdown-item>
      <!-- <el-dropdown-item @click.native="turnTo('/account-setting')">
        <i class="el-icon-setting" />
        账号设置
      </el-dropdown-item> -->
      <el-dropdown-item @click.native="handleLogout">
        <i class="el-icon-remove-outline" />
        退出登录
      </el-dropdown-item>
      <el-button
        type="primary"
        class="logo_wrap_button"
        slot="reference"
      >
        设置
      </el-button>
    </el-popover>
  </div>
</template>

<script>
import { getUserInfo } from '@/api/user'
export default {
  data () {
    return {
      whetherCollapse: true,
      userName: '游客',
      avatar: 'https://bpic.51yuansu.com/pic2/cover/00/35/43/58119f542530c_610.jpg'
    }
  },
  props: {
    isCollapse: {
      type: Boolean,
      default: true
    }
  },
  created () {
    // if (localStorage.getItem('userInfo')) {
    //   this.userName = JSON.parse(localStorage.getItem('userInfo')).username
    //   this.avatar = JSON.parse(localStorage.getItem('userInfo')).avatar
    // }
  },
  async mounted () {
    const { data } = await getUserInfo()
    this.userName = data.data.username
    this.avatar = data.data.avatar
  },
  watch: {
    isCollapse: {
      handler (newV) {
        this.whetherCollapse = newV
      },
      immediate: true
    }
  },
  methods: {
    turnTo (dir) {
      this.$router.push(dir)
    },
    handleLogout () {
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="scss" scoped>

.logo_wrap {
  position: relative;
  // z-index: 30000;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: calc(100% - 1px);
  // box-shadow: 0 15px 10px -15px #333;
  // border-right: 1px solid #e6e6e6;
  // background-color: #fff;

  &_img {
    width: 90px;
    height: 90px;
    margin: 50px 0 13px 0;
    border-radius: 50px;
  }

  &_title {
    font-size: 15px;
    font-weight: 600;
    color: #777;
  }

  &_button {
    height: 20px;
    margin-bottom: 30px;
    line-height: 0px;
  }
}

.logo_collapse {
  position: relative;
  // z-index: 30000;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: calc(100% - 1px);
  background-color: #fff;
  // border-right: 1px solid #e6e6e6;

  .logo_wrap_img {
    width: 22px;
    height: 22px;
    margin: 31px 5px;
  }

  .logo_wrap_title,
  .logo_wrap_button {
    display: none;
  }
}
</style>
