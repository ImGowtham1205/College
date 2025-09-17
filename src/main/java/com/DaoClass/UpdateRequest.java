package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateRequest {

	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Make The Student Request Log Update In Database
    public void updateStudentRequest(int id,int rollno,String status) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		
    		//Getting Department Number And Year From fetchDno() And fetchYear() Method
    		FetchStudent fs=new FetchStudent();
    		int dno=fs.fetchDno(rollno);
    		int year=fs.fetchYear(rollno);
    		
    		//Geting Table Name From getStudentRequestTable() Method
    		
    		AdminRequest ar=new AdminRequest();
    		String table=ar.getStudentRequestTable(dno,year);
    		
    		String qry="update "+table+" set progress=? where reqid=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, status);
    		ps.setInt(2, id);
    		int row=ps.executeUpdate();
    		
    		//It Checks The Query Executed Successfully Or Not If True Then It Allows To Save Changes In Database
    		if(row>0)
    			con.commit();
    		else
    			con.rollback();
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
    			}
    }
    
    //Method For Make The Staff & Hod Request Log Update In Database
    public void updateStaffRequest(int id,int staffid,String status) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		
    		//Getting Department Number From fetchDno() Method
    		FetchStudent fs=new FetchStudent();
    		int dno=fs.fetchDno(staffid);
    		
    		//Geting Table Name From getStaffRequestTable() Method
    		AdminRequest ar=new AdminRequest();
    		String table=ar.getStaffRequestTable(dno);
    		
    		String qry="update "+table+" set progress=? where reqid=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, status);
    		ps.setInt(2, id);
    		int row=ps.executeUpdate();
    		
    		//It Checks The Query Executed Successfully Or Not If True Then It Allows To Save Changes In Database
    		if(row>0)
    			con.commit();
    		else
    			con.rollback();
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
    			}
    }

}
