<template>
  <div class="Nav">
    <el-menu
      :default-active="$route.path"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="rgb(24, 134, 238)"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
    >
      <img src="@/assets/png.png" alt class="imgLogo" />
      <el-menu-item index="/Main/ProjectKanban" class="el-submenu__title">项目看板</el-menu-item>
      <el-submenu :index="(item+1)+''" v-for="(sonMenu,item) in menu" :key="item">
        <template slot="title">{{ sonMenu.title }}</template>
        <el-menu-item
          :index="mainMenu.path"
          v-for="(mainMenu,items) in sonMenu.minMenu"
          :key="items"
        >{{ mainMenu.minTitle }}</el-menu-item>
      </el-submenu>
      <el-menu-item index="/Main/ViewTask" class="el-submenu__title">任务管理</el-menu-item>
      <el-menu-item index="/Main/UserManagement" class="el-submenu__title">用户管理</el-menu-item>
      <el-menu-item index="/Main/CodeView" class="el-submenu__title">代码检视</el-menu-item>
    </el-menu>
    <div class="personMsg">
      
      <el-button type="text" style="color:#fff" ref="username" @click="loginBtn">{{ username==""?"请登录":username }}</el-button>
      <el-button type="text" style="color:#fff" v-show="username!=''" @click="gotoLogin">注销</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      menu: [
        // {
        //   title: "项目看板",
        //   minMenu: [
        //     {
        //       minTitle: "项目看板",
        //       path: "/Main/ProjectKanban"
        //     },
        //   ]
        // },
        {
          title: "项目管理",
          minMenu: [
            {
              minTitle: "项目列表",
              path: "/Main/ViewProject"
            },
            {
              minTitle: "项目详情",
              path: "/Main/ProjectInfo"
            },
            {
              minTitle: "查看迭代",
              path: "/Main/ViewIteration"
            },
            {
              minTitle: "查看责任田",
              path: "/Main/ViewField"
            },
            {
              minTitle: "成员管理",
              path: "/Main/Member"
            }
          ]
        }
        // {
        //   title: "任务管理",
        //   minMenu: [
        //     {
        //       minTitle: "查看任务",
        //       path: "/Main/ViewTask"
        //     }
        //   ]
        // },
        // {
        //   title: "用户管理",
        //   minMenu: [
        //     {
        //       minTitle: "用户管理",
        //       path: "/Main/UserManagement"
        //     }
        //   ]
        // },
        // {
        //   title: "代码检视",
        //   minMenu: [
        //     {
        //       minTitle: "代码检视",
        //       path: "/Main/CodeView"
        //     }
        //   ]
        // }
      ]
    };
  },
  mounted() {
    this.username = window.sessionStorage.getItem("name");
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    gotoLogin() {
      this.$router.push("/");
      window.sessionStorage.setItem("name", "");
    },
    loginBtn(){
      if(this.username==""){
        this.$router.push("/")
      }
    }
  }
};
</script>

<style lang="less">
.imgLogo {
  margin-top: 10px;
  float: left;
}
.el-menu-item:hover {
  color: #fff !important;
  background-color: #409eff !important;
}
.el-submenu__title:focus,
.el-submenu__title:hover {
  color: #fff !important;
  background: #409eff !important;
}
.el-submenu__title {
  font-size: 16px;
}
.personMsg {
  width: 200px;
  height: 50px;
  line-height: 50px;
  color: #fff;
  position: absolute;
  text-align: center;
  top: 6px;
  right: 0px;
}
</style>