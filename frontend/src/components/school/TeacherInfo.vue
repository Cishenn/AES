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
            <template>
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
        <el-table class="table" :data="teachertablenoneapprove">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="姓名" prop="name" align="center" width="120px"/>
          <el-table-column label="年龄" prop="age" align="center" width="120px"/>
          <el-table-column label="电话" prop="telephoneNumber" align="center" width="120px"/>
          <el-table-column label="性别" prop="sex" align="center" width="120px"/>
          <el-table-column label="年级" prop="grade" align="center" width="120px"/>
          <el-table-column label="科目" prop="subject" align="center" width="120px"/>
          <el-table-column label="操作" width="180px" align="center">
            <template>
              <el-button size="mini">选中</el-button>
              <el-button size="mini">取消</el-button>
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
          <el-button class="submitBtn">提交给上级招办</el-button>
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
      teachertablenoneapprove: [{
        name: '王明',
        age: '40',
        phone: '13856781234',
        sex: '男',
        grade: '高二',
        subject: '数学'
      }]
    }
  },
  mounted () {
    this.getTeachertable()
  },
  created () {
    if (this.$store.state.schoolId === '') {
      this.$router.push('/login')
    }
  },
  methods: {
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    getTeachertable () {
      this.$axios.get('exStaff/getList', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        console.log(resp.data)
        this.teachertablenoneapprove = resp.data.ExamStaff
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
