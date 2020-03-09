package com.wxf.contentprovider;

import java.util.regex.Matcher;

import android.R.integer;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore.RightsStatus;
import android.net.Uri;
import android.renderscript.Program;

public class MyContextProvider extends ContentProvider {

//	�������
	private static final String TABLENAME = "user";
//	������룬�޸ġ�ɾ������ѯ�Ĺؼ��֡�����ѡ���Ӧ��Ҫ���������ݿ���ɾ�Ķ�Ӧ���ǿ�д�����ݿ�
//	���Ӧ���ǿɶ������ݿ�
	private static final String INSERT = "insert";
	private static final String UPDATe = "update";
	private static final String DELETE = "delete";
	private static final String QUERY = "query";
//	����������ݿ�sqlite�Ĺ����ࡣ
	private OpenSqliteTool openSqliteTool = null;
//	����urimatch����
	private static final UriMatcher uriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
//	��־λ��Ӧ�ĵ��������Ͷ��������
	private static final int SIGNALUSER = 1;
	private static final int MULTIUSERS = 2;

//	���uri
	static {
		uriMatcher.addURI("com.wxf.contentprovider.MyContextProvider", "user",
				MULTIUSERS);
		uriMatcher.addURI("com.wxf.contentprovider.MyContextProvider",
				"user/#", SIGNALUSER);
	}

	public MyContextProvider() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * �ڸ÷����У����г�ʼ����
	 * 
	 */
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		openSqliteTool = new OpenSqliteTool(getContext(), null);
		String[] sql = { "create table user(_id integer primary key autoincrement,name varchar(64) not null,age integer)" };
		openSqliteTool.setSqls(sql);//primary key autoincrement
		return true;
	}

	/**
	 * 
	 * ��ѯ���ݡ�
	 * ͨ��switch���жϲ�ѯ������¼���Ƕ�����¼��
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		Cursor cursor=null;
		SQLiteDatabase database=getSqliteDataBase("query");
		int match=uriMatcher.match(uri);
		switch (match) {
		case SIGNALUSER:
//			������¼����ȡid
			long id=ContentUris.parseId(uri);
			StringBuilder builder=new StringBuilder("id="+id);
			if(selection!=null && !selection.equals("")){
				builder.append(selection);
			}
			cursor=database.query(TABLENAME, projection, builder.toString(), selectionArgs, null, null, sortOrder);
			
			break;

		case MULTIUSERS:
			cursor=database.query(TABLENAME, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		}
		return cursor;
	}

	
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		int match = uriMatcher.match(uri);
		switch (match) {
		case SIGNALUSER:
//		����vnd.android.cursor.item��������¼
			return "vnd.android.cursor.item/user";

		case MULTIUSERS:
//		����vnd.android.cursor.dir���������¼
			return "vnd.android.cursor.dir/user";
		}

		return null;
	}

	/**
	 * 
	 * 
	 * @param uri
	 *            ָ����uri����ӡ�
	 * @param values���������
	 *            ��
	 * @return
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		Uri uri2 = null;
		// ��ȡҪ���������ݿ⡣
		SQLiteDatabase database = getSqliteDataBase("insert");
		int match = uriMatcher.match(uri);
		switch (match) {
		case MULTIUSERS:
			long result = 0;
			// �����ݿ������ӡ�
			result = database.insert(TABLENAME, null, values);
			// �����µ�uri���ء�
			uri2 = ContentUris.withAppendedId(uri, result);
			break;
		}
		database.close();
		return uri2;
	}

	/**
	 * ͨ������Ĳ�������ȡ��Ӧ�Ĳ�ͬ���������ݿ⡣
	 * 
	 * @param string
	 * @return
	 */
	private SQLiteDatabase getSqliteDataBase(String string) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		// ����ǲ��룬ɾ�����޸Ĳ������򷵻ؿ�д�����ݿ⡣
		if (string.equals(INSERT) || string.equals(UPDATe)
				|| string.equals(DELETE)) {
			database = openSqliteTool.getWritableDatabase();
			// ����Ƕ�Ӧ�ļ��������Ļ������ص��ǿɶ������ݿ⡣retrieve������
		} else if (string.equals(QUERY)) {
			database = openSqliteTool.getReadableDatabase();
		}
		return database;
	}

	/**
	 * 
	 * ɾ�����ݡ�
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int match=uriMatcher.match(uri);
//		��ȡ���ݿ⡣
		SQLiteDatabase database=getSqliteDataBase("delete");
		int row=0;
		switch (match) {
		case SIGNALUSER:
//			����id
			long id=ContentUris.parseId(uri);
			String whereClause="id="+id;
			if(selection!=null &&!selection.equals("")){
				whereClause+=selection;
			}
			row=database.delete(TABLENAME, whereClause, selectionArgs);
			break;

		case MULTIUSERS:
			row=database.delete(TABLENAME, selection, selectionArgs);
			break;
		}
		return row;
	}

	/**
	 * 
	 * �������ݡ�
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
//		��ȡ���ݿ�
		SQLiteDatabase database =getSqliteDataBase("update");
//		����uri
		int match=uriMatcher.match(uri);
		int count=0;
		switch (match) {
		case SIGNALUSER:
			long id=ContentUris.parseId(uri);
			StringBuilder builder=new StringBuilder();
			builder.append("id="+id);
			if(selection!=null && !selection.equals("")){
				builder.append(selection);
			}
			count=database.update(TABLENAME, values, builder.toString(), selectionArgs);
			break;

		case MULTIUSERS:
			count=database.update(TABLENAME, values, selection, selectionArgs);
			break;
		}
		return count;
	}

}
