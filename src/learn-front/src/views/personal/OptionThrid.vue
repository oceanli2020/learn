<template>
  <div class="option_thrid">
    <div class="info">
      <span class="title">个人信息</span>
      <div style="margin-top:40px">
        <el-collapse accordion @change="changeEvents(activeNames)" v-model="activeNames">
          <el-collapse-item name="1">
            <template slot="title">
              <span class="text">昵称&#12288;&#12288;</span>
              <span
                class="content"
                style="margin-left:70px"
                v-if="index_user_name"
              >{{ user.user_name }}</span>
            </template>
            <div>
              <el-form
                :model="user_input"
                :rules="rules"
                ref="user_nameForm"
                label-width="110px"
                class="user_nameForm"
              >
                <el-form-item label="新昵称" prop="user_name">
                  <el-input type="text" v-model="user_input.user_name" class="user_name_input"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('user_nameForm')" size="medium">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-collapse-item>
          <el-collapse-item name="2">
            <template slot="title">
              <span class="text">头像&#12288;&#12288;</span>
              <div class="content" style="margin-left:70px" v-if="index_profile_photo">
                <el-avatar shape="square" size="large" :src="squareUrl" class="l-avatar"></el-avatar>
              </div>
            </template>
            <!-- :before-upload="beforeAvatarUpload" -->
            <div>
              <el-upload
                class="avatar-uploader"
                action
                :http-request="uploadFileMethod"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"

              >
                <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </div>
          </el-collapse-item>
          <el-collapse-item name="3">
            <template slot="title">
              <span class="text">真实姓名</span>
              <span
                class="content"
                style="margin-left:70px"
                v-if="index_real_name"
              >{{ user.real_name }}</span>
            </template>
            <div>
              <el-form
                :model="user_input"
                :rules="rules"
                ref="real_nameForm"
                label-width="110px"
                class="real_nameForm"
              >
                <el-form-item label="更改真实姓名" prop="real_name">
                  <el-input type="text" v-model="user_input.real_name" class="real_name_input"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('real_nameForm')" size="medium">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-collapse-item>
          <el-collapse-item name="4">
            <template slot="title">
              <span class="text">邮箱&#12288;&#12288;</span>
              <span class="content" style="margin-left:70px" v-if="index_email">{{ user.email }}</span>
            </template>
            <div>
              <el-form
                :model="user_input"
                :rules="rules"
                ref="emailForm"
                label-width="110px"
                class="emailForm"
              >
                <el-form-item label="更改邮箱" prop="email">
                  <el-input type="text" v-model="user_input.email" class="email_input"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('emailForm')" size="medium">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-collapse-item>
          <el-collapse-item name="5">
            <template slot="title">
              <span class="text">手机号码</span>
              <span
                class="content"
                style="margin-left:70px"
                v-if="index_phone_number"
              >{{ user.phone_number }}</span>
            </template>
            <div>
              <el-form
                :model="user_input"
                :rules="rules"
                ref="phone_numberForm"
                label-width="110px"
                class="phone_numberForm"
              >
                <el-form-item label="更改手机号码" prop="phone_number">
                  <el-input
                    type="text"
                    v-model="user_input.phone_number"
                    class="phone_number_input"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('phone_numberForm')" size="medium">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-collapse-item>
          <el-collapse-item name="6">
            <template slot="title">
              <span class="text">密码&#12288;&#12288;</span>
              <span
                class="content"
                style="margin-left:70px"
                v-if="index_password"
              >{{ user.password }}</span>
            </template>
            <div>
              <el-form
                :model="user_input"
                :rules="rules"
                ref="passwordForm"
                label-width="110px"
                class="passwordForm"
              >
                <el-form-item label="当前密码" prop="old_password">
                  <el-input
                    type="password"
                    v-model="user_input.old_password"
                    class="password_input"
                  ></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="new_password">
                  <el-input
                    type="password"
                    v-model="user_input.new_password"
                    class="password_input"
                  ></el-input>
                </el-form-item>
                <el-form-item label="确认新密码" prop="check_password">
                  <el-input
                    type="password"
                    v-model="user_input.check_password"
                    class="password_input"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('passwordForm')" size="medium">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>
  </div>
</template>

<script>
import { update, checkPass, uploadAvatar, getImg } from '@/api/user'
export default {
  name: 'OptionThrid',

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
    var checkRealName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('真实姓名不能为空'))
      } else if (!/^[\u4e00-\u9fa5]{0,}$/.test(value)) {
        callback(new Error('格式错误，请填写中文'))
      } else {
        callback()
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
    var checkPassWord1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.length < 8 || value.length > 16) {
        callback(new Error('密码应为8~16位'))
      } else if (!this.checkLetterNumber(value)) {
        callback(new Error('密码必须包含字母和数字'))
      } else if (this.user_input.check_password !== '') {
        this.$refs.passwordForm.validateField('check_password')
        callback() // wo C N M
      } else {
        callback()
      }
    }

    var checkPassWord2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.user_input.new_password) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    return {
      user: {
        user_name: '',
        real_name: '',
        profile_photo: '',
        email: '',
        phone_number: '',
        password: '******'
      },
      user_input: {
        user_name: null,
        real_name: null,
        profile_photo: null,
        email: null,
        phone_number: null,
        old_password: '',
        new_password: null,
        check_password: ''
      },
      lastActiveName: '',
      activeNames: '',
      rules: {
        user_name: [{ validator: checkName, trigger: 'blur' }],
        real_name: [{ validator: checkRealName, trigger: 'blur' }],
        phone_number: [{ validator: checkPhoneNumber, trigger: 'blur' }],
        email: [{ validator: checkEmail, trigger: 'blur' }],
        new_password: [{ validator: checkPassWord1, trigger: 'blur' }],
        check_password: [{ validator: checkPassWord2, trigger: 'blur' }]
      },
      index_user_name: true,
      index_profile_photo: true,
      index_real_name: true,
      index_email: true,
      index_phone_number: true,
      index_password: true,
      imageUrl: '',
      squareUrl:
        'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    uploadFileMethod(fileObj) {
      this.imageUrl = URL.createObjectURL(fileObj.file)
      let fromData = new FormData()
      fromData.set('file', fileObj.file)
      uploadAvatar(fromData)
        .then(res => {
          this.$message({
            showClose: true,
            duration: 2500,
            message: '图片上传成功',
            type: 'success'
          })
          this.squareUrl = this.imageUrl
          this.$store.commit('SET_PROFILE_PHOTO', res.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 规定上传文件的类型和大小
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    getUserInfo() {
      this.$store
        .dispatch('getInfo')
        .then(res => {
          const data = res.data
          this.user.user_name = data.userName
          this.user.real_name = data.realName
          this.user.profile_photo = data.profilePhoto
          this.user.email = data.email
          this.user.phone_number = data.phoneNumber
          if (data.profilePhoto !== '' && data.profilePhoto !== null) {
            this.user.profile_photo = data.profilePhoto
            getImg(this.user.profile_photo).then(res => {
              this.squareUrl = `data: image/jpeg;base64,${btoa(
                new Uint8Array(res).reduce(
                  (data, byte) => data + String.fromCharCode(byte),
                  ''
                )
              )}`
            })
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    checkPassword(passwordInput) {
      return new Promise((resolve, reject) => {
        checkPass(passwordInput)
          .then(res => {
            if (res.code === -1008) {
              resolve(false)
            }
            resolve(true)
          })
          .catch(err => {
            reject(err)
          })
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate(async valid => {
        if (valid) {
          if (formName === 'user_nameForm') {
            update(
              this.user_input.user_name,
              this.user_input.profile_photo,
              this.user_input.real_name,
              this.user_input.email,
              this.user_input.phone_number,
              this.user_input.new_password
            )
              .then(res => {
                if (res.code === -1005) {
                  this.$message({
                    showClose: true,
                    duration: 2500,
                    message: res.message,
                    type: 'error'
                  })
                } else {
                  this.$message({
                    showClose: true,
                    duration: 2500,
                    message: '修改成功',
                    type: 'success'
                  })
                  this.user.user_name = this.user_input.user_name
                  this.updateStore(this.user.user_name)
                }
              })
              .catch(error => {
                console.log(error)
              })
          }
          if (formName === 'real_nameForm') {
            update(
              this.user_input.user_name,
              this.user_input.profile_photo,
              this.user_input.real_name,
              this.user_input.email,
              this.user_input.phone_number,
              this.user_input.new_password
            )
              .then(() => {
                this.$message({
                  showClose: true,
                  duration: 2500,
                  message: '修改成功',
                  type: 'success'
                })
                this.user.real_name = this.user_input.real_name
              })
              .catch(error => {
                console.log(error)
              })
          }
          if (formName === 'phone_numberForm') {
            update(
              this.user_input.user_name,
              this.user_input.profile_photo,
              this.user_input.real_name,
              this.user_input.email,
              this.user_input.phone_number,
              this.user_input.new_password
            )
              .then(res => {
                if (res.code === -1007) {
                  this.$message({
                    showClose: true,
                    duration: 2500,
                    message: res.message,
                    type: 'error'
                  })
                } else {
                  this.$message({
                    showClose: true,
                    duration: 2500,
                    message: '修改成功',
                    type: 'success'
                  })
                  this.user.phone_number = this.user_input.phone_number
                }
              })
              .catch(error => {
                console.log(error)
              })
          }
          if (formName === 'emailForm') {
            update(
              this.user_input.user_name,
              this.user_input.profile_photo,
              this.user_input.real_name,
              this.user_input.email,
              this.user_input.phone_number,
              this.user_input.new_password
            )
              .then(res => {
                if (res.code === -1006) {
                  this.$message({
                    showClose: true,
                    duration: 2500,
                    message: res.message,
                    type: 'error'
                  })
                } else {
                  this.$message({
                    showClose: true,
                    duration: 2500,
                    message: '修改成功',
                    type: 'success'
                  })
                  this.user.email = this.user_input.email
                }
              })
              .catch(error => {
                console.log(error)
              })
          }
          if (formName === 'passwordForm') {
            try {
              const res = await this.checkPassword(this.user_input.old_password)
              if (res === false) {
                this.$message({
                  showClose: true,
                  duration: 2500,
                  message: '密码不正确',
                  type: 'error'
                })
                this.$refs.passwordForm.resetFields()
                return
              }
            } catch (error) {
              console.log(error)
              this.$message({
                showClose: true,
                duration: 2500,
                message: '提交时出错',
                type: 'error'
              })
              return
            }
            update(
              this.user_input.user_name,
              this.user_input.profile_photo,
              this.user_input.real_name,
              this.user_input.email,
              this.user_input.phone_number,
              this.user_input.new_password
            )
              .then(() => {
                this.$message({
                  showClose: true,
                  duration: 2500,
                  message: '修改成功',
                  type: 'success'
                })
                this.user.password = this.user_input.new_password
              })
              .catch(error => {
                console.log(error)
              })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    correspond(name) {
      if (name === '1') {
        this.index_user_name = true
        this.$refs.user_nameForm.resetFields()
      }
      if (name === '2') {
        this.index_profile_photo = true
        this.imageUrl = ''
      }
      if (name === '3') {
        this.index_real_name = true
        this.$refs.real_nameForm.resetFields()
      }
      if (name === '4') {
        this.index_email = true
        this.$refs.emailForm.resetFields()
      }
      if (name === '5') {
        this.index_phone_number = true
        this.$refs.phone_numberForm.resetFields()
      }

      if (name === '6') {
        this.index_password = true
        this.$refs.passwordForm.resetFields()
        this.user.password = '******'
      }
    },
    changeEvents(activeName) {
      this.correspond(this.lastActiveName)
      this.lastActiveName = activeName
      if (activeName !== '') {
        if (activeName === '1') {
          this.index_user_name = false
        }
        if (activeName === '2') {
          this.index_profile_photo = false
        }
        if (activeName === '3') {
          this.index_real_name = false
        }
        if (activeName === '4') {
          this.index_email = false
        }
        if (activeName === '5') {
          this.index_phone_number = false
        }
        if (activeName === '6') {
          this.index_password = false
        }
      }
    },
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

    updateStore(username) {
      this.$store.commit('SET_USER_NAME', username)
    }
  }
}
</script>

<style scoped>
.info {
  width: 600px;
  height: 400px;
  /* background-color: yellow; */
  position: absolute;
  left: 700px;
  top: 120px;
}
.title {
  color: #303030;
  font-size: 25px;
}
.text {
  color: #484848;
  font-size: 18px;
}
.content {
  color: #585858;
  font-size: 16px;
}
.l-avatar {
  /* 相对定位 */
  position: relative;
  /* left: 30px; */
  top: 9px;
}
.user_nameForm {
  margin-top: 25px;
}
.user_name_input {
  width: 300px;
}
.real_nameForm {
  margin-top: 25px;
}
.real_name_input {
  width: 300px;
}
.phone_numberForm {
  margin-top: 25px;
}
.phone_number_input {
  width: 300px;
}
.emailForm {
  margin-top: 25px;
}
.email_input {
  width: 300px;
}
.passwordForm {
  margin-top: 25px;
}
.password_input {
  width: 300px;
}
.avatar-uploader {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  width: 180px;
  position: relative;
  overflow: hidden;
}
.avatar-uploader:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 200px;
  line-height: 200px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 200px;
  display: block;
}
</style>
