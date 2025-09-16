<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*"%>
    
 <%! String aname;
	int aid;
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
 <link rel="stylesheet" href="csscodes/ChangeAdminPassword.css" />
 <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
</head>
<body>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
		if(session.getAttribute("Adminid")==null){
			response.sendRedirect("AdminLogin.jsp");
			return;
		}
	//Read The Admin Id  from session
	String id=session.getAttribute("Adminid").toString();
	aid=Integer.parseInt(id);

	//Getting Admin Name By Calling fetchName() Method Using Admin ID As Arugment
	FetchStaff fs=new FetchStaff();
	aname=fs.fetchName(aid);
%>

  <!-- Navigation Bar -->
<nav>
  <!-- Hamburger Menu -->
  <div class="menu-toggle" onclick="toggleSidebar(this)">
      <span></span>
      <span></span>
      <span></span>
  </div>

  <!-- Welcome Text -->
  <div class="nav-welcome">
    Welcome, <%= aname %>
  </div>

  <!-- Logout Button -->
  <div class="nav-right">
    <form class="logout-form" action="AdminLogout" method="post">
      <button type="submit" class="logout-btn">Logout</button>
    </form>
  </div>
</nav>
 
    <div class="sidebar" id="sidebar">
      <a href="AdminWelcome.jsp">Admin Welcome Page</a>
      <a href="AddStudent.jsp">Add Student Details</a>	
      <a href="StudentUpdate.jsp">Update Student Details</a>
      <a href="StaffUpdate.jsp">Update Staff Details</a>
      <a href="StudentDelete.jsp">Delete Student Record</a>
      <a href="StaffDelete.jsp">Delete Staff Record</a>
    </div>
<!-- Messages -->
<div class="form-container">
<div class="main-content">
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
		<form id="PassForm" action="ChangeAdminPass" method="post" novalidate>
			<label for="currentPassword">Current Password</label> 
			<input type="password" id="currentPassword" name="currentPassword" required  />
			<span class="error-text"></span>
			
			<label for="newPassword">New Password</label> 
			<input type="password" id="newPassword" name="newPassword" required pattern="^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{7,}$"/> 
			<span class="error-text"></span>
			
			<label for="confirmPassword">Confirm New Password</label> 
			<input type="password" id="confirmPassword" name="confirmPassword" required pattern="^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{7,}$"/>
			<span class="error-text"></span>
			
			<button type="submit">Change Password</button>
		</form>
	</div>
	</div>
	<script src="jscodes/AdminMenu.js"></script>
	<script src="jscodes/Password.js"></script>
</body>
</html>