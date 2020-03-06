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
  </div>
</template>

<script scoped>
export default {
  name: 'Header',
  data() {
    return {}
  },
  methods: {}
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
</style>
