<template>
  <div class="termSchedule">
    <div class="queryWarp">
      <!-- <div class="week">
        <span>周次</span>
        <Select v-model="week" style="width:220px">
          <Option v-for="item in weekList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div> -->
      <!-- <div class="semester">
        <span>学年学期</span>
        <Select v-model="semester" style="width:220px">
          <Option v-for="item in semesterList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <Button type="primary">查询</Button> -->
    </div>
    <!-- <Divider dashed /> -->
    <!-- ------ -->

    <table>
      <thead>
        <tr>
          <th>时间</th>
          <th v-for='(week, index) in weeks' :key='index'> {{'周' + digital2Chinese(index+1, 'week')}}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for='(item, index) in classTableData' :key='index'>
          <td>
            <p>{{'第' + digital2Chinese(index+1) + '节'}}</p>
            <p class='period'>{{ item.classesTime }}</p>
          </td>

          <td v-for='(week, index) in weeks' :key='index'>
            {{item[week]  || '-'}}
          </td>
        </tr>
      </tbody>
    </table>

    <!-- ------ -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      weeks: ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'],
      classTableData: [
        {
          classesTime: '08:30-10:05',
          monday: '物理',
          tuesday: '英语',
          wednesday: '政治',
          thursday: '历史',
          friday: '化学',
          saturday: '历史',
          sunday: '化学'
        },
        {
          classesTime: '10:15-11:55',
          monday: '生物',
          tuesday: '物理',
          wednesday: '化学',
          thursday: '英语',
          friday: '化学',
          saturday: '生物',
          sunday: '化学'
        },
        // {
        //   classesTime: '10:00-11:00',
        //   monday: '生物',
        //   tuesday: '物理',
        //   wednesday: '生物',
        //   thursday: '历史',
        //   friday: '生物',
        //   saturday: '英语',
        //   sunday: '政治'
        // },
        // {
        //   classesTime: '11:00-12:00',
        //   monday: '',
        //   tuesday: '政治',
        //   wednesday: '物理',
        //   thursday: '政治',
        //   friday: '历史',
        //   saturday: '历史',
        //   sunday: '生物'
        // },
        // {
        //   classesTime: '13:00-14:00',
        //   monday: '生物',
        //   tuesday: '历史',
        //   wednesday: '历史',
        //   thursday: '历史',
        //   friday: '',
        //   saturday: '英语',
        //   sunday: '化学'
        // },
        {
          classesTime: '14:00-15:50',
          monday: '化学',
          tuesday: '英语',
          wednesday: '物理',
          thursday: '化学',
          friday: '语文',
          saturday: '物理',
          sunday: '英语'
        },
        {
          classesTime: '16:00-17:50',
          monday: '历史',
          tuesday: '历史',
          wednesday: '语文',
          thursday: '历史',
          friday: '生物',
          saturday: '英语',
          sunday: ''
        },
        {
          classesTime: '19:30-21:00',
          monday: '物理',
          tuesday: '生物',
          wednesday: '英语',
          thursday: '历史',
          friday: '生物',
          saturday: '语文',
          sunday: ''
        }
      ]

      // week: '',
      // semester: '',
      // weekList: [
      //   '第1周',
      //   '第2周',
      //   '第3周',
      //   '第4周',
      //   '第5周',
      //   '第6周',
      //   '第7周',
      //   '第8周',
      //   '第9周',
      //   '第10周',
      //   '第11周',
      //   '第12周',
      //   '第13周',
      //   '第14周',
      //   '第15周',
      //   '第16周'
      // ],
      // semesterList: ['2019-2020-1', '2018-2029-1', '2018-2029-2']
    }
  },
  mounted() {},
  methods: {
    digital2Chinese(num, identifier) {
      const character = [
        '零',
        '一',
        '二',
        '三',
        '四',
        '五',
        '六',
        '七',
        '八',
        '九',
        '十',
        '十一',
        '十二'
      ]
      return identifier === 'week' && (num === 0 || num === 7) ? '日' : character[num]
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
table {
  border: 1px solid #c0c0c0;
  border-collapse: collapse;
  width: 100%;
}
thead {
  background: #f0faff;
}
tr {
  height: 85px;
  text-align: center;
}
tr:nth-of-type(even) {
  background: #f0faff;
}
td {
  border: 1px solid #c0c0c0;
}
</style>
