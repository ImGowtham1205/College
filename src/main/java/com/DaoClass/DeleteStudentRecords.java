package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteStudentRecords {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
    //Creating Object For FetchStudentRecord Class
    FetchStudentRecord fsr=new FetchStudentRecord();
    
    //Method For To Delete Student Record
	public void deleteStudent(int rollno,int dno ,int year) {
		 String table=fsr.getStudentTable(dno, year);
		 Connection con=null;
		 PreparedStatement ps=null;
		 
		//Code For To Delete Student Record
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(url, user, pass);
	            con.setAutoCommit(false);
	            
	            String qry = "Delete From " + table + " where Rollno = ?";
		        ps = con.prepareStatement(qry);
		        ps.setInt(1, rollno);
		        int row=ps.executeUpdate();
		        
		      //It Checks The Query Executed Successfully Or Not If True Then It Allows To Save Changes In Database
		        if(row>0)
		            con.commit();
		        else
		           con.rollback();
	            	  
	        }	        
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        //Close The Connection Using Finally Block
	        finally {
	        	try {
	        		if(ps!=null) ps.close();
	        	}catch(Exception e) {}
	        	try {
	        		if(con!=null) con.close();
	        	}catch(Exception e) {}
	        }
	}	
}