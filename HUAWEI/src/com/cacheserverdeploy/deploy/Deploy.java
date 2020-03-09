package com.cacheserverdeploy.deploy;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.fail.Anode;
import com.fail.CustomNode;
import com.fail.Vnode;
import com.graph.AdjMatrixGraphic;

public class Deploy {
	/**
	 * 你需要完成的入口 <功能详细描述>
	 * 
	 * @param graphContent
	 *            用例信息文件
	 * @return [参数说明] 输出结果信息
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] deployServer(String[] graphContent) {
		// 打印出图的信息
		for (String str : graphContent) {
			System.out.println(Arrays.toString(trans(str)));
		}
		PriorityQueue<Anode> queue = new PriorityQueue<Anode>();
		AdjMatrixGraphic graph = initGraph(queue, graphContent);
		// 1.首先找出边数最多的镖头结点，然后设置其为第一台服务器，然后不断的找路径
		Anode first = queue.poll();
		System.out.println(first.size + "******************"
				+ queue.poll().size);

		/** do your work here **/
		return new String[] { "17", "\r\n", "0 8 0 20" };
	}

	/**
	 * 初始化图的信息
	 * 
	 * @param graphContent
	 */
	private static AdjMatrixGraphic initGraph(PriorityQueue<Anode> queue,
			String[] graphContent) {
		// 实现思路如下：
		// 1.首先确定服务器的个数（为了首先保证做出来，先采用暴力法求解）
		// 2.然后寻找路径（待定）
		// 3.将其保存到文件中
		int k = 4;
		int symFirst = -1;
		int symEnd = -1;
		int arr[] = null;
		// 定义的n变量用来保存节点的个数
		int[] initInfo = trans(graphContent[0]);
		int n = initInfo[0];
		// 定义边的个数
		int edgeCount = initInfo[1];
		// 消费节点的数目
		int customeCount = initInfo[2];
		AdjMatrixGraphic graph = new AdjMatrixGraphic(n);
		int[][] edge = new int[n][n];
		// 初始化边的信息
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					edge[i][j] = -1;
			}
		}
		// for()

		// 一下进行构建镖头结点数组
		/*
		 * Anode[] nodes = new Anode[n]; for (int i = 0; i < n; i++) { nodes[i]
		 * = new Anode(i); }
		 * 
		 * // 下面对nodes进行初始化 Vnode vnodeF = null; Vnode vnodeE = null; while (k <
		 * edgeCount + 4) { arr = trans(graphContent[k]); if (symFirst !=
		 * arr[0]) { symFirst = arr[0]; vnodeF = new Vnode(arr[1], arr[2], null,
		 * arr[3], true); // 给该表头节点添加第一个临节元素 nodes[symFirst].firstVex = vnodeF;
		 * nodes[symFirst].size++; // } else { vnodeF.nextVex = new
		 * Vnode(arr[1], arr[2], null, arr[3], false); vnodeF = vnodeF.nextVex;
		 * nodes[symFirst].size++; } k++; } k = 4; // 继续初始化边的信息，从终点开始扫描 while (k
		 * < edgeCount + 4) { arr = trans(graphContent[k]); // 一下是对边的另外一个节点进行添加
		 * if (symEnd != arr[1] && nodes[arr[1]].firstVex == null) { symEnd =
		 * arr[1]; vnodeE = new Vnode(arr[0], arr[2], null, arr[3], true); //
		 * 给该表头节点添加第一个临节元素 nodes[symEnd].firstVex = vnodeE;
		 * nodes[symEnd].size++; } else { vnodeE =
		 * graph.getLastNode(nodes[arr[1]]); vnodeE.nextVex = new Vnode(arr[0],
		 * arr[2], null, arr[3], false); vnodeE = vnodeE.nextVex; // 统计该节点的边数
		 * nodes[arr[1]].size++; } k++; }
		 * 
		 * int temp = edgeCount + 5; while (temp < edgeCount + 5 + customeCount)
		 * { arr = trans(graphContent[temp]); Anode anode = nodes[arr[1]];
		 * anode.customNode = new CustomNode(arr[0], arr[2]); temp++; }
		 * graph.setAnode(nodes); graph.print();
		 */
		// System.out.println(graph.getServerCost());
		// 通过优先级队列来找出边最多的节点
		return graph;
	}

	/**
	 * 将读进来的数据，先通过空格来分割
	 * 
	 * @param info
	 * @return
	 */
	public static int[] trans(String info) {
		String arr[] = info.trim().split(" ");
		int[] a = new int[arr.length];
		for (int i = 0; i < a.length; i++)
			if (!arr[i].trim().isEmpty())
				a[i] = Integer.parseInt(arr[i]);
		return a;
	}

}
