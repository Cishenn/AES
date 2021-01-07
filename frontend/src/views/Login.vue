<template>
  <div class="box">
    <!-- <img src="../assets/back-grond.jpg" alt=""> -->
    <img src="" alt="">
    <div class="login-box">
      <div ><h1 class="login-title">登录</h1></div>
      <div class="form">
        <div class="line">
          <i class="el-icon-school"></i>
          <el-select class="select-box" clearable @change="changeselect" v-model="value" placeholder="请选择你的身份">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </div>
        <div class="line">
          <i class="el-icon-user"></i>
          <el-input v-model="username" class="item" type="text" placeholder="请输入你的用户名" />
        </div>
        <div class="line">
          <i class="el-icon-key"></i>
          <el-input v-model="password" class="item" type="text" placeholder="请输入你的密码" show-password />
        </div>
        <div >
          <el-button type="primary" class="button-box" @click="login">登录</el-button>
          <div v-if="this.istrue"
          style="
          margin: 5px;"
          class="router-to"
          >
            <router-link to="/Register" style="text-decoration: none ;color:blue">没有账号，现在去注册</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      teacherId: '1',
      username: '',
      password: '',
      istrue: false,
      value: '',
      options: [{
        value: 1,
        label: '教师'
      }, {
        value: 2,
        label: '学校'
      }, {
        value: 3,
        label: '招办'
      }]
    }
  },
  methods: {
    changeselect (selectValue) {
      // console.log(selectValue)
      if (selectValue === 1) { this.istrue = true } else { this.istrue = false }
      // console.log(this.istrue)
    },
    teacherlogin () {
      if (this.username === '' || this.password === '') {
        alert('请输入用户名或密码。')
      } else {
        this.$axios.get('testPersonnelLogin/login', {
          params: {
            telephoneNumber: this.username,
            password: this.password
          }
        }).then(resp => {
          console.log(resp.data)
          if (resp.data !== -1) {
            this.$message({
              message: '登录成功',
              type: 'success'
            })
            this.$store.commit('setteacherId', resp.data)
            this.$router.push('/teacher')
          }
        }).catch(resp => {
          console.log(resp)
        })
      // this.$router.push('/teacher')
      }
    },
    schoollogin () {
      if (this.username === '' || this.password === '') {
        alert('请输入用户名或密码。')
      } else {
        this.$axios.get('schoolAdminLogin/login', {
          params: {
            account: this.username,
            password: this.password
          }
        }).then(resp => {
          if (resp.data !== -1) {
            this.$message({
              message: '登陆成功',
              type: 'success'
            })
            // console.log(resp.data)
            this.$store.commit('setschoolId', resp.data)
            this.$router.push('/school')
          } else {
            this.$message.error('用户名或密码错误')
          }
        })
      }
    },
    edulogin () {
      if (this.username === '' || this.password === '') {
        alert('请输入用户名或密码。')
      } else {
        this.$axios.get('enrollmentDepartmentLogin/login', {
          params: {
            account: this.username,
            password: this.password
          }
        }).then(resp => {
          if (resp.data !== -1) {
            this.$message({
              message: '登陆成功',
              type: 'success'
            })
            this.$store.commit('setaddmissionsId', resp.data)
            this.$router.push('/admissions')
          } else {
            this.$message.error('用户名或密码错误')
          }
        })
      }
    },
    login () {
      if (this.value === 1) {
        this.teacherlogin()
      } else if (this.value === 2) {
        this.schoollogin()
      } else if (this.value === 3) {
        this.edulogin()
      } else {
        this.$message({
          message: '请选择身份登录',
          type: 'warning'
        })
      }
    }
  }
}
</script>

<style  scoped>
h1{
  font-size: 25px;
}
i {
  margin-left: 10px;
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
  /* font-size: 18px; 将字体适当的变大加粗 */
  color: white; /* 上面的文本颜色设置为白色，但是placeholder的颜色要单独设置 */
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
