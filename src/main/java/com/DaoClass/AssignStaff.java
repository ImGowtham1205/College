package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AssignStaff {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Creating Object For FetchStaff & GetCourseDetails Class
    FetchStaff fs=new FetchStaff();
    GetCourseDetails gc=new GetCourseDetails();
    
    //Method For Assign The Staff For Given Paper
	public void assignStaff(int hodid,String name,String subject,String code,int sid,int year,int sem) {
		   	
    	//Getting Department Number By calling fetchDno() Method
    	int dno=fs.fetchDno(hodid);
    	   	
    	//Code For To Assign A Subject To The Staff
    	Connection con=null;
    	PreparedStatement ps=null;
    	PreparedStatement ps1=null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		con.setAutoCommit(false);
    		
    		//Getting Table Name By Calling getTable() Method
    		String table=getAssignedStaffTable(dno,sem);
    		
    		String qry="insert into "+table+" values(?,?,?,?,?,?,?);";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		ps.setString(2, subject);
    		ps.setString(3, name);
    		ps.setInt(4, dno);
    		ps.setInt(5, sem);
    		ps.setInt(6, sid);
    		ps.setInt(7, year);
    		int row=ps.executeUpdate();
    		
    		//It Checks The Query Executed Successfully Or Not
    		if(row <=0) {
				con.rollback();
				return;
			}
    		
    		/*Code For Change Subject_Assign To Yes In The Particular Department Table
			  Getting Query By Calling getQry() Method Using Department Number As Arugment For To Change Subject_Assign To Yes In Particular Department
			 */
    		String qry2=getQry(dno);
    		ps1=con.prepareStatement(qry2);
    		ps1.setInt(1, sid);
    		int row1=ps1.executeUpdate();
    		
    		//It Checks The Query Executed Successfully Or Not
    		if(row1 <=0) {
				con.rollback();
				return;
			}
    		
    		//When The Both Query Is Executed Successfully Then It Allows To Save Changes In Database
			con.commit();  		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
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
    			if(ps1!=null) ps1.close();
    		}
    		catch(Exception e) {}
    	}
    }
    
	//Method For To Check The Subject Is Already Assigend 
	public String subjectName(int hodid,String code) {
		
		//Getting Semseter By calling fetchSem() Method
    	int sem=gc.fetchSem(code);
    	
    	//Getting Department Number By calling fetchDno() Method
    	int dno=fs.fetchDno(hodid);
    	
    	//Code For The Subject Is Already Assigned Or Not
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String table=getAssignedStaffTable(dno,sem);
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Subject_name from "+table+" where subject_code=?;";
    		ps=con.prepareStatement(qry);
    		ps.setString(1, code);
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getString("Subject_name");
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
		return "";
	}
	
	//Method For To Check The Staff Already Assign The Subject For This Semester
	public int assignStaffCheck(int id,int hodid,String code) {
		
		//Getting Semseter By calling fetchSem() Method
    	int sem=gc.fetchSem(code);
    	
    	//Getting Department Number By calling fetchDno() Method
    	int dno=fs.fetchDno(hodid);
    	
		//Code For The Staffid Is Exist Or Not In Table
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String table=getAssignedStaffTable(dno,sem);
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String qry="select Staffid from "+table+" where Staffid=?;";
    		ps=con.prepareStatement(qry);
    		ps.setInt(1, id);
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getInt("Staffid");
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
		return 0;
	}
	
	//Method For Get Table Name
    protected String getAssignedStaffTable(int dno, int sem) {
        if (dno == 5 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_General_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 10 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_cs_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 15 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_AF_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 20 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_BM_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 25 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_ISM_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 30 && sem >= 1 && sem <= 6) 
            return String.format("Bcom_CA_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 35 && sem >= 1 && sem <= 6) 
            return String.format("BBA_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        else if (dno == 40 && sem >= 1 && sem <= 6) 
            return String.format("Bsc_cs_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        
        else if (dno == 45 && sem >= 1 && sem <= 6) 
            return String.format("BCA_%d%ssem_staff", sem,(sem == 1) ? "st" : (sem == 2) ? "nd" : (sem == 3) ? "rd" : "th");
        
        return " ";
    }
    
  //Method For To Generate The Query Dynamically By Using Department Number As parameter For To Change Subject_Assign To Yes In The Particular Department Table
    protected String getQry(int dno) {
    	switch(dno) {
    		case 5:  return "update Bcom_General_staff set subject_assign='Yes' where staffid=?";
    		case 10: return "update Bcom_cs_staff set subject_assign='Yes' where staffid=?";
    		case 15: return "update Bcom_AF_staff set subject_assign='Yes' where staffid=?";
    		case 20: return "update Bcom_BM_staff set subject_assign='Yes' where staffid=?";
    		case 25: return "update Bcom_ISM_staff set subject_assign='Yes' where staffid=?";
    		case 30: return "update Bcom_CA_staff set subject_assign='Yes' where staffid=?";
    		case 35: return "update BBA_staff set subject_assign='Yes' where staffid=?";
    		case 40: return "update Bsc_Cs_staff set subject_assign='Yes' where staffid=?";
    		case 45: return "update BCA_staff set subject_assign='Yes' where staffid=?";	
    		default:return "";
    	}
    }
}