<template>
  <div class="teacher">
    <sidemenu></sidemenu>
    <router-view class="routerv"/>
  </div>
</template>

<script>
import Sidemenu from '../components/teacher/Sidemenu.vue'

export default {
  components: {
    Sidemenu
  },
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>

<style scoped>
.routerv {
  position: absolute;
  top: 2%;
  left: 20%;
}
</style>
