<template>
  <div class="schoolInfo">
        <div style="font-size:20px">下属学校</div>
        <div>
          <el-table class="schoolTable" :data="tableData">
            <el-table-column label="学校名称" prop="schoolName" width="120px"/>
            <el-table-column label="学校考生数" prop="studentsNum" width="120px"/>
            <el-table-column label="学校考务人员数" prop="invigilatorNum" width="120px"/>
            <el-table-column label="学校考场数" prop="roomNum" width="120px"/>
          </el-table>
          <el-pagination class="schoolPagination"
            background
            layout="prev, pager, next"
            :total="1000">
          </el-pagination>
        </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{
        schoolName: '合肥第一中学',
        studentsNum: '100000',
        invigilatorNum: '100000',
        roomNum: '100000'
      }]
    }
  }
}
</script>

<style >
  .schoolTable{
    margin-top: 20px;
    margin-left: 280px;
    height: 680px;
  }
  .schoolPagination{
    margin-left: 280px;
  }
</style>
