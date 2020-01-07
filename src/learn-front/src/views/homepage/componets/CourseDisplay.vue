<template>
  <div class="course-display">
    <div class="row" style="margin-top:60px;margin-bottom:60px">
      <el-row :gutter="100" type="flex" justify="center">
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img
                class="course-img"
                src="@/assets/courses/course1.jpg"
              />计算机科学与技术
            </div></el-link
          ></el-col
        >
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img class="course-img" src="@/assets/courses/course2.jpg" />人工智能
            </div></el-link
          ></el-col
        >
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img class="course-img" src="@/assets/courses/course3.jpg" />机器学习
            </div></el-link
          ></el-col
        >
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img class="course-img" src="@/assets/courses/course4.jpg" />云计算
            </div></el-link
          ></el-col
        >
      </el-row>
    </div>
    <div class="row">
      <el-row :gutter="100" type="flex" justify="center">
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img
                class="course-img"
                src="@/assets/courses/course5.jpg"
              />大数据
            </div></el-link
          ></el-col
        >
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img class="course-img" src="@/assets/courses/course6.jpg" />设计模式
            </div></el-link
          ></el-col
        >
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img class="course-img" src="@/assets/courses/course7.jpg" />计算机通信与网络
            </div></el-link
          ></el-col
        >
        <el-col :span="4"
          ><el-link href="#" underline="false" target="_blank"
            ><div class="grid-content">
              <img class="course-img" src="@/assets/courses/course8.jpg" />操作系统
            </div></el-link
          ></el-col
        >
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CourseDisplay',
  data () {
    return {

    }
  }
}
</script>
<style scoped>
/* scoped  即下列css代码只在当前组件有效 */
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 320px;
}
.course-img {
  height: 250px;
  width: 100%;
}
.row {
  /* 去除下方滚动条 */
  overflow: hidden;
}
</style>
