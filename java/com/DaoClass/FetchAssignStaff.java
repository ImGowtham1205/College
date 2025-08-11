package com.DaoClass;

//Getters & Setters Class For To Set & Fetch About Assigned Subjects Details
public class FetchAssignStaff {
	private String code;
	private String subject;
	private String name;
	private int sem;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
}
