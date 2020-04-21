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
            <Tooltip :content="`教师:${teacherName}---地点:${place}---类别:${category}`" :delay="1000" placement="top" @on-popper-show="getCourseInfo(item[week])">
              {{item[week] ? item[week].courseName  : '-'}}
            </Tooltip>
          </td>
        </tr>
      </tbody>
    </table>
    <Spin v-if="loading" fix size="large"></Spin>
  </div>
</template>

<script>
export default {
  data() {
    return {
      teacherName: '',
      place: '',
      category: '', // 课程类别
      loading: false,
      weeks: ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'],
      classTableData: []
    }
  },
  mounted() {
    this.getStuCourseTable()
  },
  methods: {
    // 查询学生课程表
    async getStuCourseTable() {
      this.loading = true
      const result = await this.$service.course.getStuCourseTable({})
      this.loading = false
      if (result.status) {
        this.classTableData = result.data
      }
    },
    // 拿课程ID请求课程信息，获取教师与上课地点信息
    getCourseInfo(item) {
      if (!item) {
        return
      }
      if (this.teacherName && this.place && this.category) {
        this.getCourse(item.courseId)
        this.teacherName = ''
        this.place = ''
        this.category = ''
      } else {
        this.getCourse(item.courseId)
      }
    },
    async getCourse(courseId) {
      const result = await this.$service.course.getCourse({
        id: courseId,
        pageNum: 1,
        pageSize: 10
      })
      if (result.status) {
        this.teacherName = result.data.courseList[0].teacher
        this.place = result.data.courseList[0].place
        this.category = result.data.courseList[0].category
      }
    },
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
  width: 12.5%;
}
</style>
