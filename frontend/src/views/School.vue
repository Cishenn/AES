<template>
  <div class="school">
    <side-menu />
    <router-view />
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

</style>
