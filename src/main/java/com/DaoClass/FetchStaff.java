package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchStaff {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
   
    //Creating Object For DeleteStaffRecords Class
    DeleteStaffRecords dsr= new DeleteStaffRecords();
        
    /*Fetching Staff Name,Phone Number,Mail,Desigination,Password & Any Subject Is Assign To That Staff Or Not
    From The Database By Calling This Method Declared Below Through The Help OF fetchColumnValue() Method*/
    
    public String fetchName(int staffid) {
    	return fetchColumnValue(staffid, "Sname");
    }
      
    public String fetchMail(int staffid) {
    	return fetchColumnValue(staffid, "Email");
    }
      
    public String fetchPhone(int staffid) {
    	return fetchColumnValue(staffid, "phoneno");
    }
       
    public String fetchDesigination(int staffid) {
    	return fetchColumnValue(staffid, "Desigination");
    }
       
    public String fetchPass(int staffid) {
    	return fetchColumnValue(staffid, "pass");
    }
      
    public String assignSubject(int staffid) {
    	return fetchColumnValue(staffid, "subject_assign");
    }
    
  //Method For To Get The Staff Department Number
    public int fetchDno(int staffid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Staff Department Number
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String tables[]= {"Bcom_General_staff","Bcom_cs_staff","Bcom_AF_staff","Bcom_BM_staff","Bcom_ISM_staff","Bcom_CA_staff","BBA_staff","Bsc_Cs_staff","BCA_staff"};
    		for(String table:tables) {
    			String qry="select Dno from "+table+" where staffid=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, staffid);
        		rs=ps.executeQuery();
        		if(rs.next()) 
        			return rs.getInt("Dno");
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
    
    //Method Use For To Fetch The Student Details Based On columnName Parameter 
    private String fetchColumnValue(int rollno, String columnName) {
    int dno = fetchDno(rollno);
 
    // If The Given Student Rollno Not Found DB It Returns null
    if (dno == 0)
        return null;
    
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    try {
    	Class.forName("com.mysql.jdbc.Driver");
    	con=DriverManager.getConnection(url, user, pass);
    	
    	//Getting Table Name From getTableName() Method
    	String table = dsr.getStaffTable(dno);
        String qry = "select " + columnName + " from " + table + " where staffid=?";
        ps=con.prepareStatement(qry);
        ps.setInt(1, rollno);
        rs=ps.executeQuery();
        if(rs.next())
        	return rs.getString(columnName);           
    }
     catch (Exception e) {
        e.printStackTrace();
    }

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
    	return null;
	}  
}