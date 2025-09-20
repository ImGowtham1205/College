<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.DaoClass.*" %>

<%! String aname;
	int aid;
%>
<%
    //If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("pragma", "no-cache");
	response.setHeader("Expires", "0");

	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if (session.getAttribute("Adminid") == null) {
    	response.sendRedirect("AdminLogin.jsp");
    	return;
	}

	//Read The Admin Id  from session
	String id=session.getAttribute("Adminid").toString();
	aid=Integer.parseInt(id);

	//Getting Admin Name By Calling fetchName() Method Using Admin ID As Arugment
	FetchStaff fs=new FetchStaff();
	aname=fs.fetchName(aid);

	// Initialize variables
	String fetchedName = "",fetchedDno = "", fetchedyear= "",fetchedsem="",fetchedcredits="";
	String code = "";

	// Read values from session
	if (session.getAttribute("DeleteCode") != null)
    	code = session.getAttribute("DeleteCode").toString();
	if (session.getAttribute("DeleteSubject") != null)
    	fetchedName = session.getAttribute("DeleteSubject").toString();
	if (session.getAttribute("DeleteCourseDno") != null)
    	fetchedDno = session.getAttribute("DeleteCourseDno").toString();
	if (session.getAttribute("DeleteCourseYear") != null)
		fetchedyear = session.getAttribute("DeleteCourseYear").toString();
	if (session.getAttribute("DeleteCourseSem") != null)
		fetchedsem = session.getAttribute("DeleteCourseSem").toString();
	if (session.getAttribute("DeleteCredits") != null)   
		fetchedcredits = session.getAttribute("DeleteCredits").toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Delete Course Details</title>
   <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
  <link rel="stylesheet" href="csscodes/UpdatePage.css" />
</head>
<body>

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
    <a href="StaffUpdate.jsp">Update Staff Details</a>
    <a href="StudentDelete.jsp">Delete Student Record</a>
    <a href="StaffDelete.jsp">Delete Staff Record</a>
    <a href="ChangeAdminPassword.jsp">Change Password</a>
    <a href="AddCourse.jsp">Add Course</a>
    <a href="UpdateCourse.jsp">Update Course details</a>
  </div>

<div class="container">
	<div class="main-content">
<!-- Display Messages -->
<%
String error = request.getParameter("error");
String success = request.getParameter("success");

if ("unauthorized".equals(error)) {
%>
  <div class="error-msg msg-box">You are not authorized to Delete Course Details for the selected department.</div>
<% } else if ("true".equals(success)) { %>
  <div class="success-msg msg-box">Course details Deleted successfully.</div>
<% }  else if("false".equals(success)) {%>
	<div class="error-msg msg-box">Select Valid Semester For Delete Course Record.</div>
<%} %>

<% if ("true".equals(request.getParameter("fetchError"))) { %>
  <div class="error-msg msg-box">No course record found with the Entered Code.</div>
<% } %>
  <h2>Delete Course Details</h2>
  
  <!-- Form to Fetch Course Details -->
  <form id="fetchcourseForm" class="update-form" method="post" action="FetchCourseDelete" novalidate>
    <label for="code">Code:</label>
    <input type="text" id="code" name="code" 
     pattern="^[A-Z0-9]{6,}$" title="Course code must be at least 6 characters long, only capital letters and numbers allowed"
    required value="<%= code %>">
    <small class="error-text"></small>
    
    <button type="submit" name="fetch" value="true">Fetch</button>
  </form>

  <!-- Form to Delete Course Details -->
  <form id="updatecourseForm" class="update-form" action="DeleteCourse" method="post" novalidate>
    <input type="hidden" name="code" value="<%= code %>">

    <label for="subjectName">Subject Name:</label>
    <input type="text" id="subjectName" name="subject" required value="<%= fetchedName %>">
	<small class="error-text"></small>
	
    <label for="dno">Department Number:</label>
    <select id="dno" name="dno" required>
      <option value="" disabled <%= fetchedDno.isEmpty() ? "selected" : "" %>>Select Department Number</option>
      <% String[] dnos = {"5", "10", "15", "20", "25", "30", "35", "40", "45"}; 
         for(String d : dnos) { %>
        <option value="<%= d %>" <%= d.equals(fetchedDno) ? "selected" : "" %>><%= d %></option>
      <% } %>
    </select>
	<small class="error-text"></small>
	
	<label for="year">Year:</label>
	<select id="year" name="year" required>
  		<option value="" disabled <%= fetchedyear.isEmpty() ? "selected" : "" %>>Select Year</option>
  		<% String[] years = {"1", "2", "3"}; 
     		for(String y : years) { %>
    	<option value="<%= y %>" <%= y.equals(fetchedyear) ? "selected" : "" %>><%= y %></option>
  		<% } %>
	</select>
	<small class="error-text"></small>

	<label for="sem">Semester:</label>
	<select id="sem" name="sem" required>
  		<option value="" disabled <%= fetchedsem.isEmpty() ? "selected" : "" %>>Select Semester</option>
  		<% String[] sems = {"1", "2", "3","4","5","6"}; 
     		for(String s : sems) { %>
    <option value="<%= s %>" <%= s.equals(fetchedsem) ? "selected" : "" %>><%= s %></option>
  <% } %>
	</select>
	<small class="error-text"></small>
	
	<label for="credits">Credits:</label>
	<select id="credits" name="credits" required> 
  		<option value="" disabled <%= fetchedcredits.isEmpty() ? "selected" : "" %>>Select Credits</option>
  		<% String[] credits = {"3","4","5"}; 
     		for(String c : credits) { %>
    <option value="<%= c %>" <%= c.equals(fetchedcredits) ? "selected" : "" %>><%= c %></option>
  <% } %>
	</select>
	<small class="error-text"></small>
	
    <button type="submit" id="updateBtn" <%= code.isEmpty() ? "disabled" : "" %>>Delete</button>
    
  </form>
</div>
</div>
	<script src="jscodes/AdminMenu.js"></script>
	<script src="jscodes/UpdateCourseValidation.js"></script>
</body>
</html>
