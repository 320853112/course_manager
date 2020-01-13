<template>
  <div class="teacherManage">
    <div class="queryWrap">
      <Input v-model="name" placeholder="请输入教师姓名" style="width: 300px" />
      <Button type="primary" @click="searchTeacher">查询</Button>
    </div>
    <Divider dashed />
    <div class="add">
      <Button type="primary" @click="showTeacher()">添加信息</Button>
    </div>
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
      <Page :transfer="true" :total="total" :current="pageIndex" v-model="pageSize" show-elevator show-total size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" />
    </div>
    <!-- 编辑&添加弹窗 -->
    <Modal class="model" v-model="modal" :closable="false" :footer-hide="true">
      <p v-if="formValidate.id" class="form-title">教师编辑</p>
      <p v-else class="form-title">教师新增</p>
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" label-position="left" :label-width="100">
        <FormItem label="工号" prop="id">
          <Input v-model="formValidate.id"></Input>
        </FormItem>
        <FormItem label="姓名" prop="name">
          <Input v-model="formValidate.name"></Input>
        </FormItem>
        <FormItem label="院系" prop="college">
          <Input v-model="formValidate.college"></Input>
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
      <p class="model-content">确认选择注销{{formValidate.name}}的信息吗？</p>
      <div class="btn">
        <Button type="primary" @click="deleteTeacher()">确认</Button>
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
      formValidate: {
        id: '',
        name: '',
        college: ''
      },
      ruleValidate: {
        id: [{ required: true, message: '工号不能为空', trigger: 'blur' }],
        name: [{ required: true, message: '名字不能为空', trigger: 'blur' }],
        college: [{ required: true, message: '学院不能为空', trigger: 'blur' }]
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
                      this.showTeacher(params.row.id, params.row.name, params.row.college)
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
                      this.formValidate.id = params.row.id
                      this.formValidate.name = params.row.name
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
    // 检索
    searchTeacher() {},
    async searchTeacher() {
      this.loading = true
      const result = await this.$service.teacher.getTeacher({
        pageNum: this.pageIndex,
        pageSize: this.pageSize,
        name: this.name
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.teachers
        this.name = ''
      }
    },
    // 编辑弹窗信息回显
    showTeacher(id, name, college) {
      this.getTeacher()
      this.modal = true
      this.formValidate.id = ''
      this.formValidate.name = ''
      this.formValidate.college = ''
      if (id) {
        this.formValidate.id = id
        this.formValidate.name = name
        this.formValidate.college = college
      } else {
        this.formValidate.id = ''
      }
    },
    // 新增&编辑教师信息
    async handleSubmit() {
      this.$refs.formValidate.validate(async valid => {
        if (valid) {
          if (this.formValidate.id) {
            alert('编辑')
          } else {
            alert('新增')
            const result = await this.$service.teacher.insertTeacher({
              id: this.formValidate.id,
              name: this.formValidate.name,
              college: this.formValidate.college
            })
            if (result.status) {
              this.getTeacher()
              this.$Message.success('新增成功！')
              this.modal = false
            }
          }
        }
      })
    },
    // 删除教师信息
    async deleteTeacher() {
      const result = await this.$service.teacher.deleteTeacher({
        id: this.formValidate.id
      })
      if (result.status) {
        this.getTeacher()
        this.$Message.success('注销成功！')
        this.withdrawModal = false
      } else {
        this.$Message.warning('注销失败！')
        this.withdrawModal = false
      }
    },
    // 分页
    pageChange(val) {
      this.pageIndex = val
      this.getTeacher()
    },
    pageSizeChange(pageSize) {
      this.pageIndex = 1
      this.pageSize = pageSize
      this.getTeacher()
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
.form-title {
  text-align: center;
  font-size: 18px;
  margin-bottom: 10px;
}
</style>
