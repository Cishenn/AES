<template>
  <div class="teacherInfo">
    <el-tabs>
      <el-tab-pane label="信息审核" class="first">
        <div class="inspectorTableTitle" style="font-size:20px">本校已注册考务人员信息</div>
        <el-table border class="table" :data="teachertablenoneapprove.slice((currentPage-1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage">
          <el-table-column label="姓名" align="center" width="120px">
            <template slot-scope="scope">
              {{ scope.row.name }}
            </template>
          </el-table-column>
          <el-table-column label="年龄" align="center" width="120px">
            <template slot-scope="scope">
              {{ scope.row.age }}
            </template>
          </el-table-column>
          <el-table-column label="电话" align="center" width="120px">
            <template slot-scope="scope">
              {{ scope.row.telephoneNumber }}
            </template>
          </el-table-column>
          <el-table-column label="性别" align="center" width="120px">
            <template slot-scope="scope">
              {{ scope.row.sex }}
            </template>
          </el-table-column>
          <el-table-column label="年级" align="center" width="120px">
            <template slot-scope="scope">
              {{ scope.row.grade }}
            </template>
          </el-table-column>
          <el-table-column label="科目" align="center" width="120px">
            <template slot-scope="scope">
              {{ scope.row.subject }}
            </template>
          </el-table-column>
          <el-table-column label="操作" width="180px" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="success" @click="handleaccept(scope.$index, scope.row)">批准</el-button>
              <el-button size="mini" type="danger" @click="handlerepuls(scope.$index, scope.row)">打回</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5,10,15,20]"
            :page-size="pagesize"
            class="pagination"
            layout="total, sizes, prev, pager, next, jumper"
            :total="teachertablenoneapprove.length">
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="信息上传" class="second">
        <el-table border class="table" :data="teacherTableapprove" ref="multipleTable">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="姓名" prop="name" align="center" width="120px"/>
          <el-table-column label="年龄" prop="age" align="center" width="120px"/>
          <el-table-column label="电话" prop="telephoneNumber" align="center" width="120px"/>
          <el-table-column label="性别" prop="sex" align="center" width="120px"/>
          <el-table-column label="年级" prop="grade" align="center" width="120px"/>
          <el-table-column label="科目" prop="subject" align="center" width="120px"/>
          <!-- <el-table-column label="操作" width="180px" align="center">
            <template>
              <el-button size="mini">选中</el-button>
              <el-button size="mini">取消</el-button>
            </template>
          </el-table-column> -->
          <el-table-column
            align="center"
            prop="tag"
            label="状态"
            width="120px"
            :filters="[{ text: '未提交', value: '未提交' }, { text: '已提交，未审核', value: '已提交,未审核' },{ text: '已审核', value: '已审核' }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.finalRejection"
                disable-transitions>{{scope.row.eduExamine}}</el-tag>
            </template>
          </el-table-column>
        </el-table>
        <div>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5,10,15,20]"
            :page-size="pagesize"
            class="pagination"
            layout="total, sizes, prev, pager, next, jumper"
            :total="teachertablenoneapprove.length">
          </el-pagination>
        </div>
        <div>
          <el-button class="submitBtn" @click="submitBtn">提交给上级招办</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data () {
    return {
      stripe: true,
      currentPage: 1,
      pagesize: 10,
      total: 0,
      schoolId: this.$store.state.schoolId,
      teachertablenoneapprove: [],
      teacherTableapprove: [],
      tags: []
    }
  },
  mounted () {
    this.run()
  },
  created () {
    if (this.$store.state.schoolId === '') {
      this.$router.push('/login')
    }
  },
  methods: {
    filterTag (value, row) {
      return row.eduExamine === value
    },
    run () {
      this.getTeachertablenoneapprove()
      this.getTeachertableapprove()
      // setTimeout(this.run, 5000)
    },
    handlerepuls (index, row) {
      this.$prompt('请输入驳回原因', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        this.$axios.post(`exStaff/finalRejection?esId=${row.esId}&finalRejection=${value}`)
          .then(resp => {
            this.$message({
              type: 'success',
              message: '驳回成功！'
            })
            this.getTeachertablenoneapprove()
          }).catch(resp => {
            this.$message({
              message: '服务器无响应',
              type: 'false'
            })
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消驳回'
        })
      })
    },
    handleaccept (index, row) {
      this.$confirm('此操作将通过考务人员审核, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post(`exStaff/schoolExamine?esId=${row.esId}&schoolExamine=${2}`).then(resp => {
          this.$message({
            message: '审核成功',
            type: 'success'
          })
          this.getTeachertablenoneapprove()
          this.getTeachertableapprove()
        }).catch(resp => {
          this.$message({
            message: '审核失败',
            type: 'danger'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消审核'
        })
      })
    },
    submitBtn () {
      console.log(this.$refs.multipleTable.selection.length)
      this.$confirm('此操作将上交考务人员信息并且之后无法修改, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var a = true
        for (let i = 0; i < this.$refs.multipleTable.selection.length; i++) {
          this.$axios.post(`exStaff/eduExamine?esId=${this.$refs.multipleTable.selection[i].esId}&eduExamine=${1}`)
            .then(resp => {
              console.log(resp)
              this.getTeachertableapprove()
            }).catch(resp => {
              a = false
            })
        }
        if (!a) {
          this.$message({
            message: '上传失败，请重新上传',
            type: 'false'
          })
        } else {
          this.$message({
            message: '上传成功！',
            type: 'success'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消上传'
        })
      })
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    getTeachertablenoneapprove () {
      this.$axios.get('exStaff/getVerifying', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.teachertablenoneapprove = resp.data.ExamStaff
      })
    },
    getTeachertableapprove () {
      this.$axios.get('exStaff/getVerified', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.teacherTableapprove = resp.data.ExamStaff
        console.log(this.teacherTableapprove)
        for (let i = 0; i < this.teacherTableapprove.length; i++) {
          if (this.teacherTableapprove[i].eduExamine === 0) {
            this.teacherTableapprove[i].eduExamine = '未提交'
            this.teacherTableapprove[i].finalRejection = 'danger'
          } else if (this.teacherTableapprove[i].eduExamine === 1) {
            this.teacherTableapprove[i].eduExamine = '已提交,未审核'
            this.teacherTableapprove[i].finalRejection = 'info'
          } else if (this.teacherTableapprove[i].eduExamine === 2) {
            this.teacherTableapprove[i].eduExamine = '已通过'
            this.teacherTableapprove[i].finalRejection = 'success'
          }
        }
      })
    }
  }
}
</script>

<style scoped>

.inspectorTableTitle {
  text-align: center;
  margin-bottom: 2%;
}

.table {
  margin-bottom: 2%;
}

.pagination {
  width: 400px;
  margin: auto;
}

.submitBtn {
  width: 140px;
  display: block;
  margin: auto;
  margin-top: 1%;
}

</style>

<style >
.el-tabs__item {
  font-size: 18px !important;
}
</style>
