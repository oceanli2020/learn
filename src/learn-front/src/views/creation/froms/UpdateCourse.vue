<template>
  <div>
    <el-dialog
      title="编辑课程"
      :visible.sync="dialogFormVisible"
      width="700px"
      :before-close="handleClose"
      @open="open"
    >
      <el-form :model="updateForm" ref="updateForm">
        <el-form-item prop="introduction" label="课程介绍" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            :rows="15"
            placeholder="请填写课程介绍"
            v-model="updateForm.introduction"
            resize="none"
            maxlength="1000"
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
  name: 'UpdateCourse',
  props: {
    dialogFormVisible: {
      type: Boolean,
      required: true
    },
    sup_this: {
      type: Object,
      default: null
    },
    course: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      formLabelWidth: '80px',
      updateForm: {
        id: '',
        introduction: ''
      }
    }
  },
  mounted() {},
  methods: {
    open() {
      this.updateForm.id = this.course.id
      this.updateForm.introduction =
        this.course.introduction != null
          ? this.course.introduction
          : this.updateForm.introduction
    },
    cancel() {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    determine() {
      this.sup_this.updateCourse(this.updateForm)
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    handleClose(done) {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    }
  }
}
</script>

<style scoped>
.textarea {
  width: 500px;
}
</style>
