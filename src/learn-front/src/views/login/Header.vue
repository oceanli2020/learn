<template>
  <div style="margin-left:230px;margin-right:200px;margin-top:10px">
    <el-link type="primary" class="brand" :underline="false" href="http://localhost:8080/">在线教育平台</el-link>
  </div>
</template>

<script scoped>
export default {
  name: 'Header',
  data() {
    return {}
  },
  methods: {}
}
</script>

<style scoped>
.brand {
  float: left;
  width: 125px;
  color: #409eff;
  font-size: 20px;
  /* background-color: aqua; */
  height: 60px;
  position: relative;
  left: 55px;
}
</style>
