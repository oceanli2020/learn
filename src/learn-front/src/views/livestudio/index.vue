<template>
  <div class="course">
    <el-container>
      <el-header height="80px">
        <Header />
      </el-header>
      <el-main>
        <Main />
      </el-main>
      <el-footer style="height:100px">
        <Footer />
      </el-footer>
    </el-container>
  </div>
</template>
<script>
import Header from './Header'
import Footer from './Footer'
import Main from './Main'
export default {
  name: 'LiveStudio',
  components: {
    Header,
    Footer,
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
  /* background-color: aquamarine; */
}
.el-main {
  min-height: 1500px;
}
.el-footer {
  background-color: #606266;
  line-height: 60px;
}
</style>
