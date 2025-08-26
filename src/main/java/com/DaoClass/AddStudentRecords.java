package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddStudentRecords{
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Creating Object For FetchStudentRecord Class
    FetchStudentRecord fsr=new FetchStudentRecord();
    
    //Method For Adding New Student Record In The Particular Department
	public void addRecords(String name,String gender,String department,String blood_group,String phoneno,String address,String email ,int year,int sem) {
		
		//Getting Department Number Uisng getDno() Method
		int dno= getDno(department);
        
		//Getting Table Name Using getTableName() Method
		String table = fsr.getStudentTable(dno, year);
		
        //Code For Add The New Student Record In The Particular Department
        Connection con=null;
        PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			con.setAutoCommit(false);
			
			String qry="insert into " + table +"(sname,Gender,Dname,Dno,Phoneno,Email,Address,Blood_Group,Semester,Batch_Year) values(?,?,?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(qry);
			ps.setString(1,name);
			ps.setString(2,gender);
			ps.setString(3, department);
			ps.setInt(4, dno);
			ps.setString(5, phoneno);
			ps.setString(6, email);
			ps.setString(7, address);
			ps.setString(8, blood_group);
			ps.setInt(9, sem);
			ps.setInt(10, year);
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
				if(con!=null) con.close();
			}catch(Exception e) {}
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {}
		}
	}
	
	
	 //Method For Getting Department Number
	 private int getDno(String dep) {
		  
		 if(dep.equals("B.com(General)"))
			 return 5;
		 
		 else if(dep.equals("B.com(Coporate Secretaryship)"))
			 return 10;
		 
		 else if(dep.equals("B.com(A&F)"))
			 return 15;
		 
		 else if(dep.equals("B.com(Bank Management)"))
			 return 20;
		 
		 else if(dep.equals("B.com(ISM)"))
			 return 25;
		 
		 else if(dep.equals("B.com(CA)"))
			return 30;
		 
		 else if(dep.equals("BBA"))
			 return 35;
		 
		 else if(dep.equals("B.Sc(Computer Science)"))
			 return 40;
		 
		 else if(dep.equals("BCA"))
			 return 45;
		 
		 return 0;
	 }
}