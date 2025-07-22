package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchAssignSubject {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For To Get Assigned Subject For Selected Staff By Using Staff Id As parameter
	public List<FetchSubject>getAssignedSubject(int sid){
		List<FetchSubject>list=new ArrayList<>();
		
		//Getting Hod Id For Getting Department Number & Current Semester
		int hodid=getHodId(sid);
		
		//Code For Getting The Assigned Subject Table Name 
		FetchCourse fc=new FetchCourse();
		List<CourseName>list1 = fc.getCourseName(hodid);
    	int sem=0,dno=0;
    	
    	for(CourseName course : list1) {
    		sem=course.getSem();
    		dno=course.getDno();
    	}
    	
    	AssignStaff as=new AssignStaff();
    	String table=as.getTable(dno,sem);
    	
    	/*
    	 * After Getting Table Name For Assign Subject
    	 * Code For Getting Assigned Subject For Selected Staff By Using Staff Id
    	 */
		Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
		try {
			con=DriverManager.getConnection(url, user, pass);
			String qry="select Subject_name,subject_code from "+table+" where Staffid=?;";
			ps=con.prepareStatement(qry);
			ps.setInt(1, sid);
			rs=ps.executeQuery();
			while(rs.next()) {
				FetchSubject fs=new FetchSubject();
				fs.setSubject(rs.getString("Subject_name"));
				fs.setCode(rs.getString("subject_code"));
				list.add(fs);
			}
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Close The Connection
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
	
	//Method For Getting Hod ID By Using Staff ID
	private int getHodId(int sid) {
		Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
		try {
			con=DriverManager.getConnection(url, user, pass);
			FetchStaff fs=new FetchStaff();
			
			//Getting Department Number By Using Staff ID For Generating The Query
			int dno=fs.fetchDno(sid);
			
			//Getting Qry From getQry() Method
			String qry=getQry(dno);
			ps=con.prepareStatement(qry);
			rs=ps.executeQuery();
			while(rs.next())
				return rs.getInt("staffid");
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
	
	//Method For Generate Qry To Get Hod ID
	private String getQry(int dno) {
		switch(dno) {
		case 5:return"select staffid from Bcom_General_staff where Desigination='HOD';";
		case 10:return"select staffid from Bcom_cs_staff where Desigination='HOD';";
		case 15:return"select staffid from Bcom_AF_staff where Desigination='HOD';";
		case 20:return"select staffid from Bcom_BM_staff where Desigination='HOD';";
		case 25:return"select staffid from Bcom_ISM_staff where Desigination='HOD';";
		case 30:return"select staffid from Bcom_CA_staff where Desigination='HOD';";
		case 35:return"select staffid from BBA_staff where Desigination='HOD';";
		case 40:return"select staffid from Bsc_Cs_staff where Desigination='HOD';";
		case 45:return"select staffid from BCA_staff where Desigination='HOD';";
		default:return "";
		}
		
	}
}
