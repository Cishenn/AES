<template>
  <div class="box">
    <!-- <img src="../assets/back-grond.jpg" alt=""> -->
    <img src="" alt="">
    <div class="login-box">
      <div class="login-title"><h1 class="login-title">Regist</h1></div>
      <div class="form">
        <div class="line">
          <i class="el-icon-phone"></i>
          <el-input v-model="phonenum" class="item" type="text" placeholder="Please enter your phonenum." />
        </div>
        <div class="line">
          <i class="el-icon-key"></i>
          <el-input v-model="password" class="item" type="text" placeholder="Please enter you pas."  />
        </div>
        <div class="line">
          <i class="el-icon-school"></i>
          <el-select class="select-box" v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
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
            <el-input v-model="identity" class="classifyitem" type="text" placeholder="请输入右中的验证码." />
          </div>
          <img src="../assets/classify.jpg" class="yanzhengma-style"  />
        </div>
        <div>
          <el-button type="primary" class="button-box" >Regist</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      url: '../assets/classify.jpg',
      phonenum: '',
      password: '',
      value: '',
      identity: '',
      options: [{
        value: 1,
        label: '合工大'
      }, {
        value: 2,
        label: '安徽大学'
      }, {
        value: 3,
        label: '中科大'
      }]
    }
  },
  methods: {
  }
}
</script>

<style  scoped>
.line-x{
  display: flex;
}
.yanzhengma-style{
  width: 90px;
  height: 40px;
  border-radius: 10px;
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
  background-image: linear-gradient(to right, #00dbde 0%, #fc00ff 100%); /* 为按钮增加渐变颜色 */
}
.regist{
  color: aqua;
}
</style>
