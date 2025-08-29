<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
 <link rel="stylesheet" href="csscodes/HodChangePass.css" />
</head>
<body>
<%!String name=" "; %>
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
		String id=session.getAttribute("Hodid").toString();
		int hodid=Integer.parseInt(id);
	    
		//Getting Hod Name By Calling fetchName() Method Using HOD ID As Arugment
	    FetchStaff fh=new FetchStaff();
	    name=fh.fetchName(hodid);
%>

 <!-- Navigation Bar-->
	<nav class="navbar">
  <div class="nav-left">
    <h2>Welcome, <%=name %></h2>
  </div>
  <div class="nav-right">
    <a href="HodWelcome.jsp">Home Page</a>
    <a href="HodRequest.jsp">Update Personal Info</a>
    <a href="ViewAssignSubject.jsp">View Assign Subject</a>
    <a href="UpdateSubject.jsp">Update Assigned Subject</a>
    <a href="DeleteSubject.jsp">Delete Assigned Subject</a>
    <form action="HodLogout" method="post"><button class="logout-btn">Logout</button></form>
  </div>
</nav>

<!-- Messages -->
<div class="form-container">
<%
	String status = request.getParameter("status");
	if("unmatched".equals(status)){
%>
	<div class="error-msg">
   		New password And Confirm Password Are Not Matched...
  </div>
<%
	}
	else if("notmatched".equals(status)){
%>
	<div class="error-msg">
   		Current Password Not Matched ...
  </div>
<%} 
	else if("success".equals(status)){
%>
	<div class="success-msg">
   		Password Changed Successfully ...
  </div>
<%} %>

<!-- Form For change The Password-->
		<h2>Change Password</h2>
		<form action="ChangeHodPass" method="post">
			<label for="currentPassword">Current Password</label> 
			<input type="password" id="currentPassword" name="currentPassword" required />

			<label for="newPassword">New Password</label> 
			<input type="password" id="newPassword" name="newPassword" required /> 
			
			<label for="confirmPassword">Confirm New Password</label> 
			<input type="password" id="confirmPassword" name="confirmPassword" required />

			<button type="submit">Change Password</button>
		</form>
	</div>
</body>
</html>