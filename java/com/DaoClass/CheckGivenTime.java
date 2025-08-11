package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckGivenTime {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
	public String getTime(String time,String date,int sid,String code) {
		//Code For Get Time For The Given Date
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DriverManager.getConnection(url, user, pass);
			
			//Getting Department Number From fetchDno() Method Using Rollno For To Get The Argument For getTable() To Fetch The Time
			FetchStaff fs=new FetchStaff();
			int dno=fs.fetchDno(sid);
			
			//Getting Current Semester From getSem() Method Using Subject code & Department Number For To Get The Argument For getTable() To Fetch The Time
			PutAttendance pa=new PutAttendance();
			int sem=pa.getSem(code,dno);
			
			//Getting Table Name By Calling getTable() Method Using Department Number & Semester
			String table=pa.getTable(dno, sem);
			String qry="select distinct Atime from "+table+" where Atime=? and Adate=?";
			ps=con.prepareStatement(qry);
			ps.setString(1, time);
			ps.setString(2, date);
			rs=ps.executeQuery();
			while(rs.next())
				return rs.getString("Atime");
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Close The Connection Using Finally Block
    	finally {
    		try {
    			if(con!=null) con.close();
    		}catch(Exception e) {}
    		try {
    			if(ps!=null) ps.close();
    		}catch(Exception e) {}
    		try {
    			if(rs!=null) rs.close();
    		}catch(Exception e) {}
    	}
		return "";
	}
}
