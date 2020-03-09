package com.wxf.test;

import com.wxf.contentprovider.MyContextProvider;

import android.R.integer;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.widget.ArrayAdapter;

public class TestContentProvider extends AndroidTestCase{

	public TestContentProvider() {
		// TODO Auto-generated constructor stub
	}
	
	public void testInsert(){
		ContentResolver contentResolver=getContext().getContentResolver();
		ContentValues values=new ContentValues();
		values.put("name", "wen");
		values.put("age", 12);
		Uri uri=Uri.parse("content://com.wxf.contentprovider.MyContextProvider/user");
		uri=contentResolver.insert(uri, values);
		System.out.println("--->"+uri);
	}
	
	public void  testDelete(){
		ContentResolver contentResolver=getContext().getContentResolver();
//		Uri uri=Uri.parse("content://com.wxf.contentprovider.MyContextProvider/user");
		Uri uri=Uri.parse("content://com.wxf.contentprovider.MyContextProvider/user/4");
		int row=contentResolver.delete(uri, null, null);
		System.out.println("---->"+row);
	}
	
	public void testUpdate(){
		ContentResolver contentResolver=getContext().getContentResolver();
		Uri uri=Uri.parse("content://com.wxf.contentprovider.MyContextProvider/user/4");
//		Uri uri=Uri.parse("content://com.wxf.contentprovider.MyContextProvider/user");
		ContentValues contentValues=new ContentValues();
		contentValues.put("name", "wenxiaofei");
		int row=contentResolver.update(uri, contentValues, null, null);
		assertEquals(1, row);
	}

	public void testQuery(){
		ContentResolver resolver=getContext().getContentResolver();
		Uri uri=Uri.parse("content://com.wxf.contentprovider.MyContextProvider/user");
		Cursor cursor=resolver.query(uri, null, null, null, null);
		while(cursor.moveToNext()){
			System.out.println(cursor.getString(cursor.getColumnIndex("name"))+"---->");
			
		}
		assertEquals(3, cursor.getCount());
	}
}
