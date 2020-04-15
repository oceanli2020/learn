<template>
  <div class="upload-one">
    <div class="block">
      <el-form
        :model="uploadForm"
        class="upload-form"
        ref="uploadForm"
        :rules="uploadRules"
        label-position="right"
      >
        <el-form-item prop="file">
          <el-upload class="upload" drag :limit="1" action>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或点击上传</div>
            <div class="el-upload__tip" slot="tip">
              只能上传mp4/flv文件，且不超过1024MB
            </div>
          </el-upload>
        </el-form-item>
        <el-form-item prop="courseId">
          <el-select
            v-model="uploadForm.courseId"
            placeholder="请选择已创建课程"
            class="select"
            clearable
            @change="getChapters"
            filterable="true"
          >
            <el-option
              v-for="item in selectdata"
              :key="item"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="chapterId">
          <el-select
            v-model="uploadForm.chapterId"
            placeholder="请选择已选课程的已创建章节或新建章节"
            class="select"
            clearable
            allow-create="true"
            filterable="true"
          >
            <el-option
              v-for="item in chapterList"
              :key="item"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name">
          <el-input
            placeholder="请填写视频名称"
            v-model="uploadForm.name"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item prop="text">
          <el-input
            type="textarea"
            :rows="8"
            placeholder="请填写视频简介"
            v-model="uploadForm.text"
            resize="none"
            maxlength="500"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm" style="float: right"
            >提交</el-button
          >
          <el-button @click="resetForm" style="  float: right;margin-right:10px"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getCourseList, getChapterList } from '@/api/course'
export default {
  name: 'UploadOne',
  data() {
    var checkCourseId = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('课程名称不能为空'))
      } else if (value.length > 75) {
        callback(new Error('课程名称不能大于75位'))
      } else {
        callback()
      }
    }
    var checkChapterId = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('课程名称不能为空'))
      } else if (value.length > 75) {
        callback(new Error('课程名称不能大于75位'))
      } else {
        callback()
      }
    }
    var checkName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('课程名称不能为空'))
      } else if (value.length > 75) {
        callback(new Error('课程名称不能大于75位'))
      } else {
        callback()
      }
    }
    return {
      uploadForm: {
        file: null,
        courseId: '',
        chapterId: '',
        name: '',
        text: ''
      },
      chapterList: [],
      selectdata: [],
      uploadRules: {
        courseId: [{ validator: checkCourseId, trigger: 'blur' }],
        chapterId: [{ validator: checkChapterId, trigger: 'blur' }],
        name: [{ validator: checkName, trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      getCourseList().then(res => {
        this.selectdata = res.data
      })
    },
    getChapters() {
      if (this.uploadForm.courseId.length !== 0) {
        getChapterList(this.uploadForm.courseId).then(res => {
          this.chapterList = res.data
        })
      } else {
        this.chapterList = []
      }
    },
    resetForm() {},
    submitForm() {}
  }
}
</script>

<style scoped>
.upload-one {
  height: 850px;
  background-color: white;
  position: relative;
  bottom: 20px;
  width: 800px;
  left: 330px;
  border-radius: 10px;
}
.block {
  height: 100%;
  width: 600px;
  margin: auto;
  /* background-color: darkseagreen; */
}
.upload {
  margin-top: 30px;
  width: 600px;
}
.upload >>> .el-upload-dragger {
  width: 600px;
  height: 300px;
}
.el-icon-upload {
  margin: 100px 0 16px;
}

.el-upload__tip {
  margin-top: -15px;
  line-height: 20px;
}
.select {
  width: 600px;
}
.autocomplete {
  width: 600px;
}
</style>
