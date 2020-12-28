import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Inspector from '../views/Inspector.vue'
import School from '../views/School.vue'
import Recruit from '../views/Recruit.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '',
    redirect: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/inspector',
    component: Inspector
  },
  {
    path: '/school',
    component: School
  },
  {
    path: '/recruit',
    component: Recruit
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
