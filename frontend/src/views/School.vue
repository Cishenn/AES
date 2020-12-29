<template>
  <div class="school">
    <side-menu />
    <router-view class="routerv"/>
  </div>
</template>

<script>
import SideMenu from '../components/school/SideMenu.vue'

export default {
  components: {
    SideMenu
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
