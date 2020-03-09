package com.wxf.datepinker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;

public class MainActivity extends Activity implements OnDateChangedListener{

	private DatePicker datePicker;
	private TimePicker timePicker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datePicker=(DatePicker) findViewById(R.id.datPinker);
		timePicker=(TimePicker) findViewById(R.id.timePicker);
		
	}
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	}
}
