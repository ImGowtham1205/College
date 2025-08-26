import java.io.IOException;

import com.DaoClass.ChangePassword;
import com.DaoClass.FetchStudent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
	
	//This Servlet Is Use For To Change The Student Password
	private static final long serialVersionUID = 1L;

	ChangePassword cp=new ChangePassword();
	FetchStudent fs= new FetchStudent();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads Student Rollno From session
		HttpSession session=request.getSession();
		int no=(int) session.getAttribute("Rollno");
		
		//Reads Values From The Form
		String currentpass=request.getParameter("currentPassword");
		String newpass=request.getParameter("newPassword");
		String confirmpass=request.getParameter("confirmPassword");
		
		//Getting Current Password Of The Student By Calling fetchPass() Method Passing Rollno As Argument
		String verifypass=fs.fetchPass(no);
		
		//It Checks CurrentPassword Entered In The Form & The Pass Get By calling getPass() Method Is Same Or Not
		if(!currentpass.equals(verifypass)) 
			response.sendRedirect("ChangePassword.jsp?status=notmatched");
		
		//It Checks New & Current Password Is Same That Entered In The Form Is Same Or Not
		else {
			if(!newpass.equals(confirmpass)) 
				response.sendRedirect("ChangePassword.jsp?status=unmatched");
			
			//Above Two Condition Is False Then By Calling changePass() Method Passing Rollno & NewPassword As Argument Is Able To Change Student Password 
			else {
				cp.changePass(no, newpass);
				response.sendRedirect("ChangePassword.jsp?status=success");
			}
		}
	}
}