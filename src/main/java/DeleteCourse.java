import java.io.IOException;

import com.DaoClass.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteCourse")
public class DeleteCourse extends HttpServlet {
	
	//This Servlet Is Use For Delete Course Details In The Particular Department
	private static final long serialVersionUID = 1L;

	Course c=new Course();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read The Values From The Form
		String code=request.getParameter("code");		
		int year=Integer.parseInt(request.getParameter("year"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		int dno=Integer.parseInt(request.getParameter("dno"));
		
		//Creating a HttpSession
		HttpSession session=request.getSession();
		
		//Check When Admin Is Entered valid Year & Semester If True Then It Allow To Delete Course Detail In Particular Department
		if ((year == 1 && (sem == 1 || sem == 2)) || (year == 2 && (sem == 3 || sem == 4)) || (year == 3 && (sem == 5 || sem == 6))) {
			
		//By Using deleteCourse() Method For To Add The Course In The Particular Department
			c.deleteCourse(code,dno);
			session.removeAttribute("DeleteSubject");
			session.removeAttribute("DeleteCourseDno");
			session.removeAttribute("DeleteCourseYear");
			session.removeAttribute("DeleteCourseSem");
			session.removeAttribute("DeleteCredits");
			session.removeAttribute("DeleteCode");
			response.sendRedirect("DeleteCourse.jsp?success=true");
		}
				
		//When The Condition Is False It Redirect To DeleteCourse.jsp Page
		else {
			request.getRequestDispatcher("DeleteCourse.jsp?success=false").forward(request, response);;
		}
	}

}
