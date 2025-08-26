package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchRequest {

	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
    //Method For Fetching Student Request 
	public List<StudentRequestForAdmin> getStudentRequest(int aid){
		List<StudentRequestForAdmin> list=new ArrayList<>();
		Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For Fetching Student Request 
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() Method
    		FetchStaff fs=new FetchStaff();
    		int dno=fs.fetchDno(aid);
    		
    		//Getting Table Name From getStudentRequestTable() Method
    		AdminRequest ar=new AdminRequest();
    		String table=ar.getStudentRequestTable(dno);
    		
    		String qry="select reqid,sname,request,rollno from "+table+" where progress='pending';";
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			StudentRequestForAdmin s=new StudentRequestForAdmin();
    			s.setReqid(rs.getInt("reqid"));
    			s.setName(rs.getString("sname"));
    			s.setRequest(rs.getString("request"));
    			s.setRollno(rs.getInt("rollno"));
    			list.add(s);
    		}
    	}
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection Using Finally Block
 		finally {
 			try {
 				if(con!=null) con.close();
 			}catch(Exception e) {}
 			try {
 				if(ps!=null) ps.close();
 			}catch(Exception e) {}
 			try {
 				if(rs!=null) rs.close();
 			}catch(Exception e) {}
 		}
		return list;
	}
	
	//Method For Fetching Student Request 
		public List<StaffRequestForAdmin> getStaffRequest(int aid){
			List<StaffRequestForAdmin> list=new ArrayList<>();
			Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	
	    	//Code For Fetching Student Request 
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number From fetchDno() Method
	    		FetchStaff fs=new FetchStaff();
	    		int dno=fs.fetchDno(aid);
	    		
	    		//Getting Table Name From getStaffRequestTable() Method
	    		AdminRequest ar=new AdminRequest();
	    		String table=ar.getStaffRequestTable(dno);
	    		
	    		String qry="select reqid,sname,request,staffid from "+table+" where progress='pending';";
	    		ps=con.prepareStatement(qry);
	    		rs=ps.executeQuery();
	    		while(rs.next()) {
	    			StaffRequestForAdmin s=new StaffRequestForAdmin();
	    			s.setReqid(rs.getInt("reqid"));
	    			s.setName(rs.getString("sname"));
	    			s.setRequest(rs.getString("request"));
	    			s.setStaffId(rs.getInt("staffid"));
	    			list.add(s);
	    		}
	    	}
	    	catch(Exception e) {e.printStackTrace();}
	    	
	    	//Close The Connection Using Finally Block
	 		finally {
	 			try {
	 				if(con!=null) con.close();
	 			}catch(Exception e) {}
	 			try {
	 				if(ps!=null) ps.close();
	 			}catch(Exception e) {}
	 			try {
	 				if(rs!=null) rs.close();
	 			}catch(Exception e) {}
	 		}
			return list;
		}
}
