package com.wxf.decode;

import java.awt.Image;
import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.wxf.read.ReadJPG;
import com.wxf.util.JpgFile;
import com.wxf.util.JpgFile.SOF0.Jcomponent;
import com.wxf.util.JpgFile.SOS.SOSComponent;
import com.wxf.util.Tool;
import com.wxf.util.JpgFile.APP0;
import com.wxf.util.JpgFile.DHT;
import com.wxf.util.JpgFile.DQT;
import com.wxf.util.JpgFile.SOF0;
import com.wxf.util.JpgFile.SOS;
import com.wxf.util.JpgFile.SelectionType;
import com.wxf.util.JpgFile.DQT.QT;

public class DecodeJPG {

	public static void decode(String fileName) throws Exception {
		// 获取图片对应的字节数组
		byte[] buffer = ReadJPG.FromJPGToByte(fileName);
		System.out.println(buffer.length);
		// 构建字节数组输入流来读取字节数组中的内容
		ByteArrayInputStream bias = new ByteArrayInputStream(buffer);
		if (bias.available() < 2) {
			System.out.println("File length is too small");
			return;
		}
		// 首先读取该字节数组的前两个字节
		SelectionType selectionType = JpgFile.getSelectionType(bias.read(),
				bias.read());
		if (selectionType != SelectionType.SOI) {
			throw new RuntimeException("the picture is not a normal jpg file!");
		}
		boolean flag = true;
		while (bias.available() > 0 && flag) {
			int a=bias.read();
			int b=bias.read();
			SelectionType selectionType2 = JpgFile.getSelectionType(
					a, b);
			if (selectionType2 == null) {
				return;
			}
			switch (selectionType2) {
			case APP0:
				// JpgFile.JumpOverSection(bias, selectionType2);
				JpgFile.ReadAPP0(bias);
				break;
			case COM:
				JpgFile.JumpOverSection(bias, selectionType2);
				break;
			case DHT:
				// JpgFile.JumpOverSection(bias, selectionType2);
				JpgFile.ReadDHT(bias);
				break;
			case DRI:
				JpgFile.JumpOverSection(bias, selectionType2);
				break;
			case DQT:
				JpgFile.ReadDQT(bias);
				break;
			case EOI:
				// JpgFile.JumpOverSection(bias, selectionType2);
				flag = false;
				decodeDQT();
				break;
			case SOFO:
				// JpgFile.JumpOverSection(bias, selectionType2);
				JpgFile.ReadSOF0(bias);
				break;
			case SOS:
				// JpgFile.JumpOverSection(bias, selectionType2);
				JpgFile.ReadSOS(bias);
				break;
			default:
				JpgFile.JumpOverSection(bias, selectionType2);
				break;
			}
		}
	}

	/**
	 * 解析DQT的数据
	 */
	public static void decodeDQT() {
		// 取得总的DQT段。一般为两段为Y值（亮度）定义一个，为C值（色度）定义一个。
		ArrayList<DQT> list = JpgFile.dqtList;
		int n = 0;
		for (DQT dqt : list) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("第" + (++n) + "段DQT数据如下：" + "\r\n");
			int info = dqt.qtInfo;
			int infoL = info & 0xf;
			buffer.append("QT号为：" + infoL + "\r\n");
			if (((info << 4) & 0xf) == 0)
				buffer.append("QT的精度为：8bit，1个字节\r\n");
			else {
				buffer.append("QT的精度为：16bit，2个字节\r\n");
			}
			// 获取每个dqt中包含的QT集合
			ArrayList<QT> list2 = dqt.arrQt;
			for (int i = 0; i < list2.size(); i++) {
				System.out.println("dq的信息：");
				// System.out.println(Tool.Bytes2HexString(list2.get(i).qArray));
				String data = Tool.Bytes2HexString(list2.get(i).qArray);
				// System.out.println(data);
				String result = "QT的段内容：\r\n";
				for (int j = 0; j < data.length(); j = j + 2) {
					if (j % 16 == 0 && j > 0) {
						// System.out.println();
						result += "\r\n";
					}
					// System.out.print(data.substring(i,i+2)+"   ");
					result += data.substring(j, j + 2) + "\t";
				}
				buffer.append(result + "\r\n");
				System.out.println(buffer.toString());
			}
			OutputInfoToFile(buffer);
		}
	}

	/**
	 * 解析app0的数据
	 */
	public static void decodeAPP0() {
		StringBuffer buffer = new StringBuffer();
		APP0 app0 = JpgFile.app0;
		System.out.println("-----------"
				+ Tool.Bytes2HexString(app0.changeStyle));
		if (Tool.Bytes2HexString(app0.changeStyle).equals("4A46494600"))
			buffer.append("该图片的交换格式为：" + "JFIF 的ASCII码\r\n");
		else {
			buffer.append("该图片的交换格式为：" + "无法辨别\r\n");
		}
		buffer.append("主次版本号：" + app0.hostVersion + "." + app0.SecondVersion
				+ "\r\n");
		String unit = "";
		switch (app0.densityUnit) {
		case 0:
			unit += "无单位\r\n";
			break;
		case 1:
			unit += "点数/英寸\r\n";
			break;
		case 2:
			unit += "点数/厘米\r\n";
			break;
		}
		buffer.append("密度单位为：" + unit);
		int Xdensity = ((app0.XpxDensity[0] << 8) + app0.XpxDensity[1]) & 0xffff;
		int Ydensity = ((app0.YpxDensity[0] << 8) + app0.YpxDensity[1]) & 0xffff;
		System.out.println(Tool.Bytes2HexString(app0.XpxDensity));
		System.out.println(Tool.Bytes2HexString(app0.YpxDensity));
		buffer.append("水平方向的密度为：" + Xdensity + unit);
		buffer.append("垂直方向的密度为：" + Ydensity + unit);
		if (app0.XZoom == 0)
			buffer.append("水平方向没有缩略图\r\n");
		if (app0.YZoom == 0)
			buffer.append("垂直方向没有缩略图\r\n");
		OutputInfoToFile(buffer);
		System.out.println(buffer.toString());
	}

	/**
	 * 解析SOF0段的数据，并将其保存到文件中
	 */
	public static void decodeSOF0() {

		StringBuffer buffer = new StringBuffer();
		buffer.append("以下是SOF0段的数据信息：\r\n");
		SOF0 sof0 = JpgFile.sof0;
		int samplePrecision = sof0.samplePrecision & 0xff;
		buffer.append("该照片的样本精度为：" + samplePrecision + "bit\r\n");
		// 以下对高度和宽度的高字节进行判断，如果高字节为0则不用将高字节左移8位，否则需要左移然后再做&运算
		int height = (sof0.height[0] << 8) == 0 ? (sof0.height[1]) & 0xffff
				: ((sof0.height[0] << 8) + sof0.height[1]) & 0xffff;
		int width = (sof0.width[0] == 0) ? (sof0.width[1]) & 0xff
				: ((sof0.width[0] << 8) + sof0.width[1]) & 0xff;
		buffer.append("图片的高度为：" + height + "," + "图片的宽度为：" + width + "\r\n");
		ArrayList<Jcomponent> list = sof0.component;
		// 待处理的代码

		for (int i = 0; i < list.size(); i++) {
			// 组件的数量为3时，表示为：YCbCr/YIQ彩色图
			// Y表示量度，Cr表示红色分量，Cb表示蓝色分量
			// 组件的id有三个
			// 1表示Y,2表示Cb，3表示Cr，4表示I,5表示Q
			Jcomponent com = list.get(i);
			// 水平采样系数,也可以表示为逐点采样或者每两个点采样一次等，
			int Xfactor = (com.factor >> 4) & 0xf;
			// 垂直采样系数
			int Yfactor = com.factor & 0xf;
			// 量化的表号
			int tableNo = com.tableNo & 0xff;
			switch (com.id) {
			case 1:
				buffer.append("亮度的水平采样系数为：" + Xfactor + "\r\n");
				buffer.append("亮度的垂直采样系数为：" + Yfactor + "\r\n");
				buffer.append("亮度使用的是QT=" + tableNo + "的量化表" + "\r\n");
				break;
			case 2:
				buffer.append("红色分量的水平采样系数为：" + Xfactor + "\r\n");
				buffer.append("红色分量的垂直采样系数为：" + Yfactor + "\r\n");
				buffer.append("红色分量使用的是QT=" + tableNo + "的量化表" + "\r\n");
				break;
			case 3:
				buffer.append("蓝色分量的水平采样系数为：" + Xfactor + "\r\n");
				buffer.append("蓝色分量的垂直采样系数为：" + Yfactor + "\r\n");
				buffer.append("蓝色分量使用的是QT=" + tableNo + "的量化表" + "\r\n");
				break;
			}
		}
		System.out.println(buffer.toString());
		OutputInfoToFile(buffer);
	}

	/**
	 * 解析DHT段的数据
	 */
	public static void decodeDHT() {
		String title = "以下是对DHT段，定义霍夫曼表的数据的解析";
		System.out.println(title);
		OutputInfoToFile(new StringBuffer(title));
		// 获取dht段的数据集合
		ArrayList<DHT> list = JpgFile.dhtList;
		// 从list集合中读取DHT霍夫曼编码的数据
		for (int i = 0; i < list.size(); i++) {
			StringBuffer buffer = new StringBuffer();
			DHT dht = list.get(i);
			int htNo = dht.htInfo & 0xf;
			int htType = dht.htInfo >> 4 & 0x1;
			String type = (htType == 0) ? "DC表" : "AC表";
			buffer.append("第" + (i + 1) + "个霍夫曼表数据：\r\n");
			buffer.append("HT号为：" + htNo + "该表的类型是：" + type + "\r\n");
			buffer.append("哈弗曼的位表如下：\r\n");
			// 首先将哈弗曼的位表的数据转换为字符串，然后再进行输出
			String hfmUnitTable = Tool.Bytes2HexString(dht.htUnitTable);
			String str = "";
			for (int k = 0; k < hfmUnitTable.length(); k = k + 2) {
				if (k % 16 == 0 && k > 0) {
					str += "\r\n";
				}
				str += hfmUnitTable.substring(k, k + 2) + "\t";

			}
			buffer.append(str + "\r\n");
			buffer.append("HT的值表为数据如下：\r\n");
			// 将霍夫曼的码表进行格式化输出
			String data = Tool.Bytes2HexString(dht.htValueTable);
			// System.out.println(data);
			String result = "";
			for (int j = 0; j < data.length(); j = j + 2) {
				if (j % 12 == 0 && j > 0) {
					// System.out.println();
					result += "\r\n";
				}
				// System.out.print(data.substring(i,i+2)+"   ");
				result += data.substring(j, j + 2) + "\t";
			}
			buffer.append(result + "\r\n");
			System.out.println(buffer.toString());
			// 将数据写入到文件中
			OutputInfoToFile(buffer);
		}
	}

	/**
	 * 解析SOS段的数据
	 */
	public static void decodeSOS() {
		StringBuffer buffer = new StringBuffer("以下是对SOS扫描行段的信息：\r\n");
		SOS sos = JpgFile.sos;
		int componentNum = sos.componentNum;
		buffer.append("扫描的组件数为：" + componentNum + "\r\n");
		ArrayList<SOSComponent> list = sos.sosComponents;
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			SOSComponent component = list.get(i);
			int id = component.id;
			int humTableDC = (component.humTableNO >> 4) & 0xf;
			int humTableAC = component.humTableNO & 0xf;

			switch (id) {
			case 1:
				buffer.append("亮度对应的霍夫曼码AC表号为：" + humTableAC + "\r\n");
				buffer.append("亮度对应的霍夫曼码AC表号为：" + humTableDC + "\r\n");
				break;
			case 2:
				buffer.append("红色分量对应的霍夫曼码AC表号为：" + humTableAC + "\r\n");
				buffer.append("红色分量对应的霍夫曼码AC表号为：" + humTableDC + "\r\n");
				break;

			case 3:
				buffer.append("蓝色分量对应的霍夫曼码AC表号为：" + humTableAC + "\r\n");
				buffer.append("蓝色分量对应的霍夫曼码AC表号为：" + humTableDC + "\r\n");
				break;
			}
		}
		System.out.println(buffer.toString());
		OutputInfoToFile(buffer);
	}

	/**
	 * 将解析出来的信息保存到文件中，默认是追加保存
	 * 
	 * @param buffer
	 */
	private static void OutputInfoToFile(StringBuffer buffer) {
		FileOutputStream os = null;
		try {
			// 构建输出流,如果要进行给原始的文件进行追加数据时。可以指定FileOUtputstream的第二个参数为true
			os = new FileOutputStream(new File(JpgFile.JPGFILEINFO), true);
			// os = new FileOutputStream(new File(JpgFile.JPGFILEINFO));
			os.write(buffer.toString().getBytes());
			os.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * public static void EnhanceQuantizationTable(ArrayList<DQT>dqts){ for(DQT
	 * dqt:dqts){ ArrayList<QT> list = dqt.arrQt; // 对list进行遍历,里面存放的是 for (int i
	 * = 0; i < list.size(); i++) { // 取得对应的每个量化表中的存放数据的数组 byte[] qtArr =
	 * list.get(i).qArray; // 创建整形数组存放数据，方便后面进行对量化表中的数据，进行增强 int[] qtTable = new
	 * int[qtArr.length]; for(int j=0;j<qtArr.length;j++){ qtTable[j]=qtArr[j];
	 * } } } }
	 */
}
