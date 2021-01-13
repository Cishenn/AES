<template>
  <div class="container">
    <div class="myAvatar">
      <el-avatar v-if="imageUrl" :src="imageUrl" :size="60" style="margin-top: 15px;">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
      </el-avatar>
    </div>
    <el-menu :default-active="$route.path" class="sideNav" :router="true" text-color="gray" active-text-color="#000" border>
      <el-menu-item index="/teacher/personalinfo">
        <i class="el-icon-menu"></i>
        <span class="menuText">个人信息</span>
      </el-menu-item>
      <el-menu-item index="/teacher/history">
        <i class="el-icon-menu"></i>
        <span class="menuText">历史信息</span>
      </el-menu-item>
      <el-menu-item index="/teacher/feedback">
        <i class="el-icon-menu"></i>
        <span class="menuText">上级反馈</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data () {
    return {
      esId: '',
      avatarUrl: ''
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      // alert('不要随便乱进哦!')
      this.$router.push('/login')
    }
    this.esId = this.$store.getters.getTeacherId
    this.avatarUrl = `https://avatar-1301419632.cos.ap-nanjing.myqcloud.com/avatar/${this.esId}.jpg`
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
