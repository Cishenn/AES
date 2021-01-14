<!--  -->
<template>
  <div class="schooldetail">
    <div class="mainarea">
      <div class="app">
        <el-tabs v-model="activeName">
          <el-tab-pane label="考场信息" name="first">
            <div style="font-size: 20px; margin-bottom: 10px">
              学校考场信息表单
            </div>
            <el-scrollbar
              wrapClass="scrollbar-wrap"
              style="height: 450px"
              ref="scrollbarContainer"
            >
              <div>
                <el-table
                  :data="
                    roomTableData.slice(
                      (currentPage - 1) * pagesize,
                      currentPage * pagesize
                    )
                  "
                  :stripe="stripe"
                  :current-page.sync="currentPage"
                  border
                  style="width: 100%"
                >
                  <el-table-column
                    prop="floor.building"
                    label="考场所在楼"
                    width="120"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="floor.floorStep"
                    label="考场所在楼层"
                    width="120"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="examRoom.roomNum"
                    label="考场房间号"
                    width="120"
                  >
                  </el-table-column>
                  <el-table-column
                    align="center"
                    label="审核状态"
                    width="180px"
                  >
                    <el-tag :type="tags.type">
                      {{ tags.name }}
                    </el-tag>
                  </el-table-column>
                  <!-- <el-table-column label="审核" width="120px">
                        <template slot-scope="scope">
                          <el-button @click="handleDelete(scope.$index, scope.row)" type="danger" size="small" >删除</el-button>
                        </template>
                      </el-table-column> -->
                </el-table>
              </div>
            </el-scrollbar>
            <div>
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pagesize"
                class="pagination"
                layout="total, sizes, prev, pager, next, jumper"
                style="margin-top: 1%; margin-bottom: 1%"
                :total="roomTableData.length"
              >
              </el-pagination>
            </div>
            <div class="buttondiv">
              <el-button type="primary" @click="passroom">审核通过</el-button>
              <el-button type="danger" @click="unpassroom">打回重填</el-button>
            </div>
          </el-tab-pane>
          <el-tab-pane label="考务人员信息" name="second">
            <div class="search">
              <el-input
                v-model="search"
                placeholder="请输入"
                suffix-icon="el-icon-search"
                style="height: 10px; margin-top: 10px"
              ></el-input>
            </div>
            <div style="font-size: 20px; margin-bottom: 10px">
              考务人员信息表单
            </div>
            <el-scrollbar
              wrapClass="scrollbar-wrap"
              style="height: 450px"
              ref="scrollbarContainer"
            >
              <div>
                <el-table
                  class="teacherTable"
                  :data="
                    tables.slice(
                      (currentPage2 - 1) * pagesize2,
                      currentPage2 * pagesize2
                    )
                  "
                  :stripe="stripe"
                  :current-page.sync="currentPage2"
                  ref="multipleTable"
                  border
                  style="width: 100%; margin-top: 1%"
                >
                  <el-table-column type="selection" width="55" align="center" />
                  <el-table-column
                    fixed
                    prop="name"
                    label="姓名"
                    width="120"
                    align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="age"
                    label="年龄"
                    width="120"
                    align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="sex"
                    label="性别"
                    width="120"
                    align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="telephoneNumber"
                    label="电话号码"
                    width="120"
                    align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="grade"
                    label="年级"
                    width="120"
                    align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    prop="subject"
                    label="科目"
                    width="120"
                    align="center"
                  >
                  </el-table-column>
                  <el-table-column
                    align="center"
                    prop="tag"
                    label="是否具有主考资格"
                    width="120px"
                    :filters="[
                      { text: '是', value: '是' },
                      { text: '否', value: '否' },
                    ]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end"
                  >
                    <template slot-scope="scope">
                      <el-tag
                        :type="scope.row.finalRejection"
                        disable-transitions
                        >{{ scope.row.qualified }}</el-tag
                      >
                    </template>
                  </el-table-column>
                  <el-table-column
                    align="center"
                    prop="tag"
                    label="审核状态"
                    width="120px"
                    :filters="[
                      { text: '未审核', value: '未审核' },
                      { text: '已审核', value: '已审核' },
                    ]"
                    :filter-method="filterTag2"
                    filter-placement="bottom-end"
                  >
                    <template slot-scope="scope">
                      <el-tag
                        :type="scope.row.photograph"
                        disable-transitions
                        >{{ scope.row.eduExamine }}</el-tag
                      >
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-scrollbar>
            <el-pagination
              @size-change="handlesizechange2"
              @current-change="handleCurrentChange2"
              :current-page="currentPage2"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="pagesize2"
              class="pagination"
              style="margin-top: 1%; margin-left: 28%"
              layout="total, sizes, prev, pager, next, jumper"
              :total="teacherTable.length"
            >
            </el-pagination>
            <div class="buttondiv">
              <el-button type="primary" @click="passteacher"
                >审核通过</el-button
              >
              <el-button type="danger" @click="acceptspecialteacher"
                >特批主考</el-button
              >
            </div>
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
      roomstate: '',
      search: '',
      eduId: this.$store.state.eduId,
      edulevel: '',
      schoolId: this.$store.state.SubordinateschoolsId,
      activeName: 'first',
      roomTableData: [],
      teacherTable: [],
      tags: {
        type: '',
        name: ''
      },
      stripe: true,
      currentPage: 1,
      pagesize: 5,
      currentPage2: 1,
      pagesize2: 5,
      loading: true
    }
  },
  computed: {
    tables: function () {
      var search = this.search
      if (search) {
        return this.teacherTable.filter(function (dataNews) {
          return Object.keys(dataNews).some(function (key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.teacherTable
    }
  },
  created () {
    if (this.$store.state.eduId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
  },

  components: {},

  mounted () {
    this.getroomtable()
    this.getteacherTable()
    this.getedulevel()
  },

  methods: {
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
    acceptteacher () {
      // 增加主考资格
      var a = true
      for (let i = 0; i < this.$refs.multipleTable.selection.length; i++) {
        // this.$axios.post(`exStaff/eduExamine?esId=${this.$refs.multipleTable.selection[i].esId}&eduExamine=${2}`)
        this.$axios.post(`exStaff/exStaff?esId=${this.$refs.multipleTable.selection[i].esId}`)
          .then(resp => {
            this.getteacherTable()
          }).catch(resp => {
            a = false
            this.$message.error('审批失败')
          })
      }
      if (!a) {
        this.$message({
          message: '通过失败，请重新审批',
          type: 'false'
        })
      } else {
        this.$message({
          message: '审批成功！',
          type: 'success'
        })
      }
    },
    acceptspecialteacher () {
      this.$confirm('此操作将给定选中人员主考资格, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.edulevel !== 2) {
          this.$message.error('您没有权限.')
        } else {
          this.$prompt('请输入招办ID', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消'
          }).then(({ value }) => {
            if (parseInt(value) === this.eduId) {
              this.acceptteacher()
            } else {
              this.$message.error('招办ID错误！')
            }
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消设置'
            })
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消审核'
        })
      })
    },
    passteacher () {
      console.log(this.$refs.multipleTable.selection.length)
      this.$confirm('此操作将通过选中的考务人员, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var a = true
        for (let i = 0; i < this.$refs.multipleTable.selection.length; i++) {
          this.$axios.post(`exStaff/eduExamine?esId=${this.$refs.multipleTable.selection[i].esId}&eduExamine=${2}`)
            .then(resp => {
              console.log(resp)
              this.getteacherTable()
            }).catch(resp => {
              a = false
            })
        }
        if (!a) {
          this.$message({
            message: '通过失败，请重新上传',
            type: 'false'
          })
        } else {
          this.$message({
            message: '通过成功！',
            type: 'success'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消审核'
        })
      })
    },
    filterTag (value, row) {
      return row.qualified === value
    },
    filterTag2 (value, row) {
      return row.eduExamine === value
    },
    getteacherTable () {
      this.$axios.get('exStaff/getSchoolEduExamine', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        console.log(resp.data)
        this.teacherTable = resp.data.ExamStaff
        for (let i = 0; i < this.teacherTable.length; i++) {
          // 用qualified暂存状态，finalrejection暂存标签类型
          if (this.teacherTable[i].qualified === 1) {
            this.teacherTable[i].qualified = '是'
            this.teacherTable[i].finalRejection = 'success'
          } else {
            this.teacherTable[i].qualified = '否'
            this.teacherTable[i].finalRejection = 'danger'
          }
          if (this.teacherTable[i].eduExamine === 1) {
            this.teacherTable[i].photograph = 'info'
            this.teacherTable[i].eduExamine = '未审核'
          } else if (this.teacherTable[i].eduExamine === 2) {
            this.teacherTable[i].photograph = 'success'
            this.teacherTable[i].eduExamine = '已审核'
          }
        }
      })
    },
    unpassroom () {
      this.$confirm('此操作将取消通过该校所有考场, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post(`school/exRoomExamine/schoolId_exRoomExamine?schoolId=${this.schoolId}&exRoomExamine=${3}`)
          .then(resp => {
            this.$message({
              message: '打回成功!',
              type: 'success'
            })
            this.tags.type = 'danger'
            this.tags.name = '被打回'
          }).catch(resp => {
            this.$message.error('服务器无响应')
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    passroom () {
      this.$confirm('此操作将通过所有考场, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post(`school/exRoomExamine/schoolId_exRoomExamine?schoolId=${this.schoolId}&exRoomExamine=${2}`)
          .then(resp => {
            this.$message({
              message: '审核已通过!',
              type: 'success'
            })
            this.tags.type = 'success'
            this.tags.name = '已审核'
          }).catch(resp => {
            this.$message.error('服务器无响应')
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消审核'
        })
      })
    },
    getroomtable () {
      // console.log(1)
      this.$axios.get('school/exRoomExamine/schoolId', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        this.roomstate = resp.data
        if (resp.data !== 0) {
          this.$axios.get('floor_exRoom/floor_exRoom/schoolId', {
            params: {
              schoolId: this.schoolId
            }
          }).then(resp => {
            this.roomTableData = resp.data.floor_exRoom
            // console.log(resp.data.floor_exRoom)
          }).catch(resp => {
            alert('服务器无响应')
          })
        }
        if (this.roomstate === 1) {
          this.tags.type = 'info'
          this.tags.name = '未审核'
        } else if (this.roomstate === 2) {
          this.tags.type = 'success'
          this.tags.name = '已审核'
        } else if (this.roomstate === 3) {
          this.tags.type = 'danger'
          this.tags.name = '被打回'
        }
      }).catch(resp => {
        alert('服务器无响应')
      })
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handlesizechange2 (val) {
      this.pagesize2 = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    handleCurrentChange2 (val) {
      this.currentPage2 = val
    }
  }
}

</script>
<style scoped>
.schooldetail {
  background-color: #e4eeff;
  width: 89.7%;
  height: 91.5%;
  margin-left: -150px;
  margin-top: 3%;
}
.mainarea {
  background-color: #ffffff;
  width: 95%;
  height: 96%;
  margin-top: 1%;
  margin-left: 2.5%;
}
.app {
  margin-left: 5%;
  position: fixed;
}
.buttondiv {
  margin-bottom: 1%;
  display: flex;
  justify-content: center;
}
.search {
  width: 200px;
  margin-top: -1%;
  float: right;
}
</style>
