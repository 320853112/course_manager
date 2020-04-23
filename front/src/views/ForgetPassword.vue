<template>
  <div class="forgetPassword">
    <Card>
      <div class="ID">
        <span style="marginRight: 15px">请输入您要找回密码的登录账号:</span>
        <Input v-model="username" style="width: 220px" />
      </div>
      <div class="btn">
        <Button type="primary" :disabled="!username" @click="forgetPasswordModal = true">下一步</Button>
        <Button @click="$router.push({ path: '/login'})">取消</Button>
      </div>
    </Card>
    <!-- 找回密码弹窗 -->
    <Modal v-model="forgetPasswordModal" :closable="false" :footer-hide="true">
      <div class="user">
        <span style="marginRight: 15px">登录账号:</span>
        <Input v-model="username" style="width: 60%" disabled />
      </div>
      <div class="id">
        <span style="marginRight: 15px">密码:</span>
        <Input v-model="password" style="width: 60%" />
      </div>
      <div class="btn">
        <Button type="primary" @click="forgetPasswordModal = false">上一步</Button>
        <Button type="primary" @click="resetPassword">重置密码</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  name: 'forgetPassword',
  data() {
    return {
      username: '',
      password: '',
      forgetPasswordModal: false
    }
  },
  methods: {
    async resetPassword() {
      if (this.$route.query.identity === '学生') {
        const result = await this.$service.login.resetPassword({
          userType: 'student',
          userId: this.username,
          newPassword: this.password
        })
        if (result.status) {
          this.$Message.success('密码重置成功，请登录~')
          this.$router.push('/login')
        } else {
          this.$Message.error('密码重置失败，请检查学号是否正确~')
          this.forgetPasswordModal = false
        }
      } else if (this.$route.query.identity === '教师') {
        const result = await this.$service.login.resetPassword({
          userType: 'teacher',
          userId: this.username,
          newPassword: this.password
        })
        if (result.status) {
          this.$Message.success('密码重置成功，请登录~')
          this.$router.push('/login')
        } else {
          this.$Message.error('密码重置失败，请检查工号是否正确~')
          this.forgetPasswordModal = false
        }
      } else if (this.$route.query.identity === '管理员') {
        const result = await this.$service.login.resetPassword({
          userType: 'admin',
          userId: this.username,
          newPassword: this.password
        })
        if (result.status) {
          this.$Message.success('密码重置成功，请登录~')
          this.$router.push('/login')
        } else {
          this.$Message.error('密码重置失败，请检查账号是否正确~')
          this.forgetPasswordModal = false
        }
      }
    }
  }
}
</script>

<style lang="less" scoped>
.ivu-card {
  width: 500px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
}
.ID {
  text-align: center;
}
.btn {
  text-align: center;
  margin-top: 10px;
}
.ivu-btn-primary {
  margin-right: 20px;
}
.ivu-modal {
  .user {
    text-align: center;
  }
  .id {
    margin: 30px 0;
    text-align: center;
  }
  .btn {
    text-align: center;
  }
}
</style>
