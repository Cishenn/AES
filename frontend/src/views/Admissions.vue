<template>
  <div class="admissions">
    <adminssions-side-menu />
    <router-view class="routerv"/>
  </div>
</template>

<script>
import AdminssionsSideMenu from '../components/admissions/AdminssionsSideMenu.vue'

export default {
  components: {
    AdminssionsSideMenu
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
