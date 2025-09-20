package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateAttendance {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Creating Object For FetchCourse & PutAttendance Class
    GetCourseDetails gc=new GetCourseDetails();
	PutAttendance pa=new PutAttendance();
	
    //Method For To Check Staff Will Mark Attendance For Selected Date & Time 
	public int checkAttendanceMarked(int sid,String code,String time,String date) {
		Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() Method
    		FetchStaff fs=new FetchStaff();
    		int dno=fs.fetchDno(sid);
   
    		//Getting Semseter By calling fetchSem() Method
        	int sem=gc.fetchSem(code);
    		
    		//Getting Table Name From getTable() Method
    		String table=pa.getAttendanceTable(dno, sem);
    		
    		//Code For To Check Any Staff Will Mark Attendance For Selected Date & Time
    		String qry="select distinct Staffid from "+table+" where subject_code=? and Atime=? and Staffid=? and Adate=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		ps.setString(2, time);
    		ps.setInt(3, sid);
    		ps.setString(4, date);
    		rs=ps.executeQuery();
    		if(rs.next()) 
    			return rs.getInt("Staffid");		
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
	
	//Method For To Update Student Attendance
	public void changeAttendance(int rollno,String status,String date,String code,String time,int sid) {
		Connection con=null;
    	PreparedStatement ps=null;
    	Statement s=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		//Getting Department Number From fetchDno() 
    		FetchStudent fs=new FetchStudent();
    		int dno=fs.fetchDno(rollno);
    		
    		//Getting Semseter By calling fetchSem() Method
        	int sem=gc.fetchSem(code);
    		
    		//Getting Table Name From getTable() Method
    		String table=pa.getAttendanceTable(dno, sem);
    		
    		//Making SQL_SAFE_UPDATES TO 0
    		s=con.createStatement();
    		String sql="SET SQL_SAFE_UPDATES = 0;";
    		s.executeUpdate(sql);
    		
    		//Code For To Update Student Attendance
    		String qry="update "+table+" set Attendance=? where Rollno=? and Adate=? and subject_code=? and Atime=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, status);
    		ps.setInt(2, rollno);
    		ps.setString(3, date);
    		ps.setString(4, code);
    		ps.setString(5, time);
    		int row=ps.executeUpdate();
    		
    		//It Checks The Query Executed Successfully Or Not If True Then It Allows To Save Changes In Database
    		if(row>0)
    			con.commit();
    		else
    			con.rollback();
    		
    		//Making SQL_SAFE_UPDATES TO 1
    		sql="SET SQL_SAFE_UPDATES = 1;";
    		s.executeUpdate(sql);
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
    			if(s!=null) s.close();
    		}
    		catch(Exception e) {}
    	}
	}
}
