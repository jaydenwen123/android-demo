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
		// ��ȡͼƬ��Ӧ���ֽ�����
		byte[] buffer = ReadJPG.FromJPGToByte(fileName);
		System.out.println(buffer.length);
		// �����ֽ���������������ȡ�ֽ������е�����
		ByteArrayInputStream bias = new ByteArrayInputStream(buffer);
		if (bias.available() < 2) {
			System.out.println("File length is too small");
			return;
		}
		// ���ȶ�ȡ���ֽ������ǰ�����ֽ�
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
	 * ����DQT������
	 */
	public static void decodeDQT() {
		// ȡ���ܵ�DQT�Ρ�һ��Ϊ����ΪYֵ�����ȣ�����һ����ΪCֵ��ɫ�ȣ�����һ����
		ArrayList<DQT> list = JpgFile.dqtList;
		int n = 0;
		for (DQT dqt : list) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("��" + (++n) + "��DQT�������£�" + "\r\n");
			int info = dqt.qtInfo;
			int infoL = info & 0xf;
			buffer.append("QT��Ϊ��" + infoL + "\r\n");
			if (((info << 4) & 0xf) == 0)
				buffer.append("QT�ľ���Ϊ��8bit��1���ֽ�\r\n");
			else {
				buffer.append("QT�ľ���Ϊ��16bit��2���ֽ�\r\n");
			}
			// ��ȡÿ��dqt�а�����QT����
			ArrayList<QT> list2 = dqt.arrQt;
			for (int i = 0; i < list2.size(); i++) {
				System.out.println("dq����Ϣ��");
				// System.out.println(Tool.Bytes2HexString(list2.get(i).qArray));
				String data = Tool.Bytes2HexString(list2.get(i).qArray);
				// System.out.println(data);
				String result = "QT�Ķ����ݣ�\r\n";
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
	 * ����app0������
	 */
	public static void decodeAPP0() {
		StringBuffer buffer = new StringBuffer();
		APP0 app0 = JpgFile.app0;
		System.out.println("-----------"
				+ Tool.Bytes2HexString(app0.changeStyle));
		if (Tool.Bytes2HexString(app0.changeStyle).equals("4A46494600"))
			buffer.append("��ͼƬ�Ľ�����ʽΪ��" + "JFIF ��ASCII��\r\n");
		else {
			buffer.append("��ͼƬ�Ľ�����ʽΪ��" + "�޷����\r\n");
		}
		buffer.append("���ΰ汾�ţ�" + app0.hostVersion + "." + app0.SecondVersion
				+ "\r\n");
		String unit = "";
		switch (app0.densityUnit) {
		case 0:
			unit += "�޵�λ\r\n";
			break;
		case 1:
			unit += "����/Ӣ��\r\n";
			break;
		case 2:
			unit += "����/����\r\n";
			break;
		}
		buffer.append("�ܶȵ�λΪ��" + unit);
		int Xdensity = ((app0.XpxDensity[0] << 8) + app0.XpxDensity[1]) & 0xffff;
		int Ydensity = ((app0.YpxDensity[0] << 8) + app0.YpxDensity[1]) & 0xffff;
		System.out.println(Tool.Bytes2HexString(app0.XpxDensity));
		System.out.println(Tool.Bytes2HexString(app0.YpxDensity));
		buffer.append("ˮƽ������ܶ�Ϊ��" + Xdensity + unit);
		buffer.append("��ֱ������ܶ�Ϊ��" + Ydensity + unit);
		if (app0.XZoom == 0)
			buffer.append("ˮƽ����û������ͼ\r\n");
		if (app0.YZoom == 0)
			buffer.append("��ֱ����û������ͼ\r\n");
		OutputInfoToFile(buffer);
		System.out.println(buffer.toString());
	}

	/**
	 * ����SOF0�ε����ݣ������䱣�浽�ļ���
	 */
	public static void decodeSOF0() {

		StringBuffer buffer = new StringBuffer();
		buffer.append("������SOF0�ε�������Ϣ��\r\n");
		SOF0 sof0 = JpgFile.sof0;
		int samplePrecision = sof0.samplePrecision & 0xff;
		buffer.append("����Ƭ����������Ϊ��" + samplePrecision + "bit\r\n");
		// ���¶Ը߶ȺͿ�ȵĸ��ֽڽ����жϣ�������ֽ�Ϊ0���ý����ֽ�����8λ��������Ҫ����Ȼ������&����
		int height = (sof0.height[0] << 8) == 0 ? (sof0.height[1]) & 0xffff
				: ((sof0.height[0] << 8) + sof0.height[1]) & 0xffff;
		int width = (sof0.width[0] == 0) ? (sof0.width[1]) & 0xff
				: ((sof0.width[0] << 8) + sof0.width[1]) & 0xff;
		buffer.append("ͼƬ�ĸ߶�Ϊ��" + height + "," + "ͼƬ�Ŀ��Ϊ��" + width + "\r\n");
		ArrayList<Jcomponent> list = sof0.component;
		// ������Ĵ���

		for (int i = 0; i < list.size(); i++) {
			// ���������Ϊ3ʱ����ʾΪ��YCbCr/YIQ��ɫͼ
			// Y��ʾ���ȣ�Cr��ʾ��ɫ������Cb��ʾ��ɫ����
			// �����id������
			// 1��ʾY,2��ʾCb��3��ʾCr��4��ʾI,5��ʾQ
			Jcomponent com = list.get(i);
			// ˮƽ����ϵ��,Ҳ���Ա�ʾΪ����������ÿ���������һ�εȣ�
			int Xfactor = (com.factor >> 4) & 0xf;
			// ��ֱ����ϵ��
			int Yfactor = com.factor & 0xf;
			// �����ı��
			int tableNo = com.tableNo & 0xff;
			switch (com.id) {
			case 1:
				buffer.append("���ȵ�ˮƽ����ϵ��Ϊ��" + Xfactor + "\r\n");
				buffer.append("���ȵĴ�ֱ����ϵ��Ϊ��" + Yfactor + "\r\n");
				buffer.append("����ʹ�õ���QT=" + tableNo + "��������" + "\r\n");
				break;
			case 2:
				buffer.append("��ɫ������ˮƽ����ϵ��Ϊ��" + Xfactor + "\r\n");
				buffer.append("��ɫ�����Ĵ�ֱ����ϵ��Ϊ��" + Yfactor + "\r\n");
				buffer.append("��ɫ����ʹ�õ���QT=" + tableNo + "��������" + "\r\n");
				break;
			case 3:
				buffer.append("��ɫ������ˮƽ����ϵ��Ϊ��" + Xfactor + "\r\n");
				buffer.append("��ɫ�����Ĵ�ֱ����ϵ��Ϊ��" + Yfactor + "\r\n");
				buffer.append("��ɫ����ʹ�õ���QT=" + tableNo + "��������" + "\r\n");
				break;
			}
		}
		System.out.println(buffer.toString());
		OutputInfoToFile(buffer);
	}

	/**
	 * ����DHT�ε�����
	 */
	public static void decodeDHT() {
		String title = "�����Ƕ�DHT�Σ����������������ݵĽ���";
		System.out.println(title);
		OutputInfoToFile(new StringBuffer(title));
		// ��ȡdht�ε����ݼ���
		ArrayList<DHT> list = JpgFile.dhtList;
		// ��list�����ж�ȡDHT���������������
		for (int i = 0; i < list.size(); i++) {
			StringBuffer buffer = new StringBuffer();
			DHT dht = list.get(i);
			int htNo = dht.htInfo & 0xf;
			int htType = dht.htInfo >> 4 & 0x1;
			String type = (htType == 0) ? "DC��" : "AC��";
			buffer.append("��" + (i + 1) + "�������������ݣ�\r\n");
			buffer.append("HT��Ϊ��" + htNo + "�ñ�������ǣ�" + type + "\r\n");
			buffer.append("��������λ�����£�\r\n");
			// ���Ƚ���������λ�������ת��Ϊ�ַ�����Ȼ���ٽ������
			String hfmUnitTable = Tool.Bytes2HexString(dht.htUnitTable);
			String str = "";
			for (int k = 0; k < hfmUnitTable.length(); k = k + 2) {
				if (k % 16 == 0 && k > 0) {
					str += "\r\n";
				}
				str += hfmUnitTable.substring(k, k + 2) + "\t";

			}
			buffer.append(str + "\r\n");
			buffer.append("HT��ֵ��Ϊ�������£�\r\n");
			// ���������������и�ʽ�����
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
			// ������д�뵽�ļ���
			OutputInfoToFile(buffer);
		}
	}

	/**
	 * ����SOS�ε�����
	 */
	public static void decodeSOS() {
		StringBuffer buffer = new StringBuffer("�����Ƕ�SOSɨ���жε���Ϣ��\r\n");
		SOS sos = JpgFile.sos;
		int componentNum = sos.componentNum;
		buffer.append("ɨ��������Ϊ��" + componentNum + "\r\n");
		ArrayList<SOSComponent> list = sos.sosComponents;
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			SOSComponent component = list.get(i);
			int id = component.id;
			int humTableDC = (component.humTableNO >> 4) & 0xf;
			int humTableAC = component.humTableNO & 0xf;

			switch (id) {
			case 1:
				buffer.append("���ȶ�Ӧ�Ļ�������AC���Ϊ��" + humTableAC + "\r\n");
				buffer.append("���ȶ�Ӧ�Ļ�������AC���Ϊ��" + humTableDC + "\r\n");
				break;
			case 2:
				buffer.append("��ɫ������Ӧ�Ļ�������AC���Ϊ��" + humTableAC + "\r\n");
				buffer.append("��ɫ������Ӧ�Ļ�������AC���Ϊ��" + humTableDC + "\r\n");
				break;

			case 3:
				buffer.append("��ɫ������Ӧ�Ļ�������AC���Ϊ��" + humTableAC + "\r\n");
				buffer.append("��ɫ������Ӧ�Ļ�������AC���Ϊ��" + humTableDC + "\r\n");
				break;
			}
		}
		System.out.println(buffer.toString());
		OutputInfoToFile(buffer);
	}

	/**
	 * ��������������Ϣ���浽�ļ��У�Ĭ����׷�ӱ���
	 * 
	 * @param buffer
	 */
	private static void OutputInfoToFile(StringBuffer buffer) {
		FileOutputStream os = null;
		try {
			// ���������,���Ҫ���и�ԭʼ���ļ�����׷������ʱ������ָ��FileOUtputstream�ĵڶ�������Ϊtrue
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
	 * dqt:dqts){ ArrayList<QT> list = dqt.arrQt; // ��list���б���,�����ŵ��� for (int i
	 * = 0; i < list.size(); i++) { // ȡ�ö�Ӧ��ÿ���������еĴ�����ݵ����� byte[] qtArr =
	 * list.get(i).qArray; // �����������������ݣ����������ж��������е����ݣ�������ǿ int[] qtTable = new
	 * int[qtArr.length]; for(int j=0;j<qtArr.length;j++){ qtTable[j]=qtArr[j];
	 * } } } }
	 */
}
