package com.wxf.seekbar;

import com.wxf.progressbar.SecondActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private SeekBar seekBar;
	private TextView textView;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBar=(SeekBar) findViewById(R.id.seekbar);
		textView=(TextView) findViewById(R.id.textView);
		button=(Button) findViewById(R.id.toNext);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			/* (non-Javadoc)
			 * @see android.widget.SeekBar.OnSeekBarChangeListener#onStopTrackingTouch(android.widget.SeekBar)
			 * 当手指按下的时候出发
			 */
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				createToast(MainActivity.this, "end touch :"+seekBar.getProgress());
			}
			
			/* (non-Javadoc)
			 * @see android.widget.SeekBar.OnSeekBarChangeListener#onProgressChanged(android.widget.SeekBar, int, boolean)
			 * 当拖动结束的时候触发
			 */
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				createToast(MainActivity.this, "start touch :"+seekBar.getProgress());
			}
			
			/* (non-Javadoc)
			 * @see android.widget.SeekBar.OnSeekBarChangeListener#onProgressChanged(android.widget.SeekBar, int, boolean)
			 * 当拖动的时候出发
			 */
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textView.setText("progress:"+progress+",fromUser:"+fromUser);
			}
	});
	}
	
	public void  createToast(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
