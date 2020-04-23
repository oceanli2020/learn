<template>
  <div class="live">
    <el-container>
      <el-header height="60px">
        <Header />
      </el-header>
      <el-container style="background-color:#E8E8E8;">
        <el-aside width="200px">
          <Aside @childFn="parentFn" />
        </el-aside>
        <el-main v-if="index==='1'">
          <start-live />
        </el-main>
        <el-main v-else-if="index==='2'">
          <manage-live />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Header from './Header'
import Aside from './Aside'
import StartLive from './menu/StartLive'
import ManageLive from './menu/ManageLive'
export default {
  name: 'Creation',
  components: {
    Header,
    Aside,
    StartLive,
    ManageLive
  },
  data() {
    return {
      index: '1'
    }
  },
  mounted() {},
  methods: {
    parentFn(val) {
      this.index = val
    }
  }
}
</script>

<style scoped>
.el-main {
  min-height: 900px;
  margin-top: 20px;
}
.el-aside {
  background-color: white;
  color: #333;
  text-align: center;
  height: 100%;
  /* 去除滚动条 */
  overflow-y: hidden;
  margin-top: 20px;
  border-radius: 0 10px 10px 0;
}
</style>
