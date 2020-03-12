import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function login(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: prefixURL + 'login',
    method: 'post',
    data
  })
}
export function refreshToken(refreshToken) {
  return request({
    url: prefixURL + 'token',
    method: 'get',
    params: { refreshToken }
  })
}
export function logout() {
  return request({
    url: 'logout',
    method: 'get'
  })
}
