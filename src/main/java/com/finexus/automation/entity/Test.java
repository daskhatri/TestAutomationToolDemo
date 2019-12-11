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
@Table(name = "test")
// test node 
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int duration_ms=0;
	private String started_at; //=new String();
	private String finished_at; //=new String();

	@OneToMany( targetEntity = TestCase.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "testTestCase_fk", referencedColumnName = "id")
	List<TestCase> testCaseList; //=new ArrayList<Classes>();
	// TestCase is representing node "class"
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

	public int getDuration_ms() {
		return duration_ms;
	}

	public void setDuration_ms(int duration_ms) {
		this.duration_ms = duration_ms;
	}

	public String getStarted_at() {
		return started_at;
	}

	public void setStarted_at(String started_at) {
		this.started_at = started_at;
	}

	public String getFinished_at() {
		return finished_at;
	}

	public void setFinished_at(String finished_at) {
		this.finished_at = finished_at;
	}

	public List<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(List<TestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}
	
}
