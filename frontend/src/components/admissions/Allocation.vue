<template>
  <div class="Allocation">
    <div style="font-size:20px;color: #FFFFFF;;margin-top: 20px;">自动排考</div>
      <el-tabs style="margin-top: 50px">
        <el-table class="roomtable" v-loading="loading"
        :data="schoolTabledata.slice((currentPage - 1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage"
        border
        header-cell-class-name="tableStyle"
        >
          <!-- <template slot="empty">
            <div>
              <h1>当前本地区未安排排考</h1>
            </div>
          </template> -->
          <el-table-column label="考点名称" align="center" prop="schoolName" width="120px"/>
          <el-table-column label="考点类型" align="center" prop="typeOfExaminationSite" width="120px"/>
          <el-table-column
              align="center"
              prop="tag"
              label="排考状态"
              width="120px"
              filter-placement="bottom-end">
              <template>
                <el-tag
                  :type="success"
                  disable-transitions>{{tags}}</el-tag>
              </template>
            </el-table-column>
          <el-table-column
          align="center"
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.$index, scope.row)" type="text" size="small" >查看</el-button>
            <!-- <el-button type="text" size="small">编辑</el-button> -->
          </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,15,20]"
          :page-size="pagesize"
          class="pagination"
          layout="total, sizes, prev, pager, next, jumper"
          :total="schoolTabledata.length">
        </el-pagination>
      <el-dialog title="第一次排考" :visible.sync="firstarrange">
        <el-tabs>
          <el-tab-pane>
            <span slot="label"><i class="el-icon-price-tag"></i> 考场安排</span>
            <el-table
              :data="roomTable"
              style="width: 100%">
              <el-table-column
                prop=""
                label="教学楼"
                width="180">
              </el-table-column>
              <el-table-column
                prop=""
                label="楼层"
                width="180">
              </el-table-column>
              <el-table-column
                prop="房间号"
                label="地址">
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane>
            <span slot="label"><i class="el-icon-user"></i> 监考组安排</span>
            监考组安排
          </el-tab-pane>
          <el-tab-pane>
            <span slot="label"><i class="el-icon-s-custom"></i> 巡考组安排</span>
            巡考组安排
          </el-tab-pane>
        </el-tabs>
      </el-dialog>
      </el-tabs>
    <div class="submit">
      <el-button size="medium" @click="Autoallocation">自动分配</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      firstarrange: false,
      secondarrange: false,
      eduId: this.$store.state.eduId,
      tags: '未排考',
      arrangeexamstate: '',
      schoolTabledata: [],
      loading: true,
      stripe: true,
      currentPage: 1,
      pagesize: 10,
      invigilatorTableData: [{
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }],
      allocationTableData: [{
        schoolName: '合肥第一中学',
        roomId: '1',
        invigilatorGroup: '2'
      }],
      edulevel: Number,
      roomTable: []
    }
  },
  created () {
    if (this.$store.state.eduId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
    this.getArrangeexamstate()
    this.getedulevel()
    this.getschooltable()
  },
  methods: {
    getfirstexamRoom (eduId) {

    },
    getschooltable () {
      this.$axios.get('school/arrangedSchools/eduId', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.schoolTabledata = resp.data.School
        this.loading = false
      }).catch(resp => {
        this.$message.error('请求获得已排考信息时出错')
      })
    },
    handleClick (index, row) {
      console.log(row)
      this.firstarrange = true
    },
    Autoallocation () {
      if (this.edulevel === 1 || this.edulevel === 3) {
        this.$message('您没有权限安排排考！')
      } else {
        if (this.tags === '未排考') {
          this.$confirm('当前未排考,此操作将进行第一次自动化分配, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.get('enrollmentDepartment/arrangeLevel/eduId', {
              params: {
                eduId: this.eduId
              }
            }).then(resp => {
              alert(resp.data)
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消分配'
            })
          })
        } else if (this.tags === '第一步排考') {
        }
      }
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    getArrangeexamstate () {
      this.$axios.get('enrollmentDepartment/arrangeLevel/eduId', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.arrangeexamstate = resp.data
        if (resp.data === 1) {
          this.tags = '第一步排考'
        } else if (resp.data === 2) {
          this.tags = '第二步排考'
        }
      })
    },
    getedulevel () {
      this.$axios.get('enrollmentDepartment/getOne', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.edulevel = resp.data.eduLevel
        // alert(this.edulevel)
      })
    }
  }
}
</script>

<style>
  /* .tableStyle {
    background-color:#3498db!important;
    color:#fff;
    font-weight:400;
    overflow: auto;
    white-space: nowrap;
  }
  .RoomTable {
    margin-top: 30px;
    margin-left: -63px;
    width: 601px;
    overflow: auto;
  }
  .roomtable {
    height: 280px;
    overflow: auto;
  }
  .InvigilatorTable{
    margin-top: 15px;
    max-width: 601px;
    margin-left: -63px;
    table-layout:auto !important;
    overflow: auto;
  }
  .invigilatortable{
    overflow: auto;
    height: 280px;
  }
  .AllocationTable{
    float: right;
    margin-top: -575px;
    margin-left: 550px;
  }
  .submit{
    margin-right: 80px;
    float: right;
  }
  .slot::-webkit-scrollbar{
      width: 2px;
  }
  .slot::-webkit-scrollbar-thumb{
      border-radius: 2px;
      height: 50px;
      background: #eee;
  }
  .slot::-webkit-scrollbar-track{
      box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
      border-radius: 2px;
      background: rgba(0,0,0,0.4);
  } */
</style>
