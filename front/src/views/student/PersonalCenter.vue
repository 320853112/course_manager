<template>
  <div class="personalCenter">
    <Card>
      <Form :model="formInline" label-position="left" :label-width="100">
        <FormItem label="学生姓名:">
          <Input v-model="formInline.name" disabled></Input>
        </FormItem>
        <FormItem label="学生学号:">
          <Input v-model="formInline.id" disabled></Input>
        </FormItem>
        <FormItem label="所属院系:">
          <Input v-model="formInline.college" disabled></Input>
        </FormItem>
        <FormItem label="专业名称:">
          <Input v-model="formInline.major" disabled></Input>
        </FormItem>
        <FormItem label="班级名称:">
          <Input v-model="formInline.className" disabled></Input>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {}
    }
  },
  mounted() {
    this.getStuInfo()
  },
  methods: {
    // 返回个人信息
    async getStuInfo() {
      const result = await this.$service.student.getStuInfo({
        id: 2016034449,
        pageNum: 1,
        pageSize: 10
      })
      if (result.status) {
        this.formInline = result.data.stuInfos[0]
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
