import java.io.IOException;

import com.DaoClass.ChangePassword;
import com.DaoClass.FetchStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChangeHodPass")
public class ChangeHodPass extends HttpServlet {
	
	//This Servlet Is Use For To Change The HOD Password
	private static final long serialVersionUID = 1L;

	FetchStaff fh=new FetchStaff();
	ChangePassword cp=new ChangePassword();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads HOD ID From session
		HttpSession session=request.getSession();
		int no=(int) session.getAttribute("Hodid");
		
		//Reads Values From The Form
		String currentpass=request.getParameter("currentPassword");
		String newpass=request.getParameter("newPassword");
		String confirmpass=request.getParameter("confirmPassword");
		
		//Getting Current Password Of Hod By Calling fetchPass() Method Passing HOD ID As Argument
		String verifypass=fh.fetchPass(no);
		
		//It Checks CurrentPassword Entered In The Form & The Pass Get By calling fetchPass() Method Is Same Or Not
		if(!currentpass.equals(verifypass)) 
			response.sendRedirect("ChangeHodPassword.jsp?status=notmatched");
		
		//It Checks New & Current Password Is Same That Entered In The Form Is Same Or Not
		else {
			if(!newpass.equals(confirmpass)) 
				response.sendRedirect("ChangeHodPassword.jsp?status=unmatched");
			
			//Above Two Condition Is False Then By Calling changeHodPass() Method Passing HOD ID & NewPassword As Argument Is Able To Change HOD Password 
			else {
				cp.changeHodPass(no, newpass);
				response.sendRedirect("ChangeHodPassword.jsp?status=success");
			}
		}
	}
}