package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Percentage {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For To Get The Total Numbers Of Hours Attended For All Subjects 
	public int getOverAllPresent(int rollno) {
		//Code For Getting Total Numbers Of Hours Attended For All Subjects
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number By Calling fetchDno() Method Passing Argument As Rollno To The Method
    		FetchStudent fs=new FetchStudent();
    		int dno=fs.fetchDno(rollno);
    		
    		//Getting Department Number By Calling getSem() Method Passing Argument As Rollno To The Method
    		FetchCourse fc=new FetchCourse();
        	List<CourseName>list = fc.getSem(rollno);
        	int sem=0;
        	for(CourseName course : list) {
        		sem=course.getSem();
        	}
        	
        	//After Getting Department Number & Current Semester Passing The Values As Argument To getTable() Method To Get The Table Name
        	PutAttendance pa=new PutAttendance();
        	String table=pa.getTable(dno, sem);
        	
        	String qry="select count(Attendance)AS presentCount from "+table+" where Attendance='present' and Rollno=?;";
        	ps=con.prepareStatement(qry);
        	ps.setInt(1, rollno);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		return rs.getInt("presentCount");
        	}
    	}
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection
    	finally {
    		try {
    			if(con!=null) con.close();
    		}
    		catch(Exception e) {}
    		try {
    			if(ps!=null) ps.close();
    		}
    		catch(Exception e) {}
    		try {
    			if(rs!=null) rs.close();
    		}
    		catch(Exception e) {}
    	}
    	return 0;
    }
	
	//Method For To Get The Total Numbers Of Hours Completed For All Subjects 
	public int getOverAllClass(int rollno) {
		//Code For Getting Total Numbers Of Hours Completed For All Subjects
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number By Calling fetchDno() Method Passing Argument As Rollno To The Method
    		FetchStudent fs=new FetchStudent();
    		int dno=fs.fetchDno(rollno);
    		
    		//Getting Department Number By Calling getSem() Method Passing Argument As Rollno To The Method
    		FetchCourse fc=new FetchCourse();
        	List<CourseName>list = fc.getSem(rollno);
        	int sem=0;
        	for(CourseName course : list) {
        		sem=course.getSem();
        	}
  
        	//After Getting Department Number & Current Semester Passing The Values As Argument To getTable() Method To Get The Table Name
        	PutAttendance pa=new PutAttendance();
        	String table=pa.getTable(dno, sem);
        	
        	String qry="select count(Attendance)AS presentCount from "+table+" where Rollno=?;";
        	ps=con.prepareStatement(qry);
        	ps.setInt(1, rollno);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		return rs.getInt("presentCount");
        	}
    	}
    	catch(Exception e) {e.printStackTrace();}
    	
     	//Close The Connection Using Finally Block
    	finally {
    		try {
    			if(con!=null) con.close();
    		}
    		catch(Exception e) {}
    		try {
    			if(ps!=null) ps.close();
    		}
    		catch(Exception e) {}
    		try {
    			if(rs!=null) rs.close();
    		}
    		catch(Exception e) {}
    	}
    	return 0;
    }
	
	 //Method For To Get The Total Numbers Of Hours Attended For Particular Subjects 
		public int classAttendedBySubject(int rollno,String code) {
			//Code For Getting Total Numbers Of Hours Attended For All Subjects
	    	Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	try {
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number By Calling fetchDno() Method Passing Argument As Rollno To The Method
	    		FetchStudent fs=new FetchStudent();
	    		int dno=fs.fetchDno(rollno);
	    		
	    		//Getting Department Number By Calling getSem() Method Passing Argument As Rollno To The Method
	    		FetchCourse fc=new FetchCourse();
	        	List<CourseName>list = fc.getSem(rollno);
	        	int sem=0;
	        	for(CourseName course : list) {
	        		sem=course.getSem();
	        	}
	        	
	        	//After Getting Department Number & Current Semester Passing The Values As Argument To getTable() Method To Get The Table Name
	        	PutAttendance pa=new PutAttendance();
	        	String table=pa.getTable(dno, sem);
	        	
	        	String qry="select count(Attendance)AS presentCount from "+table+" where Attendance='present' and Rollno=? and subject_code=?;";
	        	ps=con.prepareStatement(qry);
	        	ps.setInt(1, rollno);
	        	ps.setString(2, code);
	        	rs=ps.executeQuery();
	        	while(rs.next()) {
	        		return rs.getInt("presentCount");
	        	}
	    	}
	    	catch(Exception e) {e.printStackTrace();}
	    	
	    	//Close The Connection
	    	finally {
	    		try {
	    			if(con!=null) con.close();
	    		}
	    		catch(Exception e) {}
	    		try {
	    			if(ps!=null) ps.close();
	    		}
	    		catch(Exception e) {}
	    		try {
	    			if(rs!=null) rs.close();
	    		}
	    		catch(Exception e) {}
	    	}
	    	return 0;
	    }
		
		//Method For To Get The Total Numbers Of Hours Completed For Particular Subjects 
		public int getOverAllClassBySubject(int rollno,String code) {
			//Code For Getting Total Numbers Of Hours Completed For All Subjects
	    	Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	try {
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number By Calling fetchDno() Method Passing Argument As Rollno To The Method
	    		FetchStudent fs=new FetchStudent();
	    		int dno=fs.fetchDno(rollno);
	    		
	    		//Getting Department Number By Calling getSem() Method Passing Argument As Rollno To The Method
	    		FetchCourse fc=new FetchCourse();
	        	List<CourseName>list = fc.getSem(rollno);
	        	int sem=0;
	        	for(CourseName course : list) {
	        		sem=course.getSem();
	        	}
	  
	        	//After Getting Department Number & Current Semester Passing The Values As Argument To getTable() Method To Get The Table Name
	        	PutAttendance pa=new PutAttendance();
	        	String table=pa.getTable(dno, sem);
	        	
	        	String qry="select count(Attendance)AS presentCount from "+table+" where Rollno=? and subject_code=?;";
	        	ps=con.prepareStatement(qry);
	        	ps.setInt(1, rollno);
	        	ps.setString(2, code);
	        	rs=ps.executeQuery();
	        	while(rs.next()) {
	        		return rs.getInt("presentCount");
	        	}
	    	}
	    	catch(Exception e) {e.printStackTrace();}
	    	
	     	//Close The Connection Using Finally Block
	    	finally {
	    		try {
	    			if(con!=null) con.close();
	    		}
	    		catch(Exception e) {}
	    		try {
	    			if(ps!=null) ps.close();
	    		}
	    		catch(Exception e) {}
	    		try {
	    			if(rs!=null) rs.close();
	    		}
	    		catch(Exception e) {}
	    	}
	    	return 0;
	    }
}
