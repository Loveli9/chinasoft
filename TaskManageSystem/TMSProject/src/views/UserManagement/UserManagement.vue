<template>
  <div class="UserManagement">
    <el-card>
      <div slot="header" class="clearfix">
        <span style="font-size:16px">用户管理</span>
        <el-button
          style="float: right"
          class="btn btn-primary"
          type="text"
          @click.stop="addMember"
        >添加用户</el-button>
        <el-dialog
          title="添加用户"
          :visible.sync="newMember"
          :modal-append-to-body="false"
          :append-to-body="true"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
        >
          <el-form :model="addMemberForm">
            <el-form-item label="用户名" label-width="120px">
              <el-input v-model="addMemberForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" label-width="120px">
              <el-input type="password" v-model="addMemberForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="活动性质" label-width="120px">
              <el-checkbox-group v-model="addMemberForm.type">
                <el-checkbox label="项目看板" name="type"></el-checkbox>
                <el-checkbox label="项目管理" name="type"></el-checkbox>
                <el-checkbox label="迭代管理" name="type"></el-checkbox>
                <el-checkbox label="责任田管理" name="type"></el-checkbox>
                <el-checkbox label="任务管理" name="type"></el-checkbox>
                <el-checkbox label="系统管理" name="type"></el-checkbox>
                <el-checkbox label="成员管理" name="type"></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click.stop="addMemberButton" style="margin-left:60px">确认添加</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </div>
      <el-table :data="tableData" stripe style="width: 100%" :cell-style="{padding:0}" :height="tableHeight"
        ref="table"
        :row-style="rowHeight">
        <el-table-column prop="ID" label="角色ID" width="180">
          <template slot-scope="scope">{{ scope.$index+1 + (nowPage-1)*20}}</template>
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="180"></el-table-column>
        <el-table-column prop="permission" label="角色权限">
          <template scope="scope">
            <span v-for="(children,item) in scope.row.permission" :key="item">{{ children }}&emsp;</span>
          </template>
        </el-table-column>
        <el-table-column prop="enable" label="是否启用" width="100">
          <template scope="scope">
            <span v-if="scope.row.enable">启用</span>
            <span v-else>禁用</span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" placement="top-start">
              <el-button
                @click.stop="handleClick(scope.row)"
                type="primary"
                size="mini"
                class="el-icon-edit"
              ></el-button>
            </el-tooltip>
            <el-dialog
              title="修改权限"
              :visible.sync="dialogFormVisible"
              :modal-append-to-body="false"
              :append-to-body="true"
              :close-on-click-modal="false"
              :close-on-press-escape="false"
            >
              <el-form :model="form">
                <el-form-item label="用户姓名" label-width="120px">
                  <el-input v-model="form.name" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="活动性质" label-width="120px">
                  <el-checkbox-group v-model="form.type">
                    <el-checkbox label="项目看板" name="type"></el-checkbox>
                    <el-checkbox label="项目管理" name="type"></el-checkbox>
                    <el-checkbox label="迭代管理" name="type"></el-checkbox>
                    <el-checkbox label="责任田管理" name="type"></el-checkbox>
                    <el-checkbox label="任务管理" name="type"></el-checkbox>
                    <el-checkbox label="系统管理" name="type"></el-checkbox>
                    <el-checkbox label="成员管理" name="type"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
                <el-form-item label="是否禁用" label-width="120px">
                  <el-switch v-model="form.delivery"></el-switch>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click.stop="onSubmit" style="margin-left:60px">保存</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <CutPage v-on:currentPage="currentPage" :total="430"></CutPage>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rowHeight: {},
      tableHeight: "无",
      newMember: false,
      dialogFormVisible: false,
      addMemberForm: {
        name: "",
        password: "",
        type: []
      },
      form: {
        name: "",
        type: [],
        delivery: true
      },
      tableData: [
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "23131",
          name: "张三",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "4234234",
          name: "李四",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: true
        },
        {
          ID: "2131231",
          name: "王五",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理"
          ],
          enable: false
        },
        {
          ID: "13145",
          name: "王小虎",
          permission: [
            "项目看板",
            "项目管理",
            "迭代管理",
            "责任田管理",
            "任务管理",
            "权限管理"
          ],
          enable: false
        }
      ],
      nowPage: 1
    };
  },
   mounted() {
    this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 70;
    this.rowHeight = { height: (this.tableHeight - 60) / 20 + "px" };
  },
  methods: {
    currentPage(value) {
      console.log(value);
      this.nowPage = value;
    },
    handleClick(row) {
      this.dialogFormVisible = true;
      this.form.name = row.name;
    },
    onSubmit() {
      this.dialogFormVisible = false;
      this.$message({
        message: "恭喜你，修改成功",
        type: "success"
      });
    },
    addMember() {
      this.newMember = true;
    },
    addMemberButton() {
      console.log(this.addMemberForm);
      this.newMember = false;
      this.$message({
        message: "恭喜你，修改成功",
        type: "success"
      });
    }
  }
};
</script>

<style lang="less" scoped>
</style>