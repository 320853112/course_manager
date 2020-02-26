<template>
  <div class="scoreQuery">
    <div class="queryWrap">
      <div class="courseNature">
        <span>课程类别</span>
        <Select v-model="category" @on-change="getCategoryVal" style="width:220px">
          <Option v-for="item in categoryList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </div>
      <div class="courseName">
        <span>课程名称</span>
        <Input v-model="courseName" style="width: 220px" />
      </div>
      <Button type="primary" @click="searchCourse">查询</Button>
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
      category: '',
      courseName: '',
      model1: '',
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
      columns: [
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
          title: '课程类别',
          key: 'category',
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
    // 得到下拉框的值
    getCategoryVal(val) {
      this.category = val
    },
    // 检索
    async searchCourse() {
      this.loading = true
      const result = await this.$service.course.getCourse({
        pageNum: this.pageIndex,
        pageSize: this.pageSize,
        name: this.courseName,
        category: this.category
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.courseList
        this.courseName = ''
        this.category = ''
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
