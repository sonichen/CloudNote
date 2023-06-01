<template>
  <div class="index_container">
    <PageLayout :title="'账号设置'" />
  </div>
</template>

<script>
import PageLayout from '@/components/PageLayout'
export default {
  name: 'UserInfo',
  components: { PageLayout },
  data () { return {} },
  methods: {}
}
</script>

<style lang="scss" scoped>
.index_container {
  width: 100%;
  height: 100%;
  background-color: #fff;
}
</style>
