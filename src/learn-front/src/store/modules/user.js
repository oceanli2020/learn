import { login, logout } from '@/api/login'
import {info} from '@/api/user'
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
    requestLists: []
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
    SET_REQUEST_LISTS: (state, requestList) => {
      state.requestLists.push(requestList)
    },
    RESET_REQUEST_LISTS: state => {
      state.requestLists = []
    }
  },

  actions: {
    // 用户名登录
    login({ commit }, userInfo) {
      const username = userInfo.username
      const password = userInfo.password
      return new Promise((resolve, reject) => {
        login(username, password)
          .then(response => {
            const data = response.data
            if (data.code === 200) {
              setToken(data.data.token)
              setRefreshToken(data.data.refreshToken)
              commit('SET_TOKEN', data.data.token) // 将token保存到cookie里 -> 作为前端用户已登录的标识
              setUserInfo(data, commit)
            }
            resolve(response)
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
            setUserInfo(res, commit)
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
export const setUserInfo = (data, commit) => {
  commit('SET_USER_NAME', data.data.userDTO.userName)
  commit('SET_REAL_NAME', data.data.userDTO.realName)
  commit('SET_EMAIL', data.data.userDTO.email)
  commit('SET_PHONE_NUMBER', data.data.userDTO.phoneNumber)
  commit('SET_PROFILE_PHOTO', data.data.userDTO.profilePhoto)
}
export default user
