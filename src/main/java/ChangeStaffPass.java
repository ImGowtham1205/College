import java.io.IOException;

import com.DaoClass.ChangePassword;
import com.DaoClass.FetchStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChangeStaffPass")
public class ChangeStaffPass extends HttpServlet {
	
	//This Servlet Is Use For To Change The Staff Password
	private static final long serialVersionUID = 1L;

	FetchStaff fs=new FetchStaff();
	ChangePassword cp=new ChangePassword();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads Staff ID From session
		HttpSession session=request.getSession();
		int no=(int) session.getAttribute("sid");
		
		//Reads Values From The Form
		String currentpass=request.getParameter("currentPassword");
		String newpass=request.getParameter("newPassword");
		String confirmpass=request.getParameter("confirmPassword");
		
		//Getting Current Password Of The Hod By Calling fetchPass() Method Passing Staff D As Argument
		String verifypass=fs.fetchPass(no);
		
		//It Checks CurrentPassword Entered In The Form & The Pass Get By calling fetchPass() Method Is Same Or Not
		if(!currentpass.equals(verifypass)) 
			response.sendRedirect("ChangeStaffPassword.jsp?status=notmatched");
		
		//It Checks New & Current Password Is Same That Entered In The Form Is Same Or Not
		else {
			if(!newpass.equals(confirmpass)) 
				response.sendRedirect("ChangeStaffPassword.jsp?status=unmatched");
			
			//Above Two Condition Is False Then By Calling changeStaffPass() Method Passing Staff ID & NewPassword As Argument Is Able To Change Staff Password 
			else {
				cp.changeStaffPass(no, newpass);
				response.sendRedirect("ChangeStaffPassword.jsp?status=success");
			}
		}
	}
}