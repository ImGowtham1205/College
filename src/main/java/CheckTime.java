import java.io.IOException;
import java.util.List;

import com.DaoClass.Attendance;
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

	@SuppressWarnings("unused")
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
	    int sem=Integer.parseInt(request.getParameter("sem"));
	    int year=Integer.parseInt(request.getParameter("year"));
	    String time="";
	    
	    boolean check3rd=false;
	    boolean check2nd=false;
	    boolean check1st=false;
	    
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
		    String gettime=cgt.getTime(time, date, sid, code,sem);
		    
		    /* In 3 Lists It Stores Staff Id & Selected Time For Mark Attendance For All Classes
		     With The help Of This Lists Checks whether The Staff Is Mark Attendance In Another Class Or Not 
		     If True Then It Not Allow To Mark The Attendance For Selected Time For Selected Class
		     */
		    List<Attendance>year3rd=cgt.get3rdYearTime(sid, 5, time, date);
		    List<Attendance>year2nd=cgt.get3rdYearTime(sid, 3, time, date);
		    List<Attendance>year1st=cgt.get3rdYearTime(sid, 1, time, date);
		    
		    //Checks In 3rd Year Class
		    for(Attendance a : year3rd) {
		    	String atime=a.getTime();
		    	int staffid=a.getStaffid();
		    	if(atime.equals(time) && staffid==sid) {
		    		check3rd=true;
		    	}
		    }
		    
		    //Checks In 2nd Year Class
		    for(Attendance a : year2nd) {
		    	String atime=a.getTime();
		    	int staffid=a.getStaffid();
		    	if(atime.equals(time) && staffid==sid) {
		    		check2nd=true;
		    	}
		    }
		    
		  //Checks In 1st Year Class
		    for(Attendance a : year1st) {
		    	String atime=a.getTime();
		    	int staffid=a.getStaffid();
		    	if(atime.equals(time) && staffid==sid) {
		    		check1st=true;
		    	}
		    }
		    
		    //Check The Selected Time Is Already Entered Attendance Or Not  If True Then It Not Allow To Mark The Attendance For Selected Time
		    if (time.equals(gettime)) 
		        request.getRequestDispatcher("PutAttendance.jsp?time=taken&subject="+subject+"&code="+code).forward(request, response);
		    else if(check3rd||check2nd||check1st)
		    	request.getRequestDispatcher("PutAttendance.jsp?time=mark&subject="+subject+"&code="+code).forward(request, response);
		    
		    //When The Staff Not Marked Attendance In Selected Time By Staff Then It Allow To Mark The Attendance For Selected Time
		    else{
		        request.setAttribute("fetch", "yes");
		        request.getRequestDispatcher("PutAttendance.jsp?subject="+subject+"&code="+code).forward(request, response);
		    } 
		} 
	    
	    //When The Staff Selected Invalid Time For Mark Attendance It Not Allow To Mark The Attendance For Selected Time
	    else 
		    request.getRequestDispatcher("PutAttendance.jsp?time=invalid&subject="+subject+"&code="+code).forward(request, response);   
	}
}
