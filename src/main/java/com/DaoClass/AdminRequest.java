package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AdminRequest {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Sending Student Request To Admin
    public void sendStudentRequest(int rollno,String request) {
    	
    	//Code For Sending Student Request To Admin
    	Connection con=null;
    	PreparedStatement ps=null;
    	 try {
    		 Class.forName("com.mysql.jdbc.Driver");
    		 con=DriverManager.getConnection(url, user, pass);
    		 con.setAutoCommit(false);
    		 
    		 //Getting Department Number And Year  From fetchDno() ,fetchYear() Method
    		 FetchStudent fs=new FetchStudent();
    		 int dno=fs.fetchDno(rollno);
    		 int year=fs.fetchYear(rollno);
    		 
    		 //Getting Student Name From fetchName() Method
    		 String name=fs.fetchName(rollno);
    		 
    		 //Getting Table Name From getStudentRequestTable() Method
    		 String table=getStudentRequestTable(dno,year);
    		 
    		 String qry="insert into "+table+"(sname,request,rollno) values(?,?,?);";
    		 ps=con.prepareStatement(qry);
    		 ps.setString(1, name);
    		 ps.setString(2, request);
    		 ps.setInt(3, rollno);    		 
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
    
  //Method For Sending Staff Request To Admin
    public void sendStaffRequest(int sid,String request) {
    	
    	//Code For Sending Staff Request To Admin
    	Connection con=null;
    	PreparedStatement ps=null;
    	 try {
    		 Class.forName("com.mysql.jdbc.Driver");
    		 con=DriverManager.getConnection(url, user, pass);
    		 con.setAutoCommit(false);
    		 
    		 //Getting Department Number From fetchDno() Method
    		 FetchStaff fs=new FetchStaff();
    		 int dno=fs.fetchDno(sid);
    		 
    		 //Getting Staff Name From fetchName() Method
    		 String name=fs.fetchName(sid);
    		 
    		 //Getting Table Name From getStaffRequestTable() Method
    		 String table=getStaffRequestTable(dno);
    		 
    		 String qry="insert into "+table+"(sname,request,staffid) values(?,?,?);";
    		 ps=con.prepareStatement(qry);
    		 ps.setString(1, name);
    		 ps.setString(2, request);
    		 ps.setInt(3, sid);
    		 
    		 int row=ps.executeUpdate();
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
    
    //Method For Getting Student Request Table Name 
    protected String getStudentRequestTable(int dno ,int year) {
    	if (dno == 5 && year < 3) 
	        return "Bcom_general_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 5 && year == 3) 
	        return "Bcom_general_request";
    	
	    if(dno==10 &&year <3)
	    	return "Bcom_cs_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 10 && year == 3) 
	        return "Bcom_cs_request";
	    
	    if(dno==15 &&year <3)
	    	return "Bcom_AF_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 15 && year == 3) 
	        return "Bcom_AF_request";
	    
	    if(dno==20 &&year <3)
	    	return "Bcom_BM_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 20 && year == 3) 
	        return "Bcom_BM_request";
	    
	    if(dno==25 &&year <3)
	    	return "Bcom_ISM_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 25 && year == 3) 
	        return "Bcom_ISM_request";
	    
	    if(dno==30 &&year <3)
	    	return "Bcom_CA_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 30 && year == 3) 
	        return "Bcom_CA_request";
	    
	    if(dno==35 &&year <3)
	    	return "BBA_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 35 && year == 3) 
	        return "BBA_request";
	    
	    if(dno==40 &&year <3)
	    	return "Bsc_CS_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 40 && year == 3) 
	        return "Bsc_CS_request";
	    
	    if(dno==45 &&year <3)
	    	return "BCA_" + year + (year == 1 ? "st" : "nd") + "_yearrequest";
	    else if (dno == 45 && year == 3) 
	        return "BCA_request";
	    return null;
    }
    
  //Method For Getting Staff Request Table Name 
    protected String getStaffRequestTable(int dno) {
    	switch(dno) {
    		case 5: return "Bcom_general_staff_request";
    		case 10: return "Bcom_cs_staff_request";
    		case 15: return "Bcom_AF_staff_request";
    		case 20: return "Bcom_BM_staff_request";
    		case 25: return "Bcom_ISM_staff_request";
    		case 30: return "Bcom_CA_staff_request";
    		case 35: return "BBA_staff_request";
    		case 40: return "Bsc_cs_staff_request";
    		case 45: return "BCA_staff_request";
    		default: return null;
    	}
    }
}
