package com.xml.edu.entity;

public class Manager {
	private int id;
	private String managerName;
	private String major;
	
	@Override
	public String toString() {
		return "Manager [id=" + id + ", managerName=" + managerName + ", major=" + major + "]";
	}
	
	public Manager(int id, String managerName, String major) {
		super();
		this.id = id;
		this.managerName = managerName;
		this.major = major;
	}
	public Manager(String managerName, String major) {
		super();
		this.managerName = managerName;
		this.major = major;
	}
	public Manager() {
		super();
	}
	
}
