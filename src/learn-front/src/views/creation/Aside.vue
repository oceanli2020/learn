<template>
  <div class="aside">
    <el-menu
      class="el-menu-vertical"
      text-color="#303030 "
      active-text-color="#2894FF"
      :default-active="active"
    >
      <el-menu-item index="1" @click="click('1')">
        <i class="el-icon-menu"></i>
        <span slot="title">开设课程</span>
      </el-menu-item>
      <el-menu-item index="2" @click="click('2')">
        <i class="el-icon-upload"></i>
        <span slot="title">内容上传</span>
      </el-menu-item>
      <el-menu-item index="3" @click="click('3')">
        <i class="el-icon-s-tools"></i>
        <span slot="title">内容管理</span>
      </el-menu-item>
      <!-- <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>内容管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="3-1" @click="click('3-1')">
            <span slot="title">我的课程</span>
          </el-menu-item>
          <el-menu-item index="3-2" @click="click('3-2')">
            <span slot="title">我的文字</span>
          </el-menu-item>
          <el-menu-item index="3-3" @click="click('3-3')">
            <span slot="title">图片的墙</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="4" @click="click('4')">
        <i class="el-icon-document"></i>
        <span slot="title">数据中心</span>
      </el-menu-item>-->
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
