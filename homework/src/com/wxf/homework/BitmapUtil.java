package com.wxf.homework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.R.color;
import android.R.integer;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.os.Environment;

public class BitmapUtil {

	public static final int GRAY1 = 1;
	public static final int GRAY2 = 2;
	public static final int GRAY3 = 3;

	public static Bitmap decodeBitmap(Resources resources, int id,
			int reqWidth, int reqHeight) {
		Bitmap bitmap = null;
		BitmapFactory.Options opts = new Options();
		// 将该属性设置为true时，可以系统不会分配空间来解析位图，但是可以任然可以获取图片的原始的高度和宽度。
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resources, id, opts);
		// 设置对图片的压缩
		opts.inSampleSize = getSampleSize(opts, reqWidth, reqHeight);
		// 为bitmap分配空间，
		opts.inJustDecodeBounds = false;
		// System.out.println("reqWidth:" + reqWidth + ",reqHeight:" +
		// reqHeight);
		bitmap = BitmapFactory.decodeResource(resources, id, opts);
		/*
		 * System.out.println("actual width:" + bitmap.getWidth() + ",height:" +
		 * bitmap.getHeight());
		 */

		// 获取图片的实际的宽度和高度

		/*
		 * int width = bitmap.getWidth(); int height = bitmap.getHeight();
		 * int[][] pix = new int[width][height]; for (int i = 0; i < width; i++)
		 * { for (int j = 0; j < height; j++) { pix[i][j] = bitmap.getPixel(i,
		 * j); System.out.print(pix[i][j] + " "); } System.out.println(); }
		 */

		// saveBmp(bitmap,"hello.jpg");
		return bitmap;
	}

	/**
	 * @param bitmap
	 * @param fileName
	 *            将bitmap位图保存为图片。
	 */
	public static void saveBmp(Bitmap bitmap, String fileName) {
		// ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		File file = new File(Environment.getExternalStorageDirectory() + "/"
				+ fileName);
		FileOutputStream outputStream = null;
		try {
			System.out.println("filename:" + file.getAbsolutePath());
			outputStream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 50, outputStream);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param bitmap
	 * @param fileName
	 *            将bitmap位图保存为图片。
	 */
	public static void saveBmp(Bitmap bitmap, String fileName, int quality) {
		// ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		File file = new File(Environment.getExternalStorageDirectory() + "/"
				+ fileName);
		FileOutputStream outputStream = null;
		try {
			System.out.println("filename:" + file.getAbsolutePath());
			outputStream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, quality, outputStream);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param bmp
	 * @param option
	 * @return 分别对应不通的灰度级返回对应的bitmap对象
	 */
	public static Bitmap gray(Bitmap bmp, int option) {
		Bitmap bitmap = null;
		bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				bmp.getConfig());
		for (int i = 0; i < bmp.getWidth(); i++) {
			for (int j = 0; j < bmp.getHeight(); j++) {
				int pixel = bmp.getPixel(i, j);
				int red = Color.red(pixel);
				int green = Color.green(pixel);
				int blue = Color.blue(pixel);
				int alpha = 0;
				switch (option) {
				case GRAY1:
					alpha = (Math.max(Math.max(red, blue), green) + Math.min(
							green, Math.min(red, blue))) / 2;
					break;
				case GRAY2:
					alpha = (red + blue + green) / 3;
					break;
				case GRAY3:
					alpha = (int) (red * 0.30 + blue * 0.11 + green * 0.59);
					break;

				}
				bitmap.setPixel(i, j, Color.argb(alpha, red, green, blue));
			}
		}
		return bitmap;
	}

	/**
	 * @param bmp
	 * @param depth
	 *            改变图片的量度
	 * @return
	 */
	public static Bitmap light(Bitmap bmp, double depth) {

		Bitmap bitmap = null;
		bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				bmp.getConfig());
		int height = bmp.getHeight();
		int width = bmp.getWidth();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = bmp.getPixel(i, j);
				int red = Color.red(pixel);
				int green = Color.green(pixel);
				int blue = Color.blue(pixel);
				int alpha = Color.alpha(pixel);
				int gray = (int) ((green * 0.59 + red * 0.3 + blue * 0.11) / 3);
				red = (int) ((red + depth * gray) > 255 ? 255 : red + depth
						* gray);
				green = (int) ((green + depth * gray) > 255 ? 255 : green
						+ depth * gray);
				blue = (int) ((blue + depth * gray) > 255 ? 255 : blue + depth
						* gray);
				bitmap.setPixel(i, j, Color.argb(alpha, red, green, blue));
			}
		}
		return bitmap;
	}

	/**
	 * @param bmp
	 * @return 显示bitmap的镜像 相当于对bitmap进行镜子一样，反射，左右的交换
	 */
	public static Bitmap flip(Bitmap bmp) {
		Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				bmp.getConfig());
		int height = bmp.getHeight();
		int width = bmp.getWidth();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = bmp.getPixel(i, j);
				int red = Color.red(pixel);
				int green = Color.green(pixel);
				int blue = Color.blue(pixel);
				int alpha = Color.alpha(pixel);
				int gray = (int) ((green * 0.59 + red * 0.3 + blue * 0.11) / 3);
				bitmap.setPixel(width - i - 1, j,
						Color.argb(alpha, red, green, blue));
			}
		}
		return bitmap;
	}

	/**
	 * @param opts
	 * @param reqWidth
	 * @param reqHeight
	 * @return 修改bitmap的insampleSize属性
	 */
	private static int getSampleSize(Options opts, int reqWidth, int reqHeight) {
		// TODO Auto-generated method stub
		// 获取原始的相片的高度
		int height = opts.outHeight;
		// 获取原始相片的宽度
		int width = opts.outWidth;
		// 获取原始相片的类型
		String type = opts.outMimeType;
		/*
		 * System.out.println("height:" + height + "|| width:" + width +
		 * "|| type:" + type);
		 */
		int inSampleSize = 1;
		int heightRatio = 0;
		int widthRatio = 0;
		// 对其进行判断，如果超过该要求的宽度则记性压缩

		if (height > reqHeight || width > reqWidth) {
			heightRatio = Math.round((float) height / (float) reqHeight);
			widthRatio = Math.round((float) width / (float) reqWidth);
		}
		inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;

		/*
		 * if (height > reqHeight || width > reqWidth) { final int halfHeight =
		 * height / 2; final int halfWidth = width / 2; while ((halfHeight /
		 * inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
		 * inSampleSize*=2; } }
		 */
		// System.out.println("imSampleSize:" + inSampleSize);
		return inSampleSize;
	}

}
