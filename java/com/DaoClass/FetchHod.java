package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchHod {

	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For To Get The Hod Name
    public String fetchName(int hodid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Hod Name
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Hname from Hod where Hodid=?;";
    		ps=con.prepareStatement(qry);
    		ps.setInt(1, hodid);
    		String name;
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			name=rs.getString("Hname");
    			return name;
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
    	return "";
    }
    
  //Method For To Get The Hod Department Number
    public int fetchDno(int hodid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Hod Department Number
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Dno from Hod where Hodid=?;";
    		ps=con.prepareStatement(qry);
    		ps.setInt(1, hodid);
    		int no;
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			no=rs.getInt("Dno");
    			return no;
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
    
  //Method For To Get The Hod Password
    public String fetchPass(int hodid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Hod Password
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Pass from Hod where Hodid=?;";
    		ps=con.prepareStatement(qry);
    		ps.setInt(1, hodid);
    		String name;
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			name=rs.getString("Pass");
    			return name;
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
    	return "";
    }
}
