package com.wxf.edittext02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wxf.edittext01.R;
import com.wxf.edittext03.ThridActivity;

public class SecondActivity extends Activity {

	private Button button3;
	private EditText editText3;
	private Button button4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		button3=(Button) findViewById(R.id.button3);
		editText3=(EditText) findViewById(R.id.inputEmail);
		button4=(Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SecondActivity.this, ThridActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void onclick(View v){
		if(v.getId()==R.id.button3){
			System.out.println(((Button)v).getText()+"------------");
			System.out.println(editText3.getError()+"0000");
			
			if((editText3.getText().toString()).trim().equals("")|| editText3.getText()==null){
//				�������ͨ��������ʽ��У��������Ƿ�������͵绰����.
				editText3.setError("�����������Ϊ��");
				Toast.makeText(SecondActivity.this, "�����������Ϊ��", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(SecondActivity.this, "������������ǣ�"+editText3.getText(), Toast.LENGTH_SHORT).show();
			}
		}
	}
}
