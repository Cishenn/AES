<template>
  <div class="roomInfo">
    <el-tabs>
      <el-tab-pane label="考场录入" class="first">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="所属楼号">
            <el-select v-model="form.building" placeholder="请选择楼号">
              <el-option label="1号楼" value="1"></el-option>
              <el-option label="2号楼" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="房间号">
            <el-input v-model="form.room"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">立即创建</el-button>
            <el-button>清空</el-button>
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
        <el-table :data="tableData">
          <el-table-column type="selection" width="55"/>
          <el-table-column label="考场号" prop="examroomId" width="120px"/>
          <el-table-column label="所属楼" prop="buildingId" width="120px"/>
          <el-table-column label="房间号" prop="roomId" width="120px"/>
          <el-table-column label="操作" width="180px">
            <template>
              <el-button size="mini">修改</el-button>
              <el-button size="mini">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          layout="prev, pager, next"
          :total="1000">
        </el-pagination>
        <div>
          <el-button>上传</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data () {
    return {
      form: {
        building: '',
        room: ''
      },
      tableData: [{
        examroomId: '666',
        buildingId: '7号楼',
        roomId: '209'
      }]
    }
  }
}
</script>

<style scoped>

</style>
