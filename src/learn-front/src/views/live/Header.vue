<template>
  <div>
    <span class="title">直播中心</span>
    <el-link type="info" class="brand" href="http://localhost:8080/" :underline="false">首页</el-link>
    <div class="info">
      <el-avatar :src="circleUrl" class="avatar"></el-avatar>
      <el-dropdown @command="handleCommand" show-timeout="0" hide-timeout="100">
        <el-tooltip
          v-bind:content="username"
          placement="right-end"
          effect="light"
          open-delay="700"
          class="item"
        >
          <el-link :underline="false">
            <span>{{ username | ellipsis }}</span>
            <i class="el-icon-arrow-down el-icon--right"></i>
          </el-link>
        </el-tooltip>
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
import { getImg } from '@/api/user'

export default {
  name: 'HorizontalMenu',
  data() {
    return {
      username: '',
      circleUrl: '',
      profilePhoto: ''
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      if (store.getters.token) {
        this.username = store.getters.username
        this.profilePhoto = store.getters.profilePhoto
        if (this.profilePhoto === null || this.profilePhoto === '') {
          this.circleUrl =
            'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
        } else {
          getImg(this.profilePhoto).then(res => {
            this.circleUrl = `data: image/jpeg;base64,${btoa(
              new Uint8Array(res).reduce(
                (data, byte) => data + String.fromCharCode(byte),
                ''
              )
            )}`
          })
        }
      }
    },
    handleCommand(command) {
      if (command === 'a') {
        this.$router.push({ path: '/personal', query: { index: '1' } })
      }
      if (command === 'b') {
        this.$router.push({ path: '/personal', query: { index: '2' } })
      }
      if (command === 'c') {
        this.$router.push({ path: '/personal', query: { index: '3' } })
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
  font-size: 13px;
  margin-left: 70px;
  margin-top: 20px;
}
.title {
  float: left;
  color: #0099ff;
  font-size: 18px;
  margin-left: 50px;
  margin-top: 15px;
}
.info {
  float: right;
  position: relative;
  bottom: 8px;
  margin-right: 140px;
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
