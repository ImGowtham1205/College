import java.io.IOException;

import com.DaoClass.AdminRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StudentRequest")
public class StudentRequest extends HttpServlet {
	//This Servlet Is Use For To Send Student Record To Their Department Admin
	private static final long serialVersionUID = 1L;

	AdminRequest ar=new AdminRequest();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creating HttpSession To Get the Staff ID
		HttpSession session=request.getSession();
		int rollno = (int) session.getAttribute("Rollno");
		
		//Read The Values From Student Request Form
		String req=request.getParameter("request");
		
		//Send Student Request To Admin By Using sendStudentRequest() Method
		ar.sendStudentRequest(rollno, req);
		request.getRequestDispatcher("StudentRequest.jsp?success=success").forward(request, response);
	}

}
