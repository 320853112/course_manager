<template>
  <div class="pageSider">
    <Sider breakpoint="md" collapsible :collapsed-width="78">
      <Menu active-name="1-1" theme="light" width="auto">
        <template v-if="isShow==='学生'">
          <MenuItem name="1-1">
          <router-link to="/personalCenter">
            <Icon type="ios-person"></Icon>
            <span>个人中心</span>
          </router-link>
          </MenuItem>
          <MenuItem name="1-2">
          <router-link to="/electiveCenter">
            <Icon type="ios-paper"></Icon>
            <span>选课中心</span>
          </router-link>
          </MenuItem>
          <MenuItem name="1-3">
          <router-link to="/termSchedule">
            <Icon type="ios-copy"></Icon>
            <span>学期课表</span>
          </router-link>
          </MenuItem>
          <MenuItem name="1-4">
          <router-link to="/scoreQuery">
            <Icon type="ios-book"></Icon>
            <span>成绩查询</span>
          </router-link>
          </MenuItem>
        </template>
        <template v-if="isShow==='教师'">
          <MenuItem name="1-1">
          <router-link to="/myCourse">
            <Icon type="ios-people"></Icon>
            <span>我的课程</span>
          </router-link>
          </MenuItem>
          <MenuItem name="1-6">
          <router-link to="/scoreEntry">
            <Icon type="ios-create"></Icon>
            <span>成绩录入</span>
          </router-link>
          </MenuItem>
        </template>
        <template v-if="isShow==='管理员'">
          <MenuItem name="1-1">
          <router-link to="/courseManage">
            <Icon type="ios-people"></Icon>
            <span>课程管理</span>
          </router-link>
          </MenuItem>
          <MenuItem name="1-7">
          <router-link to="/studentManage">
            <Icon type="ios-create"></Icon>
            <span>学生管理</span>
          </router-link>
          </MenuItem>
          <MenuItem name="1-8">
          <router-link to="/teacherManage">
            <Icon type="ios-create"></Icon>
            <span>教师管理</span>
          </router-link>
          </MenuItem>
        </template>
        <MenuItem name="1-9">
        <router-link to="/changePassword">
          <Icon type="ios-lock"></Icon>
          <span>修改密码</span>
        </router-link>
        </MenuItem>
      </Menu>
      <div slot="trigger"></div>
    </Sider>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isShow: ''
    }
  },
  created() {
    this.setSilder()
  },

  mounted() {},

  methods: {
    setSilder() {
      const role = localStorage.getItem('role')
      if (role === '学生') {
        this.isShow = '学生'
      } else if (role === '教师') {
        this.isShow = '教师'
      } else if (role === '管理员') {
        this.isShow = '管理员'
      }
    }
  }
}
</script>

<style lang="less" scoped>
</style>
