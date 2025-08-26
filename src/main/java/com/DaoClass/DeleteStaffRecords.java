package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteStaffRecords {
	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For To Delete Selected Staff Record Using Staff Id & Department Number As Parameter
	public void deteleStaff(int staffid,int dno) {
		Connection con=null;
		PreparedStatement ps=null;
		
		//Code For Delete Selected Staff Record
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String table=getStaffTable(dno);
			con = DriverManager.getConnection(url,user,pass);
			con.setAutoCommit(false);
			String qry = "Delete From " + table + " where staffid=?";
			ps = con.prepareStatement(qry);
            ps.setInt(1, staffid);
            int row=ps.executeUpdate();
            
          //It Checks The Query Executed Successfully Or Not If True Then It Allows To Save Changes In Database
            if(row>0)
            	con.commit();
            else
            	con.rollback();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Close The Connection Using Finally Block
		finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {}
			try {
				if(con!=null) con.close();
			}catch(Exception e) {}
		}
	}

	//Method For Getting Staff Table Name
	protected String getStaffTable(int dno) {
        switch (dno) {
        case 5: return "Bcom_General_staff";
        case 10: return "Bcom_cs_staff";
        case 15: return "Bcom_AF_staff";
        case 20: return "Bcom_BM_staff";
        case 25: return "Bcom_ISM_staff";
        case 30: return "Bcom_CA_staff";
        case 35: return "BBA_staff";
        case 40: return "Bsc_Cs_staff";
        case 45: return "BCA_staff";
        default: return null;
        }
}
}
