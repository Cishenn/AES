<template>
  <div class="roomInfo">
    <div class="mainarea">
      <div class="app">
        <el-tabs @tab-click="GOGOGO">
          <el-tab-pane label="考场录入" class="first">
                     <span slot="label">
                         <i class="el-icon-s-grid" style="margin:0 303px 0 80px" align="center">
                             <span style="padding-left: 153px">考场录入</span>
                         </i>
                     </span>
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="所属楼号">
                <el-select v-model="form.building" placeholder="请选择楼号" style="width: 300px" @change="choosestep">
                  <el-option
                  v-for="item in form.buildingtable"
                  :key="item"
                  :value="item"
                  :lable="item"
                  >
                  </el-option>
                </el-select>
                <!-- <el-input v-model="form.building"></el-input> -->
              </el-form-item>
              <el-form-item label="所属楼层">
                <el-select v-model="form.floor" clearable placeholder="请选择楼层" style="width: 300px" @change="getfloorId">
                  <el-option
                  v-for="item in form.floortable"
                  :key="item.floorId"
                  :value="item.floorId"
                  :label="item.floorStep"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="房间号">
                <el-input v-model="form.room" style="width: 300px;"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="addexamroom">立即创建</el-button>
                <el-button @click="deleteform">清空</el-button>
              </el-form-item>
            </el-form>
            <el-upload
              class="upload-demo"
              drag
              action="http://localhost:8080/floor_exRoom/upload"
              ref="upload"
              name="file"
              :limit="1"
              :auto-upload="true"
              :accept="acceptFileType"
              :on-exceed="handleExceed"
              :file-list="fileList"
              :before-upload="beforeUpload"
              :on-preview="handlePreview"
              :on-remove="handleRemove">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">只能上传excel文件</div>
            </el-upload>
            <el-button type="primary" @click="readFile">数据录入</el-button>
          </el-tab-pane>
          <el-tab-pane label="考场信息" class="second" align="center">
            <span slot="label">
                <i class="el-icon-s-grid" style="margin:0 304px 0 80px">
                    <span style="padding-left: 154px">考场信息</span>
                </i>
            </span>

                <el-scrollbar
                    wrapClass="scrollbar-wrap"
                    :style="{height: scrollHeight}"
                    ref="scrollbarContainer">
                    <div>
                      <el-table border class="table" :data="Examroomtable.slice((currentPage-1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage">
                        <!-- <el-table-column type="selection" width="55"/> -->
                        <el-table-column align="center" label="所属楼"  width="120px">
                          <template slot-scope="scope">
                            {{ scope.row.floor.building }}
                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="楼层"  width="120px">
                          <template slot-scope="scope">
                            {{ scope.row.floor.floorStep }}
                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="房间号"  width="120px">
                          <template slot-scope="scope">
                            {{ scope.row.examRoom.roomNum }}
                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="操作" width="180px">
                          <template slot-scope="scope">
                            <el-button
                              size="mini"
                              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                            <el-button
                              size="mini"
                              type="danger"
                              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="审核状态" width="180px">
                          <el-tag
                            :type="tags.type">
                            {{tags.name}}
                          </el-tag>
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
                          :total="Examroomtable.length">
                        </el-pagination>
                      </div>
                      <div>
                        <el-button class="submitBtn" @click="submitBtn">上传</el-button>
                      </div>
                    </div>
                </el-scrollbar>
          </el-tab-pane>
        </el-tabs>
        <el-dialog title="修改考场" :visible.sync="dialogTableVisible">
          <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="所属楼号">
                <el-select v-model="activebuilding" placeholder="请选择楼号" style="width: 300px" @change="chooseactivestep2">
                  <el-option
                  v-for="item in form.buildingtable"
                  :key="item"
                  :value="item"
                  :lable="item"
                  >
                  </el-option>
                </el-select>
                <!-- <el-input v-model="form.building"></el-input> -->
              </el-form-item>
              <el-form-item label="所属楼层">
                <el-select v-model="activefloor" clearable placeholder="请选择楼层" style="width: 300px" @change="getfloorId2">
                  <el-option
                  v-for="item in activefloortavle"
                  :key="item.floorId"
                  :value="item.floorId"
                  :label="item.floorStep"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="房间号" >
                <el-input v-model="activeroom" style="width: 300px"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="modifyexamroom">修改</el-button>
                <!-- <el-button @click="deleteform">取消</el-button> -->
              </el-form-item>
            </el-form>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      scrollHeight: '580px',
      operate: 0,
      tags: {
        name: '未提交',
        type: 'danger'
      },
      dialogTableVisible: false,
      schoolId: this.$store.state.schoolId,
      stripe: true,
      currentPage: 1,
      pagesize: 10,
      total: 0,
      activebuilding: '',
      activefloor: '',
      activefloorId: '',
      activeroom: '',
      activefloortavle: [],
      activeroomId: '',
      form: {
        building: '',
        floor: '',
        floorId: '',
        room: '',
        buildingtable: [],
        floortable: [{
          floorStep: '请先选择楼号'
        }]
      },
      Examroomtable: [],
      acceptFileType: '.xls',
      fileList: [],
      uploadLoading: false,
      downLoadLoading: ''
    }
  },
  mounted () {
    this.getschoolbuilding()
    this.getExamroomdata()
  },
  created () {
    if (this.$store.state.schoolId === '') {
      // alert('不要随便乱进哦!')
      this.$router.push('/login')
    }
    this.getapprovalstate()
  },
  methods: {
    getapprovalstate () {
      this.$axios.get('school/exRoomExamine/schoolId', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        this.operate = resp.data
        console.log(resp.data)
        if (resp.data === 1) {
          this.tags.name = '已提交，未审核'
          this.tags.type = 'info'
        } else if (resp.data === 2) {
          this.tags.name = '已审核'
          this.tags.type = 'success'
        } else if (resp.data === 3) {
          this.tags.name = '被打回'
          this.tags.type = 'danger'
        }
      }).catch()
      // setTimeout(this.getapprovalstate, 1000)
    },
    updateroom () {
      this.$axios.post(`school/exRoomExamine/schoolId?schoolId=${this.schoolId}`)
        .then(resp => {
          this.tags.name = '已提交，未审核'
          this.tags.type = 'info'
          this.$message({
            message: '提交成功，在审核前您有权修改考场。',
            type: 'success'
          })
        }).catch(resp => {
          this.$message({
            message: '提交失败！',
            type: 'false'
          })
        })
    },
    submitBtn () {
      if (this.operate === 2) {
        this.$message({
          message: '当前考场已被招办审核，不能再上传考场!',
          type: 'warning'
        })
      } else {
        this.$confirm('此操作将提交所有考场给招办, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.updateroom()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交'
          })
        })
        this.getapprovalstate()
      }
    },
    modifyexamroom () {
      this.$confirm('此操作将修改考场信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.activefloor === '' || this.activebuilding === '' || this.activeroom === '') {
          this.$message({
            message: '请输入完整的考场信息',
            type: 'warning'
          })
        } else {
          this.$axios.post('exRoom/exRoom/exRoom', {
            exRoomId: this.activeroomId,
            schoolId: this.schoolId,
            floorId: this.activefloorId,
            roomNum: this.activeroom
          }
          )
            .then(resp => {
              console.log(this.activefloorId)
              this.getExamroomdata()
              this.dialogTableVisible = false
              this.$message({
                message: '修改成功',
                type: 'success'
              })
            }).catch(resp => {
              alert('修改失败')
            })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        })
      })
    },
    chooseactivestep2 () {
      // 为了实现点击之后楼层变化
      this.activefloor = ''
      this.$axios.get('floor/floor/building_schoolId', {
        params: {
          building: this.activebuilding,
          schoolId: this.schoolId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.activefloortavle = resp.data.Floor
        // console.log(this.form.floortable)
      })
    },
    chooseactivestep () {
      this.$axios.get('floor/floor/building_schoolId', {
        params: {
          building: this.activebuilding,
          schoolId: this.schoolId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.activefloortavle = resp.data.Floor
        // console.log(this.form.floortable)
      })
    },
    handleEdit (index, row) {
      if (this.operate === 2) {
        this.$message({
          message: '当前考场已被招办审核，不能再编辑考场!',
          type: 'warning'
        })
      } else {
        this.dialogTableVisible = true
        this.activebuilding = this.Examroomtable[index].floor.building
        this.activefloor = this.Examroomtable[index].floor.floorStep
        this.activeroom = this.Examroomtable[index].examRoom.roomNum
        // console.log(this.Examroomtable)
        this.activeroomId = this.Examroomtable[index].examRoom.exRoomId
        this.activefloorId = this.Examroomtable[index].floor.floorId
        this.chooseactivestep()
      }
    },
    handleDelete (index, row) {
      if (this.operate === 2) {
        this.$message({
          message: '当前考场已被招办审核，不能再修改或添加考场!',
          type: 'warning'
        })
      } else {
        this.$confirm('此操作将永久删除该考场, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.delete('exRoom/exRoom/exRoomId', {
            params: {
              exRoomId: row.examRoom.exRoomId
            }
          }).then(resp => {
            this.getExamroomdata()
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          }).catch(resp => {
            console.log(resp)
            this.$message({
              message: '删除失败',
              type: 'warning'
            })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }
    },
    GOGOGO () {
      this.getExamroomdata()
      this.deleteform()
    },
    handleSizeChange (val) {
      this.pagesize = val
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    getExamroomdata () {
      this.$axios.get('floor_exRoom/floor_exRoom/schoolId', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        this.Examroomtable = resp.data.floor_exRoom
      })
    },
    addexamroom () {
      if (this.operate === 2) {
        this.$message({
          message: '当前考场已被招办审核，不能再修改或添加考场!',
          type: 'warning'
        })
      } else {
        if (this.form.building === '' || this.form.floor === '' || this.form.room === '') {
          this.$message({
            message: '请输入完整的考场信息',
            type: 'warning'
          })
        } else {
          this.$axios.post(`exRoom/exRoom?schoolId=${this.schoolId}&floorId=${this.form.floorId}&roomNum=${this.form.room}`)
            .then(resp => {
              this.cleardata()
              this.$message({
                message: '添加成功',
                type: 'success'
              })
            }).catch(resp => {
              alert('添加失败')
            })
        }
        this.getExamroomdata()
      }
    },
    cleardata () {
      this.form.building = ''
      this.form.floor = ''
      this.form.room = ''
    },
    getfloorId2 (selectvalue) {
      this.activefloorId = selectvalue
      this.activeroom = ''
    },
    getfloorId (selectvalue) {
      this.form.floorId = selectvalue
      this.form.room = ''
      // console.log(this.form.floorId)
    },
    getschoolbuilding () {
      this.$axios.get('floor/building/schoolId', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        this.form.buildingtable = resp.data.building
      })
    },
    choosestep () {
      this.form.floor = ''
      this.form.room = ''
      this.$axios.get('floor/floor/building_schoolId', {
        params: {
          building: this.form.building,
          schoolId: this.schoolId
        }
      }).then(resp => {
        console.log(resp.data)
        this.form.floortable = resp.data.Floor
        console.log(this.form.floortable)
      })
    },
    deleteform () {
      this.form.building = ''
      this.form.floor = ''
      this.form.room = ''
    },
    beforeUpload (file) {
      var that = this
      // 文件类型
      var fileName = file.name.substring(file.name.lastIndexOf('.') + 1)
      if (fileName !== 'xls') {
        that.uploadLoading = false
        that.$message({
          type: 'error',
          showClose: true,
          duration: 3000,
          message: '文件类型不是excel文件!'
        })
        return false
      }
      // 读取文件大小
      var fileSize = file.size
      if (fileSize > 1048576) {
        that.uploadLoading = false
        that.$message({
          type: 'error',
          showClose: true,
          duration: 3000,
          message: '文件大于1M!'
        })
        return false
      }
      return true
    },
    handleExceed (files, fileList) {
      this.$message.warning('只能选择1个文件!')
    },
    handleRemove (file, fileList) {
      // console.log(file,fileList);
    },
    handlePreview (file) {
      // console.log(file);
    },
    readFile () {
      this.$axios.get('/floor_exRoom/floor_exRoom/crbe', {
        params: {
          schoolId: this.schoolId
        }
      }).then(resp => {
        if (resp.data === -999) {
          this.$message({
            message: '数据读取成功',
            type: 'success'
          })
          this.$refs.upload.clearFiles()
        } else if (resp.data === -1) {
          this.$message.warning('表格为空!')
          this.$refs.upload.clearFiles()
        } else {
          this.$message.error('第' + resp.data + '行数据出错')
          this.$refs.upload.clearFiles()
        }
      })
    }
  }
}
</script>

<style scoped>
  .roomInfo{
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
    margin-left: 1%;
    position: fixed;
  }
.scrollbar-wrap.el-scrollbar__wrap {
    overflow:hidden;
}
.scrollbar-wrap .el-scrollbar{
  overflow-x: hidden;
}
.el-scrollbar__wrap{
  overflow-x: hidden;
}
/* .second{
    margin-left: 25%;
  } */
  .first{
    margin-left: 35%;
    margin-top: 5%;
  }
.table {
  margin-bottom: 2%;
}

.pagination {
  width: 400px;
  margin-top: 3%;
}

.submitBtn {
  width: 140px;
  display: block;
  margin-top: 1%;
}
</style>

<style>
  .scrollbar-wrap.el-scrollbar__wrap {
      overflow-x:hidden;
  }
</style>
