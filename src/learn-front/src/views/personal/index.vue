<template>
  <div class="personal">
    <el-container>
      <el-header>
        <Header @childFn="parentFn" />
      </el-header>
      <el-container>
        <el-aside width="200px" class="aside">
          <el-menu
            class="el-menu-vertical"
            text-color="#303030 "
            active-text-color="#2894FF"
            :default-active="index"
          >
            <el-menu-item index="1" @click="changeContent('1')">
              <span slot="title">课程表</span>
            </el-menu-item>
            <el-menu-item index="2" @click="changeContent('2')">
              <span slot="title">我的收藏</span>
            </el-menu-item>
            <el-menu-item index="3" @click="changeContent('3')">
              <span slot="title">个人信息</span>
            </el-menu-item>
            <el-menu-item index="4" @click="changeContent('4')">
              <span slot="title">全部订单</span>
            </el-menu-item>
            <el-menu-item index="5" @click="changeContent('5')">
              <span slot="title">优惠券</span>
            </el-menu-item>
            <el-menu-item index="6" @click="toLogout()">
              <span slot="title">退出登录</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main v-if="index==='1'">
          <option-first />
        </el-main>
        <el-main v-else-if="index==='2'">
          <!-- <option-second /> -->
        </el-main>
        <el-main v-else-if="index==='3'">
          <option-thrid />
        </el-main>
        <el-main v-else-if="index==='4'"></el-main>
        <el-main v-else-if="index==='5'"></el-main>
      </el-container>
      <el-footer style="height:100px">
        <Footer />
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import Header from './Header'
import OptionFirst from './OptionFirst'
import OptionThrid from './OptionThrid'
import Footer from './Footer'
import store from '@/store'
export default {
  name: 'Personal',
  components: {
    Header,
    OptionFirst,
    OptionThrid,
    Footer
  },
  data() {
    return {
      index: this.$route.query.index
    }
  },
  mounted() {
    if (this.$route.query.index === undefined) {
      this.index = '3'
    }
    this.judge()
  },
  methods: {
    judge() {
      if (!store.getters.token) {
        this.$router.push('/')
      }
    },
    changeContent(i) {
      this.index = i
    },
    parentFn(payload) {
      this.index = payload
    },
    toLogout() {
      this.$store
        .dispatch('logout')
        .then(() => {
          location.reload()
        })
        .catch(() => {})
    }
  }
}
</script>

<style scoped>
.el-header {
  text-align: center;
  line-height: 60px;
  /* background-color: blueviolet; */
}
.el-main {
  height: 865px;
}
.el-footer {
  background-color: #606266;
  line-height: 60px;
}
.el-aside {
  background-color: white;
  color: #333;
  text-align: center;
  height: 400px;
}
.aside {
  width: 1000px;
  position: absolute;
  left: 459px;
  top: 150px;
}
.el-menu-vertical {
  text-align: left;
}
</style>
