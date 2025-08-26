<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<%
	//Read The Values from The View Attendance Function
    String subject = request.getParameter("subject");
    String code = request.getParameter("code");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ViewAttendance</title>
<link rel="stylesheet" href="ViewPercentage.css" />
</head>
<body>
	<%!String name,dep; %>

	<%
		//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0"); // Proxies

		 //If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
		if(session.getAttribute("Rollno") == null) {
    		response.sendRedirect("StudentLogin.jsp");
    		return; 
		
		}
		
		//Read The Student Rollno from session 
		String id=session.getAttribute("Rollno").toString();
		int rollno=Integer.parseInt(id);
		
		//Getting Student Name & Department Name By Calling fetchName() & fetchDname() Method Using Student RollNo As Arugment
		FetchStudent fs=new FetchStudent();
		name=fs.fetchName(rollno);
		dep=fs.fetchDname(rollno);
		
		//Getting Individula Papers Attendance Details By Calling getAttendanceDetails() Method using Student RollNo & Subject Code As Arugment
		FetchAttendanceDetails fad=new FetchAttendanceDetails();
		List<Attendance>list=fad.getAttendanceDetails(rollno, code);
%>
	<!-- Navigation Bar-->
	<nav>
    <div class="nav-left">Welcome,<%=name %> </div>
    <div class="nav-links">
    	<a href="StudentWelcome.jsp">Home</a>
        <a href="PersonalInfo.jsp">Personal Info</a>
        <a href="CourseDetails.jsp">Course Details</a>
        <a href="ChangePassword.jsp">Change Password</a>
        <a href="ViewAttendance.jsp">View Attendance</a>
        <a href="StudentRequest.jsp">Update Personal Info</a>
        <form action="Logout" method="post"><button class="logout-btn">Logout</button></form>
    </div>
</nav>
	
	<!-- Display Subject Detail -->
    <div class="info">
        <h2>Attendance Details (Class-wise)</h2>
        <p><b>Programme Name</b>: <%=dep %></p>
        <p><b>Course</b>: <%= subject %> (<%= code %>)</p>
        <p><b>Student Name</b>: <%=name %></p>
        <p><b>Student RollNo</b>: <%=rollno %></p>
    </div>

	<!-- Display Subject Attendance Details -->
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>Teacher</th>
                <th>Attendance</th>
            </tr>
        </thead>
        <tbody>
        
        <%for(Attendance a:list){ 
        	String date=a.getDate();
        	String time=a.getTime();
        	String staff=a.getStaff_name();
        	String attendance=a.getAttendance();
        %>
            <tr>
                <td data-label="Date"><%=date %></td>
                <td data-label="Time"><%=time %></td>
                <td data-label="Teacher"><%=staff %></td>
                <td data-label="Attendance"><%=attendance %></td>
            </tr>
        <% }%>    
        </tbody>
    </table>
</body>
</html>
