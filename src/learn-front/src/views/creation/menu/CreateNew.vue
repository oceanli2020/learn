<template>
  <div class="create-new">
    <div class="title">
      <el-button type="primary" class="create-button" @click="dialog">新建课程</el-button>
      <create-dialog
        :dialogFormVisible="dialogFormVisible"
        :sup_this="sup_this"
        @childFn="parentFn"
      />
    </div>
    <el-divider></el-divider>
    <el-row>
      <el-col :span="5" v-for="item in tabledata" :key="item">
        <div class="courses" style="margin-top:3px">
          <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px' }">
            <div class="course-name">
              <el-link :underline="false" @click="clickLink(item.id)">
                <span style="font-size: 14px;">{{ item.name }}</span>
              </el-link>
            </div>
            <div class="text" style="margin-top:12px">
              <span>共{{item.chapterCount}}节</span>
            </div>
            <div class="text" style="margin-top:12px;margin-left:-2px">
              <svg-icon icon-class="sub" style="font-size: 19px;"></svg-icon>
              <span>{{subNumber}}</span>
              <svg-icon icon-class="point1" style="font-size: 19px;"></svg-icon>
              <span>{{likeNumber}}</span>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <div class="pagination">
      <el-pagination
        background
        @current-change="currentChange"
        :page-size="coursePage.size"
        :current-page="coursePage.current"
        layout="prev, pager, next"
        :total="total"
        style="text-align: center"
        :hide-on-single-page="true"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import CreateDialog from '../froms/CreateDialog'
import { saveCourse, getCourse, getChapterList } from '@/api/course'
export default {
  components: {
    CreateDialog
  },
  data() {
    return {
      dialogFormVisible: false,
      tabledata: [],
      sup_this: this,
      total: 0,
      chapterNumber: 0,
      likeNumber: 0,
      subNumber: 0,
      coursePage: {
        size: 10,
        current: 1,
        sort: 'id',
        query: { isCreateBy: true }
      }
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    dialog() {
      this.dialogFormVisible = true
    },
    parentFn(val) {
      this.dialogFormVisible = val
    },
    async createNew(val) {
      await saveCourse(val.name, val.typeIds[val.typeIds.length - 1]).then(
        res => {
          this.$message({
            message: res.data,
            type: 'success'
          })
        }
      )
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
      // this.tabledata.push(JSON.parse(JSON.stringify(val))) // 把引用传递变为值传递
    },
    currentChange(val) {
      this.coursePage.current = val
      getCourse(this.coursePage).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
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
.create-new {
  height: 530px;
  background-color: white;
  position: relative;
  bottom: 20px;
  width: 1340px;
  left: 50px;
}
.title {
  height: 35px;
}
.create-button {
  float: right;
  margin-top: 10px;
  margin-right: 35px;
}
.box-card {
  width: 240px;
  height: 150px;
  word-break: break-all;
}
.course-name {
  height: 60px;
}
.text {
  font-size: 5px;
  color: #707070;
}
.el-row {
  padding-left: 20px;
}
.el-col {
  padding: 5px 8px 5px 8px;
  width: 260px;
}
.pagination {
  margin-top: 30px;
}
</style>
