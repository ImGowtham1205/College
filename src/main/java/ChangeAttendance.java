import java.io.IOException;

import com.DaoClass.UpdateAttendance;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChangeAttendance")
public class ChangeAttendance extends HttpServlet {
	//This Servlet Is Use For To Update Selected Student Attendance
	private static final long serialVersionUID = 1L;

	UpdateAttendance ua=new UpdateAttendance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creating HttpSession To Get the Staff ID
		HttpSession session=request.getSession();
		int sid=(int) session.getAttribute("sid");
		
		//Reads Values From The Form
		String begintime = request.getParameter("beginTime");
	    String endtime = request.getParameter("endTime");
		String subject = request.getParameter("subject");
		String code = request.getParameter("code");
	    String date=request.getParameter("date");
	    String attendance=request.getParameter("attendance");
	    String id=request.getParameter("studentname");
	    int rollno=Integer.parseInt(id);
	    String year=request.getParameter("year");
		String time="";
		
		//Check Whether The Staff Selected valid Time Or Not For Update Their Attendance For The Students
	    if(("08:30".equals(begintime) && "09:15".equals(endtime)) ||
	       ("09:15".equals(begintime) && "10:00".equals(endtime)) ||
	       ("10:15".equals(begintime) && "11:00".equals(endtime)) ||
	       ("11:00".equals(begintime) && "11:45".equals(endtime)) ||
	       ("11:45".equals(begintime) && "12:30".equals(endtime))) 
	    		//Concatenate Begin & End Time 
	    		time=begintime.concat("-"+endtime);
	    else
	    	request.getRequestDispatcher("ChangeAttendance.jsp?time=invalid").forward(request, response);
	    
	    /*Check Staff Marked Attendace For Selected Date & Time 
	     If Staff Not Marked Then It Not Allow To Update Attendance For Selected Date & Time For Selected Student 
	     */ 
	    int staffid=ua.checkAttendanceMarked(sid, code, time, date);
		if(sid!=staffid)	
			request.getRequestDispatcher("ChangeAttendance.jsp?time=not&subject="+subject+"&code="+code+"&year="+year).forward(request, response);
		
		//If Attendance Marked Then It Allow To Update Attendance For Selected Date & Time For Selected Student 
		else {
			ua.changeAttendance(rollno, attendance, date, code, time,sid);
			request.getRequestDispatcher("ChangeAttendance.jsp?status=success&subject="+subject+"&code="+code+"&year="+year).forward(request, response);
		}
			
	}
}
