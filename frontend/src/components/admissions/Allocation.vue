<template>
  <div class="Allocation">
    <div style="font-size:20px;color: #FFFFFF;;margin-top: 26px;">自动排考</div>
    <div class="RoomTable" >
      <el-table class="roomtable"
      :data="roomTableData"
      border
      header-cell-class-name="tableStyle"
      >
        <el-table-column label="所属学校" prop="roomSchool" width="120px"/>
        <el-table-column label="考场编号" prop="roomId" width="120px"/>
        <el-table-column label="考场所在楼" prop="roomBuilding" width="120px"/>
        <el-table-column label="考场所在楼层" prop="roomFloor" width="120px"/>
        <el-table-column label="考场房间号" prop="roomNo" width="120px"/>
      </el-table>
    </div>
    <div class="InvigilatorTable">
      <el-table class="invigilatortable"
      :data="invigilatorTableData" border header-cell-class-name="tableStyle" >
        <el-table-column class="invigilatortable1" label="姓名" prop="invigilatorName" width="80px"/>
        <el-table-column label="所属学校" prop="invigilatorSchool" width="121px"/>
        <el-table-column label="性别" prop="invigilatorSex" width="80px"/>
        <el-table-column label="年级" prop="invigilatorGrade" width="80px"/>
        <el-table-column label="科目" prop="invigilatorSubject" width="80px"/>
        <el-table-column label="监考组" prop="invigilatorGroup" width="80px"/>
        <el-table-column label="主考" prop="chief" width="80px"/>
      </el-table>
    </div>
    <div class="AllocationTable">
      <el-table :data="allocationTableData" border header-cell-class-name="tableStyle">
        <el-table-column label="考点名称" prop="schoolName" width="120px"/>
        <el-table-column label="考场编号" prop="roomId" width="120px"/>
        <el-table-column label="监考组" prop="invigilatorGroup" width="120px"/>
      </el-table>
    </div>
    <div class="submit">
      <el-button size="medium">自动分配</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      roomTableData: [{
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }, {
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }, {
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }, {
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }, {
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }, {
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }, {
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }, {
        roomSchool: '合肥第一中学',
        roomId: '1',
        roomBuilding: '01',
        roomFloor: '2',
        roomNo: '201'
      }],
      invigilatorTableData: [{
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
        invigilatorName: '张叁',
        invigilatorSchool: '合肥第一中学',
        invigilatorSex: '男',
        invigilatorGrade: '2',
        invigilatorSubject: '语文',
        invigilatorGroup: '2',
        chief: '是'
      }, {
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
      }]
    }
  },
  created () {
    if (this.$store.state.addmissionsId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
  }
}
</script>

<style>
  .tableStyle {
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
  }
</style>
