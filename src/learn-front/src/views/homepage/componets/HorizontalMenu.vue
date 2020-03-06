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
      <el-input v-model="input" placeholder="请输入内容" class="select"></el-input>
      <el-button type="info" style="margin-right:100px" icon="el-icon-search">搜索</el-button>
      <el-button type="success" style="margin-right:10px;margin-top:15px" @click="test">开课</el-button>
      <el-button type="primary" @click="toLogin">登录 &nbsp;| &nbsp; 注册</el-button>
    </div>
    <div v-else>
      <el-input v-model="input" placeholder="请输入内容" class="select"></el-input>
      <el-button type="info" style="margin-right:100px" icon="el-icon-search">搜索</el-button>
      <el-button type="success" style="margin-right:10px;margin-top:15px" @click="test">开课</el-button>
      <el-dropdown @command="handleCommand">
        <el-button type="primary">
          <span>{{username | ellipsis}}</span>
          <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="a">登出</el-dropdown-item>
          <el-dropdown-item command="b">测试</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script scoped>
import store from '@/store'
import { info } from '@/api/login'
export default {
  name: 'HorizontalMenu',
  inject: ['reload'],
  data() {
    return {
      input: '',
      username: '',
      change: true
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
      if (store.getters.username) {
        this.username = store.getters.username
        this.change = false
      }
    },
    toLogout() {
      this.$store
        .dispatch('logout', '')
        .then(() => {
          // this.reload()
          location.reload()
        })
        .catch(() => {})
    },
    handleCommand(command) {
      if (command === 'a') {
        this.toLogout()
      }
      if (command === 'b') {
      }
    },
    test() {
      info()
        .then(res => {
          alert(res.data.data.userName)
        })
        .catch(err => {
          alert('请登录后操作')
          console.log(err)
        })
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
</style>
