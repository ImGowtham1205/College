<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*"%>
    
<%
	//Read The Values from The AssignSubject Function
    String subject = request.getParameter("subject");
    String code = request.getParameter("code"); 
    String staffname=request.getParameter("staffname");
    String staffid=request.getParameter("staffid");
    String sem=request.getParameter("sem");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Assign Subject</title>
<link rel="stylesheet" href="csscodes/UpdateAssignSubject.css" />
</head>
<body>
	<%! String name,hodname;%>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	///If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if (session.getAttribute("Hodid") == null) {
		response.sendRedirect("HodLogin.jsp");
		return;
	}
	
	//Read The HOD ID from session 
	String id=session.getAttribute("Hodid").toString();
	int hodid=Integer.parseInt(id);
	
	//Getting Hod Name By Calling fetchName() Method Using HOD ID As Arugment
	FetchStaff fh=new FetchStaff();
	hodname=fh.fetchName(hodid); 
	%>
	
<!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <span class="welcome-text">Welcome, <%= hodname %></span>
  </div>
  <div class="nav-right">
   <a href="HodWelcome.jsp" class="nav-link">Home Page</a>
    <a href="ChangeHodPassword.jsp" class="nav-link">Change Password</a>
    <a href="HodRequest.jsp" class="nav-link">Update Personal Info</a>
    <a href="ViewAssignSubject.jsp" class="nav-link">View Assign Subject</a>
    <a href="UpdateSubject.jsp" class="nav-link">Update Assigned Subject</a>
    <a href="DeleteSubject.jsp" class="nav-link">Delete Assigned Subject</a>
    <form action="HodLogout" method="post" class="logout-form"><button type="submit" class="logout-btn">Logout</button></form>
  </div>
</nav>

<h2>Delete Assign Subject</h2>
<!-- Messages -->
<%
	String status=request.getParameter("status");
	if("sucess".equals(status)){
%>
		<div class="success-msg">
   			Delete Successfully....
  		</div>
<%}%>	

<!-- Form For Delete The Selected Assigned Subject To The Staff -->
<form action="DeleteAssignSubject" method="post">
<label for="subject">Subject Name:</label>
<input type="text" name="subject" id="subject" value="<%=subject%>" readonly>

<label for="code">Subject Code:</label>
<input type="text" name="code" id="code" value="<%=code%>" readonly>

<label for="sname">Current Staff Name For Subject:</label>
<input type="text" name="sname" id="sname" value="<%=staffname%>" readonly>

<label for="subject">Current Staff Id For Subject:</label>
<input type="text" name="sid" id="sid" value="<%=staffid%>" readonly>

<input type="hidden" name="sem" value="<%= sem%>">

<button type="submit">Delete Staff</button>
</form>
</body>
</html>