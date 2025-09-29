package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HodVerify {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
    //Method For To Check Login credentials For HOD By Using HOD ID & Password As Parameter
	public boolean checkHod(int hodid,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//Code For Check HOD Login Credential
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			String qry="select 1 from Hod where Hodid=? and Pass=?";
			ps=con.prepareStatement(qry);
			ps.setInt(1, hodid);
			ps.setString(2, password);
			rs=ps.executeQuery();
			return rs.next();
		}
		catch(Exception e) {e.printStackTrace();return false;}
		
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
