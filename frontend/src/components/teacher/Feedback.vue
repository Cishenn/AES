<!--  -->
<template>
  <div class="box">
    <div class="title-box">提交您的反馈信息</div>
    <div class="form-box">
      <!-- <div></div> -->
      <el-form label-width="80px">
        <el-form-item label="反馈内容">
          <el-input type="textarea" v-model="reason" style="width:300px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即提交</el-button>
          <el-button>取消</el-button>
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
      // alert('不要随便乱进哦!')
      this.$router.push('/login')
    }
  },
  components: {},

  computed: {},

  mounted: {},

  methods: {}
}

</script>
<style scoped>
.box{

  border-radius: 5px;
  padding: 20px;
}
.title-box{
  font-size: 20px;
  /* color: red; */
  margin-bottom: 20px;
}
.form-box{
  margin-left: 60px;
}
</style>
