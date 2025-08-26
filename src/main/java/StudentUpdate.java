import java.io.IOException;

import com.DaoClass.UpdateStudentRecords;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentUpdate")
public class StudentUpdate extends HttpServlet {
	//This Servlet Is Use For To Delete The Selected Student Record
	
	private static final long serialVersionUID = 1L;

	UpdateStudentRecords usr=new UpdateStudentRecords();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Values From The Form
		int rollno=Integer.parseInt(request.getParameter("studentId"));
		String name=request.getParameter("name");
		int dno=Integer.parseInt(request.getParameter("dno"));
		String email=request.getParameter("email");
		String blood_group=request.getParameter("blood");
		String phoneno=request.getParameter("phone");
		String address=request.getParameter("address");
		int year=Integer.parseInt(request.getParameter("year"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		
		//Check When Admin Is Entered valid Year & Semester If True Then It Allow To Update Student Record In Particular Department
		if ((year == 1 && (sem == 1 || sem == 2)) || (year == 2 && (sem == 3 || sem == 4)) || (year == 3 && (sem == 5 || sem == 6))) {
			
			/*By Using updateStudent() Method Is Able To Delete The Selected Student Record Using 
			 Rollno,Student Name,Department Number,Email,Blood Group,Phone Number & Address As Argument*/
			usr.upadteStudent(rollno, name, dno, email, blood_group, phoneno, address,year,sem);
			response.sendRedirect("StudentUpdate.jsp?success=true&studentId=" + rollno);
		}
		else
			request.getRequestDispatcher("StudentUpdate.jsp?success=false&studentId="+rollno).forward(request, response);;
	}
}