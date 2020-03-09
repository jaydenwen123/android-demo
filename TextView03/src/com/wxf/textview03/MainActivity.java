package com.wxf.textview03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView1, textView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		String text1 = "显示activity1";
		String text2 = "显示activity2";
		textView1.setText(text1);
		textView1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView textView=(TextView) v;
				System.out.println(textView.getText()+"-------->");
				
			}
		});
//		// 主要是用来拆分字符串
		SpannableString spannableString1 = new SpannableString(text1);
		SpannableString spannableString2 = new SpannableString(text2);
//		通过
		spannableString1.setSpan(new ClickableSpan() {

			@Override
			public void onClick(View widget) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		}, 0, text1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		spannableString2.setSpan(new ClickableSpan() {

			@Override
			public void onClick(View widget) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ThirdActivity.class);
				startActivity(intent);

			}
		}, 0, text2.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		textView1.setText(spannableString1);
		textView2.setText(spannableString2);
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
		System.out.println(textView2.getText()+"-------->");
		
	}
}
