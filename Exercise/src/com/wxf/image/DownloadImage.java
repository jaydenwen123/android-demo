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
		
//		������Ϣ��
		final Handler handler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				imageCallback.OnDownLoadFinishCallBack((Bitmap) msg.obj);
			}
		};
//		�����µ��̡߳���������ͼƬ��
		new Thread(){
			public void run() {
//				��ȡbitmap���󡣣�
				Bitmap bitmap=BitmapFactory.decodeStream(HttpUtil.getInputStream(imageUrl));
				Message message=Message.obtain();
				message.obj=bitmap;
				handler.sendMessage(message);
			};
		}.start();
	}
	
//	ͨ���ӿڻص��ķ�ʽ�������ݡ�
	public interface ImageCallback{
		public void OnDownLoadFinishCallBack(Bitmap bitmap);
	}

}
