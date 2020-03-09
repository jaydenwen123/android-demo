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
		// 构建一个1-9之间的随机数
		Integer number = new Random().nextInt(9) + 1;
		/**
		 * 根据个人理解，要在TextView和EditText上显示图片和表情等信息
		 * 主要的思路是通过SpannableString来将其转换为charSequence序列。
		 * 再将其追加到控件中。
		 * 
		 */
		SpannableString spannableString = new SpannableString("face");
//		在Android中所有的图片都是以位图的形式显示。因此必须要构建一个位图。
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
	 *         首先根据图片的名称利用Java反射机制来取得对应额的图片id
	 */
	public int getResourceId(String name) {
		int id = 0;
		Field field = null;
		try {
//			通过反射取得图片对应的属性。
			field = R.drawable.class.getDeclaredField(name);
//			下面之所以传值为空null因为：
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
