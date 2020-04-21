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
    <Spin v-if="loading" fix size="large"></Spin>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      formInline: {}
    }
  },
  mounted() {
    this.getStuInfo()
  },
  methods: {
    // 返回个人信息
    async getStuInfo() {
      this.loading = true
      const result = await this.$service.student.getStuInfo({
        id: localStorage.getItem('stuId'),
        pageNum: 1,
        pageSize: 10
      })
      this.loading = false
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
