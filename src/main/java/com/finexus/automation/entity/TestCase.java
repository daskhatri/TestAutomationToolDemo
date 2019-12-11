package com.finexus.automation.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "test_case")
// This class represents the "class" node in the tenstng-Results.xml
public class TestCase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany( targetEntity = TestMethod.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "tCaseTMethod_fk", referencedColumnName = "id")
	List<TestMethod> testMethodsList;
	
	
	public TestCase() {

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


	public List<TestMethod> getTestMethodsList() {
		return testMethodsList;
	}


	public void setTestMethodsList(List<TestMethod> testMethodsList) {
		this.testMethodsList = testMethodsList;
	}


	public TestCase(int id, String name, List<TestMethod> testMethodsList) {
		super();
		this.id = id;
		this.name = name;
		this.testMethodsList = testMethodsList;
	}
	


}
