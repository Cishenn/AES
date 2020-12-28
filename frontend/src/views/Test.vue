<template>
  <div class="test">
    <h1>This is an test page</h1>
    <div>{{ msg }}</div>
  </div>
</template>

<script>

export default {
  data: function () {
    return {
      msg: 'test unsuccessfully!'
    }
  },

  created: function () {
    console.log('hahahahaha')
    this.axios.get('/api/tests')
      .then(response => {
        this.msg = response.data.tests[0].name
      })
  }
}
</script>
