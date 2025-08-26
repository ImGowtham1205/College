import java.io.IOException;

import com.DaoClass.FetchStaff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/FetchStaffUpdate")
public class FetchStaffUpdate extends HttpServlet {
	
	//This Servlet Is Use For Admin Entered Staff Id In The Form And Click Fetch Button It Fills The Values In The StaffUpdate.jsp
	private static final long serialVersionUID = 1L;

	FetchStaff fs=new FetchStaff();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads Staff ID From The Form
		int sid=Integer.parseInt(request.getParameter("staffId"));
		
		String name="",phone="",mail="",des="";
		int dno;
		
		 /*Getting Staff Name ,Departmant Number,Phone Number,Mail & Desigination
		 By Calling fetchName(),fetchDno(),fetchPhone(),fetchMail(),fetchDesigination() Method
		 Using StaffId As Arugment*/
		name=fs.fetchName(sid);
		dno=fs.fetchDno(sid);
		mail=fs.fetchMail(sid);
		phone=fs.fetchPhone(sid);
		des=fs.fetchDesigination(sid);
		
		//Creating A HttpSession
		HttpSession session=request.getSession();
		
		// If No Record Found For The Given Staff Id Then It Removes Session Values By Using removeAttribute() Method
		if(name == null || name.isEmpty()) {
			 session.removeAttribute("Name");
	         session.removeAttribute("Phone");
	         session.removeAttribute("Mail");
	         session.removeAttribute("Desigination");
	         session.removeAttribute("Dno");
	         session.removeAttribute("staffid");
	         response.sendRedirect("StaffUpdate.jsp?fetchError=true");
		}
		
		//If Record Found For The Given Staff Id Then It Set Session Values By Using setAttribute() Method
		else {
		    session.setAttribute("Name", name);
		    session.setAttribute("Phone", phone);
		    session.setAttribute("Mail", mail);
		    session.setAttribute("Desigination", des);
		    session.setAttribute("Dno", dno);
		    session.setAttribute("staffid", sid);
			response.sendRedirect("StaffUpdate.jsp");
		}
	}
}