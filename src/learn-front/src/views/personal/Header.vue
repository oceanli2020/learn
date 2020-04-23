<template>
  <div style="margin-left:230px;margin-right:200px;margin-top:10px">
    <el-link type="primary" class="brand" :underline="false" href="http://localhost:8080/">在线教育平台</el-link>
    <el-link class="live" type="info" :underline="false" @click="getLiveList">直播</el-link>
    <el-link class="course" type="info" :underline="false" @click="getCourseList">课程</el-link>
    <div class="search-input">
      <el-input v-model="input" placeholder="请输入内容" class="search" size="medium"></el-input>
      <el-button type="info" icon="el-icon-search" size="medium" @click="search" class="button">搜索</el-button>
    </div>
    <div v-if="change" class="login">
      <el-link :underline="false" @click="toLogin">登录 | 注册</el-link>
    </div>
    <div v-else class="login">
      <el-avatar :src="circleUrl" class="avatar"></el-avatar>
      <el-dropdown @command="handleCommand" show-timeout="0" hide-timeout="100">
        <el-tooltip
          v-bind:content="username"
          :disabled="tooltip"
          placement="right-end"
          :open-delay="700"
          class="user_name"
        >
          <el-link :underline="false">
            <span>{{username | ellipsis}}</span>
            <i class="el-icon-arrow-down el-icon--right"></i>
          </el-link>
        </el-tooltip>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="a">个人中心</el-dropdown-item>
          <el-dropdown-item command="b">创作中心</el-dropdown-item>
          <el-dropdown-item command="c">直播中心</el-dropdown-item>
          <el-dropdown-item command="d" divided>退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import store from '@/store'
export default {
  name: 'HorizontalMenu',
  data() {
    return {
      input: '',
      username: '',
      change: true,
      circleUrl: '',
      profilePhoto: '',
      tooltip: true
    }
  },
  mounted() {
    this.info()
  },
  watch: {
    '$store.getters.username': function() {
      this.username = store.getters.username
    },
    '$store.getters.profilePhoto': function() {
      this.profilePhoto = store.getters.profilePhoto
      this.circleUrl = 'http://localhost:9091' + this.profilePhoto
    }
  },
  methods: {
    toLogin() {
      this.$router.push('/login')
    },
    info() {
      if (store.getters.token) {
        this.username = store.getters.username
        if (this.username.length > 5) {
          this.tooltip = false
        }
        this.profilePhoto = store.getters.profilePhoto
        if (this.profilePhoto === null || this.profilePhoto === '') {
          this.circleUrl =
            'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
        } else {
          this.circleUrl = 'http://localhost:9091' + this.profilePhoto
        }
        this.change = false
      }
    },
    toLogout() {
      this.$store
        .dispatch('logout')
        .then(() => {
          this.$router.push('/')
        })
        .catch(() => {})
    },
    handleCommand(command) {
      if (command === 'a') {
        this.$router.push({ path: '/personal', query: { index: '3' } })
      }
      if (command === 'b') {
        this.$router.push('/creation')
      }
      if (command === 'c') {
        this.$router.push('/livecenter')
      }
      if (command === 'd') {
        this.toLogout()
      }
    },
    search() {
      this.$router.push('/course')
    },
    getCourseList() {
      this.$router.push('/course')
    },
    getLiveList() {
      this.$router.push('/livelist')
    }
  },
  filters: {
    ellipsis(value) {
      if (!value) return ''
      if (value.length > 5) {
        return value.slice(0, 5) + '...'
      }
      return value
    }
  }
}
</script>

<style scoped>
.brand {
  float: left;
  width: 125px;
  color: #409eff;
  font-size: 20px;
  /* background-color: aqua; */
  height: 60px;
  position: relative;
  left: 55px;
}
.search {
  width: 300px;
  position: relative;
  right: 120px;
}
.button {
  position: relative;
  right: 120px;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.avatar {
  /* 相对定位 */
  position: relative;
  top: 10px;
}
.search-input {
  /* background-color: aqua; */
  width: 590px;
  position: relative;
  left: 520px;
  height: 60px;
}
.login {
  /* background-color: aqua; */
  width: 150px;
  float: right;
  position: relative;
  bottom: 60px;
  right: 55px;
  height: 60px;
}
.user_name {
  position: relative;
  bottom: 5px;
}
.live {
  float: left;
  position: relative;
  left: 100px;
  /* background-color: aqua; */
  font-size: 15px;
}
.course {
  float: left;
  position: relative;
  left: 120px;
  /* background-color: aqua; */
  font-size: 15px;
}
</style>
