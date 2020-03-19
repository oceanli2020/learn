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
        <span style=" font-size: 14px; color:#787878 ">分类</span>
        <el-link :underline="false" style="margin-left:50px;" type="primary" >全部</el-link>
        <el-link :underline="false" style="margin-left:50px">Java</el-link>
        <el-link :underline="false" style="margin-left:50px">Python</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
      </div>
      <el-divider></el-divider>
      <div>
      <el-checkbox-group v-model="checkList">
        <!--vertical-align:top 把元素的顶端与行中最高元素的顶端对齐: -->
        <el-link :underline="false" style="margin-left:0px;margin-right:55px;vertical-align:top;">全部</el-link>
        <el-checkbox label="录播"></el-checkbox>
        <el-checkbox label="直播"></el-checkbox>
        <el-checkbox label="免费"></el-checkbox>
        <el-checkbox label="付费"></el-checkbox>
      </el-checkbox-group>
     </div>
     <el-divider></el-divider>
     <div>
        <el-link :underline="false" type="primary">综合排序</el-link>
        <el-link :underline="false" style="margin-left:20px">好评<i class="el-icon-caret-bottom"></i></el-link>
        <el-link :underline="false" style="margin-left:20px">人气<i class="el-icon-caret-bottom"></i></el-link>
        <el-link :underline="false" style="margin-left:20px;margin-right:20px">价格<i class="el-icon-caret-bottom"></i></el-link>
        <el-divider direction="vertical"></el-divider>
        <el-dropdown @command="handleCommand" show-timeout="0" hide-timeout="100">
        <!-- v-bind:用于属性绑定 -->
          <el-link :underline="false">
            <span>价格区间</span>
             <i class="el-icon-arrow-down el-icon--right"></i>
          </el-link>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="a">课程表</el-dropdown-item>
          <el-dropdown-item command="b">我的收藏</el-dropdown-item>
          <el-dropdown-item command="c">个人信息</el-dropdown-item>
          <el-dropdown-item command="d" divided>退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
     </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      checkList: []
    }
  },
  methods: {
    formatter(row, column) {
      return row.address
    }
  }
}
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
 .el-divider--horizontal{
   /* 修改分割线的上下外边距 */
     margin: 15px 0;
 }
</style>>
