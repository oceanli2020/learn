<template>
  <div class="main">
    <div class="center">
      <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:15px">
        <el-breadcrumb-item :to="{ path: '/' }">全部课程</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">IT·互联网</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">编程语言</el-breadcrumb-item>
        <el-breadcrumb-item>Javascript</el-breadcrumb-item>
      </el-breadcrumb>
      <el-divider></el-divider>
      <div>
        <el-link :underline="false" type="primary">全部</el-link>
        <el-link :underline="false" style="margin-left:50px">Java</el-link>
        <el-link :underline="false" style="margin-left:50px">Python</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
        <el-link :underline="false" style="margin-left:50px">C++</el-link>
      </div>
      <el-divider></el-divider>
      <div>
        <el-checkbox-group v-model="checkList">
          <!--vertical-align:top 把元素的顶端与行中最高元素的顶端对齐: -->
          <el-link
            :underline="false"
            style="margin-left:0px;margin-right:55px;vertical-align:top;"
             type="primary"
          >全部</el-link>
          <el-checkbox label="录播"></el-checkbox>
          <el-checkbox label="直播"></el-checkbox>
          <el-checkbox label="免费"></el-checkbox>
          <el-checkbox label="付费"></el-checkbox>
        </el-checkbox-group>
      </div>
      <el-divider></el-divider>
      <div>
        <el-link :underline="false" type="primary">综合排序</el-link>
        <el-link :underline="false" style="margin-left:20px">
          好评
          <i class="el-icon-caret-bottom"></i>
        </el-link>
        <el-link :underline="false" style="margin-left:20px">
          人气
          <i class="el-icon-caret-bottom"></i>
        </el-link>
        <el-link :underline="false" style="margin-left:20px;margin-right:20px">
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
            <span>价格区间</span>
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
          <el-col :span="6" v-for="item in tabledata" :key="item.id">
            <div class="grid-content" style="margin-top:3px">
              <!--padding: 内边距-->
              <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px'}">
                <div class="clearfix">
                  <span style="font-size: 14px;">{{item.name}}</span>
                </div>
                <div class="text" style="margin-top:20px">共10节</div>
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
import { getCourse } from '@/api/course'
export default {
  name: 'Main',
  data() {
    return {
      checkList: [],
      icon: true,
      tabledata: null, // 后端数据
      current: 1, // 当前页码
      total: null, // 总条目
      size: 16 // 每页条目数
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    visibleChange(visible) {
      if (visible) {
        this.icon = false
      } else {
        this.icon = true
      }
    },
    info() {
      getCourse(this.current, this.size).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    currentChange(val) {
      this.current = val
      getCourse(this.current, this.size).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    prevClick(val) {
      // alert(val)
    },
    nextClick(val) { // alert(val)
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
