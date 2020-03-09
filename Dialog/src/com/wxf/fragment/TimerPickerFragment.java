package com.wxf.fragment;

import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * @author Administrator
 * 
 *         通过dialogFragment来管理该对话框。
 *         并实现了android.app.TimePickerDialog.OnTimeSetListener;该接口。
 */
public class TimerPickerFragment extends DialogFragment implements
		OnTimeSetListener {

	private Calendar calendar = null;
	private TimePickerDialog dialog = null;
	private int hour;
	private int minute;

	public TimerPickerFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 从日历对象中获得对应的小时和分钟。
		calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		// 然后给时间对话框进行赋值。
		// TimePickerDialog(context, callBack, hourOfDay, minute, is24HourView)
		/**
		 *该构造函数的参数的意思分别是：context指对应的应用上下文。
		 *callBack：是指回调函数
		 *hgourofDay ：对话框初始化的小时数
		 *minute：对话框初始化的分钟数。
		 *is24HourView：是否已24小时的格式显示。
		 */
		dialog = new TimePickerDialog(getContext(), this, hour, minute, true);
		return dialog;
	}

	/** (non-Javadoc)
	 * @see android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(android.widget.TimePicker, int, int)
	 * 通过该回调函数可以取得设置后的对话框的小时，分钟的值。
	 * 
	 */
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), hourOfDay + ":" + minute,
				Toast.LENGTH_SHORT).show();
	}

}
