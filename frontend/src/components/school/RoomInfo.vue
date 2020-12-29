<template>
  <div class="roomInfo">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="考场录入" name="first">考场录入</el-tab-pane>
      <el-tab-pane label="考场信息" name="second">考场信息</el-tab-pane>
    </el-tabs>
  </div>
</template>
