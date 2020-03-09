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
 *         ͨ��dialogFragment������öԻ���
 *         ��ʵ����android.app.TimePickerDialog.OnTimeSetListener;�ýӿڡ�
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
		// �����������л�ö�Ӧ��Сʱ�ͷ��ӡ�
		calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		// Ȼ���ʱ��Ի�����и�ֵ��
		// TimePickerDialog(context, callBack, hourOfDay, minute, is24HourView)
		/**
		 *�ù��캯���Ĳ�������˼�ֱ��ǣ�contextָ��Ӧ��Ӧ�������ġ�
		 *callBack����ָ�ص�����
		 *hgourofDay ���Ի����ʼ����Сʱ��
		 *minute���Ի����ʼ���ķ�������
		 *is24HourView���Ƿ���24Сʱ�ĸ�ʽ��ʾ��
		 */
		dialog = new TimePickerDialog(getContext(), this, hour, minute, true);
		return dialog;
	}

	/** (non-Javadoc)
	 * @see android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(android.widget.TimePicker, int, int)
	 * ͨ���ûص���������ȡ�����ú�ĶԻ����Сʱ�����ӵ�ֵ��
	 * 
	 */
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), hourOfDay + ":" + minute,
				Toast.LENGTH_SHORT).show();
	}

}
