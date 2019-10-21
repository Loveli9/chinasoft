<template>
  <div class="ViewProject">
    <el-card class="box-card">
      <span>项目名称 &emsp;</span>
      <el-select v-model="projectValue" filterable placeholder="请输入项目名称" size="mini" clearable>
        <el-option
          v-for="item in options"
          :key="item.projectCode"
          :label="item.projectName"
          :value="item.projectCode"
        ></el-option>
      </el-select>&emsp;
      <span>项目类型 &emsp;</span>
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
      <el-select v-model="value4" placeholder="交付部" style="width:100px" size="mini" clearable>
        <el-option
          v-for="item in departmentValue"
          :key="item.value"
          :label="item.value"
          :value="item.value"
        ></el-option>
      </el-select>&emsp;
      <span>PM &emsp;</span>
      <el-input v-model="PMValue" placeholder size="mini" style="width:100px"></el-input>&emsp;
      <el-button type="info" size="small" @click="searchInfo" class="btn btn-primary">查询</el-button>
      <NewProject style="float:right"></NewProject>
    </el-card>
    <el-card class="box-card projectList">
      <div style="font-size:20px">项目列表</div>
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        :cell-style="{padding:0}"
        @row-click="clickView"
        :height="tableHeight"
        ref="table"
        :row-style="rowHeight"
      >
        <el-table-column prop="code" label="项目编码" width="100">
          <template slot-scope="scope">{{ scope.$index+1 + (nowPage-1)*20}}</template>
        </el-table-column>
        <el-table-column prop="projectName" label="项目名称" width="180"></el-table-column>
        <el-table-column prop="businessType" label="商务类型" width="100"></el-table-column>
        <el-table-column prop="deliveryDepartment" label="交付部" width="100"></el-table-column>
        <el-table-column prop="deliveryManager" label="交付经理" width="100"></el-table-column>
        <el-table-column prop="actualProjectEndTime" label="预计结束时间" width="140"></el-table-column>
        <el-table-column prop="projectCreationTime" label="项目创建时间" width="150"></el-table-column>
        <el-table-column prop="founder" label="创建人" width="100"></el-table-column>
        <el-table-column prop="pm" label="PM" width="100"></el-table-column>
        <el-table-column prop="attributionArea" label="归属区域" width="100"></el-table-column>
        <el-table-column prop="projectDescription" label="描述"></el-table-column>
        <el-table-column fixed="right" label="操作" width="180">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="删除" placement="top-start">
              <el-button
                @click.stop="deleteClickMenber(scope.row)"
                size="mini"
                class="el-icon-delete"
                type="danger"
                style="margin-right:-10px"
              ></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="修改" placement="top-start">
              <el-button
                @click.stop="editClickMenber(scope.row)"
                size="mini"
                class="el-icon-edit"
                type="primary"
                style="margin-right:-10px"
              ></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="查看" placement="top-start">
              <el-button
                @click.stop="handleClickMenber(scope.row)"
                size="mini"
                class="el-icon-view"
                type="success"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <CutPage v-on:currentPage="currentPage" :total="total" v-bind:pagesize="pagesize"></CutPage>
    </el-card>
    <el-dialog title="修改" :visible.sync="dialogFormVisible" width="35%">
      <el-form :model="form" size="mini">
        <el-form-item label="项目名称" label-width="120px">
          <el-input v-model="form.projectName" autocomplete="off" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="商务类型" label-width="120px" style="width:80%;margin-top:-14px">
          <el-select v-model="form.businessType" placeholder="商务类型">
            <el-option label="IT业务线" value="IT业务线"></el-option>
            <el-option label="商务类型2" value="商务类型2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交付部" label-width="120px" style="width:80%;margin-top:-14px">
          <el-select v-model="form.deliveryDepartment" placeholder="交付部">
            <el-option label="无线交付部" value="无线交付部"></el-option>
            <el-option label="交付部2" value="交付部2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交付经理" label-width="120px" style="margin-top:-14px">
          <el-input v-model="form.deliveryManager" autocomplete="off" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="预计结束时间" label-width="120px" style="margin-top:-14px">
          <el-date-picker v-model="form.actualProjectEndTime" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="归属区域" label-width="120px" style="width:80%;margin-top:-14px">
          <el-select v-model="form.attributionArea" placeholder="区域">
            <el-option label="成都" value="成都"></el-option>
            <el-option label="西安" value="西安"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" label-width="120px" style="margin-top:-14px">
          <el-input
            type="textarea"
            :autosize="{ minRows: 4}"
            placeholder="请输入内容"
            v-model="form.projectDescription"
            style="width:80%"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEditMsg">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {},
      options: [],
      rowHeight: {},
      tableHeight: "无",
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
      PMValue: "",
      value5: "",
      tableData: [],
      nowPage: 1,
      total: 1,
      pagesize: 20,
      dialogFormVisible: false,
      formLabelWidth: "120px"
    };
  },
  mounted() {
    this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 50;
    this.rowHeight = { height: (this.tableHeight - 60) / 20 + "px" };
  },
  created() {
    this.projectList();
    this.projectSearch();
  },
  methods: {
    projectSearch() {
      let params = {
        queryConditionStr:""
      };
      this.request
        .get("/ViewProject/findAllProjectName",params)
        .then(res => {
          console.log(res);
          this.options = res;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    clickView(row) {
      console.log(row);
      this.$router.push("/Main/ProjectInfo");
    },
    //搜索
    searchInfo() {
      let params = {
        pageSize: 20,
        pageNumber: 1,
        searchParams: {
          search_eq_project_code: this.projectValue,
          search_eq_project_type: this.value1,
          search_eq_business_type: this.value2,
          search_eq_attribution_area: this.value3,
          search_eq_delivery_department: this.value4,
          search_eq_pm: this.PMValue
        }
      };
      console.log(params);
      this.request
        .post("ViewProject/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    handleClickMenber(row) {
      console.log(row);
      this.$router.push("/Main/ProjectInfo");
    },
    currentPage(value) {
      this.nowPage = value;
      let params = {
        pageSize: 20,
        pageNumber: value,
        searchParams: {
          search_like_project_name: this.projectValue,
          search_eq_project_type: this.value1,
          search_eq_business_type: this.value2,
          search_eq_attribution_area: this.value3,
          search_eq_delivery_department: this.value4,
          search_eq_pm: this.PMValue
        }
      };
      this.request
        .post("ViewProject/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    //列表分页
    projectList() {
      let params = {
        pageSize: 20,
        pageNumber: 1,
        searchParams: {
          search_like_project_name: this.projectValue,
          search_eq_project_type: this.value1,
          search_eq_business_type: this.value2,
          search_eq_attribution_area: this.value3,
          search_eq_delivery_department: this.value4,
          search_eq_pm: this.PMValue
        }
      };
      this.request
        .post("ViewProject/list", params)
        .then(res => {
          console.log(res);
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    deleteClickMenber(row) {
      this.$confirm("确认删除？")
        .then(_ => {
          let id = row.projectCode;
          this.request
            .get(`/ViewProject/delete/` + id)
            .then(res => {
              if (res.code == 200) {
                this.$message({
                  message: "恭喜你，删除成功",
                  type: "success"
                });
                this.projectList();
              }
            })
            .catch(err => {
              console.log("错误原因" + err);
            });
        })
        .catch(_ => {});
    },
    editClickMenber(row) {
      this.dialogFormVisible = true;
      this.form = row;
    },
    saveEditMsg() {
      let params = this.form;
      console.log(params);
      this.request
        .post("ViewProject/update", params)
        .then(res => {
          if (res.code == 200) {
            this.$message({
              message: "恭喜你，修改成功",
              type: "success"
            });
            this.dialogFormVisible = false;
          }
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    }
  }
};
</script>

<style lang="less" scoped>
.cutPage {
  position: absolute;
  bottom: 0;
  right: 100px;
}
.projectList {
  height: 100%;
}
</style>