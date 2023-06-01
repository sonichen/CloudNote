import { request } from '@/utils/request'

export function getUserInfo () {
  return request({
    url: '/note/user/information',
    method: 'post',
    data: {

    }
  })
}

export function updateImage () {
  return request({
    url: '/note/user/upload',
    method: 'post',
    data: {

    }
  })
}

export function updateInfo (userInfo) {
  return request({
    url: '/note/user/update',
    method: 'post',
    data: userInfo
  })
}

export function updatePassword (passwordInfo) {
  return request({
    url: '/note/user/updatePwd',
    method: 'post',
    params: {
      oldPwd: passwordInfo.oldpassword,
      newPwd: passwordInfo.password,
      checkNewPwd: passwordInfo.repassword
    }
  })
}
