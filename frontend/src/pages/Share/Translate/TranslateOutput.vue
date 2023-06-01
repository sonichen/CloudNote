<template>
  <div class="translate_output_container">
    <div v-text="translatedText" />
  </div>
</template>

<script>
export default {
  name: 'TranslateOutput',
  props: {
    translatedText: {
      type: String,
      default: null
    }
  }
}
</script>

<style scoped>
.translate_output_container {
  max-height: 345px;
  padding: 20px;
  margin-top: 10px;
  overflow: auto;
  line-height: 23px;
  color: #5a5a5a;
  background-color: #f7f7f7;
  border: 1px solid #dfdfdf;
}
</style>
