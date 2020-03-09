package com.wxf.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText[] editTexts;
	private TextView[] textViews;
	private Button button;
	private Button button2;
	public MainActivity() {
		// TODO Auto-generated constructor stub
		editTexts=new EditText[3];
		textViews=new TextView[3];
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editTexts[0]=(EditText) findViewById(R.id.editText1);
        editTexts[1]=(EditText) findViewById(R.id.editText2);
        editTexts[2]=(EditText) findViewById(R.id.editText3);
        textViews[0]=(TextView) findViewById(R.id.textView1);
        textViews[1]=(TextView) findViewById(R.id.textView2);
        textViews[2]=(TextView) findViewById(R.id.textView3);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonListener());
        button2=(Button) findViewById(R.id.nextActivity);
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, TableLayout.class);
				startActivity(intent);
			}
		});
    }
    private class  ButtonListener implements OnClickListener{

		@SuppressLint("ShowToast") @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			for(int i=0;i<textViews.length;i++){
				textViews[i].setText(editTexts[i].getText());
			}
			Toast.makeText(MainActivity.this, "给textView赋值完成", Toast.LENGTH_SHORT);
		}
    	
    }
}
