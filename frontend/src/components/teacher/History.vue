<template>
  <div class="history">
    <div class="mainarea">
      <div class="form-box">
        <el-tabs v-model="activeName" >
          <el-tab-pane label="历史监考" name="first">
            <el-scrollbar
                wrapClass="scrollbar-wrap"
                style="height:580px"
                ref="scrollbarContainer">
                <div>
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
                      @size-change="hHandleSizeChange"
                      @current-change="hHandleCurrentChange"
                      :current-page="hCurrentPage"
                      :page-sizes="[5,10,15,20]"
                      :page-size="hPagesize"
                      class="pagination"
                      layout="total, sizes, prev, pager, next, jumper"
                      :total="historyTable.length">
                    </el-pagination>
                  </div>
                </div>
            </el-scrollbar>
          </el-tab-pane>
          <el-tab-pane label="历史违规" name="second">
            <el-scrollbar
                wrapClass="scrollbar-wrap"
                style="height:580px"
                ref="scrollbarContainer">
                <div>
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
                      @size-change="vHandleSizeChange"
                      @current-change="vHandleCurrentChange"
                      :current-page="vCurrentPage"
                      :page-sizes="[5,10,15,20]"
                      :page-size="vPagesize"
                      class="pagination"
                      layout="total, sizes, prev, pager, next, jumper"
                      :total="violationTable.length">
                    </el-pagination>
                  </div>
                </div>
            </el-scrollbar>
          </el-tab-pane>
          <el-tab-pane label="历史反馈" name="third">
            <el-scrollbar
                wrapClass="scrollbar-wrap"
                style="height:580px"
                ref="scrollbarContainer">
                <div>
                  <div style="font-size:20px;margin-bottom:10px">历史违规信息表单</div>
                  <el-table border class="feedbackTable"
                    :data="feedbackTable.slice((fCurrentPage-1)*fPagesize,fCurrentPage*fPagesize)"
                    :stripe="true"
                    :current-page.sync="fCurrentPage">
                    <el-table-column label="反馈详细信息" prop="stateMessage" align="center" width="269" />
                    <el-table-column label="审核状态" prop="auditState" align="center" width="200" />
                  </el-table>
                  <div>
                    <el-pagination
                      @size-change="fHandleSizeChange"
                      @current-change="fHandleCurrentChange"
                      :current-page="fCurrentPage"
                      :page-sizes="[5,10,15,20]"
                      :page-size="fPagesize"
                      class="pagination"
                      layout="total, sizes, prev, pager, next, jumper"
                      :total="feedbackTable.length">
                    </el-pagination>
                  </div>
                </div>
            </el-scrollbar>
          </el-tab-pane>
        </el-tabs>
      </div>
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
      feedbackTable: [],
      hPagesize: 10,
      hCurrentPage: 1,
      vPagesize: 10,
      vCurrentPage: 1,
      fPagesize: 10,
      fCurrentPage: 1
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      this.$router.push('/login')
    }
    this.esId = this.$store.getters.getTeacherId
  },
  mounted () {
    this.getHistoryTable()
    this.getViolationTable()
    this.getFeedbackTable()
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
          this.historyTable = resp.data.History
        })
    },
    getViolationTable () {
      this.$axios
        .get('violationRecord/violationRecords', {
          params: {
            esId: this.esId
          }
        })
        .then(resp => {
          this.violationTable = resp.data.violationRecords
        })
    },
    getFeedbackTable () {
      this.$axios
        .get('StatusNotes/StatusNotes', {
          params: {
            esId: this.esId
          }
        })
        .then(resp => {
          for (const item of resp.data.StatusNotes) {
            console.log(item)
            const tuple = { stateMessage: '', auditState: '' }
            tuple.stateMessage = item.stateMessage
            switch (item.auditState) {
              case '0':
                tuple.auditState = '未通过'
                break
              case '1':
                tuple.auditState = '未审核'
                break
              case '2':
                tuple.auditState = '已审核'
                break
            }
            console.log(tuple)
            this.feedbackTable.push(tuple)
          }
        })
    },
    hHandleSizeChange (val) {
      this.hPagesize = val
    },
    hHandleCurrentChange (val) {
      this.hCurrentPage = val
    },
    vHandleSizeChange (val) {
      this.vPagesize = val
    },
    vHandleCurrentChange (val) {
      this.vCurrentPage = val
    },
    fHandleSizeChange (val) {
      this.fPagesize = val
    },
    fHandleCurrentChange (val) {
      this.fCurrentPage = val
    }
  }
}

</script>

<style scoped>
.history{
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
.form-box{
  margin-top: 2%;
  margin-left: 28%;
  position: fixed;
}

.historyTable {
  margin-bottom: 2%;
  width: 470px;
}

.violationTable {
  margin-bottom: 2%;
  width: 551px;
}

.feedbackTable {
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
.scrollbar-wrap.el-scrollbar__wrap {
    overflow-x: hidden;
}
</style>
