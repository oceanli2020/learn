<template>
  <div style="margin-left:100px;margin-right:100px">
      <el-link type="primary" class="brand" href="http://localhost:8080/">在线教育平台</el-link>
    <div class="left-menu" style="cursor: pointer; margin-right:300px">
      <el-menu class="el-menu" mode="horizontal">
        <el-menu-item>处理中心</el-menu-item>
        <el-menu-item>处理中心</el-menu-item>
        <el-menu-item>处理中心</el-menu-item>
        <el-menu-item>处理中心</el-menu-item>
        <el-menu-item>处理中心</el-menu-item>
      </el-menu>
    </div>
    <div>
      <el-input v-model="input" placeholder="请输入内容" class="select">
      </el-input>
      <el-button type="info" style="margin-right:100px" icon="el-icon-search"
        >搜索</el-button
      >
      <el-button type="primary" @click="toLogin">登录 &nbsp;| &nbsp; 注册</el-button>
    </div>
  </div>
</template>

<script scoped>
export default {
  name: 'HorizontalMenu',
  data () {
    return {
      input: ''
    }
  },
  methods: {
    toLogin () {
      this.$router.push('login')
    }
  }
}
</script>

<style>
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
</style>
