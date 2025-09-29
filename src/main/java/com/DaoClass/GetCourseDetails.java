package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetCourseDetails {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
   
    //Creating Object For FetchCourse Class
   FetchCourse fc=new FetchCourse();
        
    /*Fetching Subject Name,Semester,Credits&Year 
      From The Database By Calling This Method Declared Below Through The Help OF fetchColumnValue() & fetchNumValue() Method*/  
    public String fetchSubjectName(String code) {
    	return fetchColumnValue(code, "Subject_name");
    }
      
    public int fetchSem(String code) {
    	return fetchNumValue(code, "Semseter");
    }
      
    public int fetchcredits(String code) {
    	return fetchNumValue(code, "Credits");
    }
       
    public int fetchYear(String code) {
    	return fetchNumValue(code, "cyear");
    }
        
  //Method For To Get The partment Number For The Given Course Code
    public int fetchDno(String code) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	//Code For To Get The Department Number For The Given Course Code
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(url, user, pass);
    		String tables[]= {"Bcom_General_Course","Bcom_cs_Course","Bcom_AF_Course","Bcom_BM_Course","Bcom_ISM_Course","Bcom_CA_Course","BBA_Course","Bsc_cs_Course","BCA_Course"};
    		for(String table:tables) {
    			String qry="select Dno from "+table+" where subject_code=?;";
        		ps=con.prepareStatement(qry);
        		ps.setString(1, code);
        		rs=ps.executeQuery();
        		if(rs.next()) 
        			return rs.getInt("Dno");
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
    	return 0;
    }
    
    //Method Use For To Fetch The Course Details Based On Course Code & ColumnName Parameter 
    private String fetchColumnValue(String code, String columnName) {
    int dno = fetchDno(code);
 
    // If The Given Student Rollno Not Found DB It Returns null
    if (dno == 0)
        return null;
    
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	con=DriverManager.getConnection(url, user, pass);
    	
    	//Getting Table Name From getTableName() Method
    	String table = fc.getCourseTable(dno);
        String qry = "select " + columnName + " from " + table + " where subject_code=?";
        ps=con.prepareStatement(qry);
        ps.setString(1, code);
        rs=ps.executeQuery();
        if(rs.next())
        	return rs.getString(columnName);           
    }
     catch (Exception e) {
        e.printStackTrace();
    }

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
    	return null;
	}  
    
  //Method Use For To Fetch The Student Details Are Stored In Integer Value On Database Based On Course Code & ColumnName Parameter 
    private int fetchNumValue(String code, String columnName) {
    	 int dno = fetchDno(code);

        // If The Given Student Rollno Not Found DB It Returns null
        if (dno == 0) {
            return 0;
        }

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con=DriverManager.getConnection(url, user, pass);
        	
        	//Getting Table Name From getTable() Method
        	String table = fc.getCourseTable(dno);
            String qry = "select " + columnName + " from " + table + " where subject_code=?";
            ps=con.prepareStatement(qry);
            ps.setString(1, code);
            rs=ps.executeQuery();
            if(rs.next())
            	return rs.getInt(columnName);           
        }
         catch (Exception e) {
            e.printStackTrace();
        }

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
        return 0;
}  
}