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
    </div>
    <!-- 编辑&添加弹窗 -->
    <Modal class="model" v-model="modal" :closable="false" :footer-hide="true">
      <Form :model="edit" label-position="left" :label-width="100">
        <FormItem label="课程号">
          <Input v-model="edit.courseNumber"></Input>
        </FormItem>
        <FormItem label="课程名称">
          <Input v-model="edit.courseName"></Input>
        </FormItem>
        <FormItem label="授课教师">
          <Input v-model="edit.teacher"></Input>
        </FormItem>
        <FormItem label="上课时间">
          <Input v-model="edit.time"></Input>
        </FormItem>
        <FormItem label="上课地点">
          <Input v-model="edit.address"></Input>
        </FormItem>
        <FormItem label="周数">
          <Select v-model="edit.weeks">
            <Option value="add">单周</Option>
            <Option value="even">双周</Option>
            <Option value="all">1-16周</Option>
          </Select>
        </FormItem>
        <FormItem label="课程类型">
          <Select v-model="edit.type">
            <Option value="obligatory">必修</Option>
            <Option value="elective">选修</Option>
          </Select>
        </FormItem>
        <FormItem label="学分">
          <Select v-model="edit.credit">
            <Option value="one">1</Option>
            <Option value="two">2</Option>
            <Option value="three">3</Option>
            <Option value="four">4</Option>
          </Select>
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      courseName: '',
      modal: false,
      withdrawModal: false,
      edit: {
        courseNumber: '',
        courseName: '',
        teacher: '',
        time: '',
        address: '',
        weeks: '',
        type: '',
        credit: ''
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
      const result = await this.$service.course.getCourse({
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
