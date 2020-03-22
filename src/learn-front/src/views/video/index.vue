<template>
  <div class="course">
    <el-container>
      <el-header>
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
  name: 'Video',
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
  text-align: center;
  line-height: 60px;
  /* background-color: blueviolet; */
}
.el-main {
  height: 1100px;
}
.el-footer {
  background-color: #606266;
  line-height: 60px;
}
</style>
