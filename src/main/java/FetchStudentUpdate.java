import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DaoClass.FetchStudent;

@WebServlet("/FetchStudentUpdate")
public class FetchStudentUpdate extends HttpServlet {
	//This Servlet Is Use For Admin Entered Staff Id In The Form And Click Fetch Button It Fills The Values In The StudentUpdate.jsp
	
	private static final long serialVersionUID = 1L;

	FetchStudent fs = new FetchStudent();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reads Student Rollno From The Form
		int rollno = Integer.parseInt(request.getParameter("studentId"));
		
		String name = "", blood = "", phone = "", mail = "", address = "";
		int dno ,year,sem;

		 /*Getting Student Name ,Departmant Number,Blood Group,Phone Number,Mail &A ddress
		 By Calling fetchName(),fetchDno(),fetchPhone(),fetchMail(),fetchAddress(),fetchBlood(),fetchYear() & fecthSem() Method
		 Using StaffId As Arugment*/
		name = fs.fetchName(rollno);
		blood = fs.fetchBlood(rollno);
		phone = fs.fetchPhone(rollno);
		mail = fs.fetchMail(rollno);
		address = fs.fetchAddress(rollno);
		dno = fs.fetchDno(rollno);
		year=fs.fetchYear(rollno);
		sem=fs.fetchSem(rollno);
		
		//Creating A HttpSession
		HttpSession session = request.getSession();

		// If No Record Found For The Given Student Rollno Then It Removes Session Values By Using removeAttribute() Method
		if (name == null || name.isEmpty()) {
			session.removeAttribute("UpdateName");
			session.removeAttribute("UpdateBlood");
			session.removeAttribute("UpdatePhone");
			session.removeAttribute("UpdateMail");
			session.removeAttribute("UpdateAddress");
			session.removeAttribute("UpdateDno");
			session.removeAttribute("Updateyear");
			session.removeAttribute("Updatesem");
			session.removeAttribute("Updaterollno");
			response.sendRedirect("StudentUpdate.jsp?fetchError=true");
		} 
		
		//If Record Found For The Given Student Rollno Then It Set Session Values By Using setAttribute() Method
		else {
			session.setAttribute("UpdateName", name);
			session.setAttribute("UpdateBlood", blood);
			session.setAttribute("UpdatePhone", phone);
			session.setAttribute("UpdateMail", mail);
			session.setAttribute("UpdateAddress", address);
			session.setAttribute("UpdateDno", dno);
			session.setAttribute("Updateyear", year);
			session.setAttribute("Updatesem", sem);
			session.setAttribute("Updaterollno", rollno);
			response.sendRedirect("StudentUpdate.jsp");
		}
	}
}
