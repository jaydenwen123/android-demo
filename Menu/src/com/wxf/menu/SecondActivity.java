package com.wxf.menu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private ListView listView;
	private Button button;
	private List<String> list;
	private ActionMode actionMode;
	private TextView textView;
	private Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		textView=(TextView) findViewById(R.id.textView3);
		listView = (ListView) findViewById(R.id.listView2);
		PrepareData();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				SecondActivity.this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
		//对按钮绑定长按事件监听器。当按钮长按时。会弹出菜单。
		button = (Button) findViewById(R.id.button2);
		button.setOnLongClickListener(new OnLongClickListener() {

			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				if (actionMode != null) {

					return false;
				} else {
					actionMode = startActionMode(mActionCallback);
					v.setSelected(true);
					return true;
				}
			}
		});
		
		button2=(Button) findViewById(R.id.button11);
		button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu popupMenu=new PopupMenu(SecondActivity.this, v);
				popupMenu.inflate(R.menu.first);
				popupMenu.show();
				popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						switch (item.getItemId()) {
						case R.id.open:
//							show("open");
							Intent intent=new Intent();
							intent.setClass(SecondActivity.this, MainActivity.class);
							startActivity(intent);
							setIntent(intent);
							return true;
						case R.id.save:
							show("save");
							return true;
						case R.id.close:
							show("close");
							return true;

						default:
							return false;
						}
					}

				});
			}
		});
		
//		对list组件绑定actionmode
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				MenuInflater inflater=getMenuInflater();
				inflater.inflate(R.menu.first, menu);
				return true;
			}
			
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.open:
					show("open");
					mode.finish();
					return true;
				case R.id.save:
					show("save");
					mode.finish();
					return true;
				case R.id.close:
					show("close");
					mode.finish();
					return true;

				default:
					return false;
				}
			}
			

			public void onItemCheckedStateChanged(ActionMode mode, int position,
					long id, boolean checked) {
				// TODO Auto-generated method stub
				Menu menu=mode.getMenu();
				MenuItem menuItem=menu.getItem(position);
				if(checked){
					textView.setText(id+","+menuItem.getTitle()+","+position);
				}
			}
		});
	}

	
	
	/**
	 * 给list贮备数据。
	 */
	private void PrepareData() {
		list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("fei" + i);
		}
	}

	/**
	 * 
	 * 
	 * 要使用ActionMode方法来创建一个浮动的菜单。需要以下的步骤
	 * 1.首先创建一个ActionMode.Callback接口对象。然后来实现其中的四个方法。
	 * 
	 */
	private ActionMode.Callback mActionCallback = /**
	 * @author Administrator
	 * 
	 */
	new ActionMode.Callback() {

		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			actionMode = null;
		}

		/**
		 * 
		 * 动态的加载菜单的布局文件。
		 */
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.first, menu);
			return true;
		}

		/**
		 * 
		 * 通过switch语句来执行对不同的菜单选项的操作。
		 */
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			switch (item.getItemId()) {
			case R.id.open:
				show("open");
				mode.finish();
				return true;
			case R.id.save:
				show("save");
				mode.finish();
				return true;
			case R.id.close:
				show("close");
				mode.finish();
				return true;

			default:
				return false;
			}
		}

		private void show(String string) {
			// TODO Auto-generated method stub
			Toast.makeText(SecondActivity.this, string, Toast.LENGTH_SHORT)
					.show();
		}
	};
	private void show(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(SecondActivity.this, string, Toast.LENGTH_SHORT).show();
	}
}
