import store from '@/store'
export const hunhe = {
  methods: {
    changeMenu () {
      store.commit('updateMenu')
    }
  }
}
