package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateStudentRecords {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
    //Creating Object For FetchStudentRecord Class
    FetchStudentRecord fsr=new FetchStudentRecord();
    
  //Method For To Update Selected Student Record Using Student Rollno & Department Number As Parameter
	public void upadteStudent(int rollno,String name,int dno,String email,String blood,String phone,String address,int year,int sem) {
		 String table = fsr.getStudentTable(dno, year);
		 Connection con=null;
		 PreparedStatement ps=null;
		 
		 //Code For Update Selected Student Record
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(url, user, pass);
	            con.setAutoCommit(false);
	            String qry = "update " + table + " set Sname=?,Email=?,Blood_Group=?,phoneno=?,Address=?,Semester=?,Batch_Year=? where Rollno=?";
	            ps = con.prepareStatement(qry);
	            ps.setString(1, name);
	            ps.setString(2, email);
	            ps.setString(3, blood);
	            ps.setString(4, phone);
	            ps.setString(5, address);
	            ps.setInt(6, sem);
	            ps.setInt(7, year);
	            ps.setInt(8, rollno);
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