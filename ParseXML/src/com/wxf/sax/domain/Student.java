package com.wxf.sax.domain;

public class Student {
	private int id;
	private String name;
	private int age;
	private String isMarry;
	private String sex;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, int age, String isMarry, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.isMarry = isMarry;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", isMarry=" + isMarry + ", sex=" + sex + "]";
	}

}
