<template>
  <div class="scoreQuery">
    <div class="queryWrap">
      <div class="classBegins">
        <span>开课时间</span>
        <Select v-model="classBegins" style="width:220px">
          <Option v-for="item in timeList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <div class="courseNature">
        <span>课程性质</span>
        <Select v-model="courseNature" style="width:220px">
          <Option v-for="item in natureList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <div class="courseName">
        <span>课程名称</span>
        <Input v-model="courseName" style="width: 220px" />
      </div>
      <Button type="primary">查询</Button>
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
      value: '',
      classBegins: '',
      timeList: ['全部学期', '2018-2019-1', '2018-2019-2', '2019-2020-1'],
      courseNature: '',
      natureList: ['专业课', '专业基础课', '公共基础课', '公共课', '其它'],
      courseName: '',
      model1: '',
      columns: [
        // {
        //   title: '开课学期',
        //   key: 'semester',
        //   align: 'center'
        // },
        {
          title: '课程编号',
          key: 'id',
          align: 'center'
        },
        {
          title: '课程名称',
          key: 'name',
          align: 'center'
        },
        {
          title: '成绩',
          key: 'score',
          align: 'center'
        },
        {
          title: '学分',
          key: 'credit',
          align: 'center'
        },
        {
          title: '考核方式',
          key: 'checkType',
          align: 'center'
        },
        {
          title: '课程属性',
          key: 'category',
          align: 'center'
        },
        {
          title: '课程性质',
          key: 'studyType',
          align: 'center'
        }
      ],
      tableData: []
    }
  },
  mounted() {
    this.getStuCourse()
  },
  methods: {
    // 学生已选课程
    async getStuCourse() {
      const result = await this.$service.course.getStuCourse({
        id: '2016030594',
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      })
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.courseList
      }
    },
    // 分页
    pageChange(val) {
      this.pageIndex = val
      this.getStuCourse()
    },
    pageSizeChange(pageSize) {
      this.pageIndex = 1
      this.pageSize = pageSize
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
.ivu-page {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
