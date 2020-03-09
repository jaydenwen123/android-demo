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

//	定义表名
	private static final String TABLENAME = "user";
//	定义插入，修改。删除、查询的关键字。用来选择对应的要操作的数据库增删改对应的是可写的数据库
//	查对应的是可读的数据库
	private static final String INSERT = "insert";
	private static final String UPDATe = "update";
	private static final String DELETE = "delete";
	private static final String QUERY = "query";
//	定义操作数据库sqlite的工具类。
	private OpenSqliteTool openSqliteTool = null;
//	定义urimatch对象。
	private static final UriMatcher uriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
//	标志位对应的单个操作和多个操作。
	private static final int SIGNALUSER = 1;
	private static final int MULTIUSERS = 2;

//	添加uri
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
	 * 在该方法中，进行初始化。
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
	 * 查询数据。
	 * 通过switch来判断查询单条记录还是多条记录。
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
//			单条记录来获取id
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
//		返回vnd.android.cursor.item代表单条记录
			return "vnd.android.cursor.item/user";

		case MULTIUSERS:
//		返回vnd.android.cursor.dir代表多条记录
			return "vnd.android.cursor.dir/user";
		}

		return null;
	}

	/**
	 * 
	 * 
	 * @param uri
	 *            指定的uri来添加。
	 * @param values传入的内容
	 *            。
	 * @return
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		Uri uri2 = null;
		// 获取要操作的数据库。
		SQLiteDatabase database = getSqliteDataBase("insert");
		int match = uriMatcher.match(uri);
		switch (match) {
		case MULTIUSERS:
			long result = 0;
			// 对数据库进行添加。
			result = database.insert(TABLENAME, null, values);
			// 构建新的uri返回。
			uri2 = ContentUris.withAppendedId(uri, result);
			break;
		}
		database.close();
		return uri2;
	}

	/**
	 * 通过传入的参数啦获取对应的不同操作的数据库。
	 * 
	 * @param string
	 * @return
	 */
	private SQLiteDatabase getSqliteDataBase(String string) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = null;
		// 如果是插入，删除，修改操作，则返回可写的数据库。
		if (string.equals(INSERT) || string.equals(UPDATe)
				|| string.equals(DELETE)) {
			database = openSqliteTool.getWritableDatabase();
			// 如果是对应的检索操作的话。返回的是可读的数据库。retrieve检索。
		} else if (string.equals(QUERY)) {
			database = openSqliteTool.getReadableDatabase();
		}
		return database;
	}

	/**
	 * 
	 * 删除数据。
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int match=uriMatcher.match(uri);
//		获取数据库。
		SQLiteDatabase database=getSqliteDataBase("delete");
		int row=0;
		switch (match) {
		case SIGNALUSER:
//			解析id
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
	 * 更新数据。
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
//		获取数据库
		SQLiteDatabase database =getSqliteDataBase("update");
//		构建uri
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
