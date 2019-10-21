'use strict';

import { window, Terminal, Disposable } from 'vscode';

let _terminal: Terminal | undefined;
let _terminalCwd: string | undefined;
let _disposable: Disposable | undefined;

function ensureTerminal(cwd: string): Terminal {
    if (_terminal === undefined) {
        _terminal = window.createTerminal("codereview");
        _disposable = window.onDidCloseTerminal((e: Terminal) => {
            if (e.name === "codereview") {
                _terminal = undefined;
                _disposable!.dispose();
                _disposable = undefined;
            }
        });

        _terminalCwd = undefined;
    }

    if (_terminalCwd !== cwd) {
        _terminal.sendText(`cd "${cwd}"`, true);
        _terminalCwd = cwd;
    }

    return _terminal;
}

export function runGitCommandInTerminal(gitCmd: string, fileDir: string, execute: boolean = false) {
    const terminal = ensureTerminal(fileDir);
    //process.stdout.on("data", (data: any) => { console.log(data); });
    terminal.show(false);
    terminal.sendText(gitCmd, execute);
}