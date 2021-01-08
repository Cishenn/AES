<template>
  <div class="container">
    <div class="myAvatar">
      <div class="avatarImg">
        <el-avatar :src="avatarUrl" :size="60" style="margin-top: 15px;"></el-avatar>
      </div>
    </div>
    <el-menu :default-active="$route.path" class="sideNav" :router="true" text-color="gray" active-text-color="#000"
      border>
      <el-menu-item index="/school/teacherInfo">
        <i class="el-icon-menu"></i>
        <span class="menuText">考务人员</span>
      </el-menu-item>
      <el-menu-item index="/school/roomInfo">
        <i class="el-icon-menu"></i>
        <span class="menuText">考场信息</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data () {
    return {
      avatarUrl: 'https://cube.elemecdn.com/0/c7/731d222b16d4537c0dcb5dfdc0402svg.svg'
    }
  },
  mounted () {
    this.getTitle()
  },
  created () {
    // get avatar and username
  },
  methods: {
    getTitle () {
      //
    }
  }
}
</script>

<style scoped>
  .myAvatar {
    font-family: "microsoft yahei";
    border-bottom-right-radius: 5px;
    width: 15%;
    margin-top: -10px;
    margin-left: -8px;
    height: 80px;
  }

  .avatarImg {
    width: 60px;
    margin: auto;
  }

  .sideNav {
    width: 15%;
    height: 80%;
    border: #000000;
    margin-left: -8px;
    margin-top: 1%;
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
