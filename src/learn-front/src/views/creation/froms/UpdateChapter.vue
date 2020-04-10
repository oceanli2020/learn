<template>
  <div>
    <el-dialog title="编辑章节" :visible.sync="dialogFormVisible" width="700px"  :before-close="handleClose" @close="close">
      <el-form :model="updateForm" ref="updateForm">
         <el-form-item label="章节名称" :label-width="formLabelWidth" prop="name" >
          <el-input v-model="updateForm.name" class="input" clearable ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="determine">确 定</el-button>
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
    }
  },
  data() {
    return {
      formLabelWidth: '80px',
      updateForm: {
        name: ''
      }
    }
  },
  mounted() {},
  methods: {
    close() {

    },
    cancel() {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    determine() {
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
.input {
  width: 500px;
}
</style>
