<template>
  <div class="ViewProject">
    <el-card class="box-card">
      <span>请输入项目名称 &emsp;</span>
      <el-select v-model="value1" filterable placeholder="请输入项目名称" size="mini">
          <el-option
            v-for="item in options"
            :key="item.projectCode"
            :label="item.projectName"
            :value="item.projectCode"
          ></el-option>
        </el-select>&emsp;
      <span>迭代状态 &emsp;</span>
      <el-select v-model="value2" placeholder="请选择" size="mini" style="width:150px" clearable>
        <el-option label="已计划" value="1"></el-option>
        <el-option label="进行中" value="2"></el-option>
        <el-option label="已关闭" value="3"></el-option>
        <el-option label="延期" value="4"></el-option>
      </el-select>&emsp;
      <el-button type="text" class="btn btn-primary" @click="searchProject">搜索</el-button>
      <NewIteration style="float:right"></NewIteration>
    </el-card>
    <el-card class="box-card">
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
        <el-table-column
          prop="iterativeCode"
          label="迭代编码"
          width="120"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="projectOwnership"
          label="所属项目"
          min-width="180"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="iterativeName"
          label="迭代名称"
          min-width="180"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="iterationGoal"
          label="迭代目标"
          min-width="180"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column prop="iterativeStatus" label="迭代状态" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="iterativeDescription" label="描述" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="iterationPlanEndDate" label="计划结束时间" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="founder" label="创建人"></el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="查看详情" placement="top-start">
              <el-button
                @click.stop="viewIteration(scope.row)"
                type="success"
                size="mini"
                success
                class="el-icon-view"
                style="margin-right:-10px"
              ></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="删除" placement="top-start">
              <el-button
                @click.stop="deleteIteration(scope.row)"
                type="danger"
                size="mini"
                class="el-icon-delete"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <CutPage v-on:currentPage="currentPage" :total="total" v-bind:pagesize="pagesize"></CutPage>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rowHeight: {},
      tableHeight: "无",
      value1: "",
      value2: "",
      nowPage: 1,
      total: 1,
      pagesize: 20,
      tableData: [],
      nowPage: 1,
      options:[],
    };
  },
  created() {
    this.initList();
    this.projectSearch()
  },
  mounted() {
    this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 70;
    this.rowHeight = { height: (this.tableHeight - 60) / 20 + "px" };
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
      this.$router.push("/Main/IterationTask");
    },
    currentPage(value) {
      this.nowPage = value;
      let params = {
        pageSize: 20,
        pageNumber: value,
        searchParams: {
          search_like_project_ownership: this.value1,
          search_eq_iterative_status: this.value2
        }
      };
      this.request
        .post("ViewIteration/init", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    viewIteration(row) {
      this.$router.push("/Main/IterationTask");
    },
    searchProject() {
      console.log(this.value1, this.value2);
      let params = {
        pageSize: 20,
        pageNumber: 1,
        searchParams: {
          search_eq_project_ownership: this.value1,
          search_eq_iterative_state: this.value2
        }
      };
      console.log(params)
      this.request
        .post("/ViewIteration/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
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
          search_like_project_ownership: this.value1,
          search_eq_iterative_status: this.value2
        }
      };
      this.request
        .post("/ViewIteration/list", params)
        .then(res => {
          console.log(res);
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    deleteIteration(row) {
      this.$confirm("确认删除？")
        .then(_ => {
          let id = row.iterativeCode;
          this.request
            .get(`/ViewIteration/delete/` + id)
            .then(res => {
              if (res.code == 200) {
                this.$message({
                  message: "恭喜你，删除成功",
                  type: "success"
                });
                this.initList();
              }
            })
            .catch(err => {
              console.log("错误原因" + err);
            });
        })
        .catch(_ => {});
    }
  }
};
</script>

<style lang="less" scoped>
</style>