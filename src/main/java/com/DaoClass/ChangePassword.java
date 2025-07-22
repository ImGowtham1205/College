package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChangePassword {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	private String tables[]= {"Bcom_general","Bcom_cs","Bcom_AF","Bcom_BM","Bcom_ISM","Bcom_CA","BBA","Bsc_CS","BCA"};

	//Method For To Change Student Password By Using Student Rollno & password As Parameter
	public void changePass(int id,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//Code For Change Student Password
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			con.setAutoCommit(false);
			for(String table:tables) {
				String qry="update "+table+" set Pass=? where Rollno=?;";
				ps=con.prepareStatement(qry);
				ps.setString(1, password);
				ps.setInt(2, id);
				int row=ps.executeUpdate();
				if(row>0)
					con.commit();
				else
					con.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Close The Connection By Using Finally Block
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
	}
	
	//Method For To Change HOD Password By Using HOD ID & password As Parameter
	public void changeHodPass(int id,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		FetchHod fh=new FetchHod();
		
		//Code For Change HOD Password In Hod Table
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			con.setAutoCommit(false);
			int dno=fh.fetchDno(id);
			String qry="update hod set Pass=? where Hodid=?;";
			ps=con.prepareStatement(qry);
			ps.setString(1, password);
			ps.setInt(2, id);
			int row =ps.executeUpdate();
			if(row <=0) {
				con.rollback();
				return;
			}
			
			/*Code For Change HOD Password In The Particular Department Table
			  Getting Query By Calling getQry() Method Using Student RollNo As Arugment For To Change The HOD Password In Particular Department
			 */
			String qry2=getQry(dno);
			ps1=con.prepareStatement(qry2);
			ps1.setString(1, password);
			ps1.setInt(2, id);
			int row1 =ps1.executeUpdate();
			if(row1 <=0) {
				con.rollback();
				return;
			}
			con.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Close The Connection By Using Finally Block
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
				if(ps1!=null) ps1.close();
			}
			catch(Exception e) {}
		}
	}
	
	//Method For To Get The Student Current Password By Using Student Rollno As Parameter
	public String getPass(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getpass;
		
		//Code For Get The Student Current Password
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			for(String table : tables) {
				String qry="select pass from "+table+" where Rollno=?;";
				ps=con.prepareStatement(qry);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
					getpass=rs.getString("Pass");
					return getpass;
				}		
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Close The Connection By Using Finally Block
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
		return " ";
	}
	
	//Method For To Change Staff Password By Using Staff ID & password As Parameter
	public void changeStaffPass(int id,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		
		//Code For Change Staff Password
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			con.setAutoCommit(false);
			FetchStaff fs=new FetchStaff();
			int dno=fs.fetchDno(id);
			String qry=getQry(dno);
			ps=con.prepareStatement(qry);
			ps.setString(1, password);
			ps.setInt(2, id);
			int row=ps.executeUpdate();
			if(row>0)
				con.commit();
			else
				con.rollback();
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Close The Connection By Using Finally Block
		finally {
			try {
				if(con!=null) con.close();
			}
			catch(Exception e) {}
			try {
				if(ps!=null) ps.close();
			}
			catch(Exception e) {}	
		}
	}
	
	//Method For To Generate The Query Dunamically By Using Department Number As parameter For To Change HOD Password In The Particular Department Table
	private String getQry(int dno) {
		switch(dno) {
		case 5: return "update Bcom_General_staff set pass=? where staffid=?;";
		case 10:return "update Bcom_cs_staff set pass=? where staffid=?;";
		case 15:return "update Bcom_AF_staff set pass=? where staffid=?;";
		case 20:return "update Bcom_BM_staff set pass=? where staffid=?;";
		case 25:return "update Bcom_ISM_staff set pass=? where staffid=?;";
		case 30:return "update Bcom_CA_staff set pass=? where staffid=?;";
		case 35:return "update BBA_staff set pass=? where staffid=?;";
		case 40:return "update Bsc_Cs_staff set pass=? where staffid=?;";
		case 45:return "update BCA_staff set pass=? where staffid=?;";
		default:return " ";	
		}
	}
}
