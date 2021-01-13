<template>
  <div class="container">
    <div id="topNav" >
      <span class="welcome">{{this.eduName}},欢迎使用自动排考系统!</span>
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
      eduName: '',
      eduId: ''
    }
  },
  created () {
    this.eduId = this.$store.state.eduId
    this.getEduInfo()
    console.log(this.eduId)
  },
  methods: {
    getEduInfo () {
      this.$axios
        .get('enrollmentDepartment/getOne', {
          params: {
            eduId: this.eduId
          }
        })
        .then(resp => {
          this.eduName = resp.data.eduName
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
