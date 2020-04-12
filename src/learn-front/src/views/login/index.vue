<template>
  <div class="login">
    <el-container>
      <el-header height="80px">
        <Header />
      </el-header>
      <el-main>
        <Main />
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from './Header'
import Main from './Main'
export default {
  name: 'Login',
  components: {
    Header,
    Main
  },
  data() {
    return {}
  }
}
</script>

<style scoped>
.el-header {
  line-height: 60px;
}
</style>
