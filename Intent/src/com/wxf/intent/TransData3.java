package com.wxf.intent;

import com.wxf.app.MyApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Administrator
 *
 *测试从剪贴板传值
 *不过已经过时了。
 *
 */
public class TransData3 extends Activity {

	private Button button5;
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_data3);
        @SuppressWarnings("deprecation")
//        关键代码:以下两行
		ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText("ciaoming");
        button5=(Button) findViewById(R.id.button5);
        button5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TransData3.this, TransData33.class);
				startActivity(intent);
			}
		});
    }
}
