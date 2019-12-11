
package com.finexus.automation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "testng_results")
//testng-results node
public class TestngResults {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long testngId;
	
	private int skipped=0;
	private int failed=0;
	private int ignored=0; 
	private int total=0; 
	private int passed=0;
	
	@OneToMany( targetEntity = Suite.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "ts_fk", referencedColumnName = "testngId")
	private List<Suite> suiteList ;//= new ArrayList<Suit>();
	
	
	public TestngResults() {
		
	}
	
	public TestngResults(Long testng_id, int skipped, int failed, int ignored, int total, int passed , List<Suite> suiteList) {
		super();
		this.testngId = testng_id;
		this.skipped = skipped;
		this.failed = failed;
		this.ignored = ignored;
		this.total = total;
		this.passed = passed;
		this.suiteList = suiteList;
	}
	
	
	
	public Long getTestngId() {
		return testngId;
	}
	
	public void setTestngId(Long testng_id) {
		this.testngId = testng_id;
	}
	
	public int getSkipped() {
		return skipped;
	}
	
	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}
	
	public int getFailed() {
		return failed;
	}
	
	public void setFailed(int failed) {
		this.failed = failed;
	}
	
	public int getIgnored() {
		return ignored;
	}
	
	public void setIgnored(int ignored) {
		this.ignored = ignored;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getPassed() {
		return passed;
	}
	
	public void setPassed(int passed) {
		this.passed = passed;
	}

	public List<Suite> getSuiteList() {
		return suiteList;
	}

	public void setSuiteList(List<Suite> suiteList) {
		this.suiteList = suiteList;
	}
	
	
	
	
	
}