package com.wxf.util;


/**
 * @author wenxiaofei
 * 
 */
public class Tool {

	public final static byte[] hex = "0123456789ABCDEF".getBytes();

	public static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	/*
	 * public static void main(String[] args) { System.out.println(hex.length);
	 * for(byte b:hex){
	 * 
	 * System.out.println(b); } }
	 */

	// 字节数组转十六进制字符串
	/**
	 * @author wenxiaofei
	 * @函数功能：字节数组转为字符串
	 * @返回值：返回转换后的十六进制字符串
	 * @param :the byte array
	 */
	public static String Bytes2HexString(byte[] b) {
		byte[] buff = new byte[2 * b.length];
		for (int i = 0; i < b.length; i++) {
			buff[2 * i] = hex[b[i] >> 4 & 0x0f];
			buff[2 * i + 1] = hex[b[i] & 0x0f];

		}
		return new String(buff);
	}

	public static String intToHex() {
		return "";
	}

	// 十六进制字符串转字节数组

	/**
	 * @author wenxiaofei
	 * @param: hexstr
	 * @return :byte array value
	 */
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	/* 以十六进制输出字节数组的值 */
	public static void printHexString(byte[] b) {
		System.out.println(Bytes2HexString(b));
	}

	/* 两个byte数组连接函数 */

	/**
	 * @author wenxiaofei
	 * @param a
	 *            the a array
	 * @param b
	 *            the b array
	 * @return return the final copy array
	 */
	public static byte[] ToConnByteArrays(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	// 把整数转换为字节数组////////////

	/**
	 * @author wenxiaofei
	 * @param the
	 *            int res
	 * @return return the byte array
	 */
	public static byte[] intTobyte(int res) {
		byte[] targets = new byte[4];
		// System.out.println(res);
		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	//

	/**
	 * @author wenxiaofei
	 * @function to exchange the short s to the byte array ,(小端转换即低位在前高位在后)
	 * @param short s
	 * @return
	 */
	public static byte[] ShortToByteLittle(Short s) {
		byte[] targets = new byte[2];
		targets[0] = (byte) (s & 0xff);
		targets[1] = (byte) ((s >> 8) & 0xff);
		return targets;
	}

	//

	/**
	 * @author wenxiaofei
	 * @Function 将一个短整型的数转为大端字节数组即（高位在前，低位在后）
	 * @param s
	 * @return 返回转换后的字节数组
	 */
	public static byte[] ShortToBytebig(Short s) {
		byte[] targets = new byte[2];
		targets[1] = (byte) (s & 0xff);
		targets[0] = (byte) ((s >> 8) & 0xff);
		return targets;
	}

	// 十六进制转化为二进制字符串

	/**
	 * @author wenxiaofei
	 * @Function 将十六进制字符串转换为二进制字符串
	 * @param hexString
	 * @return 返回二进制字符串
	 */
	public static String HexStrToBinStr(String hexString) {
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000"
					+ Integer.toBinaryString(Integer.parseInt(
							hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	// 将十六进制的时间转换为短格式的时间

	/**
	 * @authority 文小飞
	 * @Function 转换时间（将十六进制的日期转换为数字时间）
	 * @param hexTimeStr
	 * @return 返回转换后的时间
	 */
	public static String TimeChange(String hexTimeStr) {
		String binStr = HexStrToBinStr(hexTimeStr);
		String hourStr = binStr.substring(0, 5);
		int hour = Integer.parseInt(hourStr, 2);
		String minuteStr = binStr.substring(5, 11);
		int minute = Integer.parseInt(minuteStr, 2);
		String secondStr = binStr.substring(11);
		int second = Integer.parseInt(secondStr, 2);
		String time = hour + ":" + minute + ":" + second + "\r\n";
		System.out.print(time);
		return time;
	}

	// 将十六进制的日期转换为短格式的日期

	/**
	 * @author wenxiaofei
	 * @Function 将十六进制的日期转换为短时间日期
	 * @param hexDateStr
	 * @return 返回转换后的日期
	 */
	public static String DateChange(String hexDateStr) {
		String binStr = HexStrToBinStr(hexDateStr);
		String yearStr = binStr.substring(0, 7);
		int year = Integer.parseInt(yearStr, 2) + 2000;
		String monthStr = binStr.substring(7, 11);
		int month = Integer.parseInt(monthStr, 2);
		String dayStr = binStr.substring(11);
		int day = Integer.parseInt(dayStr, 2);
		String date = year + "年" + month + "月" + day + "日";
		System.out.print(date);
		return date;
	}

	/**
	 * @函数功能: BCD码转为10进制串(阿拉伯数据)
	 * @输入参数: BCD码
	 * @输出结果: 10进制串
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
				.toString().substring(1) : temp.toString();
	}

	/**
	 * @函数功能: 10进制串转为BCD码
	 * @输入参数: 10进制串
	 * @输出结果: BCD码
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}
	/*
	 * public static void main(String[] args) { byte[]
	 * a=Tool.str2Bcd(Integer.toString(43)); Tool.printHexString(a); }
	 */
}