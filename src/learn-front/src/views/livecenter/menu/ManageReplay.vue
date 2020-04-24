<template>
  <div class="manage-replay">
    <div class="replay-block">
      <span class="title">直播回放</span>
      <div class="table">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="name" label="回放名称" width="400" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="createDate" label="时间" width="200"></el-table-column>
          <el-table-column prop="durationTime" label="时长" width="200"></el-table-column>
          <el-table-column width="100" align="right">
            <template slot-scope="scope">
              <el-popconfirm
                confirmButtonText="确认"
                cancelButtonText="取消"
                icon="el-icon-info"
                iconColor="red"
                title="是否删除？"
                @onConfirm="handleDelete(scope.$index, scope.row)"
              >
                <el-button size="mini" type="danger" @click.stop slot="reference">Delete</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <el-pagination
          background
          @current-change="currentChange"
          :page-size="page.size"
          :current-page="page.current"
          layout="prev, pager, next"
          :total="total"
          style="text-align: center"
          :hide-on-single-page="true"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { getReplayList, deleteReplay } from '@/api/live'
export default {
  name: 'ManageReplay',
  props: {
    liveId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tableData: [],
      total: 0,
      page: {
        size: 7,
        current: 1,
        sort: 'id',
        query: { liveId: '' }
      }
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      this.page.query.liveId = this.liveId
      getReplayList(this.page).then(res => {
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    currentChange(val) {
      this.page.current = val
      getReplayList(this.page).then(res => {
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    handleDelete(index, row) {
      deleteReplay(row.id).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        if (this.tableData.length === 1 && this.page.current !== 1) {
          this.page.current--
        }
        getReplayList(this.page).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      })
    }
  }
}
</script>

<style scoped>
.manage-replay {
  height: 600px;
  background-color: white;
  position: relative;
  bottom: 20px;
  width: 1000px;
  left: 197px;
  border-radius: 10px;
}
.replay-block {
  margin: auto;
  height: 100%;
  width: 900px;
  /* background-color: aqua; */
  padding-top: 20px;
}

.table {
  margin-top: 20px;
}
.pagination {
  margin-top: 30px;
}
</style>
