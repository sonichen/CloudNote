import { request } from '@/utils/request'

export function findHistory (contentId) {
  return request({
    url: '/note/history/findHistory',
    method: 'get',
    params: {
      contentId
    }
  })
}
