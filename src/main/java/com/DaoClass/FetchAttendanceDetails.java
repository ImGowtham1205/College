package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchAttendanceDetails {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For To Get The Attendance Details For Particular Student For The Selected Subject 
    public List<Attendance> getAttendanceDetails(int rollno,String code){
    	List<Attendance>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Attendance Details For Particular Student For The Selected Subject
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() 
    		FetchStudent fs=new FetchStudent();
    		int dno=fs.fetchDno(rollno);
    		
    		//Getting Current Semester From fetchSem() 
    		GetCourseDetails gc=new GetCourseDetails();
    		int sem=gc.fetchSem(code);
    		
    		//Getting Table Name From getTable() Method
    		PutAttendance pa=new PutAttendance();
    		String table=pa.getAttendanceTable(dno, sem);
    		String qry = "select Adate, Atime, Staff_name, Attendance from " + table + " where subject_code=? and rollno=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		ps.setInt(2, rollno);
    		rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			Attendance a=new Attendance();
    			a.setDate(rs.getString("Adate"));
    			a.setTime(rs.getString("Atime"));
    			a.setStaff_name(rs.getString("Staff_name"));
    			a.setAttendance(rs.getString("Attendance"));
    			list.add(a);
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
    	return list;
    }
}
