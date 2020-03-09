package com.wxf.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Toast;

public class AlertDialogFragment5 extends DialogFragment {

	private View layout;

	public View getLayout() {
		return layout;
	}

	public void setLayout(View layout) {
		this.layout = layout;
	}

	public AlertDialogFragment5() {
		// TODO Auto-generated constructor stub
	}

//	定义一个接口，用来实现从对话框到activity传递数据。
	public interface NtoiceDialogListener{
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
		public void onDialogNeutralClick(DialogFragment dialog);
	}
	
	NtoiceDialogListener ntoiceDialogListener;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		 // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
        	ntoiceDialogListener = (NtoiceDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		layout=getActivity().getLayoutInflater().inflate(com.wxf.dialog.R.layout.sign_layout, null);
		builder.setView(layout);
		builder.setTitle("提示：");
		builder.setPositiveButton("确定", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				/*Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();*/
				ntoiceDialogListener.onDialogPositiveClick(AlertDialogFragment5.this);

			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "you click:" + which,
						Toast.LENGTH_LONG).show();
				ntoiceDialogListener.onDialogNegativeClick(AlertDialogFragment5.this);
			}
		});
		return builder.create();
	}

}
