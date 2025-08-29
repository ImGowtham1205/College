import java.io.IOException;

import com.DaoClass.HodVerify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/HodLogin")
public class HodLogin extends HttpServlet {
	//This Servlet Is Use For To Verify The HOD Login credentials 
	
	private static final long serialVersionUID = 1L;

	HodVerify hv=new HodVerify();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Read The Values From The HOD Login Form
			int Hodid=Integer.parseInt(request.getParameter("id"));
			String pass=request.getParameter("password");
			
			//It Checks The Login credentials By Using checkStaff() Method If It Returns True Then Redirect To The Welcome Page 
			if(hv.checkHod(Hodid, pass)) {
				HttpSession session=request.getSession();
				session.setAttribute("Hodid", Hodid);
				response.sendRedirect("HodWelcome.jsp");
			}
			
			//When It Return False It Redirect To Hod Login Page
			else
				response.sendRedirect("HodLogin.jsp?error=unauthorized");
	}
}