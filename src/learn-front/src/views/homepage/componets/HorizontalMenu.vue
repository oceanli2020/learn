<template>
  <div style="margin-left:100px;margin-right:100px;margin-top:10px">
    <el-link type="primary" class="brand" href="http://localhost:8080/">在线教育平台</el-link>
    <div class="left-menu" style="cursor: pointer; margin-right:300px">
      <el-menu class="el-menu" mode="horizontal">
        <el-menu-item>功能优势</el-menu-item>
        <el-menu-item>企业合作</el-menu-item>
        <el-menu-item>帮助中心</el-menu-item>
      </el-menu>
    </div>
    <div v-if="change">
      <el-input v-model="input" placeholder="请输入内容" class="select" size="medium"></el-input>
      <el-button type="info" style="margin-right:120px" icon="el-icon-search" size="medium">搜索</el-button>
      <el-link :underline="false" style="margin-right:20px">开课</el-link>
      <el-link :underline="false" @click="toLogin">登录 | 注册</el-link>
      <!-- <el-button type="primary" @click="toLogin">登录 &nbsp;| &nbsp; 注册</el-button> -->
    </div>
    <div v-else>
      <el-input v-model="input" placeholder="请输入内容" class="select" size="medium"></el-input>
      <el-button type="info" style="margin-right:120px" icon="el-icon-search" size="medium">搜索</el-button>
      <el-link :underline="false" style="margin-right:20px">开课</el-link>
      <el-avatar :size="medium" :src="circleUrl" class="avatar"></el-avatar>
      <el-dropdown @command="handleCommand" show-timeout="0" hide-timeout="100">
        <!-- v-bind:用于属性绑定 -->
        <el-tooltip
          v-bind:content="username"
          placement="right-end"
          effect="light"
          open-delay="700"
          class="item"
        >
          <el-link :underline="false">
            <span>{{username | ellipsis}}</span>
          </el-link>b
        </el-tooltip>
        <i class="el-icon-arrow-down el-icon--right"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="a">课程表</el-dropdown-item>
          <el-dropdown-item command="b">我的收藏</el-dropdown-item>
          <el-dropdown-item command="c">个人信息</el-dropdown-item>
          <el-dropdown-item command="d" divided>退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import store from '@/store'
import {getImg} from '@/api/user'
export default {
  name: 'HorizontalMenu',
  data() {
    return {
      input: '',
      username: '',
      change: true,
      circleUrl: '',
      profilePhoto: ''
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    toLogin() {
      this.$router.push('/login')
    },
    info() {
      if (store.getters.token) {
        this.username = store.getters.username
        this.profilePhoto = store.getters.profilePhoto
        if (this.profilePhoto === null || this.profilePhoto === '') {
          this.circleUrl =
            'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
        } else {
          getImg(this.profilePhoto).then(res => {
            this.circleUrl = 'http://localhost:8088/' + this.profilePhoto
          })
        }
        this.change = false
      }
    },
    toLogout() {
      this.$store
        .dispatch('logout')
        .then(() => {
          location.reload()
        })
        .catch(() => {})
    },
    handleCommand(command) {
      if (command === 'a') {
      }
      if (command === 'b') {
      }
      if (command === 'c') {
        this.$router.push('/personal')
      }
      if (command === 'd') {
        this.toLogout()
      }
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
  min-width: 200px;
  text-align: center;
  color: #409eff;
  font-size: 20px;
}
.left-menu {
  float: left;
}

.select {
  width: 300px;
}

.el-icon-arrow-down {
  font-size: 12px;
}
.avatar {
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: 15px;
}
</style>
