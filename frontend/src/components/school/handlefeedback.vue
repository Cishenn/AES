<!--  -->
<template>
  <div>
    <h1 style="margin:50px 20px auto 350px">反馈列表</h1>
    <template style="margin-left:200px">
      <el-table
        :data="feedtable"
        style="width: 100%">
        <el-table-column
          prop="fromName"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="stateMessage"
          label="反馈内容"
          width="180">
        </el-table-column>
        <el-table-column label="操作" width="180px" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="success" @click="handleaccept(scope.$index, scope.row)">批准</el-button>
            <el-button size="mini" type="danger" @click="handlerepuls(scope.$index, scope.row)">打回</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <el-card class="box-card" style="margin-top:50px">
      <div>
        <span style="color:red">  请注意，本次批准意味着通过了老师的请假，也就是说不再安排其参与本次排考！</span>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      schoolId: this.$store.state.schoolId,
      feedtable: []
    }
  },

  components: {},

  computed: {},

  mounted () {
    this.getfeedback()
  },

  methods: {
    handleaccept (index, row) {
      this.$confirm('此操作将通过考务人员的请假, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('StatusNotes/StatusNotesSloved', {
          params: {
            esId: row.esId,
            stateId: row.stateId,
            auditState: 2
          }
        }).then(resp => {
          this.$message.success('通过成功')
          this.getfeedback()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消打回'
        })
      })
    },
    handlerepuls (index, row) {
      this.$confirm('此操作将打回考务人员的请假, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('StatusNotes/StatusNotesSloved', {
          params: {
            esId: row.esId,
            stateId: row.stateId,
            auditState: 0
          }
        }).then(resp => {
          this.$message.success('打回成功')
          this.getfeedback()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消打回'
        })
      })
    },
    getfeedback () {
      this.$axios.get('StatusNotes/StatusNotesToSchool', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        console.log(resp.data)
        this.feedtable = resp.data.StatusNotes
      })
    }
  }
}

</script>
<style  scoped>
</style>
