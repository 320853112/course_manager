<template>
  <div class="courseManage">
    <div class="queryWrap">
      <div>
        <span>课程类别</span>
        <Select v-model="category" @on-change="getCategoryVal" style="width:220px">
          <Option v-for="item in categoryList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </div>
      <div>
        <span>课程名称</span>
        <Input v-model="courseName" placeholder="请输入课程名称" style="width: 220px" />
      </div>
      <Button type="primary" @click="searchCourse()">查询</Button>
    </div>
    <Divider dashed />
    <div class="addCourse">
      <Button type="primary" @click="showCourse()">添加课程</Button>
    </div>
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
      <Page :transfer="true" :total="total" :current="pageIndex" v-model="pageSize" show-elevator show-total size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" />
    </div>
    <!-- 编辑&添加弹窗 -->
    <Modal class="model" v-model="modal" :closable="false" :footer-hide="true">
      <div class="form-title" v-if="formValidate.userId">课程编辑</div>
      <div class="form-title" v-else>课程新增</div>
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" label-position="left" :label-width="100">
        <FormItem label="课程号" prop="id">
          <Input v-model="formValidate.id"></Input>
        </FormItem>
        <FormItem label="课程名称" prop="name">
          <Input v-model="formValidate.name"></Input>
        </FormItem>
        <FormItem label="课程类别" prop="category">
          <Select v-model="formValidate.category" @on-change="getCategoryVal">
            <Option v-for="item in categoryList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="授课教师" prop="teacher">
          <Input v-model="formValidate.teacher"></Input>
        </FormItem>
        <FormItem label="上课时间" prop="timeWeek">
          <Select v-model="formValidate.timeWeek" @on-change="getWeekVal">
            <Option v-for="item in weekList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="上课地点" prop="place">
          <Input v-model="formValidate.place"></Input>
        </FormItem>
        <FormItem label="学分" prop="credit">
          <Input v-model="formValidate.credit"></Input>
        </FormItem>
        <FormItem label="剩余量" prop="surplus">
          <Input v-model="formValidate.surplus"></Input>
        </FormItem>
      </Form>
      <div class="btn">
        <Button type="primary" @click="handleSubmit">确认</Button>
        <Button type="primary" @click="modal=false">取消</Button>
      </div>
    </Modal>
    <!-- 删除弹窗 -->
    <Modal class="model" v-model="withdrawModal" :closable="false" :footer-hide="true">
      <p class="model-title">删除提醒</p>
      <p class="model-content">确认选择删除课程{{formValidate.name}}吗？</p>
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
      courseName: '',
      category: '',
      modal: false,
      withdrawModal: false,
      formValidate: {
        id: '',
        name: '',
        category: '',
        teacher: '',
        timeWeek: '',
        place: '',
        credit: '',
        surplus: '',
        userId: '' // 新增&编辑标识
      },
      ruleValidate: {
        id: [{ required: true, message: '课程号不能为空', trigger: 'blur' }],
        name: [{ required: true, message: '课程名称不能为空', trigger: 'blur' }],
        category: [{ required: true, message: '课程类别不能为空', trigger: 'change' }],
        teacher: [{ required: true, message: '授课教师不能为空', trigger: 'blur' }],
        timeWeek: [{ required: true, message: '上课时间不能为空', trigger: 'change' }],
        place: [{ required: true, message: '上课地点不能为空', trigger: 'blur' }],
        credit: [{ required: true, message: '学分不能为空', trigger: 'blur' }],
        surplus: [{ required: true, message: '剩余量不能为空', trigger: 'blur' }]
      },
      categoryList: [
        {
          value: '专业课',
          label: '专业课'
        },
        {
          value: '公共课',
          label: '公共课'
        },
        {
          value: '专业基础课',
          label: '专业基础课'
        },
        {
          value: '公共基础课',
          label: '公共基础课'
        },
        {
          value: '其它',
          label: '其它'
        }
      ],
      weekList: [
        {
          value: '周一',
          label: '周一'
        },
        {
          value: '周二',
          label: '周二'
        },
        {
          value: '周三',
          label: '周三'
        },
        {
          value: '周四',
          label: '周四'
        },
        {
          value: '周五',
          label: '周五'
        },
        {
          value: '周六',
          label: '周六'
        },
        {
          value: '周日',
          label: '周日'
        }
      ],
      columns: [
        {
          title: '课程号',
          key: 'id',
          align: 'center'
        },
        {
          title: '课程名称',
          key: 'name',
          align: 'center'
        },
        {
          title: '授课教师',
          key: 'teacher',
          align: 'center'
        },
        {
          title: '课程类别',
          key: 'category',
          align: 'center'
        },
        {
          title: '上课时间',
          key: 'timeWeek',
          align: 'center'
        },
        {
          title: '上课地点',
          key: 'place',
          align: 'center'
        },
        {
          title: '学分',
          key: 'credit',
          align: 'center'
        },
        {
          title: '剩余量',
          key: 'surplus',
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
                      this.showCourse(
                        params.row.id,
                        params.row.name,
                        params.row.category,
                        params.row.teacher,
                        params.row.timeWeek,
                        params.row.place,
                        params.row.credit,
                        params.row.surplus,
                        params.row.userId
                      )
                      this.formValidate.userId = params.row.userId
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
                '删除'
              )
            ])
          }
        }
      ],
      tableData: []
    }
  },
  mounted() {
    this.getCourse()
  },
  methods: {
    // 获取所有课程信息
    async getCourse() {
      this.loading = true
      const result = await this.$service.course.getCourse({
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.courseList
      }
    },
    // 检索
    async searchCourse() {
      this.loading = true
      const result = await this.$service.course.getCourse({
        pageNum: this.pageIndex,
        pageSize: this.pageSize,
        name: this.courseName,
        category: this.category ? this.category : null
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.courseList
        this.courseName = ''
        this.category = ''
      }
    },
    // 得到下拉框的值
    getCategoryVal(val) {
      this.formValidate.category = val
    },
    getWeekVal(val) {
      this.formValidate.timeWeek = val
    },
    // 编辑弹窗信息回显
    showCourse(id, name, category, teacher, timeWeek, place, credit, surplus, userId) {
      this.getCourse()
      this.modal = true
      this.formValidate.id = ''
      this.formValidate.name = ''
      this.formValidate.category = ''
      this.formValidate.teacher = ''
      this.formValidate.timeWeek = ''
      this.formValidate.place = ''
      this.formValidate.credit = ''
      this.formValidate.surplus = ''
      if (userId) {
        this.formValidate.id = id
        this.formValidate.name = name
        this.formValidate.category = category
        this.formValidate.teacher = teacher
        this.formValidate.timeWeek = timeWeek
        this.formValidate.place = place
        this.formValidate.credit = credit
        this.formValidate.surplus = surplus
      } else {
        this.formValidate.userId = ''
      }
    },
    // 新增&编辑课程信息
    async handleSubmit() {
      this.$refs.formValidate.validate(async valid => {
        if (valid) {
          if (this.formValidate.userId) {
            this.loading = true
            const result = await this.$service.course.updateCourse({
              id: this.formValidate.id,
              name: this.formValidate.name,
              category: this.formValidate.category,
              teacher: this.formValidate.teacher,
              timeWeek: this.formValidate.timeWeek,
              place: this.formValidate.place,
              credit: this.formValidate.credit,
              surplus: this.formValidate.surplus
            })
            this.loading = false
            if (result.status) {
              this.getCourse()
              this.$Message.success('编辑成功！')
              this.modal = false
            }
          } else {
            this.loading = true
            const result = await this.$service.course.insertCourse({
              id: this.formValidate.id,
              name: this.formValidate.name,
              category: this.formValidate.category,
              teacher: this.formValidate.teacher,
              timeWeek: this.formValidate.timeWeek,
              place: this.formValidate.place,
              credit: this.formValidate.credit,
              surplus: this.formValidate.surplus,
              teacherId: this.formValidate.teacherId
            })
            this.loading = false
            if (result.status) {
              this.getCourse()
              this.$Message.success('新增成功！')
              this.modal = false
            }
          }
        }
      })
    },
    // 删除
    async confirm() {
      this.loading = true
      const result = await this.$service.course.deleteCourse({
        id: this.formValidate.id
      })
      this.loading = false
      if (result.status) {
        this.getCourse()
        this.$Message.success('删除成功！')
        this.withdrawModal = false
      } else {
        this.$Message.warning('删除失败！')
        this.withdrawModal = false
      }
    },
    // 分页
    pageChange(val) {
      this.pageIndex = val
      this.getCourse()
    },
    pageSizeChange(pageSize) {
      this.pageIndex = 1
      this.pageSize = pageSize
      this.getCourse()
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
.addCourse {
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
