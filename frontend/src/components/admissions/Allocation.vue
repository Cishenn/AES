<template>
  <div class="Allocation">
    <div style="font-size:20px">自动排考</div>
    <div class="RoomTable">
      <el-table :data="roomTableData">
        <el-table-column label="所属学校" prop="roomSchool" width="120px"/>
        <el-table-column label="考场编号" prop="roomId" width="120px"/>
        <el-table-column label="考场所在楼" prop="roomBuilding" width="120px"/>
        <el-table-column label="考场所在楼层" prop="roomFloor" width="120px"/>
        <el-table-column label="考场房间号" prop="roomNo" width="120px"/>
      </el-table>
    </div>
    <div class="InvigilatorTable">
      <el-table :data="invigilatorTableData">
        <el-table-column label="姓名" prop="invigilatorName" width="120px"/>
        <el-table-column label="所属学校" prop="invigilatorSchool" width="120px"/>
        <el-table-column label="性别" prop="invigilatorSex" width="120px"/>
        <el-table-column label="年级" prop="invigilatorGrade" width="120px"/>
        <el-table-column label="科目" prop="invigilatorSubject" width="120px"/>
        <el-table-column label="监考组" prop="invigilatorGroup" width="120px"/>
        <el-table-column label="主考" prop="chief" width="120px"/>
      </el-table>
    </div>
    <div class="AllocationTable">
      <el-table :data="allocationTableData">
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
      }],
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
      }]
    }
  }
}
</script>

<style>
  .RoomTable {
    margin-top: 20px;
    height: 300px;
    width: 600px;
    border-color: black;
    background-color: white;
    /* border-width: 5px; */
  }
  .InvigilatorTable{
    margin-top: 20px;
    height: 300px;
    max-width: 800px;
  }
  .AllocationTable{
    float: right;
    margin-top: -620px;
    margin-left: 900px;
  }
  .submit{
    margin: auto;
  }
</style>
