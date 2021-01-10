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
import Error404 from '../views/404Error.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '',
    redirect: '/login',
    meta: { title: '登录' }
  },
  {
    path: '/login',
    component: Login,
    meta: { title: '登录' }
  },
  {
    path: '/register',
    component: Register,
    meta: { title: '注册' }
  },
  {
    path: '/school',
    redirect: '/school/teacherInfo',
    component: School,
    children: [
      {
        path: 'teacherInfo',
        component: TeacherInfo,
        meta: { title: '教师设置' }
      },
      {
        path: 'roomInfo',
        component: RoomInfo,
        meta: { title: '考场设置' }
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
        component: SchoolInfo,
        meta: { title: '学校信息' }
      },
      {
        path: 'inputStudents',
        component: InputStudents,
        meta: { title: '考生输入' }
      },
      {
        path: 'schoolDetail',
        component: SchoolDetail,
        meta: { title: '学校细节' }
      },
      {
        path: 'allocation',
        component: Allocation,
        meta: { title: 'Allocation' }
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
        component: Personalinfo,
        meta: { title: '个人信息' }
      },
      {
        path: 'history',
        component: history,
        meta: { title: '历史记录' }
      },
      {
        path: 'feedback',
        component: feedback,
        meta: { title: '反馈信息' }
      }
    ]
  },
  {
    path: '/test',
    component: Test
  },
  {
    path: '*',
    component: Error404,
    meta: { title: '404' }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {
  // 根据路由元信息设置文档标题
  window.document.title = to.meta.title
  next()
})

export default router
