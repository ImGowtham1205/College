import java.io.IOException;

import com.DaoClass.DeleteStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteAssignSubject")
public class DeleteAssignSubject extends HttpServlet {
	//This Servlet Is Use For Delete Staff For Assigned Subject
	
	private static final long serialVersionUID = 1L;
	
	DeleteStaff ds= new DeleteStaff();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads values From the Form
		String code=request.getParameter("code");
		int currentsid=Integer.parseInt(request.getParameter("sid"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		
		//Creating HttpSession To Get the HOD ID
		HttpSession session=request.getSession();
		int hodid=(int) session.getAttribute("Hodid");
		
		//Delete Staff For Assigned Subject
		ds.deleteStaff(code, currentsid,sem,hodid);
		response.sendRedirect("DeleteSubject.jsp");
	}
}