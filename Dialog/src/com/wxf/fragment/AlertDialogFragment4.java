package com.wxf.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class AlertDialogFragment4 extends DialogFragment {
	List<Integer> list=null;
	AlertDialog alertDialog = null;
	CharSequence[] items = new CharSequence[] { "����", "����", "����", "��ë��" };

	public AlertDialogFragment4() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		list=new ArrayList<Integer>();
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("��ʾ����ѡ����İ��ã�");
//		ͨ��setSingleChoiceItems�÷��������Ի�����ӵ�ѡ��ͬʱ�������������
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(!list.isEmpty()){
					list.clear();
					list.add(which);
					System.out.println("11"+"---"+which);
				}else {
					list.add(which);
					System.out.println("22"+"---"+which);
				}
			}});

		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(new String(items[list.get(0)].toString()));
				/*for (Integer w : list) {
				stringBuilder.append(new String(items[w].toString())+"|");
				}*/
				Toast.makeText(getContext(), "��ѡ��İ����ǣ�"+stringBuilder, Toast.LENGTH_SHORT).show();
			}
		});
		alertDialog=builder.create();
		return alertDialog;

	}

}
