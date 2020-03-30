<template>
  <div class="login">
    <el-form :model="loginForm" :rules="loginRules" class="login-form" ref="loginForm">
      <h3 class="title">{{ title }}</h3>
      <el-form-item prop="username">
        <el-input placeholder="用户名" type="text" v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input auto-complete="off" placeholder="密码" type="password" v-model="loginForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          auto-complete="off"
          class="identify"
          placeholder="验证码"
          type="text"
          v-model="loginForm.validateCode"
        ></el-input>
        <span class="vali">{{ code }}</span>
      </el-form-item>
      <el-form-item>
        <el-button @click="login" style="width:100%;" type="primary">
          <span>登 录</span>
        </el-button>
      </el-form-item>
      <div class="re">
        <el-link @click="toRegister">注册</el-link>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      title: '登录',
      code: '',
      loginForm: {
        username: '',
        password: '',
        validateCode: ''
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', message: '用户名不能为空' }
        ],
        password: [{ required: true, trigger: 'blur', message: '密码不能为空' }]
      }
    }
  },
  mounted() {
    this.createCode()
  },
  methods: {
    validate() {
      // eslint-disable-next-line eqeqeq
      if (this.loginForm.validateCode == this.code) {
        return true
      }
      this.$notify.error({
        title: '失败',
        message: '验证码错误'
      })
      this.loginForm.validateCode = ''
      this.code = ''
      this.createCode()
      return false
    },
    login() {
      var b = this.validate()
      if (b === false) return
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$store
            .dispatch('login', this.loginForm)
            .then(res => {
              if (res.code === 200) {
                this.$router.push('/')
              } else {
                this.$notify.error({
                  title: '失败',
                  message: res.message
                })
                this.loginForm.validateCode = ''
                this.loginForm.username = ''
                this.loginForm.password = ''
                this.code = ''
                this.createCode()
              }
            })
            .catch(error => {
              console.log(error)
            })
        } else {
          console.log('error submit!!')
        }
      })
    },
    toRegister() {
      this.$router.push('/register')
    },
    createCode() {
      var codeLength = 4 // 验证码的长度
      // eslint-disable-next-line no-array-constructor
      var random = new Array(
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'J',
        'K',
        'L',
        'M',
        'N',
        'O',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z'
      ) // 随机数
      for (var i = 0; i < codeLength; i++) {
        // 循环操作
        var index = Math.floor(Math.random() * 36) // 取得随机数的索引（0~35）
        this.code += random[index] // 根据索引取得随机数加到code上
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 550px;
  background-repeat: no-repeat;
  background-position: top center;
}
.title {
  margin: 0px auto 25px;
  font-size: 24px;
  text-align: center;
  color: #333;
}
.login-form {
  position: relative;
  border-radius: 6px;
  background: #ffffff;
  width: 382px;
  padding: 35px 35px 15px;
}
.el-input {
  height: 42px;
  border-radius: 2px;
}
.identify {
  width: auto;
  margin-right: 70px;
}
.vali {
  font-size: 20px;
}
.re {
  margin-left: 354px;
}
</style>
