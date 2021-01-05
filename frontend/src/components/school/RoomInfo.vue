<template>
  <div class="roomInfo">
    <el-tabs>
      <el-tab-pane label="考场录入" class="first">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="所属楼号">
            <el-select v-model="building" placeholder="请选择楼号" style="width: 300px" @change="choosestep">
              <el-option
              v-for="item in form"
              :key="item.building"
              :value="item.building"
              :lable="item.building"
              >
              </el-option>
            </el-select>
            <!-- <el-input v-model="form.building"></el-input> -->
          </el-form-item>
          <el-form-item label="所属楼层">
            <el-select v-model="floor" placeholder="请选择楼层" style="width: 300px" >
              <el-option
              v-for="item in floortable"
              :key="item.floorStep"
              :value="item.floorStep"
              :lable="item.floorStep"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="房间号">
            <el-input v-model="form.room"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">立即创建</el-button>
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
      <el-tab-pane label="考场信息" class="second">
        <el-table class="table" :data="tableData">
<<<<<<< HEAD
          <el-table-column type="selection" width="55"/>
          <el-table-column label="所属楼" prop="buildingId" width="120px"/>
          <el-table-column label="楼层" prop="floor" width="120px"/>
          <el-table-column label="房间号" prop="roomId" width="120px"/>
          <el-table-column label="操作" width="180px">
=======
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="考场号" prop="examroomId" align="center" width="120px"/>
          <el-table-column label="所属楼" prop="buildingId" align="center" width="120px"/>
          <el-table-column label="房间号" prop="roomId" align="center" width="120px"/>
          <el-table-column label="操作" width="180px" align="center">
>>>>>>> cad5da5 (Improve table style in school page. Ref #73)
            <template>
              <el-button size="mini">修改</el-button>
              <el-button size="mini">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div>
          <el-pagination
            class="pagination"
            background
            layout="prev, pager, next"
            :total="1000">
          </el-pagination>
        </div>
        <div>
          <el-button class="submitBtn">上传</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data () {
    return {
      building: '',
      floor: '',
      form: [{
        building: '1号楼'
      }, {
        building: '2号楼'
      }],
      floortable: [
        {
          floorStep: 1
        },
        {
          floorStep: 2
        }
      ],
      tableData: [{
        buildingId: '1号楼',
        floor: '2',
        roomId: '209'
      }]
    }
  },
  methods: {
    choosestep () {
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
