import java.io.IOException;

import com.DaoClass.UpdateRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UpdateStudentRequest")
public class UpdateStudentRequest extends HttpServlet {
	//This Servlet Is Use For Make The Student Request Update In Database
	
	private static final long serialVersionUID = 1L;

	UpdateRequest ur=new UpdateRequest();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads values From the Form
		int id=Integer.parseInt(request.getParameter("id"));
		int rollno=Integer.parseInt(request.getParameter("rollno"));
		String status=request.getParameter("status");
		
		//It checks If The Status Value Is Completed Or Rejected Then It Allow To Update The Log In DataBase
		if(status.equals("completed")) {
			ur.updateStudentRequest(id, rollno,status);
			request.getRequestDispatcher("UpdateStudentRequest.jsp?change=success&reqid="+id).forward(request, response);
		}
		
		else if(status.equals("rejected")) {
			ur.updateStudentRequest(id, rollno,status);
			request.getRequestDispatcher("UpdateStudentRequest.jsp?change=reject&reqid="+id).forward(request, response);
		}
		
		else 
			request.getRequestDispatcher("UpdateStudentRequest.jsp?change=fail&reqid="+id).forward(request, response);
	}

}
