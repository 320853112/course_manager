<template>
  <div class="pageSider">
    <Menu ref="siderBar" width="220" :active-name="activeName" :open-names="openNames" accordion>
      <!-- <template v-for="menu in courseMenu">
        <MenuItem :name="menu.name" v-if="menu.isMain" :key="menu.name" :to="menu.to">
        <Icon size="20" :type="menu.icon" />
        {{menu.title}}
        </MenuItem>
      </template> -->
      <MenuItem name="index" :to="{path:'/index'}">
      <Icon type="md-home"></Icon>
      <span>首页</span>
      </MenuItem>
      <MenuItem name="courseManage" :to="{path:'/courseManage'}" v-if="role === 'admin' ? true : false">
      <Icon type="ios-people"></Icon>
      <span>课程管理</span>
      </MenuItem>
      <MenuItem name="studentManage" :to="{path:'/studentManage'}" v-if="role === 'admin' ? true : false">
      <Icon type="ios-create"></Icon>
      <span>学生管理</span>
      </MenuItem>
      <MenuItem name="teacherManage" :to="{path:'/teacherManage'}" v-if="role === 'admin' ? true : false">
      <Icon type="ios-create"></Icon>
      <span>教师管理</span>
      </MenuItem>
      <MenuItem name="myCourse" :to="{path:'/myCourse'}" v-if="role === 'teacher' ? true : false">
      <Icon type="ios-people"></Icon>
      <span>我的课程</span>
      </MenuItem>
      <MenuItem name="scoreEntry" :to="{path:'/scoreEntry'}" v-if="role === 'teacher' ? true : false">
      <Icon type="ios-create"></Icon>
      <span>成绩录入</span>
      </MenuItem>
      <MenuItem name="personalCenter" :to="{path:'/personalCenter'}" v-if="role === 'student' ? true : false">
      <Icon type="ios-person"></Icon>
      <span>个人中心</span>
      </MenuItem>
      <MenuItem name="electiveCenter" :to="{path:'/electiveCenter'}" v-if="role === 'student' ? true : false">
      <Icon type="ios-paper"></Icon>
      <span>选课中心</span>
      </MenuItem>
      <MenuItem name="termSchedule" :to="{path:'/termSchedule'}" v-if="role === 'student' ? true : false">
      <Icon type="ios-copy"></Icon>
      <span>学期课表</span>
      </MenuItem>
      <MenuItem name="scoreQuery" :to="{path:'/scoreQuery'}" v-if="role === 'student' ? true : false">
      <Icon type="ios-book"></Icon>
      <span>成绩查询</span>
      </MenuItem>
      <MenuItem name="changePassword" :to="{path:'/changePassword'}" v-if="role === 'admin' ? false : true">
      <Icon type="md-mail"></Icon>
      <span>修改密码</span>
      </MenuItem>
    </Menu>
  </div>
</template>

<script>
export default {
  data() {
    return {
      role: '',
      activeName: 'index',
      openNames: []
      // courseMenu: [
      // {
      //   title: '首页',
      //   name: 'index',
      //   icon: 'md-home',
      //   to: '/index',
      //   isMain: true
      // },
      // {
      //   title: '课程管理',
      //   name: 'courseManage',
      //   icon: 'ios-people',
      //   to: '/courseManage',
      //   isMain: true
      // },
      // {
      //   title: '学生管理',
      //   name: 'studentManage',
      //   icon: 'ios-create',
      //   to: '/studentManage',
      //   isMain: true
      // },
      // {
      //   title: '教师管理',
      //   name: 'teacherManage',
      //   icon: 'ios-create',
      //   to: '/teacherManage',
      //   isMain: true
      // },
      // {
      //   title: '我的课程',
      //   name: 'myCourse',
      //   icon: 'ios-people',
      //   to: '/myCourse',
      //   isMain: true
      // },
      // {
      //   title: '成绩录入',
      //   name: 'scoreEntry',
      //   icon: 'ios-create',
      //   to: '/scoreEntry',
      //   isMain: true
      // },
      // {
      //   title: '个人中心',
      //   name: 'personalCenter',
      //   icon: 'ios-person',
      //   to: '/personalCenter',
      //   isMain: true
      // },
      // {
      //   title: '选课中心',
      //   name: 'electiveCenter',
      //   icon: 'ios-paper',
      //   to: '/electiveCenter',
      //   isMain: true
      // },
      // {
      //   title: '学期课表',
      //   name: 'termSchedule',
      //   icon: 'ios-copy',
      //   to: '/termSchedule',
      //   isMain: true
      // },
      // {
      //   title: '成绩查询',
      //   name: 'scoreQuery',
      //   icon: 'ios-book',
      //   to: '/scoreQuery',
      //   isMain: true
      // },
      // {
      //   title: '修改密码',
      //   name: 'changePassword',
      //   icon: 'md-mail',
      //   to: '/changePassword',
      //   isMain: true
      // }
      // ]
    }
  },
  mounted() {
    this.getUserRole()
  },
  methods: {
    // 返回用户角色
    async getUserRole() {
      const result = await this.$service.login.getUserRole()
      if (result.status) {
        this.role = result.data.roleName
      }
    }
  }
}
</script>

<style lang="less" scoped>
</style>
