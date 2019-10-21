'use strict';

import { window, TextDocument, Selection, Uri } from 'vscode';
import { isNullOrUndefined } from 'util';
import { Log } from '../log/log';
import { Utils } from '../utils/utils';
import { SelectionUtils } from '../utils/selectionUtils';
import { Commamd } from '../command/command';

export class SvnControler {
    constructor() {

    }

    public init() {

    }

    public run(uri: Uri) {
        let document = window.activeTextEditor!.document;
        let selection = window.activeTextEditor!.selection;

        if (isNullOrUndefined(document) || isNullOrUndefined(selection)) {
            Log.warn("document or selection is NullOrUndefined,function exit!");
            return;
        }

        this.cmdFunc(document, selection, uri);
    }

    private async cmdFunc(document: TextDocument, selection: Selection, uri: Uri) {
        if (isNullOrUndefined(document) || isNullOrUndefined(selection)) {
            Log.warn("document or selection is NullOrUndefined,function exit!");
            return;
        }
        let fullPath = uri.fsPath;
        let fileDir = Utils.getDirName(uri);
        let fileName = Utils.getFileName(uri);
        if (isNullOrUndefined(fileDir) || isNullOrUndefined(fileName)) {
            Log.warn("fileDir or fileName is NullOrUndefined,function exit!");
            return;
        }

        let startLine = SelectionUtils.getStartLine(selection);
        let endLine = SelectionUtils.getEndLine(selection);
        let selectionText = document.getText(selection);
        Log.log("this file name: " + fileName);
        Log.log("this choosen file start at line: " + startLine);
        Log.log("this choosen file end at line: " + endLine);
        Log.log("this choosen text block is:\n " + selectionText);

        let command = new Commamd();
        let svnCmd = command.getSvnLogCmd(fileName);
        let resultMap = new Map<string, string>();

        this.my_exec(svnCmd, function (result: string) {
            let data = result.toString().split('\r\n')[1].split('|');
            resultMap.set("version", data[0]!.trim());
            resultMap.set("author", data[1]!.trim());
            resultMap.set("comitetime", data[2].trim().split(" ")[0] + " " + data[2].trim().split(" ")[1]);
            console.log(data);
        });

        console.log(resultMap);
    }

    private my_exec(command: string, callback: Function) {
        let exec = require('child_process').exec;
        let proc = exec(command);

        let list: string[] = [];
        proc.stdout.setEncoding('utf8');

        proc.stderr.on('err', function (stderr: any) {
            console.log("程序异常" + stderr);
        });

        proc.stdout.on('data', function (chunk: string) {
            list.push(chunk);
        });

        proc.stdout.on('end', function () {
            callback(list.join());
        });
    }
}