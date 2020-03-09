package com.wxf.fragment;

import java.util.ArrayList;
import java.util.List;

import com.wxf.dialog.R;

import android.R.raw;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class AlertDialogFragment3 extends DialogFragment {
	List<Integer> list=null;
	AlertDialog alertDialog = null;
//	构建数组，用来显示其实也可以在string.xml文件中，构建一个数组。
	CharSequence[] items = new CharSequence[] { "篮球", "足球", "排球", "羽毛球" };
	//String[] items=getResources().getStringArray(R.array.items);
	public AlertDialogFragment3() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		list=new ArrayList<Integer>();
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("提示：请选择你的爱好：");
		builder.setMultiChoiceItems(items, null,
				new OnMultiChoiceClickListener() {

					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						// TODO Auto-generated method stub
					if(isChecked){
						list.add(which);
					}
					else if(list.contains(which)){
						list.remove(Integer.valueOf(which));
					}
					}
				});
		builder.setPositiveButton("确定", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				StringBuilder stringBuilder = new StringBuilder();
				for (Integer w : list) {
					stringBuilder.append(new String(items[w].toString())+"|");
				}
				Toast.makeText(getContext(), "你选择的爱好是："+stringBuilder, Toast.LENGTH_SHORT).show();
			}
		});
		alertDialog=builder.create();
		return alertDialog;

	}

}
