import { request } from '@/utils/request'

export function getNoteList (categoriesId) {
  return request({
    url: '/note/content/findNoteList',
    method: 'get',
    params: {
      categoriesId
    }
  })
}

export function getNoteContent (id) {
  return request({
    url: '/note/content/findById',
    method: 'get',
    params: {
      id
    }
  })
}

export function addNote (data) {
  return request({
    url: '/note/content/add',
    method: 'post',
    data
  })
}

export function deleteNote (id) {
  return request({
    url: '/note/content/delete',
    method: 'post',
    params: {
      id
    }
  })
}

export function moveNote (categoriesId, noteId) {
  return request({
    url: '/note/content/move',
    method: 'get',
    params: {
      categoriesId: categoriesId, // 头笔记本id
      noteId: noteId // 笔记id
    }
  })
}

export function updateNote (categoriesId, content, id, mode, star, title) {
  return request({
    url: '/note/content/update',
    method: 'post',
    data: {
      categoriesId, // 头笔记本id
      content, // 笔记本内容
      id, // 笔记id
      mode, // 模式
      star, // 是否收藏
      title // 标题
    }
  })
}

export function renameNote (id, title) {
  return request({
    url: '/note/content/update',
    method: 'post',
    data: {
      id, // 笔记id
      title // 标题
    }
  })
}

export function saveNote (content, id, mode) {
  return request({
    url: '/note/content/update',
    method: 'post',
    data: {
      content, // 笔记本内容
      id, // 笔记id
      mode // 模式
    }
  })
}

export function starNote (id, star) {
  return request({
    url: '/note/content/update',
    method: 'post',
    data: {
      id, // 笔记id
      star // 是否收藏
    }
  })
}
