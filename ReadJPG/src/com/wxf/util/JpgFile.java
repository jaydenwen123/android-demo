package com.wxf.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wxf.util.JpgFile.SOF0.Jcomponent;
import com.wxf.util.JpgFile.SOS.SOSComponent;

public class JpgFile {
	// C:\\Users\\Administrator\\Desktop\\����ͼ����С��ҵ\\����ͼƬ�����ű�����ѹ��\\200px-50.jpg
	// ���峣�����ò���ָҪ��ȡ��jpg�ļ���ȫ·��
	public static final String READFILENAME = "C:\\Users\\Administrator\\Desktop\\����ͼ������ҵ\\����ͼƬ�����ű�����ѹ��\\200px-50.jpg";
	// �ó���ָҪ����jpgͼƬ��Ӧ��ʮ���������ݵ��ļ�·��
	public static final String SAVEFILENAME = "C:\\Users\\Administrator\\Desktop\\wen.xls";
	// �ó�����ָҪ�������������jpgͼƬ��Ϣ���ļ�·��
	public static final String JPGFILEINFO = "C:\\Users\\Administrator\\Desktop\\info.txt";
	// �����ļ�ͷ�ı�ʶ
	public static final int SOIFLAG = 0xD8;
	// �����ļ�β�ı�ʶ
	public static final int EOIFLAG = 0xD9;
	// ����֡��ʼ�ı�־λ����׼��JPEG��
	public static final int SOFOFLAG = 0xC0;
	// ����Huffman�����������ı�ʶ
	public static final int DHTFLAG = 0xC4;
	// ����ɨ���п�ʼ�ı�ʶ
	public static final int SOSFLAG = 0xDA;
	// ����������ı�ʶ
	public static final int DQTFLAG = 0xDB;
	// ���彻����ʽ��ͼ��ʶ����Ϣ�ı�ʶ
	public static final int APP0FLAG = 0xE0;
	// ����ע�͵ı�ʶ
	public static final int COMFLAG = 0xFE;
	// �������¿�ʼ����ı�ʶ
	public static final int DRIFLAG = 0xDD;

	public static enum SelectionType {
		SOI, EOI, SOFO, DHT, SOS, DQT, DRI, APP0, COM
	};

	// public static SelectionType selectionType;

	// �ü�����Ҫ�������Ҫ�����Ĳ�ͬ�ε�����
	public static Map<Integer, SelectionType> map = new HashMap<Integer, JpgFile.SelectionType>();
	static {
		map.put(SOIFLAG, SelectionType.SOI);
		map.put(EOIFLAG, SelectionType.EOI);
		map.put(SOFOFLAG, SelectionType.SOFO);
		map.put(DHTFLAG, SelectionType.DHT);
		map.put(SOSFLAG, SelectionType.SOS);
		map.put(DQTFLAG, SelectionType.DQT);
		map.put(DRIFLAG, SelectionType.DRI);
		map.put(APP0FLAG, SelectionType.APP0);
		map.put(COMFLAG, SelectionType.COM);
	}

	/**
	 * ���ݸߵ��ֽ�����ȡ��ͬ�Ķ�
	 * 
	 * @param firstByte
	 * @param secondByte
	 * @return
	 * @throws Exception
	 * 
	 */
	public static SelectionType getSelectionType(int firstByte, int secondByte)
			throws Exception {

		firstByte = firstByte & 0xff;
		secondByte = secondByte & 0xff;
		if (firstByte != 0xff)
			return null;
		else {
			if (!map.containsKey(secondByte))
				return null;
			return map.get(secondByte);
		}
	}

	/**
	 * ����sofo�Ķνṹ
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class SOF0 {
		// ��������
		public byte samplePrecision;
		// ͼƬ�ĸ߶�
		public byte[] height = new byte[2];
		// ͼƬ�Ŀ��
		public byte[] width = new byte[2];
		// �������
		public byte componentNum;

		// ���������
		public static final class Jcomponent {
			// �����id
			public byte id;
			// ����ϵ����0-3λ����ֱ����ϵ������4-7λ����ˮƽ����ϵ��
			public byte factor;
			// �������
			public byte tableNo;
		}

		// ��������list����
		public ArrayList<Jcomponent> component = new ArrayList<Jcomponent>();
	}

	// SOF0��
	public static SOF0 sof0 = new SOF0();

	/**
	 * ����������˵Ľṹ
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class DQT {

		// QT����Ϣ��0-3λ��QT�ţ�4-7λQT�ľ��ȡ���0=8bit��1���ֽڣ�����=16bit��2�ֽڡ���
		public byte qtInfo;

		// ����QT�����ݽṹ
		public static final class QT {
			// qt��64���ֽڵ�����
			public byte[] qArray = new byte[64];
		}

		// һ��dqt�ο��԰������QT��ÿ�������Լ�����Ϣ�ֽڡ�
		public ArrayList<QT> arrQt = new ArrayList<QT>();

		// ��QT�ж�������
		public void ReadQTArray(ByteArrayInputStream bais, int n)
				throws IOException {
			for (int i = 0; i < n + 1; ++i) {
				QT qt = new QT();
				bais.read(qt.qArray);
				arrQt.add(qt);
			}
		}

	}

	public static ArrayList<DQT> dqtList = new ArrayList<JpgFile.DQT>();

	/**
	 * ����app0��
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class APP0 {
		// ������ʽ 4A46494600 JFIF��ASICLL��
		public byte[] changeStyle = new byte[5];
		// ���汾��
		public byte hostVersion;
		// �ΰ汾��
		public byte SecondVersion;
		// �ܶȵ�λ
		public byte densityUnit;
		// X�����ܶ�
		public byte[] XpxDensity = new byte[2];
		// Y�����ܶ�
		public byte[] YpxDensity = new byte[2];
		// ����ͼX����
		public byte XZoom;
		// ����ͼY����
		public byte YZoom;

	}

	public static APP0 app0 = new APP0();

	/**
	 * ����DHT�ε����ݽṹ
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class DHT {
		// ����һ���ֽڵ�HT����Ϣ
		public byte htInfo;
		// ����HT��λ��
		public byte[] htUnitTable = new byte[16];
		private int length = Sum();
		// ����HT��ֵ��
		public byte[] htValueTable = new byte[length];

		// ����ǰ���HT��λ������ݵĺ�
		public int Sum() {
			int sum = 0;
			for (byte b : htUnitTable) {
				sum += b;
			}
			return sum & 0xff;
		}
	}

	// ����ʢ�Ż������ε����ݵļ���
	public static ArrayList<DHT> dhtList = new ArrayList<JpgFile.DHT>();

	/**
	 * ����ɨ���жε����ݽṹ
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class SOS {

		// ɨ�����ڵ��������
		public byte componentNum;

		/**
		 * ����ɨ���е������
		 * 
		 * @author Administrator
		 * 
		 */
		public static final class SOSComponent {
			public byte id;
			public byte humTableNO;
		}

		// ����ɨ�����ڵ������
		public ArrayList<SOSComponent> sosComponents = new ArrayList<JpgFile.SOS.SOSComponent>();
	}

	// ������ɨ���
	public static SOS sos = new SOS();

	/**
	 * ��ȡÿһ�ε����ݳ���
	 * 
	 * @param iByteHigh
	 * @param iByteLow
	 * @return
	 */
	public static int GetSectionLen(int iByteHigh, int iByteLow) {
		/*
		 * System.out .println("GetSectionLen(" + iByteHigh + ", " + iByteLow +
		 * ")");
		 */
		int iBlockLen = ((iByteHigh << 8) + iByteLow) & 0xffff;
		// System.out.println("Section length: " + iBlockLen + " bytes");
		return iBlockLen;
	}

	/**
	 * ����ĳһ������
	 * 
	 * @param bais
	 * @param eSectionType
	 * 
	 */
	public static void JumpOverSection(ByteArrayInputStream bais,
			SelectionType eSectionType) {
		// System.out.println("Section[" + eSectionType + "] jump over");
		int iBlockLen = GetSectionLen(bais.read(), bais.read());
		bais.skip(iBlockLen - 2);
	}

	/**
	 * ��ȡDQT������
	 * 
	 * @param bais
	 */
	public static void ReadDQT(ByteArrayInputStream bais) {
		// ��ȡ�εĳ���
		int blockLen = GetSectionLen(bais.read(), bais.read());
		DQT dqt = new DQT();
		dqt.qtInfo = (byte) bais.read();
		int n = (dqt.qtInfo << 4) & 0xf;
		try {
			dqt.ReadQTArray(bais, n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dqtList.add(dqt);
		// System.out.println("DQT�Ѿ���д��ɣ�");

	}

	/**
	 * ����APP0������
	 * 
	 * @param bais
	 * @throws IOException
	 */
	public static void ReadAPP0(ByteArrayInputStream bais) throws IOException {
		int blockLen = GetSectionLen(bais.read(), bais.read());
		APP0 app0 = new APP0();
		// ����app0������
		bais.read(app0.changeStyle);
		app0.hostVersion = (byte) bais.read();
		app0.SecondVersion = (byte) bais.read();
		app0.densityUnit = (byte) bais.read();
		bais.read(app0.XpxDensity);
		bais.read(app0.YpxDensity);
		app0.XZoom = (byte) bais.read();
		app0.YZoom = (byte) bais.read();
		JpgFile.app0 = app0;

	}

	/**
	 * ��ȡSOF0�ε�����
	 * 
	 * @param bais
	 * @throws IOException
	 */
	public static void ReadSOF0(ByteArrayInputStream bais) throws IOException {
		int blockLen = GetSectionLen(bais.read(), bais.read());
		// ����sof0�εĶ���Ȼ��Խ��и�ֵ
		SOF0 sof0 = new SOF0();
		sof0.samplePrecision = (byte) bais.read();
		bais.read(sof0.height);
		bais.read(sof0.width);
		sof0.componentNum = (byte) bais.read();
		for (int i = 0; i < sof0.componentNum; i++) {
			Jcomponent jcomponent = new Jcomponent();
			jcomponent.id = (byte) bais.read();
			jcomponent.factor = (byte) bais.read();
			jcomponent.tableNo = (byte) bais.read();
			sof0.component.add(jcomponent);
		}
		// �����и�ֵ
		JpgFile.sof0 = sof0;
	}

	/**
	 * ��jpg���ļ����ݵ�DHT�ν��ж�ȡ
	 * 
	 * @param bais
	 * @throws IOException
	 */
	public static void ReadDHT(ByteArrayInputStream bais) throws IOException {
		// ��ȡDHT�εĳ���
		int blockLen = GetSectionLen(bais.read(), bais.read());
		// ����DHT����Ȼ����ж�ȡ����
		DHT dht = new DHT();
		dht.htInfo = (byte) bais.read();
		bais.read(dht.htUnitTable);
		int length = dht.Sum();
		dht.htValueTable = new byte[length];
		bais.read(dht.htValueTable);
		// ��list�����dht��
		dhtList.add(dht);
	}

	/**
	 * ��ȡSOS�ε�����
	 * 
	 * @param bais
	 */
	public static void ReadSOS(ByteArrayInputStream bais) {
		// �����öεĳ���
		int blockLen = GetSectionLen(bais.read(), bais.read());
		// ����SOS����Ȼ�������и�ֵ
		SOS sos = new SOS();
		sos.componentNum = (byte) bais.read();
		for (int i = 0; i < sos.componentNum; i++) {
			SOSComponent component = new SOSComponent();
			component.id = (byte) bais.read();
			component.humTableNO = (byte) bais.read();
			sos.sosComponents.add(component);
		}
		// ���д�ֵ
		JpgFile.sos = sos;
	}
}
