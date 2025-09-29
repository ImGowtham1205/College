package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentVerify {

    private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String password1 = "test";

  //Method For To Check Login credentials For Student By Using Student Rollno & Password As Parameter
    public boolean checkStudent(int id, String password) {
    	Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
    	
    	//Code For Check Student Login Credential
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password1);
            
            //Getting Department Number From fetchDno() Method
            FetchStudent fs=new FetchStudent();
	    	int dno=fs.fetchDno(id);
            int year=fs.fetchYear(id);
            
            //Getting Table Name By Calling getTable() Method
	    	FetchStudentRecord fsr=new FetchStudentRecord();
            String table=fsr.getStudentTable(dno, year);
            
          //Prevents The Code From Crash 
	    	if (table == null || table.trim().isEmpty()) 
	              return false;
            
            String qry = "SELECT 1 FROM " + table + " WHERE Rollno=? AND Pass=?";
            ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } 
        catch (Exception e) { e.printStackTrace(); return false;}
        
      //Close The Connection By Using Finally Block
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
    }
}
