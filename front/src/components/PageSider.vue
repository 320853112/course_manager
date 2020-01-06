<template>
  <div class="pageSider">
    <Menu ref="siderBar" width="220" :active-name="activeName" :open-names="openNames" accordion>
      <template v-for="menu in courseMenu">
        <MenuItem :name="menu.name" :key="menu.name" :to="menu.to">
        <Icon size="20" :type="menu.icon" />
        {{menu.title}}
        </MenuItem>
      </template>
    </Menu>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: 'index',
      openNames: [],
      courseMenu: [
        {
          title: '首页',
          name: 'index',
          icon: 'md-home',
          to: '/index',
          isMain: false
        },
        {
          title: '课程管理',
          name: 'courseManage',
          icon: 'ios-people',
          to: '/courseManage',
          isMain: false
        },
        {
          title: '学生管理',
          name: 'studentManage',
          icon: 'ios-create',
          to: '/studentManage',
          isMain: false
        },
        {
          title: '教师管理',
          name: 'teacherManage',
          icon: 'ios-create',
          to: '/teacherManage',
          isMain: false
        },
        {
          title: '我的课程',
          name: 'myCourse',
          icon: 'ios-people',
          to: '/myCourse',
          isMain: true
        },
        {
          title: '成绩录入',
          name: 'scoreEntry',
          icon: 'ios-create',
          to: '/scoreEntry',
          isMain: false
        },
        {
          title: '个人中心',
          name: 'personalCenter',
          icon: 'ios-person',
          to: '/personalCenter',
          isMain: true
        },
        {
          title: '选课中心',
          name: 'electiveCenter',
          icon: 'ios-paper',
          to: '/electiveCenter',
          isMain: true
        },
        {
          title: '学期课表',
          name: 'termSchedule',
          icon: 'ios-copy',
          to: '/termSchedule',
          isMain: true
        },
        {
          title: '成绩查询',
          name: 'scoreQuery',
          icon: 'ios-book',
          to: '/scoreQuery',
          isMain: true
        },
        {
          title: '修改密码',
          name: 'changePassword',
          icon: 'md-mail',
          to: '/changePassword',
          isMain: true
        }
      ]
    }
  },
  mounted() {},
  methods: {}
}
</script>

<style lang="less" scoped>
</style>
