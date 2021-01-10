<!--  -->
<template>
  <div>
    <h1>网址输入错误</h1>
    <div>
      <h1>404Error！</h1>
      <button @click="goback">点击返回登陆页面</button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },

  components: {},

  computed: {},

  mounted: {},

  methods: {
    goback () {
      this.$router.push('/login')
    }
  }
}

</script>
<style  scoped>
</style>
