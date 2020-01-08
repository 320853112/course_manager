<template>
  <div class="pageHeader">
    <Header>
      <Menu mode="horizontal" theme="light" active-name="1">
        <div class="layout-logo">
          <img src="../assets/logo.png" />
        </div>
        <div class="layout-nav">
          <MenuItem name="1">
          <span>姓名: {{user.name}}</span>
          </MenuItem>
          <MenuItem name="2">
          <span>角色: {{user.role}}</span>
          </MenuItem>
          <MenuItem name="3">
          <Button @click="modal1 = true">退出登录</Button>
          </MenuItem>
        </div>
      </Menu>
    </Header>
    <!-- 退出系统弹窗 -->
    <Modal class="model" v-model="modal1" :closable="false" :footer-hide="true">
      <div class="model-img"><img src="../assets/delete.png"></div>
      <p class="model-title">确认退出</p>
      <p class="model-content">确认退出系统吗？</p>
      <div class="btn">
        <Button type="primary" @click="confirm">确认</Button>
        <Button type="primary" @click="modal1=false">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        name: '邓藿',
        role: ''
      },
      modal1: false
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
        localStorage.setItem('role', result.data.roleName)
        localStorage.setItem('name', result.data.userName)
        this.user.role = result.data.roleName
        this.user.name = result.data.userName
      }
    },
    // 退出系统
    confirm() {
      this.modal1 = false
      localStorage.removeItem('name')
      localStorage.removeItem('role')
      this.$Message.success('退出成功')
      this.$router.push({ path: '/login' })
    }
  }
}
</script>

<style lang="less" scoped>
.ivu-layout-header {
  border: 1px solid #d7dde4;
  background: #fff;
  position: relative;
  border-radius: 4px;
}
.layout-logo {
  width: 200px;
  height: 69px;
  background: #2d8cf0;
  border-radius: 3px;
  float: left;
  position: relative;
  img {
    width: 200px;
    height: 69px;
  }
}
.layout-nav {
  margin: 0 auto;
  margin-right: 20px;
  float: right;
}
.ivu-layout-header {
  padding: 0px;
  height: 68px;
  line-height: 68px;
}
.ivu-menu-horizontal {
  height: 68px;
}

.model-img {
  width: 40px;
  height: 40px;
  margin: auto;
  img {
    width: 40px;
    height: 40px;
  }
}
.model-title {
  text-align: center;
  font-size: 20px;
  margin: 10px 0;
}
.model-content {
  text-align: center;
  font-size: 16px;
}
.btn {
  text-align: center;
  margin: 30px 0 20px 0;
  .ivu-btn-primary {
    width: 100px;
    height: 40px;
    font-size: 15px;
  }
  .ivu-btn-primary:nth-child(2) {
    margin-left: 20px;
    background: rgb(233, 233, 233);
    color: gray;
    border: none;
  }
}
</style>
