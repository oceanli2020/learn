<template>
  <div class="my-courses">
    <div class="block">
      <div class="breadcrumb">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item v-for="bread in breadList" :key="bread">
            <el-link :underline="false" @click="changeBread(bread.id, bread.name)">{{ bread.name }}</el-link>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="table-block">
        <el-input
          v-model="input"
          :placeholder="placeholder"
          class="search"
          size="small"
          @change="search"
        ></el-input>
        <el-table :data="tableData" style="width: 100%" @row-click="rowClick">
          <el-table-column
            :prop="column[0].value"
            :label="column[0].name"
            width="400"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column :prop="column[1].value" :label="column[1].name" width="200"></el-table-column>
          <el-table-column :prop="column[2].value" :label="column[2].name" width="200"></el-table-column>
          <el-table-column :prop="column[3].value" :label="column[3].name" width="200"></el-table-column>
          <el-table-column width="200" align="right">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="success"
                @click.stop="handleEdit(scope.$index, scope.row)"
              >Edit</el-button>
              <el-popconfirm
                confirmButtonText="确认"
                cancelButtonText="取消"
                icon="el-icon-info"
                iconColor="red"
                title="是否确定删除？"
                @onConfirm="handleDelete(scope.$index, scope.row)"
              >
                <el-button size="mini" type="danger" @click.stop slot="reference">Delete</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <update-course
          :dialogFormVisible="courseVisible"
          :course="row"
          :sup_this="sup_this"
          @childFn="parentFn"
        />
        <update-chapter
          :dialogFormVisible="chapterVisible"
          :chapter="row"
          :sup_this="sup_this"
          @childFn="parentFn"
        />
        <update-video
          :dialogFormVisible="videoVisible"
          :video="row"
          :chapterList="chapterList"
          :chapterName="chapterName"
          :courseId="courseId"
          :sup_this="sup_this"
          @childFn="parentFn"
        />
      </div>
    </div>
    <div class="pagination">
      <el-pagination
        background
        @current-change="currentCourse"
        :page-size="coursePage.size"
        :current-page="coursePage.current"
        layout="prev, pager, next"
        :total="total"
        style="text-align: center"
        :hide-on-single-page="true"
        v-if="type==0"
      ></el-pagination>
      <el-pagination
        background
        @current-change="currentChapter"
        :page-size="chapterPage.size"
        :current-page="chapterPage.current"
        layout="prev, pager, next"
        :total="total"
        style="text-align: center"
        :hide-on-single-page="true"
        v-else-if="type==1"
      ></el-pagination>
      <el-pagination
        background
        @current-change="currentVideo"
        :page-size="videoPage.size"
        :current-page="videoPage.current"
        layout="prev, pager, next"
        :total="total"
        style="text-align: center"
        :hide-on-single-page="true"
        v-else-if="type==2"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import UpdateCourse from '../froms/UpdateCourse'
import UpdateChapter from '../froms/UpdateChapter'
import UpdateVideo from '../froms/UpdateVideo'
import {
  getChapter,
  getChapterList,
  updateCourse,
  updateChapter,
  deleteCourse,
  deleteChapter,
  getMyCourses
} from '@/api/course'
import { getVideo, updateVideo, deleteVideo } from '@/api/video'
export default {
  name: 'MyCourses',
  components: {
    UpdateCourse,
    UpdateChapter,
    UpdateVideo
  },
  data() {
    return {
      coursePage: {
        size: 7,
        current: 1,
        sort: 'id',
        query: { name: '' }
      },
      chapterPage: {
        size: 7,
        current: 1,
        sort: 'id',
        query: { courseId: '', name: '' }
      },
      videoPage: {
        size: 7,
        current: 1,
        sort: 'id',
        query: { chapterId: '', name: '' }
      },
      column: [],
      type: 0,
      breadList: [{ id: '0', name: '全部课程' }],
      tableData: [],
      courseVisible: false,
      chapterVisible: false,
      videoVisible: false,
      sup_this: this,
      courseColumn: [
        { name: '课程名称', value: 'name' },
        { name: '课程分类', value: 'courseTypeName' },
        { name: '创建时间', value: 'createDate' },
        { name: '订阅人数', value: 'subscribeAmount' }
      ],
      chapterColumn: [
        { name: '章节名称', value: 'name' },
        { name: '创建时间', value: 'createDate' },
        { name: '视频个数', value: 'videoCount' },
        { name: '点赞总数', value: 'likeCount' }
      ],
      videoColumn: [
        { name: '视频名称', value: 'name' },
        { name: '上传时间', value: 'createDate' },
        { name: '收藏数量', value: 'collectAmount' },
        { name: '点赞数量', value: 'likeAmount' }
      ],
      row: null,
      chapterList: [],
      chapterName: '',
      courseId: '',
      total: 0,
      input: '',
      placeholder: '请输入课程名称'
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      getMyCourses(this.coursePage).then(res => {
        this.column = this.courseColumn
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    handleEdit(index, row) {
      this.row = row
      if (this.type === 0) {
        this.courseVisible = true
      } else if (this.type === 1) {
        this.chapterVisible = true
      } else if (this.type === 2) {
        this.courseId = this.chapterPage.query.courseId
        getChapterList(this.chapterPage.query.courseId).then(res => {
          this.chapterList = res.data
        })
        this.videoVisible = true
      }
    },
    handleDelete(index, row) {
      if (this.type === 0) {
        deleteCourse(row.id).then(res => {
          this.$message({
            showClose: true,
            duration: 2500,
            message: res.data,
            type: 'success'
          })
          if (this.tableData.length === 1 && this.coursePage.current !== 1) {
            this.coursePage.current--
          }
          getMyCourses(this.coursePage).then(res => {
            this.tableData = res.data.content
            this.total = res.data.totalElements
          })
        })
      } else if (this.type === 1) {
        deleteChapter(row.id).then(res => {
          this.$message({
            showClose: true,
            duration: 2500,
            message: res.data,
            type: 'success'
          })
          if (this.tableData.length === 1 && this.chapterPage.current !== 1) {
            this.chapterPage.current--
          }
          getChapter(this.chapterPage).then(res => {
            this.tableData = res.data.content
            this.total = res.data.totalElements
          })
        })
      } else if (this.type === 2) {
        deleteVideo(row.id).then(res => {
          this.$message({
            showClose: true,
            duration: 2500,
            message: res.data,
            type: 'success'
          })
          if (this.tableData.length === 1 && this.videoPage.current !== 1) {
            this.videoPage.current--
          }
          getVideo(this.videoPage).then(res => {
            this.tableData = res.data.content
            this.total = res.data.totalElements
          })
        })
      }
    },
    currentCourse(val) {
      this.coursePage.current = val
      getMyCourses(this.coursePage).then(res => {
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    currentChapter(val) {
      this.chapterPage.current = val
      getChapter(this.chapterPage).then(res => {
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    currentVideo(val) {
      this.videoPage.current = val
      getVideo(this.videoPage).then(res => {
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    rowClick(row, column, event) {
      if (this.type === 0) {
        this.chapterPage.current = 1
        this.input = ''
        this.placeholder = '请输入章节名称'
        this.chapterPage.query.courseId = row.id
        getChapter(this.chapterPage).then(res => {
          this.breadList.push({ id: '1', name: row.name })
          this.column = this.chapterColumn
          this.tableData = res.data.content
          this.total = res.data.totalElements
          this.type++
        })
      } else if (this.type === 1) {
        this.videoPage.current = 1
        this.input = ''
        this.placeholder = '请输入视频名称'
        this.videoPage.query.chapterId = row.id
        this.chapterName = row.name
        getVideo(this.videoPage).then(res => {
          this.breadList.push({ id: '2', name: row.name })
          this.column = this.videoColumn
          this.tableData = res.data.content
          this.total = res.data.totalElements
          this.type++
        })
      }
    },
    changeBread(id, name) {
      if (id === '0') {
        this.input = ''
        this.placeholder = '请输入课程名称'
        getMyCourses(this.coursePage).then(res => {
          this.breadList = [{ id: '0', name: '全部课程' }]
          this.column = this.courseColumn
          this.tableData = res.data.content
          this.total = res.data.totalElements
          this.type = 0
        })
      } else if (id === '1' && this.type === 2) {
        this.input = ''
        this.placeholder = '请输入章节名称'
        getChapter(this.chapterPage).then(res => {
          this.breadList.pop()
          this.column = this.chapterColumn
          this.tableData = res.data.content
          this.total = res.data.totalElements
          this.type = 1
        })
      }
    },
    updateCourse(val) {
      updateCourse(val).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        getMyCourses(this.coursePage).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      })
    },
    updateChapter(val) {
      updateChapter(val).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        getChapter(this.chapterPage).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      })
    },
    updateVideo(val) {
      updateVideo(val).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        if (this.tableData.length === 1 && this.videoPage.current !== 1) {
          this.videoPage.current--
        }
        getVideo(this.videoPage).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      })
    },
    parentFn(val) {
      if (this.type === 0) {
        this.courseVisible = val
      } else if (this.type === 1) {
        this.chapterVisible = val
      } else if (this.type === 2) {
        this.videoVisible = val
      }
    },
    search() {
      if (this.type === 0) {
        this.coursePage.current = 1
        this.coursePage.query.name = this.input
        getMyCourses(this.coursePage).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      } else if (this.type === 1) {
        this.chapterPage.current = 1
        this.chapterPage.query.name = this.input
        getChapter(this.chapterPage).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      } else {
        this.videoPage.current = 1
        this.videoPage.query.name = this.input
        getVideo(this.videoPage).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      }
    }
  }
}
</script>
<style scoped>
.my-courses {
  min-height: 640px;
  background-color: white;
  position: relative;
  bottom: 20px;
  width: 1340px;
  left: 27px;
  padding-top: 10px;
}
.block {
  height: 100%;
  width: 1200px;
  margin: auto;
  /* background-color: bisque; */
}

.search {
  width: 300px;
  float: left;
}
.pagination {
  margin-top: 30px;
}
.breadcrumb {
  /* background-color:cyan; */
  height: 40px;
  padding-top: 20px;
  padding-bottom: 10px;
}
</style>
