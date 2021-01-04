<template>
  <div class="schoolInfo">
    <div class="container">
      <div style="font-size:20px;color: #FFFFFF;;margin-top: 26px;">下属学校</div>
      <div class="search">
          <el-input v-model="searchfor" placeholder="请输入"
                    suffix-icon="el-icon-search"></el-input>
      </div>
      <div style=" margin-left: 320px;margin-top: 70px;background-color: #FFFFFF;">
        <el-table class="schoolTable"
        :data="tableData"
        border
        header-cell-class-name="tableStyle"
        >
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
  </div>
</template>

<script>
export default {
  data () {
    return {
      searchfor: '',
      tableData: [{
        schoolName: '合肥第一中学',
        studentsNum: '100000',
        invigilatorNum: '100000',
        roomNum: '100000'
      }, {
        schoolName: '合肥第一中学',
        studentsNum: '100000',
        invigilatorNum: '100000',
        roomNum: '100000'
      }, {
        schoolName: '合肥第一中学',
        studentsNum: '100000',
        invigilatorNum: '100000',
        roomNum: '100000'
      }, {
        schoolName: '合肥第一中学',
        studentsNum: '100000',
        invigilatorNum: '100000',
        roomNum: '100000'
      }, {
        schoolName: '合肥第一中学',
        studentsNum: '100000',
        invigilatorNum: '100000',
        roomNum: '100000'
      }, {
        schoolName: '合肥第一中学',
        studentsNum: '100000',
        invigilatorNum: '100000',
        roomNum: '100000'
      }, {
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
  .container{
    width: 100%;
    height: 100%;
  }
  .tableStyle {
    background-color:#3498db!important;
    color:#fff;
    font-weight:400;
    overflow: auto;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
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
