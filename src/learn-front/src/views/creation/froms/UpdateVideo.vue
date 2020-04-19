<template>
  <div>
    <el-dialog
      title="编辑视频"
      :visible.sync="dialogFormVisible"
      width="700px"
      :before-close="handleClose"
      @open="open"
    >
      <el-form :model="updateForm" ref="updateForm" :rules="rules">
        <el-form-item label="视频名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="updateForm.name" class="input" placeholder="请填写内容" clearable></el-input>
        </el-form-item>
        <el-form-item prop="chapterName" label="选择章节" :label-width="formLabelWidth">
          <el-select
            v-model="updateForm.chapterName"
            placeholder="请选择该课程的已创建章节或新建章节"
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
        <el-form-item prop="introduction" label="视频简介" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            :rows="8"
            placeholder="请填写视频简介"
            v-model="updateForm.introduction"
            resize="none"
            maxlength="500"
            class="textarea"
            show-word-limit
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="determine">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'UpdateVideo',
  props: {
    dialogFormVisible: {
      type: Boolean,
      required: true
    },
    sup_this: {
      type: Object,
      default: null
    },
    video: {
      type: Object,
      default: null
    },
    chapterList: {
      type: Object,
      default: null
    },
    chapterName: {
      type: String,
      default: ''
    },
    courseId: {
      type: String,
      default: ''
    }
  },
  data() {
    var checkName = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('视频名称不能为空'))
      } else if (value.length > 40) {
        callback(new Error('视频名称不能大于40位'))
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
    return {
      formLabelWidth: '80px',
      updateForm: {
        id: '',
        name: '',
        courseId: '',
        chapterName: '',
        introduction: ''
      },
      rules: {
        name: [{ validator: checkName, trigger: 'change' }],
        chapterName: [{ validator: checkChapterName, trigger: 'change' }]
      }
    }
  },
  mounted() {},
  methods: {
    open() {
      this.updateForm.id = this.video.id
      this.updateForm.name = this.video.name
      this.updateForm.courseId = this.courseId
      this.updateForm.chapterName = this.chapterName
      this.updateForm.introduction = this.video.introduction
    },
    cancel() {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    determine() {
      this.$refs.updateForm.validate(valid => {
        if (valid) {
          this.sup_this.updateVideo(this.updateForm)
          this.dialogFormVisible = false
          this.$emit('childFn', this.dialogFormVisible)
        }
      })
    },
    handleClose(done) {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    }
  }
}
</script>

<style scoped>
.input {
  width: 500px;
}
.select {
  width: 500px;
}
.autocomplete {
  width: 500px;
}
.textarea {
  width: 500px;
}
</style>
