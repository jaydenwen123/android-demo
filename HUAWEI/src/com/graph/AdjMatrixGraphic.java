package com.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 通过邻接矩阵来存储图的信息
 * 
 * @author Administrator
 * 
 */
public class AdjMatrixGraphic {

	// 图的邻接矩阵的存储
	// 1.抽象数据结构

	// c。点个数
	private int n;
	// a。点集合
	private Object[] vertex;
	// b。边集合
	private int[][] edge;
	// d。该数组用来记录是否遍历
	private boolean[] visit;

	public AdjMatrixGraphic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造器
	 * 
	 * @param n
	 */
	public AdjMatrixGraphic(int n) {
		super();
		this.n = n;
		vertex = new Object[n];
		edge = new int[n][n];
		// 对边的信息进行初始化
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				edge[i][j] = -1;
			}
		}
		visit = new boolean[n];
	}

	/**
	 * 添加边的信息
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j) {
		if (i < 0 || i >= n || j < 0 || j >= n) {
			return;
		}
		edge[i][j] = 1;
		// 如果加了这句话，表示的是无向图，否则表示的是有向图
		// edge[j][i] = 1;
	}

	/**
	 * 通过直接传二维数组来构建边的信息
	 * 
	 * @param weight
	 */
	public void addEdge(int[][] weight) {
		this.edge = weight;
	}

	/**
	 * 添加点的信息
	 * 
	 * @param vertex
	 */
	public void addVertex(Object[] vertex) {
		this.vertex = vertex;
	}

	/**
	 * 获取有向图的边的个数
	 * 
	 * @return
	 */
	public int getEdgeCount() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (edge[i][j] != 0)
					count++;
			}
		}
		return count;
	}

	/**
	 * 图的深度优先遍历，非递归结构
	 */
	public void depthSearchNoStack(int i) {

		// 图的深度优先遍历非递归结构的设计思路如下：
		// 1.通过栈来模拟，首先初始化栈
		// 2.将初始化的节点加入到栈中
		// 3.当栈不为空时，一直循环
		// 4.从栈中弹出节点
		for (int j = 0; j < n; j++) {
			visit[j] = false;
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(i);
		visit[i] = true;
		int k = 0;
		while (!stack.isEmpty()) {
			i = stack.pop();
			System.out.print(vertex[i] + "\t");
			// 从该节点的最后一个邻接节点找，然后如果未访问，则入栈,直到把该元素的所有节点都遍历完为止
			k = getLastAdj(i);
			while (k >= 0) {
				if (!visit[k]) {
					visit[k] = true;
					stack.push(k);
				}
				k = getPreAdj(i, k);
			}
		}
		System.out.println();
	}

	/**
	 * 图的深度优先遍历 递归结构
	 * 
	 * 注意：其实下面的第二个循环是没有必要的，它只是保证了一个初始化的作用
	 */
	public void depthSearch() {
		// 图的深度优先遍历的思路如下：
		// 1.深度优先遍历采用递归结构
		// 2.递归体为：从给定的节点开始找从该节点出发，第一个未被访问过的节点，然后再递归
		// 3.递归出口为：当所有的节点都访问完为止，算法结束
		for (int i = 0; i < n; i++) {
			visit[i] = false;
		}
		// 其实这个for循环 可以不要，因为如下：因为当对于无向图遍历时，可以不需要，但是对于有向图，有可能在遍历的过程中
		// 出现无连通的情况，因此该循环可以保证正常的对其无联通的图的遍历
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				depthSearch(i);
			}
		}
		System.out.println();
	}

	/**
	 * 遍历该节点
	 * 
	 * @param graphic
	 * @param i
	 */
	private void depthSearch(int i) {
		// 首先访问该节点
		System.out.print(vertex[i] + "\t");
		// 然后再把该节点标记为已访问
		visit[i] = true;
		// 找到该元素的第一个邻接节点，然后
		int k = getFirstAdj(i);
		while (k >= 0) {
			if (!visit[k]) {
				// visit[k] = true;
				depthSearch(k);
			}
			// 当i节点的第一个邻接节点遍历完后，在回溯，从k节点的下一个邻接节点遍历
			k = getNextAdj(i, k);
		}
	}

	/**
	 * 获得该元素的第一个邻接节点
	 * 
	 * @param i
	 * @return
	 */
	private int getFirstAdj(int i) {
		// TODO Auto-generated method stub
		for (int j = 0; j < n; j++) {
			if (edge[i][j] != 0)
				return j;
		}
		return -1;
	}

	/**
	 * 获得i节点的j节点的下一个临节节点
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int getNextAdj(int i, int j) {
		for (int k = j + 1; k < n; k++) {
			if (edge[i][k] != 0)
				return k;
		}
		return -1;
	}

	/**
	 * 获取节点i的最后一个邻接节点
	 * 
	 * @param i
	 * @return
	 */
	private int getLastAdj(int i) {
		for (int k = n - 1; k >= 0; k--) {
			if (edge[i][k] != 0)
				return k;
		}
		return -1;
	}

	/**
	 * 获取元素i的节点j的上一个节点元素
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int getPreAdj(int i, int j) {

		for (int k = j - 1; k >= 0; k--) {
			if (edge[i][k] != 0) {
				return k;
			}
		}
		return -1;
	}

	/**
	 * 图的广度优先遍历
	 */
	public void broadSearch(int i) {
		// 图的广度优先遍历思路如下：
		// 0.初始化存储访问信息的visit数组
		// 1.首先采用队列来组织各节点，将初始节点加入到队列中
		// 2.当队列不为空时，一直循环
		// 3.将初始节点的所有未访问的节点加入到队列中
		// 直到所有的节点已经访问完为止，算法结束
		for (int j = 0; j < n; j++) {
			visit[j] = false;
		}
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(i);
		visit[i] = true;
		int k = 0;
		while (!queue.isEmpty()) {
			// 将队头的元素进行出栈
			i = queue.poll();
			System.out.print(vertex[i] + "\t");
			k = getFirstAdj(i);
			while (k >= 0) {
				if (!visit[k]) {
					queue.offer(k);
					visit[k] = true;
				}
				k = getNextAdj(i, k);
			}
		}
		System.out.println();
	}

	public void printGraphic() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(edge[i][j] + "\t");
			}
			System.out.println();
		}
	}

	// ************以下是进行最小生成树的算法的实现****************//
	// ****************************************************//

	public static void main(String[] args) {
		// 以下测试有向图
		AdjMatrixGraphic graphic = new AdjMatrixGraphic(5);
		Object[] vertex = { 0, 1, 2, 3, 4 };
		graphic.addVertex(vertex);
		graphic.addEdge(0, 1);
		graphic.addEdge(0, 4);

		graphic.addEdge(1, 2);
		graphic.addEdge(1, 3);
		graphic.addEdge(1, 4);

		graphic.addEdge(3, 2);
		graphic.addEdge(3, 4);

		// 打印入得邻接矩阵
		System.out.println("图的邻接矩阵：");
		graphic.printGraphic();

		// 图的深度优先遍历
		System.out.println("图的深度优先遍历(递归结构)：");
		graphic.depthSearch();
		System.out.println("图的深度优先遍历(非递归结构)：");
		graphic.depthSearchNoStack(0);
		System.out.println("图的广度优先遍历：");
		graphic.broadSearch(0);
	}
}
