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

	// Redirect to login if Admin not logged in
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
	String fetchedName = "", fetchedEmail = "", fetchedBlood = "", fetchedPhone = "", fetchedAddress = "", fetchedDno = "", fetchedyear= "",fetchedsem="";
	int studentIdParam = 0;

	// Read values from session
	if (session.getAttribute("Updaterollno") != null)
    	studentIdParam = (int) session.getAttribute("Updaterollno");
	if (session.getAttribute("UpdateName") != null)
    	fetchedName = session.getAttribute("UpdateName").toString();
	if (session.getAttribute("UpdateMail") != null)
    	fetchedEmail = session.getAttribute("UpdateMail").toString();
	if (session.getAttribute("UpdateBlood") != null)
    	fetchedBlood = session.getAttribute("UpdateBlood").toString();
	if (session.getAttribute("UpdatePhone") != null)
    	fetchedPhone = session.getAttribute("UpdatePhone").toString();
	if (session.getAttribute("UpdateAddress") != null)
    	fetchedAddress = session.getAttribute("UpdateAddress").toString();
	if (session.getAttribute("UpdateDno") != null)
    	fetchedDno = session.getAttribute("UpdateDno").toString();
	if (session.getAttribute("Updateyear") != null)
		fetchedyear = session.getAttribute("Updateyear").toString();
	if (session.getAttribute("Updatesem") != null)
		fetchedsem = session.getAttribute("Updatesem").toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Update Student Details</title>
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
    <a href="DeleteCourse.jsp">Delete Course details</a>
  </div>

<div class="container">
	<div class="main-content">
<!-- Display Messages -->
<%
String error = request.getParameter("error");
String success = request.getParameter("success");

if ("unauthorized".equals(error)) {
%>
  <div class="error-msg msg-box">You are not authorized to Update student details for the selected department.</div>
<% } else if ("true".equals(success)) { %>
  <div class="success-msg msg-box">Student details Update successfully.</div>
<% }  else if("false".equals(success)) {%>
	<div class="error-msg msg-box">Select Valid Semester For Update Student Record.</div>
<%} %>

<% if ("true".equals(request.getParameter("fetchError"))) { %>
  <div class="error-msg msg-box">No student record found with the entered Roll Number.</div>
<% } %>
  <h2>Update Student Details</h2>
  <!-- Form to Fetch Student Details -->
  <form id="fetchStudentForm" class="update-form" method="post" action="FetchStudentUpdate" novalidate>
    <label for="studentId">Roll Number:</label>
    <input type="number" id="studentId" name="studentId" required value="<%= studentIdParam != 0 ? String.valueOf(studentIdParam) : "" %>">
    <button type="submit" name="fetch" value="true">Fetch</button>
  </form>

  <!-- Form to Update Student Details -->
  <form id="updateStudentForm" class="update-form" action="StudentUpdate" method="post" novalidate>
    <input type="hidden" name="studentId" value="<%= studentIdParam != 0 ? String.valueOf(studentIdParam) : "" %>">

    <label for="studentName">Name:</label>
    <input type="text" id="studentName" name="name" required value="<%= fetchedName %>">
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
	
    <label for="studentEmail">Email:</label>
    <input type="email" id="studentEmail" name="email" required value="<%= fetchedEmail %>">
	<small class="error-text"></small>
	
    <label for="blood-group">Blood Group:</label>
    <select id="blood-group" name="blood" required>
      <option value="" disabled <%= fetchedBlood.isEmpty() ? "selected" : "" %>>Select Blood Group</option>
      <% String[] bloodGroups = {"A+ve", "A-ve", "B+ve", "B-ve", "O+ve", "O-ve", "AB+ve", "AB-ve"}; 
         for(String bg : bloodGroups) { %>
        <option value="<%= bg %>" <%= bg.equals(fetchedBlood) ? "selected" : "" %>><%= bg %></option>
      <% } %>
    </select>
	<small class="error-text"></small>
	
    <label for="phone">Phone Number:</label>
    <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required placeholder="10-digit number" value="<%= fetchedPhone %>">
	<small class="error-text"></small>
	
    <label for="address">Address:</label>
    <textarea id="address" name="address" rows="3" required><%= fetchedAddress %></textarea>
	<small class="error-text"></small>
	
	<label for="year">Year:</label>
	<select id="year" name="year" required>
  		<option value="" disabled <%= fetchedyear.isEmpty() ? "selected" : "" %>>Select Year</option>
  		<% String[] year = {"1", "2", "3"}; 
     		for(String y : year) { %>
    	<option value="<%= y %>" <%= y.equals(fetchedyear) ? "selected" : "" %>><%= y %></option>
  		<% } %>
	</select>
	<small class="error-text"></small>

	<label for="sem">Semester:</label>
	<select id="sem" name="sem" required>
  		<option value="" disabled <%= fetchedsem.isEmpty() ? "selected" : "" %>>Select Semester</option>
  		<% String[] sem = {"1", "2", "3","4","5","6"}; 
     		for(String s : sem) { %>
    <option value="<%= s %>" <%= s.equals(fetchedsem) ? "selected" : "" %>><%= s %></option>
  <% } %>
	</select>
	<small class="error-text"></small>
	
    <button type="submit" id="updateBtn" <%= studentIdParam == 0 ? "disabled" : "" %>>Update</button>
    
  </form>
</div>
</div>
	<script src="jscodes/AdminMenu.js"></script>
    <script src="jscodes/ManageStudent.js"></script>
</body>
</html>
