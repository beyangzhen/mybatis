package com.xml.edu.entity;

import java.util.List;

public class Classes {
	private int id;
	private String class_name;
	private List<Student> studentList;
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	
	@Override
	public String toString() {
		return "Classes [id=" + id + ", class_name=" + class_name + ", studentList=" + studentList + "]";
	}
	public Classes(int id, String class_name) {
		super();
		this.id = id;
		this.class_name = class_name;
	}
	public Classes() {
		super();
	}
	public Classes(String class_name) {
		super();
		this.class_name = class_name;
	}
	
}
