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
//		����ʱ������ÿ�ζ�����һ���ö�����Ϊ��������context���󣬶��ö�������static��̬����
//		���г�ʼ��.
		operateSharePreference = new OperateSharePreference(getContext());
		String[] keys = new String[] { "one", "two", "three", "four" };
		Map<String, String> map = operateSharePreference
				.readDataFromSharePreference(keys);
		System.out.println(map);
		assertNotNull(map);
	}
}
