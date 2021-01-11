<template>
  <div class="box">
    <div class="title-box">历史记录</div>
    <div class="form-box">
      <el-tabs v-model="activeName" >
        <el-tab-pane label="历史监考" name="first">
          <div style="font-size:20px;margin-bottom:10px">历史监考信息表单</div>
          <el-table border class="table"
            :data="historyTable.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            :stripe="true"
            :current-page.sync="currentPage">
            <el-table-column label="监考年份" prop="year" align="center" width="220" />
            <el-table-column label="监考详细信息" prop="hsMessage" align="center" width="249" />
          </el-table>
          <div>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage1"
              :page-sizes="[5,10,15,20]"
              :page-size="pagesize"
              class="pagination"
              layout="total, sizes, prev, pager, next, jumper"
              :total="historyTable.length">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="历史违规" name="second">
          <div style="font-size:20px;margin-bottom:10px">历史违规信息表单</div>
          <el-table
            :data="Violationtable"
            :stripe="stripe"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="date"
              label="违规日期"
              width="150">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="school"
              label="考点"
              width="120">
            </el-table-column>
            <el-table-column
              prop="reason"
              label="违规原因"
              width="120">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
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
      pagesize: 10,
      currentPage: 1
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      // alert('不要随便乱进哦!')
      this.$router.push('/login')
    }
    this.esId = this.$store.getters.getTeacherId
    console.log(this.esId)
  },
  mounted () {
    this.getHistoryTable()
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

.table {
  margin-bottom: 2%;
  width: 470px;
}

.pagination {
  width: 400px;
  margin: auto;
}

.el-table__header {
  margin: 0;
}
</style>
