<template>
  <div class="block-root">
    <div>
      <el-col :span="5">
        <el-menu
          class="el-menu-vertical"
          background-color="#545c64"
          text-color="#FFFFFF"
          active-text-color="#FFFFFF"
        >
          <el-menu-item index="1">
            <i class="el-icon-ice-cream-round"></i>
            <span slot="title">导航一</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-water-cup"></i>
            <span slot="title">导航二</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-attract"></i>
            <span slot="title">导航四</span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-monitor"></i>
            <span slot="title">导航五</span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="el-icon-headset"></i>
            <span slot="title">导航六</span>
          </el-menu-item>
          <el-menu-item index="7">
            <i class="el-icon-star-off"></i>
            <span slot="title">导航七</span>
          </el-menu-item>
          <el-menu-item index="8">
            <i class="el-icon-bell"></i>
            <span slot="title">导航八</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </div>
    <div class="block">
      <el-carousel height="448px">
        <el-carousel-item v-for="item in imgList" :key="item">
          <img :src="item.idView" class="img" />
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script scoped>
export default {
  name: 'Carousel',
  data() {
    return {
      imgList: [
        { id: 0, idView: require('@/assets/carousel/carousel_first.jpg') },
        { id: 1, idView: require('@/assets/carousel/carousel_second.jpg') },
        { id: 2, idView: require('@/assets/carousel/carousel_third.jpg') },
        { id: 3, idView: require('@/assets/carousel/carousel_forth.jpg') }
      ]
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.block-root {
  width: 1000px;
  position: absolute;
  left: 459px;
  top: 120px;
}
.img {
  width: 100%;
  height: 100%;
}
</style>
