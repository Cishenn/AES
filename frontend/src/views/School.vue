<template>
  <div class="school">
    <top-nav></top-nav>
    <side-menu />
    <router-view class="routerv"/>
  </div>
</template>

<script>
import SideMenu from '../components/school/SideMenu.vue'
import TopNav from '../components/school/SchoolTopNav.vue'

export default {
  components: {
    SideMenu,
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
