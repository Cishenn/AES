<template>
  <div class="container">
    <div class="myAvatar">
      <div class="avatarImg">
        <el-avatar :src="avatarUrl" :size="60" ></el-avatar>
      </div>
    </div>
    <el-menu default-active="1" class="sideNav" router="true">
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
      avatarUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    }
  },
  mounted () {
    this.getTitle()
  },
  created () {
    // get avatar and username
    if (this.$store.state.teacherId === '') {
      // alert('不要随便乱进哦!')
      this.$router.push('/login')
    }
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
  width: 15%;
  margin-top: 1%;
  margin-bottom: 1%;
}

.avatarImg {
  width: 60px;
  margin: auto;
}

.sideNav {
  width: 15%;
  height: 500px;
}

.menuText {
  font-size: 18px;
}
</style>
