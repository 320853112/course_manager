<template>
  <div class="MyCourse">
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
      columns: [
        {
          title: '课程名称',
          key: 'name',
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
          title: '课程类型',
          key: 'studyType',
          align: 'center'
        },
        {
          title: '学分',
          key: 'credit',
          align: 'center'
        }
      ],
      tableData: []
    }
  },
  mounted() {
    this.getTeacherCourse()
  },
  methods: {
    async getTeacherCourse() {
      this.loading = true
      const result = await this.$service.teacher.getTeacherCourse({
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      })
      this.loading = false
      if (result.status) {
        this.total = result.data.totalCount
        this.tableData = result.data.courses
      }
    },
    // 分页
    pageChange(val) {
      this.pageIndex = val
      this.getTeacherCourse()
    },
    pageSizeChange(pageSize) {
      this.pageIndex = 1
      this.pageSize = pageSize
      this.getTeacherCourse()
    }
  }
}
</script>

<style lang="less" scoped>
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