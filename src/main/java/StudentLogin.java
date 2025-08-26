import java.io.IOException;

import com.DaoClass.StudentVerify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	//This Servlet Is Use For To Verify The HOD Login credentials 
	
	StudentVerify s=new StudentVerify();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Read The Values From The Staff Login Form
			int id=Integer.parseInt(request.getParameter("student-id"));
			String password=request.getParameter("password");
			
			//It Checks The Login credentials By Using checkStudent() Method If It Returns True Then Redirect To The Welcome Page 
			if(s.checkStudent(id,password)) {
				HttpSession session=request.getSession();
				session.setAttribute("Rollno", id);
				response.sendRedirect("StudentWelcome.jsp");
			}
			
			//When It Return False It Redirect To Student Login Page
			else 
				response.sendRedirect("StudentLogin.jsp?error=unauthorized");			
	}
}