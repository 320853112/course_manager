<template>
  <div class="studentManage">
    <div class="queryWrap">
      <Input v-model="value" placeholder="请输入姓名" style="width: 300px" />
      <Button type="primary" @click="searchStu">查询</Button>
    </div>
    <Divider dashed />
    <div class="add">
      <Button type="primary" @click="showStudent()">添加信息</Button>
    </div>
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
      <Page :transfer="true" :total="total" :current="pageIndex" v-model="pageSize" show-elevator show-total size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" />
    </div>
    <!-- 编辑&添加弹窗 -->
    <Modal class="model" v-model="modal" :closable="false" :footer-hide="true">
      <p v-if="formValidate.id" class="form-title">学生编辑</p>
      <p v-else class="form-title">学生新增</p>
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" label-position="left" :label-width="100">
        <FormItem label="学号" prop="id">
          <Input v-model="formValidate.id"></Input>
        </FormItem>
        <FormItem label="姓名" prop="name">
          <Input v-model="formValidate.name"></Input>
        </FormItem>
        <FormItem label="院系" prop="college">
          <Select v-model="formValidate.college">
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
        <FormItem label="专业" prop="major">
          <Input v-model="formValidate.major"></Input>
        </FormItem>
        <FormItem label="班级" prop="className">
          <Input v-model="formValidate.className"></Input>
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
        <Button type="primary" @click="deleteStuInfo()">确认</Button>
        <Button type="primary" @click="withdrawModal=false">取消</Button>
      </div>
    </Modal>
    <Spin v-if="loading" fix size="large"></Spin>
  </div>
</template>

<script>
import { ELOOP } from 'constants'
export default {
  data() {
    return {
      total: 0,
      pageIndex: 1,
      pageSize: 10,
      loading: false,
      value: '',
      modal: false,
      withdrawModal: false,
      formValidate: {
        id: '',
        name: '',
        college: '',
        major: '',
        className: ''
      },
      ruleValidate: {
        id: [{ required: true, message: '学号不能为空', trigger: 'blur' }],
        name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
        college: [{ required: true, message: '院系不能为空', trigger: 'change' }],
        major: [{ required: true, message: '专业不能为空', trigger: 'blur' }],
        className: [{ required: true, message: '班级不能为空', trigger: 'blur' }]
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
          title: '性别',
          key: 'gender',
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
          key: 'className',
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
                      this.showStudent(
                        params.row.id,
                        params.row.name,
                        params.row.college,
                        params.row.major,
                        params.row.className
                      )
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
    this.getStuInfo()
  },
  methods: {
    // 返回所有学生信息
    async getStuInfo() {
      this.loading = true
      const result = await this.$service.student.getStuInfo({
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.stuInfos
      }
    },
    // 检索
    async searchStu() {
      this.loading = true
      const result = await this.$service.student.getStuInfo({
        pageNum: this.pageIndex,
        pageSize: this.pageSize,
        name: this.value
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.stuInfos
        this.value = ''
      }
    },
    // 编辑弹窗信息回显
    showStudent(id, name, college, major, class_name) {
      this.getStuInfo()
      this.modal = true
      this.formValidate.id = ''
      this.formValidate.name = ''
      this.formValidate.college = ''
      this.formValidate.major = ''
      this.formValidate.className = ''
      if (id) {
        this.formValidate.id = id
        this.formValidate.name = name
        this.formValidate.college = college
        this.formValidate.major = major
        this.formValidate.className = className
      } else {
        this.formValidate.id = ''
      }
    },
    // 新增&编辑学生信息
    handleSubmit() {
      this.$refs.formValidate.validate(async valid => {
        if (valid) {
          if (this.formValidate.id) {
            alert('编辑')
          } else {
            alert('新增')
          }
        }
      })
    },
    // 删除学生信息
    async deleteStuInfo() {
      const result = await this.$service.student.deleteStuInfo({
        id: this.formValidate.id
      })
      if (result.status) {
        this.getStuInfo()
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
      this.getStuInfo()
    },
    pageSizeChange(pageSize) {
      this.pageIndex = 1
      this.pageSize = pageSize
      this.getStuInfo()
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
