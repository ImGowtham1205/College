import java.io.IOException;

import com.DaoClass.AdminVerify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	//This Servlet Is Use For To Verify The Admin Login credentials 
	private static final long serialVersionUID = 1L;

	AdminVerify av=new AdminVerify();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Read The Values From The Admin Login Form
		int aid=Integer.parseInt(request.getParameter("id"));
		String password=request.getParameter("password");
		
		//It Checks The Login credentials By Using checkAdmin() Method If It Returns True Then Redirect To The Welcome Page 
		if(av.checkAdmin(aid,password)){
			HttpSession session=request.getSession();
			session.setAttribute("Adminid", aid);
			response.sendRedirect("AdminWelcome.jsp");
		}
		
		//When It Return False It Redirect To Admin Login Page
		else
			response.sendRedirect("AdminLogin.jsp?error=unauthorized");
	}

}