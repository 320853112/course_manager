<template>
  <div class="MyCourse">
    <div class="queryWrap">
      <Input v-model="courseName" placeholder="请输入课程名称" style="width: 300px" />
      <Button type="primary">查询</Button>
    </div>
    <Divider dashed />
    <!-- <div class="addCourse">
      <Button type="primary" @click="addCourse">添加课程</Button>
    </div> -->
    <div class="tableWrap">
      <Table border :columns="columns1" :data="data1"></Table>
    </div>
    <!-- 添加课程弹窗 -->
    <Modal class="model" v-model="modal1" :closable="false" :footer-hide="true">
      <Form ref="formValidate" :model="formValidate" :label-width="80">
        <FormItem label="课程名称" prop="courseName">
          <Input v-model="formValidate.courseName" placeholder="请输入课程名称"></Input>
        </FormItem>
        <FormItem label="上课地点" prop="time">
          <Input v-model="formValidate.time" placeholder="请输入上课地点"></Input>
        </FormItem>
        <FormItem label="周数" prop="weeks">
          <Select v-model="formValidate.weeks" placeholder="请选择上课周数">
            <Option value="add">单周</Option>
            <Option value="even">双周</Option>
            <Option value="all">1-16周</Option>
          </Select>
        </FormItem>
        <FormItem label="课程类型" prop="type">
          <Select v-model="formValidate.type" placeholder="请选择课程类型">
            <Option value="obligatory">必修</Option>
            <Option value="elective">选修</Option>
          </Select>
        </FormItem>
        <FormItem label="学分" prop="credit">
          <Select v-model="formValidate.credit" placeholder="请选择学分">
            <Option value="one">1</Option>
            <Option value="two">2</Option>
          </Select>
        </FormItem>
      </Form>
      <div class="btn">
        <Button type="primary" @click="confirm">确认</Button>
        <Button type="primary" @click="cancel">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      courseName: '',
      modal1: false,
      formValidate: {
        courseName: '',
        time: '',
        city: '',
        weeks: '',
        type: '',
        credit: ''
      },
      columns1: [
        {
          title: '课程名称',
          key: 'courseName',
          align: 'center'
        },
        {
          title: '上课时间',
          key: 'time',
          align: 'center'
        },
        {
          title: '上课地点',
          key: 'address',
          align: 'center'
        },
        {
          title: '周数',
          key: 'weeks',
          align: 'center'
        },
        {
          title: '课程类型',
          key: 'type',
          align: 'center'
        },
        {
          title: '学分',
          key: 'credit',
          align: 'center'
        }
      ],
      data1: [
        {
          courseName: '数据结构',
          time: '周二',
          address: 'J9-302',
          weeks: '1-16周',
          type: '必修',
          credit: '2'
        },
        {
          courseName: '离散数学',
          time: '周三',
          address: 'J9-201',
          weeks: '单周',
          type: '必修',
          credit: '2'
        },
        {
          courseName: '创新教育',
          time: '周五',
          address: 'J9-308',
          weeks: '双周',
          type: '选修',
          credit: '1'
        }
      ]
    }
  },
  methods: {
    addCourse() {
      this.modal1 = true
    },
    confirm() {
      this.$Message.success('修改成功！')
      this.modal1 = false
    },
    cancel() {
      this.$Message.warning('修改失败！')
      this.modal1 = false
    }
  }
}
</script>

<style lang="less" scoped>
.queryWrap {
  display: flex;
  justify-content: space-between;
}
.addCourse {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
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