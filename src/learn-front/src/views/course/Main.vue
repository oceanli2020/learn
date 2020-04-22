<template>
  <div class="main">
    <div class="center">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>
          <el-link :underline="false" href>全部课程</el-link>
        </el-breadcrumb-item>
        <el-breadcrumb-item v-for="bread in breadList" :key="bread">
          <el-link
            :underline="false"
            @click="changeTypeBread(bread.id, bread.name)"
          >{{ bread.name }}</el-link>
        </el-breadcrumb-item>
      </el-breadcrumb>
      <el-divider></el-divider>
      <div>
        <el-link :underline="false" type="primary" @click="resetType()">全部</el-link>
        <el-link
          :underline="false"
          style="margin-left:50px"
          v-for="type in typeList"
          :key="type"
          @click="changeType(type.id, type.name)"
        >{{ type.name }}</el-link>
      </div>
      <el-divider></el-divider>
      <div>
        <el-link :underline="false" :type="comprehensiveType" @click="comprehensiveSort()">综合排序</el-link>
        <el-link :underline="false" style="margin-left:20px" :type="timeType" @click="timeSort()">
          时间
          <svg-icon icon-class="sort"></svg-icon>
        </el-link>
        <el-link :underline="false" style="margin-left:20px" :type="subType" @click="subSort()">
          订阅
          <svg-icon icon-class="sort"></svg-icon>
        </el-link>
        <el-link
          :underline="false"
          style="margin-left:20px;margin-right:20px"
          :type="likeType"
          @click="likeSort()"
        >
          点赞
          <svg-icon icon-class="sort"></svg-icon>
        </el-link>
      </div>
      <div style="margin-top:20px">
        <el-row>
          <el-col :span="6" v-for="item in tabledata" :key="item">
            <div class="courses" style="margin-top:-3px">
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
                  <svg-icon icon-class="point1" style="font-size: 19px;;margin-left:8px"></svg-icon>
                  <span>{{item.likeCount}}</span>
                </div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="block">
        <el-pagination
          background
          @current-change="currentChange"
          @prev-click="prevClick"
          @next-click="nextClick"
          :page-size="coursePage.size"
          :current-page="coursePage.current"
          layout="prev, pager, next"
          :total="total"
          style="text-align: center"
          :hide-on-single-page="true"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getCourse,
  getChildrenType,
  getParentsType,
  getChapterList
} from '@/api/course'
import store from '@/store'
export default {
  name: 'Main',
  data() {
    return {
      breadList: [], // 面包屑数组
      typeList: null,
      tabledata: null, // 后端数据
      total: null, // 总条目
      parentId: 0,
      comprehensiveType: 'primary',
      timeType: '',
      subType: '',
      likeType: '',
      lastType: 0,
      coursePage: {
        current: 1, // 当前页码
        size: 16, // 每页条目数
        sort: 'id',
        query: { courseTypeId: 0 }
      }
    }
  },
  mounted() {
    if (store.getters.courseTypeId === '') {
      this.info()
    } else {
      this.routerInfo()
    }
  },
  watch: {},
  methods: {
    async routerInfo() {
      this.parentId = store.getters.courseTypeId
      this.$store.commit('SET_COURSETYPE_ID', '')
      await getParentsType(this.parentId).then(res => {
        this.breadList = res.data
      })
      getChildrenType(this.parentId).then(res => {
        var arr = Object.keys(res.data)
        if (arr.length !== 0) {
          this.typeList = res.data
        } else {
          this.lastType = 1
          var pId
          if (this.breadList.length < 2) {
            pId = 0
          } else {
            pId = this.breadList[this.breadList.length - 2].id
          }
          getChildrenType(pId).then(res => {
            this.typeList = res.data
          })
        }
      })
      this.coursePage.query.courseTypeId = this.parentId
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    info() {
      getChildrenType(this.parentId).then(res => {
        this.typeList = res.data
      })
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    async changeTypeBread(pId, pName) {
      if (this.parentId === pId) return
      var index = -1
      for (let i = 0; i < this.breadList.length; i++) {
        if (this.breadList[i].id === pId) {
          index = i
          break
        }
      }
      var length = this.breadList.length
      while (index < length - 1) {
        this.breadList.pop()
        index++
      }
      this.parentId = pId
      await getChildrenType(this.parentId).then(res => {
        var arr = Object.keys(res.data)
        if (arr.length !== 0) {
          this.lastType = 0
          this.typeList = res.data
        } else {
          this.lastType = 1
        }
      })
      this.coursePage.current = 1
      this.coursePage.query.courseTypeId = pId
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    async changeType(pId, pName) {
      if (this.parentId === pId) return
      if (this.lastType === 1) {
        this.breadList.pop()
      }
      this.breadList.push({ id: pId, name: pName })
      this.parentId = pId
      await getChildrenType(this.parentId).then(res => {
        var arr = Object.keys(res.data)
        if (arr.length !== 0) {
          this.lastType = 0
          this.typeList = res.data
        } else {
          this.lastType = 1
        }
        this.coursePage.current = 1
        this.coursePage.query.courseTypeId = pId
      })
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    resetType() {
      this.breadList = []
      this.parentId = 0
      getChildrenType(this.parentId).then(res => {
        this.typeList = res.data
      })
      this.coursePage.current = 1
      this.coursePage.query.courseTypeId = 0
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    comprehensiveSort() {
      this.comprehensiveType = 'primary'
      this.timeType = ''
      this.subType = ''
      this.likeType = ''
      this.coursePage.sort = 'id'
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    timeSort() {
      this.comprehensiveType = ''
      this.timeType = 'primary'
      this.subType = ''
      this.likeType = ''
      if (this.coursePage.sort !== '-createDate') {
        this.coursePage.sort = '-createDate'
      } else {
        this.coursePage.sort = 'createDate'
      }
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    subSort() {
      this.comprehensiveType = ''
      this.timeType = ''
      this.subType = 'primary'
      this.likeType = ''
      if (this.coursePage.sort !== '-subscribeAmount') {
        this.coursePage.sort = '-subscribeAmount'
      } else {
        this.coursePage.sort = 'subscribeAmount'
      }
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    likeSort() {
      this.comprehensiveType = ''
      this.timeType = ''
      this.subType = ''
      this.likeType = 'primary'
      if (this.coursePage.sort !== '-likeCount') {
        this.coursePage.sort = '-likeCount'
      } else {
        this.coursePage.sort = 'likeCount'
      }
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },

    currentChange(val) {
      this.coursePage.current = val
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    prevClick(val) {},
    nextClick(val) {},
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
.center {
  height: 1000px;
  width: 1000px;
  /* background-color: aqua; */
  margin: 0 auto;
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: -5px;
}
.el-divider--horizontal {
  /* 修改分割线的上下外边距 */
  margin: 15px 0;
}
.text {
  font-size: 5px;
  color: #707070;
}
.box-card {
  width: 240px;
  height: 150px;
  word-break: break-all;
}

.el-col {
  padding: 5px 4px 5px 4px;
}
.course-name {
  height: 60px;
}
.block {
  margin-top: 30px;
}
</style>
