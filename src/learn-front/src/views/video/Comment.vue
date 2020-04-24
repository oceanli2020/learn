<template>
  <div class="comment">
    <div class="areas">
      <div class="area" v-for="index in tabledata" :key="index">
        <div class="avt">
          <el-avatar :size="50" :src="circleUrl"></el-avatar>
        </div>
        <div class="text">
          <div style="font-size:6px;color:#686868">
            <strong>{{ index.createBy }}</strong>
          </div>
          <span style="font-size:13px; word-break:break-all;">{{ index.comment }}</span>
          <br />
          <span style="font-size:5px;color:#989898;">{{ index.createDate }}</span>
          <svg-icon icon-class="point1" style="margin-left:10px"></svg-icon>
          <span style="font-size:5px;color:#989898;">12</span>
          <el-link
            :underline="false"
            style="font-size:5px;margin-left:15px;margin-bottom:2px"
            @click="getWrite(index)"
          >回复</el-link>
        </div>
        <div class="areachild" v-for="indexchild in index.commentChildList" :key="indexchild">
          <div class="avtchild">
            <el-avatar size="small" :src="circleUrl"></el-avatar>
          </div>
          <div class="textchild">
            <span style="font-size:6px;color:#686868">
              <strong>{{ indexchild.createBy }}</strong>
            </span>
            <span style="font-size:6px;color:#686868" v-if="indexchild.createTo!==0">
              <strong>回复</strong>
            </span>
            <span style="font-size:6px;color:#686868" v-if="indexchild.createTo!==0">
              <strong>{{indexchild.createTo}}</strong>
            </span>
            <span
              style="font-size:13px;margin-left:10px;word-break:break-all;"
            >{{ indexchild.comment }}</span>
            <br />
            <span style="font-size:5px;color:#989898;">{{ indexchild.createDate }}</span>
            <svg-icon icon-class="point1" style="margin-left:10px"></svg-icon>
            <span style="font-size:5px;color:#989898;">12</span>
            <el-link
              :underline="false"
              style="font-size:5px;margin-left:15px;margin-bottom:2px"
              @click="getWriteChild(index,indexchild)"
            >回复</el-link>
          </div>
        </div>
        <el-pagination
          small
          @current-change="currentChildChange(index,$event)"
          layout="prev, pager, next"
          :total="index.total"
          style="margin-left: 73px"
          :page-size="index.pageDTO.size"
          :hide-on-single-page="true"
          :current-page="index.pageDTO.current"
        ></el-pagination>
        <div class="writechild" v-if="index.isDisplays.isDisplay">
          <el-avatar :size="50" :src="circleUrl" class="avatar"></el-avatar>
          <el-input
            type="textarea"
            :rows="3"
            :placeholder="'回复 '+createTO"
            v-model="commentChildInfo.comment"
            resize="none"
            :maxlength="500"
            show-word-limit="true"
            size="mini "
          ></el-input>
          <el-button
            type="primary"
            size="mini"
            style="height:65px"
            @click="pushChildComment(index)"
          >发表评论</el-button>
        </div>
        <el-divider class="divider"></el-divider>
      </div>
    </div>
    <el-pagination
      background
      @current-change="currentChange"
      @prev-click="prevClick"
      @next-click="nextClick"
      :page-size="commentPage.size"
      :current-page="commentPage.current"
      layout="prev, pager, next"
      :total="commentTotal"
      style="margin-top:20px;margin-left:73px;"
      :hide-on-single-page="true"
    ></el-pagination>
    <div class="write" v-if="videoId!=''">
      <el-avatar :size="50" :src="myCircleUrl" class="avatar"></el-avatar>
      <el-input
        type="textarea"
        :rows="3"
        :placeholder="place"
        v-model="commentInfo.comment"
        resize="none"
        :maxlength="500"
        show-word-limit="true"
        size="mini"
      ></el-input>
      <el-button type="primary" size="mini" style="height:65px" @click="pushComment()">发表评论</el-button>
    </div>
  </div>
</template>

<script>
import store from '@/store'
import { getComment, saveComment, getChildComment } from '@/api/comment'
export default {
  name: 'Comment',
  props: {
    sup_this: {
      type: Object,
      default: null
    },
    videoId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      place: '请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论',
      tabledata: [],
      circleUrl: '',
      name: 'oceanLi',
      comment: '第一次评论呢',
      commentDate: '2019-10-03 18:39',
      profilePhoto: '',
      myCircleUrl: '',
      commentTotal: 0,
      commentPage: {
        size: 10,
        current: 1,
        sort: '-createDate',
        query: { videoId: '' }
      },
      commentChildInfo: {
        videoId: '',
        parentId: '',
        comment: '',
        createTo: ''
      },
      commentInfo: {
        videoId: '',
        parentId: '',
        comment: '',
        createTo: ''
      },
      createTO: '',
      index: ''
    }
  },
  watch: {
    videoId: function() {
      this.commentPage.query.videoId = this.videoId
      getComment(this.commentPage).then(res => {
        this.tabledata = res.data.content
        this.commentTotal = res.data.totalElements
      })
    },
    commentTotal: function() {
      this.$emit('childFn', this.commentTotal)
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      if (store.getters.token) {
        this.profilePhoto = store.getters.profilePhoto
        if (this.profilePhoto === null || this.profilePhoto === '') {
          this.circleUrl =
            'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
        } else {
          this.myCircleUrl = 'http://localhost:9091' + this.profilePhoto
        }
      }
    },
    getWrite(index) {
      if (index.isDisplays.isDisplay === false) {
        index.isDisplays.id = index.id
        index.isDisplays.isDisplay = true
        this.createTO = index.createBy
        this.index = index
        this.tabledata.forEach(t => {
          if (index.id !== t.id) {
            t.isDisplays.id = ''
            t.isDisplays.isDisplay = false
          }
        })
      } else if (
        index.isDisplays.isDisplay === true &&
        index.isDisplays.id === index.id
      ) {
        index.isDisplays.id = ''
        index.isDisplays.isDisplay = false
        this.index = ''
      } else if (
        index.isDisplays.isDisplay === true &&
        index.isDisplays.id !== index.id
      ) {
        index.isDisplays.id = index.id
        this.createTO = index.createBy
        this.index = index
      }
    },
    getWriteChild(index, indexchild) {
      if (index.isDisplays.isDisplay === false) {
        index.isDisplays.id = indexchild.id
        index.isDisplays.isDisplay = true
        this.index = indexchild
        this.createTO = indexchild.createBy
        this.tabledata.forEach(t => {
          if (index.id !== t.id) {
            t.isDisplays.id = ''
            t.isDisplays.isDisplay = false
          }
        })
      } else if (
        index.isDisplays.isDisplay === true &&
        index.isDisplays.id === indexchild.id
      ) {
        index.isDisplays.id = ''
        index.isDisplays.isDisplay = false
        this.index = ''
      } else if (
        index.isDisplays.isDisplay === true &&
        index.isDisplays.id !== indexchild.id
      ) {
        index.isDisplays.id = indexchild.id
        this.createTO = indexchild.createBy
        this.index = indexchild
      }
    },
    currentChange(val) {
      this.commentPage.current = val
      getComment(this.commentPage).then(res => {
        this.tabledata = res.data.content
        this.commentTotal = res.data.totalElements
      })
    },
    currentChildChange(index, val) {
      var pageDTO = index.pageDTO
      pageDTO.query = { parentId: index.id }
      pageDTO.current = val
      getChildComment(pageDTO).then(res => {
        index.commentChildList = res.data.content
        index.total = res.data.totalElements
        index.pageDTO.current = val
      })
    },
    async pushComment() {
      if (
        this.commentInfo.comment === '' ||
        this.commentInfo.comment.length === 0
      ) {
        this.$message({
          showClose: true,
          duration: 2500,
          message: '你还没有评论',
          type: 'error'
        })
        return
      }
      this.commentInfo.videoId = this.videoId
      this.commentInfo.parentId = 0
      this.commentInfo.createTo = 0
      await saveComment(this.commentInfo).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        getComment(this.commentPage).then(res => {
          this.tabledata = res.data.content
          this.commentTotal = res.data.totalElements
        })
      })
      this.commentInfo = {
        videoId: '',
        parentId: '',
        comment: '',
        createTo: ''
      }
    },
    async pushChildComment(index) {
      if (
        this.commentChildInfo.comment === '' ||
        this.commentChildInfo.comment.length === 0
      ) {
        this.$message({
          showClose: true,
          duration: 2500,
          message: '你还没有评论',
          type: 'error'
        })
        return
      }
      this.commentChildInfo.parentId = index.id
      if (index.id === this.index.id) {
        this.commentChildInfo.createTo = 0
      } else {
        this.commentChildInfo.createTo = this.index.createBy
      }
      this.commentChildInfo.videoId = this.videoId
      await saveComment(this.commentChildInfo).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        var pageDTO = index.pageDTO
        pageDTO.query = { parentId: index.id }
        getChildComment(pageDTO).then(res => {
          index.commentChildList = res.data.content
          index.total = res.data.totalElements
        })
      })
      this.commentChildInfo = {
        videoId: '',
        parentId: '',
        comment: '',
        createTo: ''
      }
    }
  }
}
</script>

<style scoped>
.comment {
  height: auto;
  margin-top: -9px;
  /* background-color: aqua; */
}
.write {
  padding-top: 15px;
  padding-bottom: 50px;
  height: 33px;
  margin-top: 20px;
  /* background-color: antiquewhite; */
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
  margin-top: 8px;
  float: left;
}
.divider {
  width: 903px;
  float: right;
}
.area {
  margin-top: 19px;
  padding-top: 15px;
  padding-bottom: 15px;
  /* background-color: aqua; */
}
.avt {
  margin-left: 10px;
  width: 50px;
  float: left;
  height: 50px;
  /* background-color: khaki; */
}
.text {
  margin-left: 83px;
  height: auto;
  width: 700px;
  position: relative;
  top: 6px;
  /* background-color: khaki; */
}
.areachild {
  /* background-color: deepskyblue; */
  padding-top: 5px;
  padding-bottom: 5px;
  margin-left: 83px;
  margin-top: 9px;
  width: 702px;
}
.avtchild {
  float: left;
}
.textchild {
  margin-left: 35px;
  height: auto;
  width: 667px;
  /* background-color: brown; */
}
</style>
