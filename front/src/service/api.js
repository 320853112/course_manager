const API = {
  login: {
    adminLogin: '/course_manage/adminLogin', // 管理员登录
    stuLogin: '/course_manage/stuLogin', // 学生登录
    getUserRole: '/course_manage/getUserRole', // 返回用户角色
  },
  student: {
    getStuInfo: '/course_manage/getStuInfo', // 返回所有学生信息
  }
}

export default API;
