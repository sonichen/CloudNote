<template>
  <div>
    <el-button-group class="buttons-container">
      <AddTemplate />
      <Star />
      <Save />
      <Tag />
      <Version />
      <Import />
      <Print />
      <Export />
      <Share />
    </el-button-group>
  </div>
</template>

<script>
import Print from './Print.vue'
import Save from './Save.vue'
import Star from './Star.vue'
import Version from './Version.vue'
import Tag from './Tag.vue'
import Share from './Share.vue'
import Export from './Export.vue'
import Import from './Import.vue'
import AddTemplate from './AddTemplate'
export default {
  components: { Print, Save, Star, Version, Tag, Share, Export, Import, AddTemplate }
}
</script>

<style lang="scss" scoped>
.buttons-container {
  display: flex;
  flex-direction: row;
}

::v-deep .el-button {
  margin-right: 3px;
  border: none;
}

::v-deep .el-button--info.is-plain,
::v-deep .el-button--info.is-plain.is-disabled {
  background-color: transparent;
}
</style>
