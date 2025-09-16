package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminVerify {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
    //Method For To Check Login credentials For Admin By Using Admin ID & Password As Parameter
	public boolean checkAdmin(int aid,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//Code For Check Admin Login Credential
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			String qry="select 1 from admin_staff where Adminid=? and pass=?";
			ps=con.prepareStatement(qry);
			ps.setInt(1, aid);
			ps.setString(2, password);
			rs=ps.executeQuery();
			return rs.next();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//Close The Connection By Using Finally Block
		finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {}
			try {
				if(rs!=null) rs.close();
			}catch(Exception e) {}
			try {
				if(con!=null) con.close();
			}catch(Exception e) {}
		}
	}
}