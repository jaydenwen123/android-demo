package com.wxf.trans;

/**
 * ͼ��ı任
 * @author luoweifu
 *
 */
public class Transformation {
	/**
	 * Ҫ����DCT�任��ͼƬ�Ŀ���
	 */
	public static final int N = 256;
	
	/**
	 * ����Ҷ�任
	 * @return
	 */
	public int[] FFT() {
		
		return null;
	}
	
	/**
	 * ��ɢ���ұ任
	 * @param pix ԭͼ������ݾ���
	 * @param n ԭͼ��(n*n)�ĸ߻��
	 * @return �任��ľ�������
	 */
	public int[] DCT(int[] pix, int n) {		
		double[][] iMatrix = new double[n][n]; 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				iMatrix[i][j] = (double)(pix[i*n + j]);
			}
		}
		double[][] quotient = coefficient(n);	//��ϵ������
		double[][] quotientT = transposingMatrix(quotient, n);	//ת��ϵ������
		
		double[][] temp = new double[n][n];
		temp = matrixMultiply(quotient, iMatrix, n);
		iMatrix =  matrixMultiply(temp, quotientT, n);
		
		int newpix[] = new int[n*n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				newpix[i*n + j] = (int)iMatrix[i][j];
			}
		}
		return newpix;
	}
	/**
	 * ����ת��
	 * @param matrix ԭ����
	 * @param n ����(n*n)�ĸ߻��
	 * @return ת�ú�ľ���
	 */
	private double[][]  transposingMatrix(double[][] matrix, int n) {
		double nMatrix[][] = new double[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				nMatrix[i][j] = matrix[j][i];
			}
		}
		return nMatrix;
	}
	/**
	 * ����ɢ���ұ任��ϵ������
	 * @param n n*n����Ĵ�С
	 * @return ϵ������
	 */
	private double[][] coefficient(int n) {
		double[][] coeff = new double[n][n];
		double sqrt = 1.0/Math.sqrt(n);
		for(int i=0; i<n; i++) {
			coeff[0][i] = sqrt;
		}
		for(int i=1; i<n; i++) {
			for(int j=0; j<n; j++) {
				coeff[i][j] = Math.sqrt(2.0/n) * Math.cos(i*Math.PI*(j+0.5)/(double)n);
			}
		}
		return coeff;
	}
	/**
	 * �������
	 * @param A ����A
	 * @param B ����B
	 * @param n ����Ĵ�Сn*n
	 * @return �������
	 */
	private double[][] matrixMultiply(double[][] A, double[][] B, int n) {
		double nMatrix[][] = new double[n][n];
		double t = 0.0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				t = 0;
				for(int k=0; k<n; k++) {
					t += A[i][k]*B[k][j];
				}
				nMatrix[i][j] = t;			}
		}
		return nMatrix;
	}
	
}
