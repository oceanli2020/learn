<template>
  <div class="start-live">
    <el-backtop></el-backtop>
    <div class="block">
      <div class="info">
        <div class="left">
          <el-avatar :size="70" :src="circleUrl" class="avatar"></el-avatar>
          <span class="my-name">{{username}}</span>
        </div>
        <span class="my-address-title">我的直播间地址:</span>
        <el-link
          :underline="false"
          class="my-address"
          type="primary"
          @click="myLiveStudio"
        >{{address}}</el-link>
      </div>
      <el-divider></el-divider>
      <div class="method">
        <el-row :gutter="5">
          <el-col :span="5" push="1">
            <div class="address">
              <span>rtmp地址</span>
              <br />
              <span style="color: #2894FF;">{{rtmp}}</span>
            </div>
          </el-col>
          <el-col :span="5" push="5">
            <div class="key">
              <span>推流码</span>
              <br />
              <span style="color: #2894FF">{{code}}</span>
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
          v-model="name"
          maxlength="20"
          show-word-limit
          placeholder="请输入内容"
          class="input-title"
        ></el-input>
        <el-link :underline="false" class="save" @click="updateName">保存</el-link>
      </div>
      <div class="div-start" v-if="isStart==='0'">
        <el-button class="start-button" type="primary" @click="startClick">开始直播</el-button>
        <p>
          <span class="msg">{{msg1}}</span>
        </p>
      </div>
      <div class="div-start" v-else>
        <el-button class="start-button" type="danger" @click="closeClick">关闭直播</el-button>
        <p>
          <span class="msg">{{msg2}}</span>
        </p>
      </div>
    </div>
    <!-- <div class="block-cover">
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
    </div>-->
    <div class="block-introduction">
      <div class="div-introduction">
        <span class="title">个人简介:</span>
        <br />
        <el-input
          type="textarea"
          :rows="10"
          placeholder="请填写个人简介"
          v-model="introduction"
          resize="none"
          maxlength="500"
          show-word-limit
          class="textarea"
        ></el-input>
        <br />
        <el-button type="info" class="button-info" @click="updateIntroduction">保存</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import HowLive from '../froms/HowLive'
import store from '@/store'
import { getLiveInfo, updateLive } from '@/api/live'
export default {
  components: {
    HowLive
  },
  data() {
    return {
      circleurl: '',
      dialogFormVisible: false,
      sup_this: this,
      msg1: '点击开始直播，分享美好生活',
      msg2: '点击关闭直播，今天也要早点休息哦',
      username: '',
      profilePhoto: '',
      address: 'http://localhost:8091/livestuido?id=',
      rtmp: '',
      code: '',
      live: {
        id: '',
        isStart: null,
        name: null,
        introduction: null
      },
      isStart: '0',
      name: '',
      introduction: ''
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      if (store.getters.token) {
        this.username = store.getters.username
        this.profilePhoto = store.getters.profilePhoto
        if (this.profilePhoto === null || this.profilePhoto === '') {
          this.circleUrl =
            'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
        } else {
          this.circleUrl = 'http://localhost:9091' + this.profilePhoto
        }
      }
      getLiveInfo().then(res => {
        this.live.id = res.data.id
        this.name = res.data.name
        this.rtmp = res.data.rtmp
        this.code = res.data.pushCode
        this.isStart = res.data.isStart
        this.introduction = res.data.introduction
        this.address += res.data.id
        this.$emit('childFn', this.live.id)
      })
    },
    parentFn(val) {
      this.dialogFormVisible = val
    },
    dialog() {
      this.dialogFormVisible = true
    },
    startClick() {
      this.isStart = '1'
      this.live.isStart = '1'
      updateLive(this.live).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: '直播间已开启',
          type: 'success'
        })
        this.live.isStart = null
      })
    },
    closeClick() {
      this.isStart = '0'
      this.live.isStart = '0'
      updateLive(this.live).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: '直播间已关闭',
          type: 'error'
        })
        this.live.isStart = null
      })
    },
    updateName() {
      if (this.name === '' || this.name.length === 0) {
        this.$message({
          showClose: true,
          duration: 2500,
          message: '房间标题不能为空',
          type: 'error'
        })
        return
      }
      this.live.name = this.name
      updateLive(this.live).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        this.live.name = null
      })
    },
    updateIntroduction() {
      this.live.introduction = this.introduction
      updateLive(this.live).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        this.live.introduction = null
      })
    },
    myLiveStudio() {
      this.$router.push({
        path: '/livestudio',
        query: { id: this.live.id }
      })
    }
  }
}
</script>
<style scoped>
.start-live {
  height: 900px;
  position: relative;
  bottom: 20px;
  width: 800px;
  left: 320px;
}
.block {
  height: 177px;
  background-color: white;
  padding-top: 10px;
  border-radius: 10px;
}

.left {
  width: 400px;
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
  margin: 5px 13px;
  width: 770px;
}
.my-address-title {
  position: relative;
  left: 450px;
  bottom: 37px;
  font-size: 5px;
}
.my-address {
  position: relative;
  left: 450px;
  bottom: 38px;
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
  height: 202px;
  background-color: white;
  border-radius: 10px;
  padding: 15px 10px 15px 10px;
}
.div-title {
  height: 100px;
}
.title {
  position: relative;
  left: 22px;
  top: 5px;
}
.input-title {
  width: 350px;
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
.div-start {
  margin-top: 2px;
  height: 100px;
}
.start-button {
  position: relative;
  left: 22px;
  width: 120px;
}
.msg {
  font-size: 13px;
  color: #989898;
  position: relative;
  left: 22px;
}
/* .block-cover {
  margin-top: 30px;
  height: 230px;
  background-color: white;
  border-radius: 10px;
  padding: 15px 10px 15px 10px;
} */
/* .div-cover {
  height: 230px;
} */
/* .cover-picture {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  width: 240px;
  height: 150px;
  position: relative;
  overflow: hidden;
  left: 22px;
  top: 20px;
} */
/* .cover-picture:hover {
  border-color: #409eff;
}
.cover-picture-icon {
  font-size: 28px;
  color: #8c939d;
  width: 240px;
  height: 150px;
  line-height: 150px;
  text-align: center;
} */
/* .picture {
  width: 240px;
  height: 150px;
  display: block;
} */
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
