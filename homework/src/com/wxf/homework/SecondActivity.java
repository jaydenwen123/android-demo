package com.wxf.homework;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends Activity implements OnClickListener {

	private Button button1, button2, button3, button4, button5, button6,
			button7;
	private ImageView imageView;
	public static double FLAG = 0.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.image);
		imageView.setImageBitmap(BitmapUtil.decodeBitmap(getResources(),
				R.drawable.wen, 300, 300));
	}

	public void onClick(View v) {
		Bitmap bitmap = BitmapUtil.decodeBitmap(getResources(), R.drawable.wen,
				300, 300);
		switch (v.getId()) {
		case R.id.button1:
			bitmap = BitmapUtil.gray(bitmap, BitmapUtil.GRAY1);
			BitmapUtil.saveBmp(bitmap, "gray1-0.jpg", 0);
			BitmapUtil.saveBmp(bitmap, "gray1-50.jpg", 50);
			BitmapUtil.saveBmp(bitmap, "gray1-100.jpg", 100);
			imageView.setImageBitmap(bitmap);
			break;
		case R.id.button2:
			bitmap = BitmapUtil.gray(bitmap, BitmapUtil.GRAY2);
			BitmapUtil.saveBmp(bitmap, "gray2-0.jpg", 0);
			BitmapUtil.saveBmp(bitmap, "gray2-50.jpg", 50);
			BitmapUtil.saveBmp(bitmap, "gray2-100.jpg", 100);
			imageView.setImageBitmap(bitmap);
			break;
		case R.id.button3:
			bitmap = BitmapUtil.gray(bitmap, BitmapUtil.GRAY3);
			BitmapUtil.saveBmp(bitmap, "gray3-0.jpg", 0);
			BitmapUtil.saveBmp(bitmap, "gray3-50.jpg", 50);
			BitmapUtil.saveBmp(bitmap, "gray3-100.jpg", 100);
			imageView.setImageBitmap(bitmap);
			break;
		case R.id.button4:
			bitmap = BitmapUtil.light(bitmap, FLAG--);
			BitmapUtil.saveBmp(bitmap, "light" + FLAG + "-0.jpg", 0);
			BitmapUtil.saveBmp(bitmap, "light" + FLAG + "-50.jpg", 50);
			BitmapUtil.saveBmp(bitmap, "light" + FLAG + "-100.jpg", 100);
			imageView.setImageBitmap(bitmap);
			break;
		case R.id.button5:
			bitmap = BitmapUtil.flip(bitmap);
			BitmapUtil.saveBmp(bitmap, "flip-0.jpg", 0);
			BitmapUtil.saveBmp(bitmap, "flip-50.jpg", 50);
			BitmapUtil.saveBmp(bitmap, "flip-100.jpg", 100);
			imageView.setImageBitmap(bitmap);
			break;
		case R.id.button6:
			bitmap = BitmapUtil.light(bitmap, FLAG++);
			// 保存文件到手机中
			BitmapUtil.saveBmp(bitmap, "light" + FLAG + "-0.jpg", 0);
			BitmapUtil.saveBmp(bitmap, "light" + FLAG + "-50.jpg");
			BitmapUtil.saveBmp(bitmap, "light" + FLAG + "-100.jpg", 100);
			imageView.setImageBitmap(bitmap);
			break;
		case R.id.button7:
			imageView.setImageBitmap(bitmap);
			FLAG = 0.0;
			break;

		}
	}
}
