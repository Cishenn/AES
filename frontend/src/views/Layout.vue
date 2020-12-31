<!--  -->
<template>
  <el-container class="main_window">
    <navmean></navmean>
    <el-container>
      <el-header>头部</el-header>
      <el-main>页面</el-main>
    </el-container>
  </el-container>
</template>

<script>
import Navmean from './Navmean.vue'
export default {
  data () {
    return {
    }
  },

  components: { Navmean },

  computed: {},

  mounted: {},

  methods: {}
}

</script>
<style lang='scss' scoped>
</style>
