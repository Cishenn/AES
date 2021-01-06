<!--  -->
<template>
  <div class="box">
    <div class="title-box" style="color: #FFFFFF;">学校详细信息</div>
    <div class="form-box">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="考场信息" name="first">
          <div style="font-size:20px;margin-bottom:10px">学校考场信息表单</div>
          <el-table
            :data="roomTableData"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="roomId"
              label="考场编号"
              width="150">
            </el-table-column>
            <el-table-column
              prop="roomBuilding"
              label="考场所在楼"
              width="120">
            </el-table-column>
            <el-table-column
              prop="roomFloor"
              label="考场所在楼层"
              width="120">
            </el-table-column>
            <el-table-column
              prop="roomNo"
              label="考场房间号"
              width="120">
            </el-table-column>
          </el-table>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
          </el-pagination>
        </el-tab-pane>
        <el-tab-pane label="考务人员信息" name="second">
          <div style="font-size:20px;margin-bottom:10px">考务人员信息表单</div>
          <el-table
            :data="examPersonData"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="age"
              label="年龄"
              width="120">
            </el-table-column>
            <el-table-column
              prop="sex"
              label="性别"
              width="120">
            </el-table-column>
            <el-table-column
              prop="telephoneNumber"
              label="电话号码"
              width="120">
            </el-table-column>
            <el-table-column
              prop="grade"
              label="年级"
              width="120">
            </el-table-column>
            <el-table-column
              prop="subject"
              label="科目"
              width="120">
            </el-table-column>
            <el-table-column
              prop="qualified"
              label="是否具有主考资格"
              width="150">
            </el-table-column>
            <el-table-column
              prop="examine"
              label="是否通过审核"
              width="120">
            </el-table-column>
          </el-table>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
          </el-pagination>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      activeName: 'first',
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
      examPersonData: [{
        name: 'Crilias',
        age: 'Crilias',
        sex: 'Crilias',
        telephoneNumber: 'Crilias',
        grade: 'Crilias',
        subject: 'Crilias',
        qualified: 'Crilias',
        examine: 'Crilias'
      }]
    }
  },
  created () {
    if (this.$store.state.addmissionsId === '') {
      alert('请不要乱输入网址哦')
      this.$router.push('/login')
    }
  },
  components: {},

  computed: {},

  mounted: {},

  methods: {}
}

</script>
<style scoped>
.box{

  border-radius: 5px;
  padding: 20px;
}
.title-box{
  font-size: 20px;
  /* color: red; */
  margin-bottom: 20px;
}
.form-box{
  margin-left: 60px;
}
</style>
