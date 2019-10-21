<template>
  <div class="ProjectInfo">
    <div>
      <el-card>
        <NewProject></NewProject>
        <div>
          <span>类型 &emsp;</span>
          <el-select v-model="value3" placeholder="类型" style="width:100px" size="mini" clearable>
            <el-option
              v-for="item in area"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>&emsp;
          <span>责任人 &emsp;</span>
          <el-input v-model="value5" placeholder="责任人" size="mini" style="width:150px"></el-input>&emsp;
          <span>项目名称 &emsp;</span>
          <el-select v-model="value1" filterable placeholder="请输入项目名称" size="mini">
            <el-option
              v-for="item in options"
              :key="item.projectCode"
              :label="item.projectName"
              :value="item.projectCode"
            ></el-option>
          </el-select>&emsp;
          <el-button type="text" class="btn btn-primary" @click="searchInfo">搜索</el-button>
        </div>
      </el-card>
    </div>
    <div class="infoBox">
      <el-card class="box-card leftBox" ref="left">
        <p style="font-size:18px">
          任务列表
          <el-button style="padding: 3px 0;float:right" type="text" @click.stop="taskMaintain">任务维护</el-button>
          <el-button
            style="padding: 3px 0;float:right;margin-right:5px"
            type="text"
            @click.stop="newTask"
          >新建任务</el-button>
          <el-button style="padding: 3px 0;float:right" type="text" @click.stop="projectMember">成员维护</el-button>
          <el-dialog title="新建任务" :visible.sync="dialogFormVisible" width="35%">
            <el-form :model="form" :rules="rules" ref="form" size="mini" :inline-message="true">
              <el-form-item label="任务名称" label-width="120px" prop="taskName">
                <el-input v-model="form.taskName" clearable style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="任务编码" label-width="120px" prop="taskCode">
                <el-input v-model="form.taskCode" clearable style="width:80%"></el-input>
              </el-form-item>
              <el-form-item
                label="所属项目"
                label-width="120px"
                prop="project"
                style="margin-top:-12px"
              >
                <el-select
                  v-model="form.project"
                  filterable
                  placeholder="请选择所属项目"
                  size="mini"
                  style="width:80%"
                  @blur="changeBox"
                >
                  <el-option
                    v-for="item in taskOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item
                label="所属迭代"
                label-width="120px"
                prop="Iteration"
                style="margin-top:-12px"
              >
                <el-select
                  v-model="form.Iteration"
                  filterable
                  placeholder="请选择所属项目"
                  size="mini"
                  style="width:80%"
                >
                  <el-option
                    v-for="item in IterationOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="关联责任田" label-width="120px" style="margin-top:-12px">
                <el-select
                  v-model="form.AssociationField"
                  filterable
                  placeholder="请选择关联的责任田"
                  size="mini"
                  style="width:80%"
                >
                  <el-option
                    v-for="item in AssociationFieldOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>

              <el-form-item
                label="指派给"
                label-width="120px"
                prop="responsiblePerson"
                style="margin-top:-12px"
              >
                <el-input v-model="form.responsiblePerson" clearable style="width:80%"></el-input>
              </el-form-item>
              <el-form-item
                label="任务级别"
                label-width="120px"
                prop="taskLevel"
                style="margin-top:-12px"
              >
                <el-select v-model="form.taskLevel" placeholder="请选择任务级别">
                  <el-option label="一级" value="first"></el-option>
                  <el-option label="二级" value="second"></el-option>
                  <el-option label="三级" value="third"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item
                label="计划时间"
                label-width="120px"
                required
                style="width:80%;margin-top:-12px"
              >
                <el-date-picker
                  type="date"
                  placeholder="开始时间"
                  v-model="form.taskStartTime"
                  style="width: 48%;"
                ></el-date-picker>-
                <el-date-picker
                  type="date"
                  placeholder="结束时间"
                  v-model="form.taskEndTime"
                  style="width: 48%;"
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="备注信息" label-width="120px" style="margin-top:-12px">
                <el-input
                  type="textarea"
                  v-model="form.des"
                  :autosize="{ minRows: 4}"
                  style="width:80%"
                ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click.stop="resetForm('form')">重置</el-button>
              <el-button type="primary" @click.stop="submitForm('form')">确 定</el-button>
            </div>
          </el-dialog>
        </p>
        <el-table
          :data="taskInfo"
          stripe
          style="width: 100%"
          :cell-style="{padding:0}"
          @row-click="clickProjectInfo"
          :height="tableHeight"
          ref="table"
          :row-style="rowHeight"
        >
          <el-table-column prop="taskName" label="名称" min-width="120"></el-table-column>
          <el-table-column prop="projectCode" label="所属项目" min-width="120"></el-table-column>
          <el-table-column prop="taskCode" label="编码" width="120"></el-table-column>
          <el-table-column prop="taskLevel" label="级别" width="100"></el-table-column>
          <el-table-column prop="taskType" label="类型" width="100"></el-table-column>
          <el-table-column prop="creationTime" label="创建时间" width="150"></el-table-column>
          <el-table-column prop="taskStartTime" label="开始时间" width="150"></el-table-column>
          <el-table-column prop="taskEndTime" label="结束时间" width="150"></el-table-column>
          <el-table-column prop="responsiblePerson" label="责任人" width="80"></el-table-column>
          <el-table-column prop="schedule" label="进度" width="160">
            <template slot-scope="scope">
              <el-progress
                :percentage="scope.row.schedule"
                :color="scope.row.schedule===100?'#67C23A':(scope.row.schedule>50?'#1989fa':'#f56c6c')"
              ></el-progress>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="60">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" content="查看详情" placement="top-start">
                <el-button
                  @click.stop="clickProjectInfo(scope.row)"
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
                      <div>任务描述：</div>
                      <div id="echarts_div" ref="chart" style="width: 100%;height:400px;"></div>
                    </el-card>
                  </el-col>
                  <el-col :span="9">
                    <el-card class="box-card">
                      <p>项目进度</p>
                      <el-progress
                        :text-inside="true"
                        :stroke-width="24"
                        :percentage="100"
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
        <CutPage v-on:currentPage="currentPage" :total="total"></CutPage>
      </el-card>
      <div class="rightBox">
        <el-card class="box-card rightBottonBox" ref="rightTop">
          <el-col :span="10">
            <ul class="leftUl">
              <li>&emsp;项目编码：31321</li>
              <li>&emsp;项目名称：第一个项目名称</li>
              <li>&emsp;项目类型：自研开发</li>
              <li>&emsp;商务类型：商务类型1</li>
              <li>&emsp;&emsp;业务群：华为业务群</li>
              <li>&emsp;&emsp;业务线：第一条业务线</li>
              <li>&emsp;归属地区：成都</li>
              <li>客户接口人：张三</li>
              <li>&emsp;&emsp;&emsp;&nbsp;PM：李四</li>
              <li>&emsp;&emsp;&emsp;SPM：王五</li>
            </ul>
          </el-col>
          <el-col :span="14">
            <ul class="leftUl">
              <li>计划启动时间：2019-19-19</li>
              <li>计划结束时间：2019-20-20</li>
              <li>实际启动时间：2019-25-25</li>
              <li>实际结束时间：2019-13-13</li>
              <li>&emsp;&emsp;创建时间：2019-52-22</li>
              <li>&emsp;&emsp;项目状态：进行中</li>
              <li>&emsp;&emsp;交付经理：张三</li>
              <li>&emsp;&emsp;&emsp;创建人：李四</li>
              <li>&emsp;&emsp;&emsp;&emsp;:&nbsp;QA：王五</li>
            </ul>
          </el-col>
        </el-card>
        <el-card class="box-card">
          <p style="font-size:18px">
            责任田信息
            <el-button
              style="float:right"
              type="text"
              class="btn btn-primary"
              @click.stop="fliedMaintain"
            >责任田维护</el-button>
          </p>
          <div class="fieldInfo">
            <el-collapse :accordion="true">
              <el-collapse-item
                :title="children.responsibleFieldName"
                v-for="(children,item) in fieldInfo"
                :key="item"
              >
                <div style="margin-left:20px">
                  {{ children.remarks }}
                  <el-tooltip class="item" effect="dark" content="修改" placement="top-start">
                    <el-button
                      type="primary"
                      size="mini"
                      class="el-icon-edit"
                      style="float:right;margin-right:10px"
                      @click="editFild(children)"
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
                        <el-select
                          v-model="memberPositionss"
                          placeholder="请选择成员角色"
                          style="width:30%"
                        >
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
                      <el-button type="primary" @click="saveEditMember">保 存</el-button>
                    </span>
                  </el-dialog>
                </div>
                <el-table
                  :data="children.modifiedMemberList"
                  style="width: 100%;margin-left:20px"
                  :cell-style="{padding:0}"
                >
                  <el-table-column prop="memberName" label="姓名" width="80"></el-table-column>
                  <el-table-column prop="employeeNumber" label="华为域账号" min-width="180"></el-table-column>
                  <el-table-column prop="isResponsible" label="角色">
                    <template slot-scope="scope">{{ scope.row.isResponsible==0?"成员":"田主" }}</template>
                  </el-table-column>
                </el-table>
              </el-collapse-item>
              <el-pagination
                small
                layout="total, prev, pager, next"
                :total="totalmin"
                :page-size="15"
                @current-change="currentPageChange"
              ></el-pagination>
            </el-collapse>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import echarts from "echarts";
export default {
  data() {
    return {
      rowHeight: {},
      tableHeight: "无",
      taskOptions: [
        {
          value: "选项1",
          label: "第一个任务名称"
        },
        {
          value: "选项2",
          label: "第二个任务名称"
        },
        {
          value: "选项3",
          label: "第三个任务名称"
        },
        {
          value: "选项4",
          label: "第四个任务名称"
        },
        {
          value: "选项5",
          label: "第五个任务名称"
        }
      ],
      IterationOptions: [
        {
          value: "选项1",
          label: "第一个任务名称"
        },
        {
          value: "选项2",
          label: "第二个任务名称"
        },
        {
          value: "选项3",
          label: "第三个任务名称"
        },
        {
          value: "选项4",
          label: "第四个任务名称"
        },
        {
          value: "选项5",
          label: "第五个任务名称"
        }
      ],
      AssociationFieldOptions: [
        {
          value: "选项1",
          label: "第一个任务名称"
        },
        {
          value: "选项2",
          label: "第二个任务名称"
        },
        {
          value: "选项3",
          label: "第三个任务名称"
        },
        {
          value: "选项4",
          label: "第四个任务名称"
        },
        {
          value: "选项5",
          label: "第五个任务名称"
        }
      ],
      options: [],
      nowPage: 1,
      memberInput: "",
      editFieldInfo: {},
      dialogVisible: false,
      value: "",
      types: [{ value: "开发" }, { value: "测试" }, { value: "其它" }],
      value1: "",
      department: [{ value: "TM" }, { value: "FP" }, { value: "其它" }],
      area: [
        { value: "1", label: "需求" },
        { value: "2", label: "问题" },
        { value: "3", label: "资料" }
      ],
      value3: "",
      value5: "",
      form: {
        taskName: "",
        taskCode: "",
        project: "",
        AssociationField: "",
        Iteration: "",
        responsiblePerson: "",
        taskLevel: "",
        taskStartTime: "",
        taskEndTime: "",
        des: ""
      },
      dialogFormVisible: false,
      dialogTableVisible: false,
      taskInfo: [],
      input: "",
      total: 1,
      totalmin: 1,
      fieldInfo: [],
      memberPositionss: "",
      rules: {
        taskName: [{ required: true, message: "必填", trigger: "blur" }],
        taskCode: [{ required: true, message: "必填", trigger: "blur" }],
        project: [{ required: true, message: "必填", trigger: "blur" }],
        Iteration: [{ required: true, message: "必填", trigger: "blur" }],
        responsiblePerson: [
          { required: true, message: "必填", trigger: "blur" }
        ],
        taskLevel: [{ required: true, message: "必填", trigger: "blur" }],
        data1: [{ required: true, message: "必填", trigger: "blur" }],
        data2: [{ required: true, message: "必填", trigger: "blur" }]
      },
      projectCode: "空"
    };
  },
  mounted() {
    this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 70;
    this.rowHeight = { height: (this.tableHeight - 60) / 20 + "px" };
  },
  created() {
    this.initInfo();
    this.projectInfo();
    this.fieldInfoMsg();
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
    fieldInfoMsg() {
      let params = {
        pageSize: 15,
        pageNumber: 1,
        searchParams: {
          search_eq_project_code: this.value1
        }
      };
      this.request
        .post("/ProjectInfo/findViewFieldInfo/", params)
        .then(res => {
          console.log(res);
          this.totalmin = res.total;
          this.fieldInfo = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    saveEditMember() {
      let params = this.editFieldInfo;
      params.isEnabled ? (params.isEnabled = 1) : (params.isEnabled = 0);
      console.log(params);
      this.request
        .post("/ProjectInfo/updateViewFieldInfo", params)
        .then(res => {
          if (res.code == 200) {
            this.dialogVisible = false;
            this.$message({
              message: "恭喜你，修改成功",
              type: "success"
            });
            this.fieldInfoMsg();
          }
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    searchInfo() {
      let params = {
        pageSize: 20,
        pageNumber: 1,
        searchParams: {
          search_eq_task_type: this.value3,
          search_eq_responsible: this.value5,
          search_eq_project_code: this.value1
        }
      };
      this.request
        .post("/ProjectInfo/list", params)
        .then(res => {
          this.total = res.total;
          this.taskInfo = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    projectInfo() {
      this.request
        .get(`ViewProject/qryDetail/` + this.projectCode)
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
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
    editHandleClose(tag) {
      for (let i = 0; i < this.editFieldInfo.modifiedMemberList.length; i++) {
        if (this.editFieldInfo.modifiedMemberList[i].memberName == tag) {
          this.editFieldInfo.modifiedMemberList.splice(i, 1);
          break;
        }
      }
    },
    editFild(taskName) {
      this.dialogVisible = true;
      console.log(taskName);
      this.editFieldInfo = taskName;
      if (this.editFieldInfo.isEnabled == 1) {
        this.editFieldInfo.isEnabled = true;
      } else {
        this.editFieldInfo.isEnabled = false;
      }
    },
    clickProjectInfo(row) {
      this.dialogTableVisible = true;
    },
    currentPageChange(val) {
      let params = {
        pageSize: 15,
        pageNumber: val,
        searchParams: {
          search_eq_project_code: ""
        }
      };
      this.request
        .post("/ProjectInfo/findViewFieldInfo/", params)
        .then(res => {
          console.log(res);
          this.totalmin = res.total;
          this.fieldInfo = res.rows;
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
          search_eq_task_type: this.value3,
          search_eq_responsible: this.value5,
          search_eq_project_code: this.value1
        }
      };
      this.request
        .post("/ProjectInfo/list", params)
        .then(res => {
          console.log(res);
          this.total = res.total;
          this.taskInfo = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    },
    newTask() {
      this.dialogFormVisible = true;
    },
    changeBox() {
      alert(1);
    },
    fliedMaintain() {
      this.$router.push("/Main/ViewField");
    },
    projectMember() {
      this.$router.push("/Main/Member");
    },
    taskMaintain() {
      this.$router.push("/Main/ViewTask");
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.form.taskStartTime =
            this.form.taskStartTime.getFullYear() +
            "-" +
            (this.form.taskStartTime.getMonth() + 1) +
            "-" +
            this.form.taskStartTime.getDate();
          this.form.taskEndTime =
            this.form.taskEndTime.getFullYear() +
            "-" +
            (this.form.taskEndTime.getMonth() + 1) +
            "-" +
            this.form.taskEndTime.getDate();
          let params = this.form;
          console.log(params);
          this.request
            .post("ViewTask/insert", params)
            .then(res => {
              console.log(res);
              if (res.code == 200) {
                this.dialogFormVisible = false;
                this.$message({
                  message: "恭喜你，创建成功",
                  type: "success"
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

      console.log(this.form);
    },
    resetForm(formtaskName) {
      this.$refs[formtaskName].resetFields();
    },
    initInfo() {
      let params = {
        pageSize: 20,
        pageNumber: 1,
        searchParams: {
          search_eq_task_type: this.value3,
          search_eq_responsible: this.value5,
          search_eq_project_code: this.value1
        }
      };
      this.request
        .post("/ProjectInfo/list", params)
        .then(res => {
          this.total = res.total;
          this.taskInfo = res.rows;
        })
        .catch(err => {
          console.log("错误原因" + err);
        });
    }
  }
};
</script>

<style lang="less">
.infoBox {
  clear: both;
  .leftBox {
    width: 75%;
    float: left;
  }
  .rightBox {
    width: 24%;
    float: right;
    margin-right: 5px;
  }
  .rightBottonBox {
    width: 100%;
    float: right;
    margin-bottom: 10px;
    .leftUl {
      list-style: none;
      font-size: 12px;
      li {
        margin-top: 5px;
      }
    }
  }
}
.el-collapse-item__header {
  height: 30px !important;
}
</style>
