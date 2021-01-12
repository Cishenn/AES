<template>
  <div class="InputStudents">
    <div style="font-size:20px;color: #FFFFFF;margin-top: 26px;">考生信息录入</div>
    <div class="mainarea">
      <div class="inputLi">
        理科考生人数：
        <el-input v-model="liNum" placeholder="请输入今年理科生数目" ></el-input>
      </div>
      <div class="inputWen">
        文科考生人数：
        <el-input v-model="wenNum" placeholder="请输入今年文科生数目"></el-input>
      </div>
      <div class="inputTe">
        理特长考生人数：
        <el-input v-model="teNum" placeholder="请输入今年特长生数目"></el-input>
      </div>
      <div class="save">
        <el-button size="medium" @click="savenum">保存</el-button>
      </div>
    </div>
  </div>

</template>

<script>

export default {
  data () {
    return {
      eduId: this.$store.state.eduId,
      liNum: '',
      wenNum: '',
      teNum: '',
      numofcanid: ''
    }
  },
  created () {
    if (this.$store.state.eduId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
    this.getstudentnum()
  },
  methods: {
    savenum () {
      this.$confirm('此操作将修改今年的文理科考生数目, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('numberOfCandidates/Candidates', {
          numOfCanId: this.numofcanid,
          eduId: this.eduId,
          numOfScience: this.liNum,
          numOfArt: this.wenNum,
          numOfExcellent: this.teNum
        }).then(resp => {
          // console.log(resp)
          this.$message.success('保存成功!')
        }).catch(resp => {
          this.$message.error('保存失败!')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消审核'
        })
      })
    },
    getstudentnum () {
      this.$axios.get('numberOfCandidates/Candidates', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.numofcanid = resp.data.numOfCanId
        this.liNum = resp.data.numOfScience
        this.wenNum = resp.data.numOfArt
        this.teNum = resp.data.numOfExcellent
      }).catch(resp => {
        this.$message.error('服务器获取今年文理科数目无响应!')
      })
    }
  }
}
</script>

<style>
  .mainarea{
    margin-left: 60px;
  }
  .inputLi{
    margin-top: 50px;
  }
  .inputWen{
    margin-top: 20px;
  }
  .inputTe{
    margin-top: 20px;
  }
  .save{
    margin-top: 20px;
  }
</style>
