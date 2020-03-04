import axios from 'axios'
import store from '@/store'
import { getToken, setToken, getRefreshToken } from '@/utils/auth'
import { refreshToken } from '@/api/login'

// create an axios instance
const service = axios.create({
  baseURL: 'http://localhost:8088' // api 的 base_url  use proxy table
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (store.getters.token) {
      // 让每个请求携带token
      config.headers.Authorization = getToken()
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)
let retryRequest = [] // 存放token 过期的请求
let isRefresh = false // 是否在请求新的token
// response interceptor
service.interceptors.response.use(
  res => {
    return res
  },
  error => {
    if (error.response.data.code === -401) {
      if (!isRefresh) {
        isRefresh = true
        return new Promise((resolve, reject) => {
          refreshToken(getRefreshToken()).then(response => {
            // 这里是去请求新的token 并返回promise 然后保存新的token
            const data = response.data
            store.commit(' SET_TOKEN', data.data.token)
            setToken(data.data)
            error.response.config.headers.Authorization = data.data
            retryRequest.forEach(cb => {
              cb(data.data)
            })
            isRefresh = false
            retryRequest = []
            resolve(axios(error.response.config))
          })
        })
      } else {
        return new Promise(resolve => {
          // 将resolve放进队列，用一个函数形式来保存，等token刷新后直接执行
          retryRequest.push(token => {
            error.response.config.headers.Authorization = token
            resolve(axios(error.response.config))
          })
        })
      }
    }
    return Promise.reject(error)
  }
)

export default service
