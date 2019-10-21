'use strict';

const cmdArray = {
    'cdCmd': 'cd /d',
    'gitBlame': 'git blame -L',
    'gitLog': 'git log --pretty=format:hash:%h,author:%cn,email:%ce,date:%ci,subject:%s',
    'svnLog': 'svn log -l 1'
};

export class Commamd {

    constructor() {

    }

    private getCdcmdStr(): string {
        return cmdArray['cdCmd'];
    }

    private getGitBlameStr(): string {
        return cmdArray['gitBlame'];
    }

    private getGitLogStr(): string {
        return cmdArray['gitLog'];
    }

    private getSvnLogStr(): string {
        return cmdArray['svnLog'];
    }

    public getCdCmd(dir: string): string {
        return `${this.getCdcmdStr()} ${dir}`;
    }

    public getGitBlameCmd(sl: number, el: number, fileName: string) {
        return `${this.getGitBlameStr()} ${sl},${el} ${fileName}`;
    }

    public getGitLogCmd(fileName: string) {
        return `${this.getGitLogStr()} ${fileName}`;
    }

    public getSvnLogCmd(fileName: string) {
        return `${this.getSvnLogStr()} ${fileName}`;
    }

    public getCdAndGitBlameCmd(dir: string, sl: number, el: number, fileName: string) {
        let cmdStr = this.getCdCmd(dir);
        let gitBlameStr = this.getGitBlameCmd(sl, el, fileName);
        return `${cmdStr} && ${gitBlameStr}`;
    }

    public getCdAndGitLogCmd(dir: string, fileName: string) {
        let cmdStr = this.getCdCmd(dir);
        let gitStr = this.getGitLogCmd(fileName);
        return `${cmdStr} && ${gitStr}`;
    }
}