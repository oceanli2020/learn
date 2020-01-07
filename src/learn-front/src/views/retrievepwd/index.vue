<template>
  <div class="block">
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      class="demo-ruleForm"
      ref="ruleForm"
    >
      <h3 class="title">{{ title }}</h3>

      <div>
        <el-form-item prop="pass">
        <el-input
          type="password"
          v-model="ruleForm.pass"
          autocomplete="off"
          placeholder="用户名"
        ></el-input>
      </el-form-item>
      <el-date-picker v-model="value" type="date" placeholder="输入生日" style="margin-bottom:22px;width:300px">
      </el-date-picker>
</div>
<div v-if="show">
      <el-form-item prop="pass">
        <el-input
          type="password"
          v-model="ruleForm.pass"
          autocomplete="off"
          placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item prop="checkPass">
        <el-input
          type="password"
          v-model="ruleForm.checkPass"
          autocomplete="off"
          placeholder="确认密码"
        ></el-input>
      </el-form-item>
</div>
      <el-form-item>

        <el-button type="primary" @click="showCont"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      title: '找回密码',
      value: '',
      show: true,
      ruleForm: {
        pass: '',
        checkPass: ''
      },
      rules: {
        pass: [{ validator: validatePass, trigger: 'blur' }],
        checkPass: [{ validator: validatePass2, trigger: 'blur' }]
      }
    }
  },

  methods: {
    showCont () {
      this.show = !this.show
    },
    submitForm (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
.block {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 600px;
  background-repeat: no-repeat;
  background-position: top center;
}
.title {
  margin: 0px auto 25px;
  font-size: 20px;
  text-align: center;
  color: #333;
}
.demo-ruleForm {
  position: relative;
  border-radius: 6px;
  background: #ffffff;
  width: 300px;
  padding: 35px 35px 15px;
}
.el-input {
  height: 42px;
  border-radius: 2px;
}
</style>
