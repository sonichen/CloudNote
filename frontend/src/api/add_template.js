import { request } from '@/utils/request'

// 查询我的模板
export function findMyTemplates () {
  return request({
    url: '/note/template/findMyTemplate',
    method: 'get'
  })
}

// 查询系统模板
export function findSystemTemplates () {
  return request({
    url: '/note/template/findSystemTemplate',
    method: 'get'
  })
}

// 查询单个模板
export function findTemplate (id) {
  return request({
    url: '/note/template/findById',
    method: 'get',
    params: {
      id
    }
  })
}

// 添加模板
export function addTemplate ({ content, mode, title }) {
  return request({
    url: '/note/template/add',
    method: 'post',
    data: {
      content,
      mode,
      title
    }
  })
}

// 删除模板
export function deleteTemplate (id) {
  return request({
    url: '/note/template/delete',
    method: 'post',
    params: {
      id
    }
  })
}

// 更新模板
export function updateTemplate ({ id, content, mode, title }) {
  return request({
    url: '/note/template/update',
    method: 'post',
    data: {
      id,
      content,
      mode,
      title
    }
  })
}
