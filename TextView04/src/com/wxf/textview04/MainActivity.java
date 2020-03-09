package com.wxf.textview04;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textview);
		// 设置文字滚动。，有三个要点
		// 1.文字的长度大于TExtView的宽度
		// 2.文字必须单行显示
		// 3.必须设置属性为跑马灯
		String html = "中新网10月10日电 据外电报道，美国科学家罗伯特J。<a href='http://www.baidu.com'>勒夫科维兹</a>(Robert J. Lefkowitz)与布莱恩·K·卡比尔卡(Brian K. Kobilka)因在G蛋白偶联受体方面的研究获得2012年诺贝尔化学奖。";
		textView.setText(Html.fromHtml(html));
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
