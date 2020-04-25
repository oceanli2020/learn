<template>
  <div class="course">
    <el-container>
      <el-header height="80px">
        <Header :sup_this="sup_this" />
      </el-header>
      <el-main>
        <Main @childFn="parentFn" />
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
  name: 'Course',
  components: {
    Header,
    Footer,
    Main
  },
  data() {
    return {
      sup_this: null
    }
  },
  methods: {
    parentFn(val) {
      this.sup_this = val
    }
  }
}
</script>

<style scoped>
.el-header {
  line-height: 60px;
}
.el-main {
  height: 1000px;
}
.el-footer {
  background-color: #606266;
  line-height: 60px;
}
</style>
