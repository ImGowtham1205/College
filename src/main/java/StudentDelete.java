import java.io.IOException;

import com.DaoClass.DeleteStudentRecords;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentDelete")
public class StudentDelete extends HttpServlet {
	//This Servlet Is Use For To Delete The Selected Student Record
	
	private static final long serialVersionUID = 1L;

	DeleteStudentRecords dsr=new DeleteStudentRecords();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Values From The Form
		int rollno=Integer.parseInt(request.getParameter("studentId"));
		int dno=Integer.parseInt(request.getParameter("dno"));
		int year=Integer.parseInt(request.getParameter("year"));
		
		//By Using deleteStudent() Method Is Able To Delete The Selected Student Record Using Rollno & Department Number As Argument
		dsr.deleteStudent(rollno, dno ,year);
		response.sendRedirect("StudentDelete.jsp?success=true&studentId=" + rollno);
	}
}