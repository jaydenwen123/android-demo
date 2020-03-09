package com.wxf.fragment;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class AlertDialogFragment2 extends DialogFragment {

	public AlertDialogFragment2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new AlertDialog.Builder(getContext(),R.attr.alertDialogTheme);
		AlertDialog alertDialog=null;
//		���ñ��⣺��Ҫע�⣬���Ҫ����б���ѡ�򣬸�ѡ��ʱ�������ܸ��Ի������message���ԡ�
		builder.setTitle("��ʾ����ѡ���Ա�");
		builder.setPositiveButton("ok", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
			}
		});
//		����һ��������ʾѡ������顣
		CharSequence[] array=new CharSequence[]{"green","black","red","blue"};
//		ͨ��setItems����������
		builder.setItems(array, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "select"+which, Toast.LENGTH_SHORT).show();
				//alertDialog.show();
			}
		});
		alertDialog=builder.create();
		return alertDialog;
	}
}
