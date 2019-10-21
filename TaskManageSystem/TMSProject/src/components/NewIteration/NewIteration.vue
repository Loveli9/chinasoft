<template>
  <div class="NewIteration">
    <el-button class="btn btn-primary" type="text" @click.stop="newIteration">新建迭代</el-button>
    <el-dialog
      title="新建迭代"
      :visible.sync="dialogFormVisible"
      :modal-append-to-body="false"
      :append-to-body="true"
      width="35%"
    >
      <el-form :model="form" :rules="rules" ref="ruleForm" size="mini" :inline-message="true">
        <el-form-item label="迭代名称" label-width="120px" prop="iterativeName">
          <el-input v-model="form.iterativeName" autocomplete="off" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="迭代编码" label-width="120px" prop="iterativeCode">
          <el-input v-model="form.iterativeCode" autocomplete="off" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item
          label="所属项目"
          label-width="120px"
          prop="projectOwnership"
          style="margin-top:-14px"
        >
          <el-input v-model="form.projectOwnership" autocomplete="off" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="迭代状态" label-width="120px" prop="iterativeState">
          <el-select v-model="form.iterativeState" placeholder="请选择" size="mini" style="width:150px">
            <el-option label="已计划" value="1"></el-option>
            <el-option label="进行中" value="2"></el-option>
            <el-option label="已关闭" value="3"></el-option>
            <el-option label="延期" value="4"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="迭代时间" label-width="120px" required style="width:80%;margin-top:-14px">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="form.iterationPlanStartDate"
            style="width: 48%;"
          ></el-date-picker>-
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="form.iterationPlanEndDate"
            style="width: 48%;"
          ></el-date-picker>
        </el-form-item>
        <el-form-item
          label="迭代目标"
          label-width="120px"
          prop="iterationGoal"
          style="margin-top:-14px"
        >
          <el-input
            type="textarea"
            :autosize="{ minRows: 4}"
            placeholder="请输入内容"
            v-model="form.iterationGoal"
            style="width:80%"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="margin-top:-20px">
        <el-button type="primary" @click.stop="submitForm('ruleForm')" class="btn-primary">创建</el-button>
        <el-button @click.stop="resetForm('ruleForm')">重置</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      form: {
        projectOwnership: "",
        iterationPlanStartDate: "",
        iterationPlanEndDate: "",
        iterativeName: "",
        iterativeCode: "",
        iterativeState:"",
        iterationGoal: "",
        founder: ""
      },
      rules: {
        iterativeName: [{ required: true, message: "必填", trigger: "blur" }],
        projectOwnership: [
          { required: true, message: "必填", trigger: "blur" }
        ],
        iterativeState: [{ required: true, message: "必填", trigger: "blur" }],
        iterationGoal: [{ required: true, message: "必填", trigger: "blur" }],
        iterativeCode: [{ required: true, message: "必填", trigger: "blur" }],
        iterationPlanStartDate: [
          {
            type: "date",
            required: true,
            message: "必填",
            trigger: "change"
          }
        ],
        iterationPlanEndDate: [
          {
            type: "date",
            required: true,
            message: "必填",
            trigger: "change"
          }
        ]
      }
    };
  },
  methods: {
    newIteration() {
      this.dialogFormVisible = true;
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let year = this.form.iterationPlanStartDate.getFullYear();
          let month = this.form.iterationPlanStartDate.getMonth() + 1;
          let day = this.form.iterationPlanStartDate.getDate();
          this.form.iterationPlanStartDate = year + "-" + month + "-" + day;
          this.form.founder = window.sessionStorage.getItem("name");
          this.form.iterationPlanEndDate =
            this.form.iterationPlanEndDate.getFullYear() +
            "-" +
            (this.form.iterationPlanEndDate.getMonth() + 1) +
            "-" +
            this.form.iterationPlanEndDate.getDate();
          let params = this.form;
          console.log(params);
          this.request
            .post("ViewIteration/insert", params)
            .then(res => {
              console.log(res);
              if (res.code == 200) {
                this.dialogFormVisible = false;
                this.$message({
                  message: "恭喜你，创建成功",
                  type: "success"
                });
                location.reload()
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
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style lang="scss" scoped>
</style>