<template>
  <div class="electiveCenter">
    <div class="queryWrap">
      <div class="category">
        <span>类别</span>
        <Select v-model="category" style="width:140px">
          <Option v-for="item in categoryList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <div class="course">
        <span>课程</span>
        <Input v-model="course" style="width: 140px" />
      </div>
      <div class="teacher">
        <span>上课老师</span>
        <Input v-model="teacher" style="width: 140px" />
      </div>
      <div class="week">
        <span>星期</span>
        <Select v-model="week" style="width:140px">
          <Option v-for="item in weekList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <div class="festivals">
        <span>节次</span>
        <Select v-model="festivals" style="width:140px">
          <Option v-for="item in festivalsList" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </div>
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
    <div class="tableWrap">
      <Table border :columns="columnsSelection" :data="selectionData"></Table>
    </div>
    <Divider dashed />
    <!-- 选课结果及退选折叠面板 -->
    <Collapse v-model="withdrawal">
      <Panel name="1">
        选课结果及退选
        <div slot="content">
          <Table border :columns="columnsOut" :data="outData"></Table>
        </div>
      </Panel>
    </Collapse>
    <!-- 选课弹窗 -->
    <Modal class="model" v-model="modal1" :closable="false" :footer-hide="true">
      <p class="model-title">确认选课</p>
      <p class="model-content">确认选择网页设计与制作课程吗？</p>
      <div class="btn">
        <Button type="primary" @click="ok">确认</Button>
        <Button type="primary" @click="cancel">取消</Button>
      </div>
    </Modal>
    <!-- 退选弹窗 -->
    <Modal class="model" v-model="modal2" :closable="false" :footer-hide="true">
      <p class="model-title">确认退选</p>
      <p class="model-content">确认退选网页设计与制作课程吗？</p>
      <div class="btn">
        <Button type="primary" @click="confirm">确认</Button>
        <Button type="primary" @click="off">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: '',
      category: '',
      course: '',
      teacher: '',
      week: '',
      festivals: '',
      modal1: false,
      modal2: false,
      withdrawal: '1',
      categoryList: ['人文科学', '自然科学', '社会科学', '工程技术', '其它'],
      weekList: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
      festivalsList: ['1-2节', '3-4节', '5-6节', '7-8节', '9-10节'],
      columnsSelection: [
        {
          title: '课程号',
          key: 'id',
          align: 'center'
        },
        {
          title: '课程名称',
          key: 'name',
          align: 'center'
        },
        {
          title: '授课教师',
          key: 'teacher',
          align: 'center'
        },
        {
          title: '上课时间',
          key: 'time_week',
          align: 'center'
        },
        {
          title: '课程类型',
          key: 'category',
          align: 'center'
        },
        {
          title: '学分',
          key: 'credit',
          align: 'center'
        },
        {
          title: '剩余量',
          key: 'surplus',
          align: 'center'
        },
        {
          title: '操作',
          key: 'operation',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'Button',
                {
                  props: {
                    size: 'small',
                    type: 'info'
                  },
                  style: {
                    // marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      // this.$router.push({
                      //   path: 'vendorDetail',
                      //   query: {
                      //     id: params.row.id,
                      //     name: params.row.name
                      //   }
                      // })
                      this.modal1 = true
                    }
                  }
                },
                '选课'
              )
            ])
          }
        }
      ],
      selectionData: [],
      columnsOut: [
        {
          title: '课程号',
          key: 'courseNumber',
          align: 'center'
        },
        {
          title: '课程名',
          key: 'courseName',
          align: 'center'
        },
        {
          title: '学分',
          key: 'credit',
          align: 'center'
        },
        {
          title: '课程属性',
          key: 'attribute',
          align: 'center'
        },
        {
          title: '上课教师',
          key: 'teacher',
          align: 'center'
        },
        {
          title: '上课时间',
          key: 'time',
          align: 'center'
        },
        {
          title: '上课地点',
          key: 'place',
          align: 'center'
        },
        {
          title: '选课状态',
          key: 'category',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'p',
                {
                  props: {
                    // size: 'small',
                    // type: 'info'
                  },
                  style: {
                    // marginRight: '5px'
                  }
                },
                '选中/退选'
              )
            ])
          }
        },
        {
          title: '操作',
          key: 'operation',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'Button',
                {
                  props: {
                    size: 'small',
                    type: 'info'
                  },
                  style: {
                    // marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      // this.$router.push({
                      //   path: 'vendorDetail',
                      //   query: {
                      //     id: params.row.id,
                      //     name: params.row.name
                      //   }
                      // })
                      this.modal2 = true
                    }
                  }
                },
                '退选'
              )
            ])
          }
        }
      ],
      outData: [
        {
          courseNumber: '	081200',
          courseName: '网页设计',
          credit: 2,
          attribute: '必修',
          teacher: '黄淑丽',
          time: '3-4节',
          place: 'J9-305'
        }
      ]
    }
  },
  mounted() {
    this.getCourse()
  },
  methods: {
    // 获取所有教师信息
    async getCourse() {
      const result = await this.$service.course.getCourse({
        pageNum: 1,
        pageSize: 10
      })
      if (result.status) {
        this.selectionData = result.data
      }
    },
    ok() {
      this.$Message.success('选课成功！')
      this.modal1 = false
    },
    cancel() {
      this.$Message.warning('选课失败！')
      this.modal1 = false
    },
    confirm() {
      this.$Message.success('退选成功！')
      this.modal2 = false
    },
    off() {
      this.$Message.warning('退选失败！')
      this.modal2 = false
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
.model-title {
  text-align: center;
  font-size: 20px;
  margin: 10px 0;
}
.model-content {
  text-align: center;
  font-size: 16px;
}
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
</style>
