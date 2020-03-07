<template>
  <div class="register">
    <el-form
      :model="registerForm"
      :rules="registerRules"
      class="register-form"
      ref="registerForm"
      label-position="right"
      label-width="80px"
      style="margin-top:19px"
    >
      <h3 class="title">{{ title }}</h3>
      <el-form-item prop="username" label="用户名">
        <el-input placeholder="2~10位" type="text" v-model="registerForm.username" maxlength="10"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="密码">
        <el-input
          placeholder="8~16位,必须包含字母和数字"
          type="password"
          v-model="registerForm.password"
          maxlength="16"
        ></el-input>
      </el-form-item>
      <el-form-item prop="checkPass" label="确认密码">
        <el-input placeholder="请再次输入密码" type="password" v-model="registerForm.checkPass"></el-input>
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input placeholder="请输入邮箱" type="text" v-model="registerForm.email"></el-input>
      </el-form-item>
      <el-form-item prop="phone_number" label="手机号码">
        <el-input placeholder="请输入手机号码" type="text" v-model="registerForm.phone_number"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>user

<script>
import { register } from '@/api/user'
import { Notification } from 'element-ui'
export default {
  name: 'Main',
  data() {
    var checkName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('用户名不能为空'))
      } else if (value.length < 2 || value.length > 10) {
        callback(new Error('用户名应为2~10位'))
      } else {
        callback()
      }
    }
    var checkPassWord1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.length < 8 || value.length > 16) {
        callback(new Error('密码应为8~16位'))
      } else if (!this.checkLetterNumber(value)) {
        callback(new Error('密码必须包含字母和数字'))
      } else if (this.registerForm.checkPass !== '') {
        this.$refs.registerForm.validateField('checkPass')
        callback() // wo C N M
      } else {
        callback()
      }
    }

    var checkPassWord2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    var checkEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('邮箱不能为空'))
      } else {
        var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
        if (!regEmail.test(value)) {
          callback(new Error('邮箱格式不正确'))
        } else {
          callback()
        }
      }
    }
    var checkPhoneNumber = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('手机号码不能为空'))
      } else {
        var regPhoneNumber = /^1[3456789]\d{9}$/
        if (!regPhoneNumber.test(value)) {
          callback(new Error('手机号码格式不正确'))
        } else {
          callback()
        }
      }
    }
    return {
      title: '注册',
      registerForm: {
        username: '',
        password: '',
        checkPass: '',
        email: '',
        phone_number: ''
      },
      registerRules: {
        username: [{ validator: checkName, trigger: 'blur' }],
        password: [{ validator: checkPassWord1, trigger: 'blur' }],
        checkPass: [{ validator: checkPassWord2, trigger: 'blur' }],
        email: [{ validator: checkEmail, trigger: 'blur' }],
        phone_number: [{ validator: checkPhoneNumber, trigger: 'blur' }]
      }
    }
  },
  methods: {
    checkLetterNumber(value) {
      let arrVerify = [
        { regName: 'Number', regValue: /^.*[0-9]+.*/ },
        { regName: 'Case', regValue: /^.*[A-Za-z]+.*/ }
        // { regName: 'LowerCase', regValue: /^.*[a-z]+.*/ },
        // { regName: 'UpperCase', regValue: /^.*[A-Z]+.*/ }
        // { regName: 'SpecialCharacters', regValue: /^.*[^a-zA-Z0-9]+.*/ }
      ]
      let regNum = 0
      for (let iReg = 0; iReg < arrVerify.length; iReg++) {
        if (arrVerify[iReg].regValue.test(value)) {
          regNum = regNum + 1
        }
      }
      if (regNum === 2) {
        return true
      } else {
        return false
      }
    },
    submitForm() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          register(
            this.registerForm.username,
            this.registerForm.password,
            this.registerForm.email,
            this.registerForm.phone_number
          )
            .then(res => {
              if (res.data.length === 1 && res.data[0].code === 200) {
                alert(res.data[0].data)
                this.$router.push('/login')
              } else {
                var f = function(a) {
                  setTimeout(() => {
                    Notification.error(res.data[a].message)
                  }, 0)
                }
                for (var i = 0; i < res.data.length; i++) {
                  f(i)
                }
              }
            })
            .catch(err => {
              console.log(err)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm() {
      this.$refs.registerForm.resetFields()
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 600px;
  background-repeat: no-repeat;
  background-position: top center;
}
.title {
  margin: 0px auto 25px;
  font-size: 24px;
  text-align: center;
  color: #333;
}
.register-form {
  position: relative;
  border-radius: 6px;
  background: #ffffff;
  width: 450px;
  padding: 35px 35px 15px;
}
.el-input {
  height: 42px;
  border-radius: 2px;
  width: 310px;
}
</style>
