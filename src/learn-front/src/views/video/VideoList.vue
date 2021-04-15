<template>
  <div
    class="vl-main"
    :style="{
      height: 'calc(100vh - 186px)'
    }"
  >
    <el-scrollbar style="height: 100%" class="lazy-scrollbar">
      <div
        class="list"
        v-infinite-scroll="load"
        :infinite-scroll-disabled="disabled"
      >
        <el-timeline class="vl-timeline">
          <el-timeline-item
            v-for="(videos, index) in videosData"
            :key="index"
            :timestamp="videos.timestamp"
            placement="top"
            type="primary"
          >
            <span v-for="(video, i) in videos.videoList" :key="i">
              <el-image
                class="cover"
                :src="video.poster"
                fit="fill"
                @error="handleError"
              >
              </el-image>
              <svg-icon
                icon-class="play_white"
                class="play-icon"
                @click.native="playVideo(video)"
              />
            </span>
          </el-timeline-item>
        </el-timeline>
        <p v-if="loading" class="txt"></p>
        <p v-if="noMore" class="txt"></p>
      </div>
      <video-dialog
        :dialogFormVisible="dialogFormVisible"
        :video="video"
        @childFn="parentFn"
      />
    </el-scrollbar>
  </div>
</template>

<script>
import VideoDialog from './VideoDialog'
import { formatTime } from '../../utils/format'
import { ipcRenderer } from 'electron'
import { createNamespacedHelpers } from 'vuex'
const { mapState } = createNamespacedHelpers('show')
export default {
  name: 'VideoList',
  components: {
    VideoDialog
  },
  data() {
    return {
      videosData: [],
      videos: [],
      lastTimestamp: -1,
      dialogFormVisible: false,
      video: {},
      defaultImgUrl: require('../../assets/black.png'),
      loading: false,
      count: 0,
      inspectionId: '',
      lazyNumber: 6
    }
  },
  computed: {
    ...mapState({
      keyList: state => state.keyList,
      isFilter: state => state.isFilter
    }),
    noMore() {
      return this.count >= this.videos.length
    },
    disabled() {
      return this.loading || this.noMore
    }
  },
  created() {
    this.init()
  },
  watch: {
    '$route.query': {
      handler: function(val) {
        this.init()
      },
      deep: true
    }
  },
  methods: {
    init() {
      this.inspectionId = this.$route.query.inspectionId
      this.videosData = []
      this.count = 0
      this.loading = false
      this.lastTimestamp = -1
      let params = {
        inspectionId: this.inspectionId
      }
      if (this.isFilter) {
        params['keyList'] = this.keyList
      } else {
        params['keyList'] = null
      }
      ipcRenderer.once('result:video:reply', async (event, result) => {
        this.videos = result.videos
        if (this.videos.length > 0) {
          await this.getVideoposter()
          this.getVideosData()
          this.count += this.lazyNumber
        }
      })
      ipcRenderer.send('result:video', params)
    },
    getVideosData() {
      let end
      if (this.videos.length > this.count + this.lazyNumber) {
        end = this.count + this.lazyNumber
      } else {
        end = this.videos.length
      }
      let i = this.count
      for (; i < end; i++) {
        let timestamp = formatTime(this.videos[i].mtime)
        if (timestamp !== this.lastTimestamp) {
          this.videosData.push({ timestamp: timestamp, videoList: [] })
          this.lastTimestamp = timestamp
        }
        this.videosData[this.videosData.length - 1].videoList.push(
          this.videos[i]
        )
      }
    },
    load() {
      this.loading = true
      setTimeout(async () => {
        await this.getVideoposter()
        this.getVideosData()
        this.count += this.lazyNumber
        this.loading = false
      }, 200)
    },
    getVideoposter() {
      const that = this
      let end
      if (this.videos.length > this.count + this.lazyNumber) {
        end = this.count + this.lazyNumber
      } else {
        end = this.videos.length
      }
      let videoList = this.videos.slice(this.count, end)
      videoList.forEach(item => {
        let video = document.createElement('video')
        video.setAttribute('crossOrigin', 'Anonymous')
        video.src = 'file:///' + item.currentPath
        // 如果不设置currentTime，画出来的图片是空的
        video.currentTime = 0.001
        video.onloadeddata = loadedmetadata
        function loadedmetadata(e) {
          let canvas = document.createElement('canvas')
          let vWidth = e.path[0].videoWidth
          let vHeight = e.path[0].videoHeight
          // 判断视频的高度和宽度
          // 画布的大小，由设置的视频显示的大小决定，以免画布太大，画的图片太大，浪费资源，这里设置显示视频盒子为width:140px，height:140px;
          // 设置倍数是rate，倍数越大画图的图片越大，加载速度越慢
          // https://www.w3school.com.cn/html5/canvas_drawimage.asp
          let rate = 4
          canvas.width = 140 * rate
          canvas.height = ((vHeight * 140) / vWidth) * rate
          canvas
            .getContext('2d')
            .drawImage(video, 0, 0, canvas.width, canvas.height)
          let url = canvas.toDataURL('image/png', 1)
          that.$set(item, 'poster', url)
        }
      })
    },
    playVideo(video) {
      this.video = video
      this.dialogFormVisible = true
    },
    parentFn(val) {
      this.dialogFormVisible = val
    },
    handleError(error) {
      error.srcElement.src = this.defaultImgUrl
    }
  }
}
</script>

<style scoped>
.vl-main {
  background-color: #ffffff;
  border-radius: 4px 4px 0px 0px;
  border: solid 1px #d9d9d9;
  margin: 9px;
}
.vl-timeline {
  margin: 32px 0px 0px 45px;
}
.vl-timeline >>> .el-timeline-item__wrapper {
  padding-left: 18px;
}
.vl-main >>> .el-scrollbar__wrap {
  overflow-x: hidden;
}
.cover {
  width: 128px;
  height: 128px;
  border-radius: 4px;
  margin-right: -40px;
}
.play-icon {
  width: 40px;
  height: 40px;
  position: relative;
  right: 47px;
  bottom: 45px;
  cursor: pointer;
}
</style>
