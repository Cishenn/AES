<template>
  <div class="box">
    <div class="title-box">历史记录</div>
    <div class="form-box">
      <el-tabs v-model="activeName"  @tab-click="handleClick">
        <el-tab-pane label="历史监考" name="first">
          <div style="font-size:20px;margin-bottom:10px">历史监考信息表单</div>
          <el-table border class="historyTable"
            :data="historyTable.slice((hCurrentPage-1)*hPagesize,hCurrentPage*hPagesize)"
            :stripe="true"
            :current-page.sync="hCurrentPage">
            <el-table-column label="监考年份" prop="year" align="center" width="220" />
            <el-table-column label="监考详细信息" prop="hsMessage" align="center" width="249" />
          </el-table>
          <div>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="hCurrentPage"
              :page-sizes="[5,10,15,20]"
              :page-size="hPagesize"
              class="pagination"
              layout="total, sizes, prev, pager, next, jumper"
              :total="historyTable.length">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="历史违规" name="second">
          <div style="font-size:20px;margin-bottom:10px">历史违规信息表单</div>
          <el-table border class="violationTable"
            :data="violationTable.slice((vCurrentPage-1)*vPagesize,vCurrentPage*vPagesize)"
            :stripe="true"
            :current-page.sync="vCurrentPage">
            <el-table-column label="违规年份" prop="year" align="center" width="150" />
            <el-table-column label="违规详细信息" prop="vrMessage" align="center" width="250" />
            <el-table-column label="处罚时长" prop="vrPunish" align="center" width="150" />
          </el-table>
          <div>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="vCurrentPage"
              :page-sizes="[5,10,15,20]"
              :page-size="vPagesize"
              class="pagination"
              layout="total, sizes, prev, pager, next, jumper"
              :total="violationTable.length">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="历史反馈" name="third">历史反馈信息表单</el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      esId: '',
      activeName: 'first',
      historyTable: [],
      violationTable: [],
      hPagesize: 10,
      hCurrentPage: 1,
      vPagesize: 10,
      vCurrentPage: 1
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      this.$router.push('/login')
    }
    this.esId = this.$store.getters.getTeacherId
    console.log(this.esId)
  },
  mounted () {
    this.getHistoryTable()
    this.getViolationTable()
  },

  methods: {
    getHistoryTable () {
      this.$axios
        .get('history/oneHistory', {
          params: {
            esId: this.esId
          }
        })
        .then(resp => {
          console.log(resp)
          this.historyTable = resp.data.History
        })
    },
    getViolationTable () {
      // this.$axios
      //   .get('history/oneHistory', {
      //     params: {
      //       esId: this.esId
      //     }
      //   })
      //   .then(resp => {
      //     console.log(resp)
      //     this.historyTable = resp.data.History
      //   })
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
  margin-left: 40px;
  margin-bottom: 20px;
}

.form-box{
  margin-left: 60px;
  width: 100%;
}

.historyTable {
  margin-bottom: 2%;
  width: 470px;
}

.violationTable {
  margin-bottom: 2%;
  width: 551px;
}

.pagination {
  width: 400px;
  margin: auto;
}

.el-table__header {
  margin: 0;
}
</style>
