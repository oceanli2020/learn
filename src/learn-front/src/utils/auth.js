import Cookies from 'js-cookie'
import jwt from 'jsonwebtoken'

const TokenKey = 'token'
const refreshTokenKey = 'refreshToken'

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}
export function getToken() {
  return Cookies.get(TokenKey)
}
export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function setRefreshToken(refreshToken) {
  return Cookies.set(refreshTokenKey, refreshToken)
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
  const lessTime = 5 * 60
  const moreTime = 20 * 60
  return exp - now < lessTime && now < exp + moreTime
}
