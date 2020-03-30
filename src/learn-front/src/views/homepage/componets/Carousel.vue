<template>
  <div class="block-root">
    <div>
      <el-col :span="5">
        <el-menu
          class="el-menu-vertical"
          background-color="#545c64"
          text-color="#FFFFFF"
          active-text-color="#FFFFFF"
          @select="selectMenu"
        >
          <el-menu-item index="1">
            <i class="el-icon-ice-cream-round"></i>
            <span slot="title">{{courseTypeList[0].name}}</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-water-cup"></i>
            <span slot="title">{{courseTypeList[1].name}}</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">{{courseTypeList[2].name}}</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-attract"></i>
            <span slot="title">{{courseTypeList[3].name}}</span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-monitor"></i>
            <span slot="title">{{courseTypeList[4].name}}</span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="el-icon-headset"></i>
            <span slot="title">{{courseTypeList[5].name}}</span>
          </el-menu-item>
          <el-menu-item index="7">
            <i class="el-icon-star-off"></i>
            <span slot="title">{{courseTypeList[6].name}}</span>
          </el-menu-item>
          <el-menu-item index="8">
            <i class="el-icon-bell"></i>
            <span slot="title">{{courseTypeList[7].name}}</span>
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
    <div>
      <el-tabs v-model="activeName" stretch="true">
        <el-tab-pane label="职业技能" name="first">
          <div class="text" style="margin-top:10px;margin-bottom:20px">{{title}}</div>
          <el-row>
            <el-col :span="12">
              <div class="grid-content bg-purple">
                <img class="course-img" src="@/assets/carousel/carousel_first.jpg" />
              </div>
            </el-col>
            <el-col :span="12">
              <div class="grid-content bg-purple-light"></div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="编程语言" name="second">
          <div class="text" style="margin-top:10px;margin-bottom:20px">{{title}}</div>
          <el-row>
            <el-col :span="12">
              <div class="grid-content bg-purple">
                <img class="course-img" id="changeImg2" :src="Img2Url" />
              </div>
            </el-col>
            <el-col :span="12">
              <div class="grid-content bg-purple-light">
                <el-menu
                  class="el-menu-vertical"
                  background-color="#E8E8E8"
                  text-color="#272727"
                  active-text-color="#272727"
                >
                  <el-menu-item index="1" @mouseenter.native="changeImg(a,'changeImg2')">
                    <span slot="title">{{aText}}</span>
                  </el-menu-item>
                  <el-menu-item index="2" @mouseenter.native="changeImg(b,'changeImg2')">
                    <span slot="title">{{bText}}</span>
                  </el-menu-item>
                  <el-menu-item index="3" @mouseenter.native="changeImg(c,'changeImg2')">
                    <span slot="title">{{cText}}</span>
                  </el-menu-item>
                  <el-menu-item index="4" @mouseenter.native="changeImg(d,'changeImg2')">
                    <span slot="title">{{dText}}</span>
                  </el-menu-item>
                  <el-menu-item index="5" @mouseenter.native="changeImg(e,'changeImg2')">
                    <span slot="title">{{eText}}</span>
                  </el-menu-item>
                  <el-menu-item index="6" @mouseenter.native="changeImg(f,'changeImg2')">
                    <span slot="title">{{fText}}</span>
                  </el-menu-item>
                </el-menu>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="实用外语" name="third">
          <div class="text" style="margin-top:10px;margin-bottom:20px">{{title}}</div>
          <el-row>
            <el-col :span="12">
              <div class="grid-content bg-purple">
                <img class="course-img" src="@/assets/carousel/carousel_third.jpg" />
              </div>
            </el-col>
            <el-col :span="12">
              <div class="grid-content bg-purple-light"></div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="传统文化" name="fourth">
          <div class="text" style="margin-top:10px;margin-bottom:20px">{{title}}</div>
          <el-row>
            <el-col :span="12">
              <div class="grid-content bg-purple">
                <img class="course-img" src="@/assets/carousel/carousel_forth.jpg" />
              </div>
            </el-col>
            <el-col :span="12">
              <div class="grid-content bg-purple-light"></div>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script scoped>
import { getChildrenType } from '@/api/course'
export default {
  name: 'Carousel',
  data() {
    return {
      imgList: [
        { id: 0, idView: require('@/assets/carousel/carousel_first.jpg') },
        { id: 1, idView: require('@/assets/carousel/carousel_second.jpg') },
        { id: 2, idView: require('@/assets/carousel/carousel_third.jpg') },
        { id: 3, idView: require('@/assets/carousel/carousel_forth.jpg') }
      ],
      activeName: 'second',
      title: '精选直播课',
      a: 'assets/courses/course1.jpg',
      b: 'assets/courses/course2.jpg',
      c: 'assets/courses/course3.jpg',
      d: 'assets/courses/course4.jpg',
      e: 'assets/courses/course5.jpg',
      f: 'assets/courses/course6.jpg',
      aText: 'PHP7进阶到架构-Laravel/Redis/Swoole/高并发分布式',
      bText: 'Python零基础快速就业到高级工程师/网络爬虫/人工智能/商业项目',
      cText: 'Java架构师,Java高级开发|java进阶大型互联网架构师',
      dText: 'JAVA高级开发 架构师课程',
      eText: 'JAVA架构师精品课 企业级架构/分布式/高并发/微服务',
      fText: 'C/C++ 零基到大神精讲(C++17最新标准/游戏编程/项目实战)',
      courseTypeList: [],
      Img2Url: ''
    }
  },
  mounted() {
    this.info()
    this.Img2Url = require('@/' + this.a)
  },
  methods: {
    info() {
      getChildrenType(0).then(res => {
        this.courseTypeList = res.data
      })
    },
    changeImg(x, y) {
      document.getElementById(y).src = require('@/' + x)
    },
    selectMenu(index) {
      this.$store.commit('SET_COURSETYPE_ID', this.courseTypeList[index - 1].id)
      this.$router.push('/course')
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
  top: 100px;
}
.img {
  width: 100%;
  height: 100%;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.course-img {
  height: 336px;
  width: 100%;
}
.text {
  text-align: center;
  font-size: 30px;
}
.bg-purple-light {
  background: #e5e9f2;
  height: 336px;
}
</style>
