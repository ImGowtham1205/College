import java.io.IOException;

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

@SuppressWarnings("serial")
@WebFilter("/StaffUpdate")
public class AdminUpdateStaff extends HttpFilter implements Filter {
	//Filter For To Check Admin ID Is Match To Thier Department Number If True Then It Allow To Update The Staff Record
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Creating HttpServelet Request & Response Object
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		
		//Read Admin ID from session
		HttpSession session=req.getSession();
		String id=session.getAttribute("Adminid").toString();
		
		int aid=Integer.parseInt(id);
		//Read Department Number From The Form
		int dno=Integer.parseInt(req.getParameter("dno"));
		
		if((aid==501 && dno==5)||(aid==1001 && dno==10)||(aid==1501 && dno==15)||(aid==2001 && dno==20)||(aid==2501 && dno==25)||(aid==3001 && dno==30)
		||(aid==3501 && dno==35)||(aid==4001 && dno==40)||(aid==4501 && dno==45))
			 	chain.doFilter(request, response);
			
		else 
		    res.sendRedirect("StaffUpdate.jsp?error=unauthorized");
	}
}