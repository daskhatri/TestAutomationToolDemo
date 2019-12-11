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
@Table(name = "test_method")
public class TestMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	String status;
	String signature;
	String name;
	int duration_ms;
	String started_at; //= new String();
	String finished_at;// = new String();

	@OneToMany(targetEntity = ExceptionsNode.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "tMethodExcep_fk", referencedColumnName = "id")
	List<ExceptionsNode> exceptionsList; // =new ArrayList<ExceptionOccured>();

	public TestMethod(String status, String signature, String name, int duration_ms, String started_at, String finished_at,
			List<ExceptionsNode> exceptionsList) {
		super();
		this.status = status;
		this.signature = signature;
		this.name = name;
		this.duration_ms = duration_ms;
		this.started_at = started_at;
		this.finished_at = finished_at;
		this.exceptionsList = exceptionsList;
	}

	public TestMethod() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ExceptionsNode> getExceptionsList() {
		return exceptionsList;
	}

	public void setExceptionsList(List<ExceptionsNode> exceptionsList) {
		this.exceptionsList = exceptionsList;
	}

	@Override
	public String toString() {
		return "TestMethod [status=" + status + ", signature=" + signature + ", name=" + name + ", duration_ms="
				+ duration_ms + ", started_at=" + started_at + ", finished_at=" + finished_at
				+ ", ExceptionsList="+ exceptionsList 
				+ "]";
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

}
