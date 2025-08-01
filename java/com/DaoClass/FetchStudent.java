package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchStudent {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    private String tables[]= {"Bcom_general","Bcom_cs","Bcom_AF","Bcom_BM","Bcom_ISM","Bcom_CA","BBA","Bsc_CS","BCA"};
    
    //Method For To Get The Student Name
	public String fetchName(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
    	String name;
    	
    	//Code For To Get The Student Name
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String  table:tables) {
            	String qry = "SELECT Sname FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
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
	
	//Method For To Get The Student Department Number
	public int fetchDno(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
    	int no;
    	
    	//Code For To Get The Student Department Number
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String table:tables) {
            	String qry = "SELECT Dno FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
                rs=ps.executeQuery();
                if(rs.next()) {
                	no=rs.getInt("Dno");
                	return no;
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

	//Method For To Get The Student Department Name
	public String fetchDname(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
    	String dname;
    	
    	//Code For To Get The Student Department Name
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String table:tables) {
            	String qry = "SELECT Dname FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
                rs=ps.executeQuery();
                if(rs.next()) {
                	dname=rs.getString("Dname");
                	return dname;
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
	
	//Method For To Get The Student Gender
	public String fetchGender(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
		String gender;
		
		//Code For To Get The Student Gender
		try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String table:tables) {
            	String qry = "SELECT Gender FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
                rs=ps.executeQuery();
                if(rs.next()) {
                	gender=rs.getString("Gender");
                	return gender;
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
	
	//Method For To Get The Student Blood Group
	public String fetchBlood(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
		String blood;
		
		//Code For To Get The Student Blood Group
		try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String table:tables) {
            	String qry = "SELECT Blood_Group FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
                rs=ps.executeQuery();
                if(rs.next()) {
                	blood=rs.getString("Blood_Group");
                	return blood;
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
	
	//Method For To Get The Student Phone Number
	public String fetchPhone(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
		String phone;
		
		//Code For To Get The Student Phome Number
		try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String table:tables) {
            	String qry = "SELECT phoneno FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
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
	
	//Method For To Get The Student Email
	public String fetchMail(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
		String mail;
		
		//Method For To Get The Student Email
		try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String table:tables) {
            	String qry = "SELECT Email FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
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
	
	//Method For To Get The Student Address
	public String fetchAddress(int rollno){
		Connection con=null;
    	ResultSet rs=null;
    	PreparedStatement ps=null;
		String address;
		
		//Code For To Get The Student Address
		try {
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            for(String table:tables) {
            	String qry = "SELECT Address FROM " + table + " WHERE Rollno=?";
                ps = con.prepareStatement(qry);
                ps.setInt(1, rollno);
                rs=ps.executeQuery();
                if(rs.next()) {
                	address=rs.getString("Address");
                	return address;
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
}