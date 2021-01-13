<template>
  <div class="schoolInfo">
    <div class="mainarea">
      <div class="app">
        <div class="search">
            <el-input v-model="search" placeholder="请输入"
            suffix-icon="el-icon-search"></el-input>
        </div>
        <div style=" margin-left: 120px;margin-top: 70px;background-color: #FFFFFF;">
          <el-table class="schoolTable"
          v-loading="loading"
          :data="tables.slice((currentPage-1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage"
          border
          header-cell-class-name="tableStyle"
          >
            <el-table-column label="学校名称" prop="schoolName" width="120px" align="center"/>
            <el-table-column label="学校考务人员数" prop="typeOfExaminationSite" width="120px" align="center"/>
            <el-table-column label="学校考场数" prop="eduId" width="120px" align="center"/>
            <el-table-column align="center" label="考场审核状态" width="120px">
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.exRoomExamine === 1 ? 'info' : (scope.row.exRoomExamine === 2 ?'success':'danger')"
                  >{{scope.row.exRoomExamine === 1 ? '未审核' : (scope.row.exRoomExamine === 2 ? '已审核': '被打回')}}
                </el-tag>
              </template>
              </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.$index, scope.row)" type="text" size="small" >查看</el-button>
                <!-- <el-button type="text" size="small">编辑</el-button> -->
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
              style="margin-left: 12%;"
              :total="tables.length">
            </el-pagination>
          </div>
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
      search: '',
      schoolTable: [],
      schoolTable2: [],
      stripe: true,
      currentPage: 1,
      pagesize: 5,
      total: 0,
      loading: true
    }
  },
  computed: {
    tables: function () {
      var search = this.search
      if (search) {
        return this.schoolTable.filter(function (dataNews) {
          return Object.keys(dataNews).some(function (key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.schoolTable
    }
  },
  created () {
    if (this.$store.state.eduId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
  },
  mounted () {
    this.getAllschool()
    this.a()
  },
  watch: {
    $route: 'getAllschool'
  },
  methods: {
    a () {
      setTimeout(() => {
        this.schoolTable = this.schoolTable2
        this.loading = false
      }, 1000)
    },
    getAllschool () {
      this.$axios.get('school/schools/eduId', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        console.log(resp.data)
        this.schoolTable2 = resp.data.School
        // console.log(this.schoolTable)
        const length = this.schoolTable2.length
        for (let i = 0; i < length; i++) {
          // 以eduId暂存考场数目，以typeOfExaminationSite暂存考务人员数目
          this.$axios.get('exRoom/countSelect/schoolId', {
            params: {
              schoolId: this.schoolTable2[i].schoolId
            }
          }).then(resp => {
            this.schoolTable2[i].eduId = resp.data
          }).catch(resp => {
            this.$message.error('获取考场数目不成功')
          })
          this.$axios.get('exStaff/countSelect/schoolId', {
            params: {
              schoolId: this.schoolTable2[i].schoolId
            }
          }).then(resp => {
            this.schoolTable2[i].typeOfExaminationSite = resp.data
          })
          // if (i === length - 1) {
          //   this.schoolTable = this.schoolTable2
          //   this.loading = false
          // }
        }
      })
    },
    handleClick (index, row) {
      // console.log(this.schoolTable[index])
      this.$store.commit('setsubordinatesschoolsId', this.schoolTable[index].schoolId)
      this.$router.push('/admissions/schoolDetail')
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    }
  }
}
</script>

<style scoped>
  .schoolInfo{
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
    margin-left: 18%;
    position: fixed;
  }
  .tableStyle {
    background-color:#3498db!important;
    color:#fff;
    font-weight:400;
    overflow: auto;
  }
  .schoolTable{
    height: 40%;
  }
  .schoolPagination{
    margin-top: 10px;
    margin-left: 35px;
  }
  .search{
    width: 200px;
    margin-top: 5%;
    margin-bottom: 15px;
    float: right;
  }
</style>
