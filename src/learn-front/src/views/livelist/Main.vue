<template>
  <div class="main">
    <div class="center">
      <div>
        <el-radio-group v-model="radio" @change="changeRadio">
          <el-radio :label="1">正在直播</el-radio>
          <el-radio :label="2">所有</el-radio>
        </el-radio-group>
      </div>
      <el-divider></el-divider>
      <div style="margin-top:20px">
        <el-row>
          <el-col :span="6" v-for="item in tabledata" :key="item">
            <div style="margin-top:-3px">
              <!--padding: 内边距-->
              <el-card class="box-card" shadow="hover" :body-style="{ padding: '15px' }">
                <div class="live-name">
                  <el-link :underline="false" @click="clickLink(item.id)">
                    <span style="font-size: 14px;">{{ item.name }}</span>
                  </el-link>
                </div>
                <div class="text" style="margin-top:24px">
                  <span>{{item.isStart==='1'?'已开播':'在休息'}}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span>{{item.userName}}</span>
                </div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="block">
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
import { getLiveList } from '@/api/live'
export default {
  name: 'Main',
  data() {
    return {
      radio: 1,
      tabledata: [],
      total: 0,
      page: {
        size: 16,
        current: 1,
        sort: 'id',
        query: { isStart: '1', input: '' }
      },
      sup_this: this
    }
  },
  mounted() {
    this.info()
    this.$emit('childFn', this.sup_this)
  },
  watch: {},
  methods: {
    info() {
      getLiveList(this.page).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    changeRadio(val) {
      if (val === 1) {
        this.page.query.isStart = '1'
      } else {
        this.page.query.isStart = ''
      }
      this.page.current = 1
      getLiveList(this.page).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    currentChange(val) {
      this.page.current = val
      getLiveList(this.page).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
      })
    },
    clickLink(val) {
      this.$router.push({ path: '/livestudio', query: { id: val } })
    },
    search(val) {
      this.page.current = 1
      this.page.query.input = val
      getLiveList(this.page).then(res => {
        this.tabledata = res.data.content
        this.total = res.data.totalElements
        this.page.query.input = ''
      })
    }
  }
}
</script>

<style scoped>
.center {
  height: 900px;
  width: 1000px;
  /* background-color: aqua; */
  margin: 0 auto;
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: -5px;
}
.el-divider--horizontal {
  /* 修改分割线的上下外边距 */
  margin: 15px 0;
}
.text {
  font-size: 5px;
  color: #707070;
}
.box-card {
  width: 240px;
  height: 150px;
  word-break: break-all;
}

.el-col {
  padding: 5px 4px 5px 4px;
}
.live-name {
  height: 60px;
}
.block {
  margin-top: 30px;
}
</style>
