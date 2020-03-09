package com.wxf.fragment;

import java.util.Calendar;

import android.R.integer;
import android.R.layout;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * @author Administrator
 *
 *通过dialogFragment来管理该日期对话框
 *如果要和将设置的日期的值传入到activity中
 *可以通过接口回到的方法来实现。
 */
public class DatePickerFragment extends DialogFragment implements
		OnDateSetListener {

	private Calendar calendar;
	DatePickerDialog datePickerDialog = null;
	private int year;
	private int month;
	private int day;

	public DatePickerFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		获取日历对象
		calendar = Calendar.getInstance();
//		然后分别获取年月日的值。
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
//		创建一个日期对话框。
		datePickerDialog = new DatePickerDialog(getContext(), this, year,
				month, day);
		return datePickerDialog;

	}

	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(),
				year + "年" + month + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT)
				.show();
	}
}
