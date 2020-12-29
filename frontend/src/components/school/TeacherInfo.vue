<template>
  <div class="teacherInfo">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="信息审核" name="first">信息审核</el-tab-pane>
      <el-tab-pane label="信息上传" name="second">信息上传</el-tab-pane>
    </el-tabs>
  </div>
</template>
