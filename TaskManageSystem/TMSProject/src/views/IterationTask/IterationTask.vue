<template>
  <div class="IterationTask">
    <el-card class="box-card">
      <span>请选择项目 &emsp;</span>
      <el-select v-model="value1" filterable placeholder="选择项目" size="mini">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>&emsp;
      <span>请选择迭代 &emsp;</span>
      <el-select v-model="value2" filterable placeholder="选择迭代" size="mini">
        <el-option
          v-for="item in Iterations"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>&emsp;
      <el-button type="text" class="btn btn-primary" @click.stop="taskSearch">搜索</el-button>
      <NewIteration style="float:right"></NewIteration>
    </el-card>
    <el-col :span="17" class="leftBox" style="margin-top:10px">
      <el-card class="box-card" style="padding-bottom:20px">
        <el-col :span="4" class="schedule" style="text-align:center">
          <div>
            <el-progress
              type="circle"
              :percentage="percentage"
              :color="percentage===100?'#67C23A':(percentage>50?'#1989fa':'#f56c6c')"
            ></el-progress>
          </div>
          <div style="margin-top:10px">迭代进行情况</div>
        </el-col>
        <el-col :span="20">
          <el-card class="box-card" style="padding-bottom:10px">
            <el-col :span="18">
              <el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="100"
                status="success"
                style="width:95%"
              ></el-progress>
            </el-col>
            <el-col :span="3">总任务:</el-col>
            <el-col :span="3">已完成:</el-col>
          </el-card>
          <el-card class="box-card" style="margin-top:4px;padding-bottom:10px">
            <el-col :span="18">
              <el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="80"
                status="warning"
                style="width:95%"
              ></el-progress>
            </el-col>
            <el-col :span="3">昨日任务:</el-col>
            <el-col :span="3">已完成:</el-col>
          </el-card>
          <el-card class="box-card" style="margin-top:4px;padding-bottom:10px">
            <el-col :span="18">
              <el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="40"
                status="exception"
                style="width:95%"
              ></el-progress>
            </el-col>
            <el-col :span="3">今日任务:</el-col>
            <el-col :span="3">已完成:</el-col>
          </el-card>
        </el-col>
      </el-card>
      <el-card style="margin-top:10px">
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
              <el-button @click="resetForm('form')">重置</el-button>
              <el-button type="primary" @click="submitForm">确 定</el-button>
            </div>
          </el-dialog>
        </p>
        <el-table
          :data="taskInfo"
          stripe
          style="width:100%"
          :cell-style="tableStyle"
          @row-click="clickTaskInfo"
        >
          <el-table-column prop="code" label="编码" width="120"></el-table-column>
          <el-table-column prop="name" label="名称" min-width="120"></el-table-column>
          <el-table-column prop="level" label="级别" width="80"></el-table-column>
          <el-table-column prop="newDate" label="创建时间" width="120"></el-table-column>
          <el-table-column prop="beginDate" label="开始时间" width="120"></el-table-column>
          <el-table-column prop="endDate" label="结束时间" width="120"></el-table-column>
          <el-table-column prop="responsible" label="责任人" width="80"></el-table-column>
          <el-table-column prop="schedule" label="进度" min-width="260">
            <template slot-scope="scope">
              <el-progress
                :percentage="scope.row.schedule"
                :color="scope.row.schedule===100?'#67C23A':(scope.row.schedule>50?'#1989fa':'#f56c6c')"
              ></el-progress>
            </template>
          </el-table-column>
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
                  @click.stop="taskClick(scope.row)"
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
                <div class="mainTaskInfo" style="height:600px">
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
                        :percentage="70"
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
                      <p>任务优先级</p>
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
        <CutPage v-on:currentPage="currentPage" style="margin-top:20px"></CutPage>
      </el-card>
    </el-col>
    <el-col :span="7" style="margin-top:10px">
      <el-card>
        <div style="font-weight:700">迭代总览</div>
        <div class="allmsg">
          <div style="width:25%">
            <p>所有迭代</p>
            <p style="font-size:48px;margin-top:-10px">224</p>
            <el-button type="primary" round class="mainBtn btn-primary" size="small">主要按钮</el-button>
          </div>
          <div style="width:18%">
            <div>{{firstNum}}</div><div class="notStart" :style="firstHeight"></div>未开始
          </div>
          <div style="width:18%">
            <div>{{secondNum}}</div><div class="notStart" :style="secondHeight"></div>进行中
          </div>
          <div style="width:18%">
            <div>{{thirdNum}}</div><div class="notStart" :style="thirdHeight"></div>已挂起
          </div>
          <div style="width:18%">
            <div>{{fourNum}}</div><div class="notStart" :style="fourHeight"></div>已关闭
          </div>
        </div>
      </el-card>
      <el-card class="box-card" style="margin-top:10px">
        <div style="margin-left:10px;font-size:16px">我的任务</div>
        <el-table
          :data="myTaskList"
          stripe
          style="width: 100%"
          @row-click="clickRow"
          :cell-style="tableStyle"
        >
          <el-table-column prop="code" label="编码" width="100"></el-table-column>
          <el-table-column prop="name" label="任务名称" width="180"></el-table-column>

          <el-table-column prop="level" label="任务级别" width="100">
            <template scope="scope">
              <div v-if="scope.row.level=== '1'" style="color:red">
                <div
                  style="width:20px;height:20px;line-height:20px;text-align:center;border:1px solid red;border-radius:50%"
                >1</div>
              </div>
              <div v-else-if="scope.row.level=== '2'" style="color:rgb(130, 5, 231)">
                <div
                  style="width:20px;height:20px;line-height:20px;text-align:center;border:1px solid rgb(130, 5, 231);border-radius:50%"
                >2</div>
              </div>
              <div v-else-if="scope.row.level=== '3'" style="color:rgba(6, 99, 19, 0.678)">
                <div
                  style="width:20px;height:20px;line-height:20px;text-align:center;border:1px solid rgba(6, 99, 19, 0.678);border-radius:50%"
                >3</div>
              </div>
            </template>
          </el-table-column>

          <el-table-column prop="status" label="状态" width="100">
            <template scope="scope">
              <span v-if="scope.row.status=== '已完成'" style="color:green">已完成</span>
              <span v-else style="color: red">进行中</span>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import echarts from "echarts";
export default {
  data() {
    return {
      firstHeight:{"height":""},
      firstNum:60,
      secondNum:30,
      thirdNum:30,
      fourNum:30,
      secondHeight:{"height":""},
      thirdHeight:{"height":""},
      fourHeight:{"height":""},
      percentage: 30,
      myTaskList: [
        {
          code: "1",
          name: "王小虎",
          level: "1",
          status: "已完成"
        },
        {
          code: "11",
          name: "王小虎",
          level: "2",
          status: "已完成"
        },
        {
          code: "111",
          name: "王小虎",
          level: "3",
          status: "已完成"
        },
        {
          code: "2",
          name: "王小虎",
          level: "1",
          status: "进行中"
        },
        {
          code: "3",
          name: "王小虎",
          level: "1",
          status: "进行中"
        },
        {
          code: "42",
          name: "王小虎",
          level: "1",
          status: "进行中"
        },
        {
          code: "411",
          name: "王小虎",
          level: "1",
          status: "进行中"
        },
        {
          code: "43",
          name: "王小虎",
          level: "1",
          status: "进行中"
        },
        {
          code: "412",
          name: "王小虎",
          level: "1",
          status: "进行中"
        },
        {
          code: "44",
          name: "王小虎",
          level: "1",
          status: "进行中"
        },
        {
          code: "413",
          name: "王小虎",
          level: "1",
          status: "进行中"
        }
      ],
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ],
      options: [
        {
          value: "选项1",
          label: "第一个项目"
        },
        {
          value: "选项2",
          label: "第二个项目"
        },
        {
          value: "选项3",
          label: "第三个项目"
        },
        {
          value: "选项4",
          label: "第四个项目"
        },
        {
          value: "选项5",
          label: "第五个项目"
        }
      ],
      Iterations: [
        {
          value: "选项1",
          label: "第一个迭代"
        },
        {
          value: "选项2",
          label: "第二个迭代"
        },
        {
          value: "选项3",
          label: "第三个迭代"
        },
        {
          value: "选项4",
          label: "第四个迭代"
        },
        {
          value: "选项5",
          label: "第五个迭代"
        }
      ],
      value1: "",
      value2: "",
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
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90
        },
        {
          code: "1231243232",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 50
        },
        {
          code: "1231236532",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 100
        },
        {
          code: "1231231232",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90
        },
        {
          code: "1231235432",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90
        },
        {
          code: "1231231276",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90
        },
        {
          code: "1231231298",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90
        },
        {
          code: "1231231123",
          name: "第一个任务名称",
          level: "一级",
          newDate: "2019-01-01",
          beginDate: "2019-02-02",
          endDate: "2019-03-03",
          responsible: "张三",
          schedule: 90
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
        data2: [{ required: true, message: "请输入任务时间", trigger: "blur" }]
      }
    };
  },
  created(){
    this.firstHeight.height=(this.firstNum*1>100?100:this.firstNum*1)+"px"
    this.secondHeight.height=(this.secondNum*1>100?100:this.secondNum*1)+"px"
    this.thirdHeight.height=(this.thirdNum*1>100?100:this.thirdNum*1)+"px"
    this.fourHeight.height=(this.fourNum*1>100?100:this.fourNum*1)+"px"
  },
  mounted(){
    
  },
  methods: {
    resetForm(formName) {
      this.form = {};
    },
    clickTaskInfo(row) {
      this.dialogTableVisible = true;
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
    },
    tableStyle({ row, rowIndex }) {
      return "padding:4px";
    },
    currentPage(value) {
      console.log(value);
    },
    clickRow(row) {
      console.log(row);
      this.dialogTableVisible = true;
    },
    taskSearch() {
      console.log(this.value1, this.value2);
    },
    handleClick(row) {
      alert(row.date);
    },
    newTask() {
      this.dialogFormVisible = true;
    },
    submitForm() {
      this.dialogFormVisible = false;
      console.log(this.form);
    },
    taskClick(row) {
      this.dialogTableVisible = true;

      console.log(row);
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
    }
  }
};
</script>

<style lang="less" scoped>
.allmsg {
  text-align: center;
  margin-top: 14px;
  display: flex;
  align-items: flex-end;
}
.notStart {
  width: 20px;
  margin: 0 auto;
  border-radius: 10px 10px 0 0;
  background-color: #3280fc;
}
</style>