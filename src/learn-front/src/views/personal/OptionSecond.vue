<template>
  <div class="option_second">
    <div class="info">
      <span class="title">个人信息</span>
      <div style="margin-top:40px">
        <el-collapse accordion>
          <el-collapse-item>
            <template slot="title">
              <span class="text">昵称&#12288;&#12288;</span>
              <span class="content" style="margin-left:70px">{{user.user_name}}</span>
            </template>
            <div></div>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <span class="text">头像&#12288;&#12288;</span>
              <span class="content" style="margin-left:70px">
                <el-avatar shape="square" size="large" :src="squareUrl" class="avatar"></el-avatar>
              </span>
            </template>
            <div></div>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <span class="text">真实姓名</span>
              <span class="content" style="margin-left:70px">{{user.real_name}}</span>
            </template>
            <div></div>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <span class="text">邮箱&#12288;&#12288;</span>
              <span class="content" style="margin-left:70px">{{user.email}}</span>
            </template>
            <div></div>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <span class="text">手机号码</span>
              <span class="content" style="margin-left:70px">{{user.phone_number}}</span>
            </template>
            <div></div>
          </el-collapse-item>
          <el-collapse-item>
            <template slot="title">
              <span class="text">密码&#12288;&#12288;</span>
              <span class="content" style="margin-left:70px">{{user.password}}</span>
            </template>
            <div></div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>
  </div>
</template>

<script>
import { info } from '@/api/login'
export default {
  name: 'OptionSecond',
  data() {
    return {
      user: {
        user_name: '',
        real_name: '',
        profile_photo: '',
        email: '',
        phone_number: '',
        password: '******'
      },
      squareUrl:
        'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
    }
  },
  mounted() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      info()
        .then(res => {
          const data = res.data.data
          this.user.user_name = data.userName
          this.user.real_name = data.realName
          this.user.profile_photo = data.profilePhoto
          this.user.email = data.email
          this.user.phone_number = data.phoneNumber
          if (data.profilePhoto !== '') {
            this.squareUrl = require('@/' + data.profilePhoto)
          }
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
.info {
  width: 600px;
  height: 400px;
  /* background-color: yellow; */
  position: absolute;
  left: 700px;
  top: 120px;
}
.title {
  color: #303030;
  font-size: 25px;
}
.text {
  color: #484848;
  font-size: 18px;
}
.content {
  color: #585858;
  font-size: 16px;
}
.avatar {
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: 9px;
}
</style>
