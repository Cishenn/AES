<template>
  <div class="container">
    <div class="myAvatar">
      <el-avatar v-if="imageUrl" :src="imageUrl" :size="60" style="margin-top: 15px;">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
      </el-avatar>
    </div>
    <el-menu :default-active="$route.path" class="sideNav" :router="true" text-color="gray" active-text-color="#000" border>
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>下属学校</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/admissions/SchoolInfo">学校列表</el-menu-item>
                <el-menu-item index="/admissions/SchoolDetail" disabled>学校信息</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
      <el-menu-item index="/admissions/inputStudents">
        <i class="el-icon-menu"></i>
        <span class="menuText">考生信息录入</span>
      </el-menu-item>
      <el-menu-item index="/admissions/Allocation">
        <i class="el-icon-menu"></i>
        <span class="menuText">自动化分配</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  mounted () {
    this.getTitle()
  },
  created () {
    // get avatar and username
  },
  methods: {
    getTitle () {}
  }
}
</script>

<style scoped>
  .container {
    background-color: #FFFFFF;
  }
  .myAvatar {
    font-family: "microsoft yahei";
    border-bottom-right-radius: 5px;
    width: 15%;
    margin-top: -10px;
    margin-left: 20px;
    height: 80px;
  }
  .sideNav {
    width: 10%;
    height: 80%;
    border: #000000;
    margin-left: -8px;
    opacity: 1;
    border-top: 1px solid lightgrey;
    border-left: 1px solid lightgrey;
  }
  .menuText {
    font-size: 14px;
  }
  .el-menu-item.is-active {
    color: #3498db;
    border-left: 4px solid #3498db;
  }
</style>
