<template>
  <div class="changePassword">
    <Card>
      <Form :model="formLeft" label-position="left" :label-width="100">
        <FormItem label="账号:">
          <Input v-model="formLeft.input1" disabled></Input>
        </FormItem>
        <FormItem label="密码:">
          <Input v-model="formLeft.input2" disabled></Input>
        </FormItem>
        <FormItem label="新密码:">
          <Input type="password" v-model="formLeft.input3"></Input>
        </FormItem>
        <FormItem label="确认密码:">
          <Input type="password" v-model="formLeft.input4"></Input>
        </FormItem>
        <FormItem>
          <Button type="primary" @click="submit">提交</Button>
          <Button @click="cancel">取消</Button>
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
        input1: '2016030594',
        input2: '123456',
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
    },
    cancel() {
      this.$Message.info('点击取消')
    },
    change() {
      if (this.seen1) {
        this.passhide = false
      } else {
        this.passhide = true
      }
      this.seen1 = !this.seen1
    }
  }
}
</script>

<style lang="less" scoped>
.ivu-card {
  width: 40%;
  margin: auto;
}
.ivu-btn {
  width: 100px;
  margin: 10px;
}
.ivu-btn-default {
  background-color: #dee1e6;
}
</style>
