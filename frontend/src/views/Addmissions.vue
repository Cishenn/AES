<template>
  <div class="addmissions">
    <addminssions-side-menu />
    <router-view class="routerv"/>
  </div>
</template>

<script>
import AddminssionsSideMenu from '../components/admissions/AddminssionsSideMenu.vue'

export default {
  components: {
    AddminssionsSideMenu
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
