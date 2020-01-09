<template>
  <div class="studentManage">
    <div class="queryWrap">
      <Input v-model="value" placeholder="请输入姓名或学号" style="width: 300px" />
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
    <div class="add">
      <Button type="primary" @click="modal=true">添加信息</Button>
    </div>
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
    </div>
    <!-- 编辑&添加弹窗 -->
    <Modal class="model" v-model="modal" :closable="false" :footer-hide="true">
      <Form :model="edit" label-position="left" :label-width="100">
        <FormItem label="学号">
          <Input v-model="edit.studentID"></Input>
        </FormItem>
        <FormItem label="姓名">
          <Input v-model="edit.name"></Input>
        </FormItem>
        <FormItem label="院系">
          <Select v-model="edit.department">
            <Option value="信息工程学院">信息工程学院</Option>
            <Option value="管理学院">管理学院</Option>
            <Option value="艺术设计学院">艺术设计学院</Option>
            <Option value="文化与传媒学院">文化与传媒学院</Option>
            <Option value="土木工程学院">土木工程学院</Option>
            <Option value="机械工程学院">机械工程学院</Option>
            <Option value="财经学院">财经学院</Option>
            <Option value="音乐舞蹈学院">音乐舞蹈学院</Option>
            <Option value="护理学院">护理学院</Option>
          </Select>
        </FormItem>
        <FormItem label="专业">
          <Input v-model="edit.className"></Input>
        </FormItem>
        <FormItem label="班级">
          <Input v-model="edit.className"></Input>
        </FormItem>
        <FormItem label="密码">
          <Input v-model="edit.className"></Input>
        </FormItem>
      </Form>
      <div class="btn">
        <Button type="primary" @click="handleSubmit">确认</Button>
        <Button type="primary" @click="modal=false">取消</Button>
      </div>
    </Modal>
    <!-- 注销弹窗 -->
    <Modal class="model" v-model="withdrawModal" :closable="false" :footer-hide="true">
      <p class="model-title">注销提醒</p>
      <p class="model-content">确认选择注销邓藿的信息吗？</p>
      <div class="btn">
        <Button type="primary" @click="confirm">确认</Button>
        <Button type="primary" @click="withdrawModal=false">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: '',
      modal: false,
      withdrawModal: false,
      edit: {
        studentID: '',
        name: '',
        sex: '',
        className: '',
        department: ''
      },
      columns: [
        {
          title: '学号',
          key: 'id',
          align: 'center'
        },
        {
          title: '姓名',
          key: 'name',
          align: 'center'
        },
        {
          title: '院系',
          key: 'college',
          align: 'center'
        },
        {
          title: '专业',
          key: 'major',
          align: 'center'
        },
        {
          title: '班级',
          key: 'class_name',
          align: 'center'
        },
        {
          title: '密码',
          key: 'password',
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
                      this.modal = true
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
      tableData: []
    }
  },
  mounted() {
    this.getStuInfo()
  },
  methods: {
    // 返回所有学生信息
    async getStuInfo() {
      const result = await this.$service.student.getStuInfo({
        pageNum: 1,
        pageSize: 10
      })
      if (result.status) {
        this.tableData = result.data
      }
    },
    handleSubmit() {
      this.$Message.success('操作成功！')
      this.modal = false
    },
    confirm() {
      this.$Message.success('注销成功！')
      this.withdrawModal = false
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
