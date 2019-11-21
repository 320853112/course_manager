import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/forgetPassword',
    name: 'forgetPassword',
    component: () => import('../views/ForgetPassword.vue')
  },
  {
    path: '*',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: Home,
    children: [
      {
        path: '/changePassword',
        name: 'ChangePassword',
        meta: {
          routeName: ['修改密码']
        },
        component: () => import('../views/ChangePassword.vue')
      },
      {
        path: '/personalCenter',
        name: 'personalCenter',
        meta: {
          routeName: ['个人中心']
        },
        component: () => import('../views/student/PersonalCenter.vue')
      },
      {
        path: '/electiveCenter',
        name: 'electiveCenter',
        meta: {
          routeName: ['选课中心']
        },
        component: () => import('../views/student/ElectiveCenter.vue')
      },
      {
        path: '/termSchedule',
        name: 'termSchedule',
        meta: {
          routeName: ['学期课表']
        },
        component: () => import('../views/student/TermSchedule.vue')
      },
      {
        path: '/scoreQuery',
        name: 'scoreQuery',
        meta: {
          routeName: ['成绩查询']
        },
        component: () => import('../views/student/ScoreQuery.vue')
      },
      {
        path: '/myCourse',
        name: 'myCourse',
        meta: {
          routeName: ['我的课程']
        },
        component: () => import('../views/teacher/MyCourse.vue')
      },
      {
        path: '/scoreEntry',
        name: 'scoreEntry',
        meta: {
          routeName: ['成绩录入']
        },
        component: () => import('../views/teacher/ScoreEntry.vue')
      },
      {
        path: '/courseManage',
        name: 'courseManage',
        meta: {
          routeName: ['课程管理']
        },
        component: () => import('../views/admin/CourseManage.vue')
      },
      {
        path: '/studentManage',
        name: 'studentManage',
        meta: {
          routeName: ['学生管理']
        },
        component: () => import('../views/admin/StudentManage.vue')
      },
      {
        path: '/teacherManage',
        name: 'teacherManage',
        meta: {
          routeName: ['教师管理']
        },
        component: () => import('../views/admin/TeacherManage.vue')
      }
    ]
  },
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 登录验证
router.beforeEach((to, from, next) => {
  //将需要登录的路由放入一个数组
  const nextRoutes = ['home', 'changePassword', 'personalCenter', 'electiveCenter', 'termSchedule', 'scoreQuery', 'studentManage', 'courseManage', 'scoreEntry']
  if (to.name === 'login') { //如果前往是的login页面，跳过验证
    next()
    return
  }
  if (nextRoutes.includes(to.name)) {//跳转的路由需要登录
    if (!localStorage.user) {
      console.log("没登录")
      next({
        name: 'login'
      })
    } else {
      next()//如果已经登录，可以跳转
    }
  } else {
    next()
  }
})

export default router
