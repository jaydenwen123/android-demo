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
//		ͨ��id��ȡ�ö�Ӧ�Ŀؼ�
		textView1=(TextView) findViewById(R.id.textView1);
		textView2=(TextView) findViewById(R.id.textView2);
//		��html��ʽдΪ�ַ�����
		String html1="<a href='http://www.baidu.com'>ȥ���ٶ�</a>";
		html1+="\n <font color='yellow'>���</font>";
		String html2="<font><big>I love Android</big></font>";
		html2+="\n <font><i>hello world</i></font>";
		html2+="my phone:1536788829\n my email: 2282184747@qq.com\n my mobile:0731-234234234";
//		Ȼ����ͨ��Html.fromHtml(html1)������ת����
		CharSequence text1=Html.fromHtml(html1);
		CharSequence text2=Html.fromHtml(html2);
//		�ڶ��ı��ؼ����и�ֵ��
		textView1.setText(text1);
		textView2.setText(text2);
//		��Ҫ�Ǹ�txetview���������ӵ����ԣ����ֱ���绰����ַ��������ӣ�ʹ��ʱֱ�ӵ���Ϳ��Դ�绰������ȥ��ַ��
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
}
