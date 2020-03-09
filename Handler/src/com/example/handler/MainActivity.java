package com.example.handler;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private Button start;
	private Button end;
	private ProgressBar bar;
	private  Button toNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button) findViewById(R.id.start);
        end=(Button) findViewById(R.id.end);
        bar=(ProgressBar) findViewById(R.id.bar);
        start.setOnClickListener(new startButtonListener());
        end.setOnClickListener(new endButtonListener());
        toNext=(Button) findViewById(R.id.toNext);
        toNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});
    }
//    设置开始按钮的监听器用来显示进度条和将鲜橙thread放入到handler的队列中
//    队列满足先进先出的原则于是很快线程thread执行run函数
    private  class startButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bar.setVisibility(View.VISIBLE);
			handler.post(thread);
		}
    	
    }
//    设置end按钮的监听器，当按钮点击后，然后将thread从handlerd的队列中，移除
    private  class endButtonListener implements OnClickListener{
    	
    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		handler.removeCallbacks(thread);
    	}
    	
    }
    Handler handler=new Handler();
//    通过runnable接口来创建线程
    Runnable thread=new Runnable() {
		int i=0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("run thread");
			i=i+10;
			bar.setProgress(i);
			if(i>100){
				handler.removeCallbacks(thread);
				i=0;
			}else {
				handler.postDelayed(thread, 3000);
			}
		}
	};
}
