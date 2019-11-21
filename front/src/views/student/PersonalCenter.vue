<template>
  <div class="personalCenter">
    <Card>
      <Form :model="formLeft" label-position="left" :label-width="100">
        <FormItem label="学生姓名:">
          <Input v-model="formLeft.input1" disabled></Input>
        </FormItem>
        <FormItem label="学生学号:">
          <Input v-model="formLeft.input2" disabled></Input>
        </FormItem>
        <FormItem label="所属院系:">
          <Input v-model="formLeft.input3" disabled></Input>
        </FormItem>
        <FormItem label="专业名称:">
          <Input v-model="formLeft.input4" disabled></Input>
        </FormItem>
        <FormItem label="班级名称:">
          <Input v-model="formLeft.input5" disabled></Input>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formLeft: {
        input1: '邓藿',
        input2: '2016030594',
        input3: '信息工程学院',
        input4: '计算机科学与技术',
        input5: '16本计算机2班'
      }
    }
  }
}
</script>

<style lang="less" scoped>
.ivu-card {
  width: 40%;
  margin: auto;
}
</style>
