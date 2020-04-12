<template>
  <div class="homepage">
    <el-container>
      <el-header height="80px">
        <horizontal-menu />
      </el-header>
      <el-main>
        <carousel />
      </el-main>
    </el-container>
    <el-container>
      <el-footer style="height:200px">
        <home-footer />
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import Carousel from './componets/Carousel'
import HorizontalMenu from './componets/HorizontalMenu'
import HomeFooter from './componets/HomeFooter'
export default {
  name: 'Homepage',
  components: {
    Carousel,
    HorizontalMenu,
    HomeFooter
  },
  data() {
    return {}
  }
}
</script>

<style scoped>
.el-header {
  line-height: 60px;
  /* background-color: bisque; */
}
.el-main {
  height: 1100px;
}
.el-footer {
  background-color: #606266;
  line-height: 60px;
}
</style>
