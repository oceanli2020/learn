<template>
  <div class="aside">
    <el-menu
      class="el-menu-vertical"
      text-color="#303030 "
      active-text-color="#2894FF"
      :default-active="active"
    >
      <el-menu-item index="1" @click="click('1')">
        <i class="el-icon-s-custom"></i>
        <span slot="title">直播设置</span>
      </el-menu-item>
      <el-menu-item index="2" @click="click('2')">
        <i class="el-icon-s-tools"></i>
        <span slot="title">回放管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'Aside',
  data() {
    return {
      active: '1'
    }
  },
  mounted() {},
  methods: {
    click(index) {
      this.$emit('childFn', index)
    }
  }
}
</script>
<style scoped>
</style>
