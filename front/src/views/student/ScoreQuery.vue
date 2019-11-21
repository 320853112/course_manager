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
      <Table border :columns="columns1" :data="data1"></Table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: '',
      classBegins: '',
      timeList: ['全部学期', '2018-2019-1', '2018-2019-2', '2019-2020-1'],
      courseNature: '',
      natureList: ['专业课', '专业基础课', '公共基础课', '公共课', '其它'],
      courseName: '',
      model1: '',
      columns1: [
        // {
        //   title: '序号',
        //   key: 'order',
        //   align: 'center'
        // },
        {
          title: '开课学期',
          key: 'semester',
          align: 'center'
        },
        {
          title: '课程编号',
          key: 'number',
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
          key: 'assessmentMethod',
          align: 'center'
        },
        {
          title: '考试性质',
          key: 'examinationNature',
          align: 'center'
        },
        {
          title: '课程属性',
          key: 'attribute',
          align: 'center'
        },
        {
          title: '课程性质',
          key: 'courseNature',
          align: 'center'
        }
      ],
      data1: [
        {
          // order: '1',
          semester: '2019-2020-1',
          number: '	300036',
          name: '软件测试',
          score: '86',
          credit: '2',
          assessmentMethod: '考试',
          examinationNature: '正常考试',
          attribute: '必修',
          courseNature: '专业课'
        },
        {
          // order: '2',
          semester: '2019-2020-1',
          number: '	030300',
          name: '公关礼仪',
          score: '82',
          credit: '1',
          assessmentMethod: '考查',
          examinationNature: '正常考试',
          attribute: '选修',
          courseNature: '公共课'
        }
      ]
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
</style>
