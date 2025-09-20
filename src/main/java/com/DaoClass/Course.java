package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Course {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
   
    //Creating Object For FetchCourse Class
    FetchCourse fc=new FetchCourse();
    
    //Method For To Add Course For Selected Department
    public void addCourse(String code,String subject,int dno,int sem,int credits,int year) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		
    		//Getting Table From getCourseTable() Method
    		String table=fc.getCourseTable(dno);
    		
    		String qry="insert into "+table+" values(?,?,?,?,?,?);";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		ps.setString(2, subject);
    		ps.setInt(3, dno);
    		ps.setInt(4, sem);
    		ps.setInt(5, credits);
    		ps.setInt(6, year);
    		int row=ps.executeUpdate();
    		if(row>0)
    			con.commit();
    		else
    			con.rollback();
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
    	}
    }
    
  //Method For To Update Selected Course Details
    public void updateCourse(String code,String subject,int dno,int sem,int credits,int year) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		
    		//Getting Table From getCourseTable() Method
    		String table=fc.getCourseTable(dno);
    		
    		String qry="update "+table+" set Subject_name=?,Dno=?,Semseter=?,Credits=?,cyear=? where subject_code=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, subject);
    		ps.setInt(2, dno);
    		ps.setInt(3, sem);
    		ps.setInt(4, credits);
    		ps.setInt(5, year);
    		ps.setString(6, code);
    		int row=ps.executeUpdate();
    		if(row>0)
    			con.commit();
    		else
    			con.rollback();
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
    	}
    }
    
  //Method For To Delete Selected Course Details
    public void deleteCourse(String code,int dno) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		
    		//Getting Table From getCourseTable() Method
    		String table=fc.getCourseTable(dno);
    		
    		String qry="delete from "+table+" where subject_code=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		int row=ps.executeUpdate();
    		if(row>0)
    			con.commit();
    		else
    			con.rollback();
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
    	}
    }
}
