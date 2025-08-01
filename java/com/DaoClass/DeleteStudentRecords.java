package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteStudentRecords {
	
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
	
	public void deleteStudent(int rollno,int dno) {
		 String table = getTableName(dno);
		 Connection con=null;
		 PreparedStatement ps=null;
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(url, user, pass);
	            String qry = "Delete From " + table + " where Rollno = ?";
	            ps = con.prepareStatement(qry);
	            ps.setInt(1, rollno);
	            ps.executeUpdate();
	            
	            ps.close();
	            con.close();
	        }
	        
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        finally {
	        	try {
	        		if(ps!=null) ps.close();
	        	}catch(Exception e) {}
	        	try {
	        		if(con!=null) con.close();
	        	}catch(Exception e) {}
	        }
	}
	
	private String getTableName(int dno) {
        switch (dno) {
            case 5: return "Bcom_general";
            case 10: return "Bcom_cs";
            case 15: return "Bcom_AF";
            case 20: return "Bcom_BM";
            case 25: return "Bcom_ISM";
            case 30: return "Bcom_CA";
            case 35: return "BBA";
            case 40: return "Bsc_CS";
            case 45: return "BCA";
            default: return null;
        }
}
}