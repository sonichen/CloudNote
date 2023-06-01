import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'
import state from './state'
import mutations from './mutations'
import createLogger from 'vuex/dist/logger'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)
const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  namespaced: true,
  actions,
  getters,
  state,
  mutations,
  strict: debug,
  plugins: (debug ? [createLogger()] : []).concat([createPersistedState({ paths: ['deal_formData'] })])
})
