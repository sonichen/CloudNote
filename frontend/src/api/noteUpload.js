import { request } from '@/utils/request'

export function addImg (formData) {
  return request({
    url: '/note/???',
    method: 'post',
    data: formData
  })
}
