<template>
  <div class="main">
    <div class="center">
      <div style="padding-top:20px;padding-bottom:20px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/course' }">全部课程</el-breadcrumb-item>
          <el-breadcrumb-item v-for="bread in breadList" :key="bread">
            <el-link :underline="false" @click="changePath(bread.id)">{{bread.name}}</el-link>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="video"></div>
    </div>
  </div>
</template>

<script>
import { getCourseInfo } from '@/api/course'
export default {
  name: 'Main',
  data() {
    return {
      breadList: [],
      courseId: this.$route.query.id
    }
  },
  mounted() {
    this.info()
  },
  watch: {},
  methods: {
    info() {
      getCourseInfo(this.courseId).then(res => {
        this.breadList = res.data.courseTypeList
        this.breadList.push({ name: res.data.course.name })
      })
    },
    changePath(val) {
      if (this.breadList[this.breadList.length - 1].id === val) return
      this.$store.commit('SET_COURSETYPE_ID', val)
      this.$router.push('/course')
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
.video {
  height: 500px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  /* background-color: coral; */
}
</style>>
