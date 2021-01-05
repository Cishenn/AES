<template>
  <div class="InputStudents">
    <div style="font-size:20px;color: #FFFFFF;margin-top: 26px;">考生信息录入</div>
    <div class="mainarea">
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
  </div>

</template>

<script>

export default {
  data () {
    return {
      liNum: '100000',
      wenNum: '100000',
      teNum: '100000'
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
  .mainarea{
    margin-left: 400px;
  }
  .inputLi{
    margin-top: 150px;
  }
  .inputWen{
    margin-top: 20px;
  }
  .inputTe{
    margin-top: 20px;
  }
  .save{
    margin-top: 20px;
  }
</style>
