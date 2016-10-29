package com.xml.edu.entity;

public class Department {
	private int id;
	private String departName;
	private Manager manger;
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", departName=" + departName + ", manger=" + manger + "]";
	}
	
	public Department(int id, String departName, Manager manger) {
		super();
		this.id = id;
		this.departName = departName;
		this.manger = manger;
	}
	public Department() {
		super();
	}
	public Department(String departName) {
		super();
		this.departName = departName;
	}
	
}
