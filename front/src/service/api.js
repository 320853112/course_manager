const API = {
  login: {
    adminLogin: '/course_manage/adminLogin', // 管理员登录
    stuLogin: '/course_manage/stuLogin', // 学生登录
    teacherLogin: '/course_manage/teacherLogin', // 教师登录
    getUserRole: '/course_manage/getUserRole' // 获取用户角色
  },
  teacher: {
    getTeacher: '/course_manage/getTeacher', // 获取所有教师信息
    insertTeacher: '/course_manage/insertTeacher', // 新增教师信息
    deleteTeacher: '/course_manage/deleteTeacher' // 删除教师信息
  },
  student: {
    getStuInfo: '/course_manage/getStuInfo' // 获取所有学生信息
  },
  course: {
    getCourse: '/course_manage/getCourse', // 获取所有课程信息
    getStuCourse: '/course_manage/getStuCourse', // 学生已选课程
    insertCourse: '/course_manage/insertCourse' // 新增课程
  }
}

export default API
