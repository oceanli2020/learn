<template>
  <div>
    <el-dialog
      title="新建课程"
      :visible.sync="dialogFormVisible"
      width="700px"
      :before-close="handleClose"
      @close="close"
    >
      <el-form :model="createForm" ref="createForm" :rules="createRules">
        <el-form-item label="课程名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="createForm.name" class="input" clearable></el-input>
        </el-form-item>
        <el-form-item label="课程类别" :label-width="formLabelWidth" prop="typeIds">
          <el-cascader
            placeholder="请选择课程类别"
            :options="options"
            :show-all-levels="false"
            class="select"
            v-model="createForm.typeIds"
            clearable
          ></el-cascader>
        </el-form-item>
        <!-- <el-form-item label="课程封面" :label-width="formLabelWidth">
          <div>
            <el-upload
              class="cover-picture"
              action
              :http-request="uploadFileMethod"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
            >
              <img v-if="imageUrl" :src="imageUrl" class="picture" />
              <i v-else class="el-icon-plus cover-picture-icon"></i>
            </el-upload>
          </div>
        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="determine">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getOptions } from '@/api/course'
export default {
  name: 'CreateDialog',
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
    var checkName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('课程名称不能为空'))
      } else if (value.length > 50) {
        callback(new Error('课程名称不能大于50位'))
      } else {
        callback()
      }
    }
    var checkTypeIds = (rule, value, callback) => {
      if (value === null || value.length === 0) {
        callback(new Error('课程类型不能为空'))
      } else {
        callback()
      }
    }
    return {
      formLabelWidth: '80px',
      createForm: {
        name: '',
        typeIds: []
      },
      options: [],
      createRules: {
        name: [{ validator: checkName, trigger: 'blur' }],
        typeIds: [{ validator: checkTypeIds, trigger: 'change' }]
      }
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      getOptions().then(res => {
        this.options = res.data
      })
    },
    close() {
      this.$refs.createForm.resetFields()
    },
    cancel() {
      this.dialogFormVisible = false
      this.$emit('childFn', this.dialogFormVisible)
    },
    determine() {
      this.$refs.createForm.validate(valid => {
        if (valid) {
          this.sup_this.createNew(this.createForm)
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
/* .cover-picture {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  width: 240px;
  height: 150px;
  position: relative;
  overflow: hidden;
}
.cover-picture:hover {
  border-color: #409eff;
}
.cover-picture-icon {
  font-size: 28px;
  color: #8c939d;
  width: 240px;
  height: 150px;
  line-height: 150px;
  text-align: center;
}
.picture {
  width: 240px;
  height: 150px;
  display: block;
} */
</style>
