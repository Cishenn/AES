<template>
  <div class="InputStudents">
    <div style="font-size:20px">考生信息录入</div>
    <div class="inputLi">
      理科考生人数：
      <el-input v-model="liNum" placeholder="liNum" @input="change($event)"></el-input>
    </div>
    <div class="inputWen">
      文科考生人数：
      <el-input v-model="wenNum" placeholder="wenNum"></el-input>
    </div>
    <div class="inputTe">
      理特长考生人数：
      <el-input v-model="teNum" placeholder="teNum"></el-input>
    </div>
    <div class="save">
      <el-button size="medium">保存</el-button>
    </div>
  </div>

</template>

<script>

export default {
  data () {
    return {
      liNum:'100000',
      wenNum:'100000',
      teNum:'100000'
    }
  },
  methods: {
    change (e) {
      this.$forceUpdate()
    }
  }
}
</script>

<style>
  .inputLi{
    margin-top: 150px;
    margin-left: 480px;
  }
  .inputWen{
    margin-top: 20px;
    margin-left: 480px;
  }
  .inputTe{
    margin-top: 20px;
    margin-left: 480px;
  }
  .save{
    margin-top: 20px;
    margin-left: 480px;
  }
</style>
