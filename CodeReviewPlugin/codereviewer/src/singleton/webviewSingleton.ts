'use strict';

import * as path from 'path';
import * as fs from 'fs';
import { ExtensionContext, WebviewPanel, Uri, window, ViewColumn, Disposable } from 'vscode';
import { Utils } from '../utils/utils';
import { isNullOrUndefined } from 'util';
import { Context } from '../common/context';
import { Log } from '../log/log';
import { utils } from 'mocha';
import { LogInfo } from '../model/logInfo';

export class WebViewSingleton implements Disposable {

    private _webViewPanel: WebviewPanel | undefined;
    private _context: ExtensionContext;
    private static instance: WebViewSingleton;

    private constructor() {
        this._context = Context.context;
    }

    public static getInstance(): WebViewSingleton {
        if (isNullOrUndefined(WebViewSingleton.instance)) {
            this.instance = new WebViewSingleton();
        }
        return this.instance;
    }

    public dispose() {
    }

    private creatPanel(): WebviewPanel {
        let panel = window.createWebviewPanel(
            'CodeReview',
            'CodeReview',
            { viewColumn: ViewColumn.Beside, preserveFocus: false },
            {
                retainContextWhenHidden: true,
                enableFindWidget: true,
                enableCommandUris: true,
                enableScripts: true
            }
        );

        panel.onDidDispose(() => {
            this._webViewPanel = undefined;
        }, null, this._context.subscriptions);

        // Update contents based on view state changes
        panel.onDidChangeViewState((e) => {
            const panel = e.webviewPanel;
            switch (panel.viewColumn) {
                case ViewColumn.One:
                    //need update funciotnA;
                    return;

                case ViewColumn.Two:
                    //need update funciotnB;
                    return;

                case ViewColumn.Three:
                    //need update funciotnC;
                    return;
            }
        },
            null,
            this._context.subscriptions
        );

        panel.webview.onDidReceiveMessage((msg) => {
            // Utils.showInfo(msg.cmd);
            switch (msg.cmd) {
                case "updateView":
                    Log.log("数据刷新完成", true);
                    break;
                case "basicInfo":
                    Context.setBasicInfo(msg.info);
                    break;

                case "loginfail":
                    Utils.showInfo("账号或密码错误，登录失败");
                    break;

                case "loginerr":
                    Utils.showError("请输入有效的域账号域密码进行登录");
                    break;

                default:
                    Log.log(msg);
                    break;

            }
        }, undefined, this._context.subscriptions);

        return panel;
    }

    /**
     * 从某个HTML文件读取能被Webview加载的HTML内容
     * @param {*} context 上下文
     * @param {*} templatePath 相对于插件根目录的html文件相对路径
     */
    private readContentFromFile(filePath: string) {
        const resourcePath = Utils.getExtensionFileAbsolutePath(this._context, filePath);
        const dirPath = path.dirname(resourcePath);
        let html = fs.readFileSync(resourcePath, 'utf-8');
        // vscode不支持直接加载本地资源，需要替换成其专有路径格式，这里只是简单的将样式和JS的路径替换
        html = html.replace(/(<link.+?href="|<script.+?src="|<img.+?src=")(.+?)"/g, (m: any, $1: any, $2: any) => {
            return $1 + Uri.file(path.resolve(dirPath, $2)).with({ scheme: 'vscode-resource' }).toString() + '"';
        });
        return html;
    }

    public showWebView(): boolean {
        try {
            if (isNullOrUndefined(this._webViewPanel)) {
                this._webViewPanel = this.creatPanel();
                this._webViewPanel.webview.html = this.readContentFromFile('src/webView/review/review.html');
                let basicInfo = Context.basicInfo;
                if(isNullOrUndefined(basicInfo)){
                    let os = require('os');
                    let userName = os.userInfo().username;
                    basicInfo = new Tempinfo(userName);
                    console.log(userName);
                }
                this.postMessage('basicInfo', basicInfo, null);
            }
            if (!this._webViewPanel.visible) {
                this._webViewPanel.reveal(ViewColumn.Active);
            }
            return true;
        } catch (error) {
            Log.error(error);
            return false;
        }
    }

    public postMessage(infoType: string, file: any, result: any) {
        if (isNullOrUndefined(this._webViewPanel)) {
            this._webViewPanel = this.creatPanel();
        }
        let fileInfoJson = JSON.stringify(file);
        let resultJson = JSON.stringify(result);
        this._webViewPanel.webview.postMessage({ cmd: infoType, fileInfo: fileInfoJson, logInfo: resultJson });
    }

    /**
     * 执行回调函数
     * @param {*} panel 
     * @param {*} message 
     * @param {*} resp 
     */
    private invokeCallback(panel: WebviewPanel, message: any, resp: any) {
        console.log('回调消息：', resp);
        // 错误码在400-600之间的，默认弹出错误提示
        if (typeof resp === 'object' && resp.code && resp.code >= 400 && resp.code < 600) {
            Utils.showError(resp.message || '发生未知错误！');
        }
        panel.webview.postMessage({ cmd: 'vscodeCallback', cbid: message.cbid, data: resp });
    }
}

class Tempinfo{
    private dominAccount:string;
    constructor(account:string){
        this.dominAccount = account;
    }

    public get account(){
        return this.dominAccount;
    }
}
