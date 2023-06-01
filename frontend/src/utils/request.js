import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'

const TIMEOUT = 3000000
export const request = axios.create({
  timeout: TIMEOUT
})

const requestInterceptor = request.interceptors.request.use(
  (request) => {
    // 防止 IE 缓存 get 请求
    if (request.method === 'get') {
      request.headers = {
        ...request.headers,
        'Cache-Control': 'no-cache',
        Pragma: 'no-cache'
      }
    }
    request.headers = {
      ...request.headers,
      // 请求时间， format ISOString 并修复时差
      'Request-DateTime': new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .slice(0, -5)
        .replace('T', ' ')
    }
    const token = localStorage.getItem('token')
    if (token) {
      request.headers.token = token
    }
    return request
  },
  (error) => {
    Message.error(`网络错误: ${error.message}`)
    Promise.reject(error)
  }
)
request.interceptors.request.use(requestInterceptor)

request.interceptors.response.use(
  (response) => {
    const { status, data } = response
    if (status !== 200 && status !== 201) {
      Message.error(data)
      return Promise.reject(new Error(`HTTP code is ${status}`))
    } else {
      if (data.code && data.code !== 200) {
        Message.error(data.data)
      } else {
        store.commit('updateCallAPI')
        return response
      }
    }
  },
  (error) => {
    Message.error('出错了')
    return Promise.reject(error)
  }
)
