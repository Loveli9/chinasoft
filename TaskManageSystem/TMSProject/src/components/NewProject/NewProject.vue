<template>
  <div>
    <el-button type="text" class="btn btn-primary" style="float:right" @click="NewProject">新建项目</el-button>
    <el-dialog
      title="新建项目"
      :visible.sync="dialogFormVisible"
      :modal-append-to-body="false"
      :append-to-body="true"
      width="35%"
    >
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
        size="mini"
        :inline-message="true"
      >
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="ruleForm.projectName" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="项目编码" prop="projectCode">
          <el-input v-model="ruleForm.projectCode" style="width:80%"></el-input>
        </el-form-item>
        <!-- <el-form-item label="项目类型" prop="types" style="margin-top:-14px">
          <el-select v-model="ruleForm.types" placeholder="请选择项目类型">
            <el-option label="研发" value="yanfa"></el-option>
            <el-option label="测试" value="ceshi"></el-option>
            <el-option label="维护" value="weihu"></el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item label="交付部" prop="deliveryDepartment" style="margin-top:-14px;">
          <el-select v-model="ruleForm.deliveryDepartment" placeholder="请选择交付部" style="width:80%">
            <el-option label="第一个交付部" value="第一交付部"></el-option>
            <el-option label="第二个交付部" value="第二交付部"></el-option>
            <el-option label="第三个交付部" value="第三交付部"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商务类型" prop="businessType" style="margin-top:-14px">
          <el-select v-model="ruleForm.businessType" placeholder="请选择商务类型" style="width:80%">
            <el-option label="商务类型1" value="1"></el-option>
            <el-option label="商务类型2" value="2"></el-option>
            <el-option label="商务类型3" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="业务线" prop="businessLine" style="margin-top:-14px">
          <el-select v-model="ruleForm.businessLine" placeholder="请选择商务类型" style="width:80%">
            <el-option label="第一业务线" value="第一业务线"></el-option>
            <el-option label="第一业务线" value="第一业务线"></el-option>
            <el-option label="第一业务线" value="第一业务线"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交付经理" style="margin-top:-14px" prop="deliveryManager">
          <el-input v-model="ruleForm.deliveryManager" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="项目经理" style="margin-top:-14px" prop="pm">
          <el-input v-model="ruleForm.pm" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="归属地区" prop="attributionArea" style="margin-top:-14px">
          <el-select v-model="ruleForm.attributionArea" placeholder="请选择归属地区" style="width:80%">
            <el-option label="成都" value="成都"></el-option>
            <el-option label="西安" value="西安"></el-option>
            <el-option label="深圳" value="深圳"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目时间" required style="margin-top:-14px">
          <el-form-item prop="date1" style="width:80%">
            <el-date-picker
              v-model="ruleForm.actualProjectEndTime"
              type="date"
              placeholder="选择日期"
              style="width:80%"
            ></el-date-picker>
          </el-form-item>
        </el-form-item>
        <el-form-item label="描述" style="margin-top:-14px">
          <el-input
            type="textarea"
            :autosize="{ minRows: 4}"
            placeholder="请输入内容"
            v-model="ruleForm.projectDescription"
            style="width:80%"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="margin-top:-20px">
        <el-button
          type="primary"
          class="btn-primary"
          @click.stop="submitForm('ruleForm')"
          style="font-size:14px"
        >创建</el-button>
        <el-button @click.stop="resetForm('ruleForm')" style="font-size:14px">重置</el-button>
      </div>
    </el-dialog>

    <el-dialog title="提示" :visible.sync="exportMember" width="30%">
      <span>是否立即导入成员</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="jumpProjectList">取 消</el-button>
        <el-button type="primary" @click="jumpMember">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      exportMember: false,
      dialogFormVisible: false,
      ruleForm: {
        projectName: "",
        actualProjectEndTime: "",
        businessType: "",
        deliveryManager: "",
        businessLine: "",
        attributionArea: "",
        deliveryDepartment: "",
        projectDescription: "",
        projectCode: "",
        projectCreationTime: "",
        founder: "",
        pm:""
      },
      rules: {
        projectName: [{ required: true, message: "必填", trigger: "blur" }],
        businessLine: [{ required: true, message: "必填", trigger: "blur" }],
        deliveryDepartment: [
          { required: true, message: "必填", trigger: "change" }
        ],
        businessType: [{ required: true, message: "必填", trigger: "change" }],
        attributionArea: [
          { required: true, message: "必填", trigger: "change" }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let year = this.ruleForm.actualProjectEndTime.getFullYear();
          let month = this.ruleForm.actualProjectEndTime.getMonth() + 1;
          let day = this.ruleForm.actualProjectEndTime.getDate();
          this.ruleForm.actualProjectEndTime = year + "-" + month + "-" + day;
          this.ruleForm.founder = window.sessionStorage.getItem("name");
          let time = new Date();
          this.ruleForm.projectCreationTime =
            time.getFullYear() +
            "-" +
            (time.getMonth() + 1) +
            "-" +
            time.getDate() +
            " " +
            time.getHours() +
            ":" +
            time.getMinutes() +
            ":" +
            time.getSeconds();
          let params = this.ruleForm;
          this.request
            .post("ViewProject/insert", params)
            .then(res => {
              console.log(res)
              if (res.code == 200) {
                this.dialogFormVisible = false;
                this.$message({
                  message: "恭喜你，创建成功",
                  type: "success"
                });
                this.exportMember = true;
              }else if(res.code==10101){
                this.$message({
                  message: res.message,
                  type: "error"
                });
              }
            })
            .catch(err => {
              console.log("错误原因" + err);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    jumpProjectList() {
      this.exportMember = false;
      this.$router.push("/Main/ViewProject");
      location.reload()
    },
    jumpMember() {
      this.exportMember = false;
      this.$router.push("/Main/Member");
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    NewProject() {
      this.dialogFormVisible = true;
    }
  }
};
</script>

<style lang="less" scoped>
</style>