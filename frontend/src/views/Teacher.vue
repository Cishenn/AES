<template>
  <div class="teacher">
    <top-nav></top-nav>
    <sidemenu></sidemenu>
    <router-view class="routerv"/>
  </div>
</template>

<script>
import Sidemenu from '../components/teacher/Sidemenu.vue'
import TopNav from '../components/teacher/TeacherTopNav.vue'

export default {
  components: {
    Sidemenu,
    TopNav
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
