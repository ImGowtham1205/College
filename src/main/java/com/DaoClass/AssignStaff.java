package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AssignStaff {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Assign The Staff For Given Paper
	public void assignStaff(int hodid,String name,String subject,String code,int sid) {
		
		//Code For Getting Table Name
    	FetchCourse fc=new FetchCourse();
    	List<CourseName>list = fc.getCourseName(hodid);
    	int sem=0,dno=0;
    	
    	for(CourseName course : list) {
    		sem=course.getSem();
    		dno=course.getDno();
    	}
    	
    	//Code For To Assign A Subject To The Staff
    	Connection con=null;
    	PreparedStatement ps=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		String table=getTable(dno,sem);
    		String qry="insert into "+table+" values(?,?,?,?,?,?);";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		ps.setString(2, subject);
    		ps.setString(3, name);
    		ps.setInt(4, dno);
    		ps.setInt(5, sem);
    		ps.setInt(6, sid);
    		int row=ps.executeUpdate();
    		if(row>0)
    			con.commit();
    		else
    			con.rollback();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
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
    	}
    }
    
	//Method For To Check The Subject Is Already Assigend 
	public String subjectName(int hodid,String code) {
		//Code For Getting Table Name
		FetchCourse fc=new FetchCourse();
    	List<CourseName>list = fc.getCourseName(hodid);
    	int sem=0,dno=0;
    	
    	for(CourseName course : list) {
    		sem=course.getSem();
    		dno=course.getDno();
    	}
    	
    	//Code For The Subject Is Already Assigned Or Not
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String table=getTable(dno,sem);
    	try {
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Subject_name from "+table+" where subject_code=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getString("Subject_name");
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
		return "";
	}
	
	//Method For To Check The Staff To Assign The Subject For This Semester
	public int assignStaffCheck(int id,int hodid) {
		//Code For Getting Table Name  
		FetchCourse fc=new FetchCourse();
    	List<CourseName>list = fc.getCourseName(hodid);
    	int sem=0,dno=0;
    	
    	for(CourseName course : list) {
    		sem=course.getSem();
    		dno=course.getDno();
    	}
    	//Code For The Staffid Is Exist Or Not In Table
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String table=getTable(dno,sem);
    	try {
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Staffid from "+table+" where Staffid=?;";
    		ps=con.prepareStatement(qry);
    		ps.setInt(1, id);
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getInt("Staffid");
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
	
	//Method For Get Table Name
    protected String getTable(int dno, int sem) {
        if (dno == 5 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_General_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 10 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_cs_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 15 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_AF_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 20 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_BM_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 25 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_ISM_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 30 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_CA_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 35 && sem >= 1 && sem <= 6) 
            return String.format("BBA_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 40 && sem >= 1 && sem <= 6) 
            return String.format("Bsc_cs_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        
        else if (dno == 45 && sem >= 1 && sem <= 6) 
            return String.format("BCA_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        return " ";
    }
}