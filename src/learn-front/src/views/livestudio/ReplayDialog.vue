<template>
  <div>
    <el-dialog
      :visible.sync="dialogFormVisible"
      width="800px"
      :before-close="handleClose"
      :show-close="true"
      @opened="opened"
      @closed="closed"
      :modal="true"
      :lock-scroll="false"
      :center="true"
    >
      <div class="video">
        <div class="warn">
          <video id="replayVideo" class="video-js vjs-big-play-centered"></video>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'ReplayDialog',
  props: {
    dialogFormVisible: {
      type: Boolean,
      required: true
    },
    replay: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      url: '',
      myPlayer: null
    }
  },
  mounted() {},
  methods: {
    opened() {
      this.url = 'http://localhost:9091' + this.replay.path
      if (this.myPlayer == null) {
        this.initVideo()
      } else {
        this.myPlayer.src({ src: this.url, type: 'video/mp4' })
        this.myPlayer.play()
      }
    },
    closed() {
      this.myPlayer.pause()
    },
    cancel() {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    handleClose(done) {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    initVideo() {
      // 初始化视频方法
      /* eslint-disable */
      this.myPlayer = this.$video(replayVideo, {
        // autoplay: 'autoplay',
        // 是否显示进度条
        controls: true,
        // 建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
        preload: 'auto',
        // 设置视频播放器的显示宽度（以像素为单位）
        width: '750px',
        // 设置视频播放器的显示高度（以像素为单位）
        height: '400px',
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
.video {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  height: 400px;
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
}
</style>
