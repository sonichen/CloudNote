import { request } from '@/utils/request'

// 获取笔记本列表
export function getNoteBookList () {
  return request({
    url: '/note/categories/findNoteBookList',
    method: 'get'
  })
}

// 新增笔记本
export function addNoteBook (data) {
  return request({
    url: '/note/categories/add',
    method: 'post',
    data
  })
}

// 删除笔记本
export function deleteNoteBook (id) {
  return request({
    url: '/note/categories/delete',
    method: 'post',
    params: { id }
  })
}

// 更新笔记本
export function updateNoteBook (head, id, value) {
  return request({
    url: '/note/categories/update',
    method: 'post',
    data: {
      head, // 头名
      id, // 笔记本id
      value // 笔记本名
    }
  })
}

// 重命名
export function renameNoteBook (id, value) {
  return request({
    url: '/note/categories/updateName',
    method: 'post',
    data: {
      id, // 笔记本id
      value // 笔记本名
    }
  })
}
