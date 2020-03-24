import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import course from './modules/course'
import getters from './getters'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user,
    course
  },
  getters,
  plugins: [createPersistedState()] // 持久化 localStorage
})

export default store
