import { login, logout } from '@/api/login'
import { info } from '@/api/user'
import {
  getToken,
  setToken,
  setRefreshToken,
  removeToken,
  removeRefreshToken
} from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    username: '',
    realname: '',
    email: '',
    phoneNumber: '',
    profilePhoto: '',
    breadList: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_USER_NAME: (state, username) => {
      state.username = username
    },
    SET_REAL_NAME: (state, realname) => {
      state.realname = realname
    },
    SET_EMAIL: (state, email) => {
      state.email = email
    },
    SET_PHONE_NUMBER: (state, phoneNumber) => {
      state.phoneNumber = phoneNumber
    },
    SET_PROFILE_PHOTO: (state, profilePhoto) => {
      state.profilePhoto = profilePhoto
    },
    SET_BREAD_LIST: (state, breadList) => {
      state.breadList = breadList
    }
  },

  actions: {
    // 用户名登录
    login({ commit }, userInfo) {
      const username = userInfo.username
      const password = userInfo.password
      return new Promise((resolve, reject) => {
        login(username, password)
          .then(res => {
            if (res.code === 200) {
              setToken(res.data.token)
              setRefreshToken(res.data.refreshToken)
              commit('SET_TOKEN', res.data.token) // 将token保存到cookie里 -> 作为前端用户已登录的标识
              setUserInfo(res.data.userDTO, commit)
            }
            resolve(res)
          })
          .catch(error => {
            // 登录失败，回传提示信息
            reject(error)
          })
      })
    },
    // 获取用户信息
    getInfo({ commit }) {
      return new Promise((resolve, reject) => {
        info()
          .then(res => {
            setUserInfo(res.data, commit)
            resolve(res)
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    // 登出
    logout({ commit }) {
      return new Promise((resolve, reject) => {
        commit('SET_TOKEN', '')
        commit('SET_USER_NAME', '')
        commit('SET_REAL_NAME', '')
        commit('SET_EMAIL', '')
        commit('SET_PHONE_NUMBER', '')
        commit('SET_PROFILE_PHOTO', '')
        removeToken()
        removeRefreshToken()
        logout()
          .then(() => {
            resolve()
          })
          .catch(error => {
            reject(error)
          })
      })
    }
  }
}
export const setUserInfo = (res, commit) => {
  commit('SET_USER_NAME', res.userName)
  commit('SET_REAL_NAME', res.realName)
  commit('SET_EMAIL', res.email)
  commit('SET_PHONE_NUMBER', res.phoneNumber)
  commit('SET_PROFILE_PHOTO', res.profilePhoto)
}
export default user
