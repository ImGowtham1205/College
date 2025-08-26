<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

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

// Initialize variables
String fetchedName = "", fetchedEmail = "", fetchedBlood = "", fetchedPhone = "", fetchedAddress = "", fetchedDno = "", fetchedyear= "",fetchedsem="";
int studentIdParam = 0;

// Read values from session
if (session.getAttribute("rollno") != null)
    studentIdParam = (int) session.getAttribute("rollno");
if (session.getAttribute("Name") != null)
    fetchedName = session.getAttribute("Name").toString();
if (session.getAttribute("Mail") != null)
    fetchedEmail = session.getAttribute("Mail").toString();
if (session.getAttribute("Blood") != null)
    fetchedBlood = session.getAttribute("Blood").toString();
if (session.getAttribute("Phone") != null)
    fetchedPhone = session.getAttribute("Phone").toString();
if (session.getAttribute("Address") != null)
    fetchedAddress = session.getAttribute("Address").toString();
if (session.getAttribute("Dno") != null)
    fetchedDno = session.getAttribute("Dno").toString();
if (session.getAttribute("year") != null)
	fetchedyear = session.getAttribute("year").toString();
if (session.getAttribute("sem") != null)
	fetchedsem = session.getAttribute("sem").toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Update Student Details</title>
  <link rel="stylesheet" href="UpdatePage.css" />
</head>
<body>

<!-- Navigation Bar -->
<nav>
  <div class="nav-links">
    <a href="AdminWelcome.jsp">Admin Welcome Page</a>
    <a href="AddStudent.jsp">Add Student Details</a>	
    <a href="StaffUpdate.jsp">Update Staff Details</a>
    <a href="StudentDelete.jsp">Delete Student Record</a>
    <a href="StaffDelete.jsp">Delete Staff Record</a>
    <a href="ChangeAdminPassword.jsp">Change Password</a>
  </div>
  <form class="logout-form" action="AdminLogout" method="post">
    <button type="submit">Logout</button>
  </form>
</nav>

<!-- Display Messages -->
<%
String error = request.getParameter("error");
String success = request.getParameter("success");

if ("unauthorized".equals(error)) {
%>
  <div class="error-msg">You are not authorized to delete student details for the selected department.</div>
<% } else if ("true".equals(success)) { %>
  <div class="success-msg">Student details deleted successfully.</div>
<% }  else if("false".equals(success)) {%>
	<div class="error-msg">Select Valid Semester For Update Student Record.</div>
<%} %>

<% if ("true".equals(request.getParameter("fetchError"))) { %>
  <div class="error-msg">No student record found with the entered Roll Number.</div>
<% } %>

<div class="container">
  <h2>Update Student Details</h2>

  <!-- Form to Fetch Student Details -->
  <form class="update-form" method="post" action="FetchStudentUpdate">
    <label for="studentId">Roll Number:</label>
    <input type="number" id="studentId" name="studentId" required value="<%= studentIdParam != 0 ? String.valueOf(studentIdParam) : "" %>">
    <button type="submit" name="fetch" value="true">Fetch</button>
  </form>

  <!-- Form to Delete Student Details -->
  <form class="update-form" action="StudentUpdate" method="post">
    <input type="hidden" name="studentId" value="<%= studentIdParam != 0 ? String.valueOf(studentIdParam) : "" %>">

    <label for="studentName">Name:</label>
    <input type="text" id="studentName" name="name" required value="<%= fetchedName %>">

    <label for="dno">Department Number:</label>
    <select id="dno" name="dno" required>
      <option value="" disabled <%= fetchedDno.isEmpty() ? "selected" : "" %>>Select Department Number</option>
      <% String[] dnos = {"5", "10", "15", "20", "25", "30", "35", "40", "45"}; 
         for(String d : dnos) { %>
        <option value="<%= d %>" <%= d.equals(fetchedDno) ? "selected" : "" %>><%= d %></option>
      <% } %>
    </select>

    <label for="studentEmail">Email:</label>
    <input type="email" id="studentEmail" name="email" required value="<%= fetchedEmail %>">

    <label for="blood-group">Blood Group:</label>
    <select id="blood-group" name="blood" required>
      <option value="" disabled <%= fetchedBlood.isEmpty() ? "selected" : "" %>>Select Blood Group</option>
      <% String[] bloodGroups = {"A+ve", "A-ve", "B+ve", "B-ve", "O+ve", "O-ve", "AB+ve", "AB-ve"}; 
         for(String bg : bloodGroups) { %>
        <option value="<%= bg %>" <%= bg.equals(fetchedBlood) ? "selected" : "" %>><%= bg %></option>
      <% } %>
    </select>

    <label for="phone">Phone Number:</label>
    <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required placeholder="10-digit number" value="<%= fetchedPhone %>">

    <label for="address">Address:</label>
    <textarea id="address" name="address" rows="3" required><%= fetchedAddress %></textarea>

	<label for="year">Year:</label>
	<select id="year" name="year" required>
  		<option value="" disabled <%= fetchedyear.isEmpty() ? "selected" : "" %>>Select Year</option>
  		<% String[] year = {"1", "2", "3"}; 
     		for(String y : year) { %>
    	<option value="<%= y %>" <%= y.equals(fetchedyear) ? "selected" : "" %>><%= y %></option>
  		<% } %>
	</select>

	<label for="sem">Semester:</label>
	<select id="sem" name="sem" required>
  		<option value="" disabled <%= fetchedsem.isEmpty() ? "selected" : "" %>>Select Semester</option>
  		<% String[] sem = {"1", "2", "3","4","5","6"}; 
     		for(String s : sem) { %>
    <option value="<%= s %>" <%= s.equals(fetchedsem) ? "selected" : "" %>><%= s %></option>
  <% } %>
	</select>
    <button type="submit">Update</button>
  </form>
</div>

</body>
</html>
