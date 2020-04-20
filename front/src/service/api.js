const API = {
  login: {
    adminLogin: '/course_manage/adminLogin', // 管理员登录
    stuLogin: '/course_manage/stuLogin', // 学生登录
    teacherLogin: '/course_manage/teacherLogin', // 教师登录
    getUserRole: '/course_manage/getUserRole' // 获取用户角色
  },
  teacher: {
    getTeacher: '/course_manage/getTeacher', // 获取所有教师信息
    getTeacherCourse: '/course_manage/getTeacherCourse', // 教师获取自己所授课程
    getStuByCourse: '/course_manage/getStuByCourse', // 教师获取自己所授课程的学生
    setScoreByStu: '/course_manage/setScoreByStu', // 为课程打分 
    insertTeacher: '/course_manage/insertTeacher', // 新增教师信息
    deleteTeacher: '/course_manage/deleteTeacher', // 删除教师信息
    updateTeacher: '/course_manage/updateTeacher' // 编辑教师信息
  },
  student: {
    getStuInfo: '/course_manage/getStuInfo', // 获取所有学生信息
    insertStuInfo: '/course_manage/insertStuInfo', // 新增学生信息
    deleteStuInfo: '/course_manage/deleteStuInfo', // 删除学生信息
    updateStuInfo: '/course_manage/updateStuInfo' // 编辑学生信息
  },
  course: {
    getCourse: '/course_manage/getCourse', // 获取所有课程信息
    getStuCourse: '/course_manage/getStuCourse', // 学生已选课程
    insertCourse: '/course_manage/insertCourse', // 新增课程
    deleteCourse: '/course_manage/deleteCourse', // 删除课程
    updateCourse: '/course_manage/updateCourse', // 编辑课程
    selectCourse: '/course_manage/selectCourse', // 学生选课
    removeCourse: '/course_manage/removeCourse'  // 退选课程
  }
}

export default API
