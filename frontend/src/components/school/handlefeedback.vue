<!--  -->
<template>
  <div>
    <h1 style="margin:50px 20px auto 350px">反馈列表</h1>
    <template style="margin-left:200px">
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          prop="date"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="反馈内容"
          width="180">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">通过</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },

  components: {},

  computed: {},

  mounted: {},

  methods: {}
}

</script>
<style  scoped>
</style>
