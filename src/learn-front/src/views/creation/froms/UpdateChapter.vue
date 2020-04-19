<template>
  <div>
    <el-dialog
      title="编辑章节"
      :visible.sync="dialogFormVisible"
      width="700px"
      :before-close="handleClose"
      @open="open"
    >
      <el-form :model="updateForm" ref="updateForm" :rules="rules">
        <el-form-item label="章节名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="updateForm.name" class="input" clearable></el-input>
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
  name: 'UpdateChapter',
  props: {
    dialogFormVisible: {
      type: Boolean,
      required: true
    },
    sup_this: {
      type: Object,
      default: null
    },
    chapter: {
      type: Object,
      default: null
    }
  },
  data() {
    var checkName = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('章节名称不能为空'))
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
        name: ''
      },
      rules: {
        name: [{ validator: checkName, trigger: 'change' }]
      }
    }
  },
  mounted() {},
  methods: {
    open() {
      this.updateForm.id = this.chapter.id
      this.updateForm.name = this.chapter.name
    },
    cancel() {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    determine() {
      this.$refs.updateForm.validate(valid => {
        if (valid) {
          this.sup_this.updateChapter(this.updateForm)
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
</style>
