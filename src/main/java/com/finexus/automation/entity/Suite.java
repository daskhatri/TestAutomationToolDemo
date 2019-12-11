package com.finexus.automation.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "suite")
//suite node
public class Suite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int suiteId;
	private String name;
	private int duration_ms;
	
	
	private String started_at;
	
	
	private String finished_at; // =new Date();

//	@OneToOne(targetEntity = Test.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "st_fk", referencedColumnName = "suiteId")
//	private Test testCase;

	@OneToMany( targetEntity = Test.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "suitTest_fk", referencedColumnName = "suiteId")
	private List<Test> testList; 


	public Suite(int suiteId, String name, int duration_ms, String started_at, String finished_at) {
		this.suiteId = suiteId;
		this.name = name;
		this.duration_ms = duration_ms;
		this.started_at = started_at;
		this.finished_at = finished_at;
	}

	public Suite() {
		
	}

	public int getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
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

	public List<Test> getTestList() {
		return testList;
	}

	public void setTestList(List<Test> testList) {
		this.testList = testList;
	}

}
