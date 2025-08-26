package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PutAttendance {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Put Attendance For Student
	public void markAttendance(String date,String code,String subject,int rollno,String Attandance,int sid,String time,int sem) {
		
		//Code For Put Attendance for Student
		Connection con=null;
		PreparedStatement ps=null;
		FetchStudent student=new FetchStudent();
		FetchStaff staff=new FetchStaff();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			con.setAutoCommit(false);
			
			//Getting Student & Staff Name By Calling fetchName() Method Using Student RollNo & Staff ID
			String student_name=student.fetchName(rollno);
			String staff_name=staff.fetchName(sid);
			
			//Getiing Department Number For To Get The Table 
			int dno=student.fetchDno(rollno);
			
			//Getting Table Name By Calling getTable() Method Using Department Number & Semester
			String table=getAttendanceTable(dno,sem);
			
			String qry="insert into "+table+" values(?,?,?,?,?,?,?,?,?);";
			ps=con.prepareStatement(qry);
			ps.setString(1, date);
			ps.setString(2, subject);
			ps.setString(3, code);
			ps.setInt(4, rollno);
			ps.setString(5, student_name);
			ps.setString(6, Attandance);
			ps.setInt(7, sid);
			ps.setString(8, staff_name);
			ps.setString(9, time);
			int row=ps.executeUpdate();
			
			//It Checks The Query Executed Successfully Or Not If True Then It Allows To Save Changes In Database
			if(row>0)
				con.commit();
			else
				con.rollback();
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Close The Connection
		finally {
			try {
				if(con!=null) con.close();
			}catch(Exception e) {}
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {}
		}
	}
	
	//Method For Get Table Name For Store The Attendance Details in Correct Table
    protected String getAttendanceTable(int dno, int sem) {
        if (dno == 5 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_General_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 10 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_cs_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 15 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_AF_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 20 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_BM_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 25 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_ISM_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 30 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_CA_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 35 && sem >= 1 && sem <= 6) 
            return String.format("BBA_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 40 && sem >= 1 && sem <= 6) 
            return String.format("Bsc_cs_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
             
        else if (dno == 45 && sem >= 1 && sem <= 6) 
            return String.format("BCA_%d%ssem_Attendance", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        return " ";
    }
    
   //Method For Getting Semester
    protected int getSem(String code,int dno) {
    	
    	//Code For getting Current Semester Using Subject Code
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Geting Table Name By Using Department Number For Getting Current Semester  
    	FetchCourse fc=new FetchCourse();
    	String table=fc.getCourseTable(dno);
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Semseter from "+table+" where subject_code=?";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			return rs.getInt("Semseter");
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
    	return 0;
    }   
}