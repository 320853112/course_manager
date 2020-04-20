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
    path: '*',
    name: 'notFount',
    component: () => import('../views/NotFount.vue')
  },
  {
    path: '/forgetPassword',
    name: 'forgetPassword',
    component: () => import('../views/ForgetPassword.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: Home,
    meta: { requiresAuth: true },
    children: [
      {
        path: '/index',
        name: 'index',
        meta: {
          routeName: ['首页'],
          requiresAuth: true
        },
        component: () => import('../views/Index.vue')
      },
      {
        path: '/changePassword',
        name: 'ChangePassword',
        meta: {
          routeName: ['修改密码'],
          requiresAuth: true
        },
        component: () => import('../views/ChangePassword.vue')
      },
      {
        path: '/personalCenter',
        name: 'personalCenter',
        meta: {
          routeName: ['个人中心'],
          requiresAuth: true
        },
        component: () => import('../views/student/PersonalCenter.vue')
      },
      {
        path: '/electiveCenter',
        name: 'electiveCenter',
        meta: {
          routeName: ['选课中心'],
          requiresAuth: true
        },
        component: () => import('../views/student/ElectiveCenter.vue')
      },
      {
        path: '/termSchedule',
        name: 'termSchedule',
        meta: {
          routeName: ['学期课表'],
          requiresAuth: true
        },
        component: () => import('../views/student/TermSchedule.vue')
      },
      {
        path: '/scoreQuery',
        name: 'scoreQuery',
        meta: {
          routeName: ['成绩查询'],
          requiresAuth: true
        },
        component: () => import('../views/student/ScoreQuery.vue')
      },
      {
        path: '/myCourse',
        name: 'myCourse',
        meta: {
          routeName: ['我的课程'],
          requiresAuth: true
        },
        component: () => import('../views/teacher/MyCourse.vue')
      },
      {
        path: '/scoreEntry',
        name: 'scoreEntry',
        meta: {
          routeName: ['成绩录入'],
          requiresAuth: true
        },
        component: () => import('../views/teacher/ScoreEntry.vue')
      },
      {
        path: '/courseManage',
        name: 'courseManage',
        meta: {
          routeName: ['课程管理'],
          requiresAuth: true
        },
        component: () => import('../views/admin/CourseManage.vue')
      },
      {
        path: '/studentManage',
        name: 'studentManage',
        meta: {
          routeName: ['学生管理'],
          requiresAuth: true
        },
        component: () => import('../views/admin/StudentManage.vue')
      },
      {
        path: '/teacherManage',
        name: 'teacherManage',
        meta: {
          routeName: ['教师管理'],
          requiresAuth: true
        },
        component: () => import('../views/admin/TeacherManage.vue')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 登录验证
router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) { // 判断该路由是否需要登录权限 some() 方法用于检测数组中的元素是否满足指定条件（函数提供）,满足条件返回true
    if (localStorage.getItem('stuId')) {
      next()
    } else {
      next({ path: '/login' })
    }
  } else {
    next()
  }
})

export default router
