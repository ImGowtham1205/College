package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchStaffName {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Creating Object For FetchStaff Class
    FetchStaff fs=new FetchStaff();
    
    //Method For To Get All Staff Name From The Department 
    public List<StaffName>getStaffName(int hodid){
    	List<StaffName> list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get All Staff Name From The Department
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() Method
    		int dno=fs.fetchDno(hodid);
    		
    		//Get table From getTableName() Method
			DeleteStaffRecords dsr=new DeleteStaffRecords();
			String table = dsr.getStaffTable(dno);
			
    		String qry="select sname,staffid from "+table+" where Desigination='Professor';";
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			StaffName sn = new StaffName();
    			sn.setName(rs.getString("sname"));
    			sn.setId(rs.getInt("staffid"));
    			list.add(sn);
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
    	return list;
    } 
}