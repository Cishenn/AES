import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Inspector from '../views/Inspector.vue'
import School from '../views/School.vue'
import Recruit from '../views/Recruit.vue'
import Test from '../views/Test.vue'
import TeacherInfo from '../components/school/TeacherInfo.vue'
import RoomInfo from '../components/school/RoomInfo.vue'

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
    redirect: '/school/teacherInfo',
    component: School,
    children: [
      {
        path: 'teacherInfo',
        component: TeacherInfo
      },
      {
        path: 'roomInfo',
        component: RoomInfo
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
