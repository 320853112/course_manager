<template>
  <div class="teacherManage">
    <div class="queryWrap">
      <Input v-model="name" placeholder="请输入姓名或工号" style="width: 300px" />
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
    <div class="add">
      <Button type="primary" @click="addInfo">添加信息</Button>
    </div>
    <div class="tableWrap">
      <Table border :columns="columns1" :data="data1"></Table>
    </div>
    <!-- 编辑弹窗 -->
    <Modal class="model" v-model="editModal" :closable="false" :footer-hide="true">
      <Form :model="edit" label-position="left" :label-width="100">
        <FormItem label="工号">
          <Input v-model="edit.jobNumber"></Input>
        </FormItem>
        <FormItem label="姓名">
          <Input v-model="edit.name"></Input>
        </FormItem>
        <FormItem label="性别">
          <Select v-model="edit.sex">
            <Option value="male">男</Option>
            <Option value="female">女</Option>
          </Select>
        </FormItem>
        <FormItem label="课程">
          <Select v-model="edit.courseName">
            <Option value="数据结构">数据结构</Option>
            <Option value="离散数学">离散数学</Option>
            <Option value="网页设计与制作">网页设计与制作</Option>
            <Option value="C语言程序设计">C语言程序设计</Option>
            <Option value="大学英语">大学英语</Option>
          </Select>
        </FormItem>
        <FormItem label="班级">
          <Input v-model="edit.className"></Input>
        </FormItem>
      </Form>
      <div class="btn">
        <Button type="primary" @click="ok">确认</Button>
        <Button type="primary" @click="cancel">取消</Button>
      </div>
    </Modal>
    <!-- 注销弹窗 -->
    <Modal class="model" v-model="withdrawModal" :closable="false" :footer-hide="true">
      <p class="model-title">注销提醒</p>
      <p class="model-content">确认选择注销黄淑丽的信息吗？</p>
      <div class="btn">
        <Button type="primary" @click="confirm">确认</Button>
        <Button type="primary" @click="off">取消</Button>
      </div>
    </Modal>
    <!-- 添加弹窗 -->
    <Modal class="model" v-model="addModal" :closable="false" :footer-hide="true">
      <Form :model="add" label-position="left" :label-width="100">
        <FormItem label="工号">
          <Input v-model="add.jobNumber"></Input>
        </FormItem>
        <FormItem label="姓名">
          <Input v-model="add.name"></Input>
        </FormItem>
        <FormItem label="性别">
          <Select v-model="add.sex">
            <Option value="male">男</Option>
            <Option value="female">女</Option>
          </Select>
        </FormItem>
        <FormItem label="身份证号">
          <Input v-model="add.IDCard"></Input>
        </FormItem>
      </Form>
      <div class="btn">
        <Button type="primary" @click="determine">确认</Button>
        <Button type="primary" @click="remove">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: '',
      editModal: false,
      withdrawModal: false,
      addModal: false,
      edit: {
        jobNumber: '',
        name: '',
        sex: '',
        courseName: '',
        className: ''
      },
      add: {
        jobNumber: '',
        name: '',
        sex: '',
        IDCard: ''
      },
      columns1: [
        {
          title: '工号',
          key: 'jobNumber',
          align: 'center'
        },
        {
          title: '姓名',
          key: 'name',
          align: 'center'
        },
        {
          title: '性别',
          key: 'sex',
          align: 'center'
        },
        {
          title: '操作',
          key: 'id',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'Button',
                {
                  props: {
                    size: 'small',
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.editModal = true
                    }
                  }
                },
                '编辑'
              ),
              h(
                'Button',
                {
                  props: {
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.withdrawModal = true
                    }
                  }
                },
                '注销'
              )
            ])
          }
        }
      ],
      data1: [
        {
          jobNumber: '20100910',
          name: '黄淑丽',
          sex: '女',
          age: 32
        }
      ]
    }
  },
  methods: {
    ok() {
      this.$Message.success('修改成功！')
      this.editModal = false
    },
    cancel() {
      this.$Message.warning('修改失败！')
      this.editModal = false
    },
    confirm() {
      this.$Message.success('注销成功！')
      this.withdrawModal = false
    },
    off() {
      this.$Message.warning('注销失败！')
      this.withdrawModal = false
    },
    addInfo() {
      this.addModal = true
    },
    determine() {
      this.$Message.success('添加成功！')
      this.addModal = false
    },
    remove() {
      this.$Message.warning('添加失败！')
      this.addModal = false
    }
  }
}
</script>


<style lang="less" scoped>
.queryWrap {
  display: flex;
  justify-content: space-between;
}
.add {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
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