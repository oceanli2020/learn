<template>
  <div class="my-courses">
    <div class="block">
      <div class="search-block">
        <el-input v-model="input" placeholder="搜索" class="search"></el-input>
      </div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="商品名称">
                <span>{{ props.row.name }}</span>
              </el-form-item>
              <el-form-item label="所属店铺">
                <span>{{ props.row.shop }}</span>
              </el-form-item>
              <el-form-item label="商品 ID">
                <span>{{ props.row.id }}</span>
              </el-form-item>
              <el-form-item label="店铺 ID">
                <span>{{ props.row.shopId }}</span>
              </el-form-item>
              <el-form-item label="商品分类">
                <span>{{ props.row.category }}</span>
              </el-form-item>
              <el-form-item label="店铺地址">
                <span>{{ props.row.address }}</span>
              </el-form-item>
              <el-form-item label="商品描述">
                <span>{{ props.row.desc }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="date" label="课程名称" width="180"></el-table-column>
        <el-table-column prop="name" label="课程种类" width="180"></el-table-column>
        <el-table-column prop="address" label="创建时间" width="180"></el-table-column>
        <el-table-column label align="right">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '2016-05-02'
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          address: '2016-05-02'
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          address: '2016-05-02'
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          address: '2016-05-02'
        }
      ]
    }
  },
  mounted() {},
  methods: {
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    }
  }
}
</script>
<style scoped>
.my-courses {
  min-height: 600px;
  background-color: white;
  position: relative;
  bottom: 20px;
  width: 1340px;
  left: 50px;
  padding-top: 10px;
}
.block {
  /* background-color: aquamarine; */
  height: 100%;
  width: 1300px;
  margin: auto;
}
.search-block {
  height: 50px;
  /* background-color: bisque; */
  padding-top: 20px;
  padding-bottom: 10px;
  padding-left: 59px;
}
.search {
  width: 400px;
}
</style>
