<template>
  <div class="directory">
    <div class="block">
      <div v-for="(item,index) in directory" :key="item" class="chapter-block">
        <span class="chapter-number">{{index+1}}</span>
        <span class="chapter-name">{{item.name}}</span>
        <div v-for="video in item.videoList" :key="video" class="video-name">
          <svg-icon icon-class="video" style="font-size: 16px; position: relative;top:3px;"></svg-icon>
          <el-link
            :underline="false"
            :type="(video.id==videoId)?videoType:''"
            @click="clickVideo(video)"
          >{{video.name}}</el-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'directory',
  props: {
    sup_this: {
      type: Object,
      default: null
    },
    directory: {
      type: Object,
      required: true
    },
    videoId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      videoType: 'primary'
    }
  },
  mounted() {},
  methods: {
    clickVideo(video) {
      this.sup_this.changeVideo(video)
    }
  }
}
</script>

<style scoped>
.directory {
  height: auto;
  /* background-color: aqua; */
  margin-top: 10px;
}
.block {
  padding: 0px 10px 10px 10px;
}
.chapter-block {
  margin-top: 5px;
  padding: 10px 10px 10px 10px;
  /* background-color: brown; */
}
.chapter-number {
  font-weight: bold;
}
.chapter-name {
  font-weight: bold;
  margin-left: 10px;
}
.video-name {
  margin-top: 10px;
  padding: 10px 10px 10px 10px;
  background-color: #f8f8f8;
  font-size: 13px;
}
</style>
