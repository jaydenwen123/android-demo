package com.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * 
 * 图的临街链表存储
 * 
 * @author Administrator
 * 
 */
public class AdjLinkedGraph {

	// 存储图的节点个数
	private int n;
	// 存储图表头节点的数组
	private Anode[] vexNodes;
	// 存储某节点是否被访问过
	private boolean visit[];

	private int serverCost;

	public AdjLinkedGraph() {
		// TODO Auto-generated constructor stub
	}

	public AdjLinkedGraph(int n) {
		super();
		this.n = n;
		this.visit = new boolean[n];
	}

	public AdjLinkedGraph(int n, Anode[] vexNodes) {
		super();
		this.n = n;
		this.vexNodes = vexNodes;
		this.visit = new boolean[n];
	}

	public AdjLinkedGraph(int n, int serverCost) {
		super();
		this.n = n;
		this.serverCost = serverCost;
		this.visit = new boolean[n];
	}

	/**
	 * 根据网络节点的id来获取表头节点
	 * 
	 * @param symbol
	 * @return
	 */
	public Anode getAnodeBySymbol(int symbol) {
		return vexNodes[symbol];
	}

	/**
	 * 获取服务器的部署费用
	 * 
	 * @return
	 */
	public int getServerCost() {
		return serverCost;
	}

	/**
	 * 添加表头节点数组
	 * 
	 * @param nodes
	 */
	public void setAnode(Anode[] nodes) {
		this.vexNodes = nodes;
	}

	/**
	 * 深度遍历
	 */
	public void depthTraverse() {

		// 1初始化
		// 2.进行单个节点遍历
		// 3.递归遍历
		initVisit();
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				traverseOne(vexNodes[i]);
			}
		}
	}

	/**
	 * 遍历单个表头节点
	 * 
	 * @param node
	 */
	public void traverseOne(Anode node) {

		// 通过该节点来获取在数组中的下标
		int index = getIndex(node);
		// 标志该节点已被访问
		visit[index] = true;
		// 访问该节点
		System.out.print(node.data + "\t");
		Vnode n = node.firstVex;
		while (n != null) {
			int i = n.adjvex;
			if (!visit[i]) {
				traverseOne(vexNodes[i]);
			}
			n = n.nextVex;

		}
	}

	/**
	 * 遍历单个表头节点
	 * 
	 * @param node
	 */
	public void traverse(Anode node) {

		// 通过该节点来获取在数组中的下标
		int index = getIndex(node);
		// 标志该节点已被访问
		visit[index] = true;
		// 访问该节点
		// if (node.customNode != null) {
		// System.out.println(node.data);
		//
		// } else {
		// if (vexNodes[node.firstVex.adjvex].customNode != null) {
		// if (node.firstVex.weight >=
		// vexNodes[node.firstVex.adjvex].customNode.bandWidth) {
		//
		// System.out
		// .println(node.data
		// + ","
		// + node.firstVex.adjvex
		// + ","
		// + vexNodes[node.firstVex.adjvex].customNode.id
		// + ","
		// + vexNodes[node.firstVex.adjvex].customNode.bandWidth);
		// } else {
		// System.out.println(node.data + "," + node.firstVex.adjvex
		// + ","
		// + vexNodes[node.firstVex.adjvex].customNode.id
		// + "," + node.firstVex.weight);
		// vexNodes[node.firstVex.adjvex].customNode.bandWidth -=
		// node.firstVex.weight;
		// }
		// }
		// }
		// 判断该服务器是否直接和消费节点相连
		if (node.customNode != null && !node.customNode.isReach) {
			System.out.println(node.data + "," + node.customNode.id + ","
					+ node.customNode.bandWidth);
			node.customNode.isReach = true;
		} else {
			// 判断是否该节点的下一个邻接节点直接和消费节点相连
			Vnode vnode = node.firstVex;
			while (vnode != null) {
				Anode anode = getAnodeBySymbol(vnode.adjvex);
				if (!visit[vnode.adjvex]) {
					if (anode.customNode != null
							&& vnode.weight >= anode.customNode.bandWidth
							&& !anode.customNode.isReach) {
						System.out.println(node.data + "," + anode.data + ","
								+ anode.customNode.id + ","
								+ anode.customNode.bandWidth);
						anode.customNode.isReach = true;
					} else {
						if (anode.customNode != null
								&& !anode.customNode.isReach) {
							anode.customNode.bandWidth -= vnode.weight;
							System.out.println(node.data + "," + anode.data
									+ "," + anode.customNode.id + ","
									+ vnode.weight);
						}
					}
					traverse(anode);
				}
				vnode = vnode.nextVex;
			}
		}
		// System.out.print(node.data + "\t");
		// Vnode n = node.firstVex;
		// while (n != null) {
		// int i = n.adjvex;
		// if (!visit[i]) {
		// traverseOne(vexNodes[i]);
		// }
		// n = n.nextVex;
		//
		// }
	}
	

	/**
	 * 根据该节点获取该镖头结点在数组中的下标
	 * 
	 * @param node
	 * @return
	 */
	public int getIndex(Anode node) {
		for (int i = 0; i < n; i++) {
			if (node.data == vexNodes[i].data)
				return i;
		}
		return -1;
	}

	/**
	 * 广度优先遍历
	 */
	public void braodTraverse() {
		initVisit();
		Queue<Anode> queue = new LinkedList<Anode>();
		queue.add(vexNodes[0]);
		visit[0] = true;
		while (!queue.isEmpty()) {
			Anode nAnode = queue.poll();
			Vnode node = nAnode.firstVex;
			System.out.print(nAnode.data + "\t");
			while (node != null) {
				if (!visit[node.adjvex]) {
					queue.add(vexNodes[node.adjvex]);
					visit[node.adjvex] = true;
				}
				node = node.nextVex;
			}

		}
		System.out.println();
	}

	/**
	 * 深度优先遍历（通过栈来实现）
	 */
	public void depthTraverseWithStack() {

		initVisit();
		Stack<Anode> stack = new Stack<>();
		stack.push(vexNodes[0]);
		visit[0] = true;
		while (!stack.isEmpty()) {
			Anode node = stack.pop();
			System.out.print(node.data + "\t");
			Vnode vnode = getLastNode(node);
			while (!vnode.isFirst) {
				if (!visit[vnode.adjvex]) {
					visit[vnode.adjvex] = true;
					stack.push(vexNodes[vnode.adjvex]);
				}
				vnode = getVnode(node, vnode);
			}
			// 访问表头节点的第一个节点
			if (!visit[vnode.adjvex]) {
				visit[vnode.adjvex] = true;
				stack.push(vexNodes[vnode.adjvex]);
			}

		}
		System.out.println();
	}

	/**
	 * 获取该表头节点的最后一个邻接节点
	 * 
	 * @param node
	 * @return
	 */
	public Vnode getLastNode(Anode node) {
		Vnode n = null;
		n = node.firstVex;
		while (n.nextVex != null)
			n = n.nextVex;
		return n;
	}

	/**
	 * 初始化访问数组
	 */
	public void initVisit() {
		for (int i = 0; i < n; i++) {
			visit[i] = false;
		}
	}

	/**
	 * 获取表头节点node的vnode的前一个节点
	 * 
	 * @param node
	 * @param pre
	 * @return
	 */
	public Vnode getVnode(Anode node, Vnode vnode) {
		Vnode n = node.firstVex;
		while (n != null) {
			if (n.adjvex == vnode.prevVex)
				return n;
			n = n.nextVex;
		}
		return n;
	}

	/**
	 * 打印邻接链表的信息
	 */
	public void print() {
		StringBuffer buffer = new StringBuffer();
		if (vexNodes != null && vexNodes.length > 0) {
			for (Anode node : vexNodes) {
				System.out.println(node.toString());
				buffer.append(node.toString() + "\r\n");
				buffer.append("\r\n");
			}
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("E:\\huawei\\result.txt"));
			os.write(buffer.toString().getBytes());
			os.flush();
			System.out.println("图的节点信息已经写入到文件中！！！");
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

	// public static void main(String[] args) {
	//
	// int n = 5;
	// // 通过临街链表来创建图
	// // Object[] nodes = new Object[] { 0, 1, 2, 3, 4 };
	// Anode nodes[] = new Anode[n];
	// for (int i = 0; i < n; i++) {
	// nodes[i] = new Anode(i);
	// }
	// nodes[0].firstVex = new Vnode(0, 4, 1, new Vnode(4, 1, 1, null, false),
	// true);
	// nodes[1].firstVex = new Vnode(1, 4, 1, new Vnode(4, 3, 1, new Vnode(3,
	// 2, 1, new Vnode(2, 0, 1, null, false), false), false), true);
	// nodes[2].firstVex = new Vnode(2, 3, 1, new Vnode(3, 1, 1, null, false),
	// true);
	// nodes[3].firstVex = new Vnode(3, 4, 1, new Vnode(4, 2, 1, new Vnode(2,
	// 1, 1, null, false), false), true);
	// nodes[4].firstVex = new Vnode(4, 3, 1, new Vnode(3, 1, 1, new Vnode(1,
	// 0, 1, null, false), false), true);
	// AdjLinkedGraph graph = new AdjLinkedGraph(n);
	// graph.setAnode(nodes);
	// System.out.println("test the adjLinkedtable!!!");
	// graph.print();
	//
	// System.out.println("test the depthTraverse!!!");
	// graph.depthTraverse();
	// System.out.println();
	// System.out.println("test teh depthTraverseWithStack!!!");
	// graph.depthTraverseWithStack();
	// System.out.println("test the broadTraverse!!!");
	//
	// graph.braodTraverse();
	//
	// System.out.println("test the getLastNode!!!");
	// System.out.println(graph.getLastNode(nodes[1]).adjvex);
	//
	// System.out.println("************************");
	//
	// System.out
	// .println(graph.getVnode(nodes[1], graph.getLastNode(nodes[1])).adjvex);
	// }
}
