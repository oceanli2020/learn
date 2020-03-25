<template>
  <div class="main">
    <!-- 回到顶部 -->
    <el-backtop></el-backtop>
    <div class="center">
      <div class="title">
        <span>{{title}}</span>
        <br />
        <span style="font-size:5px;color:#989898;">{{uploadDate}}</span>
      </div>
      <div class="breadcrumb">
        <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size:5px;">
          <el-breadcrumb-item :to="{ path: '/course' }">
            <el-link :underline="false">
              <span style="font-size:5px;vertical-align:top;">全部课程</span>
            </el-link>
          </el-breadcrumb-item>
          <el-breadcrumb-item v-for="bread in breadList" :key="bread">
            <el-link :underline="false" @click="changePath(bread.id)">
              <span style="font-size:5px;vertical-align:top;">{{bread.name}}</span>
            </el-link>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="video"></div>
      <div class="Introduction">
        <div style="padding-top:10px;padding-left:10px">
          <el-button :type="pointType" icon="el-icon-thumb" circle @click="pointIcon('point')"></el-button>
          <span class="icon_number">{{point}}</span>
          <el-button
            :type="starType"
            icon="el-icon-star-off"
            circle
            style="margin-left:30px"
            @click="pointIcon('star')"
          ></el-button>
          <span class="icon_number">{{star}}</span>
          <el-button type="success" icon="el-icon-share" circle style="margin-left:30px"></el-button>
          <span class="icon_number">{{share}}</span>
        </div>
        <div class="text">{{text}}</div>
      </div>
      <div class="comment">
        <div class="select">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="课程介绍" name="first"></el-tab-pane>
            <el-tab-pane label="目录" name="second"></el-tab-pane>
            <el-tab-pane label="评论" name="third"></el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCourseInfo, getParentsType } from '@/api/course'
export default {
  name: 'Main',
  data() {
    return {
      title: '01 尚硅谷Nginx教程',
      uploadDate: '2019-09-18 10:19:05',
      breadList: [],
      courseId: '',
      courseName: '',
      courseTypeId: '',
      point: 50,
      star: 20,
      share: 10,
      pointType: 'info',
      starType: 'info',
      activeName: 'third',
      text:
        '本视频从Nginx最基础的部分讲起，内容由浅入深，既适合初学者，也适合具备一定软件开发能力的人员。视频主要包含以下几部分内容：第一部分讲解Nginx最基本的概念，可以让大家对于Nginx有个全方位的认识。第二部分讲解Nginx软件的安装和基本的操作命令，让大家能够熟练安装和使用Nginx软件。第三部分是课程的重中之重，主要讲解Nginx的各种应用场景的配置，比如反向代理、负载均衡、动静分离，以及Nginx高可用的集群配置。第四部分讲解Nginx的原理，可以对Nginx有更深入的理解。'
    }
  },
  mounted() {
    this.info()
  },
  watch: {},
  methods: {
    async info() {
      this.courseId = this.$route.query.id
      await getCourseInfo(this.courseId).then(res => {
        this.courseName = res.data.course.name
        this.courseTypeId = res.data.course.courseTypeId
      })
      getParentsType(this.courseTypeId).then(res => {
        this.breadList = res.data
        this.breadList.push({ name: this.courseName })
      })
    },
    changePath(val) {
      if (this.breadList[this.breadList.length - 1].id === val) return
      this.$store.commit('SET_COURSETYPE_ID', val)
      this.$router.push('/course')
    },
    pointIcon(val) {
      if (val === 'point') {
        if (this.pointType === 'info') {
          this.pointType = 'primary'
        } else {
          this.pointType = 'info'
        }
      }
      if (val === 'star') {
        if (this.starType === 'info') {
          this.starType = 'primary'
        } else {
          this.starType = 'info'
        }
      }
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
.title {
  margin-top: 5px;
}
.breadcrumb {
  padding-top: 6px;
  padding-bottom: 15px;
}
.video {
  height: 500px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.Introduction {
  height: 150px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin-top: 20px;
  height: auto;
}
.comment {
  height: 1500px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin-top: 20px;
}
.icon_number {
  font-size: 10px;
  color: #787878;
  margin-left: 5px;
}
.text {
  font-size: 12px;
  color: #585858;
  width: 600px;
  padding-top: 10px;
  padding-left: 10px;
  padding-bottom: 10px;
  position: static;
}
.select {
  padding-left: 6px;
  padding-right: 6px;
}
</style>>
