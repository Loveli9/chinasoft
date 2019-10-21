<template>
  <div class="CutPage">
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        current-page.sync="5"
        :page-size="pagesize"
        layout="total,prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  props:{
    total:Number,
    pagesize:Number
  },
  methods: {
    handleCurrentChange(val) {
      this.$emit('currentPage',val)
    }
  }
};
</script>

<style lang="less" scoped>
</style>