<template>
  <div class="container">
    <div class="myAvatar">
      <el-avatar :src="avatarUrl" :size="60"></el-avatar>
    </div>
    <el-menu default-active="1" class="sideNav" router="true">
      <el-menu-item index="/school/infoCheck">
        <i class="el-icon-menu"></i>
        <span class="menuText">信息审核</span>
      </el-menu-item>
      <el-menu-item index="/school/infoUpload">
        <i class="el-icon-menu"></i>
        <span class="menuText">信息上传</span>
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
  width: 10%;
  margin-top: 2%;
  margin-bottom: 2%;
}

.sideNav {
  width: 10%;
  height: 500px;
}

.menuText {
  font-size: 18px;
}
</style>
