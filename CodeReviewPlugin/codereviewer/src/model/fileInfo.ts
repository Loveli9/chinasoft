'use strict';

export class FileInfo {
    private _fileName: string;
    private _filePath: string;
    private _codeBlock: string;
    private _sLineNumber: number;
    private _eLineNumber: number;

    constructor(fileName: string, filePath: string, codeBlock: string, sln: number, eln: number) {
        this._fileName = fileName;
        this._filePath = filePath;
        this._codeBlock = codeBlock;
        this._sLineNumber = sln;
        this._eLineNumber = eln;
    }

    public get fileName(){
        return this._fileName;
    }

    public get filePath(){
        return this._filePath;
    }

    public get codeBlock(){
        return this._codeBlock;
    }

    public get sln(){
        return this._sLineNumber;
    }

    public get eln(){
        return this._eLineNumber;
    }
}