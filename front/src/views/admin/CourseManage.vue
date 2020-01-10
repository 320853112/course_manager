<template>
  <div class="courseManage">
    <div class="queryWrap">
      <Input v-model="courseName" placeholder="请输入课程号或课程名称" style="width: 300px" />
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
    <div class="addCourse">
      <Button type="primary" @click="modal=true">添加课程</Button>
    </div>
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
      <Page :transfer="true" :total="total" :current="pageIndex" v-model="pageSize" show-elevator show-total size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" />
    </div>
    <!-- 编辑&添加弹窗 -->
    <Modal class="model" v-model="modal" :closable="false" :footer-hide="true">
      <div class="add-edit" v-if="edit.id">课程新增</div>
      <div class="add-edit" v-else>课程编辑</div>
      <Form :model="edit" label-position="left" :label-width="100">
        <FormItem label="课程号">
          <Input v-model="edit.id"></Input>
        </FormItem>
        <FormItem label="课程名称">
          <Input v-model="edit.name"></Input>
        </FormItem>
        <FormItem label="授课教师">
          <Input v-model="edit.teacher"></Input>
        </FormItem>
        <FormItem label="上课时间">
          <Input v-model="edit.time_week"></Input>
        </FormItem>
        <FormItem label="课程类型">
          <Select v-model="edit.category">
            <Option value="obligatory">必修</Option>
            <Option value="elective">选修</Option>
          </Select>
        </FormItem>
        <FormItem label="学分">
          <Input v-model="edit.credit"></Input>
        </FormItem>
        <FormItem label="剩余量">
          <Input v-model="edit.surplus"></Input>
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
      <p class="model-content">确认选择删除吗？</p>
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
      modal: false,
      withdrawModal: false,
      edit: {
        id: '',
        name: '',
        teacher: '',
        time_week: '',
        category: '',
        credit: '',
        surplus: ''
      },
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
          title: '上课时间',
          key: 'time_week',
          align: 'center'
        },
        {
          title: '课程类型',
          key: 'category',
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
                      this.modal = true
                      this.edit.id = params.row.id
                      this.editModal(params.row)
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
    // 新增课程
    async insertCourse() {
      // this.loading = true
      const result = await this.$service.course.insertCourse({
        id: this.edit.id,
        name: this.edit.name,
        teacher: this.edit.teacher,
        time_week: this.edit.time_week,
        category: this.edit.category,
        credit: this.edit.credit,
        surplus: this.edit.surplus
      })
      // this.loading = false
      if (result.status) {
        this.getCourse()
      } else {
        this.$Message.warning('课程添加失败')
      }
    },
    // 弹窗信息回显
    async editModal(row) {
      this.edit.id = row.id
      this.edit.name = row.name
      this.edit.teacher = row.teacher
      this.edit.time_week = row.time_week
      this.edit.category = row.category
      this.edit.credit = row.credit
      this.edit.surplus = row.surplus
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
    },
    handleSubmit() {
      if (this.edit.id) {
        console.log('编辑')
      } else {
        console.log('新增')
      }
      this.modal = false
    },
    confirm() {
      this.$Message.success('删除成功！')
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
.addCourse {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
}
.add-edit {
  color: red;
  text-align: center;
  font-size: 20px;
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
