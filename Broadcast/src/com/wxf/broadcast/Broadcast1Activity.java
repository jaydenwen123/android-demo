package com.wxf.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Administrator
 *
 *��Android�Ĺ㲥�У�������ʹ�÷�����һ�½��н���
 *1.
 *(1)����ͨ��activity��ͨ��Intent�ķ���setBroadCast();�������͹㲥
 *(2)Ȼ��дһ���������̳�BroadcastReceiver���࣬����ʵ�ָø����onreceiver()����
 *(3)��AndroidManifest.xml������(2)�еĽ�������
 *	<receiver 
  *          android:name=".Receiver">
  *         	 <intent-filter >
  *          		<action android:name="android.intent.action.EDIT"/>			
  *          	</intent-filter>        
  *           </receiver>	
  *           	    
 * 2.     
 *(1)ֱ���ڸ�activity�����ã�����Ҫ��AndroidManifest.xml����ע�ᣬ��Ϊ��AndroidManifest.xml��ע���
 *�����������۸�activity�Ƿ�ɼ���������ܶ�Ӧ�Ĺ㲥��
*
 */
public class Broadcast1Activity extends Activity {
	private Button button;
	private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast1);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setAction(Intent.ACTION_EDIT);
				Broadcast1Activity.this.sendBroadcast(intent);
			}
		});
        button2=(Button) findViewById(R.id.toNext);
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(Broadcast1Activity.this, Broadcast2Activity.class);
				startActivity(intent);
			}
		});
    }
}
