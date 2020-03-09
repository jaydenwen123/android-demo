package com.fail;

/**
 * 边节点
 * 
 * @author Administrator
 * 
 */
public class Vnode {
	// 边的重点
	public int adjvex;
	// 边的权重ֵ
	public int weight;
	// 该边的下一条边的信息
	public Vnode nextVex;

	// 单位网络租用费
	public int cost;
	// 该边的前一条边的重点
	public int prevVex;
	// 判断改变是否是其所属的表头节点的第一个邻接节点
	public boolean isFirst;


	public Vnode() {
		// TODO Auto-generated constructor stub
	}

	public Vnode(int adjvex, int weight, Vnode nextVex) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
	}

	public Vnode(int prevVex, int adjvex, int weight, Vnode nextVex) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
		this.prevVex = prevVex;
	}

	public Vnode(int adjvex, int weight, Vnode nextVex, int cost, int prevVex,
			boolean isFirst) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
		this.cost = cost;
		this.prevVex = prevVex;
		this.isFirst = isFirst;
	}

	public Vnode(int adjvex, int weight, Vnode nextVex, int cost,
			boolean isFirst) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
		this.cost = cost;
		this.isFirst = isFirst;
	}

	public Vnode(int prevVex, int adjvex, int weight, Vnode nextVex,
			boolean isFirst) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
		this.prevVex = prevVex;
		this.isFirst = isFirst;
	}

	@Override
	public String toString() {
		return "(" + adjvex + "," + weight + "," + cost + "," + nextVex + ","
				+ isFirst + ")";
	}

}