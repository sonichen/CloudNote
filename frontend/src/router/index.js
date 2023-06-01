import Vue from 'vue'
import Router from 'vue-router'
import routes from './routes'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/register' || to.name === 'Share') {
    next()
  } else {
    // 看本地是否有token
    const token = localStorage.getItem('token')
    if (token === null || token === '' || token === undefined) {
      // 没有token
      next('./login')
    } else {
      // 有token可以进
      next()
    }
  }
})

export default router
