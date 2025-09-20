import java.io.IOException;

import com.DaoClass.GetCourseDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/FetchCourseDelete")

//This Servlet Is Use For Admin Entered Course Code In The Form And Click Fetch Button It Fills The Values In The DeleteCourse.jsp
public class FetchCourseDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	GetCourseDetails gc=new GetCourseDetails();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read Value From The Form
		String code=request.getParameter("code");
		
		String subject="";
		int dno,sem,year,credits;
		
		 /*Getting Subject Name ,Departmant Number,Semester,year & Credits
		 By Calling fetchSubjectName(),fetchDno(),fetchYear() ,fetchCredits() & fecthSem() Method
		 Using Subject Code As Arugment*/
		subject=gc.fetchSubjectName(code);
		dno=gc.fetchDno(code);
		sem=gc.fetchSem(code);
		year=gc.fetchYear(code);
		credits=gc.fetchcredits(code);
		
		//Creating A HttpSession
		HttpSession session = request.getSession();
		
		// If No Record Found For The Given Student Rollno Then It Removes Session Values By Using removeAttribute() Method
		if (subject == null || subject.isEmpty()) {
			session.removeAttribute("DeleteSubject");
			session.removeAttribute("DeleteCourseDno");
			session.removeAttribute("DeleteCourseYear");
			session.removeAttribute("DeleteCourseSem");
			session.removeAttribute("DeleteCredits");
			session.removeAttribute("DeleteCode");
			response.sendRedirect("DeleteCourse.jsp?fetchError=true");
		} 
				
		//If Record Found For The Given Student Rollno Then It Set Session Values By Using setAttribute() Method
		else {
			session.setAttribute("DeleteSubject", subject);
			session.setAttribute("DeleteCourseDno", dno);
			session.setAttribute("DeleteCourseYear", year);
			session.setAttribute("DeleteCourseSem", sem);
			session.setAttribute("DeleteCredits", credits);
			session.setAttribute("DeleteCode", code);
			response.sendRedirect("DeleteCourse.jsp");
		}		
	}
}