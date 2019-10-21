'use strict';

export class Log {
    static log(msg: string, output = false) {
        if (output) {
            console.log(msg);
        }
    }

    static logObj(msg: object, output = false) {
        if (output) {
            console.log(msg);
        }
    }

    static warn(msg: string, output = false) {
        if (output) {
            console.warn("Something is warnning: " + msg);
        }
    }

    static error(msg: string, output = false) {
        if (output) {
            console.error("Something is error: " + msg);
        }
    }

}
