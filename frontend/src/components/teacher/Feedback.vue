<template>
  <div class="feedback">
    <div class="mainarea">
      <div class="form-box">
        <title-box>请输入反馈信息</title-box>
        <el-form label-width="80px" style="margin-top: 5%;">
          <el-form-item label="反馈内容">
            <el-input type="textarea" :rows="5" v-model="reason" style="width:300px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button @click="clear">清空</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      reason: ''
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      this.$router.push('/login')
    }
  },
  methods: {
    onSubmit () {
      if (this.reason === '') {
        this.$message({
          message: '反馈内容不能为空!',
          type: 'info'
        })
      } else {
        const esId = this.$store.getters.getTeacherId
        // 考务人员aduitStatus为1时才能成功提交
        this.$axios
          .post(`exStaff/feedback?esId=${esId}&stateMessage=${this.reason}`)
        this.$message({
          message: '反馈已提交!',
          type: 'success'
        })
      }
    },
    clear () {
      this.reason = ''
    }
  }
}

</script>
<style scoped>
.feedback{
    background-color: #e4eeff;
    width: 89.7%;
    height: 91.5%;
    margin-left: -150px;
    margin-top: 3%;
}
.title-box{
  font-size: 20px;
  margin-top: 3%;
  margin-bottom: 3%;
}
.mainarea{
  background-color: #FFFFFF;
  width: 95%;
  height: 96%;
  margin-top: 1%;
  margin-left: 2.5%;
}
.form-box{
  margin-top: 2%;
  margin-left: 30%;
  position: fixed;
}
</style>
