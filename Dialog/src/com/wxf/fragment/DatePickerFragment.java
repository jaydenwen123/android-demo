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
 *ͨ��dialogFragment����������ڶԻ���
 *���Ҫ�ͽ����õ����ڵ�ֵ���뵽activity��
 *����ͨ���ӿڻص��ķ�����ʵ�֡�
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
//		��ȡ��������
		calendar = Calendar.getInstance();
//		Ȼ��ֱ��ȡ�����յ�ֵ��
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
//		����һ�����ڶԻ���
		datePickerDialog = new DatePickerDialog(getContext(), this, year,
				month, day);
		return datePickerDialog;

	}

	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(),
				year + "��" + month + "��" + dayOfMonth + "��", Toast.LENGTH_SHORT)
				.show();
	}
}
