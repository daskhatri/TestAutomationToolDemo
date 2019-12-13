package com.finexus.automation.entity;

import java.util.Date;

public class TestCaseHistory {

	private Integer tchId;
	private Integer trId;
	private Date trLastRun;
	
	
	public TestCaseHistory() {
		
	}
	public TestCaseHistory(Integer tchId, Integer trId, Date trLastRun) {
		super();
		this.tchId = tchId;
		this.trId = trId;
		this.trLastRun = trLastRun;
	}
	public Integer getTchId() {
		return tchId;
	}
	public void setTchId(Integer tchId) {
		this.tchId = tchId;
	}
	public Integer getTrId() {
		return trId;
	}
	public void setTrId(Integer trId) {
		this.trId = trId;
	}
	public Date getTrLastRun() {
		return trLastRun;
	}
	public void setTrLastRun(Date trLastRun) {
		this.trLastRun = trLastRun;
	}
	
	
}
