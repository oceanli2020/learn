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
  }
]

export default new Router({
  mode: 'history',
  scrollBehavior: () => ({
    y: 0
  }),

  routes: constantRouterMap
})
