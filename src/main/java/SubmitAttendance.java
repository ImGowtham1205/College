import java.io.IOException;

import com.DaoClass.FetchStudentRecord;
import com.DaoClass.PutAttendance;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SubmitAttendance")
public class SubmitAttendance extends HttpServlet {
	//This Servlet Is use For To Mark The Attendance For The Selected Subject
	private static final long serialVersionUID = 1L;

	 FetchStudentRecord fsr=new FetchStudentRecord();
	 PutAttendance pa=new PutAttendance();
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creating HttpSession To Get the Staff ID
		HttpSession session=request.getSession();
		int sid=(int)session.getAttribute("sid");
		
		//Reads Values From The Form
		String subject = request.getParameter("subject");
		String code = request.getParameter("code");
		String date = request.getParameter("date");
	    String begintime = request.getParameter("beginTime");
	    String endtime = request.getParameter("endTime");
	    String time =begintime.concat("-"+endtime);
	    String rollNumberList = request.getParameter("rollNumbers");
	    String[] rollNumbers = rollNumberList.split(",");
	    int sem=Integer.parseInt(request.getParameter("sem"));
	    String s=request.getParameter("sem");
	    String year=request.getParameter("year");    
	    
	    //By Using markAttendance() Method Can Able To Mark The Attendance For All Students For The Staff Selected Subject
	    for(String no : rollNumbers) 
	    	if(no != null) {
	    		int num=Integer.parseInt(no);
	    		String status=request.getParameter("status"+num);
	    		pa.markAttendance(date, code, subject, num, status, sid, time,sem);
	    }
	    request.getRequestDispatcher("PutAttendance.jsp?status=success&subject="+subject+"&code="+code+"&sem="+s+"&year="+year).forward(request, response);
	}
}
