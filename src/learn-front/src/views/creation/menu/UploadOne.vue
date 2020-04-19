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
        <el-form-item>
          <el-upload
            ref="upload"
            class="upload"
            drag
            :limit="1"
            action
            :auto-upload="false"
            :http-request="uploadFileMethod"
            :before-upload="beforeVideoUpload"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或点击上传</div>
            <div class="el-upload__tip" slot="tip">只能上传mp4文件，且不超过1024MB</div>
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
            <el-option v-for="item in selectdata" :key="item" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="chapterName">
          <el-select
            v-model="uploadForm.chapterName"
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
              :value="item.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="name">
          <el-input placeholder="请填写视频名称" v-model="uploadForm.name" clearable></el-input>
        </el-form-item>
        <el-form-item prop="introduction">
          <el-input
            type="textarea"
            :rows="8"
            placeholder="请填写视频简介"
            v-model="uploadForm.introduction"
            resize="none"
            maxlength="500"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm" style="float: right">提交</el-button>
          <el-button @click="resetForm" style="  float: right;margin-right:10px">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getCourseList, getChapterList } from '@/api/course'
import { uploadVideo } from '@/api/upload'
import { saveVideo } from '@/api/video'
export default {
  name: 'UploadOne',
  data() {
    var checkCourseId = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('课程不能为空'))
      } else {
        callback()
      }
    }
    var checkChapterName = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('章节不能为空'))
      } else if (value.length > 40) {
        callback(new Error('章节名称不能大于40位'))
      } else {
        callback()
      }
    }
    var checkName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('视频名称不能为空'))
      } else if (value.length > 40) {
        callback(new Error('视频名称不能大于40位'))
      } else {
        callback()
      }
    }
    return {
      uploadForm: {
        courseId: '',
        chapterName: '',
        name: '',
        introduction: '',
        path: ''
      },
      chapterList: [],
      selectdata: [],
      uploadRules: {
        courseId: [{ validator: checkCourseId, trigger: 'change' }],
        chapterName: [{ validator: checkChapterName, trigger: 'change' }],
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
    uploadFileMethod(fileObj) {
      let fromData = new FormData()
      fromData.set('file', fileObj.file)
      uploadVideo(fromData)
        .then(res => {
          this.uploadForm.path = res.data
          this.$message({
            showClose: true,
            duration: 2500,
            message: '视频上传成功',
            type: 'success'
          })
          this.$refs.upload.clearFiles()
          this.saveVideo()
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 规定上传文件的类型和大小
    beforeVideoUpload(file) {
      // alert(file.type)
      const isMp4 = file.type === 'video/mp4'
      const isLt1024M = file.size / 1024 / 1024 < 1024

      if (!isMp4) {
        this.$message.error('上传视频只能是mp4格式!')
      } else if (!isLt1024M) {
        this.$message.error('上传头像图片大小不能超过1024MB!')
      }
      return isMp4 && isLt1024M
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
    resetForm() {
      this.$refs.uploadForm.resetFields()
    },
    submitForm() {
      this.$refs.uploadForm.validate(valid => {
        if (valid) {
          this.$refs.upload.submit()
        }
      })
    },
    saveVideo() {
      saveVideo(this.uploadForm).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: '内容已保存',
          type: 'success'
        })
        this.$refs.uploadForm.resetFields()
      })
    }
  }
}
</script>

<style scoped>
.upload-one {
  height: 860px;
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
