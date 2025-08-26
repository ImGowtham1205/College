import java.io.IOException;
import java.util.List;

import com.DaoClass.FetchAssignStaff;
import com.DaoClass.FetchAssignedStaff;
import com.DaoClass.FetchStaff;
import com.DaoClass.UpdateStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateAssignSubject")
public class UpdateAssignSubject extends HttpServlet {
	//This Servlet Is Use For Update Staff For Assigned Subject
	
	private static final long serialVersionUID = 1L;
	
	FetchStaff fs=new FetchStaff();
	UpdateStaff us=new UpdateStaff();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads values From the Form
		String code=request.getParameter("code");
		String sname=request.getParameter("sname");
		int currentsid=Integer.parseInt(request.getParameter("sid"));
		int newsid=Integer.parseInt(request.getParameter("staffname"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		
		//Getting Staff Name By Calling fetchName() Method Using Staff ID As Arugment
		String staffname;
		staffname=fs.fetchName(newsid);
	 
		//Creating HttpSession To Get the HOD ID
		HttpSession session=request.getSession();
		int hodid=(int) session.getAttribute("Hodid");
		
		//Getting Staffs Who Are Handle Subject By Calling getStaff() Method
		FetchAssignedStaff fas=new FetchAssignedStaff();
		List<FetchAssignStaff>list3rdyear=fas.get3rdYearStaff(hodid);
		List<FetchAssignStaff>list2ndyear=fas.get2ndYearStaff(hodid);
		List<FetchAssignStaff>list1styear=fas.get1stYearStaff(hodid);
		
		// Check if the new staff is already assigned
		boolean isassigned3rd=false;
		boolean isassigned2nd=false;
		boolean isassigned1st=false;
		
		if(sem==5) {		
			for(FetchAssignStaff s:list3rdyear) {
				int id=s.getId();
				if(id==newsid) {
					isassigned3rd=true;
					break;
				}
			}			
		}
		
		else if(sem==3) {
			for(FetchAssignStaff s:list2ndyear) {
				int id=s.getId();
				if(id==newsid) {
					isassigned2nd=true;
					break;
				}
			}
		}
		else if(sem==1) {
			for(FetchAssignStaff s:list1styear) {
				int id=s.getId();
				if(id==newsid) {
					isassigned1st=true;
					break;
				}
			}
		}
		
		// New staff already handling a subject
		if(isassigned3rd)
			request.getRequestDispatcher("UpdateAssignSubject.jsp?status=match&staffid="+currentsid+"&staffname="+sname).forward(request, response);
		else if(isassigned2nd)
			request.getRequestDispatcher("UpdateAssignSubject.jsp?status=match&staffid="+currentsid+"&staffname="+sname).forward(request, response);
		else if(isassigned1st)
			request.getRequestDispatcher("UpdateAssignSubject.jsp?status=match&staffid="+currentsid+"&staffname="+sname).forward(request, response);
		// Proceed to update
		else {
			us.updateStaff(code, staffname, newsid ,hodid ,currentsid,sem);
			request.getRequestDispatcher("UpdateAssignSubject.jsp?status=success&staffname="+staffname+"&staffid="+newsid).forward(request, response);
	    }
	}
}