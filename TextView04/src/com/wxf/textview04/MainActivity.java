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
		// �������ֹ�������������Ҫ��
		// 1.���ֵĳ��ȴ���TExtView�Ŀ��
		// 2.���ֱ��뵥����ʾ
		// 3.������������Ϊ�����
		String html = "������10��10�յ� ����籨����������ѧ���޲���J��<a href='http://www.baidu.com'>�շ��ά��</a>(Robert J. Lefkowitz)�벼������K�����ȶ���(Brian K. Kobilka)����G����ż�����巽����о����2012��ŵ������ѧ����";
		textView.setText(Html.fromHtml(html));
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
