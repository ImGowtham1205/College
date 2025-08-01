import java.io.IOException;

import com.DaoClass.CheckGivenTime;
import com.DaoClass.FetchStudentRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CheckTime")
public class CheckTime extends HttpServlet {
	//This Servlet Is Use For To Checks Staff Selected the Valid Time To Mark Thier Attendance
	private static final long serialVersionUID = 1L;
       
	FetchStudentRecord fsr=new FetchStudentRecord();
    CheckGivenTime cgt=new CheckGivenTime();

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
	    String time="";
	    
	    //Check Whether The Staff Selected valid Time Or Not For Mark Their Attendance For The Students
	    if(("08:30".equals(begintime) && "09:15".equals(endtime)) ||
	       ("09:15".equals(begintime) && "10:00".equals(endtime)) ||
	       ("10:15".equals(begintime) && "11:00".equals(endtime)) ||
	       ("11:00".equals(begintime) && "11:45".equals(endtime)) ||
	       ("11:45".equals(begintime) && "12:30".equals(endtime))) {
	    	request.setAttribute("fetch", "yes");
	    	
	    	//Concatenate Begin & End Time 
	    	time=begintime.concat("-"+endtime);
	    	
	    	//Getting Time By Calling getTime() Method Using Time,Date,Staff Id & Subject Code  As Arugment
		    String gettime=cgt.getTime(time, date, sid, code);
		    
		    //Check The Selected Time Is Already Entered In Database If True Then It Not Allow To Mark The Attendance For Selected Time
		    if (time.equals(gettime)) 
		        request.getRequestDispatcher("PutAttendance.jsp?time=taken&subject="+subject+"&code="+code).forward(request, response);
		    
		    //Selected Time Is Not Exist In Database Then It Allow To Mark The Attendance For Selected Time
		    else{
		        request.setAttribute("fetch", "yes");
		        request.getRequestDispatcher("PutAttendance.jsp?subject="+subject+"&code="+code).forward(request, response);
		    } 
		} 
	    
	    //Staff Selected Invalid Time For Mark Attendance It Not Allow To Mark The Attendance For Selected Time
	    else 
		    request.getRequestDispatcher("PutAttendance.jsp?time=invalid&subject="+subject+"&code="+code).forward(request, response);   
	}
}
