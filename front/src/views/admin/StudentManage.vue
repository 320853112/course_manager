<template>
  <div class="studentManage">
    <div class="queryWrap">
      <div>
        <span>姓名</span>
        <Input v-model="value" placeholder="请输入姓名" style="width: 220px" />
      </div>
      <div>
        <span>学院</span>
        <Select v-model="college" style="width:220px" @on-change="getSearchCollegeVal">
          <Option v-for="item in collegeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </div>
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
      <p v-if="formValidate.userId" class="form-title">学生编辑</p>
      <p v-else class="form-title">学生新增</p>
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" label-position="left" :label-width="100">
        <FormItem label="学号" prop="id">
          <Input v-model="formValidate.id" :disabled="formValidate.userId ? true: false"></Input>
        </FormItem>
        <FormItem label="姓名" prop="name">
          <Input v-model="formValidate.name"></Input>
        </FormItem>
        <FormItem label="性别" prop="gender">
          <Select v-model="formValidate.gender" @on-change="getGenderVal">
            <Option v-for="item in genderList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="院系" prop="college">
          <Select v-model="formValidate.college" @on-change="getCollegeVal">
            <Option v-for="item in collegeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="专业" prop="major">
          <Input v-model="formValidate.major"></Input>
        </FormItem>
        <FormItem label="班级" prop="className">
          <Input v-model="formValidate.className"></Input>
        </FormItem>
        <FormItem label="密码" prop="password">
          <Input v-model="formValidate.password"></Input>
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
      college: '',
      modal: false,
      withdrawModal: false,
      formValidate: {
        id: '',
        name: '',
        gender: '',
        college: '',
        major: '',
        className: '',
        password: '',
        userId: '' // 新增&编辑标识
      },
      ruleValidate: {
        id: [{ required: true, message: '学号不能为空', trigger: 'blur' }],
        name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
        gender: [{ required: true, message: '性别不能为空', trigger: 'change' }],
        college: [{ required: true, message: '院系不能为空', trigger: 'change' }],
        major: [{ required: true, message: '专业不能为空', trigger: 'blur' }],
        className: [{ required: true, message: '班级不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      },
      genderList: [
        {
          value: 'male',
          label: '男'
        },
        {
          value: 'female',
          label: '女'
        }
      ],
      collegeList: [
        {
          value: '信息工程学院',
          label: '信息工程学院'
        },
        {
          value: '管理学院',
          label: '管理学院'
        },
        {
          value: '财经学院',
          label: '财经学院'
        },
        {
          value: '教育学院',
          label: '教育学院'
        },
        {
          value: '护理学院',
          label: '护理学院'
        },
        {
          value: '音乐舞蹈学院',
          label: '音乐舞蹈学院'
        },
        {
          value: '艺术设计学院',
          label: '艺术设计学院'
        },
        {
          value: '土木工程学院',
          label: '土木工程学院'
        },
        {
          value: '机械工程学院',
          label: '机械工程学院'
        }
      ],
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
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'p',
                {
                  props: {},
                  style: {},
                  on: {
                    click: () => {}
                  }
                },
                params.row.gender === 'female' ? '女' : '男'
              )
            ])
          }
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
                        params.row.gender,
                        params.row.college,
                        params.row.major,
                        params.row.className,
                        params.row.password,
                        params.row.userId
                      )
                      this.formValidate.userId = params.row.id
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
        name: this.value,
        college: this.college ? this.college : null
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.stuInfos
        this.value = ''
        this.college = ''
      }
    },
    // 编辑弹窗信息回显
    showStudent(id, name, gender, college, major, className, password, userId) {
      this.getStuInfo()
      this.modal = true
      this.formValidate.id = ''
      this.formValidate.name = ''
      this.formValidate.gender = ''
      this.formValidate.college = ''
      this.formValidate.major = ''
      this.formValidate.className = ''
      this.formValidate.password = ''
      if (userId) {
        this.formValidate.id = id
        this.formValidate.name = name
        this.formValidate.gender = gender
        this.formValidate.college = college
        this.formValidate.major = major
        this.formValidate.className = className
        this.formValidate.password = password
      } else {
        this.formValidate.userId = ''
      }
    },
    // 得到性别下拉框的值
    getSearchCollegeVal(val) {
      this.college = val
    },
    getGenderVal(val) {
      console.log(val)
      this.formValidate.gender = val
    },
    getCollegeVal(val) {
      this.formValidate.college = val
    },
    // 新增&编辑学生信息
    handleSubmit() {
      this.$refs.formValidate.validate(async valid => {
        if (valid) {
          if (this.formValidate.userId) {
            this.loading = true
            const result = await this.$service.student.updateStuInfo({
              id: this.formValidate.id,
              name: this.formValidate.name,
              gender: this.formValidate.gender,
              college: this.formValidate.college,
              major: this.formValidate.major,
              className: this.formValidate.className,
              password: this.formValidate.password
            })
            this.loading = false
            if (result.status) {
              this.getStuInfo()
              this.$Message.success('编辑成功！')
              this.modal = false
            }
          } else {
            this.loading = true
            const result = await this.$service.student.insertStuInfo({
              id: this.formValidate.id,
              name: this.formValidate.name,
              gender: this.formValidate.gender,
              college: this.formValidate.college,
              major: this.formValidate.major,
              className: this.formValidate.className,
              password: this.formValidate.password
            })
            this.loading = false
            if (result.status) {
              this.getStuInfo()
              this.$Message.success('新增成功！')
              this.modal = false
            }
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
.queryWrap span {
  margin-right: 15px;
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
