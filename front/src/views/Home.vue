<template>
  <div class="home">
    <Layout>
      <page-header></page-header>
      <!-- 左侧菜单 -->
      <Layout>
        <Sider hide-trigger :style="{background: '#fff'}">
          <page-sider></page-sider>
        </Sider>
        <!-- 中间内容 -->
        <Layout :style="{padding: '0 24px 24px'}">
          <Breadcrumb :style="{margin: '24px 0'}">
            <BreadcrumbItem v-for="(item,index) in $route.meta.routeName" :key="index">>>&nbsp;{{item}}</BreadcrumbItem>
          </Breadcrumb>
          <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
            <router-view></router-view>
          </Content>
        </Layout>

      </Layout>
    </Layout>
  </div>
</template>

<script>
import PageHeader from '../components/PageHeader'
import PageSider from '../components/PageSider'
export default {
  name: 'home',
  components: {
    PageHeader,
    PageSider
  }
}
</script>

<style lang="less" scoped>
</style>
