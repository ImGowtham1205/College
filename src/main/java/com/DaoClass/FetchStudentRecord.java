package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchStudentRecord {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
  //Method For To Get The Student Rollno & Name 
    public List<SetStudent> fetchStudent(int sid){
    	List<SetStudent> list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Student Rollno & Name 
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() Method
    		FetchStaff fs=new  FetchStaff();
    		int dno=fs.fetchDno(sid);
    		
    		//Getting Table Name From getTable() Method
    		String table=getTable(dno);
    		
    		String qry="select Rollno,Sname from "+table+";";
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			SetStudent s=new SetStudent();
    			s.setRollno(rs.getInt("Rollno"));
    			s.setName(rs.getString("Sname"));
    			list.add(s);
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
    	return list;
    }
    
    //Method For Getting Table Name
    private String getTable(int dno) {
    	switch(dno) {
    	case 5: return "Bcom_General";
        case 10: return "Bcom_cs";
        case 15: return "Bcom_AF";
        case 20: return "Bcom_BM";
        case 25: return "Bcom_ISM";
        case 30: return "Bcom_CA";
        case 35: return "BBA";
        case 40: return "Bsc_cs";
        case 45: return "BCA";
        default: return "";
    	}
    }
    
}
