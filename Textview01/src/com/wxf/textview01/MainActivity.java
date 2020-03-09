package com.wxf.textview01;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView1,textView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		通过id来取得对应的控件
		textView1=(TextView) findViewById(R.id.textView1);
		textView2=(TextView) findViewById(R.id.textView2);
//		将html格式写为字符串。
		String html1="<a href='http://www.baidu.com'>去往百度</a>";
		html1+="\n <font color='yellow'>你好</font>";
		String html2="<font><big>I love Android</big></font>";
		html2+="\n <font><i>hello world</i></font>";
		html2+="my phone:1536788829\n my email: 2282184747@qq.com\n my mobile:0731-234234234";
//		然后日通过Html.fromHtml(html1)方法来转换。
		CharSequence text1=Html.fromHtml(html1);
		CharSequence text2=Html.fromHtml(html2);
//		在对文本控件进行赋值。
		textView1.setText(text1);
		textView2.setText(text2);
//		主要是给txetview加入了链接的属性，它分别给电话和网址添加了链接，使用时直接点击就可以打电话。或者去网址。
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
}
