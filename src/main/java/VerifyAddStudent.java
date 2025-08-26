import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/AddStudent")
public class VerifyAddStudent extends HttpFilter implements Filter {
        
	private static final long serialVersionUID = 1L;

	//Filter For To Check Admin ID Is Match To Thier Department Number If True Then It Allow To Add The Student Record
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Creating HttpServelet Request & Response Object 
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
				
		//Read Admin ID from session
		HttpSession session=req.getSession();
		String id=session.getAttribute("Adminid").toString();
		
		int aid=Integer.parseInt(id);
		
		//Read Department Number From The Form
		String dep=req.getParameter("department");
				
		if(aid==501 && dep.equals("B.com(General)") || aid==1001 && dep.equals("B.com(Coporate Secretaryship)") || aid==1501 && dep.equals("B.com(A&F)")
		|| aid==2001 && dep.equals("B.com(Bank Management)") || aid==2501 && dep.equals("B.com(ISM)") || aid==3001 && dep.equals("B.com(CA)")	
		|| aid==3001 && dep.equals("B.com(General)") || aid==3501 && dep.equals("BBA") || aid==4001 && dep.equals("B.Sc(Computer Science)")
		|| aid==4501 && dep.equals("BCA"))
			chain.doFilter(request, response);
		
		else 
			res.sendRedirect("AddStudent.jsp?success=unauthorized");
	}
	
}
