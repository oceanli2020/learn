import { login, logout } from '@/api/login'
import {
  getToken,
  getRefreshToken,
  setToken,
  setRefreshToken,
  removeToken,
  removeRefreshToken
} from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    refreshToken: getRefreshToken(),
    username: ''
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_REFRESH_TOKEN: (state, refreshToken) => {
      state.refresh_token = refreshToken
    },
    SET_USER_NAME: (state, username) => {
      state.username = username
    }
  },

  actions: {
    // 用户名登录
    login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password)
          .then(response => {
            const data = response.data
            if (data.code === 200) {
              commit('SET_TOKEN', data.data.token) // 将token保存到cookie里 -> 作为前端用户已登录的标识
              commit('SET_REFRESH_TOKEN', data.data.refreshToken)
              setToken(data.data.token)
              setRefreshToken(data.data.refreshToken)
              commit('SET_USER_NAME', data.data.userDTO.userName)
            }
            resolve(response)
          })
          .catch(error => {
            // 登录失败，回传提示信息
            reject(error)
          })
      })
    },
    // 登出
    logout({ commit }, p) {
      return new Promise((resolve, reject) => {
        logout()
          .then(() => {
            commit('SET_TOKEN', '')
            commit('SET_REFRESH_TOKEN', '')
            commit('SET_USER_NAME', '')
            removeToken()
            removeRefreshToken()
            resolve()
          })
          .catch(error => {
            reject(error)
          })
      })
    }
  }
}

export default user
