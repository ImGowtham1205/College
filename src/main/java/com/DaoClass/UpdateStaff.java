package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@SuppressWarnings("unused")
public class UpdateStaff {

	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Update Staff To Assigned Subject
    public void updateStaff(String code,String sname,int newsid,int hodid ,int presid,int sem) {
    	Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		boolean isassigned3rd=false;
		boolean isassigned2nd=false;
		boolean isassigned1st=false;
		
		//Creating Object For FetchStaff Class
		FetchStaff fh=new FetchStaff();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			con.setAutoCommit(false);
			    	
	    	//Getting Department Number By calling fetchDno() Method	    	
	    	int dno=fh.fetchDno(hodid);
			
	    	//Getting Table Name By Calling getTable() Method
	    	AssignStaff as=new AssignStaff();
    		String table=as.getAssignedStaffTable(dno,sem);
    		  		
    		//Code For Update Staff To Assigned Subject
			String qry="update "+table+" set staff_name=?,Staffid=? where subject_code=?;";
			ps=con.prepareStatement(qry);
			ps.setString(1, sname);
			ps.setInt(2, newsid);
			ps.setString(3, code);
			int row=ps.executeUpdate();
			
			//It Checks The Query Executed Successfully Or Not
			if(row <=0) {
				con.rollback();
				return;
			}
			
			//Get When The Staff Can Handle Subject Or Not
			String sub=fh.assignSubject(newsid);
			
			/*Code For Change Subject_Assign To Yes In The Particular Department Table
			  Getting Query By Calling getQry() Method Using Department Number As Arugment For To Change Subject_Assign To Yes In Particular Department
			 */			
			//Checks A New Staff Has Not Assigned Any Subject
			if(sub.equals("No")) {
				String qry2=as.getQry(dno);
				ps1=con.prepareStatement(qry2);
				ps1.setInt(1, newsid);
				int row1=ps1.executeUpdate();
				
				//It Checks The Query Executed Successfully Or Not
				if(row1 <=0) {
					con.rollback();
					return;
				}
			}
			
			/*Code For Change Subject_Assign To No In The Particular Department Table
			  Getting Query By Calling getNoQry() Method Using Department Number As Arugment For To Change Subject_Assign To No In Particular Department
			 */			
			//Checks A Previous Staff Can Handle Any Subjects In Another Class 
			else if(sub.equals("Yes")) {				
				//Getting Staffs Who Are Handle Subject By Calling getStaff() Method
				FetchAssignedStaff fas=new FetchAssignedStaff();
				List<FetchAssignStaff>list3rdyear=fas.get3rdYearStaff(hodid);
				List<FetchAssignStaff>list2ndyear=fas.get2ndYearStaff(hodid);
				List<FetchAssignStaff>list1styear=fas.get1stYearStaff(hodid);
		
				// Check if the new staff is already assigned
				if(sem==5) {		
					for(FetchAssignStaff s:list3rdyear) {
						int id=s.getId();
						if(id==presid) {
							isassigned3rd=true;
							break;
						}
					}			
				}
				
				else if(sem==3) {
					for(FetchAssignStaff s:list2ndyear) {
						int id=s.getId();
						if(id==presid) {
							isassigned2nd=true;
							break;
						}
					}
				}
				else if(sem==1) {
					for(FetchAssignStaff s:list1styear) {
						int id=s.getId();
						if(id==presid) {
							isassigned1st=true;
							break;
						}
					}
				}
			}
			
			//If True Then Change Subject_Assign To No For That Previous Staff Who Handle This Subject 
			if(isassigned3rd||isassigned2nd||isassigned1st){
				String qry3=getNoQry(dno);
				ps2=con.prepareStatement(qry3);
				ps2.setInt(1, presid);
				int row2=ps2.executeUpdate();
				
				//It Checks The Query Executed Successfully Or Not
				if(row2 <=0) {
					con.rollback();
					return;
				}
			}	
			
			//When The Both Query Is Executed Successfully Then It Allows To Save Changes In Database
			con.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Close The Connection By Using Finally Block
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
			try {
				if(ps2!=null) ps2.close();
				}
			catch(Exception e) {}	
		}
    }
    
  //Method For To Generate The Query Dynamically By Using Department Number As parameter For To Change Subject_Assign To No In The Particular Department Table
    protected String getNoQry(int dno) {
    	switch(dno) {
    		case 5:  return "update Bcom_General_staff set subject_assign='No' where staffid=?";
    		case 10: return "update Bcom_cs_staff set subject_assign='No' where staffid=?";
    		case 15: return "update Bcom_AF_staff set subject_assign='No' where staffid=?";
    		case 20: return "update Bcom_BM_staff set subject_assign='No' where staffid=?";
    		case 25: return "update Bcom_ISM_staff set subject_assign='No' where staffid=?";
    		case 30: return "update Bcom_CA_staff set subject_assign='No' where staffid=?";
    		case 35: return "update BBA_staff set subject_assign='No' where staffid=?";
    		case 40: return "update Bsc_Cs_staff set subject_assign='No' where staffid=?";
    		case 45: return "update BCA_staff set subject_assign='No' where staffid=?";	
    		default:return "";
    	}
    }
}
