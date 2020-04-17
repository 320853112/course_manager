<template>
  <div class="termSchedule">
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
            <p>{{ item.classesTime }}</p>
          </td>
          <td v-for='(week, index) in weeks' :key='index'>
            {{item[week]  || '-'}}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      weeks: ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'],
      classTableData: [
        {
          classesTime: '08:00-09:40',
          monday: '物理',
          tuesday: '英语',
          wednesday: '政治',
          thursday: '历史',
          friday: '化学',
          saturday: '历史',
          sunday: '化学'
        },
        {
          classesTime: '10:00-11:40',
          monday: '生物',
          tuesday: '物理',
          wednesday: '化学',
          thursday: '英语',
          friday: '化学',
          saturday: '生物',
          sunday: '化学'
        },
        {
          classesTime: '14:00-15:40',
          monday: '化学',
          tuesday: '英语',
          wednesday: '物理',
          thursday: '化学',
          friday: '语文',
          saturday: '物理',
          sunday: '英语'
        },
        {
          classesTime: '16:00-17:40',
          monday: '历史',
          tuesday: '历史',
          wednesday: '语文',
          thursday: '历史',
          friday: '生物',
          saturday: '英语',
          sunday: ''
        },
        {
          classesTime: '19:30-21:10',
          monday: '物理',
          tuesday: '生物',
          wednesday: '英语',
          thursday: '历史',
          friday: '生物',
          saturday: '语文',
          sunday: ''
        }
      ]
    }
  },
  mounted() {},
  methods: {
    digital2Chinese(num, identifier) {
      const character = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十']
      return identifier === 'week' && (num === 0 || num === 7) ? '日' : character[num]
    }
  }
}
</script>

<style lang="less" scoped>
table {
  border: 1px solid #dcdee2;
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
  border: 1px solid #dcdee2;
}
</style>
