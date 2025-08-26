import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.DaoClass.AddStudentRecords;

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	
	//This Servlet Is Use For Add Student Record In The Particular Department
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read The Values From The Add Student Form
		String name=request.getParameter("fullname");
		String gender=request.getParameter("gender");
		String department=request.getParameter("department");
		String blood_group=request.getParameter("blood-group");
		String phoneno=request.getParameter("phone");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		int year=Integer.parseInt(request.getParameter("year"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		
		//Check When Admin Is Entered valid Year & Semester If True Then It Allow To Add Student Record In Particular Department
		if ((year == 1 && (sem == 1 || sem == 2)) || (year == 2 && (sem == 3 || sem == 4)) || (year == 3 && (sem == 5 || sem == 6))) {
			//By Using addRecords() Method For To Add The Student Record In The Particular Department
			AddStudentRecords as=new AddStudentRecords();
			as.addRecords(name, gender, department, blood_group, phoneno, address, email ,year,sem);
			response.sendRedirect("AddStudent.jsp?success=true");
		}
		
		//When The Condition Is False It Redirect To AddStudent.jsp Page
		else {
			request.getRequestDispatcher("AddStudent.jsp?success=false").forward(request, response);;
		}
	}
}