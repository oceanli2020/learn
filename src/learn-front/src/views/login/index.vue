<template>
  <div class="login">
    <el-form :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">{{ title }}</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text" placeholder="用户名">
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
        >
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="loginForm.validateCode"
          class="identify"
          type="text"
          auto-complete="off"
          placeholder="验证码"
        >
        </el-input>
        <span class="vali"> {{ code }}</span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:100%;">
          <span>登  录</span>
        </el-button>
      </el-form-item>
      <div class="re">
          <el-link @click="retrievePwd">忘记密码</el-link>
      |   <el-link >注册</el-link>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      title: '登录',
      code: '',
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', message: '用户名不能为空' }
        ],
        password: [{ required: true, trigger: 'blur', message: '密码不能为空' }]
      }
    }
  },
  mounted () {
    this.createCode()
  },
  methods: {

    retrievePwd () {
      this.$router.push('retrieve')
    },
    createCode () {
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

<style rel="stylesheet/scss" lang="scss">
.login {
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
.re{
 margin-left: 284px;
}
</style>
