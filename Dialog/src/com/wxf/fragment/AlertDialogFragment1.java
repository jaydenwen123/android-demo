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
		builder.setMessage("是否要删除？");
		builder.setTitle("提示：");
//		给对话框添加确定按钮。通过该方法setPositiveButton
		builder.setPositiveButton("确定", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();

			}
		});
//		通过setNegativeButton方法来添加取消按钮。
		builder.setNegativeButton("取消", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();
			}
		});
//		通过setNeutralButton方法添加第三个按钮。
		builder.setNeutralButton("忽略", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();
			}
		});
		return builder.create();
	}

}
