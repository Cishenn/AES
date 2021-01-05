<template>
  <div class="roomInfo">
    <el-tabs @tab-click="GOGOGO">
      <el-tab-pane label="考场录入" class="first">
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
            <el-input v-model="form.room"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addexamroom">立即创建</el-button>
            <el-button @click="deleteform">清空</el-button>
          </el-form-item>
        </el-form>
        <el-upload
          class="upload-demo"
          drag
          action="https://jsonplaceholder.typicode.com/posts/">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传excel文件</div>
        </el-upload>
      </el-tab-pane>
      <el-tab-pane label="考场信息" class="second" >
        <el-table class="table" :data="Examroomtable.slice((currentPage-1)*pagesize,currentPage*pagesize)" :stripe="stripe" :current-page.sync="currentPage">
          <el-table-column type="selection" width="55"/>
          <el-table-column label="所属楼"  width="120px">
            <template slot-scope="scope">
              {{ scope.row.floor.building }}
            </template>
          </el-table-column>
          <el-table-column label="楼层"  width="120px">
            <template slot-scope="scope">
              {{ scope.row.floor.floorStep }}
            </template>
          </el-table-column>
          <el-table-column label="房间号"  width="120px">
            <template slot-scope="scope">
              {{ scope.row.examRoom.roomNum }}
            </template>
          </el-table-column>
          <el-table-column label="操作" width="180px">
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
          <el-button class="submitBtn">上传</el-button>
        </div>
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
            <el-button @click="deleteform">取消</el-button>
          </el-form-item>
        </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
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
      Examroomtable: []
    }
  },
  mounted () {
    this.getschoolbuilding()
    this.getExamroomdata()
  },
  methods: {
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
      this.dialogTableVisible = true
      this.activebuilding = this.Examroomtable[index].floor.building
      this.activefloor = this.Examroomtable[index].floor.floorStep
      this.activeroom = this.Examroomtable[index].examRoom.roomNum
      // console.log(this.Examroomtable)
      this.activeroomId = this.Examroomtable[index].examRoom.exRoomId
      this.activefloorId = this.Examroomtable[index].floor.floorId
      this.chooseactivestep()
    },
    handleDelete (index, row) {
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
    }
  }
}
</script>

<style scoped>
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
