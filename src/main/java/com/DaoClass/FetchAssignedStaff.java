package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchAssignedStaff {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Creating Object For FetchStaff Class
    FetchStaff fs=new FetchStaff();
    
  //Method For To Get Staffs Who Are Handle Subject For 3rd Year
    public List<FetchAssignStaff>get3rdYearForStaff(int id){
    	List<FetchAssignStaff>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Staffs Who Are Handle Subject For 3rd Year
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		int dno=fs.fetchDno(id);   	
        	int sem=5;
        	
        	//Prevents Program From Crash
        	if(dno==0)
        		return null;
        	
        	//Getting Table Name From getTable() Method
        	AssignStaff as=new AssignStaff();
        	String table=as.getAssignedStaffTable(dno, sem);
        	
        	String qry="select * from "+table+" where Staffid=?;";
        	ps=con.prepareStatement(qry);
        	ps.setInt(1, id);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		FetchAssignStaff fas=new FetchAssignStaff();
        		fas.setCode(rs.getString("subject_code"));
        		fas.setSubject(rs.getString("Subject_name"));
        		fas.setName(rs.getString("staff_name"));
        		fas.setSem(rs.getInt("Semseter"));
        		fas.setId(rs.getInt("Staffid"));
        		fas.setDno(rs.getInt("Dno"));
        		fas.setYear(rs.getInt("cyear"));
        		list.add(fas);
        	}
    	}
    	
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection Using Finally Block
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
    	return list;
    }
    
  //Method For To Get Staffs Who Are Handle Subject For 3rd Year
    public List<FetchAssignStaff>get2ndYearForStaff(int id){
    	List<FetchAssignStaff>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Staffs Who Are Handle Subject For 2nd Year
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		int dno=fs.fetchDno(id);   	
        	int sem=3;
        	
        	//Prevents Program From Crash
        	if(dno==0)
        		return null;
        	
        	//Getting Table Name From getTable() Method
        	AssignStaff as=new AssignStaff();
        	String table=as.getAssignedStaffTable(dno, sem);
        	
        	String qry="select * from "+table+" where Staffid=?;";
        	ps=con.prepareStatement(qry);
        	ps.setInt(1, id);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		FetchAssignStaff fas=new FetchAssignStaff();
        		fas.setCode(rs.getString("subject_code"));
        		fas.setSubject(rs.getString("Subject_name"));
        		fas.setName(rs.getString("staff_name"));
        		fas.setSem(rs.getInt("Semseter"));
        		fas.setId(rs.getInt("Staffid"));
        		fas.setDno(rs.getInt("Dno"));
        		fas.setYear(rs.getInt("cyear"));
        		list.add(fas);
        	}
    	}
    	
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection Using Finally Block
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
    	return list;
    }
    
  //Method For To Get Staffs Who Are Handle Subject For 1st Year
    public List<FetchAssignStaff>get1stYearForStaff(int id){
    	List<FetchAssignStaff>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Staffs Who Are Handle Subject For 3rd Year
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		int dno=fs.fetchDno(id);   	
        	int sem=1;
        	 
        	//Prevents Program From Crash
        	if(dno==0)
        		return null;
        	
        	//Getting Table Name From getTable() Method
        	AssignStaff as=new AssignStaff();
        	String table=as.getAssignedStaffTable(dno, sem);
        	
        	String qry="select * from "+table+" where Staffid=?;";
        	ps=con.prepareStatement(qry);
        	ps.setInt(1, id);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		FetchAssignStaff fas=new FetchAssignStaff();
        		fas.setCode(rs.getString("subject_code"));
        		fas.setSubject(rs.getString("Subject_name"));
        		fas.setName(rs.getString("staff_name"));
        		fas.setSem(rs.getInt("Semseter"));
        		fas.setId(rs.getInt("Staffid"));
        		fas.setDno(rs.getInt("Dno"));
        		fas.setYear(rs.getInt("cyear"));
        		list.add(fas);
        	}
    	}
    	
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection Using Finally Block
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
    	return list;
    }
    
    //Method For To Get Staffs Who Are Handle Subject For 3rd Year
    public List<FetchAssignStaff>get3rdYearStaff(int id){
    	List<FetchAssignStaff>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Staffs Who Are Handle Subject For 3rd Year
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		int dno=fs.fetchDno(id);   	
        	int sem=5;
        	       	
        	//Getting Table Name From getTable() Method
        	AssignStaff as=new AssignStaff();
        	String table=as.getAssignedStaffTable(dno, sem);
        	
        	String qry="select * from "+table;
        	ps=con.prepareStatement(qry);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		FetchAssignStaff fas=new FetchAssignStaff();
        		fas.setCode(rs.getString("subject_code"));
        		fas.setSubject(rs.getString("Subject_name"));
        		fas.setName(rs.getString("staff_name"));
        		fas.setSem(rs.getInt("Semseter"));
        		fas.setId(rs.getInt("Staffid"));
        		fas.setDno(rs.getInt("Dno"));
        		fas.setYear(rs.getInt("cyear"));
        		list.add(fas);
        	}
    	}
    	
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection Using Finally Block
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
    	return list;
    }
    
  //Method For To Get Staffs Who Are Handle Subject For 2nd Year
    public List<FetchAssignStaff>get2ndYearStaff(int id){
    	List<FetchAssignStaff>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Staffs Who Are Handle Subject For 2nd Year
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		int dno=fs.fetchDno(id);   		
        	int sem=3;
              	
        	//Getting Table Name From getTable() Method
        	AssignStaff as=new AssignStaff();
        	String table=as.getAssignedStaffTable(dno, sem);
        	
        	String qry="select * from "+table;
        	ps=con.prepareStatement(qry);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		FetchAssignStaff fas=new FetchAssignStaff();
        		fas.setCode(rs.getString("subject_code"));
        		fas.setSubject(rs.getString("Subject_name"));
        		fas.setName(rs.getString("staff_name"));
        		fas.setSem(rs.getInt("Semseter"));
        		fas.setId(rs.getInt("Staffid"));
        		fas.setDno(rs.getInt("Dno"));
        		fas.setYear(rs.getInt("cyear"));
        		list.add(fas);
        	}
    	}
    	
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection Using Finally Block
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
    	return list;
    }
    
  //Method For To Get Staffs Who Are Handle Subject For 1st Year
    public List<FetchAssignStaff>get1stYearStaff(int id){
    	List<FetchAssignStaff>list=new ArrayList<>();
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get Staffs Who Are Handle Subject For 1st Year
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		
    		//Getting Department Number From fetchDno()
    		int dno=fs.fetchDno(id);   		
        	int sem=1;
              	
        	//Getting Table Name From getTable() Method
        	AssignStaff as=new AssignStaff();
        	String table=as.getAssignedStaffTable(dno, sem);
        	
        	String qry="select * from "+table;
        	ps=con.prepareStatement(qry);
        	rs=ps.executeQuery();
        	while(rs.next()) {
        		FetchAssignStaff fas=new FetchAssignStaff();
        		fas.setCode(rs.getString("subject_code"));
        		fas.setSubject(rs.getString("Subject_name"));
        		fas.setName(rs.getString("staff_name"));
        		fas.setSem(rs.getInt("Semseter"));
        		fas.setId(rs.getInt("Staffid"));
        		fas.setDno(rs.getInt("Dno"));
        		fas.setYear(rs.getInt("cyear"));
        		list.add(fas);
        	}
    	}
    	
    	catch(Exception e) {e.printStackTrace();}
    	
    	//Close The Connection Using Finally Block
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
    	return list;
    }
}
