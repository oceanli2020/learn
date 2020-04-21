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
        <video id="myVideo" class="video-js vjs-big-play-centered" autoplay="autoplay">
          <!-- <source src="http://192.168.157.128:9090/1.mp4" type="video/mp4" /> -->
          <!-- <source src="http://192.168.157.128:80/hls/abcd/index.m3u8" /> -->
          <!-- <source src="rtmp://192.168.1.9/live/abcd" type="rtmp" /> -->
        </video>
      </div>
      <div class="Introduction">
        <div style="padding-top: 10px; padding-left: 10px;">
          <el-button :type="pointType" circle @click="pointIcon('point')">
            <svg-icon icon-class="point"></svg-icon>
          </el-button>
          <span class="icon_number">{{ point }}</span>
          <el-button :type="starType" circle style="margin-left: 30px;" @click="pointIcon('star')">
            <svg-icon icon-class="star"></svg-icon>
          </el-button>
          <span class="icon_number">{{ star }}</span>
          <el-button type="success" icon="el-icon-share" circle style="margin-left: 30px;"></el-button>
          <span class="icon_number">{{ share }}</span>
        </div>
        <div class="text">{{ text }}</div>
      </div>
      <div class="label">
        <div class="select">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="课程介绍" name="first"></el-tab-pane>
            <el-tab-pane label="目录" name="second"></el-tab-pane>
            <el-tab-pane label="评论(1555)" name="third">
              <comment />
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      title: '李海洋的直播间',
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
      text: '终于成功了呀'
    }
  },
  mounted() {
    this.initVideo()
  },
  watch: {},
  methods: {
    initVideo() {
      // 初始化视频方法
      /* eslint-disable */
      let myPlayer = this.$video(myVideo, {
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
          progressControl: false,
          durationDisplay: true,
          currentTimeDisplay: true,
          timeDivider: true,
          remainingTimeDisplay: false,
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
          src: 'http://localhost:9091/video/2020/20200418154250.mp4',
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
.title {
  margin-top: 5px;
}
.breadcrumb {
  padding-top: 6px;
  padding-bottom: 15px;
}

.Introduction {
  height: 150px;
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
  margin-top: 20px;
  height: auto;
}
.label {
  min-height: auto;
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);   */
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
</style>
