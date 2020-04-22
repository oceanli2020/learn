<template>
  <div class="carousel">
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
    </div>
    <div class="courses">
      <div class="title">
        <span>热门推荐</span>
      </div>
      <div class="block-courses">
        <el-row>
          <el-col :span="6" v-for="item in tabledata" :key="item">
            <div style="margin-top:7px;margin-left:4px;">
              <!--padding: 内边距-->
              <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px' }">
                <div class="course-name">
                  <el-link :underline="false" @click="clickLink(item.id)">
                    <span style="font-size: 14px;">{{ item.name }}</span>
                  </el-link>
                </div>
                <div class="text" style="margin-top:12px">
                  <span>共{{item.chapterCount}}节</span>
                  <el-divider direction="vertical"></el-divider>
                  <span style="margin-left:0px">{{item.createBy}}</span>
                </div>
                <div class="text" style="margin-top:12px;margin-left:-2px">
                  <svg-icon icon-class="sub" style="font-size: 19px;"></svg-icon>
                  <span>{{item.subscribeAmount}}</span>
                  <svg-icon icon-class="point1" style="font-size: 19px;margin-left:8px"></svg-icon>
                  <span>{{item.likeCount}}</span>
                </div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script scoped>
import { getCourse, getChildrenType, getChapterList } from '@/api/course'
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
      courseTypeList: [],
      tabledata: [],
      coursePage: {
        current: 1,
        size: 8,
        sort: 'id',
        query: { courseTypeId: 0 }
      }
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      getChildrenType(0).then(res => {
        this.courseTypeList = res.data
      })
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
      })
    },
    selectMenu(index) {
      this.$store.commit('SET_COURSETYPE_ID', this.courseTypeList[index - 1].id)
      this.$router.push('/course')
    },
    clickLink(val) {
      getChapterList(val).then(res => {
        if (res.data.length !== 0) {
          this.$router.push({ path: '/video', query: { id: val } })
        } else {
          this.$message({
            showClose: true,
            message: '该课程没有内容',
            type: 'error'
          })
        }
      })
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
  height: 508px;
  margin: auto;
}
.img {
  width: 100%;
  height: 100%;
}
.courses {
  width: 1000px;
  height: 400px;
  /* background-color: bisque; */
  margin: auto;
  padding-top: 10px;
}
.box-card {
  width: 240px;
  height: 150px;
  word-break: break-all;
}
.text {
  font-size: 5px;
  color: #707070;
}
.el-col-course {
  padding: 5px 4px 5px 4px;
}
.block-courses {
  margin-top: 10px;
  height: 334px;
  /* background-color: brown; */
}
.title {
  height: 30px;
  font-size: 20px;
  /* background-color: chartreuse; */
  text-align: center;
}
.course-name {
  height: 60px;
}
</style>
