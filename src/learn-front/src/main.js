// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import store from './store'
import Video from 'video.js'
import '@/icons' // icon
import locale from 'element-ui/lib/locale/lang/en' // element的国际化
import 'element-ui/lib/theme-chalk/index.css'
import 'video.js/dist/video-js.css'
import 'videojs-flash'
Vue.prototype.$video = Video

/* eslint-disable */
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
