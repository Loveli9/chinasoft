// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from 'vscode';
import { Context } from './common/context';
import { GitControler } from './controler/gitControler';
import { Uri } from 'vscode';
import { Utils } from './utils/utils';
import { Log } from './log/log';
import { isNullOrUndefined } from 'util';
import { WebViewSingleton } from './singleton/webviewSingleton';

let webViewInstance: WebViewSingleton;

// this method is called when your extension is activated
// your extension is activated the very first time the command is executed
export function activate(context: vscode.ExtensionContext) {

	// Use the console to output diagnostic information (console.log) and errors (console.error)
	// This line of code will only be executed once when your extension is activated
	Log.log('Congratulations, your extension "CodeReviewer" is now active!');
	Context.initial(context);
	webViewInstance = WebViewSingleton.getInstance();

	// The command has been defined in the package.json file
	// Now provide the implementation of the command with registerCommand
	// The commandId parameter must match the command field in package.json

	// 注册命令，可以给命令配置快捷键或者右键菜单
	// 回调函数参数uri：当通过资源管理器右键执行命令时会自动把所选资源URI带过来，当通过编辑器中菜单执行命令时，会将当前打开的文档URI传过来
	let disposable = vscode.commands.registerCommand('extension.codereview', (uri: Uri) => {
		// The code you place here will be executed every time your command is executed
		let projectPath = uri.path;
		if (isNullOrUndefined(projectPath)) {
			Utils.showInfo(`检视文件未激活，请打开检视文件`);
		}
		let webViewStatus = webViewInstance.showWebView();

		if (webViewStatus) {
			let controler = new GitControler();
			controler.run(uri);
		}
		else{
			// Display a message box to the user
			Utils.showError("创建WebView出现错误，请重新尝试");
		}
	});

	context.subscriptions.push(disposable);
}

// this method is called when your extension is deactivated
export function deactivate() {
	if (!isNullOrUndefined(webViewInstance)) {
		webViewInstance.dispose();
	}
}