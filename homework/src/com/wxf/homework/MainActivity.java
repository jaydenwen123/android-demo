package com.wxf.homework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button button;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		Bitmap bitmap = BitmapUtil.decodeBitmap(getResources(), R.drawable.xiong,
				50, 50);
		imageView1.setImageBitmap(bitmap);
//		BitmapUtil.saveBmp(bitmap, "50px-0.jpg",0);
//		BitmapUtil.saveBmp(bitmap, "50px-50.jpg",50);
//		BitmapUtil.saveBmp(bitmap, "50px-100.jpg",100);

		bitmap = BitmapUtil.decodeBitmap(getResources(), R.drawable.xiong, 100,
				100);
		imageView2.setImageBitmap(bitmap);
//		BitmapUtil.saveBmp(bitmap, "100px-0.jpg",0);
//		BitmapUtil.saveBmp(bitmap, "100px-50.jpg",50);
		BitmapUtil.saveBmp(bitmap, "100px-100.jpg",100);

		bitmap = BitmapUtil.decodeBitmap(getResources(), R.drawable.xiong, 200,
				200);
		imageView3.setImageBitmap(bitmap);
//	/	BitmapUtil.saveBmp(bitmap, "200px-0.jpg",0);
		BitmapUtil.saveBmp(bitmap, "200px-50.jpg",50);
//		BitmapUtil.saveBmp(bitmap, "200px-100.jpg",100);

		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}
}
