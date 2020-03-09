package com.wxf.textview02;
import java.lang.reflect.Field;

import android.R.drawable;
import android.R.integer;
import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	private  TextView textView;
	private static final String TAG="log";
//	ͨ��ͼƬ��������ȡ��ͼƬ��Ӧ����R�ļ��е�id
	public int getSourceId(String name){
		int id=0;
		try {
			Field field=R.drawable.class.getField(name);
			Log.i(TAG, "name="+name+"----");
//			���ھ�̬������Ҫȡ�ø����Ե�ֵ�����Դ����ֵnull
			id=Integer.parseInt(field.get(null).toString());
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView) findViewById(R.id.textView);
		String html="ͼ��һ<img src='image1'></img>ͼ���<img src='image2'></img>";
		html+="ͼ����<img src='image3'></img>"+"\n"+"<a href='http://www.baidu.com'>ͼ����<img src='image4'></img></a/>"+"\n";
		html+="ͼ����<img src='image5'></img>";
//		���html�����к���img��ǩ������Ҫʹ��Html.fromHtml(source, imageGetter, tagHandler)�÷���
//		���еĵڶ����������÷�������
		CharSequence charSequence=Html.fromHtml(html, new ImageGetter() {
			
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable drawable=null;
//				����ȡ�ø�ͼƬ
				drawable=getResources().getDrawable(getSourceId(source));
				if(source.equals("image3")){
//					Ȼ�������ø�ͼƬҪ��ʾ�Ĵ�С
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth()/2, drawable.getIntrinsicHeight()/2);
				}else if(source.equals("image5")){
					drawable.setBounds(new Rect(0, 0, drawable.getIntrinsicWidth()*2, drawable.getIntrinsicHeight()*2));
				}
				else{
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
				}
				return drawable;
			}
		}, null);
		textView.setText(charSequence);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
