package com.finexus.automation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exception_node")
public class ExceptionsNode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	String exceptionClass;
	String exceptionMessage;
	String fullStackTrace;
	
	
	public ExceptionsNode() {}
	
	public ExceptionsNode(String exceptionClass, String exceptionMessage, String fullStackTrace) {
		super();
		this.exceptionClass = exceptionClass;
		this.exceptionMessage = exceptionMessage;
		this.fullStackTrace = fullStackTrace;
	}
	public String getExceptionClass() {
		return exceptionClass;
	}
	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getFullStackTrace() {
		return fullStackTrace;
	}
	public void setFullStackTrace(String fullStackTrace) {
		this.fullStackTrace = fullStackTrace;
	}

	@Override
	public String toString() {
		return "ExceptionOccured [exceptionClass=" + exceptionClass + ", exceptionMessage=" + exceptionMessage
				+ ", fullStackTrace=" + fullStackTrace + "]";
	}
	
	
	
	

}
