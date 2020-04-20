<template>
  <div class="changePassword">
    <Card>
      <Form ref="formCustom" :model="formCustom" :rules="ruleCustom" label-position="left" :label-width="100">
        <FormItem label="账号:">
          <Input v-model="formCustom.userName" disabled></Input>
        </FormItem>
        <FormItem label="新密码:" prop="passwd">
          <Input type="password" v-model="formCustom.passwd"></Input>
        </FormItem>
        <FormItem label="确认密码:" prop="passwdCheck">
          <Input type="password" v-model="formCustom.passwdCheck"></Input>
        </FormItem>
        <Button type="primary" @click="submit">提交</Button>
      </Form>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.formCustom.passwdCheck !== '') {
          // 对第二个密码框单独验证
          this.$refs.formCustom.validateField('passwdCheck')
        }
        callback()
      }
    }
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入确认密码'))
      } else if (value !== this.formCustom.passwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      formCustom: {
        userName: localStorage.getItem('stuId'),
        passwd: '',
        passwdCheck: ''
      },
      ruleCustom: {
        passwd: [{ validator: validatePass, trigger: 'blur' }],
        passwdCheck: [{ validator: validatePassCheck, trigger: 'blur' }]
      }
    }
  },
  methods: {
    submit() {
      this.$refs.formCustom.validate(async valid => {
        if (valid) {
          if (localStorage.getItem('role') === 'student') {
            const result = await this.$service.student.updateStuInfo({
              id: localStorage.getItem('stuId'),
              password: this.formCustom.passwdCheck
            })
            if (result.status) {
              this.$Message.success('密码修改成功，请重新登录~')
              this.$router.push('/login')
            }
          } else if (localStorage.getItem('role') === 'teacher') {
            const result = await this.$service.teacher.updateTeacher({
              id: localStorage.getItem('stuId'),
              password: this.formCustom.passwdCheck
            })
            if (result.status) {
              this.$Message.success('密码修改成功，请重新登录~')
              this.$router.push('/login')
            }
          } else if (localStorage.getItem('role') === 'admin') {
            this.$Message.success('管理员更改密码')
          }
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.ivu-card {
  width: 40%;
  margin: auto;
}
.ivu-btn-primary {
  width: 15%;
  text-align: center;
  margin-left: 40%;
}
</style>
