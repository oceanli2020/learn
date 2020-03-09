import axios from 'axios'
import store from '@/store'
import router from '@/router'
import {
  getToken,
  setToken,
  isTokenExpired,
  getRefreshToken
} from '@/utils/auth'
import { refreshToken } from '@/api/login'
import { baseURL, prefixURL } from '@/utils/global'
import { MessageBox, Message, Notification } from 'element-ui'

var CancelToken = axios.CancelToken

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
      config.cancelToken = new CancelToken(c => {
        store.commit('SET_REQUEST_LISTS', c)
      })

      // 判断token是否将要过期
      if (
        isTokenExpired() &&
        config.url.indexOf(prefixURL + 'token') === -1 &&
        config.url.indexOf(prefixURL + 'config/list') === -1
      ) {
        // 判断是否正在刷新
        if (!window.isRefreshing) {
          // 将刷新token的标志置为true
          window.isRefreshing = true
          // 发起刷新token的请求
          refreshToken({ refreshToken: getRefreshToken() })
            .then(res => {
              // 将标志置为false
              window.isRefreshing = false
              // 成功刷新token
              config.headers['Authorization'] = res
              setToken(res)
              // 执行数组里的函数,重新发起被挂起的请求
              onRrefreshed(res)
              // 执行onRefreshed函数后清空数组中保存的请求
              refreshSubscribers = []
            })
            .catch(err => {
              console.log(err)
              store.dispatch('LogOut').then(() => {
                location.reload() // 为了重新实例化vue-router对象 避免bug
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
    // 下载请求处理
    if (response.config.responseType === 'arraybuffer') {
      var disposition = response.headers['content-disposition']
      var fileName = decodeURI(
        disposition.substring(
          disposition.indexOf('fileName=') + 9,
          disposition.length
        )
      )
      const blob = new Blob([response.data])
      const link = document.createElement('a')
      link.href = window.URL.createObjectURL(blob)
      link.download = fileName
      link.click()
      link.remove()
      return ''
    }

    const code = response.status
    if (code < 200 || code > 300) {
      Message.error({
        message: response.message
        // showClose:true
      })
      return Promise.reject(new Error('error'))
    } else {
      return response.data.data
    }
  },
  error => {
    console.log(error)
    let code = 0
    const status = error.response.status
    if (error.config.responseType === 'arraybuffer') {
      const enc = new TextDecoder('utf-8')
      error.response.data = JSON.parse(
        enc.decode(new Uint8Array(error.response.data))
      )
    }
    try {
      code = error.response.data.code
    } catch (e) {
      if (error.toString().indexOf('timeout')) {
        Message.error({
          message: '请求超时',
          // showClose:true,
          duration: 2500
        })
        return Promise.reject(error)
      }
    }
    // 原框架用401判断
    if (code === -401 || code === 'auth:unauthorized') {
      MessageBox.confirm(
        '登录状态已过期，您可以继续留在该页面，或者重新登录',
        '系统提示',
        {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        store.dispatch('LogOut').then(() => {
          location.reload() // 为了重新实例化vue-router对象 避免bug
        })
      })
    }
    if (status === 403) {
      // console.log('403')
      router.push({
        path: '/401'
      })
    }

    Notification.error({
      title: error.response.data.message,
      message: error.response.data.data
    })

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
