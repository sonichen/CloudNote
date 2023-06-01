import { request } from '@/utils/request'

// 查看笔记的tag
export function getTags (contentId) {
  return request({
    url: '/note/tag/findTagsByArticle',
    method: 'get',
    params: { contentId }
  })
}

// 新增tag
export function addTag (contentId, tagsName) {
  return request({
    url: '/note/tag/add',
    method: 'post',
    data: { contentId, tagsName }
  })
}

// 删除tag
export function deleteTag (id) {
  return request({
    url: '/note/tag/delete',
    method: 'post',
    params: { id }
  })
}
