<template>
  <div class="create-new">
    <div class="title">
      <el-button type="primary" class="create-button" @click="dialog"
        >新建课程</el-button
      >
      <create-dialog
        :dialogFormVisible="dialogFormVisible"
        :sup_this="sup_this"
        @childFn="parentFn"
      />
    </div>
    <el-divider></el-divider>
    <el-row>
      <el-col :span="5" v-for="item in tabledata" :key="item" >
        <div class="courses" style="margin-top:3px;">
          <el-card
            class="box-card"
            shadow="hover"
            :body-style="{ padding: '15px' }"
          >
            <div class="clearfix" style="height:30px">
              <el-link :underline="false">
                <span style="font-size: 14px;">{{ item.name }}</span>
              </el-link>
            </div>
          </el-card>
        </div>
      </el-col>
      </el-row>
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
import CreateDialog from '../froms/CreateDialog'
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
      size: 10,
      current: 1

    }
  },
  methods: {
    dialog() {
      this.dialogFormVisible = true
    },
    parentFn(val) {
      this.dialogFormVisible = val
    },
    createNew(val) {
      if (this.tabledata.length === this.size) {
        this.tabledata = []
      }
      this.tabledata.push(JSON.parse(JSON.stringify(val)))// 把引用传递变为值传递
      this.total++
    },
    currentChange(val) {
      this.current = val
    }

  }
}
</script>

<style scoped>
.create-new {
  height: 600px;
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
}
.el-row {
  padding-left: 20px;
}
.el-col {
  padding: 5px 8px 5px 8px;
  width: 260px;

}
.pagination{

  margin-top: 20px;

}
</style>
