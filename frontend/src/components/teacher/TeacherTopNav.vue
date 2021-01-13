<template>
  <div class="container">
    <div id="topNav" >
      <el-switch
        class="online"
        v-model="value1"
        active-text="在线"
        inactive-text="离线">
      </el-switch>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      value1: true,
      value2: true
    }
  }
}
</script>

<style scoped>
.container{
  width: 90%;
}
  #topNav{
    width: 90%;
    float: right;
    height: 60px;
    margin-right: -115PX;
  }
  .online{
    margin-top: 2.5%;
    float: right;
  }
</style>
