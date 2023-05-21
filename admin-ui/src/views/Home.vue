<template>
  <el-card class="main-card">
    <div class="title">{{ this.$route.name }}</div>

    <div style="margin-top: 60px">
      <el-divider/>
      <h3>架构图</h3>
      <el-image style="width: 900px; height: 600px;" :src="src"></el-image>
      <el-divider/>
    </div>

  </el-card>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      src:'https://vgg-1303838132.cos.ap-nanjing.myqcloud.com/images/%E6%9E%B6%E6%9E%84%E5%9B%BE.png'
    }
  },

  created() {

  },

  methods: {}
}
</script>

<style scoped lang="less">
</style>
