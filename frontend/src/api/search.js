import { request } from '@/utils/request'

export function searchNotesByTag (tagsName) {
  return request({
    url: '/note/tag/findArticlesByTags',
    method: 'get',
    params: {
      tagsName
    }
  })
}

export function searchNotesByName (keyword) {
  return request({
    url: '/note/content/queryContentsByKeyword',
    method: 'post',
    params: {
      keyword
    }
  })
}
