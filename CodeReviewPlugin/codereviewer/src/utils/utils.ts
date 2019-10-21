'use strict';

import { Context } from "../common/context";
import { isNullOrUndefined } from "util";
import { ExtensionContext } from "vscode";
import * as fs from 'fs';
import * as os from 'os';
import * as path from 'path';
import * as vscode from 'vscode';

export class Utils {

    constructor() {
        Utils._context = Context.context;
    }

    private static _context: Context;
    static get context(): Context {
        return this._context;
    }

    static getDirName(uri: vscode.Uri): string | undefined {
        if (isNullOrUndefined(uri)) {
            return undefined;
        }
        else {
            return path.dirname(uri.fsPath);
        }
    }

    static getFileName(uri: vscode.Uri) {
        if (isNullOrUndefined(uri)) {
            return undefined;
        }
        else {
            return path.basename(uri.fsPath);
        }
    }

    /**
     * 获取当前所在工程根目录，有3种使用方法：<br>
     * getProjectPath(uri) uri 表示工程内某个文件的路径<br>
     * getProjectPath(document) document 表示当前被打开的文件document对象<br>
     * getProjectPath() 会自动从 activeTextEditor 拿document对象，如果没有拿到则报错
     * @param {*} document 
     */
    static getProjectPath(document: any) {
        if (!document) {
            document = vscode.window.activeTextEditor ? vscode.window.activeTextEditor.document : null;
        }
        if (!document) {
            this.showError('当前激活的编辑器不是文件或者没有文件被打开！');
            return '';
        }
        const currentFile = (document.uri ? document.uri : document).fsPath;
        let projectPath = null;

        let workspaceFolders = vscode.workspace!.workspaceFolders!.map((item: any) => item.uri.path);
        // 由于存在Multi-root工作区，暂时没有特别好的判断方法，先这样粗暴判断
        // 如果发现只有一个根文件夹，读取其子文件夹作为 workspaceFolders
        if (workspaceFolders.length === 1 && workspaceFolders[0] === vscode.workspace.rootPath) {
            const rootPath = workspaceFolders[0];
            var files = fs.readdirSync(rootPath);
            workspaceFolders = files.filter((name: string) => {
                return !/^\./g.test(name);
            }).map((name: string) => {
                return path.resolve(rootPath, name);
            });
            // vscode.workspace.rootPath会不准确，且已过时
            // return vscode.workspace.rootPath + '/' + this._getProjectName(vscode, document);
        }
        workspaceFolders.forEach((folder: string) => {
            if (currentFile.indexOf(folder) === 0) {
                projectPath = folder;
            }
        });
        if (!projectPath) {
            this.showError('获取工程根路径异常！');
            return '';
        }
        return projectPath;
    }
    /**
     * 获取当前工程名
     */
    static getProjectName(projectPath: string) {
        return path.basename(projectPath);
    }

    /**
     * 将一个单词首字母大写并返回
     * @param {*} word 某个字符串
     */
    static upperFirstLetter(word: string) {
        return (word || '').replace(/^\w/, m => m.toUpperCase());
    }

    /**
     * 将一个单词首字母转小写并返回
     * @param {*} word 某个字符串
     */
    static lowerFirstLeter(word: string) {
        return (word || '').replace(/^\w/, m => m.toLowerCase());
    }

    /**
     * 弹出错误信息
     */
    static showError(info: any) {
        vscode.window.showErrorMessage(info);
    }
    
    /**
     * 弹出提示信息
     */
    static showInfo(info: any) {
        vscode.window.showInformationMessage(info);
    }

    /**
     * 从某个文件里面查找某个字符串，返回第一个匹配处的行与列，未找到返回第一行第一列
     * @param filePath 要查找的文件
     * @param reg 正则对象，最好不要带g，也可以是字符串
     */
    static findStrInFile(filePath: string, reg: any) {
        const content = fs.readFileSync(filePath, 'utf-8');
        reg = typeof reg === 'string' ? new RegExp(reg, 'm') : reg;
        // 没找到直接返回
        if (content.search(reg) < 0) {
            return { row: 0, col: 0 };
        }
        const rows = content.split(os.EOL);
        // 分行查找只为了拿到行
        for (let i = 0; i < rows.length; i++) {
            let col = rows[i].search(reg);
            if (col >= 0) {
                return { row: i, col };
            }
        }
        return { row: 0, col: 0 };
    }

    /**
     * 获取某个字符串在文件里第一次出现位置的范围，
     */
    static getStrRangeInFile(filePath: string, str: any) {
        var pos = this.findStrInFile(filePath, str);
        return new vscode.Range(new vscode.Position(pos.row, pos.col), new vscode.Position(pos.row, pos.col + str.length));
    }

    /**
     * 简单的检测版本大小
     */
    static checkVersion(version1: string, version2: string) {
        let v1Num = parseInt(version1.replace(/\./g, ''));
        let v2Num = parseInt(version2.replace(/\./g, ''));
        return v1Num > v2Num;
    }

    /**
     * 获取某个扩展文件绝对路径
     * @param context 上下文
     * @param relativePath 扩展中某个文件相对于根目录的路径，如 images/test.jpg
     */
    static getExtensionFileAbsolutePath(context: ExtensionContext, relativePath: string) {
        return path.join(context.extensionPath, relativePath);
    }

    /**
     * 获取某个扩展文件相对于webview需要的一种特殊路径格式
     * 形如：vscode-resource:/Users/toonces/projects/vscode-cat-coding/media/cat.gif
     * @param context 上下文
     * @param relativePath 扩展中某个文件相对于根目录的路径，如 images/test.jpg
     */
    static getExtensionFileVscodeResource(context: ExtensionContext, relativePath: string) {
        const diskPath = vscode.Uri.file(path.join(context.extensionPath, relativePath));
        return diskPath.with({ scheme: 'vscode-resource' }).toString();
    }

    /**
     * 在Finder中打开某个文件或者路径
     */
    static openFileInFinder(filePath: string) {
        if (!fs.existsSync(filePath)) {
            this.showError('文件不存在：' + filePath);
        }
        let exec = require('child_process').exec;
        // 如果是目录，直接打开就好
        if (fs.statSync(filePath).isDirectory()) {
            exec(`open ${filePath}`);
        } else {
            // 如果是文件，要分开处理
            const fileName = path.basename(filePath);
            filePath = path.dirname(filePath);
            // 这里有待完善，还不知道如何finder中如何选中文件
            exec(`open ${filePath}`);
        }
    }

    /**
     * 在vscode中打开某个文件
     * @param {*} path 文件绝对路径
     * @param {*} text 可选，如果不为空，则选中第一处匹配的对应文字
     */
    static openFileInVscode(path: string, text: string) {
        let options = undefined;
        if (text) {
            const selection = this.getStrRangeInFile(path, text);
            options = { selection };
        }
        vscode.window.showTextDocument(vscode.Uri.file(path), options);
    }

    /**
     * 使用默认浏览器中打开某个URL
     */
    static openUrlInBrowser(url: string) {
        let exec = require('child_process').exec;
        exec(`open '${url}'`);
    }

    /**
     * 递归遍历清空某个资源的require缓存
     * @param {*} absolutePath
     */
    static clearRequireCache(absolutePath: string) {
        const root = require.cache[absolutePath];
        if (!root) { return; }
        if (root.children) {
            // 如果有子依赖项，先清空依赖项的缓存
            root.children.forEach((item: any) => {
                this.clearRequireCache(item.id);
            });
        }
        delete require.cache[absolutePath];
    }

    /**
     * 动态require，和普通require不同的是，加载之前会先尝试删除缓存
     * @param {*} modulePath 
     */
    static dynamicRequire(modulePath: string) {
        this.clearRequireCache(modulePath);
        return require(modulePath);
    }

    /**
     * 读取properties文件
     * @param {*} filePath 
     */
    static readProperties(filePath: string) {
        const content = fs.readFileSync(filePath, 'utf-8');
        let rows = content.split(os.EOL);
        rows = rows.filter((row: string) => row && row.trim() && !/^#/.test(row));
        const result: any = {};
        rows.forEach((row: string) => {
            let temp = row.split('=');
            result[temp[0].trim()] = temp[1].trim();
        });
        return result;
    }
}