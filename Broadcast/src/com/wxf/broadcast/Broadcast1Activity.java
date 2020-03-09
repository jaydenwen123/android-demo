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
 *在Android的广播中，有两种使用方法，一下进行介绍
 *1.
 *(1)首先通过activity中通过Intent的方法setBroadCast();方法发送广播
 *(2)然后写一个子类来继承BroadcastReceiver父类，并且实现该父类的onreceiver()方法
 *(3)在AndroidManifest.xml中配置(2)中的接收器类
 *	<receiver 
  *          android:name=".Receiver">
  *         	 <intent-filter >
  *          		<action android:name="android.intent.action.EDIT"/>			
  *          	</intent-filter>        
  *           </receiver>	
  *           	    
 * 2.     
 *(1)直接在该activity中配置，不需要再AndroidManifest.xml进行注册，因为在AndroidManifest.xml中注册的
 *接收器。无论该activity是否可见，都会接受对应的广播。
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
