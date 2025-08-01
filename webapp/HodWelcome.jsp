<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>HOD Dashboard</title>
  <link rel="stylesheet" href="HodWelcome.css" />
</head>
<body>
	<%! String name=" "; %>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if (session.getAttribute("Hodid") == null) {
		response.sendRedirect("HodLogin.jsp");
		return;
	}
	
		//Read The HOD ID from session
		int hodid=(int)session.getAttribute("Hodid");
		FetchHod fh=new FetchHod();
		
		//Getting Hod Name By Calling fetchName() Method Using HOD ID As Arugment
		name=fh.fetchName(hodid);
		
		//Getting Current Semester Papers By Calling getCourseNameByStudent() Method using HOD ID As Arugment For Assign The Subject To Staffs
		FetchCourse fc=new FetchCourse();
		List<CourseName> list=fc.getCourseName(hodid);
%>

 <!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <h2>Welcome, <%=name %></h2>
  </div>
  <div class="nav-right">
    <a href="ChangeHodPassword.jsp">Change Password</a>
    <a href="HodRequest.jsp">Update Personal Info</a>
    <a href="ViewAssignSubject.jsp">View Assign Subject</a>
    <form action="HodLogout" method="post"><button class="logout-btn">Logout</button></form>
  </div>
</nav>
  
  <!-- Displaying Current Semester Papers -->
  <div class="container">
    <h3>Current Semester Papers</h3>
    <div class="subject-grid">
      <% 
      
      for(CourseName course : list) { 
      String subjectName = course.getCoursename(); 
      String code=course.getCoursecode();
%>
  <div class="subject-card" onclick="assignSubject('<%= subjectName %>', '<%= code %>')">
    <%= subjectName %>	<br>
     Course Code : <%=code %>
  </div>
<% } %>
      
    </div>
  </div>
  
  <!-- This Is Function Is Used For When The HOD Click Any Subject Name It Will Redirect To Assign Selected Subject To Staff-->
  <script>
  function assignSubject(subjectName, code) {
    window.location.href = "AssignSubject.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code);
  }
</script> 	
  </body>
</html>