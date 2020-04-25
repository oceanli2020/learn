<template>
  <div class="row">
    <el-row :gutter="100" type="flex" justify="space-around">
      <el-col :span="8" push="2">
        <div class="grid-content" style="height:70px;margin-top:0px">
          <p class="font1">knowledge is power</p>
          <p class="font2">致力于推广知识</p>
        </div>
      </el-col>
      <el-col :span="3" push="1">
        <div class="grid-content" style="height:100px;margin-top:20px">
          <p class="font3">传送门</p>
          <div class="font4">
            <p>关于我们&nbsp; &nbsp;&nbsp;联系我们</p>
            <p>帮助中心&nbsp; &nbsp;&nbsp;产品介绍</p>
            <p>用户反馈&nbsp; &nbsp;&nbsp;推荐链接</p>
          </div>
        </div>
      </el-col>
      <el-col :span="3" pull="2">
        <div class="grid-content" style="height:100px;margin-top:20px">
          <p class="font3">关注我们</p>
          <el-tooltip placement="top">
            <div class="contents" slot="content">
              <img :src="weixinUrl" class="img" />
            </div>
            <el-button type="success" icon="el-icon-chat-dot-round" circle></el-button>
          </el-tooltip>
          <el-button type="primary" icon="el-icon-message" circle></el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'HomeFooter',
  data() {
    return {
      weixinUrl: 'http://192.168.1.9:9090/weixin.png'
    }
  }
}
</script>

<style scoped>
/* scoped  即下列css代码只在当前组件有效 */

.el-row {
  margin-top: 20px;
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
}
.font1 {
  color: #ffffff;
  font-size: 25px;
}
.font2 {
  color: #c0c4cc;
  font-size: 13px;
}
.font3 {
  color: #ffffff;
  font-size: 16px;
}
.font4 {
  color: #c0c4cc;
  font-size: 13px;
}
.row {
  /* 去除下方滚动条 */
  overflow: hidden;
  line-height: 1;
}
.contents {
  width: 150px;
  height: 150px;
}
.img {
  width: 100%;
}
</style>
