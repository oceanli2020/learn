<template>
  <div class="main">
    <!-- 回到顶部 -->
    <el-backtop></el-backtop>
    <div class="center">
      <div class="title">
        <span>{{ title }}</span>
        <br />
        <span style="font-size: 5px; color: #989898;">{{ uploadDate }}</span>
      </div>
      <div class="breadcrumb">
        <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 5px;">
          <el-breadcrumb-item :to="{ path: '/course' }">
            <el-link :underline="false">
              <span style="font-size: 5px; vertical-align: top;">全部课程</span>
            </el-link>
          </el-breadcrumb-item>
          <el-breadcrumb-item v-for="bread in breadList" :key="bread">
            <el-link :underline="false" @click="changePath(bread.id)">
              <span style="font-size: 5px; vertical-align: top;">{{ bread.name }}</span>
            </el-link>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="video">
        <div class="warn">
          <video id="myVideo" class="video-js vjs-big-play-centered"></video>
          <span>{{warn}}</span>
        </div>
      </div>
      <div class="introduction">
        <div style="padding-top: 10px; padding-left: 10px;">
          <el-button :type="pointType" circle @click="pointIcon">
            <svg-icon icon-class="point"></svg-icon>
          </el-button>
          <span class="icon_number">{{ point==0?'点赞':point }}</span>
          <el-button :type="starType" circle style="margin-left: 20px;" @click="starIcon">
            <svg-icon icon-class="star"></svg-icon>
          </el-button>
          <span class="icon_number">{{ star==0?'收藏':star }}</span>
          <el-button
            :type="subType"
            icon="el-icon-s-flag"
            circle
            style="margin-left: 20px;"
            @click="subIcon"
          ></el-button>
          <span class="icon_number">{{ sub==0?'订阅':sub }}</span>
        </div>
        <div class="text">{{ text }}</div>
      </div>
      <div class="label">
        <div class="select">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="课程介绍" name="first">
              <introduction
                :sup_this="sup_this"
                :courseName="courseName"
                :introduction="courseIntroduction"
                :userName="userName"
                :courseCreateBy="courseCreateBy"
                @childFnIntroduction="parentFnIntroduction"
              />
            </el-tab-pane>
            <el-tab-pane label="目录" name="second">
              <directory :sup_this="sup_this" :directory="directory" :videoId="videoId" />
            </el-tab-pane>
            <el-tab-pane :label="'评论('+commentNumber+')'" name="third">
              <comment :videoId="videoId" @childFn="parentFn" @childFnComment="CommentparentFn" />
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getCourseInfo,
  getParentsType,
  getDirectory,
  saveSubscribe,
  removeSubscribe
} from '@/api/course'
import { like, getAmount } from '@/api/video'
import Comment from './Comment'
import Introduction from './Introduction'
import Directory from './Directory'
export default {
  name: 'Main',
  components: {
    Comment,
    Introduction,
    Directory
  },
  data() {
    return {
      title: '',
      uploadDate: '',
      breadList: [],
      courseId: '',
      courseName: '',
      courseTypeId: '',
      point: 0,
      star: 0,
      sub: 0,
      pointType: 'info',
      starType: 'info',
      subType: 'info',
      activeName: 'third',
      text: '',
      url: '',
      directory: [],
      video: null,
      videoId: '',
      sup_this: this,
      courseIntroduction: '',
      myPlayer: null,
      commentNumber: 0,
      warn: '没有内容',
      isVideo: true,
      userName: '',
      children_this: null,
      children_comment_this: null
    }
  },
  mounted() {
    this.info()
  },
  watch: {
    videoId: function() {
      getAmount(this.videoId).then(res => {
        this.point = res.data.likeAmount
        this.star = res.data.collectAmount
        if (res.data.isLike === '1') {
          this.pointType = 'danger'
        } else {
          this.pointType = 'info'
        }
        if (res.data.isCollect === '1') {
          this.starType = 'primary'
        } else {
          this.starType = 'info'
        }
      })
    }
  },
  methods: {
    async info() {
      this.courseId = this.$route.query.id
      getDirectory(this.courseId).then(res => {
        this.directory = res.data
        if (
          this.$route.query.video != null &&
          this.$route.query.video.id !== undefined
        ) {
          this.video = this.$route.query.video
        } else if (this.directory[0].videoList.length === 0) {
          return
        } else {
          this.video = this.directory[0].videoList[0]
        }
        this.title = this.video.name
        this.videoId = this.video.id
        this.uploadDate = this.video.createDateer
        this.text = this.video.introduction
        this.url = 'http://localhost:9091' + this.video.path
        this.initVideo()
      })
      await getCourseInfo(this.courseId).then(res => {
        this.courseName = res.data.name
        this.courseTypeId = res.data.courseTypeId
        this.courseIntroduction = res.data.introduction
        this.sub = res.data.subscribeAmount
        this.userName = res.data.userName
        this.children_comment_this.getCreateBy(res.data.createBy)
        if (
          res.data.profilePhoto != null &&
          res.data.profilePhoto !== '' &&
          res.data.profilePhoto.length !== 0
        ) {
          this.children_this.getCircleUrl(res.data.profilePhoto)
        }
        if (res.data.isSubscribe === true) {
          this.subType = 'success'
        } else {
          this.subType = 'info'
        }
      })
      getParentsType(this.courseTypeId).then(res => {
        this.breadList = res.data
        this.breadList.push({ name: this.courseName })
      })
    },
    parentFnIntroduction(val) {
      this.children_this = val
    },
    CommentparentFn(val) {
      this.children_comment_this = val
    },
    changePath(val) {
      if (this.breadList[this.breadList.length - 1].id === val) return
      this.$store.commit('SET_COURSETYPE_ID', val)
      this.$router.push('/course')
    },
    pointIcon() {
      var val = this.videoId
      if (val === '') {
        val = null
      }
      like(val, 'like').then(res => {
        if (res.data === '1') {
          this.pointType = 'danger'
          this.point++
        } else {
          this.pointType = 'info'
          this.point--
        }
      })
    },
    starIcon() {
      var val = this.videoId
      if (val === '') {
        val = null
      }
      like(val, 'collect').then(res => {
        if (res.data === '1') {
          this.starType = 'primary'
          this.star++
        } else {
          this.starType = 'info'
          this.star--
        }
      })
    },
    subIcon() {
      if (this.subType === 'info') {
        saveSubscribe(this.courseId).then(res => {
          this.$message({
            showClose: true,
            duration: 2500,
            message: res.data,
            type: 'success'
          })
          this.sub++
          this.subType = 'success'
        })
      } else {
        this.$confirm('确定取消订阅该课程?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            removeSubscribe(this.courseId).then(res => {
              this.$message({
                showClose: true,
                duration: 2500,
                message: res.data,
                type: 'success'
              })
              this.sub--
              this.subType = 'info'
            })
          })
          .catch(() => {})
      }
    },
    changeVideo(val) {
      this.video = val
      this.title = this.video.name
      this.videoId = this.video.id
      this.uploadDate = this.video.createDate
      this.text = this.video.introduction
      this.url = 'http://localhost:9091' + this.video.path
      if (this.myPlayer === null) {
        this.initVideo()
      } else {
        this.myPlayer.src({ src: this.url, type: 'video/mp4' })
        this.myPlayer.play()
      }
    },
    parentFn(val) {
      this.commentNumber = val
    },
    initVideo() {
      // 初始化视频方法
      /* eslint-disable */
      this.myPlayer = this.$video(myVideo, {
        // autoplay: 'autoplay',
        // 是否显示进度条
        controls: true,
        // 建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
        preload: 'auto',
        // 设置视频播放器的显示宽度（以像素为单位）
        width: '1000px',
        // 设置视频播放器的显示高度（以像素为单位）
        height: '500px',
        controlBar: {
          progressControl: true,
          durationDisplay: true,
          currentTimeDisplay: true,
          timeDivider: true,
          playbackRateMenuButton: {
            playbackRates: [0.5, 1, 1.5, 2, 2.5]
          },
          volumePanel: {
            inline: false //竖直的音量面板
          },
          children: [
            {
              name: 'playToggle' //播放暂停按钮
            },
            {
              name: 'progressControl' // 进度条
            },
            {
              name: 'currentTimeDisplay' //当前播放时间
            },
            {
              name: 'timeDivider' // '/' 分隔符
            },
            {
              name: 'durationDisplay' //总时间
            },
            {
              name: 'playbackRateMenuButton' //播放速率
            },
            {
              name: 'volumePanel' //音量控制
            },
            {
              name: 'fullscreenToggle' //全屏
            }
          ]
        },
        sources: {
          // 视频源
          src: this.url,
          type: 'video/mp4'
        }
      })
    }
  }
}
</script>

<style scoped>
.center {
  height: auto;
  width: 1000px;
  /* background-color: aqua; */
  margin: 0 auto;
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: -18px;
}

.breadcrumb {
  padding-top: 6px;
  padding-bottom: 15px;
}
.introduction {
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
  margin-top: 10px;
  height: auto;
}
.label {
  height: auto;
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
  margin-top: 10px;
}
.icon_number {
  font-size: 10px;
  color: #787878;
  margin-left: 5px;
  width: 30px;
  /* background-color: aqua; */
  display: inline-block;
}
.text {
  font-size: 12px;
  color: #585858;
  width: 600px;
  padding-top: 10px;
  padding-left: 10px;
  padding-bottom: 10px;
  position: static;
  word-break: break-all;
}
.select {
  padding-left: 6px;
  padding-right: 6px;
}
.video {
  height: 500px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.video-js {
  font-size: 10px;
}
.vjs-paused .vjs-big-play-button,
.vjs-paused.vjs-has-started .vjs-big-play-button {
  display: block;
}
.warn {
  height: 100%;
  background-color: black;
  color: white;
  text-align: center;
  line-height: 200px;
}
</style>
