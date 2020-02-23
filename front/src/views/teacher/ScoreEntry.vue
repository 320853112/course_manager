<template>
  <div class="scoreEntry">
    <div class="queryWrap">
      <div class="courseName">
        <span>课程名称</span>
        <Select v-model="courseName" style="width:200px">
          <Option v-for="item in courseList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <div class="className">
        <span>班级</span>
        <Select v-model="className" style="width:200px">
          <Option v-for="item in classList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
    <div class="tableWrap">
      <Table border :columns="columns" :data="tableData"></Table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      courseName: '',
      className: '',
      courseList: ['网页设计与制作', 'C语言程序设计', '数据结构', '计算机组成原理'],
      classList: ['16本计算机2班', '16专软件1班', '16本英语5班'],
      columns: [
        {
          title: '学号',
          key: 'studentID',
          align: 'center'
        },
        {
          title: '姓名',
          key: 'name',
          align: 'center'
        },
        {
          title: '班级',
          key: 'className',
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
                props: {
                  max: 100,
                  min: 0
                },
                style: {
                  // marginRight: '5px'
                }
              })
            ])
          }
        }
      ],
      tableData: [
        {
          studentID: '2016030594',
          name: '邓藿',
          className: '16本计算机2班',
          courseName: '高等数学'
        }
      ]
    }
  },
  mounted() {},
  methods: {}
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
