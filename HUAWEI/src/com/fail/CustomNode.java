package com.fail;

/**
 * 消费节点
 * 
 * @author Administrator
 * 
 */
public class CustomNode {
	// 消费节点的id
	public int id;
	// 消费节点的视频带宽消耗
	public int bandWidth;
	
	public boolean isReach;

	public CustomNode() {
		// TODO Auto-generated constructor stub
	}

	public CustomNode(int id, int bandWidth) {
		super();
		this.id = id;
		this.bandWidth = bandWidth;
	}

	@Override
	public String toString() {
		return "[" + id + "," + bandWidth + "]";
	}

}
