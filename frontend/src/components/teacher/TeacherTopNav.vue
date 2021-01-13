<template>
  <div class="container">
    <div id="topNav" >
      <span class="welcome">{{this.form.name}}老师,欢迎使用自动排考系统!</span>
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
      personInfo: {
        esId: '',
        name: '',
        telephoneNumber: '',
        sex: '',
        schoolId: '',
        grade: '',
        subject: ''
      },
      form: {
        name: '',
        phone: '',
        gender: '',
        school: '',
        grade: '',
        subject: ''
      }
    }
  },
  created () {
    this.personInfo.esId = this.$store.getters.getTeacherId
    this.getPersonalInfo()
  },
  methods: {
    getPersonalInfo () {
      this.$axios
        .get('exStaff/exStaff/exStaffId', {
          params: {
            esId: this.personInfo.esId
          }
        })
        .then(resp => {
          this.form.name = resp.data.name
          this.form.phone = resp.data.telephoneNumber
          this.form.gender = resp.data.sex
          this.form.school = resp.data.schoolId
          this.form.grade = resp.data.grade
          this.form.subject = resp.data.subject
          this.updatePersonalInfo()
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
