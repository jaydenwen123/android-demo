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
 * ����activity��ͨ����̬��̬��������ֵ
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
				//�Ծ�̬�������и�ֵ��				
				TransData11.name="wei";
				TransData11.age=21;
//				��ʼת����ͼ
				startActivity(intent);
			}
		});
    }
}
