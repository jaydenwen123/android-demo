package com.wxf.imageview;

import java.io.IOException;
import java.io.InputStream;

import com.wxf.http.HttpUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private ImageView imageView;
	private ImageView imageView1;
	private Button button;
	private ImageView imageView3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView3 = (ImageView) findViewById(R.id.imgeView3);
		imageView = (ImageView) findViewById(R.id.imgeView2);
		imageView1 = (ImageView) findViewById(R.id.imgeView1);
		imageView.setLayoutParams(new LinearLayout.LayoutParams(200, 100));
		imageView1.setLayoutParams(new LinearLayout.LayoutParams(400, 200));
		setTitle("height:" + imageView.getLayoutParams().height + "width:"
				+ imageView.getLayoutParams().width);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new ButtonListener());
	}

	class ButtonListener implements OnClickListener{
		Bitmap bitmap=null;
		
		public Bitmap getBitmap() {
			return bitmap;
		}

		public void setBitmap(Bitmap bitmap) {
			this.bitmap = bitmap;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			new Thread() {
				public void run() {
					/*InputStream inputStream=HttpUtil.getInputStream(HttpUtil.URL);
					 * 通过输入流来下载图片
					bitmap=BitmapFactory.decodeStream(inputStream);*/
					try {
						InputStream inputStream=HttpUtil.getInputStream(HttpUtil.URL);
						byte[] buffer=HttpUtil.getByteArrayByInputStream(inputStream);
//					通过一个字节数组来下载图片
						bitmap=BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
			System.out.println(bitmap);
			imageView3.setImageBitmap(bitmap);
			imageView3.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
		}
	}
}
