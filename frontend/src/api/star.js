import { request } from '@/utils/request'

export function getStarList () {
  return request({
    url: '/note/star/findStarList',
    method: 'get'
  })
}
