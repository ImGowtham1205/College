package com.DaoClass;

//Getters & Setters Class For To Set & Fetch Student Request For Admin
public class StudentRequestForAdmin {
 
	private int reqid;
	private int rollno;
	private String name;
	private String request;
	
	public int getReqid() {
		return reqid;
	}
	public void setReqid(int reqid) {
		this.reqid = reqid;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
}
