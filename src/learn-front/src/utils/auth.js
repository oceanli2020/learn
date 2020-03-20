import Cookies from 'js-cookie'
import jwt from 'jsonwebtoken'

const TokenKey = 'token'
const refreshTokenKey = 'refreshToken'

export function setToken(token) {
  return Cookies.set(TokenKey, token, { expires: 7 }) // 7天后过期,默认是关闭浏览器后失效
}
export function getToken() {
  return Cookies.get(TokenKey)
}
export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function setRefreshToken(refreshToken) {
  return Cookies.set(refreshTokenKey, refreshToken, { expires: 7 })
}

export function getRefreshToken() {
  return Cookies.get(refreshTokenKey)
}

export function removeRefreshToken() {
  return Cookies.remove(refreshTokenKey)
}
export function isTokenExpired() {
  const token = Cookies.get(TokenKey)
  const decoded = jwt.decode(token, { complete: true })
  const exp = decoded.payload.exp
  const now = new Date().getTime() / 1000
  const lessTime = 5 * 6
  const moreTime = 10 * 60
  return exp - now < lessTime && now < exp + moreTime
}
