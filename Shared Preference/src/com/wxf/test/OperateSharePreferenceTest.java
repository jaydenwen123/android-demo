package com.wxf.test;

import java.util.HashMap;
import java.util.Map;

import android.test.AndroidTestCase;

import com.wxf.sharedpreference.OperateSharePreference;

public class OperateSharePreferenceTest extends AndroidTestCase {
	private static OperateSharePreference operateSharePreference;

	public void testWriteDataToSharePreference() {
		operateSharePreference = new OperateSharePreference(getContext());
		Map<String, String> map = new HashMap<String, String>();
		map.put("one", "first");
		map.put("two", "second");
		map.put("three", "thrid");
		map.put("four", "forth");
		boolean flag = operateSharePreference.writeDataToSharePreference(map);
		System.out.println(flag);
	}

	public void testReadDataFromSharePreference() {
//		测试时。必须每次都创建一个该对象。因为它依赖于context对象，而该对象不能在static静态块中
//		进行初始化.
		operateSharePreference = new OperateSharePreference(getContext());
		String[] keys = new String[] { "one", "two", "three", "four" };
		Map<String, String> map = operateSharePreference
				.readDataFromSharePreference(keys);
		System.out.println(map);
		assertNotNull(map);
	}
}
