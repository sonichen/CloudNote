import { request } from '@/utils/request'

export function login (tel, password) {
  return request({
    url: '/note/login/login',
    method: 'get',
    params: {
      tel,
      password
    }
  })
}

export function register (tel, password) {
  return request({
    url: '/note/login/register',
    method: 'post',
    params: {
      tel,
      password
    }
  })
}
