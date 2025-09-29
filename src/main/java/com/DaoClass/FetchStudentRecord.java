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
    public List<SetStudent> fetchStudent(int sid,int year){
    	List<SetStudent> list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Student Rollno & Name 
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() Method
    		FetchStaff fs=new FetchStaff();
    		int dno=fs.fetchDno(sid);
    		
    		//Getting Table Name From getTable() Method
    		String table=getStudentTable(dno,year);
    		
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
    
    //Method For Getting Student Table Name
    protected String getStudentTable(int dno,int year) {
    	if (dno == 5 && year < 3) 
	        return "Bcom_general_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 5 && year == 3) 
	        return "Bcom_general";
    	
	    if(dno==10 &&year <3)
	    	return "Bcom_cs_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 10 && year == 3) 
	        return "Bcom_cs";
	    
	    if(dno==15 &&year <3)
	    	return "Bcom_AF_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 15 && year == 3) 
	        return "Bcom_AF";
	    
	    if(dno==20 &&year <3)
	    	return "Bcom_BM_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 20 && year == 3) 
	        return "Bcom_BM";
	    
	    if(dno==25 &&year <3)
	    	return "Bcom_ISM_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 25 && year == 3) 
	        return "Bcom_ISM";
	    
	    if(dno==30 &&year <3)
	    	return "Bcom_CA_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 30 && year == 3) 
	        return "Bcom_CA";
	    
	    if(dno==35 &&year <3)
	    	return "BBA_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 35 && year == 3) 
	        return "BBA";
	    
	    if(dno==40 &&year <3)
	    	return "Bsc_CS_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 40 && year == 3) 
	        return "Bsc_CS";
	    
	    if(dno==45 &&year <3)
	    	return "BCA_" + year + (year == 1 ? "st" : "nd") + "_year";
	    else if (dno == 45 && year == 3) 
	        return "BCA";
	    
	    return "";
    }
    
}
