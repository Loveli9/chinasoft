<template>
  <div class="ViewTask">
    <div>
      <el-card>
        <NewProject style="float:right"></NewProject>
        <div>
          <span>请输入项目名称 &emsp;</span>
          <el-select v-model="projectValue" filterable placeholder="请输入项目名称" size="mini">
            <el-option
              v-for="item in projectOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>&emsp;
          <span>请输入任务名称 &emsp;</span>
          <el-select v-model="taskValue" filterable placeholder="请输入任务名称" size="mini">
            <el-option
              v-for="item in taskOptions"
              :key="item.taskCode"
              :label="item.taskName"
              :value="item.taskCode"
            ></el-option>
          </el-select>&emsp;
          <span>请选择级别 &emsp;</span>
          <el-select v-model="levelValue" placeholder="级别" style="width:100px" size="mini">
            <el-option
              v-for="item in levelOptions"
              :key="item.value"
              :label="item.value"
              :value="item.value"
            ></el-option>
          </el-select>&emsp;
          <el-button type="text" class="btn btn-primary">搜索</el-button>
        </div>
      </el-card>
    </div>
    <el-card class="box-card leftBox">
      <p style="font-size:18px">
        任务列表
        <el-button
          style="float:right;margin-right:5px"
          type="text"
          class="btn btn-primary"
          @click.stop="newTask"
        >新建任务</el-button>
        <el-dialog title="新建任务" :visible.sync="dialogFormVisible">
          <el-form :model="form" :rules="rules" ref="form">
            <el-form-item label="任务名称" label-width="120px" prop="name">
              <el-input v-model="form.name" clearable></el-input>
            </el-form-item>
            <el-form-item label="所属项目" label-width="120px" prop="project">
              <el-input v-model="form.project" clearable></el-input>
            </el-form-item>
            <el-form-item label="所属迭代" label-width="120px" prop="Iteration">
              <el-input v-model="form.Iteration" clearable></el-input>
            </el-form-item>
            <el-form-item label="指派给" label-width="120px" prop="Responsible">
              <el-input v-model="form.Responsible" clearable></el-input>
            </el-form-item>
            <el-form-item label="任务级别" label-width="120px" prop="level">
              <el-select v-model="form.level" placeholder="请选择任务级别">
                <el-option label="一级" value="first"></el-option>
                <el-option label="二级" value="second"></el-option>
                <el-option label="三级" value="third"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="计划时间" label-width="120px" required>
              <el-col :span="11">
                <el-date-picker
                  type="date"
                  placeholder="开始时间"
                  v-model="form.date1"
                  style="width: 100%;"
                ></el-date-picker>
              </el-col>
              <el-col :span="11">
                <el-date-picker
                  type="date"
                  placeholder="结束时间"
                  v-model="form.date2"
                  style="width: 100%;"
                ></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label="备注信息" label-width="120px">
              <el-input type="textarea" v-model="form.des" autosize></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click.stop="resetForm('form')">重置</el-button>
            <el-button type="primary" @click.stop="submitForm">确 定</el-button>
          </div>
        </el-dialog>
      </p>
      <el-table
        :data="taskInfo"
        stripe
        style="width: 100%"
        :cell-style="{padding:0}"
        @row-click="clickViewTask"
        :height="tableHeight"
        ref="table"
        :row-style="rowHeight"
      >
        <el-table-column prop="code" label="编码" width="120"></el-table-column>
        <el-table-column prop="name" label="名称" width="120"></el-table-column>
        <el-table-column prop="level" label="级别" width="80"></el-table-column>
        <el-table-column prop="newDate" label="创建时间" width="120"></el-table-column>
        <el-table-column prop="beginDate" label="开始时间" width="120"></el-table-column>
        <el-table-column prop="endDate" label="结束时间" width="120"></el-table-column>
        <el-table-column prop="responsible" label="责任人" width="80"></el-table-column>
        <el-table-column prop="schedule" label="进度" width="260">
          <template slot-scope="scope">
            <el-progress
              :percentage="scope.row.schedule"
              :color="scope.row.schedule===100?'#67C23A':(scope.row.schedule>50?'#1989fa':'#f56c6c')"
            ></el-progress>
          </template>
        </el-table-column>
        <el-table-column prop="des" label="描述" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="删除" placement="top-start">
              <el-button
                @click.stop="deleteClick(scope.row)"
                type="danger"
                size="mini"
                class="el-icon-delete"
                style="margin-right:-10px"
              ></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="查看详情" placement="top-start">
              <el-button
                @click.stop="clickViewTask(scope.row)"
                type="success"
                size="mini"
                class="el-icon-view"
              ></el-button>
            </el-tooltip>
            <el-dialog
              title="任务详情"
              :visible.sync="dialogTableVisible"
              :modal-append-to-body="false"
              :append-to-body="true"
              @open="open()"
            >
              <div class="mainTaskInfo" style="height:500px">
                <el-col :span="14" style="margin-right:10px">
                  <el-card class="box-card infoLeftBox">
                    <div>任务描述：{{scope.row.level}}</div>
                    <div id="echarts_div" ref="chart" style="width: 100%;height:400px;"></div>
                  </el-card>
                </el-col>
                <el-col :span="9">
                  <el-card class="box-card">
                    <p>项目进度</p>
                    <el-progress
                      :text-inside="true"
                      :stroke-width="24"
                      :percentage="scope.row.schedule"
                      status="success"
                    ></el-progress>
                  </el-card>
                  <el-card class="box-card">
                    <p>&emsp;任务名称</p>
                    <p>&emsp;所属项目</p>
                    <p>&emsp;迭代编码</p>
                    <p>父任务编码</p>
                    <p>责任田编码</p>
                    <p>&emsp;任务级别</p>
                    <p>&emsp;任务类型</p>
                    <p>&emsp;创建时间</p>
                    <p>&emsp;开始时间</p>
                    <p>&emsp;计划用时</p>
                    <p>&emsp;&emsp;创建人</p>
                  </el-card>
                </el-col>
              </div>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <CutPage v-on:currentPage="currentPage" :total="430"></CutPage>
    </el-card>
  </div>
</template>

<script>
import echarts from "echarts";
export default {
  data() {
    return {
      rowHeight:{},
      tableHeight: "无",
      projectOptions: [
        {
          value: "选项1",
          label: "第一个项目大项目"
        },
        {
          value: "选项2",
          label: "第二个项目大项目"
        },
        {
          value: "选项3",
          label: "第三个项目大项目"
        },
        {
          value: "选项4",
          label: "第四个项目大项目"
        },
        {
          value: "选项5",
          label: "第五个项目大项目"
        }
      ],
      projectValue: "",
      taskOptions: [
      ],
      taskValue: "",
      levelOptions: [{ value: "一级" }, { value: "二级" }, { value: "三级" }],
      levelValue: "",
      input: "",
      dialogFormVisible: false,
      dialogTableVisible: false,
      form: {
        name: "",
        project: "",
        Iteration: "",
        Responsible: "",
        level: "",
        data1: "",
        data2: "",
        des: ""
      },
      taskInfo: [
        {
          code: "1231231232",
          name: "第一个任务名称",
          level: "二级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 100,
          des:
            "第一个任务描述第一个任务描述第一个任务描述第一个任务描述第一个任务描述第一个任务描述第一个任务描述第一个任务描述"
        },
        {
          code: "1231331232",
          name: "第一个任务名称",
          level: "二级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231276232",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231231982",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 30,
          des: "第一个任务描述"
        },
        {
          code: "1231231456",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231231654",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "12312316456",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        },
        {
          code: "1231264532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90,
          des: "第一个任务描述"
        }
      ],
      rules: {
        name: [{ required: true, message: "请输入任务名称", trigger: "blur" }],
        project: [
          { required: true, message: "请输入所属项目", trigger: "blur" }
        ],
        Iteration: [
          { required: true, message: "请输入所属迭代", trigger: "blur" }
        ],
        Responsible: [
          { required: true, message: "请输入指派人", trigger: "blur" }
        ],
        level: [{ required: true, message: "请输入任务级别", trigger: "blur" }],
        data1: [{ required: true, message: "请输入任务时间", trigger: "blur" }],
        data2: [{ required: true, message: "请输入任务 时间", trigger: "blur" }]
      }
    };
  },
  mounted() {
    this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 70;
    this.rowHeight={"height":(this.tableHeight-60)/20+"px"}
  },
  created(){
    this.taskSearch()
  },
  methods: {
    taskSearch() {
      let params = {
        queryConditionStr:""
      };
      this.request
        .get("/ViewTask/findAllTaskName",params)
        .then(res => {
          console.log(res);
          this.taskOptions = res;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    resetForm(formName) {
      this.form = {};
    },
    clickViewTask(row) {
      this.dialogTableVisible = true;
      console.log(row);
    },
    currentPage(value) {
      console.log(value);
    },
    newTask() {
      this.dialogFormVisible = true;
    },
    submitForm() {
      this.dialogFormVisible = false;
      console.log(this.form);
    },
    deleteClick(row) {
      this.$confirm("确认删除？")
        .then(_ => {
          for (let i = 0; i < this.taskInfo.length; i++) {
            if (this.taskInfo[i].code == row.code) {
              this.taskInfo.splice(i, 1);
            }
          }
        })
        .catch(_ => {});
    },
    open() {
      const t = this;
      setTimeout(() => {
        var bar_dv = this.$refs.chart;
        if (bar_dv) {
          let myChart = echarts.init(bar_dv);
          let option = {
            title: {
              left: "left",
              text: "任务进行情况"
            },
            xAxis: {
              type: "category",
              data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
            },
            yAxis: {
              type: "value"
            },
            series: [
              {
                data: [820, 932, 901, 934, 1290, 1330, 1320],
                type: "line"
              }
            ]
          };
          myChart.setOption(option);
          window.addEventListener("resize", function() {
            myChart.resize();
          });
        } else {
          console.log("bar_dv为空!");
        }
      }, 0);
    }
  }
};
</script>

<style lang="scss" scoped>
</style>