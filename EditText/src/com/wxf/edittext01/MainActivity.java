package com.wxf.edittext01;

import java.lang.reflect.Field;
import java.util.Random;

import com.wxf.edittext02.SecondActivity;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private EditText editText;
	private Button button;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.editText);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		button2=(Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});
		
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		// ����һ��1-9֮��������
		Integer number = new Random().nextInt(9) + 1;
		/**
		 * ���ݸ�����⣬Ҫ��TextView��EditText����ʾͼƬ�ͱ������Ϣ
		 * ��Ҫ��˼·��ͨ��SpannableString������ת��ΪcharSequence���С�
		 * �ٽ���׷�ӵ��ؼ��С�
		 * 
		 */
		SpannableString spannableString = new SpannableString("face");
//		��Android�����е�ͼƬ������λͼ����ʽ��ʾ����˱���Ҫ����һ��λͼ��
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				getResourceId("face" + number));
		ImageSpan imageSpan = new ImageSpan(bitmap);
		spannableString.setSpan(imageSpan, 0, 4,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		editText.append(spannableString);
	}

	/**
	 * @param name
	 * @return
	 * 
	 *         ���ȸ���ͼƬ����������Java���������ȡ�ö�Ӧ���ͼƬid
	 */
	public int getResourceId(String name) {
		int id = 0;
		Field field = null;
		try {
//			ͨ������ȡ��ͼƬ��Ӧ�����ԡ�
			field = R.drawable.class.getDeclaredField(name);
//			����֮���Դ�ֵΪ��null��Ϊ��
//			If the underlying field is a static field, the {@code obj} argument
//		    is ignored; it may be null.
			id = Integer.parseInt(field.get(null).toString());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}
