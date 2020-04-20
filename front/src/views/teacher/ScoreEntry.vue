<template>
  <div class="scoreEntry">
    <div class="queryWrap">
      <div class="courseName">
        <span>课程名称</span>
        <Input v-model="courseName" placeholder="请输入课程名称" style="width: 220px" />
      </div>
      <Button type="primary" @click="searchData()">查询</Button>
    </div>
    <Divider dashed />
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
      <Page :transfer="true" :total="total" :current="pageIndex" v-model="pageSize" show-elevator show-total size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      total: 0,
      pageIndex: 1,
      pageSize: 10,
      stuId: '', //学号
      courseId: '', //课程号
      score: '', //分数
      courseName: '',
      columns: [
        {
          title: '学号',
          key: 'stuId',
          align: 'center'
        },
        {
          title: '姓名',
          key: 'stuName',
          align: 'center'
        },
        {
          title: '班级',
          key: 'className',
          align: 'center'
        },
        {
          title: '课程号',
          key: 'courseId',
          align: 'center'
        },
        {
          title: '课程',
          key: 'courseName',
          align: 'center'
        },
        {
          title: '分数',
          key: 'score',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('InputNumber', {
                attrs: {
                  min: 0,
                  max: 100,
                  value: params.row.score
                },
                on: {
                  'on-change': event => {
                    this.stuId = params.row.stuId
                    this.courseId = params.row.courseId
                    this.score = event
                  },
                  'on-blur': () => {
                    this.setScoreByStu()
                  }
                }
              })
            ])
          }
        }
      ],
      tableData: []
    }
  },
  mounted() {
    this.getStuByCourse()
  },
  methods: {
    // 教师获取自己所授课程的学生
    async getStuByCourse() {
      this.loading = true
      const result = await this.$service.teacher.getStuByCourse({
        teacherId: localStorage.getItem('stuId'),
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.studentCourse
      }
    },
    // 检索
    async searchData() {
      this.loading = true
      const result = await this.$service.teacher.getStuByCourse({
        teacherId: localStorage.getItem('stuId'),
        courseName: this.courseName ? this.courseName : null,
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.studentCourse
        this.courseName = ''
      }
    },
    // 为课程打分
    async setScoreByStu() {
      this.loading = true
      const result = await this.$service.teacher.setScoreByStu({
        stuId: this.stuId, // 学号
        courseId: this.courseId,
        score: this.score
      })
      this.loading = false
      if (result.status) {
        this.getStuByCourse()
        this.$Message.success('操作成功！')
      }
    },
    // 分页
    pageChange(val) {
      this.pageIndex = val
      this.getStuByCourse()
    },
    pageSizeChange(pageSize) {
      this.pageIndex = 1
      this.pageSize = pageSize
      this.getStuByCourse()
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
.ivu-page {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
