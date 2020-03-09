package com.wxf.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TableLayout extends Activity {

	private Button button1;
	private Button button2;
	private EditText editText1;
	private EditText editText2;
	private TextView textView1;
	private TextView textView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablelayout);
		button1=(Button) findViewById(R.id.showText);
		button2=(Button) findViewById(R.id.toNext);
		editText1=(EditText) findViewById(R.id.edit1);
		editText2=(EditText) findViewById(R.id.edit2);
		textView1=(TextView) findViewById(R.id.text1);
		textView2=(TextView) findViewById(R.id.text2);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView1.setText(editText1.getText());
				textView2.setText(editText2.getText());
				Toast.makeText(TableLayout.this, "¸³Öµ³É¹¦", Toast.LENGTH_SHORT).show();
			}
			
			
		});
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(TableLayout.this, RelativeLayout.class);
				startActivity(intent);
			}
		});
	}
}
