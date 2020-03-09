package com.wxf.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private  Button button;
	private  EditText editText;
	private  TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button=(Button) findViewById(R.id.button);
		editText=(EditText) findViewById(R.id.editText);
		textView=(TextView) findViewById(R.id.textView);
		button.setOnClickListener(new buttonListener());
	}
	private  class  buttonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String text=editText.getText().toString();
			textView.setText("您输入的值为："+text);
//			发短信的小程序	
			//textView.setTextColor(2);
			Uri uri=Uri.parse("smsto://0800000132");
			Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
			intent.putExtra("sma_body", "heool");
			startActivity(intent);
		}

		
	}
}
