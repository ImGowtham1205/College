import java.io.IOException;

import com.DaoClass.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	
	//This Servlet Is Use For Add Course Details In The Particular Department
	private static final long serialVersionUID = 1L;

	Course c=new Course();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Read The Values From The Form
		String code=request.getParameter("code");
		String subject=request.getParameter("subject");
		int year=Integer.parseInt(request.getParameter("year"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		int credits=Integer.parseInt(request.getParameter("credits"));
		int dno=Integer.parseInt(request.getParameter("department"));
		
		//Check When Admin Is Entered valid Year & Semester If True Then It Allow To Add Course Details  In Particular Department
		if ((year == 1 && (sem == 1 || sem == 2)) || (year == 2 && (sem == 3 || sem == 4)) || (year == 3 && (sem == 5 || sem == 6))) {
		//By Using addCourse() Method For To Add The Course In The Particular Department
			c.addCourse(code, subject, dno, sem, credits, year);		
			response.sendRedirect("AddCourse.jsp?success=true");
		}
				
		//When The Condition Is False It Redirect To AddCouurse.jsp Page
		else {
			request.getRequestDispatcher("AddCourse.jsp?success=false").forward(request, response);;
		}
	}

}
