package com.fail;

/**
 * 表头节点
 * 
 * @author Administrator
 * 
 */
public class Anode implements Comparable<Anode> {

	/**
	 * 表头节点的符号
	 */
	public int data;
	// 表头节点的第一个邻接节点
	public Vnode firstVex;

	// 定义该节点总共有几条边
	public int size;

	public CustomNode customNode;

	public Anode() {
		// TODO Auto-generated constructor stub
	}

	public Anode(int data) {
		super();
		this.data = data;
	}

	public Anode(int data, Vnode firstVex) {
		super();
		this.data = data;
		this.firstVex = firstVex;
	}

	@Override
	public String toString() {
		return "(" + data + "," + firstVex + "," + size + "," + customNode
				+ ")";
	}

	@Override
	public int compareTo(Anode o) {
		// TODO Auto-generated method stub
		return o.size-this.size;
	}

}