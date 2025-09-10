import java.io.IOException;

import com.DaoClass.FetchStudent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/FetchStudentDelete")
public class FetchStudentDelete extends HttpServlet {
	//This Servlet Is Use For Admin Entered Staff Id In The Form And Click Fetch Button It Fills The Values In The StudentDelete.jsp
	
	private static final long serialVersionUID = 1L;

	FetchStudent fs = new FetchStudent();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Reads Student Rollno From The Form
		int rollno = Integer.parseInt(request.getParameter("studentId"));
		
		String name = "", blood = "", phone = "", mail = "", address = "";
		int dno,year,sem;

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

		// If No Record Found For The Given Staff Id Then It Removes Session Values By Using removeAttribute() Method
		if (name == null || name.isEmpty()) {
			session.removeAttribute("DeleteName");
			session.removeAttribute("DeleteBlood");
			session.removeAttribute("DeletePhone");
			session.removeAttribute("DeleteMail");
			session.removeAttribute("DeleteAddress");
			session.removeAttribute("DeleteDno");
			session.removeAttribute("Deleteyear");
			session.removeAttribute("Deletesem");
			session.removeAttribute("Deleterollno");
			response.sendRedirect("StudentDelete.jsp?fetchError=true");
		}

		//If Record Found For The Given Staff Id Then It Set Session Values By Using setAttribute() Method
		else {
			session.setAttribute("DeleteName", name);
			session.setAttribute("DeleteBlood", blood);
			session.setAttribute("DeletePhone", phone);
			session.setAttribute("DeleteMail", mail);
			session.setAttribute("DeleteAddress", address);
			session.setAttribute("DeleteDno", dno);
			session.setAttribute("Deleteyear", year);
			session.setAttribute("Deletesem", sem);
			session.setAttribute("Deleterollno", rollno);
			response.sendRedirect("StudentDelete.jsp");
		}
	}
}