<template>
  <div class="homepage">
    <el-container>
      <el-header><horizontal-menu /></el-header>
      <el-main><carousel /></el-main>
    </el-container>
    <el-container>
      <el-main><course-display /></el-main>
      <el-footer style="height:200px"><home-footer /></el-footer>
    </el-container>
  </div>
</template>

<script>
import Carousel from './componets/Carousel'
import HorizontalMenu from './componets/HorizontalMenu'
import CourseDisplay from './componets/CourseDisplay'
import HomeFooter from './componets/HomeFooter'
export default {
  name: 'Homepage',
  components: {
    Carousel,
    HorizontalMenu,
    CourseDisplay,
    HomeFooter
  },
  data () {
    return {

    }
  }
}
</script>

<style scoped>
.el-header {
  text-align: center;
  line-height: 60px;
}
.el-main {
  height: 900px;
}
.el-footer {
  background-color: #606266;
  line-height: 60px;
}
</style>
