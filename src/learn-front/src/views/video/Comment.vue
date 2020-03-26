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
      >
      </el-input>
      <el-button type="primary" size="mini" style="height:65px"
        >发表评论</el-button><br>
      <el-divider class="divider"></el-divider>
    </div>
    <div class="text" v-for="index in 10" :key="index">
      <el-avatar :size="50" :src="circleUrl" class="avt"></el-avatar>
      <div style="margin-left:120px;font-size:15px">
{{msg}}
      </div>
      <br>
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
      msg: '2019 Docker+Kubernetes(k8s)微服务容器化实战这个呢，被下架了？'
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
.text {
  margin-top: 20px;
  padding-top: 15px;
  padding-bottom: 15px;
  /* background-color: dodgerblue; */
}
</style>
