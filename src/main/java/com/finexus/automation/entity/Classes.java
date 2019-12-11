package com.finexus.automation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "classes")
public class Classes {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	String name;
	
	
//	List<TestMethod> testMethodList=new ArrayList<TestMethod>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public List<TestMethod> getTestMethodList() {
//		return testMethodList;
//	}
//	public void setTestMethodList(List<TestMethod> testMethodList) {
//		this.testMethodList = testMethodList;
//	}
	
			
	
}
