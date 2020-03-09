package com.wxf.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class AlertDialogFragment1 extends DialogFragment {

	public AlertDialogFragment1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setMessage("�Ƿ�Ҫɾ����");
		builder.setTitle("��ʾ��");
//		���Ի������ȷ����ť��ͨ���÷���setPositiveButton
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();

			}
		});
//		ͨ��setNegativeButton���������ȡ����ť��
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();
			}
		});
//		ͨ��setNeutralButton������ӵ�������ť��
		builder.setNeutralButton("����", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();
			}
		});
		return builder.create();
	}

}
