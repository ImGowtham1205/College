package com.DaoClass;

//Getters & Setters Class For To Set & Fetch Staff Request For Admin
public class StaffRequestForAdmin {
	
	private int reqid;
	private int staffid;
	private String name;
	private String request;
	
	public int getReqid() {
		return reqid;
	}
	public void setReqid(int reqid) {
		this.reqid = reqid;
	}
	public int getStaffId() {
		return staffid;
	}
	public void setStaffId(int staffid) {
		this.staffid = staffid;
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
