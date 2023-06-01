<template>
  <div
    class="form-container"
  >
    <el-select
      v-model="language"
      placeholder="请选择目标语言"
    >
      <el-option
        v-for="item in langOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      />
    </el-select>
    <el-button
      @click="formSubmit"
    >
      翻译
    </el-button>
    <el-input
      type="textarea"
      :rows="2"
      placeholder="输入需要翻译的内容"
      v-model="textToTranslate"
      style=" max-height: 370px; margin-top: 10px; overflow: auto; outline: none;"
    />
  </div>
</template>

<script>
export default {
  name: 'TranslateFrom',
  props: {
    selectText: {
      type: String,
      default: null
    }
  },
  watch: {
    selectText (newV) {
      this.textToTranslate = newV
    }
  },
  data () {
    return {
      textToTranslate: '', // 要被翻译的文本
      language: 'en', // 选择的语言
      langOptions: [{
        value: 'zh',
        label: '中文'
      }, {
        value: 'en',
        label: '英语'
      }, {
        value: 'cht',
        label: '繁体中文'
      }, {
        value: 'wyw',
        label: '文言文'
      }, {
        value: 'ru',
        label: '俄语'
      }, {
        value: 'fra',
        label: '法语'
      }, {
        value: 'de',
        label: '德语'
      }, {
        value: 'jp',
        label: '日语'
      }, {
        value: 'kor',
        label: '韩语'
      }]
    }
  },
  methods: {
    formSubmit (e) {
      // 点击翻译事件
      this.$emit('formDelivery', this.textToTranslate, this.language)
      e.preventDefault()
    }
  }
}
</script>
