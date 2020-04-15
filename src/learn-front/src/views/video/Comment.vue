<template>
  <div class="comment">
    <div class="areas">
      <div class="area" v-for="index in tabledata" :key="index">
        <div class="avt">
          <el-avatar :size="50" :src="circleUrl"></el-avatar>
        </div>
        <div class="text">
          <p style="font-size:6px;color:#686868">
            <strong>{{ name }}</strong>
          </p>
          <span style="font-size:13px;">{{ comment }}</span>
          <br />
          <span style="font-size:5px;color:#989898;">{{ commentDate }}</span>
          <svg-icon icon-class="point1" style="margin-left:10px"></svg-icon>
          <span style="font-size:5px;color:#989898;">12</span>
          <el-link
            :underline="false"
            style="font-size:5px;margin-left:15px;margin-bottom:2px"
             @click="getWrite(index)"
            >回复</el-link
          >
        </div>
        <div class="areachild" v-for="indexchild in index.tabledatachild" :key="indexchild">
          <div class="avtchild">
            <el-avatar size="small" :src="circleUrl"></el-avatar>
          </div>
          <div class="textchild">
            <span style="font-size:6px;color:#686868">
              <strong>{{ name }}</strong>
            </span>
            <span style="font-size:13px;margin-left:5px">{{ comment }}</span>
            <br />
            <span style="font-size:5px;color:#989898;">{{ commentDate }}</span>
            <svg-icon icon-class="point1" style="margin-left:10px"></svg-icon>
            <span style="font-size:5px;color:#989898;">12</span>
            <el-link
              :underline="false"
              style="font-size:5px;margin-left:15px;margin-bottom:2px"
              @click="getWriteChild(index,indexchild)"
              >回复</el-link
            >
          </div>
        </div>
        <el-pagination
          small
          layout="prev, pager, next"
          :total="totalchild"
          style="margin-left: 83px"
          :page-size="sizechild"
          :hide-on-single-page="true"
        >
        </el-pagination>
        <div class="writechild" v-if="index.booleans.boolean">
          <el-avatar :size="50" :src="circleUrl" class="avatar"></el-avatar>
          <el-input
            type="textarea"
            :rows="3"
            placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论"
            v-model="textarea"
            resize="none"
            maxlength="15"
            show-word-limit="true"
            size="mini "
          ></el-input>
          <el-button type="primary" size="mini" style="height:65px"
            >发表评论</el-button
          >
        </div>
        <el-divider class="divider"></el-divider>
      </div>
    </div>
    <el-pagination
      background
      @current-change="currentChange"
      @prev-click="prevClick"
      @next-click="nextClick"
      :page-size="20"
      :current-page="1"
      layout="prev, pager, next"
      :total="100"
      style="margin-top:30px;margin-left: 73px"
      :hide-on-single-page="true"
    ></el-pagination>
    <div class="write">
      <el-avatar :size="50" :src="circleUrl" class="avatar"></el-avatar>
      <el-input
        type="textarea"
        :rows="3"
        placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论"
        v-model="textarea"
        resize="none"
        maxlength="15"
        show-word-limit="true"
        size="mini "
      ></el-input>
      <el-button type="primary" size="mini" style="height:65px"
        >发表评论</el-button
      >
    </div>
  </div>
</template>

<script>
import store from '@/store'

export default {
  name: 'Comment',
  data() {
    return {
      tabledata: [{id: 1, tabledatachild: [{id: 11}, {id: 12}, {id: 13}, {id: 14}, {id: 15}], booleans: {id: '', boolean: false}}, {id: 2, booleans: {id: '', boolean: false}}, {id: 3, booleans: {id: '', boolean: false}}, {id: 4, booleans: {id: '', boolean: false}}, {id: 5, booleans: {id: '', boolean: false}}],
      textarea: '',
      circleUrl: '',
      name: 'oceanLi',
      comment: '第一次评论呢',
      commentDate: '2019-10-03 18:39',
      totalchild: 21,
      sizechild: 10
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      if (store.getters.token) {

      } else {
        var url = '20200316090713.jpg'
        this.circleUrl =
          'http://localhost:9090/' + url
      }
    },
    getWrite(index) {
      if (index.booleans.boolean === false) {
        index.booleans.id = index.id
        index.booleans.boolean = true
        this.tabledata.forEach(t => {
          if (index.id !== t.id) {
            t.booleans.id = ''
            t.booleans.boolean = false
          }
        })
      } else if (index.booleans.boolean === true && (index.booleans.id === index.id)) {
        index.booleans.id = ''
        index.booleans.boolean = false
      } else if (index.booleans.boolean === true && (index.booleans.id !== index.id)) {
        index.booleans.id = index.id
      }
    },
    getWriteChild(index, indexchild) {
      if (index.booleans.boolean === false) {
        index.booleans.id = indexchild.id
        index.booleans.boolean = true
        this.tabledata.forEach(t => {
          if (index.id !== t.id) {
            t.booleans.id = ''
            t.booleans.boolean = false
          }
        })
      } else if (index.booleans.boolean === true && (index.booleans.id === indexchild.id)) {
        index.booleans.id = ''
        index.booleans.boolean = false
      } else if (index.booleans.boolean === true && (index.booleans.id !== indexchild.id)) {
        index.booleans.id = indexchild.id
      }
    }
  }
}
</script>

<style scoped>
.write {
  padding-top: 15px;
  padding-bottom: 50px;
  /* background-color: firebrick; */
}
.writechild {
  padding-top: 15px;
  padding-bottom: 15px;
  margin-left: 73px;
}
.el-textarea {
  width: 700px;
}
.avatar {
  margin-left: 10px;
  margin-right: 20px;
  vertical-align: middle;
}
.divider {
  width: 903px;
  float: right;
}
.area {
  margin-top: 15px;
  padding-top: 15px;
  padding-bottom: 15px;
  /* background-color:hotpink; */
}
.avt {
  margin-left: 10px;
  width: 55px;
  float: left;
  /* background-color: khaki; */
}
.text {
  margin-left: 85px;
  height: 65px;
  width: 700px;
  position: relative;
  top: -3px;
}
.areachild {
  /* background-color: deepskyblue; */
  padding-top: 5px;
  padding-bottom: 5px;
  margin-left: 83px;
}
.avtchild {
  float: left;
}
.textchild {
  margin-left: 35px;
  /* background-color: deepskyblue; */
}
</style>
