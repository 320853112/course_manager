<template>
  <div class="termSchedule">
    <div class="queryWarp">
      <div class="week">
        <span>周次</span>
        <Select v-model="week" style="width:220px">
          <Option v-for="item in weekList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <div class="semester">
        <span>学年学期</span>
        <Select v-model="semester" style="width:220px">
          <Option v-for="item in semesterList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
  </div>
</template>

<script>
export default {
  data() {
    return {
      week: '',
      semester: '',
      weekList: [
        '第1周',
        '第2周',
        '第3周',
        '第4周',
        '第5周',
        '第6周',
        '第7周',
        '第8周',
        '第9周',
        '第10周',
        '第11周',
        '第12周',
        '第13周',
        '第14周',
        '第15周',
        '第16周'
      ],
      semesterList: ['2019-2020-1', '2018-2029-1', '2018-2029-2']
    }
  }
}
</script>

<style lang="less" scoped>
.queryWarp {
  display: flex;
  justify-content: space-between;
  span {
    margin-right: 15px;
  }
}
</style>
