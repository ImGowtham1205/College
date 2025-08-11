package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchAssignedStaff {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
  //Method For To Get Staffs Who Are Handle Subject
    public List<FetchAssignStaff>getStaff(int hodid){
    	List<FetchAssignStaff>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Staffs Who Are Handle Subject
    	try {
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		FetchHod fs=new FetchHod();
    		int dno=fs.fetchDno(hodid);
    		
    		//Getting Department Number By Calling getSem() Method Passing Hod ID As Argument To The Method
    		FetchCourse fc=new FetchCourse();
        	List<CourseName>semlist = fc.getSemForHod(hodid);
        	int sem=0;
        	for(CourseName course : semlist) {
        		sem=course.getSem();
        	}
        	
        	//Getting Table Name From getTable() Method
        	AssignStaff as=new AssignStaff();
        	String table=as.getTable(dno, sem);
        	
        	String qry="select subject_code,Subject_name,staff_name,Semseter from "+table;
        	ps=con.prepareStatement(qry);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		FetchAssignStaff fas=new FetchAssignStaff();
        		fas.setCode(rs.getString("subject_code"));
        		fas.setSubject(rs.getString("Subject_name"));
        		fas.setName(rs.getString("staff_name"));
        		fas.setSem(rs.getInt("Semseter"));
        		list.add(fas);
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
}
