package com.wxf.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wxf.util.JpgFile.SOF0.Jcomponent;
import com.wxf.util.JpgFile.SOS.SOSComponent;

public class JpgFile {
	// C:\\Users\\Administrator\\Desktop\\数字图像处理小作业\\控制图片的缩放比例和压缩\\200px-50.jpg
	// 定义常量，该参数指要读取的jpg文件的全路径
	public static final String READFILENAME = "C:\\Users\\Administrator\\Desktop\\数字图像处理作业\\控制图片的缩放比例和压缩\\200px-50.jpg";
	// 该常量指要保存jpg图片对应的十六进制数据的文件路径
	public static final String SAVEFILENAME = "C:\\Users\\Administrator\\Desktop\\wen.xls";
	// 该常量是指要保存解析出来的jpg图片信息的文件路径
	public static final String JPGFILEINFO = "C:\\Users\\Administrator\\Desktop\\info.txt";
	// 定义文件头的标识
	public static final int SOIFLAG = 0xD8;
	// 定义文件尾的标识
	public static final int EOIFLAG = 0xD9;
	// 定义帧开始的标志位（标准的JPEG）
	public static final int SOFOFLAG = 0xC0;
	// 定义Huffman表（霍夫曼表）的标识
	public static final int DHTFLAG = 0xC4;
	// 定义扫描行开始的标识
	public static final int SOSFLAG = 0xDA;
	// 定义量化表的标识
	public static final int DQTFLAG = 0xDB;
	// 定义交换格式和图像识别信息的标识
	public static final int APP0FLAG = 0xE0;
	// 定义注释的标识
	public static final int COMFLAG = 0xFE;
	// 定义重新开始间隔的标识
	public static final int DRIFLAG = 0xDD;

	public static enum SelectionType {
		SOI, EOI, SOFO, DHT, SOS, DQT, DRI, APP0, COM
	};

	// public static SelectionType selectionType;

	// 该集合主要用来存放要解析的不同段的类型
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
	 * 根据高低字节来获取不同的段
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
	 * 定义sofo的段结构
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class SOF0 {
		// 样本精度
		public byte samplePrecision;
		// 图片的高度
		public byte[] height = new byte[2];
		// 图片的宽度
		public byte[] width = new byte[2];
		// 组件数量
		public byte componentNum;

		// 定义组件类
		public static final class Jcomponent {
			// 组件的id
			public byte id;
			// 采样系数（0-3位代表垂直采样系数），4-7位代表水平采样系数
			public byte factor;
			// 量化表号
			public byte tableNo;
		}

		// 添加组件的list集合
		public ArrayList<Jcomponent> component = new ArrayList<Jcomponent>();
	}

	// SOF0段
	public static SOF0 sof0 = new SOF0();

	/**
	 * 定义量化表端的结构
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class DQT {

		// QT的信息：0-3位：QT号，4-7位QT的精度。（0=8bit，1个字节，否则=16bit，2字节。）
		public byte qtInfo;

		// 定义QT的数据结构
		public static final class QT {
			// qt中64个字节的数据
			public byte[] qArray = new byte[64];
		}

		// 一个dqt段可以包含多个QT，每个都有自己的信息字节。
		public ArrayList<QT> arrQt = new ArrayList<QT>();

		// 给QT中读入数据
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
	 * 定义app0段
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class APP0 {
		// 交换格式 4A46494600 JFIF的ASICLL码
		public byte[] changeStyle = new byte[5];
		// 主版本号
		public byte hostVersion;
		// 次版本号
		public byte SecondVersion;
		// 密度单位
		public byte densityUnit;
		// X像素密度
		public byte[] XpxDensity = new byte[2];
		// Y像素密度
		public byte[] YpxDensity = new byte[2];
		// 缩略图X像素
		public byte XZoom;
		// 缩略图Y像素
		public byte YZoom;

	}

	public static APP0 app0 = new APP0();

	/**
	 * 定义DHT段的数据结构
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class DHT {
		// 定义一个字节的HT的信息
		public byte htInfo;
		// 定义HT的位表
		public byte[] htUnitTable = new byte[16];
		private int length = Sum();
		// 定义HT的值表
		public byte[] htValueTable = new byte[length];

		// 计算前面的HT的位表的数据的和
		public int Sum() {
			int sum = 0;
			for (byte b : htUnitTable) {
				sum += b;
			}
			return sum & 0xff;
		}
	}

	// 定义盛放霍夫曼段的数据的集合
	public static ArrayList<DHT> dhtList = new ArrayList<JpgFile.DHT>();

	/**
	 * 定义扫描行段的数据结构
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class SOS {

		// 扫描行内的组件数量
		public byte componentNum;

		/**
		 * 定义扫描行的组件类
		 * 
		 * @author Administrator
		 * 
		 */
		public static final class SOSComponent {
			public byte id;
			public byte humTableNO;
		}

		// 定义扫描行内的组件数
		public ArrayList<SOSComponent> sosComponents = new ArrayList<JpgFile.SOS.SOSComponent>();
	}

	// 定义行扫描段
	public static SOS sos = new SOS();

	/**
	 * 获取每一段的数据长度
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
	 * 跳过某一段数据
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
	 * 读取DQT的数据
	 * 
	 * @param bais
	 */
	public static void ReadDQT(ByteArrayInputStream bais) {
		// 获取段的长度
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
		// System.out.println("DQT已经读写完成！");

	}

	/**
	 * 读入APP0的数据
	 * 
	 * @param bais
	 * @throws IOException
	 */
	public static void ReadAPP0(ByteArrayInputStream bais) throws IOException {
		int blockLen = GetSectionLen(bais.read(), bais.read());
		APP0 app0 = new APP0();
		// 读入app0的数据
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
	 * 读取SOF0段的数据
	 * 
	 * @param bais
	 * @throws IOException
	 */
	public static void ReadSOF0(ByteArrayInputStream bais) throws IOException {
		int blockLen = GetSectionLen(bais.read(), bais.read());
		// 构建sof0段的对象，然后对进行赋值
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
		// 最后进行赋值
		JpgFile.sof0 = sof0;
	}

	/**
	 * 将jpg的文件数据的DHT段进行读取
	 * 
	 * @param bais
	 * @throws IOException
	 */
	public static void ReadDHT(ByteArrayInputStream bais) throws IOException {
		// 读取DHT段的长度
		int blockLen = GetSectionLen(bais.read(), bais.read());
		// 构建DHT对象，然后进行读取数据
		DHT dht = new DHT();
		dht.htInfo = (byte) bais.read();
		bais.read(dht.htUnitTable);
		int length = dht.Sum();
		dht.htValueTable = new byte[length];
		bais.read(dht.htValueTable);
		// 该list中添加dht段
		dhtList.add(dht);
	}

	/**
	 * 读取SOS段的数据
	 * 
	 * @param bais
	 */
	public static void ReadSOS(ByteArrayInputStream bais) {
		// 读出该段的长度
		int blockLen = GetSectionLen(bais.read(), bais.read());
		// 构建SOS对象，然后对其进行赋值
		SOS sos = new SOS();
		sos.componentNum = (byte) bais.read();
		for (int i = 0; i < sos.componentNum; i++) {
			SOSComponent component = new SOSComponent();
			component.id = (byte) bais.read();
			component.humTableNO = (byte) bais.read();
			sos.sosComponents.add(component);
		}
		// 进行传值
		JpgFile.sos = sos;
	}
}
