package com.wxf.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.wxf.contentprovider.R;

public class AddDialogFragment extends DialogFragment {

	private AlertDialog alertDialog;
	private String name;
	private int age;
	private EditText editName;
	private EditText editAge;
	private Button buttonOk;
	private Button buttonErr;
	
	public AlertDialog getAlertDialog() {
		return alertDialog;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public AddDialogFragment() {
		// TODO Auto-generated constructor stub
	}
	public interface onDialogClickListener{
		public void onOkClickListener();
		public void onCancelClickListener();
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new Builder(getActivity());
		builder.setTitle("ÇëÌí¼Ó");
		LayoutInflater inflater=getActivity().getLayoutInflater();
		View view=inflater.inflate(R.layout.add, null);
		builder.setView(view);
		alertDialog=builder.create();
		editName=(EditText) view.findViewById(R.id.name);
		editAge=(EditText) view.findViewById(R.id.age);
		buttonOk=(Button) view.findViewById(R.id.addok);
		buttonErr=(Button) view.findViewById(R.id.addCancel);
		buttonOk.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			name=editName.getText().toString();
			age=Integer.parseInt(editAge.getText().toString());
			System.out.println(name+","+age);
			System.out.println(getName()+","+getAge()+"---");
			alertDialog.dismiss();	
			}
		});
		buttonErr.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
		return alertDialog;
	}

}
