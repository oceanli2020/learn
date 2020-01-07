import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [{
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
  meta: {
    title: '首页',
    noCache: true
  },
  component: () => import('@/views/homepage/index'),
  hidden: true
},
{
  path: '/retrieve',
  meta: {
    title: '找回密码',
    noCache: true
  },
  component: () => import('@/views/retrievepwd/index'),
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
