'use strict';

import { isNullOrUndefined } from 'util';
import { Log } from '../log/log';

interface Callback {
    (data: string): void;
}

export class Process {
    private static _exec: any;

    private static initial() {
        if (isNullOrUndefined(this._exec)) {
            this._exec = require('child_process').exec;
        }
        return this._exec;
    }

    public static excute(cmd: string, callBack: Callback) {
        let exec = this.initial();
        try {
            exec(cmd, function (err: any, stdout: any, stderr: any) {
                if (err) {
                    Log.error(`执行命令:"${cmd}" 出现错误，错误信息:${stderr}`);
                } else {
                    Log.log(`执行命令:"${cmd} 成功，开始解析返回数据`);
                    callBack(stdout);
                }
            });
        }
        catch (ex) {
            Log.error(`执行命令出现未知异常，异常错误信息：+${ex}`);
        }
    }
}