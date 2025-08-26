import java.io.IOException;

import com.DaoClass.FetchStaff;
import com.DaoClass.UpdateStaffRecords;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StaffUpdate")
public class StaffUpdate extends HttpServlet {
	//This Servlet Is Use For To Update The Selected Staff Record
	
	private static final long serialVersionUID = 1L;
	UpdateStaffRecords usr=new UpdateStaffRecords();
	FetchStaff fs=new FetchStaff();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Values From The Form
		int staffid=Integer.parseInt(request.getParameter("staffId"));
		String name=request.getParameter("staffName");
		int dno=Integer.parseInt(request.getParameter("dno"));
		String email=request.getParameter("staffEmail");
		String phoneno=request.getParameter("phone");
		String desigination=request.getParameter("desigination");
		
		//Get The Current Desigination Of The Selected Staff From fetchDesigination() Method
		String currentdesigination=fs.fetchDesigination(staffid);
		
		/* Checks If The Current Designation Of The Staff Is Either HOD or Admin.
		 This Is Used To Prevent Changing The Designation For HOD or Admin.*/
		 boolean isCurrentHodOrAdmin = currentdesigination.equals("HOD") || currentdesigination.equals("Admin");
		 
		 /*Checks If The New Designation Entered In The Update Form Is HOD or Admin.
		  This Is Used To Prevent Unauthorized Promotion Of Professor.*/
		 boolean isNewHodOrAdmin = desigination.equals("HOD") || desigination.equals("Admin");

		//Prevent changing designation of current HOD or Admin
		if(isCurrentHodOrAdmin && !currentdesigination.equals(desigination))
			response.sendRedirect("StaffUpdate.jsp?success=invalid&staffId=" + staffid);
		
		//Prevent promoting Professor To HOD or Admin
		else if (!isCurrentHodOrAdmin && isNewHodOrAdmin)
			response.sendRedirect("StaffUpdate.jsp?success=invalid&staffId=" + staffid);
		
		/*If Not By Using updateStaff() Method is Able To Update The Selected Staff Record Using 
		  StaffId & Name,Department Number,Email,Phone Number & Desigination As Argument */		
		else {
			usr.updateStaff(staffid,name,dno,email,phoneno,desigination);
			response.sendRedirect("StaffUpdate.jsp?success=true&staffId=" + staffid);
		}
	}
}