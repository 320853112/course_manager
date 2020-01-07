<template>
  <div class="login">
    <Card>
      <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
        <FormItem prop="username">
          <Input prefix="ios-person" type="text" v-model="formInline.username" placeholder="user" style="width: auto" />
          </Input>
        </FormItem>
        <FormItem prop="password">
          <Input prefix="ios-lock" type="password" v-model="formInline.password" placeholder="Enter name" style="width: auto" />
          </Input>
        </FormItem>
        <div class="forgetPassword" @click="forgetPassword">忘记密码</div>
        <FormItem>
          <Button type="primary" @click="handleSubmit()">登录</Button>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        username: '',
        password: ''
      },
      ruleInline: {
        username: [{ required: true, message: '账号不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleSubmit() {
      this.$refs.formInline.validate(async valid => {
        if (valid) {
          this.$router.push('/home')
          const result = await this.$service.login.adminLogin(this.formInline)
          if (result.returnCode === '200') {
            this.$Message.success('登录成功')
          } else {
            this.$Message.warning('登录失败！')
          }
        } else {
          this.$Message.error('登录失败！')
        }
      })
    },
    forgetPassword() {
      this.$router.push('/forgetPassword')
    }
  }
}
</script>

<style scoped>
.login {
  background-color: #f1f3f4;
  position: absolute;
  width: 100%;
  height: 100%;
}
.ivu-card {
  width: 400px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  padding: 30px;
  background-color: #fff;
}
.ivu-form /deep/ .ivu-input {
  width: 300px;
  height: 40px;
  border-radius: 20px;
}
.ivu-form /deep/ .ivu-icon-ios-person:before {
  line-height: 40px;
}
.ivu-form /deep/ .ivu-icon-ios-lock:before {
  line-height: 40px;
}
.ivu-btn-primary {
  width: 300px;
  border-radius: 20px;
  height: 40px;
}
.forgetPassword {
  cursor: pointer;
  margin-bottom: 15px;
  display: flex;
  justify-content: flex-end;
}
.forgetPassword:hover {
  color: #2d8cf0;
}
</style>