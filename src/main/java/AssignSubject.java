import java.io.IOException;

import com.DaoClass.AssignStaff;
import com.DaoClass.FetchStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/AssignSubject")
public class AssignSubject extends HttpServlet {
	
	//This Servlet Is Use For To Assign The Subject To The Selected Staff 
	private static final long serialVersionUID = 1L;
	
	AssignStaff as=new AssignStaff();
	FetchStaff fs=new FetchStaff();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Values From The Form
		String no=request.getParameter("staffname");
		String subject=request.getParameter("subject");
		String code=request.getParameter("code");
		int year=Integer.parseInt(request.getParameter("year"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		int sid=Integer.parseInt(no);
				
		//Get The Staff By Using Staffid
		String name=fs.fetchName(sid);
	
		//Reads HOD ID From session 
		HttpSession session =request.getSession();
		int hodid=(int) session.getAttribute("Hodid");
		
		//Getting Subject Name By Calling subjectName() Method Using HOD ID & Subject Code As Arugment
		String record=as.subjectName(hodid, code);
		
		//Getting Staff Id  By Calling assignStaffCheck() Method Using HOD & Staff ID As Arugment
		int val=as.assignStaffCheck(sid, hodid,code);
		
		//Check The Subject Is Already Assigned
		if(subject.equals(record)) 
			request.getRequestDispatcher("AssignSubject.jsp?status=exists").forward(request, response);
		
		//To Check The Staff For Already Assigned Any Subject
		else if(val==sid)
			request.getRequestDispatcher("AssignSubject.jsp?status=assign").forward(request, response);
		
		//Assign The Subjcet To The Staff
		else {
			as.assignStaff(hodid, name,subject,code,sid,year,sem);
			request.getRequestDispatcher("AssignSubject.jsp?status=success").forward(request, response);
		}
	}

}
