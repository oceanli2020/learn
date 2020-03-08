<template>
  <div class="personal">
    <el-container>
      <el-header>
        <Header />
      </el-header>
      <el-container>
        <el-aside width="200px" class="aside">
          <el-menu
            class="el-menu-vertical"
            text-color="#303030 "
            active-text-color="#2894FF"
            default-active="2"
          >
            <el-menu-item index="1" @click="changeContent(1)">
              <span slot="title">课程表</span>
            </el-menu-item>
            <el-menu-item index="2" @click="changeContent(2)">
              <span slot="title">个人信息</span>
            </el-menu-item>
            <el-menu-item index="3">
              <span slot="title">我的收藏</span>
            </el-menu-item>
            <el-menu-item index="4">
              <span slot="title">导航四</span>
            </el-menu-item>
            <el-menu-item index="5">
              <span slot="title">导航五</span>
            </el-menu-item>
            <el-menu-item index="6">
              <span slot="title">退出登录</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main v-if="index===1">
          <option-first />
        </el-main>
        <el-main v-else-if="index===2">
          <option-second />
        </el-main>
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
import OptionSecond from './OptionSecond'
import Footer from './Footer'
import store from '@/store'
export default {
  name: 'Personal',
  components: {
    Header,
    OptionFirst,
    OptionSecond,
    Footer
  },
  data() {
    return {
      index: 2
    }
  },
  mounted() {
    this.judge()
  },
  methods: {
    judge() {
      if (!store.getters.token) {
        this.$confirm('该操作需要登录，是否登录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        })
          .then(() => {
            this.$router.push('/login')
          })
          .catch(() => {
            this.$router.push('/')
          })
      }
    },
    changeContent(i) {
      this.index = i
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
  height: 793px;
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
  top: 120px;
}
.el-menu-vertical {
  text-align: left;
}
</style>
