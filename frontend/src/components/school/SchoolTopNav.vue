<template>
  <div class="container">
    <div id="topNav" >
      <span class="welcome">{{this.schoolName}}管理员,欢迎使用自动排考系统!</span>
      <el-switch
        class="online"
        v-model="value1"
        active-text="在线"
        inactive-text="离线">
      </el-switch>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      value1: true,
      value2: true,
      schoolName: ''
    }
  },
  created () {
    this.getSchoolName()
  },
  methods: {
    getSchoolName () {
      this.$axios.get('school/schoolName/schoolId', {
        params: {
          schoolId: this.$store.state.schoolId
        }
      }).then(resp => {
        this.schoolName = resp.data
      })
    }
  }
}
</script>

<style scoped>
.container{
  width: 90%;
}
  #topNav{
    width: 90%;
    float: right;
    height: 60px;
    margin-right: -115PX;
  }
  .online{
    margin-top: 2.5%;
    float: right;
  }
  .welcome{
    margin-top: 2%;
    margin-left: -80px;
    float: left;
    font-size: 20px;
    color: #606266;
  }
</style>
