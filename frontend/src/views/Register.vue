<template>
  <div class="box">
    <!-- <img src="../assets/back-grond.jpg" alt=""> -->
    <img src="" alt="">
    <div class="login-box">
      <div class="login-title"><h1 class="login-title">注册</h1></div>
      <div class="form">
        <div class="line">
          <i class="el-icon-phone"></i>
          <el-input v-model="phonenum" class="item" type="text" placeholder="请输入你的电话" />
        </div>
        <div class="line">
          <i class="el-icon-key"></i>
          <el-input v-model="password" class="item" :type="passwordType" placeholder="请输入你的密码" />
          <!-- <el-button icon="el-icon-view" circle @click="checkStatus" class="checkBtn" /> -->
        </div>
        <div class="line">
          <i class="el-icon-school"></i>
          <el-select class="select-box" v-model="schoolId" filterable :filter-method="dataFilter" placeholder="请选择你的学校">
            <el-option
              v-for="item in options"
              :key="item.schoolId"
              :label="item.schoolName"
              :value="item.schoolId">
            </el-option>
          </el-select>
        </div>
        <!-- <div>
          <i class="el-icon-picture-outline"></i>
          <el-input v-model="identity" class="classifyitem" type="text" placeholder="请输入右中的验证码." />
          <img src="../assets/classify.jpg" alt="" >
        </div> -->
        <div class="line-x">

          <div class="form_font">
            <i class="el-icon-picture-outline"></i>
            <el-input v-model="identity" class="classifyitem" type="text" placeholder="请输入右中的验证码 " />
          </div>
          <!-- <img src="../assets/classify.jpg"   /> -->
          <div @click="refreshCode"><SIdentify :identifyCode="identifyCode"  class="yanzhengma-style"></SIdentify></div>

        </div>
        <div>
          <el-button type="primary" class="button-box" @click="regist">注册</el-button>
          <div
          style="
          margin: 5px;"
          class="router-to"
          >
            <router-link to="/Login" style="text-decoration: none ;color:blue">已有账号，现在去登录</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SIdentify from './Identify.vue'

export default {
  components: {
    SIdentify
  },
  data () {
    return {
      identifyCodes: '1234567890',
      identifyCode: '',
      url: '../assets/classify.jpg',
      phonenum: '',
      password: '',
      passwordType: 'password',
      schoolId: '',
      identity: '',
      optionsCopy: [],
      options: [{
        schoolId: 1,
        schoolName: '合工大'
      }, {
        schoolId: 2,
        schoolName: '安徽大学'
      }, {
        schoolId: 3,
        schoolName: '中科大'
      }]
    }
  },
  mounted () {
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
    this.$axios.get('school/school')
      .then(resp => {
        this.options = resp.data.School
        this.optionsCopy = resp.data.School
      })
  },
  methods: {
    dataFilter (val) {
      this.schoolId = val
      if (val) {
        this.options = this.optionsCopy.filter((item) => {
          if (!!~item.schoolName.indexOf(val) || !!~item.schoolName.toUpperCase().indexOf(val.toUpperCase())) {
            return true
          }
        })
      } else {
        this.options = this.optionsCopy
      }
    },
    standardphonenum () {
      const phoneCodeVerification = /^[1][3,4,5,7,8][0-9]{9}$/
      if (!phoneCodeVerification.test(this.phonenum)) {
        this.$message.error('电话号码格式错误!')
        return false
      } else {
        return true
      }
    },
    regist () {
      if (this.phonenum !== '' && this.password !== '' && this.schoolId !== '' && this.identity !== '') {
        if (this.standardphonenum()) {
          if (this.identity === this.identifyCode) {
          // console.log('success')
            this.$axios.post(`testPersonnelLogin/register?telephoneNumber=${this.phonenum}&password=${this.password}&schoolId=${this.schoolId}`)
              .then(resp => {
                console.log(resp)
                this.$message({
                  message: '注册成功!',
                  type: 'success'
                })
                this.$router.push('/login')
              }).catch(resp => {
                this.$message.error('注册失败，已存在该手机号的账号')
              })
          } else {
            this.$message({
              message: '验证码有误，请重新填写!',
              type: 'warning'
            })
            this.refreshCode()
          }
        } else {
        }
      } else {
        this.$message({
          message: '请输入完整的信息!',
          type: 'warning'
        })
      }
    },
    randomNum (min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    refreshCode () {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode (o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
        ]
      }
      console.log(this.identifyCode)
    }
  }
}
</script>

<style  scoped>
h1{
  font-size: 25px;
}
.line-x{
  display: flex;
}
.yanzhengma-style{
  /* width: 90px;
  height: 40px;
  border-radius: 10px; */
  margin-top: 5px;
}
.form_font {
  /* line-height: 48px;
  margin-left: 67px;
  font-size: 15px; */
  margin-left:85px ;
}
.router-to{
  color: white;
  margin-top: 15px;
  font-size: 13px;
  height: 40px;
  text-align: center;
}
i{
  font-size: 20px;
  /* margin-left:30px ; */
  /* margin-top: 15px; */
}
.box{
  width: 100vw;
  height: 100vh;
  background: url("../assets/back1.jpg") no-repeat;
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  position: flex;
}

.login-box{
  border-radius: 10px;
  width: 500px;
  text-align: center;
  margin: 0 auto;
  margin-top: 15%;
  background: #cec8c880;
  padding: 10px 50px;
  margin: 200px auto auto;
}

.login-box h1{
  color: rgba(32, 30, 30, 0.993);
  margin-top: 0;
}
.item{
  width: 280px; /* 设置合适的宽度 */
  padding: 5px 10px; /* 为了placeholder的内容不是顶格显示，增加内部边界 */
  margin-left: 10px;
}
.classifyitem{
  width: 180px;
  padding: 5px 10px; /* 为了placeholder的内容不是顶格显示，增加内部边界 */
  margin-left: 10px;
}
input::-webkit-input-placeholder{
  color:white;
}
.select-box{
  width: 280px;
  /* background: #ffffff00; */
  /* background-color: violet; */
  padding: 5px 10px;
  border-radius: 10px;
  margin-left: 10px;
}
.button-box{
  width: 150px; /* 设置合适的按钮的长和宽 */
  margin-top: 18px; /* 设置合适的上部外框的宽度，增加与上面的password框的距离 */
  font-size: 18px;
  border-radius: 10px;
  background-image: linear-gradient(to right, #d8e068 0%, #75ee8f 100%); /* 为按钮增加渐变颜色 */
}
.regist{
  color: aqua;
}
</style>
