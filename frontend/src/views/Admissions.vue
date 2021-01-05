<template>
  <div class="addmissions">
    <admissions-top-nav></admissions-top-nav>
    <admissions-side-menu />
    <router-view class="routerv"/>
  </div>
</template>

<script>
import AdmissionsSideMenu from '../components/admissions/AdmissionsSideMenu.vue'
import AdmissionsTopNav from '../components/admissions/AdmissionsTopNav.vue'
export default {
  components: {
    AdmissionsSideMenu,
    AdmissionsTopNav
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
