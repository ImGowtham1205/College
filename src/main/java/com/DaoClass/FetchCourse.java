package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchCourse {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For To Get The Subject Details For The Given Semester
    public List<CourseName> getCourseName(int hodid){
    	List<CourseName> list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Subject Details For The Given Semester
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() 
    		FetchHod fh=new FetchHod();
    		int dno=fh.fetchDno(hodid);
    		
    		//Getting Table Name From getTable() 
    		String table=getTable(dno);
    		
    		String qry="SELECT Subject_name, subject_code , Dno , Semseter FROM " + table + " where Semseter=5";
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			CourseName c=new CourseName();
    			c.setCoursename(rs.getString("Subject_name"));
    			c.setCoursecode(rs.getString("subject_code"));
    			c.setDno(rs.getInt("Dno"));
    			c.setSem(rs.getInt("Semseter"));
    			list.add(c);
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
    
  //Method For To Get The Subject Details For The Given Semester By Using Student Rollno As Parameter
    public List<CourseName> getCourseNameByStudent(int id){
    	List<CourseName> list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Subject Details For The Given Semester
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		FetchStudent fh=new FetchStudent();
    		int dno=fh.fetchDno(id);
    		
    		//Getting Table Name From getTable() 
    		String table=getTable(dno);
    		
    		String qry="SELECT Subject_name, subject_code ,Semseter, Credits FROM " + table + " where Semseter=5";
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			CourseName c=new CourseName();
    			c.setCoursename(rs.getString("Subject_name"));
    			c.setCoursecode(rs.getString("subject_code"));
    			c.setSem(rs.getInt(4));
    			c.setCredits(rs.getInt("Credits"));
    			list.add(c);
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
    
  //Method For To Get The Subject Details For All Semseter By Using Student Rollno As Parameter
    public List<CourseName> getAllCourseName(int id){
    	List<CourseName> list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Subject Details For All Semseter
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		FetchStudent fh=new FetchStudent();
    		int dno=fh.fetchDno(id);
    		
    		//Getting Table Name From getTable() 
    		String table=getTable(dno);
    		
    		String qry="SELECT Subject_name, subject_code ,Semseter, Credits FROM " + table;
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			CourseName c=new CourseName();
    			c.setCoursename(rs.getString("Subject_name"));
    			c.setCoursecode(rs.getString("subject_code"));
    			c.setSem(rs.getInt("Semseter"));
    			c.setCredits(rs.getInt("Credits"));
    			list.add(c);
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
    
  //Method For To Get Semseter
    public List<CourseName>getSem(int id){
    	List<CourseName> list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Semseter
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		FetchStudent fh=new FetchStudent();
    		int dno=fh.fetchDno(id);
    		
    		//Getting Table Name From getTable()
    		String table=getTable(dno);
    		
    		String qry="SELECT Semseter FROM " + table+" where semseter=5;";
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			CourseName c=new CourseName();
    			c.setSem(rs.getInt("Semseter"));
    			list.add(c);
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
    
    //Method For To Get The Table Name
    protected String getTable(int dno) {
    	switch(dno) {
    	case 5: return "Bcom_General_Course";
        case 10: return "Bcom_cs_Course";
        case 15: return "Bcom_AF_Course";
        case 20: return "Bcom_BM_Course";
        case 25: return "Bcom_ISM_Course";
        case 30: return "Bcom_CA_Course";
        case 35: return "BBA_Course";
        case 40: return "Bsc_cs_Course";
        case 45: return "BCA_Course";
        default: return "";
    	}
    }
}
