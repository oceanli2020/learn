<template>
  <div class="start-live">
    <el-backtop></el-backtop>
    <div class="block">
      <div class="info">
        <el-avatar :size="70" :src="circleUrl" class="avatar"></el-avatar>
        <span class="my-name">我亦飘零久</span>
        <span class="my-address">我的直播间地址:</span>
        <el-link
          :underline="false"
          class="my-address"
          type="primary"
        >http://live.bilibili.com/9605499</el-link>
      </div>
      <el-divider></el-divider>
      <div class="method">
        <el-row :gutter="5">
          <el-col :span="5" push="1">
            <div class="address">
              <span>rtmp地址</span>
              <br />
              <span style="color: #2894FF;">http://live.bilibili.com/9605499</span>
            </div>
          </el-col>
          <el-col :span="5" push="5">
            <div class="key">
              <span>推流码</span>
              <br />
              <span style="color: #2894FF">abcd</span>
            </div>
          </el-col>
          <el-col :span="5" push="5">
            <div class="how">
              <svg-icon
                icon-class="live"
                aria-hidden="true"
                style="font-size: 35px; position: relative;top:7px;left:10px"
              ></svg-icon>
              <el-link :underline="false" @click="dialog">
                <span class="word">如何直播</span>
              </el-link>
              <how-live
                :dialogFormVisible="dialogFormVisible"
                :sup_this="sup_this"
                @childFn="parentFn"
              />
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="block-title">
      <div class="div-title">
        <span class="title">房间标题:</span>
        <br />
        <el-input
          v-model="title"
          maxlength="10"
          show-word-limit
          placeholder="请输入内容"
          class="input-title"
        ></el-input>
        <el-link :underline="false" class="save">保存</el-link>
      </div>
    </div>
    <div class="block-cover">
      <div class="div-cover">
        <span class="title">直播封面:</span>
        <el-upload
          class="cover-picture"
          action
          :http-request="uploadFileMethod"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="imageUrl" :src="imageUrl" class="picture" />
          <i v-else class="el-icon-plus cover-picture-icon"></i>
        </el-upload>
      </div>
    </div>
    <div class="block-introduction">
      <div class="div-introduction">
        <span class="title">个人简介:</span>
        <br />
        <el-input
          type="textarea"
          :rows="10"
          placeholder="请填写个人简介"
          v-model="text"
          resize="none"
          maxlength="500"
          class="textarea"
        ></el-input>
        <br />
        <el-button type="info" class="button-info">保存</el-button>
      </div>
    </div>
  </div>
</template>
<script>
import HowLive from '../froms/HowLive'
export default {
  components: {
    HowLive
  },
  data() {
    return {
      circleurl: '',
      title: '',
      text: '',
      dialogFormVisible: false,
      sup_this: this
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    parentFn(val) {
      this.dialogFormVisible = val
    },
    dialog() {
      this.dialogFormVisible = true
    }
  }
}
</script>
<style scoped>
.start-live {
  height: 600px;
  position: relative;
  bottom: 20px;
  width: 800px;
  left: 330px;
}
.block {
  height: 177px;
  background-color: white;
  padding-top: 10px;
  border-radius: 10px;
}

.avatar {
  margin-left: 20px;
}
.my-name {
  position: relative;
  left: 10px;
  bottom: 15px;
}
.el-divider--horizontal {
  /* 修改分割线的上下外边距 */
  margin: 10px 13px;
  width: 770px;
}
.my-address {
  position: relative;
  left: 240px;
  bottom: 15px;
  font-size: 5px;
}

.method {
  /* background-color: aqua; */
  height: 63px;
  padding-top: 10px;
  font-size: 15px;
  line-height: 25px;
}
.address {
  /* background-color: burlywood; */
  height: 50px;
  width: 290px;
}
.key {
  height: 50px;
  width: 100px;
}
.how {
  height: 50px;
  width: 120px;
}
.word {
  font-size: 15px;
  position: relative;
  left: 10px;
  top: 1px;
}
.block-title {
  margin-top: 30px;
  height: 100px;
  background-color: white;
  border-radius: 10px;
  padding: 15px 10px 15px 10px;
}
.div-title {
  /* background-color: aqua; */
  height: 100px;
}
.title {
  position: relative;
  left: 22px;
  top: 5px;
}
.input-title {
  width: 300px;
  position: relative;
  top: 15px;
  left: 22px;
}
.save {
  font-size: 10px;
  position: relative;
  top: 13px;
  left: 25px;
}
.block-cover {
  margin-top: 30px;
  height: 230px;
  background-color: white;
  border-radius: 10px;
  padding: 15px 10px 15px 10px;
}
.div-cover {
  height: 230px;
  /* background-color: aqua; */
}
.cover-picture {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  width: 240px;
  height: 150px;
  position: relative;
  overflow: hidden;
  left: 22px;
  top: 20px;
}
.cover-picture:hover {
  border-color: #409eff;
}
.cover-picture-icon {
  font-size: 28px;
  color: #8c939d;
  width: 240px;
  height: 150px;
  line-height: 150px;
  text-align: center;
}
.picture {
  width: 240px;
  height: 150px;
  display: block;
}
.block-introduction {
  margin-top: 30px;
  height: 340px;
  background-color: white;
  border-radius: 10px;
  padding: 15px 10px 15px 10px;
}
.div-introduction {
  height: 340px;
}
.textarea {
  width: 500px;
  position: relative;
  left: 22px;
  top: 20px;
}
.button-info {
  position: relative;
  left: 22px;
  top: 30px;
}
</style>
