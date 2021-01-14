<template>
  <div style="display: flex;">
    <div class="Allocation">
      <div class="mainarea">
        <div class="app">
          <el-tabs style="margin-top: 50px; align-items: center;">
            <el-scrollbar
                wrapClass="scrollbar-wrap"
                style="height: 480px"
                ref="scrollbarContainer">
                <div>
                  <el-table class="roomtable" v-loading="loading"
                  :data="schoolTabledata.slice((currentPage - 1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage"
                  border
                  header-cell-class-name="tableStyle"
                  height="350"
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
                </div>
                </el-scrollbar>
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5,10,15,20]"
                :page-size="pagesize"
                class="pagination"
                layout="total, sizes, prev, pager, next, jumper"
                style="margin-top: 1%;"
                :total="schoolTabledata.length">
              </el-pagination>
            <el-dialog title="第一次排考" :visible.sync="firstarrange" width="60%" style="margin-left: 15%;">
              <div>
                <a
                  v-if="Fjiankaolianjie !== ''"
                  @click="downloadfirstjiankao"
                  style="color: blue"
                  class="download"
                  :download="Fjiankaolianjie1"
                  :href="Fjiankaolianjie"
                  title="下载"
                >
                导出{{schoolname}}第一次排考监考组excel表格
                </a>
                <a
                  v-if="Fjiankaolianjie === ''"
                  style="color: red"
                >
                请稍等···监考组导出文件正在准备中
                </a>
              </div>
              <div style="margin-top: 0">
                <a
                  v-if="Fxunkaolianjie !== ''"
                  @click="downloadfirstxunkao"
                  style="color: blue"
                  class="download"
                  :download="Fxunkaolianjie1"
                  :href="Fxunkaolianjie"
                  title="下载"
                >
                导出{{schoolname}}第一次排考巡考组excel表格
                </a>
                <a
                  v-if="Fxunkaolianjie === ''"
                  style="color: red"
                >
                请稍等···巡考组导出文件正在准备中
                </a>
              </div>
              <el-tabs>
                <el-tab-pane>
                  <span slot="label"><i class="el-icon-price-tag"></i> 考场安排</span>
                  <el-scrollbar
                      wrapClass="scrollbar-wrap"
                      style="height:350px"
                      ref="scrollbarContainer">
                      <div>
                        <el-table
                          :data="roomTable.slice((currentPage2 - 1)*pagesize2,currentPage2*pagesize2)" :stripe="stripe" :current-page.sync="pagesize2"
                          style="width: 100%"
                          height="350">
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
                      </div>
                      </el-scrollbar>
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
                  <el-scrollbar
                      wrapClass="scrollbar-wrap"
                      style="height:360px"
                      ref="scrollbarContainer">
                      <div>
                        <el-table
                          :data="invigilatorTable.slice((currentPage3 - 1)*pagesize3,currentPage*pagesize3)" :stripe="stripe" :current-page.sync="pagesize3"
                          style="width: 100%"
                          height="360">
                          <el-table-column
                            prop="invigilatorGroupId"
                            label="监考组编号"
                            width="180">
                          </el-table-column>
                          <el-table-column
                            prop="schoolName"
                            label="监考考点"
                            width="180">
                          </el-table-column>
                          <el-table-column
                            prop="examinerName"
                            label="主考人"
                            width="180">
                          </el-table-column>
                          <el-table-column
                            prop="firstInvigilatorName"
                            label="监考人1"
                            width="180">
                          </el-table-column>
                          <el-table-column
                            prop="secondInvigilatorName"
                            label="监考人2"
                            width="180">
                          </el-table-column>
                        </el-table>
                      </div>
                      </el-scrollbar>
                  <el-pagination
                    @size-change="handleSizeChange3"
                    @current-change="handleCurrentChange3"
                    :current-page="currentPage3"
                    :page-sizes="[5,10,15,20]"
                    :page-size="pagesize3"
                    class="pagination"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="invigilatorTable.length">
                  </el-pagination>
                </el-tab-pane>
                <el-tab-pane>
                  <span slot="label"><i class="el-icon-s-custom"></i> 巡考组安排</span>
                  <el-scrollbar
                      wrapClass="scrollbar-wrap"
                      style="height:360px"
                      ref="scrollbarContainer">
                      <div>
                        <el-table
                          :data="inspectionTable.slice((currentPage4 - 1)*pagesize4,currentPage4*pagesize4)" :stripe="stripe" :current-page.sync="pagesize4"
                          style="width: 100%"
                          height="360">
                          <el-table-column
                            prop="inspectionTeamId"
                            label="巡考组编号"
                            width="180">
                          </el-table-column>
                          <el-table-column
                            prop="schoolName"
                            label="巡考考点"
                            width="180">
                          </el-table-column>
                          <el-table-column
                            prop="firstInspectionPersonName"
                            label="巡考人1"
                            width="180">
                          </el-table-column>
                          <el-table-column
                            prop="secondInspectionPersonName"
                            label="巡考人2"
                            width="180">
                          </el-table-column>
                        </el-table>
                      </div>
                      </el-scrollbar>
                  <el-pagination
                    @size-change="handleSizeChange4"
                    @current-change="handleCurrentChange4"
                    :current-page="currentPage4"
                    :page-sizes="[5,10,15,20]"
                    :page-size="pagesize4"
                    class="pagination"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="inspectionTable.length">
                  </el-pagination>
                </el-tab-pane>
              </el-tabs>
            </el-dialog>
            <el-dialog title="第二次排考" :visible.sync="secondarrange" width="60%" style="margin-left: 20%;">
              <div>
                <a
                  v-if="jiankaolianjie !== ''"
                  @click="downloadsecondjiankao"
                  style="color: blue"
                  class="download"
                  :download="jiankaolianjie1"
                  :href="jiankaolianjie"
                  title="下载"
                >
                导出{{schoolname}}第二次排考监考组excel表格
                </a>
                <a
                  v-if="jiankaolianjie === ''"
                  style="color: red"
                >
                请稍等···监考组导出文件正在准备中
                </a>
              </div>
              <div style="margin-top: 0">
                <a
                  v-if="xunkaolianjie !== ''"
                  @click="downloadsecondxunkao"
                  style="color: blue"
                  class="download"
                  :download="xunkaolianjie1"
                  :href="xunkaolianjie"
                  title="下载"
                >
                导出{{schoolname}}第二次排考巡考组excel表格
                </a>
                <a
                  v-if="xunkaolianjie === ''"
                  style="color: red"
                >
                请稍等···巡考组导出文件正在准备中
                </a>
              </div>
              <el-tabs>
                <el-tab-pane>
                  <span slot="label"><i class="el-icon-price-tag"></i> 考场安排</span>
                  <el-scrollbar
                      wrapClass="scrollbar-wrap"
                      style="height:350px"
                      ref="scrollbarContainer">
                      <div>
                        <el-table class="roomtable2"
                          :data="RoomTable2.slice((currentPage7 - 1)*pagesize7,currentPage7*pagesize7)" :stripe="stripe" :current-page.sync="pagesize7"
                          style="width: 100%"
                          height="350">
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
                      </div>
                  </el-scrollbar>
                  <el-pagination
                    @size-change="handleSizeChange7"
                    @current-change="handleCurrentChange7"
                    :current-page="currentPage7"
                    :page-sizes="[5,10,15,20]"
                    :page-size="pagesize7"
                    class="pagination"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="roomTable2.length">
                  </el-pagination>
                </el-tab-pane>
                <el-tab-pane>
                  <span slot="label"><i class="el-icon-user"></i> 监考组安排</span>
                  <el-scrollbar
                      wrapClass="scrollbar-wrap"
                      style="height:350px"
                      ref="scrollbarContainer">
                      <div>
                        <el-table
                        v-loading="jiankaoloading"
                          :data="InvigilatorTable2.slice((currentPage5 - 1)*pagesize5,currentPage5*pagesize5)" :stripe="stripe" :current-page.sync="pagesize5"
                          style="width: 100%"
                          height="350">
                          <el-table-column
                            label="考试科目"
                            width="80">
                            <template slot-scope="scope">
                              {{ scope.row.sessions === 1 ? '语文' : (scope.row.sessions === 2? '数学' : (scope.row.sessions === 3 ? '英语' : '综合')) }}
                            </template>
                          </el-table-column>
                          <el-table-column
                            prop="schoolName"
                            label="监考考点"
                            width="130">
                          </el-table-column>
                          <el-table-column
                            prop="building"
                            label="监考楼"
                            width="130">
                          </el-table-column>
                          <el-table-column
                            prop="floorStep"
                            label="监考楼层"
                            width="130">
                          </el-table-column>
                          <el-table-column
                            prop="roomNum"
                            label="房间号"
                            width="130">
                          </el-table-column>
                          <el-table-column
                            prop="examinerName"
                            label="主考人"
                            width="80">
                          </el-table-column>
                          <el-table-column
                            prop="firstInvigilatorName"
                            label="监考人1"
                            width="80">
                          </el-table-column>
                          <el-table-column
                            prop="secondInvigilatorName"
                            label="监考人2"
                            width="80">
                          </el-table-column>
                        </el-table>
                    </div>
                  </el-scrollbar>
                  <el-pagination
                    @size-change="handleSizeChange5"
                    @current-change="handleCurrentChange5"
                    :current-page="currentPage5"
                    :page-sizes="[5,10,15,20]"
                    :page-size="pagesize5"
                    class="pagination"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="invigilatorTable2.length">
                  </el-pagination>
                  <div class="search">
                      <el-input v-model="search" placeholder="请输入"
                      suffix-icon="el-icon-search"></el-input>
                  </div>
                </el-tab-pane>
                <el-tab-pane>
                  <span slot="label"><i class="el-icon-s-custom"></i> 巡考组安排</span>
                  <el-scrollbar
                      wrapClass="scrollbar-wrap"
                      style="height:350px"
                      ref="scrollbarContainer">
                      <div>
                        <el-table
                          :data="InspectionTable2.slice((currentPage6 - 1)*pagesize6,currentPage6*pagesize6)" :stripe="stripe" :current-page.sync="pagesize6"
                          style="width: 100%"
                          height="350">
                          <el-table-column
                            label="考试科目"
                            width="80">
                            <template slot-scope="scope">
                              {{ scope.row.sessions === 1 ? '语文' : (scope.row.sessions === 2? '数学' : (scope.row.sessions === 3 ? '英语' : '综合')) }}
                            </template>
                          </el-table-column>
                          <el-table-column
                            prop="inspectionTeamId"
                            label="巡考组编号"
                            width="100">
                          </el-table-column>
                          <el-table-column
                            prop="schoolName"
                            label="巡考考点"
                            width="120">
                          </el-table-column>
                          <el-table-column
                            prop="floorStep"
                            label="巡考楼层"
                            width="100">
                          </el-table-column>
                          <el-table-column
                            prop="firstInspectionPersonName"
                            label="巡考人1"
                            width="100">
                          </el-table-column>
                          <el-table-column
                            prop="secondInspectionPersonName"
                            label="巡考人2"
                            width="100">
                          </el-table-column>
                        </el-table>
                      </div>
                      </el-scrollbar>
                  <el-pagination
                    @size-change="handleSizeChange6"
                    @current-change="handleCurrentChange6"
                    :current-page="currentPage6"
                    :page-sizes="[1,10,15,20]"
                    :page-size="pagesize6"
                    class="pagination"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="inspectionTable2.length">
                  </el-pagination>
                  <div class="search">
                      <el-input v-model="search" placeholder="请输入"
                      suffix-icon="el-icon-search"></el-input>
                  </div>
                </el-tab-pane>
              </el-tabs>
            </el-dialog>
            </el-tabs>
          <div class="submit" style="display: flex;justify-content: center; margin-top: 1.5%;">
            <el-button size="medium" @click="Autoallocation" type="success">自动分配</el-button>
            <el-button size="medium" @click="Cancelarrange" type="danger">重置排考</el-button>
            <el-button size="medium" @click="Closeexam" type="primary">考试结束</el-button>
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
          <p>缺少考场数目: {{errorMessage[1]}}</p>
        </div>
        <div v-if="errorMessage[0] === 9" style="display:block;">
          <h style="color:red">上次排考学校主考人数不足</h>
          <p>学校名称： {{errorMessage[1]}}</p>
          <p>拥有的主考人数：  {{errorMessage[2]}}</p>
          <p>需要的主考人数：  {{errorMessage[3]}}</p>
          <p>缺少的主考人数：  {{errorMessage[3]-errorMessage[2]}}</p>
        </div>
        <div v-if="errorMessage[0] === 5" style="display:block;">
          <h style="color:red">上次排考男女人数不平衡，男或女比例要在1/3以上</h>
          <p>拥有的男性考务人员数：  {{errorMessage[1]}}</p>
          <p>拥有的女性考务人员数：  {{errorMessage[2]}}</p>
          <p v-if="errorMessage[1]>errorMessage[2]">至少缺少的女性考务人员数目:    {{errorMessage[1]/2-errorMessage[2]}}</p>
          <p v-if="errorMessage[2]>errorMessage[1]">至少缺少的男性考务人员数目:    {{errorMessage[2]/2-errorMessage[1]}}</p>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      schoolname: '',
      Fjiankaolianjie: '',
      Fjiankaolianjie1: '',
      Fxunkaolianjie: '',
      Fxunkaolianjei1: '',
      jiankaolianjie: '',
      jiankaolianjie1: '',
      xunkaolianjie: '',
      xunkaolianjie1: '',
      cardvisable: true,
      firstarrange: false,
      secondarrange: false,
      eduId: this.$store.state.eduId,
      tags: '',
      search: '',
      arrangeexamstate: '',
      schoolTabledata: [],
      invigilatorTable: [],
      inspectionTable: [],
      roomTable2: [],
      invigilatorTable2: [],
      inspectionTable2: [],
      loading: true,
      jiankaoloading: true,
      stripe: true,
      currentPage: 1,
      pagesize: 10,
      currentPage2: 1,
      pagesize2: 10,
      currentPage3: 1,
      pagesize3: 10,
      currentPage4: 1,
      pagesize4: 10,
      currentPage5: 1,
      pagesize5: 10,
      currentPage6: 1,
      pagesize6: 10,
      currentPage7: 1,
      pagesize7: 10,
      edulevel: Number,
      roomTable: [],
      errorMessage: this.$store.state.errorMessage
    }
  },
  computed: {
    RoomTable2: function () {
      var search = this.search
      if (search) {
        return this.roomTable2.filter(function (dataNews) {
          return Object.keys(dataNews).some(function (key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.roomTable2
    },
    InvigilatorTable2: function () {
      var search = this.search
      if (search) {
        return this.invigilatorTable2.filter(function (dataNews) {
          return Object.keys(dataNews).some(function (key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.invigilatorTable2
    },
    InspectionTable2: function () {
      var search = this.search
      if (search) {
        return this.inspectionTable2.filter(function (dataNews) {
          return Object.keys(dataNews).some(function (key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.inspectionTable2
    }
  },
  created () {
    if (this.$store.state.eduId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
    if (this.tags !== '未排考') {
      this.cardvisable = false
    } else {
      if (this.errorMessage === null) {
        this.cardvisable = false
      }
    }
    this.getArrangeexamstate()
    this.getedulevel()
    this.getschooltable()
  },
  methods: {
    clearlianjie () {
      this.Fxunkaolianjie1 = ''
      this.Fxunkaolianjie = ''
      this.Fjiankaolianjie1 = ''
      this.Fjiankaolianjie = ''
      this.jiankaolianjie1 = ''
      this.jiankaolianjie = ''
      this.xunkaolianjie1 = ''
      this.xunkaolianjie = ''
    },
    downloadfirstjiankao (schoolId) {
      this.$axios.get('/examStaff_ig_igA/InvigilatorGroupExcel', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        this.Fjiankaolianjie = 'http://localhost:8080/file/' + resp.data
        this.Fjiankaolianjie1 = resp.data
      })
    },
    downloadfirstxunkao (schoolId) {
      this.$axios.get('/examStaff_it_itA/inspectionTeamsExcel', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        this.Fxunkaolianjie = 'http://localhost:8080/file/' + resp.data
        this.Fxunkaolianjie1 = resp.data
      })
    },
    downloadsecondjiankao (schoolId) {
      // console.log(schoolId)
      this.$axios.get('/examStaff_ig_igA/InvigilatorGroupAndPosExcel', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        this.jiankaolianjie = 'http://localhost:8080/file/' + resp.data
        this.jiankaolianjie1 = resp.data
      })
    },
    downloadsecondxunkao (schoolId) {
      this.$axios.get('/examStaff_it_itA/inspectionTeamsAndPosExcel', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        this.xunkaolianjie = 'http://localhost:8080/file/' + resp.data
        this.xunkaolianjie1 = resp.data
      })
    },
    handleerrormessage () {
      // 处理第一次自动化分配出错信息
      this.$message.error('排考失败，请查看错误信息!')
    },
    autotocancle () {
      // 重置考场分配
      this.$axios.get('AutoDeploy/clearStepOne', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.loading = false
        this.clearlianjie()
        this.getschooltable()
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
        this.clearlianjie()
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
      // 获得第一次排考的考场
      this.$axios.get('floor_exRoom/ArrangeFloor_exRoom/schoolId', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.roomTable = resp.data.floor_exRoom
      })
    },
    getfirstInvigilator (schoolId) {
      // 获得第一次排考监考组
      this.$axios.get('examStaff_ig_igA/InvigilatorGroup', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        // console.log(resp.data.invigilatorGroup)
        this.invigilatorTable = resp.data.invigilatorGroup
      }).catch(resp => {
        console.log(resp)
      })
    },
    getfirstinspection (schoolId) {
      // 获得第一次排考巡考组
      this.$axios.get('examStaff_it_itA/inspectionTeams', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        console.log(resp.data.inspectionTeams)
        this.inspectionTable = resp.data.inspectionTeams
      })
    },
    getsecondexamRoom (schoolId) {
      // 获得第二次排考的考场
      this.$axios.get('floor_exRoom/ArrangeFloor_exRoom/schoolId', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.roomTable2 = resp.data.floor_exRoom
      })
    },
    getsecondInvigilator (schoolId) {
      // 获得第二次排考监考组
      this.$axios.get('examStaff_ig_igA/InvigilatorGroupAndPos', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        this.jiankaoloading = false
        // console.log(resp.data.invigilatorGroupAndPos)
        this.invigilatorTable2 = resp.data.invigilatorGroupAndPos
      }).catch(resp => {
        console.log(resp)
      })
    },
    getsecondinspection (schoolId) {
      // 获得第二次排考巡考组
      this.$axios.get('examStaff_it_itA/inspectionTeamsAndPos', {
        params: {
          schoolId: schoolId
        }
      }).then(resp => {
        console.log(resp.data)
        this.inspectionTable2 = resp.data.inspectionTeamsAndPos
      })
    },
    getschooltable () {
      // 获得已排考的考场表
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
      this.clearlianjie()
      // console.log(row)
      if (this.tags === '第一步排考') {
        this.firstarrange = true
        this.schoolname = row.schoolName
        this.getfirstexamRoom(row.schoolId)
        this.getfirstInvigilator(row.schoolId)
        this.getfirstinspection(row.schoolId)
        this.downloadfirstjiankao(row.schoolId)
        this.downloadfirstxunkao(row.schoolId)
      } else if (this.tags === '第二步排考') {
        this.secondarrange = true
        this.schoolname = row.schoolName
        this.getsecondexamRoom(row.schoolId)
        this.getsecondInvigilator(row.schoolId)
        this.getsecondinspection(row.schoolId)
        this.downloadsecondjiankao(row.schoolId)
        this.downloadsecondxunkao(row.schoolId)
      }
    },
    Autoallocation1 () {
      // 第一次自动分配
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
            this.autotocancle() // 重置排考
            this.handleerrormessage()
          } else {
            this.cardvisable = false
            this.$message.success('排考成功')
            this.$store.commit('clearerror')
            this.tags = '第一步排考'
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
      // 第二次自动分配
      this.$confirm('当前处于自动化排考第一步,此操作将把监考组分入考场（应该在考试当天分配）, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        this.$axios.get('AutoDeploy/stepTwo', {
          params: {
            eduId: this.eduId
          }
        }).then(resp => {
          this.loading = false
          this.getArrangeexamstate()
          if (resp.data === 0) {
            this.$message.success('第二次排考成功（已经把监考组分配到考场）!')
          }
        }).catch(resp => {
          this.loading = false
          this.$message.error('第二次排考不成功，请尝试重置排考或者联系管理员')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消分配'
        })
      })
    },
    Autoallocation () {
      if (this.edulevel === 1 || this.edulevel === 3) {
        this.$message('您没有权限安排排考！')
      } else {
        if (this.tags === '未排考') {
          this.Autoallocation1()
        } else if (this.tags === '第一步排考') {
          this.Autoallocation2()
        } else if (this.tags === '第二步排考') {
          this.$confirm('此步操作将清楚第二次排考并且重新第二次排考, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.loading = true
            this.$axios.get('AutoDeploy/stepTwo', {
              params: {
                eduId: this.eduId
              }
            }).then(resp => {
              this.loading = false
              this.getArrangeexamstate()
              if (resp.data === 0) {
                this.$message.success('第二次排考成功（已经把监考组分配到考场）!')
              }
            }).catch(resp => {
              this.loading = false
              this.$message.error('第二次排考不成功，请尝试重置排考或者联系管理员')
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消分配'
            })
          })
        }
      }
    },
    examjieshula () {
      this.$prompt('请输入此此考试年份(只能输入数字，例如:2018)', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^(?:19|20)[0-9][0-9]$/,
        inputErrorMessage: '年份格式不正确'
      }).then(({ value }) => {
        this.loading = true
        if (typeof (parseInt(value)) === 'number') {
          // 结束考试
          this.$axios.get('AutoDeploy/end', {
            params: {
              eduId: this.eduId,
              year: value
            }
          }).then(resp => {
            if (resp.data === 1) {
              this.$message.success('考试结束！')
              this.autotocancle()
            }
          }).catch(resp => {
            this.loading = false
            this.$message.error('结束失败,请尝试重新结束或联系管理员')
          })
        } else {
          this.$message.error('请输入数字！')
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消结束考试'
        })
      })
    },
    Closeexam () {
      this.$confirm('本次考试已结束，进行此操作将结束本次操作（添加考试信息进入历史记录）, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.edulevel === 1 || this.edulevel === 3) {
          this.$message('您没有权限取消考试！')
        } else {
          if (this.tags !== '第二步排考') {
            this.$message('当前并未排考，并进入第二次排考！')
          } else {
            this.$confirm('确定结束考试?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$prompt('请输入招办ID', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
              }).then(({ value }) => {
                if (parseInt(value) === this.eduId) {
                  // 结束考试
                  this.examjieshula()
                } else {
                  this.$message.error('招办ID错误！')
                }
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '取消结束考试'
                })
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消结束考试'
              })
            })
          }
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消分配'
        })
      })
    },
    getArrangeexamstate () {
      // 获得自动排考状态
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
        // this.tags = '未排考'
        console.log(this.tags)
      })
    },
    getedulevel () {
      // 获得招办等级
      this.$axios.get('enrollmentDepartment/getOne', {
        params: {
          eduId: this.eduId
        }
      }).then(resp => {
        this.edulevel = resp.data.eduLevel
        // alert(this.edulevel)
      })
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    handleSizeChange2 (val) {
      this.pagesize2 = val
    },
    handleCurrentChange2 (val) {
      this.currentPage2 = val
    },
    handleSizeChange3 (val) {
      this.pagesize3 = val
    },
    handleCurrentChange3 (val) {
      this.currentPage3 = val
    },
    handleSizeChange4 (val) {
      this.pagesize4 = val
    },
    handleCurrentChange4 (val) {
      this.currentPage4 = val
    },
    handleSizeChange5 (val) {
      this.pagesize5 = val
    },
    handleCurrentChange5 (val) {
      this.currentPage5 = val
    },
    handleSizeChange6 (val) {
      this.pagesize6 = val
    },
    handleCurrentChange6 (val) {
      this.currentPage6 = val
    },
    handleSizeChange7 (val) {
      this.pagesize7 = val
    },
    handleCurrentChange7 (val) {
      this.currentPage7 = val
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
  .search{
    width: 200px;
    margin-top: -4%;
    float: right;
  }
 .input.el-input__inner{
    height: 20px;
  }
</style>
