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
        <span style=" font-size: 14px; color:#787878 ">分类</span>
        <el-link :underline="false" style="margin-left:50px;" type="primary">全部</el-link>
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
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
      <div style="margin-top:20px">
        <el-row :gutter="20">
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
      <div style="margin-top:20px">
        <el-row :gutter="20">
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
      <div style="margin-top:20px">
        <el-row :gutter="20">
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span style="font-size: 14px;">平面设计/插画/PS/AI/CDR/logo设计/VI设计/品牌设计/海报设计</span>
                </div>
                <div class="text">共10节</div>
                <div class="text">免费</div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="block">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="150"
          style="text-align: center"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      checkList: [],
      icon: true
    }
  },
  methods: {
    visibleChange(visible) {
      if (visible) {
        this.icon = false
      } else {
        this.icon = true
      }
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
  font-size: 14px;
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
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>>
