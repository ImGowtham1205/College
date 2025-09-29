package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchRequest {

	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
  //Method For Fetching Individual Student Request 
  		public List<StudentRequestForAdmin> getIndividualStudentRequest(int rollno){
  			List<StudentRequestForAdmin> list=new ArrayList<>();
  			Connection con=null;
  	    	PreparedStatement ps=null;
  	    	ResultSet rs=null;
  	    	
  	    	//Code For Fetching Student Request 
  	    	try {
  	    		Class.forName("com.mysql.cj.jdbc.Driver");
  	    		con=DriverManager.getConnection(url, user, pass);
  	    		
  	    		//Getting Department Number And Year From fetchDno() & fetchYear() Method
  	    		FetchStudent fs=new FetchStudent();
  	    		int dno=fs.fetchDno(rollno);
  	    		int year=fs.fetchYear(rollno);
  	    		
  	    		//Getting Table Name From getStudentRequestTable() Method
  	    		AdminRequest ar=new AdminRequest();
  	    		String table=ar.getStudentRequestTable(dno,year);
  	    		
  	    		String qry="select request,progress from "+table+" where rollno=?;";
  	    		ps=con.prepareStatement(qry);
  	    		ps.setInt(1,rollno);
  	    		rs=ps.executeQuery();
  	    		while(rs.next()) {
  	    			StudentRequestForAdmin s=new StudentRequestForAdmin();
  	    			s.setRequest(rs.getString("request"));
  	    			s.setStatus(rs.getString("progress"));
  	    			list.add(s);
  	    		}
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
  	 			try {
  	 				if(rs!=null) rs.close();
  	 			}catch(Exception e) {}
  	 		}
  			return list;
  		}
    
  	//Method For Fetching Individual Staff And HOD Request 
  		public List<StaffRequestForAdmin> getIndividualStaffRequest(int id){
  			List<StaffRequestForAdmin> list=new ArrayList<>();
  			Connection con=null;
  	    	PreparedStatement ps=null;
  	    	ResultSet rs=null;
  	    	
  	    	//Code For Fetching Staff And HOD Request 
  	    	try {
  	    		Class.forName("com.mysql.cj.jdbc.Driver");
  	    		con=DriverManager.getConnection(url, user, pass);
  	    		
  	    		//Getting Department Number And Year From fetchDno() & fetchYear() Method
  	    		FetchStaff fs=new FetchStaff();
  	    		int dno=fs.fetchDno(id);
  	    		
  	    		//Getting Table Name From getStaffRequestTable() Method
  	    		AdminRequest ar=new AdminRequest();
  	    		String table=ar.getStaffRequestTable(dno);
  	    		
  	    		String qry="select request,progress from "+table+" where staffid=?;";
  	    		ps=con.prepareStatement(qry);
  	    		ps.setInt(1,id);
  	    		rs=ps.executeQuery();
  	    		while(rs.next()) {
  	    			StaffRequestForAdmin s=new StaffRequestForAdmin();
  	    			s.setRequest(rs.getString("request"));
  	    			s.setStatus(rs.getString("progress"));
  	    			list.add(s);
  	    		}
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
  	 			try {
  	 				if(rs!=null) rs.close();
  	 			}catch(Exception e) {}
  	 		}
  			return list;
  		}
  		
    //Method For Fetching 3rdYear Student Request 
	public List<StudentRequestForAdmin> get3rdYearStudentRequest(int aid){
		List<StudentRequestForAdmin> list=new ArrayList<>();
		Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For Fetching Student Request 
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno() Method
    		FetchStaff fs=new FetchStaff();
    		int dno=fs.fetchDno(aid);
    		
    		//Getting Table Name From getStudentRequestTable() Method
    		AdminRequest ar=new AdminRequest();
    		String table=ar.getStudentRequestTable(dno,3);
    		
    		String qry="select reqid,sname,request,rollno from "+table+" where progress='pending';";
    		ps=con.prepareStatement(qry);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			StudentRequestForAdmin s=new StudentRequestForAdmin();
    			s.setReqid(rs.getInt("reqid"));
    			s.setName(rs.getString("sname"));
    			s.setRequest(rs.getString("request"));
    			s.setRollno(rs.getInt("rollno"));
    			list.add(s);
    		}
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
 			try {
 				if(rs!=null) rs.close();
 			}catch(Exception e) {}
 		}
		return list;
	}
	
	//Method For Fetching 3rdYear Student Request 
		public List<StudentRequestForAdmin> get2ndYearStudentRequest(int aid){
			List<StudentRequestForAdmin> list=new ArrayList<>();
			Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	
	    	//Code For Fetching Student Request 
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number From fetchDno() Method
	    		FetchStaff fs=new FetchStaff();
	    		int dno=fs.fetchDno(aid);
	    		
	    		//Getting Table Name From getStudentRequestTable() Method
	    		AdminRequest ar=new AdminRequest();
	    		String table=ar.getStudentRequestTable(dno,2);
	    		
	    		String qry="select reqid,sname,request,rollno from "+table+" where progress='pending';";
	    		ps=con.prepareStatement(qry);
	    		rs=ps.executeQuery();
	    		while(rs.next()) {
	    			StudentRequestForAdmin s=new StudentRequestForAdmin();
	    			s.setReqid(rs.getInt("reqid"));
	    			s.setName(rs.getString("sname"));
	    			s.setRequest(rs.getString("request"));
	    			s.setRollno(rs.getInt("rollno"));
	    			list.add(s);
	    		}
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
	 			try {
	 				if(rs!=null) rs.close();
	 			}catch(Exception e) {}
	 		}
			return list;
		}
	
		//Method For Fetching 1stYear Student Request 
		public List<StudentRequestForAdmin> get1stYearStudentRequest(int aid){
			List<StudentRequestForAdmin> list=new ArrayList<>();
			Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	
	    	//Code For Fetching Student Request 
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number From fetchDno() Method
	    		FetchStaff fs=new FetchStaff();
	    		int dno=fs.fetchDno(aid);
	    		
	    		//Getting Table Name From getStudentRequestTable() Method
	    		AdminRequest ar=new AdminRequest();
	    		String table=ar.getStudentRequestTable(dno,1);
	    		
	    		String qry="select reqid,sname,request,rollno from "+table+" where progress='pending';";
	    		ps=con.prepareStatement(qry);
	    		rs=ps.executeQuery();
	    		while(rs.next()) {
	    			StudentRequestForAdmin s=new StudentRequestForAdmin();
	    			s.setReqid(rs.getInt("reqid"));
	    			s.setName(rs.getString("sname"));
	    			s.setRequest(rs.getString("request"));
	    			s.setRollno(rs.getInt("rollno"));
	    			list.add(s);
	    		}
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
	 			try {
	 				if(rs!=null) rs.close();
	 			}catch(Exception e) {}
	 		}
			return list;
		}
		
	//Method For Fetching Staff Request 
		public List<StaffRequestForAdmin> getStaffRequest(int aid){
			List<StaffRequestForAdmin> list=new ArrayList<>();
			Connection con=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	
	    	//Code For Fetching Student Request 
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		con=DriverManager.getConnection(url, user, pass);
	    		
	    		//Getting Department Number From fetchDno() Method
	    		FetchStaff fs=new FetchStaff();
	    		int dno=fs.fetchDno(aid);
	    		
	    		//Getting Table Name From getStaffRequestTable() Method
	    		AdminRequest ar=new AdminRequest();
	    		String table=ar.getStaffRequestTable(dno);
	    		
	    		String qry="select reqid,sname,request,staffid from "+table+" where progress='pending';";
	    		ps=con.prepareStatement(qry);
	    		rs=ps.executeQuery();
	    		while(rs.next()) {
	    			StaffRequestForAdmin s=new StaffRequestForAdmin();
	    			s.setReqid(rs.getInt("reqid"));
	    			s.setName(rs.getString("sname"));
	    			s.setRequest(rs.getString("request"));
	    			s.setStaffId(rs.getInt("staffid"));
	    			list.add(s);
	    		}
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
	 			try {
	 				if(rs!=null) rs.close();
	 			}catch(Exception e) {}
	 		}
			return list;
	}	
}
