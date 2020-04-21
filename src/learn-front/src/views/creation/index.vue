<template>
  <div class="creation">
    <el-container>
      <el-header height="60px">
        <Header />
      </el-header>
      <el-container style="background-color:#E8E8E8;">
        <el-aside width="200px">
          <Aside @childFn="parentFn" />
        </el-aside>
        <el-main v-if="index==='1'">
          <create-new />
        </el-main>
        <el-main v-else-if="index==='2'">
          <upload-one />
        </el-main>
        <el-main v-else-if="index==='3'">
          <my-courses />
        </el-main>
        <!-- <el-main v-else-if="index==='3-1'">
          <my-courses />
        </el-main>
        <el-main v-else-if="index==='3-2'">
          <my-words />
        </el-main>
        <el-main v-else-if="index==='3-3'">
          <picture-collection />
        </el-main>
        <el-main v-else-if="index==='4'">
          <data-center />
        </el-main>-->
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Header from './Header'
import Aside from './Aside'
import CreateNew from './menu/CreateNew'
import DataCenter from './menu/DataCenter'
import MyCourses from './menu/MyCourses'
import MyWords from './menu/MyWords'
import PictureCollection from './menu/PictureCollection'
import UploadOne from './menu/UploadOne'
export default {
  name: 'Creation',
  components: {
    Header,
    Aside,
    CreateNew,
    DataCenter,
    MyCourses,
    PictureCollection,
    MyWords,
    UploadOne
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
  height: 900px;
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
