'use strict';

import { Selection } from 'vscode';

export class SelectionUtils {

    static getStartLine(selection: Selection): number {
        return selection.start.line + 1;
    }

    static getEndLine(selection: Selection): number {
        return selection.end.line + 1;
    }
}