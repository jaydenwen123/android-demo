package com.wxf.intent;

import com.wxf.app.MyApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Administrator
 *
 *
 * ����activity��ͨ��ȫ�ֱ�������ֵ
 * ȫ�ֱ�������ʹ��һ����ͨ�������̳�APPlication��
 * Ȼ�����嵥�ļ��н���ע�ᡣ�Ժ����е�activity������ʹ�ø�ֵ
 */
public class TransData2 extends Activity {

	private Button button3;
	private MyApp app;
	private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_data2);
        textView=(TextView) findViewById(R.id.test);
        button3=(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				app=(MyApp) getApplication();
				textView.setText(app.getName());
				try {
					app.setName("xiaofeifei");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent=new Intent(TransData2.this, TransData22.class);
				startActivity(intent);
				
			}
		});
    }
}
