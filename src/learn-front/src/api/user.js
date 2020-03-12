import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function info() {
  return request({
    url: prefixURL + 'sys/user',
    method: 'get'
  })
}
export function register(userName, password, email, phoneNumber) {
  const data = {
    userName,
    password,
    email,
    phoneNumber
  }
  return request({
    url: prefixURL + 'sys/user/register',
    method: 'post',
    data
  })
}

export function update(
  userName,
  profilePhoto,
  realName,
  email,
  phoneNumber,
  password
) {
  const data = {
    userName,
    password,
    email,
    phoneNumber,
    profilePhoto,
    realName
  }
  return request({
    url: prefixURL + 'sys/user',
    method: 'put',
    data
  })
}
export function checkPass(passwordInput) {
  return request({
    url: prefixURL + 'sys/user/checkpass',
    method: 'get',
    params: { passwordInput }
  })
}

export function uploadAvatar(data) {
  return request({
    url: prefixURL + 'upload/avatar',
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    data
  })
}
export function getImg(name) {
  return request({
    url: name,
    method: 'get'
  })
}
