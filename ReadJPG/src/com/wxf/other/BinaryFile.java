package com.wxf.other;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.text.AbstractDocument.BranchElement;

import org.omg.PortableServer.AdapterActivator;

public class BinaryFile {

	static byte[] read(File bFile) throws IOException {

		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(
				bFile));
		ByteArrayOutputStream oStream=new ByteArrayOutputStream();
		

		try {
			byte[] data = new byte[bf.available()];
			/*int len = 0;
			while ((len = bf.read(data)) != -1) {
				oStream.write(data, 0, data.length);
			}
			oStream.flush();*/
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}
	}

	static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}

}