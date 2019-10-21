<template>
  <div class="ViewProject">
    <el-card class="box-card">
      <span>请输入项目类型 &emsp;</span>
      <el-select v-model="value1" placeholder="项目类型" style="width:100px" size="mini">
        <el-option v-for="item in types" :key="item.value" :label="item.value" :value="item.value"></el-option>
      </el-select>&emsp;
      <span>商务类型 &emsp;</span>
      <el-select v-model="value2" placeholder="商务类型" style="width:100px" size="mini">
        <el-option
          v-for="item in department"
          :key="item.value"
          :label="item.value"
          :value="item.value"
        ></el-option>
      </el-select>&emsp;
      <span>地域 &emsp;</span>
      <el-select v-model="value3" placeholder="地域" style="width:100px" size="mini">
        <el-option v-for="item in area" :key="item.value" :label="item.value" :value="item.value"></el-option>
      </el-select>&emsp;
      <span>交付部 &emsp;</span>
      <el-select v-model="value4" placeholder="交付部" style="width:100px" size="mini">
        <el-option
          v-for="item in departmentValue"
          :key="item.value"
          :label="item.value"
          :value="item.value"
        ></el-option>
      </el-select>&emsp;
      <span>PM &emsp;</span>
      <el-select v-model="value5" placeholder="PM" style="width:100px" size="mini">
        <el-option
          v-for="item in PMValve"
          :key="item.value"
          :label="item.value"
          :value="item.value"
        ></el-option>
      </el-select>&emsp;
      <el-select v-model="projectValue" filterable placeholder="请输入项目名称" size="mini">
        <el-option
          v-for="item in options"
          :key="item.projectCode"
          :label="item.projectName"
          :value="item.projectCode"
        ></el-option>
      </el-select>&emsp;
      <el-button type="text" class="btn btn-primary">搜索</el-button>
      <el-button
        style="float:right"
        class="btn btn-primary"
        type="text"
        @click.stop="taskMaintain"
      >新建责任田</el-button>
      <el-dialog
        title="新建责任田"
        :visible.sync="dialogNewField"
        :modal-append-to-body="false"
        :append-to-body="true"
        width="35%"
      >
        <el-form :model="newField" ref="newField" :rules="rules" size="mini" :inline-message="true">
          <el-form-item label="责任田名称" label-width="120px" prop="name">
            <el-input v-model="newField.responsibleFieldName" clearable style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="是否启用" label-width="120px" style="margin-top:-14px">
            <el-switch v-model="newField.isEnabled" style="width:80%"></el-switch>
          </el-form-item>
          <el-form-item label="田主" label-width="120px" style="margin-top:-14px">
            <span
              v-for="(ownerMember,index) in newField.modifiedMemberList"
              :key="index"
            >{{ ownerMember.isResponsible==1?ownerMember.memberName+"&emsp;":"" }}</span>
          </el-form-item>
          <el-form-item label="添加成员" label-width="120px" style="width:80%;margin-top:-14px">
            <el-input style="width:30%" v-model="memberInput"></el-input>&emsp;
            <el-select v-model="newField.memberPosition" placeholder="选择成员身份" style="width:33%">
              <el-option label="田主" value="1"></el-option>
              <el-option label="成员" value="0"></el-option>
            </el-select>&emsp;
            <el-button type="primary" @click.stop="addMember">添加</el-button>
          </el-form-item>
          <el-form-item label="成员" label-width="120px" style="margin-top:-14px">
            <el-tag
              v-for="tag in newField.modifiedMemberList"
              :key="tag.memberName"
              closable
              @close="handleClose(tag.memberName)"
              type="success"
              :disable-transitions="false"
              style="margin-right:4px"
            >{{tag.memberName}}</el-tag>
          </el-form-item>
          <el-form-item label="责任田描述" label-width="120px" prop="message" style="margin-top:-14px">
            <el-input
              type="textarea"
              :autosize="{ minRows: 4}"
              placeholder="请输入内容"
              style="width:80%"
              v-model="newField.remarks"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click.stop="submitForm('newField')">创建</el-button>
        </div>
      </el-dialog>
    </el-card>
    <el-card class="box-card">
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        :cell-style="{padding:0}"
        :height="tableHeight"
        ref="table"
        :row-style="rowHeight"
      >
        <el-table-column prop="id" label="ID" width="80">
          <template slot-scope="scope">{{ scope.$index+1 + (nowPage-1)*20}}</template>
        </el-table-column>
        <el-table-column
          prop="responsibleFieldName"
          label="责任田名称"
          :show-overflow-tooltip="true"
          min-width="180"
        ></el-table-column>
        <el-table-column prop="project" label="所属项目" :show-overflow-tooltip="true" min-width="180"></el-table-column>
        <el-table-column prop="memberss" label="田主" :show-overflow-tooltip="true" min-width="180">
          <template slot-scope="scope">
            <span
              v-for="(minMember,index) in scope.row.modifiedMemberList"
              :key="index"
            >{{ minMember.isResponsible==1?minMember.memberName+"&nbsp;":"" }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="remarks" label="备注信息" :show-overflow-tooltip="true" min-width="280"></el-table-column>
        <el-table-column prop="status" label="是否启用" width="100">
          <template slot-scope="scope">{{ scope.row.isEnabled==1?"是":"否" }}</template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" placement="top-start">
              <el-button
                @click.stop="editField(scope.row)"
                type="success"
                size="mini"
                class="el-icon-edit"
                style="margin-right:-10px"
              ></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="删除" placement="top-start">
              <el-button
                @click.stop="deleteField(scope.row)"
                type="danger"
                size="mini"
                class="el-icon-delete"
              ></el-button>
            </el-tooltip>
            <el-dialog
              title="责任田修改"
              :visible.sync="dialogVisible"
              width="35%"
              :modal-append-to-body="false"
              :append-to-body="true"
            >
              <el-form :model="editFieldInfo">
                <el-form-item label="责任田名称" label-width="120px">
                  <el-input
                    v-model="editFieldInfo.responsibleFieldName"
                    :disabled="true"
                    style="width:80%"
                  ></el-input>
                </el-form-item>
                <el-form-item label="是否启用" label-width="120px" style="margin-top:-20px">
                  <el-switch v-model="editFieldInfo.isEnabled"></el-switch>
                </el-form-item>
                <el-form-item label="田主" label-width="120px" style="margin-top:-20px">
                  <span
                    v-for="(ownerMember,index) in editFieldInfo.modifiedMemberList"
                    :key="index"
                  >{{ ownerMember.isResponsible==1?ownerMember.memberName+"&emsp;":"" }}</span>
                </el-form-item>
                <el-form-item label="添加成员" label-width="120px" style="margin-top:-20px;">
                  <el-input style="width:30%" v-model="memberInput"></el-input>&emsp;
                  <el-select v-model="memberPositionss" placeholder="请选择成员角色" style="width:30%">
                    <el-option label="田主" value="1"></el-option>
                    <el-option label="成员" value="0"></el-option>
                  </el-select>&emsp;
                  <el-button type="primary" @click.stop="editAddMember">添加</el-button>
                </el-form-item>
                <el-form-item label="成员" label-width="120px" style="margin-top:-20px">
                  <el-tag
                    v-for="tag in editFieldInfo.modifiedMemberList"
                    :key="tag.memberName"
                    closable
                    @close="editHandleClose(tag.memberName)"
                    type="success"
                    :disable-transitions="false"
                    style="margin-right:4px"
                  >{{tag.memberName}}</el-tag>
                </el-form-item>
                <el-form-item label="责任田描述" label-width="120px" style="margin-top:-20px">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 4}"
                    style="width:80%"
                    placeholder="请输入内容"
                    v-model="editFieldInfo.remarks"
                  ></el-input>
                </el-form-item>
              </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click.stop="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click.stop="dialogVisible = false">保 存</el-button>
              </span>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <CutPage v-on:currentPage="currentPage" :total="total" :pagesize="pagesize"></CutPage>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rowHeight: {},
      tableHeight: "无",
      options: [],
      projectValue: "",
      types: [{ value: "开发" }, { value: "测试" }, { value: "其它" }],
      value1: "",
      department: [{ value: "TM" }, { value: "FP" }, { value: "其它" }],
      value2: "",
      area: [
        { value: "成都" },
        { value: "西安" },
        { value: "深圳" },
        { value: "北京" }
      ],
      value3: "",
      departmentValue: [
        { value: "无线交付部" },
        { value: "OSS交付部" },
        { value: "乱七八糟交付部" },
        { value: "北京" }
      ],
      value4: "",
      PMValve: [
        { value: "张三" },
        { value: "李四" },
        { value: "王五" },
        { value: "赵六" }
      ],
      value5: "",
      memberInput: "",
      newField: {
        responsibleFieldName: "",
        remarks: "",
        isEnabled: true,
        memberPosition: "",
        modifiedMemberList: []
      },
      dialogNewField: false,
      dialogVisible: false,
      editFieldInfo: {},
      input: "",
      memberPositionss: "",
      tableData: [],
      nowPage: 1,
      total: 1,
      pagesize: 20,
      rules: {
        name: [{ required: true, message: "必填", trigger: "blur" }],
        status: [{ required: true, message: "必填", trigger: "blur" }],
        message: [{ required: true, message: "必填", trigger: "blur" }]
      }
    };
  },
  mounted() {
    this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 80;
    this.rowHeight = { height: (this.tableHeight - 60) / 20 + "px" };
  },
  created() {
    this.initList();
    this.projectSearch();
  },
  methods: {
    projectSearch() {
      let params = {
        queryConditionStr: ""
      };
      this.request
        .get("/ViewProject/findAllProjectName", params)
        .then(res => {
          console.log(res);
          this.options = res;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    initList() {
      let params = {
        pageSize: 20,
        pageNumber: 1,
        searchParams: {
          // search_eq_project_code: this.projectValue,
          // search_eq_role_name: this.memberNmaeInfo
        }
      };
      this.request
        .post("/ViewField/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    currentPage(value) {
      this.nowPage = value;
      let params = {
        pageSize: 20,
        pageNumber: this.nowPage,
        searchParams: {
          // search_eq_project_code: this.projectValue,
          // search_eq_role_name: this.memberNmaeInfo
        }
      };
      this.request
        .post("/ViewField/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    addMember() {
      this.newField.modifiedMemberList.push({
        memberName: this.memberInput,
        employeeNumber: "423424",
        isResponsible: this.newField.memberPosition
      });
      this.memberInput = "";
    },
    editAddMember() {
      this.editFieldInfo.modifiedMemberList.push({
        memberName: this.memberInput,
        employeeNumber: "423424",
        responsibleFieldCode: this.editFieldInfo.responsibleFieldCode,
        isResponsible: this.memberPositionss
      });
      this.memberInput = "";
    },
    handleClose(tag) {
      for (let i = 0; i < this.newField.modifiedMemberList.length; i++) {
        if (this.newField.modifiedMemberList[i].memberName == tag) {
          this.newField.modifiedMemberList.splice(i, 1);
          break;
        }
      }
    },
    editHandleClose(tag) {
      for (let i = 0; i < this.editFieldInfo.modifiedMemberList.length; i++) {
        if (this.editFieldInfo.modifiedMemberList[i].memberName == tag) {
          this.editFieldInfo.modifiedMemberList.splice(i, 1);
          break;
        }
      }
    },
    deleteField(row) {
      this.$confirm("确认删除？")
        .then(_ => {
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].id == row.id) {
              this.tableData.splice(i, 1);
            }
          }
        })
        .catch(_ => {});
    },
    editField(row) {
      this.dialogVisible = true;
      this.editFieldInfo = row;
      if (this.editFieldInfo.isEnabled == 1) {
        this.editFieldInfo.isEnabled = true;
      } else {
        this.editFieldInfo.isEnabled = false;
      }
    },
    taskMaintain() {
      this.dialogNewField = true;
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
          this.dialogNewField = false;
          console.log(this.newField);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
</style>