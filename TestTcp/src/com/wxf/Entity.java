package com.wxf;

/**
 * ÊµÌåÀà
 * 
 * @author Administrator
 * 
 */
public class Entity {

	public short start;
	public short datalen;
	public String messages;
	public short end;
	
	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public Entity(short start, short datalen, String messages, short end) {
		super();
		this.start = start;
		this.datalen = datalen;
		this.messages = messages;
		this.end = end;
	}
	
}
