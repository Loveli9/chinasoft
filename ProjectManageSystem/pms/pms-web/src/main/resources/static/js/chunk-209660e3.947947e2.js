(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-209660e3"],{"7f7f":function(e,t,a){var l=a("86cc").f,n=Function.prototype,o=/^\s*function ([^ (]*)/,i="name";i in n||a("9e1e")&&l(n,i,{configurable:!0,get:function(){try{return(""+this).match(o)[1]}catch(e){return""}}})},8859:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"Member"},[a("el-card",[a("span",[e._v("请输入项目名称  ")]),a("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入关键字",clearable:"",size:"mini"},model:{value:e.input,callback:function(t){e.input=t},expression:"input"}}),e._v(" \n    "),a("el-button",{staticClass:"btn btn-primary",attrs:{type:"text"}},[e._v("搜索")]),a("el-button",{staticClass:"btn btn-primary",staticStyle:{float:"right"},attrs:{type:"text"},on:{click:function(t){return t.stopPropagation(),e.addMember(t)}}},[e._v("添加成员")]),a("el-dialog",{attrs:{title:"添加成员",visible:e.dialogFormVisible,"append-to-body":!0,"modal-append-to-body":!1},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.form}},[a("el-form-item",{attrs:{label:"选择部门","label-width":"120px"}},[a("el-select",{attrs:{filterable:"",placeholder:"请选择部门"},model:{value:e.selectValue1,callback:function(t){e.selectValue1=t},expression:"selectValue1"}},e._l(e.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1),e._v(" \n          "),a("span",[e._v("选择团队")]),e._v(" \n          "),a("el-select",{attrs:{filterable:"",placeholder:"请选择团队"},model:{value:e.selectValue2,callback:function(t){e.selectValue2=t},expression:"selectValue2"}},e._l(e.options2,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),e._l(e.form.member,function(t,l){return a("el-form-item",{key:l,attrs:{label:"团队成员","label-width":"120px"}},[a("el-input",{staticStyle:{width:"218px"},model:{value:t.name,callback:function(a){e.$set(t,"name",a)},expression:"childMember.name"}}),e._v(" \n          "),a("span",[e._v("成员岗位")]),e._v(" \n          "),a("el-input",{staticStyle:{width:"218px"},attrs:{disabled:""},model:{value:t.position,callback:function(a){e.$set(t,"position",a)},expression:"childMember.position"}}),e._v(" \n          "),a("el-button",{attrs:{type:"primary",icon:"el-icon-plus",circle:"",size:"mini"},on:{click:function(t){return t.stopPropagation(),e.addMemberBtn(t)}}}),a("el-button",{attrs:{type:"primary",icon:"el-icon-minus",circle:"",size:"mini"},on:{click:function(a){return a.stopPropagation(),e.deleteMemberBtn(t.name)}}})],1)}),a("el-form-item",[a("el-button",{staticStyle:{"margin-left":"50px"},attrs:{type:"primary"},on:{click:function(t){return t.stopPropagation(),e.saveMember(t)}}},[e._v("保存")])],1)],2)],1)],1),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:"","cell-style":{padding:0}}},[a("el-table-column",{attrs:{prop:"id",label:"ID",width:"100"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名"}}),a("el-table-column",{attrs:{prop:"account",label:"域账号"}}),a("el-table-column",{attrs:{prop:"projectCode",label:"项目编码"}}),a("el-table-column",{attrs:{prop:"IterationCode",label:"迭代编码"}}),a("el-table-column",{attrs:{prop:"inputRatio",label:"投入比"}}),a("el-table-column",{attrs:{prop:"characterID",label:"角色ID"}}),a("el-table-column",{attrs:{prop:"enterTime",label:"进入项目时间"}}),a("el-table-column",{attrs:{prop:"dropTime",label:"退出项目时间"}}),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"删除",placement:"top-start"}},[a("el-button",{staticClass:"el-icon-delete",attrs:{type:"danger",size:"mini"},on:{click:function(a){return a.stopPropagation(),e.deleteMember(t.row)}}})],1)]}}])})],1),a("CutPage",{staticStyle:{"margin-top":"20px"},on:{currentPage:e.currentPage}})],1)},n=[],o=(a("7f7f"),{data:function(){return{selectValue1:"",selectValue2:"",options:[{value:"选项1",label:"第一个部门"},{value:"选项2",label:"第二个部门"},{value:"选项3",label:"第三个部门"},{value:"选项4",label:"第四个部门"},{value:"选项5",label:"第五个部门"}],options2:[{value:"选项1",label:"第一个团队"},{value:"选项2",label:"第二个团队"},{value:"选项3",label:"第三个团队"},{value:"选项4",label:"第四个团队"},{value:"选项5",label:"第五个团队"}],dialogFormVisible:!1,form:{name:"",member:[{name:"张三",position:"测试"},{name:"李四",position:"测试"},{name:"王五",position:"测试"}]},input:"",tableData:[{id:"1",name:"张三",account:"lwx789456",projectCode:"123456789",IterationCode:"123456",inputRatio:"50%",characterID:"2334",enterTime:"2019-01-01",dropTime:"2020-02-02"},{id:"2",name:"李四",account:"lwx789456",projectCode:"123456789",IterationCode:"123456",inputRatio:"50%",characterID:"2334",enterTime:"2019-01-01",dropTime:"2020-02-02"},{id:"3",name:"王五",account:"lwx789456",projectCode:"123456789",IterationCode:"123456",inputRatio:"50%",characterID:"2334",enterTime:"2019-01-01",dropTime:"2020-02-02"},{id:"4",name:"赵六",account:"lwx789456",projectCode:"123456789",IterationCode:"123456",inputRatio:"50%",characterID:"2334",enterTime:"2019-01-01",dropTime:"2020-02-02"},{id:"5",name:"孙二狗",account:"lwx789456",projectCode:"123456789",IterationCode:"123456",inputRatio:"50%",characterID:"2334",enterTime:"2019-01-01",dropTime:"2020-02-02"},{id:"6",name:"哈哈哈",account:"lwx789456",projectCode:"123456789",IterationCode:"123456",inputRatio:"50%",characterID:"2334",enterTime:"2019-01-01",dropTime:"2020-02-02"}]}},methods:{currentPage:function(e){console.log(e)},addMember:function(){this.dialogFormVisible=!0},deleteMember:function(e){var t=this;this.$confirm("确认删除？").then(function(a){for(var l=0;l<t.tableData.length;l++)t.tableData[l].id==e.id&&t.tableData.splice(l,1)}).catch(function(e){})},addMemberBtn:function(){var e={name:"",position:""};this.form.member.push(e)},deleteMemberBtn:function(e){for(var t=0;t<this.form.member.length;t++)this.form.member[t].name==e&&this.form.member.splice(t,1)},saveMember:function(){this.dialogFormVisible=!1,console.log(this.form)}}}),i=o,r=a("2877"),c=Object(r["a"])(i,l,n,!1,null,"a0054316",null);t["default"]=c.exports}}]);
//# sourceMappingURL=chunk-209660e3.947947e2.js.map