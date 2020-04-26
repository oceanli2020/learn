<template>
  <div class="row">
    <div class="grid-content">
      <p class="font">Copyright © 2020 Ocean. All Rights Reserved.</p>
      <p class="font">
        <span>南京市XXXXXX有限公司 版权所有</span>
        <el-link type="info">
          <span>| 隐私政策</span>
        </el-link>
        <el-link type="info">
          <span>| 侵权投诉</span>
        </el-link>
        <el-link type="info">
          <span>| 问题反馈</span>
        </el-link>
        <el-link type="info">
          <span>| 帮助</span>
        </el-link>
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
