<template>
  <div class="InputStudents">
    <div class="mainarea">
      <div class="app">
        <h1>本地区考生数目</h1>
        <div class="inputLi" style="color: #606266;">
          理科考生人数：
          <el-input v-model="liNum" placeholder="请输入今年理科生数目" ></el-input>
        </div>
        <div class="inputWen" style="color: #606266;">
          文科考生人数：
          <el-input v-model="wenNum" placeholder="请输入今年文科生数目"></el-input>
        </div>
        <div class="inputTe" style="color: #606266;">
          特长考生人数：
          <el-input v-model="teNum" placeholder="请输入今年特长生数目"></el-input>
        </div>
        <div class="save">
          <el-button size="medium" @click="savenum">保存</el-button>
        </div>
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
      numofcanid: '',
      edulevel: ''
    }
  },
  created () {
    if (this.$store.state.eduId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
    this.getedulevel()
  },
  methods: {
    getnum () {
      if (this.edulevel === 1) {
        this.getstudentnum()
      } else if (this.edulevel === 2) {
        this.getstudentsnum2()
      } else if (this.edulevel === 3) {
        this.getstudentsnum3()
      }
    },
    getedulevel () {
      // 获得招办等级
      this.$axios.get('enrollmentDepartment/getOne', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.edulevel = resp.data.eduLevel
        this.getnum()
        // alert(this.edulevel)
      })
    },
    getstudentsnum2 () {
      this.$axios.get('numberOfCandidates/num2/eduId', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.liNum = resp.data.numOfScience
        this.wenNum = resp.data.numOfArt
        this.teNum = resp.data.numOfExcellent
      })
    },
    getstudentsnum3 () {
      this.$axios.get('numberOfCandidates/num3/eduId', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.liNum = resp.data.numOfScience
        this.wenNum = resp.data.numOfArt
        this.teNum = resp.data.numOfExcellent
      })
    },
    savenum () {
      if (this.edulevel !== 1) {
        this.$message.error('只有地区级招办有权修改考生数目！')
      } else {
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
      }
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

<style scoped>
  .InputStudents{
    background-color: #e4eeff;
    width: 89.7%;
    height: 91.5%;
    margin-left: -150px;
    margin-top: 3%;
  }
  .mainarea{
    background-color: #FFFFFF;
    width: 95%;
    height: 96%;
    margin-top: 1%;
    margin-left: 2.5%;
  }
  .app{
    margin-top: 5%;
    margin-left: 32%;
    position: fixed;
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
