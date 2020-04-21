<template>
  <div class="introduction">
    <div class="info">
      <el-avatar :size="70" :src="circleUrl" class="avatar"></el-avatar>
      <span class="author">我亦飘零久</span>
    </div>
    <div class="content">
      <div>
        <span class="title">课程名称</span>
        <span class="name-content">{{courseName}}</span>
      </div>
      <div class="introduction-block">
        <span class="title">简&#12288;&#12288;介</span>
        <div class="introduction-content">
          <span>{{introduction}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Introduction',
  props: {
    courseName: {
      type: String,
      default: ''
    },
    sup_this: {
      type: Object,
      default: null
    },
    introduction: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      circleUrl: ''
    }
  }
}
</script>

<style scoped>
.introduction {
  height: auto;
  /* background-color: aqua; */
}
.info {
  height: 71px;
  padding: 10px;
  /* background-color: bisque; */
}
.author {
  position: relative;
  left: 20px;
  bottom: 15px;
}
.content {
  padding: 10px 10px 15px 15px;
  margin-top: 5px;
  height: auto;
  /* background-color: bisque; */
}
.title {
  font-size: 13px;
  color: #585858;
  float: left;
  position: relative;
  top: 3px;
}
.name-content {
  font-size: 13px;
  margin-left: 10px;
}
.introduction-block {
  margin-top: 5px;
  width: 700px;
  /* background-color: brown; */
}
.introduction-content {
  font-size: 13px;
  margin-left: 63px;
  top: 3px;
  position: relative;
  word-break: break-all;
  /* background-color: brown; */
}
</style>
