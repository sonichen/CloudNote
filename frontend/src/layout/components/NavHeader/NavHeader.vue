<template>
  <div class="header_container">
    <!-- 选择菜单 -->
    <ButtonSet />
    <Functions />
  </div>
</template>

<script>
import ButtonSet from './ButtonSet'
import Functions from './Functions/index'
export default {
  components: { ButtonSet, Functions },
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
.header_container {
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 100%;
  line-height: 100%;
  text-align: right;
  background-color: #fff;
  border-bottom: 1px solid #e6e6e6;
}

</style>
