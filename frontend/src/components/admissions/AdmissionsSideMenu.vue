<template>
  <div class="container">
    <div class="myAvatar" style="background-color:#3498db;border: lightgrey;">
      <el-avatar :src="avatarUrl" :size="60" style="margin-left: 5px; margin-top: 5px;"></el-avatar>
      <span style="font-size:20px; color: #FFFFFF; margin-left: 25px; height: 50px;font-family:Microsoft YaHei;">{{eduname}}</span>
    </div>
    <el-menu :default-active="$route.path" class="sideNav" :router="true" text-color="gray" active-text-color="#000" border>
      <el-menu-item index="/admissions/SchoolInfo">
        <i class="el-icon-menu"></i>
        <span class="menuText">下属学校</span>
      </el-menu-item>
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
      eduname: '',
      avatarUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    }
  },
  mounted () {
    this.getTitle()
    this.geteduinfo()
  },
  created () {
    // get avatar and username
  },
  methods: {
    getTitle () {},
    geteduinfo () {
      this.$axios.get('enrollmentDepartment/getOne', {
        params: {
          eduId: this.$store.state.eduId
        }
      }).then(resp => {
        // console.log(resp.data)
        this.eduname = resp.data.eduName
      })
    }
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
    margin-left: -8px;
    height: 80px;
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
