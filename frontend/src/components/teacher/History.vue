<!--  -->
<template>
  <div class="box">
    <div class="title-box">历史记录</div>
    <div class="form-box">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="历史监考" name="first">
          <div style="font-size:20px;margin-bottom:10px">历史监考信息表单</div>
          <el-table
            :data="historytable"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="date"
              label="监考日期"
              width="150">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="school"
              label="考点"
              width="120">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
          </el-pagination>
        </el-tab-pane>
        <el-tab-pane label="历史违规" name="second">
          <div style="font-size:20px;margin-bottom:10px">历史违规信息表单</div>
          <el-table
            :data="Violationtable"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="date"
              label="违规日期"
              width="150">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="school"
              label="考点"
              width="120">
            </el-table-column>
            <el-table-column
              prop="reason"
              label="违规原因"
              width="120">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="历史反馈" name="third">历史反馈信息表单</el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      activeName: 'first',
      historytable: [{
        date: '2018',
        name: 'Crilias',
        school: 'HFUT'
      },
      {
        date: '2019',
        name: 'Crilias',
        school: 'ANda'
      },
      {
        date: '2018',
        name: 'Crilias',
        school: 'ZKD'
      }],
      Violationtable: [{
        date: '2019',
        name: 'Crilias',
        school: 'HFUT',
        reason: '体罚迟到学生'
      }]
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      // alert('不要随便乱进哦!')
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
