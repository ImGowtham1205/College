import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DaoClass.AdminRequest;

@WebServlet("/HodRequest")
public class HodRequest extends HttpServlet {
	//This Servlet Is Use For To Send Hod Request To Their Department Admin
	private static final long serialVersionUID = 1L;
       
	AdminRequest ar=new AdminRequest();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creating HttpSession To Get the Staff ID
		HttpSession session=request.getSession();
		int hodid = (int) session.getAttribute("Hodid");
		
		//Read The Values From HOD Request Form
		String req=request.getParameter("request");
				
		//Send Staff Request To Admin By Using sendStaffRequest() Method
		ar.sendStaffRequest(hodid, req);
		request.getRequestDispatcher("HodRequest.jsp?success=success").forward(request, response);
	}

}
