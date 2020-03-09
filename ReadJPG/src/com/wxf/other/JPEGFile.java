package com.wxf.other;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JPEGFile {
	enum SectionType {
		TEM, SOF0, SOF1, SOF2, SOF3, SOF5, SOF6, SOF7, SOF9, SOF10, SOF11, SOF13, SOF14, SOF15, DHT, JPG, DAC, RST0, RST1, RST2, RST3, RST4, RST5, RST6, RST7, SOI, EOI, SOS, DQT, DNL, DRI, DHP, EXP, APP0, APP15, JPG0, JPG13, COM, NOP,
	};

	static HashMap<Integer, SectionType> m_mapSectionType = new HashMap<Integer, SectionType>();
	static {
		m_mapSectionType.put(0x01, SectionType.TEM);
		m_mapSectionType.put(0xc0, SectionType.SOF0);
		m_mapSectionType.put(0xc1, SectionType.SOF1);
		m_mapSectionType.put(0xc2, SectionType.SOF2);
		m_mapSectionType.put(0xc3, SectionType.SOF3);
		m_mapSectionType.put(0xc5, SectionType.SOF5);
		m_mapSectionType.put(0xc6, SectionType.SOF6);
		m_mapSectionType.put(0xc7, SectionType.SOF7);
		m_mapSectionType.put(0xc9, SectionType.SOF9);
		m_mapSectionType.put(0xca, SectionType.SOF10);
		m_mapSectionType.put(0xcb, SectionType.SOF11);
		m_mapSectionType.put(0xcd, SectionType.SOF13);
		m_mapSectionType.put(0xce, SectionType.SOF14);
		m_mapSectionType.put(0xcf, SectionType.SOF15);
		m_mapSectionType.put(0xc4, SectionType.DHT);
		m_mapSectionType.put(0xc8, SectionType.JPG);
		m_mapSectionType.put(0xcc, SectionType.DAC);
		m_mapSectionType.put(0xd0, SectionType.RST0);
		m_mapSectionType.put(0xd1, SectionType.RST1);
		m_mapSectionType.put(0xd2, SectionType.RST2);
		m_mapSectionType.put(0xd3, SectionType.RST3);
		m_mapSectionType.put(0xd4, SectionType.RST4);
		m_mapSectionType.put(0xd5, SectionType.RST5);
		m_mapSectionType.put(0xd6, SectionType.RST6);
		m_mapSectionType.put(0xd7, SectionType.RST7);
		m_mapSectionType.put(0xd8, SectionType.SOI);
		m_mapSectionType.put(0xd9, SectionType.EOI);
		m_mapSectionType.put(0xda, SectionType.SOS);
		m_mapSectionType.put(0xdb, SectionType.DQT);
		m_mapSectionType.put(0xdc, SectionType.DNL);
		m_mapSectionType.put(0xdd, SectionType.DRI);
		m_mapSectionType.put(0xde, SectionType.DHP);
		m_mapSectionType.put(0xdf, SectionType.EXP);
		m_mapSectionType.put(0xe0, SectionType.APP0);
		m_mapSectionType.put(0xef, SectionType.APP15);
		m_mapSectionType.put(0xf0, SectionType.JPG0);
		m_mapSectionType.put(0xfd, SectionType.JPG13);
		m_mapSectionType.put(0xfe, SectionType.COM);
		m_mapSectionType.put(0xff, SectionType.NOP);
	}

	enum ERROR_CODE {
		ERR_OK, ERR_NOT_JEPG_FILE,
	};

	// SOF0: Start Of Frame 0:
	class JSOF0 {
		byte m_byPrecision;
		byte m_byHeight;
		byte m_byWidth;
		byte m_byComponentNum;

		class JComponent {
			byte m_byId;
			byte m_byFactor;
			byte m_byQTId;
		}

		ArrayList<JComponent> m_arrComponents = new ArrayList<JComponent>();
	}

	JSOF0 m_oSOF0 = new JSOF0();

	// DQT: Define Quantization Table:

	class JDQT {
		byte m_byQTInfo;

		class JQT {
			byte[] m_arrByte = new byte[64];
		}

		ArrayList<JQT> m_arrQT = new ArrayList<JQT>();

		void ReadQTArray(ByteArrayInputStream bais, int n) throws IOException {
			for (int i = 0; i < n + 1; ++i) {
				JQT qt = new JQT();
				bais.read(qt.m_arrByte);
				m_arrQT.add(qt);
			}
		}
	}

	ArrayList<JDQT> m_arrDQT = new ArrayList<JDQT>();

	SectionType GetSectionType(int iFirstByte, int iSecondByte) {
		iFirstByte = iFirstByte & 0xff;
		iSecondByte = iSecondByte & 0xff;
		System.out.println("GetSectionType(" + iFirstByte + ", " + iSecondByte
				+ ")");
		if (0xff != iFirstByte)
			return SectionType.NOP;
		if (!m_mapSectionType.containsKey(iSecondByte))
			return SectionType.NOP;
		return m_mapSectionType.get(iSecondByte);
	}
	
	
	
	

	int GetSectionLen(int iByteHigh, int iByteLow) {
		System.out
				.println("GetSectionLen(" + iByteHigh + ", " + iByteLow + ")");
		int iBlockLen = ((iByteHigh << 8) + iByteLow) & 0xffff;
		System.out.println("Section length: " + iBlockLen + " bytes");
		return iBlockLen;
	}

	void JumpOverSection(ByteArrayInputStream bais, SectionType eSectionType) {
		System.out.println("Section[" + eSectionType + "] jump over");
		int iBlockLen = GetSectionLen(bais.read(), bais.read());
		bais.skip(iBlockLen - 2);
	}

	void ReadDQT(ByteArrayInputStream bais) throws IOException {
		System.out.println("----------Section[DQT]----------");
		System.out.println("Section[DQT] reading ...");
		int iBlockLen = GetSectionLen(bais.read(), bais.read());
		JDQT dqt = new JDQT();
		dqt.m_byQTInfo = (byte) bais.read();
		// check validation
		// QT
		int n = (dqt.m_byQTInfo >> 4) & 0xf;
		dqt.ReadQTArray(bais, n);
		m_arrDQT.add(dqt);
		System.out
				.println("Section[DQT] read " + (64 * (n + 1) + 1) + " bytes");
		System.out.println("");
	} //

	ERROR_CODE readFromFile(String sFilename) {
		try {
			byte[] byteArr = BinaryFile.read(sFilename);

			ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);

			if (bais.available() < 2) {

				System.out.println("File length is too small");

				return ERROR_CODE.ERR_OK;
			}
			// JPEG header
			SectionType eSectionType = GetSectionType(bais.read(), bais.read());
			if (eSectionType != SectionType.SOI) {
				System.out.println("File is not JPEG File");
				return ERROR_CODE.ERR_NOT_JEPG_FILE;
			} // Iterate Section
			System.out.println("File is JPEGFile");
			System.out.println("Start iterating sections ...");
			Boolean bStartScanning = false;
			while (bais.available() > 0) {
				int iByteHigh = bais.read();
				int iByteLow = bais.read();
				if (bStartScanning) {
					continue;
				}
				eSectionType = GetSectionType(iByteHigh, iByteLow);
				switch (eSectionType) {
				case NOP:
					continue;
				case SOF0:
					JumpOverSection(bais, eSectionType);
					break;
				case SOF2:
					JumpOverSection(bais, eSectionType);
					break;
				case DRI:
					JumpOverSection(bais, eSectionType);
					break;
				case DQT:
					ReadDQT(bais);
					break;
				case DHT:
					JumpOverSection(bais, eSectionType);
					break;
				case COM:
					JumpOverSection(bais, eSectionType);
					break;
				case SOS:
					bStartScanning = true;
					JumpOverSection(bais, eSectionType);
					break;
				default:
					JumpOverSection(bais, eSectionType);
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return ERROR_CODE.ERR_OK;
	}
}
/* </jdqt></jdqt></jqt></jqt></jcomponent></jcomponent></integer,></integer,> */
