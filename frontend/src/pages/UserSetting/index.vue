<template>
  <div class="index_container">
    <PageLayout :title="'个人信息'" />
    <div class="index_container_block">
      <div class="index_container_block_title">
        头像管理
      </div>
      <SetAvatar />
    </div>
    <div class="index_container_block">
      <div class="index_container_block_title">
        信息管理
      </div>
      <SetInfo />
    </div>
    <div class="index_container_block">
      <div class="index_container_block_title">
        密码管理
      </div>
      <SetPassword />
    </div>
  </div>
</template>

<script>
import PageLayout from '@/components/PageLayout'
import SetAvatar from './SetAvatar'
import SetInfo from './SetInfo'
import SetPassword from './setPassword'
export default {
  name: 'UserInfo',
  components: { PageLayout, SetAvatar, SetInfo, SetPassword },
  data () {
    return {

    }
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.index_container {
  width: 100%;
  height: 100%;
  padding-bottom: 120px;
  background-color: #f4f4f4;

  &_block {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    width: 100%;
    padding: 60px 60px 0;
    margin: auto;
    background-color: #f4f4f4;

    &_title {
      padding: 0 100px;
      font-size: 18px;
      font-weight: 600;
      color: #666;

      &::after {
        padding-left: 20px;
        content: '|';
      }
    }
  }
}
</style>
