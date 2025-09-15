import java.io.IOException;
import java.util.List;

import com.DaoClass.FetchAssignStaff;
import com.DaoClass.FetchAssignedStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteAssignedStaff")
public class DeleteAssignedStaff extends HttpServlet {
	//This Servlet Is Use For To Fetch The Assigned Subjects For Delete Staff In Selected Semester
	private static final long serialVersionUID = 1L;
			
	FetchAssignedStaff fas=new FetchAssignedStaff();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Values From The Form
		int sem=Integer.parseInt(request.getParameter("sem"));
		int year=Integer.parseInt(request.getParameter("year"));
	
		//Reads HOD ID From session 
		HttpSession session =request.getSession();
		int hodid=(int) session.getAttribute("Hodid");
		
		//Check When Hod Is Entered valid Year & Semester If True Then It Allow To Fetch The Subject Details
		if((year==1 && sem==1)) {
			List<FetchAssignStaff>list=fas.get1stYearStaff(hodid);
			request.setAttribute("subject", list);
			request.getRequestDispatcher("DeleteSubject.jsp").forward(request, response);
		}
		else if((year==2 && sem==3)) {
			List<FetchAssignStaff>list=fas.get2ndYearStaff(hodid);
			request.setAttribute("subject", list);
			request.getRequestDispatcher("DeleteSubject.jsp").forward(request, response);
		}
		else if((year==3 && sem==5)) {
			List<FetchAssignStaff>list=fas.get3rdYearStaff(hodid);
			request.setAttribute("subject", list);
			request.getRequestDispatcher("DeleteSubject.jsp").forward(request, response);
		}
		else
			response.sendRedirect("DeleteSubject.jsp?fetch=error");
	}
}