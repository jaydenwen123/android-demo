package com.wxf.image;

import com.wxf.http.HttpUtil;

import android.R.interpolator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class DownloadImage {

	private String imageUrl;

	public DownloadImage(String imageUrl) {
		// TODO Auto-generated constructor stub
		this.imageUrl=imageUrl;
	}

	public void download(final ImageCallback imageCallback){
		
//		处理消息。
		final Handler handler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				imageCallback.OnDownLoadFinishCallBack((Bitmap) msg.obj);
			}
		};
//		开启新的线程。用来下载图片。
		new Thread(){
			public void run() {
//				获取bitmap对象。，
				Bitmap bitmap=BitmapFactory.decodeStream(HttpUtil.getInputStream(imageUrl));
				Message message=Message.obtain();
				message.obj=bitmap;
				handler.sendMessage(message);
			};
		}.start();
	}
	
//	通过接口回调的方式传递数据。
	public interface ImageCallback{
		public void OnDownLoadFinishCallBack(Bitmap bitmap);
	}

}
