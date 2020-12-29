import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Inspector from '../views/Inspector.vue'
import School from '../views/School.vue'
import Recruit from '../views/Recruit.vue'
import Test from '../views/Test.vue'
import InfoCheck from '../components/school/InfoCheck.vue'
import InfoUpload from '../components/school/InfoUpload.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '',
    redirect: '/login'
  },
  {
    path: '/login',
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
    redirect: '/school/infoCheck',
    component: School,
    children: [
      {
        path: 'infoCheck',
        component: InfoCheck
      },
      {
        path: 'infoUpload',
        component: InfoUpload
      }
    ]
  },
  {
    path: '/recruit',
    component: Recruit
  },
  {
    path: '/test',
    component: Test
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
