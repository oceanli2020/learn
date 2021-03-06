import axios from 'axios'
import store from '@/store'
import router from '@/router'
import { Notification } from 'element-ui'
import {
  getToken,
  setToken,
  isTokenExpired,
  getRefreshToken
} from '@/utils/auth'
import { refreshToken } from '@/api/login'
import { baseURL, prefixURL } from '@/utils/global'

// 是否有请求正在刷新token
window.isRefreshing = false

// 被挂起的请求数组
let refreshSubscribers = []

// 创建axios实例
const service = axios.create({
  headers: { 'Content-Type': 'application/json' },
  timeout: 300000, // 请求超时时间
  withCredentials: true
})
// request interceptor
service.interceptors.request.use(
  config => {
    config.baseURL = baseURL
    if (getToken()) {
      config.headers['Authorization'] = getToken()
      // 判断token是否将要过期
      if (isTokenExpired() && config.url.indexOf(prefixURL + 'token') === -1) {
        // 判断是否正在刷新
        if (!window.isRefreshing) {
          // 将刷新token的标志置为true
          window.isRefreshing = true
          // 发起刷新token的请求
          refreshToken(getRefreshToken())
            .then(res => {
              // 将标志置为false
              window.isRefreshing = false
              // 成功刷新token
              config.headers['Authorization'] = res.data
              setToken(res.data)
              // 执行数组里的函数,重新发起被挂起的请求
              onRrefreshed(res.data)
              // 执行onRefreshed函数后清空数组中保存的请求
              refreshSubscribers = []
            })
            .catch(err => {
              console.log(err)
              store.dispatch('logout').then(() => {
                // location.reload() // 为了重新实例化vue-router对象 避免bug
                router.push('/login')
                Notification.error({
                  title: '失败',
                  showClose: true,
                  duration: 10000,
                  message: '登录状态已过期，请重新登录'
                })
              })
            })
        }
        // 把请求(token)=>{....}都push到一个数组中
        const retry = new Promise((resolve, reject) => {
          // (token) => {...}这个函数就是回调函数
          subscribeTokenRefresh(token => {
            config.headers['Authorization'] = token
            // 将请求挂起
            resolve(config)
          })
        })
        return retry
      }
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)
// response 拦截器
service.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    let code = error.response.data.code
    let status = error.response.status
    let message = error.response.data.message
    if (code === -401 && status === 401) {
      store.dispatch('logout').then(() => {
        router.push('/login')
        Notification.error({
          title: '失败',
          showClose: true,
          duration: 10000,
          message: '登录状态已过期，请重新登录'
        })
      })
    }
    if (message === 'token为空' && status === 500) {
      Notification.warning({
        title: '警告',
        showClose: true,
        message: '无权限，请登陆后操作'
      })
    }
    if (status === 400) {
      Notification.error({
        title: '错误',
        showClose: true,
        message: '无效操作'
      })
    }
    return Promise.reject(error)
  }
)
// push所有请求到数组中
function subscribeTokenRefresh(cb) {
  refreshSubscribers.push(cb)
}

// 刷新请求（refreshSubscribers数组中的请求得到新的token之后会自执行，用新的token去请求数据）
function onRrefreshed(token) {
  refreshSubscribers.map(cb => cb(token))
}
export default service
