import java.io.IOException;

import com.DaoClass.DeleteStaffRecords;
import com.DaoClass.FetchStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/StaffDelete")
public class StaffDelete extends HttpServlet {
	//This Servlet Is Use For To Delete The Selected Staff Record
	
	DeleteStaffRecords dsr=new DeleteStaffRecords();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Values From The Form
		int staffid=Integer.parseInt(request.getParameter("staffId"));
		int dno=Integer.parseInt(request.getParameter("dno"));
		
		//Creating A HttpSession
		HttpSession session=request.getSession();
		
		FetchStaff fs=new FetchStaff();
		//Getting Staff Desigination By Calling fetchDesigination() Method Using Staff ID As Arugment
		String des=fs.fetchDesigination(staffid);
		
		//Get When The Staff Can Handle Subject Or Not
		String sub=fs.assignSubject(staffid);
		
		//If The Desigination Of The Staff Is HOD Or Admin It Doesn't Allow To Delete The Selected Staff record
		if(des.equals("HOD")||des.equals("Admin"))
			response.sendRedirect("StaffDelete.jsp?success=invalid&staffId=" + staffid);
		
		if(sub.equals("Yes"))
			response.sendRedirect("StaffDelete.jsp?success=handle&staffId=" + staffid);
		
		//If Not By Using deleteStaff() Method is Able To Delete The Selected Staff Record Using StaffId & Department Number As Argument
		else {
			dsr.deteleStaff(staffid, dno);
			session.removeAttribute("Name");
	        session.removeAttribute("Phone");
	        session.removeAttribute("Mail");
	        session.removeAttribute("Desigination");
	        session.removeAttribute("Dno");
	        session.removeAttribute("staffid");
			response.sendRedirect("StaffDelete.jsp?success=true");
		}
	}	
}
