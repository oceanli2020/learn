<template>
  <div class="main">
    <div class="center">
      <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:15px">
        <el-breadcrumb-item>
          <el-link :underline="false" href>全部课程</el-link>
        </el-breadcrumb-item>
        <el-breadcrumb-item v-for="bread in breadList" :key="bread">
          <el-link :underline="false" @click="changeTypeBread(bread.id,bread.name)">{{bread.name}}</el-link>
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
          @click="changeType(type.id,type.name)"
        >{{type.name}}</el-link>
      </div>
      <el-divider></el-divider>
      <div>
        <el-checkbox-group v-model="checkList" @change="checkBOX()">
          <!--vertical-align:top 把元素的顶端与行中最高元素的顶端对齐: -->
          <el-link
            :underline="false"
            style="margin-left:0px;margin-right:55px;vertical-align:top;"
            :type="linkType"
            @click="resetBOX()"
          >全部</el-link>
          <el-checkbox label="录播"></el-checkbox>
          <el-checkbox label="直播"></el-checkbox>
          <el-checkbox label="免费"></el-checkbox>
          <el-checkbox label="付费"></el-checkbox>
        </el-checkbox-group>
      </div>
      <el-divider></el-divider>
      <div>
        <el-link :underline="false" :type="comprehensiveType" @click="comprehensiveSort()">综合排序</el-link>
        <el-link
          :underline="false"
          style="margin-left:20px"
          :type="praiseType"
          @click="praiseSort()"
        >
          好评
          <i class="el-icon-caret-bottom"></i>
        </el-link>
        <el-link
          :underline="false"
          style="margin-left:20px"
          :type="popularityType"
          @click="popularitySort()"
        >
          人气
          <i class="el-icon-caret-bottom"></i>
        </el-link>
        <el-link
          :underline="false"
          style="margin-left:20px;margin-right:20px"
          :type="priceType"
          @click="priceSort()"
        >
          价格
          <i class="el-icon-caret-bottom"></i>
        </el-link>
        <el-divider direction="vertical"></el-divider>
        <el-dropdown
          @command="handleCommand"
          show-timeout="0"
          hide-timeout="100"
          @visible-change="visibleChange"
        >
          <!-- v-bind:用于属性绑定 -->
          <el-link :underline="false">
            <span>{{this.dropDownTitle}}</span>
            <i v-if="icon" class="el-icon-arrow-down"></i>
            <i v-else class="el-icon-arrow-up"></i>
          </el-link>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">不限</el-dropdown-item>
            <el-dropdown-item command="b">￥1-99</el-dropdown-item>
            <el-dropdown-item command="c">￥100-499</el-dropdown-item>
            <el-dropdown-item command="d">￥500-999</el-dropdown-item>
            <el-dropdown-item command="f">￥999----</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div style="margin-top:20px">
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in tabledata" :key="item">
            <div class="grid-content" style="margin-top:3px;">
              <!--padding: 内边距-->
              <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px'}">
                <div class="clearfix" style="height:60px">
                  <el-link :underline="false" @click="clickLink(item.id)">
                    <span style="font-size: 14px;">{{item.name}}</span>
                  </el-link>
                </div>
                <div class="text" style="margin-top:30px">
                  共37节
                  <el-divider direction="vertical"></el-divider>
                  <span class="text" style>新东方教育</span>
                </div>
                <div style="font-size: 14px;color:#FF0000;margin-top:5px">
                  {{item.priceString}}
                  <span class="text" style="margin-left:8px">165164人报名</span>
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
          :page-size="size"
          :current-page="current"
          layout="prev, pager, next"
          :total="total"
          style="text-align: center"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { getCourse, getChildrenType, getParentsType } from '@/api/course'
import store from '@/store'
export default {
  name: 'Main',
  data() {
    return {
      breadList: [], // 面包屑数组
      typeList: null,
      checkList: [],
      icon: true,
      tabledata: null, // 后端数据
      current: 1, // 当前页码
      total: null, // 总条目
      size: 16, // 每页条目数
      sort: 'id',
      query: { courseTypeId: 0 },
      parentId: 0,
      linkType: 'primary',
      comprehensiveType: 'primary',
      praiseType: '',
      popularityType: '',
      priceType: '',
      dropDownTitle: '价格区间',
      lastType: 0
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
    visibleChange(visible) {
      if (visible) {
        this.icon = false
      } else {
        this.icon = true
      }
    },
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
          getChildrenType(this.breadList[this.breadList.length - 2].id).then(
            res => {
              this.typeList = res.data
            }
          )
        }
      })
      this.query.courseTypeId = this.parentId
      getCourse(this.current, this.size, this.sort, this.query).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    info() {
      getChildrenType(this.parentId).then(res => {
        this.typeList = res.data
      })
      getCourse(this.current, this.size, this.sort, this.query).then(res => {
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
      this.current = 1
      this.query.courseTypeId = pId
      getCourse(this.current, this.size, this.sort, this.query).then(res => {
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
        this.current = 1
        this.query.courseTypeId = pId
      })
      getCourse(this.current, this.size, this.sort, this.query).then(res => {
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
      this.current = 1
      this.query.courseTypeId = 0
      getCourse(this.current, this.size, this.sort, this.query).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    checkBOX() {
      if (this.checkList.length === 0) {
        this.linkType = 'primary'
      } else {
        this.linkType = ''
      }
    },
    resetBOX() {
      this.checkList = []
      this.linkType = 'primary'
    },
    comprehensiveSort() {
      this.comprehensiveType = 'primary'
      this.praiseType = ''
      this.popularityType = ''
      this.priceType = ''
    },
    praiseSort() {
      this.comprehensiveType = ''
      this.praiseType = 'primary'
      this.popularityType = ''
      this.priceType = ''
    },
    popularitySort() {
      this.comprehensiveType = ''
      this.praiseType = ''
      this.popularityType = 'primary'
      this.priceType = ''
    },
    priceSort() {
      this.comprehensiveType = ''
      this.praiseType = ''
      this.popularityType = ''
      this.priceType = 'primary'
    },
    handleCommand(command) {
      if (command === 'a') {
        this.dropDownTitle = '价格区间'
      }
      if (command === 'b') {
        this.dropDownTitle = '￥1-99'
      }
      if (command === 'c') {
        this.dropDownTitle = '￥100-499'
      }
      if (command === 'd') {
        this.dropDownTitle = '￥500-999'
      }
      if (command === 'f') {
        this.dropDownTitle = '￥999----'
      }
    },
    currentChange(val) {
      this.current = val
      getCourse(this.current, this.size, this.sort, this.query).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    prevClick(val) {},
    nextClick(val) {},
    clickLink(val) {
      this.$router.push({ path: '/video', query: { id: val } })
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
  top: 0px;
}
.el-divider--horizontal {
  /* 修改分割线的上下外边距 */
  margin: 15px 0;
}
.text {
  font-size: 5px;
  color: #707070;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
}
.box-card {
  width: 250px;
  height: 150px;
}
.el-row {
  /* margin-bottom: 下外边距 */
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
</style>>
