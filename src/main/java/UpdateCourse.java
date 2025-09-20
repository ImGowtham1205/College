import java.io.IOException;

import com.DaoClass.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateCourse")
public class UpdateCourse extends HttpServlet {
	
	//This Servlet Is Use For Update Course Details In The Particular Department
	private static final long serialVersionUID = 1L;

	Course c=new Course();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read The Values From The Form
		String code=request.getParameter("code");
		String subject=request.getParameter("subject");
		int year=Integer.parseInt(request.getParameter("year"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		int credits=Integer.parseInt(request.getParameter("credits"));
		int dno=Integer.parseInt(request.getParameter("dno"));
		
		//Creating a HttpSession
		HttpSession session=request.getSession();
		
		//Check When Admin Is Entered valid Year & Semester If True Then It Allow To Update Course Detail In Particular Department
		if ((year == 1 && (sem == 1 || sem == 2)) || (year == 2 && (sem == 3 || sem == 4)) || (year == 3 && (sem == 5 || sem == 6))) {
			
		//By Using updateCourse() Method For To Update The Course Detail In The Particular Department
			c.updateCourse(code, subject, dno, sem, credits, year);
			session.removeAttribute("UpdateSubject");
			session.removeAttribute("UpdateCourseDno");
			session.removeAttribute("UpdateCourseYear");
			session.removeAttribute("UpdateCourseSem");
			session.removeAttribute("UpdateCredits");
			session.removeAttribute("UpdateCode");
			response.sendRedirect("UpdateCourse.jsp?success=true");
		}
				
		//When The Condition Is False It Redirect To UpdateCourse.jsp Page
		else {
			request.getRequestDispatcher("UpdateCourse.jsp?success=false").forward(request, response);;
		}
	}

}
