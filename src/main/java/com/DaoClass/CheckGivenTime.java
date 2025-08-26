package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CheckGivenTime {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Create Object For FetchStaff & PutAttendance Class 
    FetchStaff fs=new FetchStaff();
    PutAttendance pa=new PutAttendance();
    
  //Method For Get Time For The Given Date
	public String getTime(String time,String date,int sid,String code,int sem) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			
			//Getting Department Number From fetchDno() Method Using Rollno For To Get The Argument For getTable() To Fetch The Time
			FetchStaff fs=new FetchStaff();
			int dno=fs.fetchDno(sid);
						
			//Getting Table Name By Calling getTable() Method Using Department Number & Semester
			PutAttendance pa=new PutAttendance();
			String table=pa.getAttendanceTable(dno, sem);
			
			//Code For Get Time For The Given Date
			String qry="select distinct Atime from "+table+" where Atime=? and Adate=?";
			ps=con.prepareStatement(qry);
			ps.setString(1, time);
			ps.setString(2, date);
			rs=ps.executeQuery();
			while(rs.next())
				return rs.getString("Atime");
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
		return "";
	}
	
	//Method For To Get selected Time And Staffid From The 3rd Year Attendance Table
	public List<Attendance> get3rdYearTime(int sid,int sem,String time,String date){
		List<Attendance>list=new ArrayList<>();
		Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() 
    		int dno=fs.fetchDno(sid);
    		
    		//Getting Table Name From getTable() Method
    		String table=pa.getAttendanceTable(dno, sem);
    		
    		//Code For To Get selected Time And Staffid From The 3rd Year Attendance Table
    		String qry="select distinct Atime,Staffid from "+table+" where Atime=? and Adate=?";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, time);
    		ps.setString(2, date);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			Attendance a=new Attendance();
    			a.setTime(rs.getString("Atime"));
    			a.setStaffid(rs.getInt("Staffid"));
    			list.add(a);
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
	
	//Method For To Get selected Time And Staffid From The 2nd Year Attendance Table
		public List<Attendance> get2ndYearTime(int sid,int sem,String time,String date){
			List<Attendance>list=new ArrayList<>();
			Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number From fetchDno() 
	    		int dno=fs.fetchDno(sid);
	    		
	    		//Getting Table Name From getTable() Method
	    		String table=pa.getAttendanceTable(dno, sem);
	    		
	    		//Code For To Get selected Time And Staffid From The 2nd Year Attendance Table
	    		String qry="select distinct Atime,Staffid from "+table+" where Atime=? and Adate=?";
	    		ps=con.prepareStatement(qry);
	    		ps.setString(1, time);
	    		ps.setString(2, date);
	    		rs=ps.executeQuery();
	    		while(rs.next()) {
	    			Attendance a=new Attendance();
	    			a.setTime(rs.getString("Atime"));
	    			a.setStaffid(rs.getInt("Staffid"));
	    			list.add(a);
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
		
		//Method For To Get selected Time And Staffid From The 1st Year Attendance Table
		public List<Attendance> get1stYearTime(int sid,int sem,String time,String date){
			List<Attendance>list=new ArrayList<>();
			Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number From fetchDno() 
	    		int dno=fs.fetchDno(sid);
	    		
	    		//Getting Table Name From getTable() Method
	    		String table=pa.getAttendanceTable(dno, sem);
	    		
	    		//Code For To Get selected Time And Staffid From The 1st Year Attendance Table
	    		String qry="select distinct Atime,Staffid from "+table+" where Atime=? and Adate=?";
	    		ps=con.prepareStatement(qry);
	    		ps.setString(1, time);
	    		ps.setString(2, date);
	    		rs=ps.executeQuery();
	    		while(rs.next()) {
	    			Attendance a=new Attendance();
	    			a.setTime(rs.getString("Atime"));
	    			a.setStaffid(rs.getInt("Staffid"));
	    			list.add(a);
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
