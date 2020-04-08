<template>
  <div class="creation">
    <el-container>
      <el-header height=60px><Header /></el-header>
      <el-container style="background-color:#E8E8E8;">
        <el-aside width="200px"><Aside /></el-aside>
        <el-main><Main /></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Header from './Header'
import Aside from './Aside'
import Main from './Main'
export default {
  name: 'Creation',
  components: {
    Header,
    Aside,
    Main
  },
  data() {
    return {}
  }
}
</script>

<style scoped>
.el-header {
  text-align: center;

}
.el-main {
  height: 1000px;
  margin-top: 20px;

}
.el-aside {
  background-color: white;
  color: #333;
  text-align: center;
  height: 100%;
  margin-top: 20px;
}
</style>
