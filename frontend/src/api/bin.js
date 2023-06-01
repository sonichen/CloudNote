import { request } from '@/utils/request'

// 查看回收站内容
export function getBinList () {
  return request({
    url: '/note/recycleBin/findNoteBookListInRecycleBin',
    method: 'get'
  })
}

// 恢复笔记本
export function recoverNoteBook (id) {
  return request({
    url: '/note/recycleBin/noteBookBack2System',
    method: 'post',
    params: {
      id
    }
  })
}

// 恢复笔记
export function recoverNote (id) {
  return request({
    url: '/note/recycleBin/notesBack2System',
    method: 'post',
    params: {
      id
    }
  })
}

// 永久删除笔记本
export function deleteNoteBook (id) {
  return request({
    url: '/note/recycleBin/deleteNoteBook',
    method: 'post',
    params: {
      id
    }
  })
}

// 永久删除笔记
export function deleteNote (id) {
  return request({
    url: '/note/recycleBin/moveNotesFromSystem',
    method: 'post',
    params: {
      id
    }
  })
}
