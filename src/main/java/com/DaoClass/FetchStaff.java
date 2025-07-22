package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchStaff {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    private String tables[]= {"Bcom_General_staff","Bcom_cs_staff","Bcom_AF_staff","Bcom_BM_staff","Bcom_ISM_staff","Bcom_CA_staff","BBA_staff","Bsc_Cs_staff","BCA_staff"};
    
    //Method For To Get The Staff Name
    public String fetchName(int staffid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Staff Name
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		for(String table:tables) {
    			String qry="select Sname from "+table+" where staffid=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, staffid);
        		String name;
        		rs=ps.executeQuery();
        		if(rs.next()) {
        			name=rs.getString("Sname");
        			return name;
        		}
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
    
  //Method For To Get The Staff Department Number
    public int fetchDno(int staffid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Staff Department Number
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		for(String table:tables) {
    			String qry="select Dno from "+table+" where staffid=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, staffid);
        		int dno;
        		rs=ps.executeQuery();
        		if(rs.next()) {
        			dno=rs.getInt("Dno");
        			return dno;
        		}
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
    
  //Method For To Get The Staff Email
    public String fetchMail(int staffid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Staff Email
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		for(String table:tables) {
    			String qry="select Email from "+table+" where staffid=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, staffid);
        		String mail;
        		rs=ps.executeQuery();
        		if(rs.next()) {
        			mail=rs.getString("Email");
        			return mail;
        		}
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
    
  //Method For To Get The Staff Phone Number
    public String fetchPhone(int staffid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Staff Phone Number
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		for(String table:tables) {
    			String qry="select phoneno from "+table+" where staffid=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, staffid);
        		String phone;
        		rs=ps.executeQuery();
        		if(rs.next()) {
        			phone=rs.getString("phoneno");
        			return phone;
        		}
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
    
    //Method For To Get The Staff Desigination
    public String fetchDesigination(int staffid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Staff Desigination
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		for(String table:tables) {
    			String qry="select Desigination from "+table+" where staffid=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, staffid);
        		String desigination;
        		rs=ps.executeQuery();
        		if(rs.next()) {
        			desigination=rs.getString("Desigination");
        			return desigination;
        		}
    		}
    	}
    	catch(Exception e) {
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
    	return "";
    }
    
    //Method For To Get The Staff Password
    public String fetchPass(int staffid) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Staff Password
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		for(String table:tables) {
    			String qry="select pass from "+table+" where staffid=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, staffid);
        		String password;
        		rs=ps.executeQuery();
        		if(rs.next()) {
        			password=rs.getString("pass");
        			return password;
        		}
    		}
    	}
    	catch(Exception e) {
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
    	return "";
    }
}
