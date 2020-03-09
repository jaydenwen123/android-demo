package com.example.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = new Toast(MainActivity.this);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 30, 0);
				// toast.setText("�Զ����toast");
				/*
				 * �������һ�仰�ȼۡ� LayoutInflater inflater=getLayoutInflater(); View
				 * layout=inflater.inflate(resource, root);
				 */
				View view = getLayoutInflater().inflate(R.layout.toast_layout,
						null);
				TextView textView = (TextView) view.findViewById(R.id.text);
				textView.setText("�Զ����toast");
				toast.setView(view);
				toast.show();
			}
		});

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ����һ��֪ͨ�����builder
				NotificationCompat.Builder builder = new Builder(
						MainActivity.this);
				// ������ʾʱ���ͼ��
				builder.setSmallIcon(R.drawable.ic_launcher);
				// ������ʾ������
				builder.setContentText("hello world");
				// ������ʾʱ��ı���
				builder.setContentTitle("waring");
				// ������ͼ��������ת��activity��
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity.class);

				PendingIntent pendingIntent = PendingIntent.getActivity(
						MainActivity.this, 0, intent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				builder.setContentIntent(pendingIntent);
				// ��ȡ֪ͨ�Ĺ�������
				NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

				manager.notify(1, builder.build());
			}
		});

	}
}
