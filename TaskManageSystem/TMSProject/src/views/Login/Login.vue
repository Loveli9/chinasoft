<template>
  <div class="login">
    <div class="loginbox">
      <!-- 标题 -->
      <div class="title">
        <span class="el-icon-menu"></span>
        <span>TMS系统登录</span>
      </div>
      <el-form
        :model="loginForm"
        status-icon
        :rules="rules"
        ref="loginForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <!-- 账号 -->
        <el-form-item label="账号" prop="account">
          <el-input type="text" v-model="loginForm.account" auto-complete="off"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click.stop="submitForm('loginForm')">登&emsp;录</el-button>
          <el-button @click.stop="resetForm('loginForm')">重&emsp;置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    const validateAccount = (rule, value, callback) => {
      if (!value) {
        callback(new Error("账号不能为空"));
      } else {
        callback();
      }
    };
    // 密码验证
    const validatePassword = (rule, value, callback) => {
      if (!value) {
        callback(new Error("密码不能为空"));
      } else {
        callback();
      }
    };

    return {
      loginForm: {
        account: "",
        password: ""
      },
      rules: {
        account: [
          { required: true, validator: validateAccount, trigger: "blur" }
        ],
        password: [
          { required: true, validator: validatePassword, trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    // 重置按钮事件
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //   提交按钮事件
    submitForm(loginForm) {
      this.$refs[loginForm].validate(valid => {
        if (valid) {
          alert("submit!");
          this.$router.push("/Main/ProjectKanban");
          window.sessionStorage.setItem("name",this.loginForm.account)
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>

<style lang="less" scope>
.login {
  height: 100%;
  background-color: rgb(11, 221, 236);
  .loginbox {
    width: 500px;
    height: 330px;
    background-color: rgba(1, 1, 17, 0.5);
    border-radius: 5px;
    box-shadow: 2px 3px 18px #000;
    //    水平垂直居中
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    box-sizing: border-box;
    padding: 20px 30px 0 0;
    // 标题
    .title {
      color: #ccc;
      font-size: 24px;
      padding: 20px 0 25px 140px;
    }
    // 表单
    .el-form {
      .el-form-item {
        margin-bottom: 35px;
        .el-form-item__content {
          width: 350px;
        }
        .el-form-item__label {
          color: #ccc;
        }
      }
    }
  }
}
</style>
