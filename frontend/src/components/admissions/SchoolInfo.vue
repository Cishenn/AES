<template>
  <div class="schoolInfo">
    <div class="container">
      <div style="font-size:20px;color: #FFFFFF;;margin-top: 12px;">下属学校</div>
      <div class="search">
          <el-input v-model="searchfor" placeholder="请输入"
          suffix-icon="el-icon-search"></el-input>
      </div>
      <div style=" margin-left: 120px;margin-top: 70px;background-color: #FFFFFF;">
        <el-table class="schoolTable"
        :data="schoolTable.slice((currentPage-1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage"
        border
        header-cell-class-name="tableStyle"
        >
          <el-table-column label="学校名称" prop="schoolName" width="120px" align="center"/>
          <el-table-column label="学校考务人员数" prop="exRoomExamine" width="120px" align="center"/>
          <el-table-column label="学校考场数" prop="eduId" width="120px" align="center"/>
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
            :total="schoolTable.length">
          </el-pagination>
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
      searchfor: '',
      schoolTable: [],
      stripe: true,
      currentPage: 1,
      pagesize: 5,
      total: 0
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
  },
  methods: {
    getAllschool () {
      this.$axios.get('school/school/eduId', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.schoolTable = resp.data.School
        console.log(this.schoolTable)
        const length = this.schoolTable.length
        for (let i = 0; i < length; i++) {
          // 以eduId暂存考场数目，以exroomexamine暂存考务人员数目
          this.$axios.get('exRoom/countSelect/schoolId', {
            params: {
              schoolId: this.schoolTable[i].schoolId
            }
          }).then(resp => {
            this.schoolTable[i].eduId = resp.data
          }).catch(resp => {
            this.$message.error('获取考场数目不成功')
          })
          this.$axios.get('exStaff/countSelect/schoolId', {
            params: {
              schoolId: this.schoolTable[i].schoolId
            }
          }).then(resp => {
            this.schoolTable[i].exRoomExamine = resp.data
          })
        }
      })
    },
    handleClick (index, row) {
      console.log(this.schoolTable[index])
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

<style >
  .container{
    width: 100%;
    height: 100%;
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
