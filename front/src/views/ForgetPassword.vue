<template>
  <div class="forgetPassword">
    <Card>
      <div class="ID">
        <span style="marginRight: 15px">请输入您要找回密码的登录账号:</span>
        <Input v-model="value" style="width: 220px" />
      </div>
      <div class="btn">
        <Button type="primary" @click="nexts">下一步</Button>
        <Button @click="cancel">取消</Button>
      </div>
    </Card>
    <!-- 找回密码弹窗 -->
    <Modal v-model="modal1" :closable="false" :footer-hide="true">
      <div class="user">
        <span style="marginRight: 15px">登录账号:</span>
        <Input v-model="user" style="width: 60%" disabled />
      </div>
      <div class="id">
        <span style="marginRight: 15px">身份证号:</span>
        <Input v-model="id" style="width: 60%" />
      </div>
      <div class="btn">
        <Button type="primary" @click="prev">上一步</Button>
        <Button type="primary" @click="reset">重置密码</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  name: 'forgetPassword',
  data() {
    return {
      value: '',
      modal1: false,
      user: '111',
      id: ''
    }
  },
  methods: {
    nexts() {
      if (this.value === '') {
        this.$Message.warning('请输入要找回密码的账号！')
      } else if (this.value != '111' && this.value != '222' && this.value !== '333') {
        this.$Message.warning('账号不存在！')
      } else {
        this.modal1 = true
      }
      console.log(typeof this.value)
    },
    cancel() {
      this.value = ''
      this.$router.push('/login')
    },
    prev() {
      this.modal1 = false
    },
    reset() {
      if (this.id !== '') {
        this.$Message.success('密码已重置为身份证后6位！')
        this.$router.push('/login')
      } else {
        this.$Message.warning('身份证号不能为空！')
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
.ivu-btn-default {
  background-color: rgb(233, 233, 233);
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