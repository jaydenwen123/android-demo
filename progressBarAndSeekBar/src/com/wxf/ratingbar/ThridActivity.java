package com.wxf.ratingbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.wxf.seekbar.R;

public class ThridActivity extends Activity {

	private RatingBar ratingBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid);
		ratingBar=(RatingBar) findViewById(R.id.ratingbar);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				Toast.makeText(ThridActivity.this, "rating+"+rating, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
