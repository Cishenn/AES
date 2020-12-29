<template>
  <div class="teacherInfo">
    <el-tabs>
      <el-tab-pane label="信息审核" class="first">
        <div style="font-size:20px">考务人员信息表单</div>
        <el-table :data="tableData">
          <el-table-column label="姓名" prop="name" width="120px"/>
          <el-table-column label="年龄" prop="age" width="120px"/>
          <el-table-column label="电话" prop="phone" width="120px"/>
          <el-table-column label="性别" prop="sex" width="120px"/>
          <el-table-column label="年级" prop="grade" width="120px"/>
          <el-table-column label="科目" prop="subject" width="120px"/>
          <el-table-column label="操作" width="180px">
            <template>
              <el-button size="mini">批准</el-button>
              <el-button size="mini">打回</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          layout="prev, pager, next"
          :total="1000">
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="信息上传" class="second">
        <el-table :data="tableData">
          <el-table-column type="selection" width="55"/>
          <el-table-column label="姓名" prop="name" width="120px"/>
          <el-table-column label="年龄" prop="age" width="120px"/>
          <el-table-column label="电话" prop="phone" width="120px"/>
          <el-table-column label="性别" prop="sex" width="120px"/>
          <el-table-column label="年级" prop="grade" width="120px"/>
          <el-table-column label="科目" prop="subject" width="120px"/>
          <el-table-column label="操作" width="180px">
            <template>
              <el-button size="mini">选中</el-button>
              <el-button size="mini">取消</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          layout="prev, pager, next"
          :total="1000">
        </el-pagination>
        <div>
          <el-button>提交给上级招办</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{
        name: '王明',
        age: '40',
        phone: '13856781234',
        sex: '男',
        grade: '高二',
        subject: '数学'
      }]
    }
  }
}
</script>

<style scoped>

</style>

<style >
.el-tabs__item {
  font-size: 18px !important;
}
</style>
