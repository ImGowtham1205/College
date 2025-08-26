package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateStaffRecords {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
  //Method For To Update Selected Staff Record Using Staff Id & Department Number As Parameter
	public void updateStaff(int staffid,String name,int dno,String email,String phone,String desigination) {
		
			//Get table From getTableName() Method
			DeleteStaffRecords dsr=new DeleteStaffRecords();
			String table = dsr.getStaffTable(dno);
			
			Connection con=null;
			PreparedStatement ps=null;
			
			//Code For Update Selected Staff Record
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(url, user, pass);
	            con.setAutoCommit(false);
	            String qry = "update " + table + " set Sname=?,Email=?,phoneno=?,Desigination=? where staffid=?";
	            ps = con.prepareStatement(qry);
	            ps.setString(1, name);
	            ps.setString(2, email);
	            ps.setString(3, phone);
	            ps.setString(4, desigination);
	            ps.setInt(5, staffid);
	            int row=ps.executeUpdate();
	            
	          //It Checks The Query Executed Successfully Or Not If True Then It Allows To Save Changes In Database
	            if(row>0)
	            	con.commit();
	            else
	            	con.rollback();
	        }
	        
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	      //Close The Connection Using Finally Block
	        finally {
	        	try {
	        		if(ps!=null) ps.close();
	        	}catch(Exception e) {}
	        	try {
	        		if(con!=null) con.close();
	        	}catch(Exception e) {}
	        }
	}
}
