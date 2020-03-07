import request from '@/utils/request'

var adminPath = '/api/v1'

export function login(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: adminPath + '/login',
    method: 'post',
    data
  })
}
export function refreshToken(refreshToken) {
  return request({
    url: adminPath + '/token',
    method: 'get',
    params: { refreshToken }
  })
}
export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}
