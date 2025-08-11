package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddStudentRecords{
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Adding New Student Record In The Particular Department
	public void addRecords(String name,String gender,String department,String blood_group,String phoneno,String address,String email) {
		
		//Getting Table Name Using getTableName() Method
		String table = getTableName(department);
		
		//Getting Department Number Uisng getDno() Method
		int dno= getDno(table);
        
        //Code For Add The New Student Record In The Particular Department
        Connection con=null;
        PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			con.setAutoCommit(false);
			ps=null;
			String qry="insert into " + table +"(sname,Gender,Dname,Dno,Phoneno,Email,Address,Blood_Group) values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(qry);
			ps.setString(1,name);
			ps.setString(2,gender);
			ps.setString(3, department);
			ps.setInt(4, dno);
			ps.setString(5, phoneno);
			ps.setString(6, email);
			ps.setString(7, address);
			ps.setString(8, blood_group);
			int row=ps.executeUpdate();
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
				if(con!=null) con.close();
			}catch(Exception e) {}
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {}
		}
	}
	
	//Method For Getting Table Name
	 private String getTableName(String department) {
		 if(department.equals("B.com(General)")) 
			 return "Bcom_general";
		 
		 else if(department.equals("B.com(Coporate Secretaryship)"))
			 return "Bcom_cs";
		 
		 else if(department.equals("B.com(A&F)"))
			 return "Bcom_AF";
		 
		 else if(department.equals("B.com(Bank Management)"))
			 return "Bcom_BM";
		 
		 else if(department.equals("B.com(ISM)"))
			 return "Bcom_ISM";
		 
		 else if(department.equals("B.com(CA)"))
			 return "Bcom_CA";
		 
		 else if(department.equals("BBA"))
			 return "BBA";
		 
		 else if(department.equals("B.Sc(Computer Science)"))
			 return "Bsc_CS";
		 
		 else if(department.equals("BCA"))
			 return "BCA";
		 
		return null;
}
	 //Method For Getting Department Number
	 private int getDno(String table) {
		  
		 if(table.equals("Bcom_general"))
			 return 5;
		 
		 else if(table.equals("Bcom_cs"))
			 return 10;
		 
		 else if(table.equals("Bcom_AF"))
			 return 15;
		 
		 else if(table.equals("Bcom_BM"))
			 return 20;
		 
		 else if(table.equals("Bcom_ISM"))
			 return 25;
		 
		 else if(table.equals("Bcom_CA"))
			return 30;
		 
		 else if(table.equals("BBA"))
			 return 35;
		 
		 else if(table.equals("Bsc_CS"))
			 return 40;
		 
		 else if(table.equals("BCA"))
			 return 45;
		 
		 return 0;
	 }
}