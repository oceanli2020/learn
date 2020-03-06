<template>
  <div class="row">
    <div class="grid-content">
      <p class="font">Copyright © 2020 Tencent. All Rights Reserved.</p>
      <p class="font">
        深圳市腾讯计算机系统有限公司 版权所有
        <el-link type="info">| 腾讯课堂服务协议</el-link>
        <el-link type="info">| 站点地图</el-link>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Footer',
  data() {
    return {}
  }
}
</script>

<style scoped>
/* scoped  即下列css代码只在当前组件有效 */

.grid-content {
  height: 65px;
  width: 600px;
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: 20px;
  text-align: center;
  margin: 0 auto;
  /* background-color: aqua; */
}
.font {
  color: #a0a0a0;
  font-size: 13px;
}

.row {
  /* 去除下方滚动条 */
  overflow: hidden;
  line-height: 1;
}
</style>
