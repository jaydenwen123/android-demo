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

	// �ֽ�����תʮ�������ַ���
	/**
	 * @author wenxiaofei
	 * @�������ܣ��ֽ�����תΪ�ַ���
	 * @����ֵ������ת�����ʮ�������ַ���
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

	// ʮ�������ַ���ת�ֽ�����

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

	/* ��ʮ����������ֽ������ֵ */
	public static void printHexString(byte[] b) {
		System.out.println(Bytes2HexString(b));
	}

	/* ����byte�������Ӻ��� */

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

	// ������ת��Ϊ�ֽ�����////////////

	/**
	 * @author wenxiaofei
	 * @param the
	 *            int res
	 * @return return the byte array
	 */
	public static byte[] intTobyte(int res) {
		byte[] targets = new byte[4];
		// System.out.println(res);
		targets[0] = (byte) (res & 0xff);// ���λ
		targets[1] = (byte) ((res >> 8) & 0xff);// �ε�λ
		targets[2] = (byte) ((res >> 16) & 0xff);// �θ�λ
		targets[3] = (byte) (res >>> 24);// ���λ,�޷������ơ�
		return targets;
	}

	//

	/**
	 * @author wenxiaofei
	 * @function to exchange the short s to the byte array ,(С��ת������λ��ǰ��λ�ں�)
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
	 * @Function ��һ�������͵���תΪ����ֽ����鼴����λ��ǰ����λ�ں�
	 * @param s
	 * @return ����ת������ֽ�����
	 */
	public static byte[] ShortToBytebig(Short s) {
		byte[] targets = new byte[2];
		targets[1] = (byte) (s & 0xff);
		targets[0] = (byte) ((s >> 8) & 0xff);
		return targets;
	}

	// ʮ������ת��Ϊ�������ַ���

	/**
	 * @author wenxiaofei
	 * @Function ��ʮ�������ַ���ת��Ϊ�������ַ���
	 * @param hexString
	 * @return ���ض������ַ���
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

	// ��ʮ�����Ƶ�ʱ��ת��Ϊ�̸�ʽ��ʱ��

	/**
	 * @authority ��С��
	 * @Function ת��ʱ�䣨��ʮ�����Ƶ�����ת��Ϊ����ʱ�䣩
	 * @param hexTimeStr
	 * @return ����ת�����ʱ��
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

	// ��ʮ�����Ƶ�����ת��Ϊ�̸�ʽ������

	/**
	 * @author wenxiaofei
	 * @Function ��ʮ�����Ƶ�����ת��Ϊ��ʱ������
	 * @param hexDateStr
	 * @return ����ת���������
	 */
	public static String DateChange(String hexDateStr) {
		String binStr = HexStrToBinStr(hexDateStr);
		String yearStr = binStr.substring(0, 7);
		int year = Integer.parseInt(yearStr, 2) + 2000;
		String monthStr = binStr.substring(7, 11);
		int month = Integer.parseInt(monthStr, 2);
		String dayStr = binStr.substring(11);
		int day = Integer.parseInt(dayStr, 2);
		String date = year + "��" + month + "��" + day + "��";
		System.out.print(date);
		return date;
	}

	/**
	 * @��������: BCD��תΪ10���ƴ�(����������)
	 * @�������: BCD��
	 * @������: 10���ƴ�
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
	 * @��������: 10���ƴ�תΪBCD��
	 * @�������: 10���ƴ�
	 * @������: BCD��
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