<template>
  <div class="login">
    <Card>
      <Tabs value="学生">
        <TabPane label="学生" name="学生">
          <Form ref="formStu" :model="formStu" :rules="ruleStu" inline>
            <FormItem prop="id">
              <Input prefix="ios-person" type="text" v-model="formStu.id" placeholder="请输入学号" style="width: auto" />
              </Input>
            </FormItem>
            <FormItem prop="password">
              <Input prefix="ios-lock" type="password" v-model="formStu.password" placeholder="请输入密码" style="width: auto" />
              </Input>
            </FormItem>
            <div class="forgetPassword" @click="$router.push({ path: '/forgetPassword', query: {identity: '学生'} })">忘记密码</div>
            <FormItem>
              <Button type="primary" @click="handleStu()">登录</Button>
            </FormItem>
          </Form>
        </TabPane>
        <TabPane label="教师" name="教师">
          <Form ref="formTea" :model="formTea" :rules="ruleTea" inline>
            <FormItem prop="id">
              <Input prefix="ios-person" type="text" v-model="formTea.id" placeholder="请输入工号" style="width: auto" />
              </Input>
            </FormItem>
            <FormItem prop="password">
              <Input prefix="ios-lock" type="password" v-model="formTea.password" placeholder="请输入密码" style="width: auto" />
              </Input>
            </FormItem>
            <div class="forgetPassword" @click="$router.push({ path: '/forgetPassword', query: {identity: '教师'} })">忘记密码</div>
            <FormItem>
              <Button type="primary" @click="handleTea()">登录</Button>
            </FormItem>
          </Form>
        </TabPane>
        <TabPane label="管理员" name="管理员">
          <Form ref="formAdmin" :model="formAdmin" :rules="ruleAdmin" inline>
            <FormItem prop="username">
              <Input prefix="ios-person" type="text" v-model="formAdmin.username" placeholder="请输入账号" style="width: auto" />
              </Input>
            </FormItem>
            <FormItem prop="password">
              <Input prefix="ios-lock" type="password" v-model="formAdmin.password" placeholder="请输入密码" style="width: auto" />
              </Input>
            </FormItem>
            <div class="forgetPassword" @click="$router.push({ path: '/forgetPassword', query: {identity: '管理员'}})">忘记密码</div>
            <FormItem>
              <Button type="primary" @click="handleAdmin()">登录</Button>
            </FormItem>
          </Form>
        </TabPane>
      </Tabs>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formStu: {
        id: '',
        password: ''
      },
      ruleStu: {
        id: [{ required: true, message: '学号不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      },
      formTea: {
        id: '',
        password: ''
      },
      ruleTea: {
        id: [{ required: true, message: '工号不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      },
      formAdmin: {
        username: '',
        password: ''
      },
      ruleAdmin: {
        username: [{ required: true, message: '账号不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      }
    }
  },
  methods: {
    // 学生登录
    handleStu() {
      this.$refs.formStu.validate(async valid => {
        if (valid) {
          const result = await this.$service.login.stuLogin(this.formStu)
          if (result.status) {
            this.$Message.success('登录成功')
            localStorage.setItem('stuId', this.formStu.id)
            this.$router.push('/index')
          } else {
            this.$Message.warning('登录失败')
          }
        } else {
          this.$Message.error('登录失败')
        }
      })
    },
    // 教师登录
    handleTea() {
      this.$refs.formTea.validate(async valid => {
        if (valid) {
          const result = await this.$service.login.teacherLogin(this.formTea)
          if (result.status) {
            this.$Message.success('登录成功')
            localStorage.setItem('stuId', this.formTea.id)
            this.$router.push('/index')
          } else {
            this.$Message.warning('登录失败')
          }
        } else {
          this.$Message.error('登录失败')
        }
      })
    },
    // 管理员登录
    handleAdmin() {
      this.$refs.formAdmin.validate(async valid => {
        if (valid) {
          const result = await this.$service.login.adminLogin(this.formAdmin)
          if (result.status) {
            this.$Message.success('登录成功')
            localStorage.setItem('stuId', this.formAdmin.username)
            this.$router.push('/index')
          } else {
            this.$Message.warning('登录失败')
          }
        } else {
          this.$Message.error('登录失败')
        }
      })
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
/deep/.ivu-tabs-nav-wrap {
  display: flex;
  justify-content: space-evenly;
}
</style>