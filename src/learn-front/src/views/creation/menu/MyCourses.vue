<template>
  <div class="my-courses">
    <div class="block">
      <div class="breadcrumb">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item v-for="bread in breadList" :key="bread">
            <el-link
              :underline="false"
              @click="changeBread(bread.id, bread.name)"
              >{{ bread.name }}</el-link
            >
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="table-block">
        <el-input
          v-model="input"
          placeholder="搜索"
          class="search"
          size="small"
        ></el-input>
        <el-table :data="tableData" style="width: 100%" @row-click="rowClick">
          <el-table-column
            prop="column1"
            :label="column[0]"
            width="400"
          ></el-table-column>
          <el-table-column
            prop="column2"
            :label="column[1]"
            width="200"
          ></el-table-column>
          <el-table-column
            prop="column3"
            :label="column[2]"
            width="200"
          ></el-table-column>
          <el-table-column
            prop="column4"
            :label="column[3]"
            width="200"
          ></el-table-column>
          <el-table-column width="200" align="right">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="success"
                @click.stop="handleEdit(scope.$index, scope.row)"
                >Edit</el-button
              >

              <el-popconfirm
                confirmButtonText="确认"
                cancelButtonText="取消"
                icon="el-icon-info"
                iconColor="red"
                title="这是一段内容确定删除吗？"
              >
                <el-button
                  size="mini"
                  type="danger"
                  @click.stop="handleDelete(scope.$index, scope.row)"
                  slot="reference"
                  >Delete</el-button
                ></el-popconfirm
              >
            </template>
          </el-table-column>
        </el-table>
        <update-course
          :dialogFormVisible="courseVisible"
          :sup_this="sup_this"
          @childFn="parentFn"
        />
         <update-chapter
          :dialogFormVisible="chapterVisible"
          :sup_this="sup_this"
          @childFn="parentFn"
        />
        <update-video
          :dialogFormVisible="videoVisible"
          :sup_this="sup_this"
          @childFn="parentFn"
        />
      </div>
    </div>
    <div class="pagination">
      <el-pagination
        background
        @current-change="currentChange"
        :page-size="size"
        :current-page="current"
        layout="prev, pager, next"
        :total="total"
        style="text-align: center"
        :hide-on-single-page="true"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import UpdateCourse from '../froms/UpdateCourse'
import UpdateChapter from '../froms/UpdateChapter'
import UpdateVideo from '../froms/UpdateVideo'
export default {
  name: 'MyCourses',
  components: {
    UpdateCourse,
    UpdateChapter,
    UpdateVideo
  },
  data() {
    return {
      size: 10,
      total: 20,
      current: 1,
      column: ['课程名称', '课程种类', '创建时间', '订阅人数'],
      type: 0,
      breadList: [{ id: '0', name: '全部课程' }],
      tableData: [],
      courseVisible: false,
      chapterVisible: false,
      videoVisible: false,
      sup_this: this,
      courses: [
        {
          column1: '工业产品设计手绘、 产品线稿、马克笔技',
          column2: '王小虎',
          column3: '2016-05-02',
          column4: '2016-05-02'
        },
        {
          column1: '工业产品设计手绘、 产品线稿、马克笔技',
          column2: '王小虎',
          column3: '2016-05-02',
          column4: '2016-05-02'
        },
        {
          column1: '工业产品设计手绘、 产品线稿、马克笔技',
          column2: '王小虎',
          column3: '2016-05-02',
          column4: '2016-05-02'
        },
        {
          column1: '工业产品设计手绘、 产品线稿、马克笔技',
          column2: '王小虎',
          column3: '2016-05-02',
          column4: '2016-05-02'
        },
        {
          column1: '工业产品设计手绘、 产品线稿、马克笔技',
          column2: '王小虎',
          column3: '2016-05-02',
          column4: '2016-05-02'
        },
        {
          column1: '工业产品设计手绘、 产品线稿、马克笔技',
          column2: '王小虎',
          column3: '2016-05-02',
          column4: '2016-05-02'
        },
        {
          column1: '工业产品设计手绘、 产品线稿、马克笔技',
          column2: '王小虎',
          column3: '2016-05-02',
          column4: '2016-05-02'
        }
      ],
      chapters: [
        {
          column1: '章节1',
          column2: '1234',
          column3: 10,
          column4: 20
        }
      ],
      videos: [
        {
          column1: '视频1',
          column2: '1234',
          column3: 120,
          column4: 202
        }
      ]
    }
  },
  mounted() {
    this.tableData = this.courses
  },
  methods: {
    handleEdit(index, row) {
      if (this.type === 0) {
        this.courseVisible = true
      } else if (this.type === 1) {
        this.chapterVisible = true
      } else if (this.type === 2) {
        this.videoVisible = true
      }
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    },
    currentChange() {},
    rowClick(row, column, event) {
      if (this.type === 0) {
        this.breadList.push({ id: '1', name: row.column1 })
        this.column = ['章节名称', '创建时间', '视频个数', '点赞数量']
        this.tableData = this.chapters
        this.type++
      } else if (this.type === 1) {
        this.breadList.push({ id: '2', name: row.column1 })
        this.column = ['视频名称', '上传时间', '收藏数量', '点赞数量']
        this.tableData = this.videos
        this.type++
      }
    },
    changeBread(id, name) {
      if (id === '0') {
        this.breadList = [{ id: '0', name: '全部课程' }]
        this.column = ['课程名称', '课程种类', '创建时间', '订阅人数']
        this.tableData = this.courses
        this.type = 0
      } else if (id === '1' && this.type === 2) {
        this.breadList.pop()
        this.column = ['章节名称', '创建时间', '视频个数', '点赞数量']
        this.tableData = this.chapters
        this.type = 1
      }
    },
    parentFn(val) {
      if (this.type === 0) {
        this.courseVisible = val
      } else if (this.type === 1) {
        this.chapterVisible = val
      } else if (this.type === 2) {
        this.videoVisible = val
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
  left: 50px;
  padding-top: 10px;
}
.block {
  height: 100%;
  width: 1200px;
  margin: auto;
}
.table-block {
  /* background-color: bisque; */
}
.search {
  width: 300px;
  float: left;
}
.pagination {
  margin-top: 20px;
}
.breadcrumb {
  /* background-color:cyan; */
  height: 40px;
  padding-top: 20px;
  padding-bottom: 10px;
}
</style>
