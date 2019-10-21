'use strict';

import { ExtensionContext } from "vscode";

export class Context {

    //全局上下文context
    private static _context: ExtensionContext;
    static initial(context: ExtensionContext) {
        this._context = context;
    }

    static get context() {
        return this._context;
    }

    //全局基础数据
    private static _basicInfo: object;
    static setBasicInfo(info: object) {
        this._basicInfo = info;
    }

    static get basicInfo() {
        return this._basicInfo;
    }
}
