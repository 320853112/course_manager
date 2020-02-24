<template>
  <div class="electiveCenter">
    <div class="queryWrap">
      <div class="course">
        <span>课程</span>
        <Input v-model="courseName" style="width: 200px" />
      </div>
      <Button type="primary" @click="searchCourse()">查询</Button>
    </div>
    <Divider dashed />
    <div class="tableWrap">
      <Table border :columns="columnsSelection" :data="selectionData"></Table>
      <Page :transfer="true" :total="total" :current="pageIndex" v-model="pageSize" show-elevator show-total size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" />
    </div>
    <Divider dashed />
    <!-- 选课结果及退选折叠面板 -->
    <Collapse v-model="withdrawal">
      <Panel name="1">
        选课结果及退选
        <div slot="content">
          <Table border :columns="columnsOut" :data="outData"></Table>
          <Page :transfer="true" :total="totalOut" :current="pageIndexOut" v-model="pageSizeOut" show-elevator show-total size="small" @on-change="pageChangeOut" @on-page-size-change="pageSizeChangeOut" />
        </div>
      </Panel>
    </Collapse>
    <!-- 选课弹窗 -->
    <Modal class="model" v-model="selectModal" :closable="false" :footer-hide="true">
      <p class="model-title">确认选课</p>
      <p class="model-content">确认选择{{selectCourseName}}课程吗？</p>
      <div class="btn">
        <Button type="primary" @click="selectConfirm">确认</Button>
        <Button type="primary" @click="selectModal=false">取消</Button>
      </div>
    </Modal>
    <!-- 退选弹窗 -->
    <Modal class="model" v-model="outModal" :closable="false" :footer-hide="true">
      <p class="model-title">确认退选</p>
      <p class="model-content">确认退选{{outCourseName}}课程吗？</p>
      <div class="btn">
        <Button type="primary" @click="withdrawalConfirm">确认</Button>
        <Button type="primary" @click="outModal=false">取消</Button>
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
      totalOut: 0,
      pageIndexOut: 1,
      pageSizeOut: 10,
      loading: false,
      courseName: '',
      selectModal: false,
      outModal: false,
      withdrawal: '1',
      selectCourseName: '',
      outCourseName: '',
      selectCourseId: '',
      outCourseId: '',
      columnsSelection: [
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
          key: 'operation',
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
                  style: {},
                  on: {
                    click: () => {
                      this.selectCourseName = params.row.name
                      this.selectCourseId = params.row.id
                      this.selectModal = true
                    }
                  }
                },
                '选课'
              )
            ])
          }
        }
      ],
      selectionData: [],
      columnsOut: [
        {
          title: '课程号',
          key: 'id',
          align: 'center'
        },
        {
          title: '课程名',
          key: 'name',
          align: 'center'
        },
        {
          title: '学分',
          key: 'credit',
          align: 'center'
        },
        {
          title: '上课教师',
          key: 'teacher',
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
          title: '选课状态',
          key: 'category',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'p',
                {
                  props: {},
                  style: {}
                },
                '选中/退选'
              )
            ])
          }
        },
        {
          title: '操作',
          key: 'operation',
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
                  style: {},
                  on: {
                    click: () => {
                      this.outCourseName = params.row.name
                      this.outCourseId = params.row.id
                      this.outModal = true
                    }
                  }
                },
                '退选'
              )
            ])
          }
        }
      ],
      outData: []
    }
  },
  mounted() {
    this.getCourse()
    this.getStuCourse()
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
        this.selectionData = result.data.courseList
      }
    },
    // 检索
    async searchCourse() {
      this.loading = true
      const result = await this.$service.course.getCourse({
        pageNum: this.pageIndex,
        pageSize: this.pageSize,
        name: this.courseName
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.selectionData = result.data.courseList
        this.courseName = ''
      }
    },
    // 学生已选课程
    async getStuCourse() {
      this.loading = true
      const result = await this.$service.course.getStuCourse({
        id: '2016030594',
        pageNum: this.pageIndexOut,
        pageSize: this.pageSizeOut
      })
      this.loading = false
      if (result.status) {
        this.totalOut = result.data.totalCount
        this.outData = result.data.courseList
      }
    },
    // 选课
    async selectConfirm() {
      this.loading = true
      const result = await this.$service.course.selectCourse({
        stuId: '2016030594',
        courseId: this.selectCourseId
      })
      this.loading = false
      if (result.status) {
        this.getCourse()
        this.getStuCourse()
      }
      this.$Message.success('选课成功！')
      this.selectModal = false
    },
    // 退选
    withdrawalConfirm() {
      this.$Message.success('退选成功！')
      this.outModal = false
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
    pageChangeOut(val) {
      this.pageIndexOut = val
      this.getStuCourse()
    },
    pageSizeChangeOut(pageSize) {
      this.pageIndexOut = 1
      this.pageSizeOut = pageSize
      this.getStuCourse()
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
