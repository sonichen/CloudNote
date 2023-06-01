<template>
  <el-button-group class="buttons-container">
    <el-button
      icon="el-icon-reading"
      @click="turnTo(1)"
      plain
      :type="activeIndex===1?'primary':null"
    >
      阅读模式
    </el-button>
    <el-button
      icon="el-icon-edit"
      @click="turnTo(2)"
      plain
      :type="activeIndex===2?'primary':null"
      :disabled="mode!=='1'"
    >
      富文本编辑器
    </el-button>
    <el-button
      icon="el-icon-edit"
      @click="turnTo(3)"
      plain
      :type="activeIndex===3?'primary':null"
      :disabled="mode!=='2'"
    >
      MarkDown编辑器
    </el-button>
  </el-button-group>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'ButtonSet',
  data () {
    return {
      activeIndex: 1,
      mode: undefined
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    'noteContent.mode': {
      handler (newV) {
        this.mode = newV + ''
      },
      immediate: true
    }
  },
  methods: {
    turnTo (index) {
      if (this.activeIndex !== index) {
        if (index === 1) {
          this.$router.push('/read-mode')
          this.activeIndex = 1
        } else if (index === 2) {
          this.$router.push('/rich-text')
          this.activeIndex = 2
        } else if (index === 3) {
          this.$router.push('/mark-down')
          this.activeIndex = 3
        }
      }
    }
  }
}
</script>

<style scoped>
.buttons-container {
  width: 100%;
  height: 100%;
}

::v-deep .el-button {
  height: 50px;
  border: none;
  border-radius: 0;
}
</style>
