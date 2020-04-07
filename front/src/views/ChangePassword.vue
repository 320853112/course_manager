<template>
  <div class="changePassword">
    <Card>
      <Form :model="formLeft" label-position="left" :label-width="100">
        <FormItem label="账号:">
          <Input v-model="formLeft.userName" disabled></Input>
        </FormItem>
        <FormItem label="新密码:">
          <Input type="password" v-model="formLeft.input3"></Input>
        </FormItem>
        <FormItem label="确认密码:">
          <Input type="password" v-model="formLeft.input4"></Input>
        </FormItem>
        <FormItem>
          <Button type="primary" @click="submit">提交</Button>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formLeft: {
        userName: localStorage.getItem('stuId'),
        input3: '',
        input4: ''
      }
    }
  },
  methods: {
    submit() {
      localStorage.removeItem('user')
      this.$Message.success('密码修改成功，请重新登录！')
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="less" scoped>
.ivu-card {
  width: 40%;
  margin: auto;
}
.ivu-form-item:last-child {
  display: flex;
  justify-content: center;
  .ivu-btn {
    width: 100px;
  }
}
</style>
