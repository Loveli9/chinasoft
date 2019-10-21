'use strict';

import { isNullOrUndefined } from "util";
import { Log } from "../log/log";

export class LogInfo {
    private _version: string | undefined;
    private _author: string | undefined;
    private _email: string | undefined;
    private _commitTime: string | undefined;
    private _subject: string | undefined;

    constructor() {

    }

    //各字段初始化
    public commonInitial(version: string, author: string, email: string, commitTime: string, subject: string) {
        this._version = version;
        this._author = author;
        this._email = email;
        this._commitTime = commitTime;
        this._subject = subject;
    }

    //特殊的字符串初始化 git日志固定格式:hash:%h,author:%cn,email:%ce,date:%ci,subject:%s
    public initialFromString(data: string) {
        let strArray = data.split(',');
        if (isNullOrUndefined(strArray)) {
            Log.log("结果字符串异常，解析退出");
            return;
        }
        this._version = strArray[0]!.split(':')![1];
        this._author = strArray[1]!.split(':')![1];
        this._email = strArray[2]!.split(':')![1];
        this._commitTime = strArray[3]!.split(':')![1];
        this._subject = strArray[4]!.split(':')![1];
    }

    public getVersion() {
        return this._version;
    }

    public getAuthor() {
        return this._author;
    }

    public getEmail() {
        return this._email;
    }

    public getCommitTime() {
        return this._commitTime;
    }

    public getSubject() {
        return this._subject;
    }
}