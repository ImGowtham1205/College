package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminName {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Getting Admin Name By Using Admin Id As Parameter
	public String fetchName(int aid) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//Code For Getting Admin Name
		try {
			String name="";
			con=DriverManager.getConnection(url, user, pass);
			String qry="select Aname from admin_staff where Adminid=?";
			ps=con.prepareStatement(qry);
			ps.setInt(1, aid);
			rs=ps.executeQuery();
			if(rs.next()) {
				name=rs.getString("Aname");
				return name;
			}
			rs.close();
			ps.close();
			con.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Close The Connection By Using Finally Block
		finally {
			try {
				if(rs!=null) rs.close();
			}catch(Exception e) {}
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {}
			try {
				if(con!=null) con.close();
			}catch(Exception e) {}
		}
	return "";
	}
}
