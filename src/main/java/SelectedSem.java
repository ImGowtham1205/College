import java.io.IOException;
import java.util.List;

import com.DaoClass.CourseName;
import com.DaoClass.FetchCourse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SelectedSem")
public class SelectedSem extends HttpServlet {
	//This Servlet Is Use For To Fetch The Selected Semester Subjects
	private static final long serialVersionUID = 1L;
			
	FetchCourse fc=new FetchCourse();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Values From The Form
		int sem=Integer.parseInt(request.getParameter("sem"));
		int year=Integer.parseInt(request.getParameter("year"));
	
		//Reads HOD ID From session 
		HttpSession session =request.getSession();
		int hodid=(int) session.getAttribute("Hodid");
		
		//Check When Hod Is Entered valid Year & Semester If True Then It Allow To Fetch The Subject Details
		if((year==1 && sem==1) || (year==2 && sem==3)|| (year==3 && sem==5)) {
			List<CourseName>list=fc.getCourseName(hodid, sem, year);
			request.setAttribute("subject", list);
			request.getRequestDispatcher("HodWelcome.jsp").forward(request, response);
		}
		else
			response.sendRedirect("HodWelcome.jsp?fetch=error");
	}
}