// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import store from './store'
import locale from 'element-ui/lib/locale/lang/en' // element的国际化
import 'element-ui/lib/theme-chalk/index.css'

/* var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8088/'
Vue.prototype.$axios = axios */

Vue.use(ElementUI, {
  locale
})
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})
