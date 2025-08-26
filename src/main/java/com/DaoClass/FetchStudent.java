package com.DaoClass;

import java.sql.*;

public class FetchStudent {

    private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";

    //Creating Object For FetchStudentRecord Class
    FetchStudentRecord fsr = new FetchStudentRecord();

    /*Fetching Student Name,Department Name ,Gender,Blood Group,Phone Number,Mail,Password,Address & Current Semester
     From The Database By Calling This Method Declared Below Through The Help OF fetchColumnValue() & fetchNumValue() Method*/
    
    public String fetchName(int rollno) {
        return fetchColumnValue(rollno, "Sname");
    }

    public String fetchDname(int rollno) {
        return fetchColumnValue(rollno, "Dname");
    }

    public String fetchGender(int rollno) {
        return fetchColumnValue(rollno, "Gender");
    }

    public String fetchBlood(int rollno) {
        return fetchColumnValue(rollno, "Blood_Group");
    }

    public String fetchPhone(int rollno) {
        return fetchColumnValue(rollno, "Phoneno");
    }

    public String fetchMail(int rollno) {
        return fetchColumnValue(rollno, "Email");
    }

    public String fetchAddress(int rollno) {
        return fetchColumnValue(rollno, "Address");
    }

    public String fetchPass(int rollno) {
    	return fetchColumnValue(rollno, "pass");
    }
    
    public int fetchSem(int rollno) {
        int val = fetchNumValue(rollno, "Semester");
        return val;
    }

    //Getting Student Department Number By Passing Student Rollno As Parameter
    public int fetchDno(int rollno) {
        String[] tables = {"Bcom_general","Bcom_general_2nd_year","Bcom_general_1st_year",
                "Bcom_cs","Bcom_cs_2nd_year","Bcom_cs_1st_year",
                "Bcom_AF","Bcom_AF_2nd_year","Bcom_AF_1st_year",
                "Bcom_BM","Bcom_BM_2nd_year","Bcom_BM_1st_year",
                "Bcom_ISM","Bcom_ISM_2nd_year","Bcom_ISM_1st_year",
                "Bcom_CA","Bcom_CA_2nd_year","Bcom_CA_1st_year",
                "BBA","BBA_2nd_year","BBA_1st_year",
                "Bsc_CS","Bsc_CS_2nd_year","Bsc_CS_1st_year",
                "BCA","BCA_2nd_year","BCA_1st_year"};

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con=DriverManager.getConnection(url, user, pass);
        	
        	for(String table:tables) {
        		String qry="select Dno from "+table+" where Rollno=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, rollno);
        		rs=ps.executeQuery();
        		if(rs.next())
        			return rs.getInt("Dno");
        	}
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
        
        return 0; // not found
    }

  //Getting Student Batch Year By Passing Student Rollno As Parameter
    public int fetchYear(int rollno) {
        String[] tables = {"Bcom_general","Bcom_general_2nd_year","Bcom_general_1st_year",
                "Bcom_cs","Bcom_cs_2nd_year","Bcom_cs_1st_year",
                "Bcom_AF","Bcom_AF_2nd_year","Bcom_AF_1st_year",
                "Bcom_BM","Bcom_BM_2nd_year","Bcom_BM_1st_year",
                "Bcom_ISM","Bcom_ISM_2nd_year","Bcom_ISM_1st_year",
                "Bcom_CA","Bcom_CA_2nd_year","Bcom_CA_1st_year",
                "BBA","BBA_2nd_year","BBA_1st_year",
                "Bsc_CS","Bsc_CS_2nd_year","Bsc_CS_1st_year",
                "BCA","BCA_2nd_year","BCA_1st_year"};

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con=DriverManager.getConnection(url, user, pass);
        	
        	for(String table:tables) {
        		String qry="select Batch_Year from "+table+" where Rollno=?;";
        		ps=con.prepareStatement(qry);
        		ps.setInt(1, rollno);
        		rs=ps.executeQuery();
        		if(rs.next())
        			return rs.getInt("Batch_Year");
        	}
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
        return 0; // not found
    }
    
    //Method Use For To Fetch The Student Details Based On columnName Parameter 
    private String fetchColumnValue(int rollno, String columnName) {
        int dno = fetchDno(rollno);
        int year = fetchYear(rollno);

        // If The Given Student Rollno Not Found DB It Returns null
        if (dno == 0 || year == 0) {
            return null;
        }

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con=DriverManager.getConnection(url, user, pass);
        	
        	//Getting Table Name From getTable() Method
        	String table = fsr.getStudentTable(dno, year);
            String qry = "select " + columnName + " from " + table + " where Rollno=?";
            ps=con.prepareStatement(qry);
            ps.setInt(1, rollno);
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
    
  //Method Use For To Fetch The Student Details Are Stored In Integer Value On Database Based On columnName Parameter 
    private int fetchNumValue(int rollno, String columnName) {
        int dno = fetchDno(rollno);
        int year = fetchYear(rollno);

        // If The Given Student Rollno Not Found DB It Returns null
        if (dno == 0 || year == 0) {
            return 0;
        }

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con=DriverManager.getConnection(url, user, pass);
        	
        	//Getting Table Name From getTable() Method
        	String table = fsr.getStudentTable(dno, year);
            String qry = "select " + columnName + " from " + table + " where Rollno=?";
            ps=con.prepareStatement(qry);
            ps.setInt(1, rollno);
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
