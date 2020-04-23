<template>
  <div>
    <el-dialog
      title="直播教程"
      :visible.sync="dialogFormVisible"
      width="700px"
      :before-close="handleClose"
      @close="close"
      class="dialog-Tutorial"
    >
      <div class="block-Tutorial">
        <el-timeline>
          <el-timeline-item timestamp="第一步" placement="top">
            <el-card class="card-obs">
              <h4>下载并安装OBS-Studio</h4>
              <p>
                <span>1、下载OBS安装包</span>
                <svg-icon icon-class="obs" style="font-size:35px; "></svg-icon>
              </p>
              <p>
                <span>2、安装OBS-Studio</span>
              </p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="第二步" placement="top">
            <el-card class="card-rtmp">
              <h4>推流设置</h4>
              <p>
                <span>1、打开推流设置</span>
                <br />
                <span>
                  <svg-icon icon-class="next"></svg-icon>点击文件
                  <svg-icon icon-class="next"></svg-icon>点击设置
                  <svg-icon icon-class="next"></svg-icon>点击推流
                </span>
              </p>
              <p>
                <span>2、填写rtmp地址和推流码</span>
                <br />
                <el-image style="width: 300px; height: 100px" :src="img1" :fit="cover"></el-image>
                <br />
                <span>服务选择自定义,服务器处填写rtmp地址，串流密钥处填写推流码</span>
              </p>
              <p>
                <span>3、点击确定</span>
              </p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="第三步" placement="top">
            <el-card class="card-Tutorial">
              <h4>开启屏幕捕获和摄像头</h4>
              <p>
                <span>添加场景，创建显示器捕获和视频捕获设备</span>
                <br />
                <el-image style="width: 300px; height: 150px" :src="img2" :fit="cover"></el-image>
                <br />
                <span>显示器捕获即屏幕捕获，视频捕获设备即摄像头</span>
              </p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="第四步" placement="top">
            <el-card class="card-Tutorial">
              <h4>设置直播延迟</h4>
              <p>
                <span>1、打开延迟设置</span>
                <br />
                <span>
                  <svg-icon icon-class="next"></svg-icon>点击文件
                  <svg-icon icon-class="next"></svg-icon>点击设置
                  <svg-icon icon-class="next"></svg-icon>点击高级
                </span>
              </p>
              <p>
                <span>2、输入需要的直播延迟</span>
                <br />
                <el-image style="width: 350px; height: 100px" :src="img3" :fit="cover"></el-image>
                <br />
                <!-- <span></span> -->
              </p>
              <p>
                <span>3、点击确定</span>
              </p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="第五步" placement="top">
            <el-card class="card-Tutorial">
              <h4>开始推流和停止推流</h4>
              <p>
                <span>1、开始推流</span>
                <br />
                <el-image style="width: 100px; height: 100px" :src="img4" :fit="cover"></el-image>
              </p>
              <p>
                <span>2、停止推流</span>
                <br />
                <el-image style="width: 100px; height: 100px" :src="img5" :fit="cover"></el-image>
              </p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="第六步" placement="top">
            <el-card class="card-Tutorial">
              <h4>开始直播和关闭直播</h4>
              <p>
                <span>1、开始直播</span>
                <br />
                <el-image style="width: 150px; height: 80px" :src="img6" :fit="cover"></el-image>
              </p>
              <p>
                <span>2、关闭直播</span>
                <br />
                <el-image style="width: 185px; height: 80px" :src="img7" :fit="cover"></el-image>
              </p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'HowLive',
  props: {
    dialogFormVisible: {
      type: Boolean,
      required: true
    },
    sup_this: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      img1: 'http://192.168.1.9:9090/1.PNG',
      img2: 'http://192.168.1.9:9090/2.PNG',
      img3: 'http://192.168.1.9:9090/3.PNG',
      img4: 'http://192.168.1.9:9090/4.PNG',
      img5: 'http://192.168.1.9:9090/5.PNG',
      img6: 'http://192.168.1.9:9090/6.PNG',
      img7: 'http://192.168.1.9:9090/7.PNG'
    }
  },
  mounted() {},
  methods: {
    close() {},
    handleClose(done) {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    }
  }
}
</script>

<style scoped>
.card-obs {
  width: 520px;
}
.card-rtmp {
  width: 520px;
}
</style>
