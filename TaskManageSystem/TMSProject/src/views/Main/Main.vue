<template>
  <div class="Main">
    <el-container>
      <el-header>
        <Nav></Nav>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Nav from "../../components/Nav/Nav.vue";
export default {
  components: {
    Nav
  }
};
</script>

<style lang="less" scoped>
.el-header{
    padding: 0;
}
.el-main{
  padding: 5px;
  height: 100%;
}

</style>