<template>
  <div class="CodeView">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-size:16px">代码检视列表</span>
        <el-button
          type="text"
          class="btn btn-primary"
          style="float:right"
          @click.stop="NewCodeView"
        >新建检视</el-button>
        <el-dialog
          title="新建检视"
          :visible.sync="dialogFormVisible"
          :close-on-click-modal="false"
          top="5vh"
          width="35%"
        >
          <el-form :model="form" :rules="rules" ref="form" size="mini" :inline-message="true">
            <el-form-item label="项目名称" label-width="120px" prop="projectName">
              <el-input v-model="form.projectName" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item
              label="项目编码"
              label-width="120px"
              prop="projectId"
              style="margin-top:-12px"
            >
              <el-input v-model="form.projectId" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item label="任务编码" label-width="120px" prop="taskId" style="margin-top:-12px">
              <el-input v-model="form.taskId" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item label="缺陷编码" label-width="120px" prop="bugId" style="margin-top:-12px">
              <el-input v-model="form.bugId" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item label="缺陷类型" label-width="120px" prop="bugType" style="margin-top:-12px">
              <el-select v-model="form.bugType" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="缺陷等级" label-width="120px" prop="bugLevel" style="margin-top:-12px">
              <el-select v-model="form.bugLevel" placeholder="请选择">
                <el-option
                  v-for="item in options2"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              label="源文件路径"
              label-width="120px"
              prop="sourceFile"
              style="margin-top:-12px"
            >
              <el-input v-model="form.sourceFile" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item label="开始行" label-width="120px" prop="beginLine" style="margin-top:-12px">
              <el-input v-model="form.beginLine" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item label="结束行" label-width="120px" prop="endLine" style="margin-top:-12px">
              <el-input v-model="form.endLine" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item label="版本信息" label-width="120px" prop="version" style="margin-top:-12px">
              <el-input v-model="form.version" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item label="检视人" label-width="120px" prop="checkUser" style="margin-top:-12px">
              <el-input v-model="form.checkUser" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item
              label="检视时间"
              label-width="120px"
              prop="checkTime"
              style="margin-top:-12px"
            >
              <el-date-picker v-model="form.checkTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
            </el-form-item>
            <el-form-item label="修改人" label-width="120px" prop="editUser" style="margin-top:-12px">
              <el-input v-model="form.editUser" autocomplete="off" style="width:70%"></el-input>
            </el-form-item>
            <el-form-item
              label="修改建议"
              label-width="120px"
              prop="editSuggest"
              style="margin-top:-12px"
            >
              <el-input v-model="form.editSuggest" autocomplete="off" type="textarea" :rows="5"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit('form')" style="margin-left:60px">保存</el-button>
              <el-button type="primary" @click="resetForm('form')" style="margin-left:60px">重置</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </div>
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        :cell-style="{padding:0}"
        :fit="false"
        empty-text="暂无数据"
      >
        <el-table-column prop="id" label="编码" width="50" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="projectId" label="代码库" min-width="200%" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="bugId" label="缺陷类型" width="100" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="bugType" label="子类型" width="100" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="bugLevel" label="缺陷等级" width="70" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column
          prop="sourceFile"
          label="源文件路径"
          min-width="200"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column prop="beginLine" label="开始行" width="70"></el-table-column>
        <el-table-column prop="endLine" label="结束行" width="70"></el-table-column>
        <el-table-column prop="version" label="版本信息" width="80" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="checkUser" label="检视人" width="120"></el-table-column>
        <el-table-column prop="checkTime" label="检视时间" width="144"></el-table-column>
        <el-table-column
          prop="editSuggest"
          label="修改建议"
          min-width="200"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column prop="editUser" label="修改人" width="150"></el-table-column>
        <el-table-column label="操作" width="120" fixed="right">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="修改"
              placement="top-start"
              style="margin-right:-10px"
            >
              <el-button
                @click.stop="modifyMsg(scope.row)"
                type="primary"
                size="mini"
                class="el-icon-edit"
              ></el-button>
            </el-tooltip>

            <el-tooltip class="item" effect="dark" content="删除" placement="top-start">
              <el-button
                @click.stop="deleteMsg(scope.row)"
                type="danger"
                size="mini"
                class="el-icon-delete"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog
        title="修改检视"
        :visible.sync="editViewcode"
        :close-on-click-modal="false"
        top="5vh"
        :append-to-body="true"
        width="35%"
      >
        <el-form :model="form" :rules="rules" ref="form" size="mini">
          <el-form-item label="项目名称" label-width="120px" prop="projectName">
            <el-input v-model="form.projectName" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="项目编码" label-width="120px" prop="projectId" style="margin-top:-12px">
            <el-input v-model="form.projectId" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="任务编码" label-width="120px" prop="taskId" style="margin-top:-12px">
            <el-input v-model="form.taskId" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="缺陷编码" label-width="120px" prop="bugId" style="margin-top:-12px">
            <el-input v-model="form.bugId" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="缺陷类型" label-width="120px" prop="bugType" style="margin-top:-12px">
            <el-select v-model="form.bugType" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="缺陷等级" label-width="120px" prop="bugLevel" style="margin-top:-12px">
            <el-select v-model="form.bugLevel" placeholder="请选择">
              <el-option
                v-for="item in options2"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="源文件路径"
            label-width="120px"
            prop="sourceFile"
            style="margin-top:-12px"
          >
            <el-input v-model="form.sourceFile" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="开始行" label-width="120px" prop="beginLine" style="margin-top:-12px">
            <el-input v-model="form.beginLine" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="结束行" label-width="120px" prop="endLine" style="margin-top:-12px">
            <el-input v-model="form.endLine" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="版本信息" label-width="120px" prop="version" style="margin-top:-12px">
            <el-input v-model="form.version" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="检视人" label-width="120px" prop="checkUser" style="margin-top:-12px">
            <el-input v-model="form.checkUser" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item label="检视时间" label-width="120px" prop="checkTime" style="margin-top:-12px">
            <el-date-picker v-model="form.checkTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
          <el-form-item label="修改人" label-width="120px" prop="editUser" style="margin-top:-12px">
            <el-input v-model="form.editUser" autocomplete="off" style="width:70%"></el-input>
          </el-form-item>
          <el-form-item
            label="修改建议"
            label-width="120px"
            prop="editSuggest"
            style="margin-top:-12px"
          >
            <el-input v-model="form.editSuggest" autocomplete="off" type="textarea" :rows="5"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="editSubmit('form')" style="margin-left:60px">保存</el-button>
            <el-button type="primary" @click="resetForm('form')" style="margin-left:60px">重置</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <CutPage
        v-on:currentPage="currentPage"
        v-bind:total="total"
        v-bind:pagesize="pagesize"
        style="margin-top:20px"
      ></CutPage>
    </el-card>
  </div>
</template>

<script>
export default {
  created() {
    this.codeViewList();
  },
  data() {
    return {
      pagesize: 10,
      total: 1,
      nowpage: 1,
      tableData: [
        {
          id:"1",
          projectId:"asdadadada",
          bugId:"21312",
          bugType:"大缺陷",
          bugLevel:"严重",
          sourceFile:"sdfsdff/ffsdf/sfsdfdf/sfsdfsd/sfsdfsdf/sdfsdfs",
          beginLine:"21",
          endLine:"432",
          version:"dasdada",
          checkUser:"lwx736703",
          checkTime:"2019-01-01 19:15:15.0",
          editSuggest:"无建议",
          editUser:"liyaping lwx736503",
        }
      ],
      editViewcode: false,
      dialogFormVisible: false,
      options: [
        {
          value: "选项1",
          label: "缺陷类型1"
        },
        {
          value: "选项2",
          label: "缺陷类型2"
        },
        {
          value: "选项3",
          label: "缺陷类型3"
        },
        {
          value: "选项4",
          label: "缺陷类型4"
        },
        {
          value: "选项5",
          label: "缺陷类型5"
        }
      ],
      options2: [
        {
          value: "警告",
          label: "警告"
        },
        {
          value: "严重",
          label: "严重"
        },
        {
          value: "重要",
          label: "重要"
        },
        {
          value: "特殊",
          label: "特殊"
        }
      ],
      form: {
        projectName: "",
        bugType: "",
        taskId: "",
        bugId: "",
        bugLevel: "",
        sourceFile: "",
        projectId: "",
        beginLine: "",
        endLine: "",
        version: "",
        checkUser: "",
        editUser: "",
        editSuggest: "",
        checkTime: ""
      },
      rules: {
        projectName: [
          { required: true, message: "请输入项目名称", trigger: "blur" }
        ],
        bugType: [
          { required: true, message: "请输入缺陷类型", trigger: "blur" }
        ],
        bugLevel: [
          { required: true, message: "请输入缺陷等级", trigger: "blur" }
        ],
        sourceFile: [
          { required: true, message: "请输入源文件路径", trigger: "blur" }
        ],
        beginLine: [
          { required: true, message: "请输入缺陷开始行", trigger: "blur" }
        ],
        endLine: [
          { required: true, message: "请输入缺陷结束行", trigger: "blur" }
        ],
        version: [
          { required: true, message: "请输入版本信息", trigger: "blur" }
        ],
        checkUser: [
          { required: true, message: "请输入检视人", trigger: "blur" }
        ],
        editUser: [
          { required: true, message: "请输入修改人", trigger: "blur" }
        ],
        editSuggest: [
          { required: true, message: "请输入修改建议", trigger: "blur" }
        ],
        checkTime: [
          { required: true, message: "请输入检视时间", trigger: "blur" }
        ],
        taskId: [
          { required: true, message: "请输入任务编码", trigger: "blur" }
        ],
        bugId: [{ required: true, message: "请输入缺陷编码", trigger: "blur" }],
        projectId: [
          { required: true, message: "请输入项目编码", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    deleteMsg(row) {
      this.$confirm("确认删除？")
        .then(_ => {
          let id = row.id;
          this.request
            .post(`/codereview/delete/` + id)
            .then(res => {
              if (res.code == 200) {
                this.$message({
                  message: "恭喜你，删除成功",
                  type: "success"
                });
                this.codeViewList();
              }
            })
            .catch(err => {
              console.log("错误原因" + err);
            });
        })
        .catch(_ => {});
    },
    currentPage(value) {
      this.nowpage = value;
      let params = {
        pageSize: 10,
        pageNumber: value
      };
      this.request
        .post("/codereview/list", params)
        .then(res => {
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    NewCodeView() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    onSubmit(formName) {
      let params = this.form;
      let year = this.form.checkTime.getFullYear();
      let month = this.form.checkTime.getMonth() + 1;
      let day = this.form.checkTime.getDate();
      let hour = this.form.checkTime.getHours();
      let min = this.form.checkTime.getMinutes();
      let sec = this.form.checkTime.getSeconds();
      let date =
        year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
      params.checkTime = date;
      this.request
        .post("/codereview/insert", params)
        .then(res => {
          if (res.code == 200) {
            this.$message({
              message: "恭喜你，创建成功",
              type: "success"
            });
            this.dialogFormVisible = false;
            this.codeViewList();
          }
        })
        .catch(err => {
          this.$message({
            message: "创建失败",
            type: "error"
          });
        });
    },
    editSubmit(formName) {
      let params = this.form;
      this.form.checkTime = new Date(params.checkTime);
      let year = this.form.checkTime.getFullYear();
      let month = this.form.checkTime.getMonth() + 1;
      let day = this.form.checkTime.getDate();
      let hour = this.form.checkTime.getHours();
      let min = this.form.checkTime.getMinutes();
      let sec = this.form.checkTime.getSeconds();
      let date =
        year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
      params.checkTime = date;
      this.request
        .post("/codereview/update", params)
        .then(res => {
          if (res.code == 200) {
            this.$message({
              message: "恭喜你，修改成功",
              type: "success"
            });
            this.editViewcode = false;
            this.codeViewList();
          }
        })
        .catch(err => {
          this.$message({
            message: "修改失败",
            type: "error"
          });
        });
    },
    modifyMsg(row) {
      this.editViewcode = true;
      this.form = row;
    },
    resetForm(formName) {
      this.form = {};
    },
    codeViewList() {
      let params = {
        pageSize: 10,
        pageNumber: this.nowpage
      };
      this.request
        .post("/codereview/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    jumpProject(row) {
      alert("跳转到项目名称为" + row.projectName + "的项目");
    }
  }
};
</script>
<style lang="less">
#app .Main .el-container .el-main .CodeView .el-card .el-card__body .el-table .el-table__fixed-right {
  height: 1000px !important;
}
</style>