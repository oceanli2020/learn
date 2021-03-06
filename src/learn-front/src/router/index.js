import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/login',
    meta: {
      title: '登录',
      noCache: true
    },
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/',
    name: '/',
    meta: {
      title: '首页',
      noCache: true
    },
    component: () => import('@/views/homepage/index'),
    hidden: true
  },
  {
    path: '/register',
    meta: {
      title: '注册',
      noCache: true
    },
    component: () => import('@/views/register/index'),
    hidden: true
  },
  {
    path: '/personal',
    meta: {
      title: '个人主页',
      noCache: true
    },
    component: () => import('@/views/personal/index'),
    hidden: true
  },
  {
    path: '/course',
    meta: {
      title: '课程列表',
      noCache: true
    },
    component: () => import('@/views/course/index'),
    hidden: true
  },
  {
    path: '/video',
    meta: {
      title: '视频播放页面',
      noCache: true
    },
    component: () => import('@/views/video/index'),
    hidden: true
  },
  {
    path: '/creation',
    meta: {
      title: '创作中心',
      noCache: true
    },
    component: () => import('@/views/creation/index'),
    hidden: true
  },
  {
    path: '/livecenter',
    meta: {
      title: '直播中心',
      noCache: true
    },
    component: () => import('@/views/livecenter/index'),
    hidden: true
  },
  {
    path: '/livelist',
    meta: {
      title: '直播列表',
      noCache: true
    },
    component: () => import('@/views/livelist/index'),
    hidden: true
  },
  {
    path: '/livestudio',
    meta: {
      title: '直播画面',
      noCache: true
    },
    component: () => import('@/views/livestudio/index'),
    hidden: true
  }
]

export default new Router({
  mode: 'history',
  scrollBehavior: () => ({
    y: 0
  }),

  routes: constantRouterMap
})
