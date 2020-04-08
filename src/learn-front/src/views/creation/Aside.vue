<template>
  <div class="aside">
    <el-menu
      class="el-menu-vertical"
      text-color="#303030 "
      active-text-color="#2894FF"
    >
     <el-menu-item index="1">
        <i class="el-icon-setting"></i>
        <span slot="title">开设课程</span>
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>内容管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1"><i class="el-icon-setting"></i><span slot="title">我的课程</span></el-menu-item>
          <el-menu-item index="1-2"><i class="el-icon-setting"></i><span slot="title">我的文字</span></el-menu-item>
          <el-menu-item index="1-3"><i class="el-icon-setting"></i><span slot="title">图片墙</span></el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-menu-item index="3">
        <i class="el-icon-menu"></i>
        <span slot="title">内容上传</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-document"></i>
        <span slot="title">内容管理</span>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-setting"></i>
        <span slot="title">数据中心</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped></style>
