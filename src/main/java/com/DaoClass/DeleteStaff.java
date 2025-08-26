package com.DaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class DeleteStaff {

	private String url = "jdbc:mysql://localhost:3306/college";
    private String user = "root";
    private String pass = "test";
    
    //Method For Delete Staff To Assigned Subject
	public void deleteStaff(String code,int sid,int sem,int hodid) {
    	Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		boolean isassigned3rd=false;
		boolean isassigned2nd=false;
		boolean isassigned1st=false;
		//Creating Object For FetchStaff Class
		FetchStaff fs=new FetchStaff();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			con.setAutoCommit(false);
			
			//Getting Department Number By calling fetchDno() Method	    	
	    	int dno=fs.fetchDno(sid);
			
	    	//Getting Table Name By Calling getTable() Method
	    	AssignStaff as=new AssignStaff();
    		String table=as.getAssignedStaffTable(dno,sem);
    		  		
    		//Code For Delete Staff To Assigned Subject
			String qry="delete from "+table+" where subject_code=?;";
			ps=con.prepareStatement(qry);
			ps.setString(1, code);
			int row=ps.executeUpdate();
			
			//It Checks The Query Executed Successfully Or Not
			if(row <=0) {
				con.rollback();
				return;
			}
			
			//Get When The Staff Can Handle Subject Or Not
			String sub=fs.assignSubject(sid);
			
			/*Code For Change Subject_Assign To No In The Particular Department Table
			  Getting Query By Calling getNoQry() Method Using Department Number As Arugment For To Change Subject_Assign To No In Particular Department
			 */			
			//Checks A Previous Staff Can Handle Any Subjects In Another Class 
			if(sub.equals("Yes")) {				
				//Getting Staffs Who Are Handle Subject By Calling getStaff() Method
				FetchAssignedStaff fas=new FetchAssignedStaff();
				List<FetchAssignStaff>list3rdyear=fas.get3rdYearStaff(hodid);
				List<FetchAssignStaff>list2ndyear=fas.get2ndYearStaff(hodid);
				List<FetchAssignStaff>list1styear=fas.get1stYearStaff(hodid);
		
				// Check if the new staff is already assigned
				if(sem==5) {		
					for(FetchAssignStaff s:list3rdyear) {
						int id=s.getId();
						if(id==sid) {
							isassigned3rd=true;
							break;
						}
					}			
				}
				
				else if(sem==3) {
					for(FetchAssignStaff s:list2ndyear) {
						int id=s.getId();
						if(id==sid) {
							isassigned2nd=true;
							break;
						}
					}
				}
				else if(sem==1) {
					for(FetchAssignStaff s:list1styear) {
						int id=s.getId();
						if(id==sid) {
							isassigned1st=true;
							break;
						}
					}
				}
			}
			
			//If True Then Change Subject_Assign To No For That Previous Staff Who Handle This Subject 
			if(isassigned3rd||isassigned2nd||isassigned1st){
				UpdateStaff us=new UpdateStaff();
				String qry3=us.getNoQry(dno);
				ps2=con.prepareStatement(qry3);
				ps2.setInt(1, sid);
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
				if(ps2!=null) ps2.close();
				}
			catch(Exception e) {}	
		}
    }
}
