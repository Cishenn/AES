<template>
  <div class="box">
    <div class="title-box">提交您的反馈信息</div>
    <div class="form-box">
      <el-form label-width="80px">
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
.box{

  border-radius: 5px;
  padding: 20px;
}
.title-box{
  font-size: 20px;
  margin-bottom: 20px;
}
.form-box{
  margin-left: 60px;
}
</style>
