import { request } from '@/utils/request'
export function getNoteContent (shareId) {
  return request({
    url: '/note/share/read',
    method: 'get',
    params: {
      shareId
    }
  })
}

export function shareNote (contentId) {
  return request({
    url: '/note/share/share.do',
    method: 'post',
    params: {
      contentId
    }
  })
}

export function transformLongLink2Short (longLink) {
  return request({
    url: '/note/share.do',
    method: 'post',
    params: {
      longLink
    }
  })
}

export function qrCodeShare (contentId) {
  return request({
    url: '/note/share/check',
    method: 'post',
    params: {
      contentId
    }
  })
}
