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
//	通过图片的名字来取得图片对应的在R文件中的id
	public int getSourceId(String name){
		int id=0;
		try {
			Field field=R.drawable.class.getField(name);
			Log.i(TAG, "name="+name+"----");
//			对于静态方法。要取得该属性的值。可以传入空值null
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
		String html="图像一<img src='image1'></img>图像二<img src='image2'></img>";
		html+="图像三<img src='image3'></img>"+"\n"+"<a href='http://www.baidu.com'>图像四<img src='image4'></img></a/>"+"\n";
		html+="图像五<img src='image5'></img>";
//		如果html代码中含有img标签。则需要使用Html.fromHtml(source, imageGetter, tagHandler)该方法
//		其中的第二个参数设置方法如下
		CharSequence charSequence=Html.fromHtml(html, new ImageGetter() {
			
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable drawable=null;
//				首先取得该图片
				drawable=getResources().getDrawable(getSourceId(source));
				if(source.equals("image3")){
//					然后再设置该图片要显示的大小
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
