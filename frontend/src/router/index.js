import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import School from '../views/School.vue'
import Teacher from '../views/Teacher.vue'
import Test from '../views/Test.vue'
import TeacherInfo from '../components/school/TeacherInfo.vue'
import RoomInfo from '../components/school/RoomInfo.vue'
import Admissions from '../views/Admissions.vue'
import SchoolInfo from '../components/admissions/SchoolInfo.vue'
import InputStudents from '../components/admissions/InputStudents.vue'
import Allocation from '../components/admissions/Allocation.vue'
import Personalinfo from '../components/teacher/Personalinfo.vue'
import history from '../components/teacher/History.vue'
import feedback from '../components/teacher/Feedback.vue'
import SchoolDetail from '../components/admissions/SchoolDetail.vue'

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
    path: '/admissions',
    redirect: '/admissions/SchoolInfo',
    component: Admissions,
    children: [
      {
        path: 'schoolInfo',
        component: SchoolInfo
      },
      {
        path: 'inputStudents',
        component: InputStudents
      },
      {
        path: 'schoolDetail',
        component: SchoolDetail
      },
      {
        path: 'allocation',
        component: Allocation
      }
    ]
  },
  {
    path: '/teacher',
    redirect: '/teacher/personalinfo',
    component: Teacher,
    children: [
      {
        path: 'personalinfo',
        component: Personalinfo
      },
      {
        path: 'history',
        component: history
      },
      {
        path: 'feedback',
        component: feedback
      }
    ]
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
