<template>
  <div class="topNav" >
    <span class="welcome">{{this.name}}老师,欢迎使用自动排考系统!</span>
    <el-switch
      class="online"
      v-model="value"
      active-text="在线"
      inactive-text="离线">
    </el-switch>
  </div>
</template>

<script>
export default {
  data () {
    return {
      value: true,
      esId: '',
      name: ''
    }
  },
  created () {
    this.esId = this.$store.getters.getTeacherId
    this.$axios
      .get('exStaff/exStaff/exStaffId', {
        params: {
          esId: this.esId
        }
      })
      .then(resp => {
        this.name = resp.data.name
      })
  }
}
</script>

<style scoped>

.topNav{
  width: 90%;
  height: 20%;
  margin-right: -115PX;
}

.online{
  margin-top: 2.5%;
  float: right;
}

.welcome{
  margin-top: 2%;
  margin-left: 15%;
  float: left;
  font-size: 20px;
  color: #606266;
}
</style>
