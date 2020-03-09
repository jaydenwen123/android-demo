package com.wxf.app;

import android.app.Application;

public class MyApp extends Application{
	
	private String name;
	private Integer age;
	public MyApp() {
		// TODO Auto-generated constructor stub
		name="wenxiaofei";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public MyApp(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	
	

}
