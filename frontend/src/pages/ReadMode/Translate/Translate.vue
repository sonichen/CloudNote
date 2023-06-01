<template>
  <div class="translate_container">
    <TranslateForm
      @formDelivery="translateText"
      :select-text="selectText"
    />
    <TranslateOutput
      :translated-text="translatedText"
    />
  </div>
</template>

<script>
import TranslateForm from './TranslateForm'
import TranslateOutput from './TranslateOutput'
import md5 from 'md5'
import { translate } from '@api/translate'

export default {
  name: 'Translate',
  components: {
    TranslateForm, TranslateOutput
  },
  props: {
    selectText: {
      type: String,
      default: null
    }
  },
  data () {
    return {
      translatedText: '' // 翻译结果
    }
  },
  methods: {
    translateText (text, language) {
      this.translatedText = ''
      const appid = '20220504001202472' // 百度翻译开放平台的个人AppId
      const salt = new Date().getTime() // 随机数
      const from = 'auto' // 源语言
      const userkey = 'IvMKAzGUuQariflMR42x' // 百度翻译开放平台的个人密匙
      const sign = md5(appid + text + salt + userkey)
      translate(text, from, language, appid, salt, sign)
        .then(res => {
          console.log(res)
          // 得到翻译结果
          for (let i = 0; i < res.data.trans_result.length; i++) {
            this.translatedText += res.data.trans_result[i].dst
          }
        })
    }
  }
}
</script>
