<template>
  <div style="display: flex;">
    <div class="Allocation">
      <div class="mainarea">
        <div class="app">
          <el-tabs style="margin-top: 50px; align-items: center;">
              <el-table class="roomtable" v-loading="loading"
              :data="schoolTabledata.slice((currentPage - 1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage"
              border
              header-cell-class-name="tableStyle"
              >
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
                        :type="tags !== '未排考'? 'success' : 'danger'"
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
            <el-dialog class="firstarrange" title="第一次排考" :visible.sync="firstarrange">
              <el-tabs>
                <el-tab-pane>
                  <span slot="label"><i class="el-icon-price-tag"></i> 考场安排</span>
                  <el-table
                    :data="roomTable.slice((currentPage2 - 1)*currentPage*pagesize2)" :stripe="stripe" :current-page.sync="pagesize2"
                    style="width: 100%">
                    <el-table-column
                      prop="floor.building"
                      label="教学楼"
                      width="180">
                    </el-table-column>
                    <el-table-column
                      prop="floor.floorStep"
                      label="楼层"
                      width="180">
                    </el-table-column>
                    <el-table-column
                      prop="examRoom.roomNum"
                      label="房间号"
                      width="180">
                    </el-table-column>
                  </el-table>
                  <el-pagination
                    @size-change="handleSizeChange2"
                    @current-change="handleCurrentChange2"
                    :current-page="currentPage2"
                    :page-sizes="[5,10,15,20]"
                    :page-size="pagesize2"
                    class="pagination"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="roomTable.length">
                  </el-pagination>
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
          <div class="submit" style="display: flex;justify-content: center;">
            <el-button size="medium" @click="Autoallocation" type="success">自动分配</el-button>
            <el-button size="medium" @click="Cancelarrange" type="danger">重置排考</el-button>
          </div>
        </div>
      </div>
    </div>
    <div>
      <el-card v-if="cardvisable & edulevel === 2" class="box-card">
        <div slot="header" class="clearfix">
          <span>排考错误描述</span>
          <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
        </div>
        <div v-if="errorMessage[0] === 6" style="display:block;">
          <h style="color:red">上次排考考务人员人数不足</h>
          <p>所需考务人员数目： {{errorMessage[1]}}</p>
          <p>拥有考务人员数目： {{errorMessage[2]}}</p>
          <p>缺少考务人员数目： {{errorMessage[1]-errorMessage[2]}}</p>
        </div>
        <div v-if="errorMessage[0] === 7" style="display:block;">
          <h style="color:red">上次排考所有学校的主考人数少于本考区的考场数</h>
          <p>拥有的主考人数：  {{errorMessage[1]}}</p>
          <p>需要的主考人数：  {{errorMessage[2]}}</p>
          <p>缺少的主考人数：  {{errorMessage[2]-errorMessage[1]}}</p>
        </div>
        <div v-if="errorMessage[0] === 8" style="display:block;">
          <h style="color:red">上次排考考场相关原因导致的排考失败</h>
          <p>请调整考场数目!</p>
        </div>
        <div v-if="errorMessage[0] === 9" style="display:block;">
          <h style="color:red">上次排考学校主考人数不足</h>
          <p>学校名称： {{errorMessage[1]}}</p>
          <p>拥有的主考人数：  {{errorMessage[2]}}</p>
          <p>需要的主考人数：  {{errorMessage[3]}}</p>
          <p>缺少的主考人数：  {{errorMessage[3]-errorMessage[2]}}</p>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      cardvisable: true,
      firstarrange: false,
      secondarrange: false,
      eduId: this.$store.state.eduId,
      tags: '',
      arrangeexamstate: '',
      schoolTabledata: [],
      loading: true,
      stripe: true,
      currentPage: 1,
      pagesize: 10,
      currentPage2: 1,
      pagesize2: 10,
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
      roomTable: [],
      errorMessage: this.$store.state.errorMessage
    }
  },
  created () {
    if (this.$store.state.eduId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
    if (this.errorMessage.length === 0 || this.tags !== '未排考') {
      this.cardvisable = false
    }
    this.getArrangeexamstate()
    this.getedulevel()
    this.getschooltable()
  },
  methods: {
    handleerrormessage () {
      // 处理第一次自动化分配出错信息
      if (this.$store.state.errorMessage[0] === 6) {
        this.$message.error('排考失败，考务人员不足，请录入更多的考务人员!')
        // alert('考务人员人数不足,当前需要' + this.$store.state.errorMessage[1] + ',当前拥有' + this.$store.state.errorMessage[2])
      }
    },
    autotocancle () {
      this.$axios.get('AutoDeploy/clearStepOne', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
      }).catch(resp => {
      })
    },
    canclearrang () {
      this.loading = true
      this.$axios.get('AutoDeploy/clearStepOne', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.loading = false
        this.$message.success('重置成功!')
        this.getschooltable()
      }).catch(resp => {
        this.$message.error('重置排考服务器出错!')
      })
    },
    Cancelarrange () {
      console.log(this.eduId)
      if (this.edulevel === 1 || this.edulevel === 3) {
        this.$message('您没有权限取消排考！')
      } else {
        if (this.tags === '未排考') {
          this.$message('当前并未排考！')
        } else {
          this.$confirm('确定重置当前的排考信息?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$prompt('请输入招办ID', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            }).then(({ value }) => {
              if (parseInt(value) === this.eduId) {
                this.canclearrang()
              } else {
                this.$message.error('招办ID错误！')
              }
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消重置'
              })
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消重置'
            })
          })
        }
      }
    },
    getfirstexamRoom (schoolId) {
      this.$axios.get('floor_exRoom/ArrangeFloor_exRoom/schoolId', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        console.log(resp.data)
        this.roomTable = resp.data.floor_exRoom
      })
    },
    getschooltable () {
      this.$axios.get('school/arrangedSchools/eduId', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.schoolTabledata = resp.data.School
        this.getArrangeexamstate()
        this.loading = false
      }).catch(resp => {
        this.$message.error('请求获得已排考信息时出错')
      })
    },
    handleClick (index, row) {
      // console.log(row)
      this.firstarrange = true
      this.getfirstexamRoom(row.schoolId)
    },
    Autoallocation1 () {
      this.$confirm('当前未排考,此操作将进行第一次自动化分配, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        this.$axios.get('AutoDeploy/stepOne', {
          params: {
            eduId: this.eduId
          }
        }).then(resp => {
          this.getArrangeexamstate()
          this.$store.commit('seterrorMessage', resp.data)
          this.errorMessage = resp.data
          console.log(resp.data[0])
          if (resp.data[0] !== -1) {
            this.cardvisable = true
            this.autotocancle()
            this.handleerrormessage()
          } else {
            this.cardvisable = false
            this.$message.success('排考成功')
            this.tags = '第一部排考'
            this.getschooltable()
          }
          this.loading = false
          // this.getschooltable()
          // alert(resp.data)
        }).catch(resp => {
          this.$message.error('服务器无响应，排考失败')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消分配'
        })
      })
    },
    Autoallocation2 () {

    },
    Autoallocation () {
      if (this.edulevel === 1 || this.edulevel === 3) {
        this.$message('您没有权限安排排考！')
      } else {
        if (this.tags === '未排考') {
          this.Autoallocation1()
        } else if (this.tags === '第一步排考') {
          this.Autoallocation2()
        }
      }
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    handleSizeChange2 (val) {
      this.pagesize = val
    },
    handleCurrentChange2 (val) {
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
        } else if (resp.data === 0) {
          this.tags = '未排考'
        }
      }).catch(resp => {
        this.tags = '未排考'
        console.log(this.tags)
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

<style scoped>
  .Allocation{
    background-color: #e4eeff;
    width: 1375px;
    height: 660px;
    margin-left: -148px;
    margin-top: 46px;
  }
  .mainarea{
    background-color: #FFFFFF;
    width: 95%;
    height: 96%;
    margin-top: 1%;
    margin-left: 2.5%;
  }
  .app{
    margin-left: 30%;
    position: fixed;
  }
  .firstarrange{
    margin-left: 10%;
  }
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
