package com.finexus.automation.pojo;

public class TestMethodPojo {

	private int id;
	private String status;
	private String name;
//		private int duration_ms;
//		private String started_at; 
	private String finishedAt;
	
	private int testMethodId;

		

	public TestMethodPojo() {
		super();
	}

	public TestMethodPojo(int id, String status, String finishedAt, String name, int testMethodId) {
		super();
		this.id = id;
		this.status = status;
		
		this.finishedAt = finishedAt;
		this.name = name;
		this.testMethodId = testMethodId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(String finishedAt) {
		this.finishedAt = finishedAt;
	}

	public int getTestMethodId() {
		return testMethodId;
	}

	public void setTestMethodId(int testMethodId) {
		this.testMethodId = testMethodId;
	}


}
