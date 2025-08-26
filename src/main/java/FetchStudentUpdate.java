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
			session.removeAttribute("Name");
			session.removeAttribute("Blood");
			session.removeAttribute("Phone");
			session.removeAttribute("Mail");
			session.removeAttribute("Address");
			session.removeAttribute("Dno");
			session.removeAttribute("year");
			session.removeAttribute("sem");
			session.removeAttribute("rollno");
			response.sendRedirect("StudentUpdate.jsp?fetchError=true");
		} 
		
		//If Record Found For The Given Student Rollno Then It Set Session Values By Using setAttribute() Method
		else {
			session.setAttribute("Name", name);
			session.setAttribute("Blood", blood);
			session.setAttribute("Phone", phone);
			session.setAttribute("Mail", mail);
			session.setAttribute("Address", address);
			session.setAttribute("Dno", dno);
			session.setAttribute("year", year);
			session.setAttribute("sem", sem);
			session.setAttribute("rollno", rollno);
			response.sendRedirect("StudentUpdate.jsp");
		}
	}
}
