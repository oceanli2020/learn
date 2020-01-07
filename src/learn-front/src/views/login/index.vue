<template>
  <div class="login">
    <el-form :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">{{ title }}</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text"  placeholder="用户名">
        </el-input>
      </el-form-item>

       <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码">
        </el-input>
      </el-form-item>
       <el-form-item>
        <el-input
          v-model="loginForm.validateCode"
          prop="validateCode"
          class="identify"
          type="text"
          auto-complete="off"
          placeholder="验证码"
        >
        </el-input>

      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin"
        >
          <span >登 录</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      title: '登录',
      identify: '',
      md5Pwd: '',
      loginForm: {
        username: '',
        password: '',
        // validateCode: '',
        rememberMe: false
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', message: '用户名不能为空' }
        ],
        password: [
          { required: true, trigger: 'blur', message: '密码不能为空' }
        ],
        validateCode: [
          { required: true, trigger: 'blur', message: '验证码不能为空' }
        ]
      },
      loading: false,
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created () {
    this.requestConfig()
    this.getCookie()
  },
  mounted () {
    this.getIdentify()
  },
  methods: {

    getIdentify () {
      this.identify = new Date().getTime()
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
  margin: 0px auto 30px;
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
  .el-input {
    height: 42px;
    input {
      height: 42px;
      border-radius: 2px;
    }
    &.identify {
      width: auto;
      margin-right: 132px;
    }
  }
  .identify + img {
    position: absolute;
    top: 0;
    right: 0;
    width: 122px;
    height: 42px;
    border: 1px solid #eee;
    cursor: pointer;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
</style>
