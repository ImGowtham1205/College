import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DaoClass.AdminRequest;

@WebServlet("/StaffRequest")
public class StaffRequest extends HttpServlet {
	//This Servlet Is Use For To Send Staff Request To Their Department Admin
	private static final long serialVersionUID = 1L;

	AdminRequest ar=new AdminRequest();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creating HttpSession To Get the Staff ID
		HttpSession session=request.getSession();
		int sid = (int) session.getAttribute("sid");
		
		//Read The Values From Staff Request Form
		String req=request.getParameter("request");
		
		//Send Staff Request To Admin By Using sendStaffRequest() Method
		ar.sendStaffRequest(sid, req);
		request.getRequestDispatcher("StaffRequest.jsp?success=success").forward(request, response);
	}

}
