<template>
  <div class="Member">
    <el-card>
      <span>请输入项目名称 &emsp;</span>
      <el-select v-model="projectValue" filterable placeholder="请输入项目名称" size="mini" clearable>
        <el-option
          v-for="item in projectOptions"
          :key="item.projectCode"
          :label="item.projectName"
          :value="item.projectCode"
        ></el-option>
      </el-select>&emsp;
      <span>请输入成员姓名 &emsp;</span>
      <el-select
        v-model="memberNmaeInfo"
        filterable
        placeholder="请输入成员姓名"
        size="mini"
        style="width:130px"
        clearable
      >
        <el-option
          v-for="item in memberName"
          :key="item.name"
          :label="item.name"
          :value="item.domainAccount"
        ></el-option>
      </el-select>&emsp;
      <el-button type="text" class="btn btn-primary" @click="searchMember">搜索</el-button>
      <el-button
        type="text"
        class="btn btn-primary"
        style="float:right"
        @click.stop="addMember"
      >添加成员</el-button>
      <el-dialog
        title="添加成员"
        :visible.sync="dialogFormVisible"
        :append-to-body="true"
        :modal-append-to-body="false"
      >
        <el-form :model="form" size="mini">
          <el-form-item label="选择项目组" label-width="120px">
            <el-select v-model="selectValue1" filterable placeholder="请选择部门">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>&emsp;
            <span>选择团队</span>&nbsp;
            <el-select v-model="selectValue2" filterable placeholder="请选择团队">
              <el-option
                v-for="item in options2"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="团队成员"
            label-width="120px"
            v-for="( childMember ,item ) in form.member"
            :key="item"
          >
            <el-input v-model="childMember.name" style="width:218px"></el-input>&emsp;
            <span>成员岗位</span>&nbsp;
            <el-input v-model="childMember.position" style="width:218px" disabled></el-input>&emsp;
            <el-button
              type="primary"
              icon="el-icon-plus"
              circle
              size="mini"
              @click.stop="addMemberBtn"
            ></el-button>
            <el-button
              type="primary"
              icon="el-icon-minus"
              circle
              size="mini"
              @click.stop="deleteMemberBtn(childMember.name)"
            ></el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.stop="saveMember" style="margin-left:50px">保存</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </el-card>
    <el-card>
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        :cell-style="{padding:0}"
        :height="tableHeight"
        ref="table"
        :row-style="rowHeight"
      >
        <el-table-column prop="id" label="ID" width="100">
          <template slot-scope="scope">{{ scope.$index+1 + (nowPage-1)*20}}</template>
        </el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="domainAccount" label="域账号"></el-table-column>
        <el-table-column prop="projectCode" label="所属项目"></el-table-column>
        <el-table-column prop="iterativeCode" label="所属迭代"></el-table-column>
        <el-table-column prop="inputRatio" label="投入比"></el-table-column>
        <el-table-column prop="id" label="角色ID"></el-table-column>
        <el-table-column prop="enterProjectTime" label="进入项目时间"></el-table-column>
        <el-table-column prop="exitProjectTime" label="退出项目时间"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="删除" placement="top-start">
              <el-button
                @click.stop="deleteMember(scope.row)"
                type="danger"
                size="mini"
                class="el-icon-delete"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <CutPage v-on:currentPage="currentPage" :total="total"></CutPage>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      memberNmaeInfo: "",
      rowHeight: {},
      tableHeight: "无",
      projectOptions: [],
      memberName: [],
      memberNameInfo: "",
      projectValue: "",
      selectValue1: "",
      selectValue2: "",
      options: [
        {
          value: "选项1",
          label: "第一个部门"
        },
        {
          value: "选项2",
          label: "第二个部门"
        },
        {
          value: "选项3",
          label: "第三个部门"
        },
        {
          value: "选项4",
          label: "第四个部门"
        },
        {
          value: "选项5",
          label: "第五个部门"
        }
      ],
      options2: [
        {
          value: "选项1",
          label: "第一个团队"
        },
        {
          value: "选项2",
          label: "第二个团队"
        },
        {
          value: "选项3",
          label: "第三个团队"
        },
        {
          value: "选项4",
          label: "第四个团队"
        },
        {
          value: "选项5",
          label: "第五个团队"
        }
      ],
      dialogFormVisible: false,
      form: {
        name: "",
        member: [
          { name: "张三", position: "测试" },
          { name: "李四", position: "测试" },
          { name: "王五", position: "测试" }
        ]
      },
      input: "",
      tableData: [
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "1",
          name: "张三",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "2",
          name: "李四",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "3",
          name: "王五",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "4",
          name: "赵六",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "5",
          name: "孙二狗",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        },
        {
          id: "6",
          name: "哈哈哈",
          account: "lwx789456",
          projectCode: "123456789",
          IterationCode: "123456",
          inputRatio: "50%",
          characterID: "2334",
          enterTime: "2019-01-01",
          dropTime: "2020-02-02"
        }
      ],
      nowPage: 1,
      total: 1
    };
  },
  mounted() {
    this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 70;
    this.rowHeight = { height: (this.tableHeight - 60) / 20 + "px" };
  },
  created() {
    this.initList();
    this.projectSearch();
    this.memberSearch();
  },
  methods: {
    memberSearch() {
      let params = {
        queryConditionStr: ""
      };
      this.request
        .post("/Member/queryAllMember", params)
        .then(res => {
          console.log(res);
          this.memberName = res;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    projectSearch() {
      let params = {
        queryConditionStr: ""
      };
      this.request
        .get("/ViewProject/findAllProjectName", params)
        .then(res => {
          this.projectOptions = res;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    currentPage(value) {
      this.nowPage = value;
      let params = {
        pageSize: 20,
        pageNumber: value,
        searchParams: {}
      };
      this.request
        .post("Member/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    addMember() {
      this.dialogFormVisible = true;
    },
    deleteMember(row) {
      this.$confirm("确认删除？")
        .then(_ => {
          let id = row.id;
          this.request
            .get(`/Member/delete/` + id)
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
    },
    addMemberBtn() {
      let item = { name: "", position: "" };
      this.form.member.push(item);
    },
    deleteMemberBtn(value) {
      for (let i = 0; i < this.form.member.length; i++) {
        if (this.form.member[i].name == value) {
          this.form.member.splice(i, 1);
        }
      }
    },
    saveMember() {
      this.dialogFormVisible = false;
      console.log(this.form);
    },
    searchMember() {
      let params = {
        pageSize: 20,
        pageNumber: 1,
        searchParams: {
          search_eq_project_code: this.projectValue,
          search_eq_role_name: this.memberNmaeInfo
        }
      };
      console.log(params);
      this.request
        .post("/Member/list", params)
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
          search_eq_project_code: this.projectValue,
          search_eq_role_name: this.memberNmaeInfo
        }
      };
      this.request
        .post("/Member/list", params)
        .then(res => {
          this.total = res.total;
          this.tableData = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    }
  }
};
</script>

<style lang="less" scoped>
</style>