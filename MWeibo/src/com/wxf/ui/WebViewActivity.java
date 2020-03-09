package com.wxf.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wxf.domain.Task;
import com.wxf.service.MainService;
import com.wxf.util.JavaScriptInterface;

public class WebViewActivity extends Activity implements IActivity {

	private WebView webView;
	private ProgressDialog progressDialog;

	// private
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		init();
		Task task = new Task(Task.GETTOKEN, null);
		MainService.addTask(task);
		// Intent intent=new Intent(this, MainService.class);
		// startService(intent);
		MainService.addActivity(this);
	}

	public void init() {
		// TODO Auto-generated method stub
		// 加载进度条对话框。
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("正在加载，请等待...");
		progressDialog.show();
		// 获取webView控件
		webView = (WebView) findViewById(R.id.webView);
		WebSettings webSettings = webView.getSettings();
		// 设置js代码可用
		webSettings.setJavaScriptEnabled(true);
		// webView.loadUrl(url);
		webView.addJavascriptInterface(new JavaScriptInterface(), "myWeiBo");
		webView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100) {
					progressDialog.dismiss();
				}
			}

		});

		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				Log.i("webView", url);
				/*Log.i("webView",
						url.equals("http://api.t.sina.com.cn/oauth/authorize")
								+ "");
				if (url.equals("http://api.t.sina.com.cn/oauth/authorize")) {
					// Log.i("webView", "the javascritp execute..............");
					// 通过对应的接口来从网页中获取授权页面的信息
					view.loadUrl("javascript:window.myWeiBo.getPin('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
					// 跳转到下一个页面，获取accessToken然后将其保存到数据库中。
					Intent intent = new Intent(WebViewActivity.this,
							AccessTokenActivity.class);
					startActivity(intent);
				}*/
				super.onPageFinished(view, url);
			}

		});

	}

	public void refresh(Object... objects) {
		// TODO Auto-generated method stub
		load(objects[0].toString(), webView);
	}

	/**
	 * @param url2
	 * @param webView2
	 *            在该线程中进行加载url
	 */
	private void load(final String url2, final WebView webView2) {
		// TODO Auto-generated method stub
		if (url2 != null) {
			new Thread() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					webView2.loadUrl(url2);
				}
			}.start();
		}
	}
}
