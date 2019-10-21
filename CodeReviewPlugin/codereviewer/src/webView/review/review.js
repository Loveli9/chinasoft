const testMode = false; // 为true时可以在浏览器打开不报错
const vscode = testMode ? {} : acquireVsCodeApi();
const callbacks = {};

/**
 * 调用vscode原生api
 * @param data 可以是类似 {cmd: 'xxx', param1: 'xxx'}，也可以直接是 cmd 字符串
 * @param cb 可选的回调函数
 */
function callVscode(data, cb) {
    if (typeof data === 'string') {
        data = { cmd: data };
    }
    if (cb) {
        // 时间戳加上5位随机数
        const cbid = Date.now() + '' + Math.round(Math.random() * 100000);
        callbacks[cbid] = cb;
        data.cbid = cbid;
    }
    vscode.postMessage(data);
}

function analysisFileInfo(fileInfo) {
    var object = JSON.parse(fileInfo);
    app.fileInfo = object;
}

function analysisGitLogInfo(gitLogInfo) {
    var object = JSON.parse(gitLogInfo);
    app.commitInfo = [];
    object.forEach(e => {
        app.commitInfo.push(e);
    });
}

function analysisBasicInfo(basicInfo) {
    var obj = JSON.parse(basicInfo);
    if(obj.dominAccount != undefined){
        app.perfectMessage.dominAccount = obj.dominAccount;
    }
    if(obj.dominPwd != undefined){
        app.perfectMessage.dominPwd = obj.dominPwd;
    }
    if(obj.repositories != undefined){
        app.perfectMessage.repositories = obj.repositories;
    }
    if(obj.token != undefined){
        app.perfectMessage.token = obj.token;
    }
    if(obj.repo_array != undefined){
        app.repositories = obj.repo_array;
    }
    if (app.perfectMessage.dominAccount != "" && app.perfectMessage.dominPwd != "" && app.perfectMessage.repositories != "" && app.perfectMessage.token != "") {
        app.isShow = false;
        app.isShowForm = true;
    }
}

function ajax(type, url, contentType, jsonData, successCallBack, failCallBack, errorCallBack) {
    $.ajax({
        type: type,
        url: url,    //测试地址，需要更换为后台服务url
        contentType: contentType,//指定为json类型
        data: jsonData,
        dataType: 'json',
        success: function (data) {//返回json结果
            if (data) { 
                successCallBack(data); //数据入库成功后的回调
            }
            else {
                failCallBack(data); //数据入库失败的回调
            }
        },
        error: function (data) {
            console.log("AJAX error: " + data); //ajax异常
            errorCallBack(data);
        }
    });
}

function ReviewObj(fileInfo, versionInfo, fixAdvice, reviewer, defectType, childType) {
    this.fn = fileInfo._fileName;
    this.fp = fileInfo._filePath;
    this.sl = fileInfo._sLineNumber;
    this.el = fileInfo._eLineNumber;
    this.cb = fileInfo._codeBlock;
    this.cn = versionInfo._author;
    this.email = versionInfo._email;
    this.version = versionInfo._version;
    this.subject = versionInfo._subject;
    this.cTime = versionInfo._commitTime;
    this.ad = fixAdvice;
    this.reviewer = reviewer;
    this.pType = defectType;
    this.cType = childType;
    this.label = '代码检视';
}

var app = new Vue({
    el: '#app',
    data: {
        isShow: true,
        isShowForm: false,
        //webview页面基础配置
        perfectMessage: {
            dominAccount: "",
            dominPwd: "",
            repositories: "",
            token: ""
        },
        repositories: [],
        //来源于git log
        fileInfo: {},
        commitInfo: [],
        selectVinfo: {},
        defectLevels: [{ value: 10, label: '致命' }, { value: 3, label: '严重' }, { value: 1, label: '一般' }, { value: 0.1, label: '提示' }],
        fixAdvice: "修改建议",
        reviewer: "检视人",
        reviewTime: "检视时间",
        defectTypes: [
            {
                lable: '业务逻辑',
                childTypes: ['业务逻辑1', '业务逻辑2']
            },
            {
                lable: '安全编码',
                childTypes: ['安全编码1', '安全编码2']
            },
            {
                lable: '编码规范',
                childTypes: ['编码规范1', '编码规范2']
            }
        ],
        defectType: {},
        childType: ""
    },
    created() {
        if (this.perfectMessage.dominAccount == "" || this.perfectMessage.dominPwd == "" || this.perfectMessage.repositories == "" || this.perfectMessage.token == "") {
            this.isShow = true;
            this.isShowForm = false;
        }
    },
    mounted() {
        window.addEventListener('message', event => {
            var message = event.data;
            switch (message.cmd) {
                case 'gitLog':
                    analysisFileInfo(message.fileInfo);
                    analysisGitLogInfo(message.logInfo);
                    //callVscode({ cmd: 'updateView', info: info }, null);
                    break;

                case 'basicInfo':
                    analysisBasicInfo(message.fileInfo);
                    break;
                default:
                    break;
            }
        });
    },
    methods: {
        // 模拟alert
        alert(info) {
            callVscode({ cmd: 'alert', info: info }, null);
        },
        openFileInFinder() {
            callVscode({ cmd: 'openFileInFinder', path: `package.json` }, () => {
                this.alert('打开成功！');
            });
        },
        submit() {
            var result = new ReviewObj(this.fileInfo, this.selectVinfo, this.fixAdvice, this.perfectMessage.dominAccount,this.defectType.lable, this.childType);
            var jsonData = JSON.stringify(result);
            this.saveBasicInfo();
            ajax('post', 'http://localhost:8434/user/login', 'application/x-www-form-urlencoded', { "result": jsonData }, null, null, null);
            // $.ajax({
            //     type: 'post',
            //     url: 'http://localhost:8434/user/login',    //测试地址，需要更换为后台服务url
            //     contentType: 'application/x-www-form-urlencoded',//指定为json类型
            //     data: { "result": jsonData },
            //     dataType: 'json',
            //     success: function (data) {//返回json结果
            //         if (data) {
            //             console.log("right");   //数据入库成功后的回调
            //         }
            //         else {
            //             console.log("false");   //数据入库失败的回调
            //         }
            //     },
            //     error: function (data) {
            //         console.log("AJAX error: " + data); //ajax异常
            //     }
            // });
        },
        reset() {
            this.fileInfo = {};
            this.commitInfo = {};
            this.selectVinfo = {};
        },
        saveBasicInfo() {
            if (this.perfectMessage.dominAccount == "" ||
                this.perfectMessage.dominPwd == "" ||
                this.perfectMessage.repositories == "" ||
                this.perfectMessage.token == "") {
                return;
            }
            var jsonData = {
                "dominAccount": this.perfectMessage.dominAccount,
                "dominPwd": this.perfectMessage.dominPwd,
                "repositories": this.perfectMessage.repositories,
                "token": this.perfectMessage.token,
                "repo_array": this.repositories
            };
            this.isShow = false;
            this.isShowForm = true;
            callVscode({ cmd: 'basicInfo', info: jsonData }, null);
        },
        resetBasicInfo() {
            this.perfectMessage = {
                dominAccount: "",
                dominPwd: "",
                repositories: "",
                token: ""
            };
        },
        editMessage() {
            this.isShow = true;
        },
        varifyAccount() {
            var url = `https://api.github.com/users/${this.perfectMessage.dominAccount}/repos`;
            ajax('get', url, 'application/json; charset=utf-8', '', (data) => {
                if (data == null || data == undefined) {
                    return;
                }
                this.repositories = [];
                data.forEach(e => {
                    this.repositories.push(e.full_name);
                });
            }, (data)=>{
                callVscode('loginfail',null);
                console.warn("账号/密码错误，登录校验失败");
            }, (data)=>{                
                callVscode('loginerr',null);
                console.error("登录发生错误，请确认账号/密码不为空");
            });
        }
    }
});