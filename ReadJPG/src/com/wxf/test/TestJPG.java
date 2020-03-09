package com.wxf.test;

import com.wxf.decode.DecodeJPG;
import com.wxf.read.ReadJPG;
import com.wxf.util.JpgFile;

public class TestJPG {

	public static void main(String[] args) {
		try {
			//DecodeJPG.decode(JpgFile.READFILENAME);
			ReadJPG.OutputJPGToBinaryFile("C:\\Users\\Administrator\\Desktop\\200px-50.jpg", "D:\\data.txt");
//			System.out.println("****************");
//			DecodeJPG.decodeAPP0();
//			System.out.println("*******************");
//			DecodeJPG.decodeDQT();
//			System.out.println("********************");
//			DecodeJPG.decodeSOF0();
//			System.out.println("********************");
//			DecodeJPG.decodeDHT();
//			System.out.println("********************");
//			DecodeJPG.decodeSOS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
