import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/utils/font-size'
import 'normalize.css'
import store from './store'
import { hunhe } from './utils/mixin'
import VueClipboard from 'vue-clipboard2'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import axios from 'axios'

Vue.prototype.$axios = axios
Vue.mixin(hunhe)
Vue.use(mavonEditor)

Vue.use(VueClipboard)
Vue.config.productionTip = false
Vue.use(ElementUI)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
