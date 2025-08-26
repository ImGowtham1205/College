import java.io.IOException;

import com.DaoClass.ChangePassword;
import com.DaoClass.FetchStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/ChangeAdminPass")
public class ChangeAdminPass extends HttpServlet {
	//This Servlet Is Use For To Change The Admin Password
	
	private static final long serialVersionUID = 1L;

	FetchStaff fs=new FetchStaff();
	ChangePassword cp=new ChangePassword();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads Admin ID From session
		HttpSession session=request.getSession();
		int no=(int) session.getAttribute("Adminid");
		
		//Reads Values From The Form
		String currentpass=request.getParameter("currentPassword");
		String newpass=request.getParameter("newPassword");
		String confirmpass=request.getParameter("confirmPassword");
		
		//Getting Current Password Of Admin By Calling fetchPass() Method Passing Admin ID As Argument
		String verifypass=fs.fetchPass(no);
		
		//It Checks CurrentPassword Entered In The Form & The Pass Get By calling fetchPass() Method Is Same Or Not
		if(!currentpass.equals(verifypass)) 
		response.sendRedirect("ChangeAdminPassword.jsp?status=notmatched");
				
		//It Checks New & Current Password Is Same That Entered In The Form Is Same Or Not
		else {
			if(!newpass.equals(confirmpass)) 
				response.sendRedirect("ChangeAdminPassword.jsp?status=unmatched");
					
		//Above Two Condition Is False Then By Calling changeAdminPass() Method Passing Admin ID & NewPassword As Argument Is Able To Change Admin Password 
		else {
				cp.changeAdminPass(no, newpass);
				response.sendRedirect("ChangeAdminPassword.jsp?status=success");
			}
		}
	}
}