import { request } from '@/utils/request'
import axios from 'axios'

export function richWORD (contentId) {
  return request({
    url: '/note/tools/exportWord',
    method: 'post',
    params: {
      contentId
    },
    responseType: 'blob'
  })
}
export function downFile (url, parameter) {
  return axios({
    url: url,
    params: parameter,
    method: 'post',
    responseType: 'blob'
  })
}

export function mdWORD (contentId) {
  return request({
    url: '/note/tools/markdownPrint2Word',
    method: 'post',
    params: {
      contentId
    }
  })
}
