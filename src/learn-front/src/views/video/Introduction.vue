<template>
  <div class="introduction">
    <div class="info">
      <el-avatar :size="70" :src="circleUrl" class="avatar"></el-avatar>
      <span class="author">{{userName}}</span>
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
    },
    userName: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      children_this: this,
      circleUrl:
        'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      this.$emit('childFnIntroduction', this.children_this)
    },
    getCircleUrl(val) {
      this.circleUrl = 'http://localhost:9091' + val
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
  min-height: 45px;
  /* background-color: bisque; */
}
.title {
  font-size: 13px;
  color: #585858;
  float: left;
  position: relative;
  top: 3px;
  height: 22px;
  /* background-color: aqua; */
}
.name-content {
  font-size: 13px;
  margin-left: 30px;
}
.introduction-block {
  margin-top: 5px;
  width: 700px;
}
.introduction-content {
  font-size: 13px;
  margin-left: 83px;
  top: 3px;
  position: relative;
  word-break: break-all;
}
</style>
