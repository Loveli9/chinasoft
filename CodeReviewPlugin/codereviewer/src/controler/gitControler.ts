'use strict';

import { window, TextDocument, Selection, Uri } from 'vscode';
import { isNullOrUndefined } from 'util';
import { Log } from '../log/log';
import { Utils } from '../utils/utils';
import { SelectionUtils } from '../utils/selectionUtils';
import { Commamd } from '../command/command';
import { Process } from '../process/process';
import { LogInfo } from '../model/logInfo';
import * as path from 'path';
import { WebViewSingleton } from '../singleton/webviewSingleton';
import { FileInfo } from '../model/fileInfo';

export class GitControler {
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
            Log.warn("document or selection is NullOrUndefined,function exit!", true);
            return;
        }
        let fileDir = Utils.getDirName(uri);
        let fileName = Utils.getFileName(uri);


        if (isNullOrUndefined(fileDir) || isNullOrUndefined(fileName)) {
            Log.warn("fileDir or fileName is NullOrUndefined,function exit!", true);
            return;
        }

        let startLine = SelectionUtils.getStartLine(selection);
        let endLine = SelectionUtils.getEndLine(selection);
        let selectionText = document.getText(selection);
        Log.log("this file name: " + fileName);
        Log.log("this choosen file start at line: " + startLine);
        Log.log("this choosen file end at line: " + endLine);
        Log.log("this choosen text block is:\n " + selectionText);

        let fileInfo = new FileInfo(fileName, fileDir, selectionText, startLine, endLine);
        let command = new Commamd();
        //let gitCmd = command.getCdAndGitBlameCmd(fileDir, startLine, endLine, fileName);
        let gitCmd = command.getCdAndGitLogCmd(fileDir, fileName);

        let result = new Array<LogInfo>();
        Process.excute(gitCmd, function (data: string) {
            let dataTemp = data.split('\n');
            if (isNullOrUndefined(dataTemp)) {
                Log.warn('结果为空', true);
                return;
            } else {
                dataTemp.forEach(data => {
                    let info = new LogInfo();
                    info.initialFromString(data);
                    result.push(info);
                });
                WebViewSingleton.getInstance().postMessage('gitLog', fileInfo, result);
                Log.logObj(result);
            }
        });
    }
}