<template>
  <div class="create-new">
    <div class="title">
      <el-button type="primary" class="create-button" @click="dialog">新建课程</el-button>
      <create-dialog :visible="dialogFormVisible" />
    </div>
    <el-divider></el-divider>
    <!-- <div class="courses">
      <div class="grid-content" style="margin-top:3px;">
        <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px' }">
          <div class="clearfix" style="height:60px">
            <el-link :underline="false">
              <span style="font-size: 14px;">{{ item.name }}</span>
            </el-link>
          </div>
        </el-card>
      </div>
    </div>-->
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
      dialogFormVisible: false
    }
  },
  methods: {
    dialog() {
      this.dialogFormVisible = true
    }
  }
}
</script>

<style scoped>
.create-new {
  height: 500px;
  background-color: white;
  position: relative;
  bottom: 20px;
  width: 1340px;
  left: 150px;
}
.title {
  height: 35px;
}
.create-button {
  float: right;
  margin-top: 10px;
  margin-right: 35px;
}
</style>
