import java.io.IOException;

import com.DaoClass.StaffVerify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StaffLogin")
public class StaffLogin extends HttpServlet {
	
	//This Servlet Is Use For To Verify The Staff Login credentials 
	private static final long serialVersionUID = 1L;
     
	StaffVerify sv=new StaffVerify();
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		//Read The Values From The Staff Login Form
 		int id=Integer.parseInt(request.getParameter("id"));
		String pass=request.getParameter("password");
		
		//It Checks The Login credentials By Using checkStaff() Method If It Returns True Then Redirect To The Welcome Page 
		if(sv.staffCheck(id, pass)) {
			HttpSession session=request.getSession();
			session.setAttribute("sid", id);
			response.sendRedirect("StaffWelcome.jsp");
		}
		
		//When It Return False It Redirect To Staff Login Page
		else
			response.sendRedirect("StaffLogin.jsp?error=unauthorized");
	}
}
