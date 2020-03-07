import request from '@/utils/request'

var adminPath = '/api/v1'

export function info() {
  return request({
    url: adminPath + '/sys/user',
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
    url: adminPath + '/sys/user/register',
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
    url: adminPath + '/sys/user',
    method: 'put',
    data
  })
}
export function checkPass(passwordInput) {
  return request({
    url: adminPath + '/sys/user/checkpass',
    method: 'get',
    params: { passwordInput }
  })
}
