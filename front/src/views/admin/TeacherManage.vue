<template>
  <div class="teacherManage">
    <div class="queryWrap">
      <Input v-model="name" placeholder="请输入姓名或工号" style="width: 300px" />
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
    <div class="add">
      <Button type="primary" @click="modal=true">添加信息</Button>
    </div>
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
      <Page :transfer="true" :total="total" :current="pageIndex" v-model="pageSize" show-elevator show-total size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" />
    </div>
    <!-- 编辑&添加弹窗 -->
    <Modal class="model" v-model="modal" :closable="false" :footer-hide="true">
      <Form :model="edit" label-position="left" :label-width="100">
        <FormItem label="工号">
          <Input v-model="edit.jobNumber"></Input>
        </FormItem>
        <FormItem label="姓名">
          <Input v-model="edit.name"></Input>
        </FormItem>
        <FormItem label="院系">
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
      <p class="model-content">确认选择注销黄淑丽的信息吗？</p>
      <div class="btn">
        <Button type="primary" @click="confirm">确认</Button>
        <Button type="primary" @click="withdrawModal=false">取消</Button>
      </div>
    </Modal>
    <Spin v-if="loading" fix size="large"></Spin>
  </div>
</template>

<script>
export default {
  data() {
    return {
      total: 0,
      pageIndex: 1,
      pageSize: 10,
      loading: false,
      name: '',
      modal: false,
      withdrawModal: false,
      edit: {
        jobNumber: '',
        name: '',
        sex: '',
        courseName: '',
        className: ''
      },
      columns: [
        {
          title: '工号',
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
    this.getTeacher()
  },
  methods: {
    // 获取所有教师信息
    async getTeacher() {
      this.loading = true
      const result = await this.$service.teacher.getTeacher({
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.teachers
      }
    },
    // 分页
    pageChange(val) {
      this.pageIndex = val
      this.getStuInfo()
    },
    pageSizeChange(pageSize) {
      this.pageIndex = 1
      this.pageSize = pageSize
      this.getStuInfo()
    },
    handleSubmit() {
      this.$Message.success('修改成功！')
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
.ivu-page {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>