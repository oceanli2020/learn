<template>
  <div class="comment">
    <div class="write">
      <el-avatar :size="50" :src="circleUrl" class="avatar"></el-avatar>
      <el-input
        type="textarea"
        :rows="3"
        placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论"
        v-model="textarea"
        resize="none"
        maxlength="15"
        show-word-limit="true"
        size="mini "
      ></el-input>
      <el-button type="primary" size="mini" style="height:65px"
        >发表评论</el-button
      >
      <br />
      <el-divider class="divider"></el-divider>
    </div>
    <div class="area" v-for="index in 9" :key="index">
      <div class="avt">
        <el-avatar :size="50" :src="circleUrl"></el-avatar>
      </div>
      <div class="text">
        <p style="font-size:6px;color:#686868">
          <strong>{{ name }}</strong>
        </p>
        <span style="font-size:13px;">{{ comment }}</span>
        <br />
        <span style="font-size:5px;color:#989898;">{{ commentDate }}</span>
        <svg-icon icon-class="point1"></svg-icon>
      </div>
      <br />
      <el-divider class="divider"></el-divider>
    </div>
  </div>
</template>

<script>
import store from '@/store'
import { getImg } from '@/api/user'

export default {
  name: 'Comment',
  data() {
    return {
      textarea: '',
      circleUrl: '',
      name: 'oceanLi',
      comment: '第一次评论呢',
      commentDate: '2019-10-03 18:39'
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      if (store.getters.token) {
        getImg(store.getters.profilePhoto).then(res => {
          this.circleUrl = `data: image/jpeg;base64,${btoa(
            new Uint8Array(res).reduce(
              (data, byte) => data + String.fromCharCode(byte),
              ''
            )
          )}`
        })
      } else {
        this.circleUrl =
          'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
      }
    }
  }
}
</script>

<style scoped>
.write {
  padding-top: 15px;
  padding-bottom: 15px;
  /* background-color: firebrick; */
}
.el-textarea {
  width: 700px;
}
.avatar {
  margin-left: 10px;
  margin-right: 20px;
  vertical-align: middle;
}
.divider {
  width: 903px;
  float: right;
}
.area {
  margin-top: 15px;
  padding-top: 15px;
  padding-bottom: 15px;
  /* background-color:hotpink; */
}
.avt {
  margin-left: 10px;
  width: 55px;
  float: left;
  /* background-color: khaki; */
}
.text {
  margin-left: 85px;
  height: 65px;
  width: 700px;
  position: relative;
  top: -3px;
}
</style>
