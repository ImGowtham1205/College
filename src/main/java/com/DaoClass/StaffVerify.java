package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StaffVerify {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
  //Method For To Check Login credentials For Staff By Using Staff ID & Password As Parameter
	public boolean staffCheck(int sid,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//Creating Object For DeleteStaffRecords & FetchStaff Class
		DeleteStaffRecords dsr= new DeleteStaffRecords();
		FetchStaff fs=new FetchStaff();
		
		//Code For Check Staff Login Credential
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			
			//Getting Department Number From fetchDno Method()
			int dno=fs.fetchDno(sid);
			
			//Getting Table Name From getTableName() Method
	    	 String table = dsr.getStaffTable(dno);	
	    	 
	    	 //Prevents The Code From Crash 
	    	 if (table == null || table.trim().isEmpty()) 
	                return false;
	            	    	 
	    	 String qry = "select 1 from " + table + " where staffid=? and pass=?;";
			 ps=con.prepareStatement(qry);
			 ps.setInt(1, sid);
			 ps.setString(2, password);
			 rs=ps.executeQuery();
			 return rs.next();			
		}
		catch(Exception e) {e.printStackTrace(); return false;}
		
		//Close The Connection By Using Finally Block
		finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {}
			try {
				if(rs!=null) rs.close();
			}catch(Exception e) {}
			try {
				if(con!=null) con.close();
			}catch(Exception e) {}
		}
	}
}
