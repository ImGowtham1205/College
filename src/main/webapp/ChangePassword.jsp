<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.FetchStudent"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm Password</title>
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
 <link rel="stylesheet" href="csscodes/ConfirmPassword.css" />
</head>
<body>
<%!String name=" "; %>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page	
	if (session.getAttribute("Rollno") == null) {
		response.sendRedirect("StudentLogin.jsp");
		return;
	}
		
		//Read The Student Rollno from session 
		String id=session.getAttribute("Rollno").toString();
		int rollno=Integer.parseInt(id);
		
		//Getting Student Name By Calling fetchName() Method Using Student RollNo As Arugment
	    FetchStudent fs=new FetchStudent();
	    name=fs.fetchName(rollno);
	%>
	
	<!-- Overlay for sidebar -->
	<div id="overlay" onclick="closeSidebar()"></div>
	
	 <!-- Navigation Bar-->
	<nav>
	<div class="menu-toggle" id="menuToggle" onclick="toggleMenu()">☰</div>  
    <div class="nav-left">Welcome, <%=name %></div> 
    
    <!-- Logout always on right -->
    <form action="Logout" method="post" class="logout-form">
        <button class="logout-btn">Logout</button>
    </form>   
    <div class="sidebar" id="sidebarMenu">
        <a href="StudentWelcome.jsp">Home</a>
        <a href="PersonalInfo.jsp">Personal Info</a>
        <a href="CourseDetails.jsp">Course Details</a>
        <a href="ViewAttendance.jsp">View Attendance</a>
        <a href="StudentRequest.jsp">Update Personal Info</a>
    </div>
</nav>

<div class="container">
<div class="form-container">

 <!-- Messages -->
<%
	String status = request.getParameter("status");
	if("unmatched".equals(status)){
%>
	<div class="error-msg" id="errorMsg">
   		New password And Confirm Password Are Not Matched...
  </div>
<%
	}
	else if("notmatched".equals(status)){
%>
	<div class="error-msg" id="errorMsg">
   		Current Password Not Matched ...
  </div>
<%} 
	else if("success".equals(status)){
%>
	<div class="success-msg" id="errorMsg">
   		Password Changed Successfully ...
  </div>
<%} %>
 	<!-- Form For change The Password-->
		<h2>Change Password</h2>
		<form id="PassForm" action="ChangePass" method="post" novalidate>
			<label for="currentPassword">Current Password</label> 
			<input type="password" id="currentPassword" name="currentPassword" required />
			<span class="error-text"></span>
			
			<label for="newPassword">New Password</label> 
			<input type="password" id="newPassword" name="newPassword" required /> 
			<span class="error-text"></span>
			
			<label for="confirmPassword">Confirm New Password</label> 
			<input type="password" id="confirmPassword" name="confirmPassword" required />
			<span class="error-text"></span>
			
			<button type="submit">Change Password</button>
		</form>
		</div>
	</div>
	<script src="jscodes/Menu.js"></script>
	<script src="jscodes/Password.js"></script>
</body>
</html>