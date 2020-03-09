package com.wxf.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Administrator
 *
 *
 * 测试activity中通过静态静态变量来传值
 */
public class TransData1 extends Activity {

	private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_data1);
        button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TransData1.this, TransData11.class);
				//对静态变量进行赋值。				
				TransData11.name="wei";
				TransData11.age=21;
//				开始转发意图
				startActivity(intent);
			}
		});
    }
}
