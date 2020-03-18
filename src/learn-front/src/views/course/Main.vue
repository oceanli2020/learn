<template>
  <div class="main">
    <div class="center">
      <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:15px">
        <el-breadcrumb-item :to="{ path: '/' }">全部课程</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">IT·互联网</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">编程语言</el-breadcrumb-item>
        <el-breadcrumb-item>Javascript</el-breadcrumb-item>
      </el-breadcrumb>
      <el-divider></el-divider>
      <div>
        <span style=" font-size: 14px; color:#787878 ">分类 :</span>
        <el-link :underline="false" style="margin-left:70px">全部</el-link>
        <el-link :underline="false" style="margin-left:50px">Java</el-link>
        <el-link :underline="false" style="margin-left:50px">Python</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
      </div>
      <div style="margin-top:20px">
        <el-link :underline="false" style="margin-left:110px">Java</el-link>
        <el-link :underline="false" style="margin-left:50px">Python</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
      </div>
      <el-divider></el-divider>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
.center {
  height: 1000px;
  width: 1000px;
  /* background-color: aqua; */
  margin: 0 auto;
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: 0px;
}
</style>>
