<template>
  <div class="main">
    <div class="center">
      <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:15px">
        <el-breadcrumb-item>
          <a href>全部课程</a>
        </el-breadcrumb-item>
        <el-breadcrumb-item
          v-for="bread in breadList"
          :key="bread"
          :to="{ path: '/course' }"
        >{{bread.name}}</el-breadcrumb-item>
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
            <div class="grid-content" style="margin-top:3px">
              <!--padding: 内边距-->
              <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px'}">
                <div class="clearfix">
                  <span style="font-size: 14px;">{{item.name}}</span>
                </div>
                <div class="text" style="margin-top:20px"></div>
                <div class="text" style>{{item.price}}</div>
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
import { getCourse, getCourseType } from '@/api/course'
export default {
  name: 'Main',
  data() {
    return {
      breadList: [],
      typeList: null,
      allTypeList: null,
      checkList: [],
      icon: true,
      tabledata: null, // 后端数据
      current: 1, // 当前页码
      total: null, // 总条目
      size: 16, // 每页条目数
      parentId: 0,
      linkType: 'primary',
      comprehensiveType: 'primary',
      praiseType: '',
      popularityType: '',
      priceType: '',
      dropDownTitle: '价格区间'
    }
  },
  mounted() {
    this.info()
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
    info() {
      getCourseType(this.parentId).then(res => {
        this.typeList = res.data
        this.allTypeList = res.data
      })
      getCourse(this.current, this.size).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    changeType(pId, pName) {
      this.parentId = pId
      getCourseType(this.parentId).then(res => {
        this.typeList = res.data
      })
      this.breadList.push({ name: pName })
    },
    resetType() {
      this.breadList = []
      this.parentId = 0
      this.typeList = this.allTypeList
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
      getCourse(this.current, this.size).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    prevClick(val) {},
    nextClick(val) {}
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
