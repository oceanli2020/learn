<template>
  <div class="main">
    <!-- 回到顶部 -->
    <el-backtop></el-backtop>
    <div class="center">
      <div class="title">
        <span>{{ title }}</span>
      </div>
      <div class="video">
        <div v-if="isStart==='1'">
          <video id="myVideo" class="video-js vjs-big-play-centered" autoplay="autoplay"></video>
        </div>
        <div v-else class="warn">
          <span class="warn_word">{{warn}}</span>
        </div>
      </div>
      <div class="introduction">
        <div class="text">{{ text }}</div>
      </div>
      <div class="replay_block">
        <p>
          <svg-icon
            icon-class="replay"
            aria-hidden="true"
            style="font-size: 35px;fill:#1296db;position: relative;top:5px;"
          ></svg-icon>
          <span>直播回放</span>
        </p>
        <div class="replay">
          <el-row>
            <el-col :span="5" v-for="item in 8" :key="item">
              <div style="margin-top:3px">
                <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px' }">
                  <div class="replay-name">
                    <el-link :underline="false">
                      <span style="font-size: 14px;"></span>
                    </el-link>
                  </div>
                  <div class="text" style="margin-top:12px">
                    <span></span>
                  </div>
                  <div class="text" style="margin-top:12px;margin-left:-2px"></div>
                </el-card>
              </div>
            </el-col>
          </el-row>
          <div class="pagination">
            <el-pagination
              background
              @current-change="currentChange"
              :page-size="page.size"
              :current-page="page.current"
              layout="prev, pager, next"
              :total="total"
              style="text-align: center"
            ></el-pagination>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getLiveInfo } from '@/api/live'
export default {
  name: 'Main',
  data() {
    return {
      title: '',
      text: '',
      liveId: '',
      rtmp: '',
      pushCode: '',
      isStart: '',
      warn: '主人还在休息哦',
      tabledata: [],
      page: {
        size: 8,
        current: 1,
        sort: 'id',
        query: { liveId: '' }
      },
      total: 0
    }
  },
  mounted() {
    this.info()
  },
  watch: {},
  methods: {
    async info() {
      this.liveId = this.$route.query.id
      await getLiveInfo(this.liveId).then(res => {
        this.title = res.data.name
        this.text = res.data.introduction
        this.rtmp = res.data.rtmp
        this.pushCode = res.data.pushCode
        this.isStart = res.data.isStart
      })
      if (this.isStart === '1') {
        this.initVideo()
      }
    },
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
          src: this.rtmp + '/' + this.pushCode,
          type: 'rtmp'
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
  padding-bottom: 15px;
}
.introduction {
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
  margin-top: 30px;
  height: auto;
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
  line-height: 500px;
}
.replay_block {
  height: 500px;
  margin-top: 60px;
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 2px;
}
.el-row {
  height: auto;
}
.el-col {
  padding: 0px 3px 3px 2px;
  width: 240px;
}
.box-card {
  width: 234px;
  height: 150px;
  word-break: break-all;
}
.replay-name {
  height: 60px;
}
.text {
  font-size: 5px;
  color: #707070;
}
.pagination {
  margin-top: 30px;
}
</style>
