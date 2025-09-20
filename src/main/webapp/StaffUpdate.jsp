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

String fetchedName = "", fetchedEmail = "", fetchedPhone = "", fetchedDesigination = "", fetchedDno = "";
int staffIdParam =0;

//Read values from session
if (session.getAttribute("StaffUpdatestaffid") != null)
 staffIdParam = (int) session.getAttribute("StaffUpdatestaffid");
if (session.getAttribute("StaffUpdateName") != null)
 fetchedName = session.getAttribute("StaffUpdateName").toString();
if (session.getAttribute("StaffUpdateMail") != null)
 fetchedEmail = session.getAttribute("StaffUpdateMail").toString();
if (session.getAttribute("StaffUpdatePhone") != null)
 fetchedPhone = session.getAttribute("StaffUpdatePhone").toString();
if (session.getAttribute("StaffUpdateDesigination") != null)
 fetchedDesigination = session.getAttribute("StaffUpdateDesigination").toString();
if (session.getAttribute("StaffUpdateDno") != null)
 fetchedDno = session.getAttribute("StaffUpdateDno").toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Update Staff Details</title>
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
    <a href="StudentUpdate.jsp">Update Student Details</a>
    <a href="StudentDelete.jsp">Delete Student Record</a>
    <a href="StaffDelete.jsp">Delete Staff Record</a>
    <a href="ChangeAdminPassword.jsp">Change Password</a>
    <a href="AddCourse.jsp">Add Course</a>
    <a href="UpdateCourse.jsp">Update Course details</a>
    <a href="DeleteCourse.jsp">Delete Course details</a>
  </div>
 
<div class="container">
<div class="main-content">
<!-- Messages -->
<%
String error = request.getParameter("error");
String success = request.getParameter("success");

if ("unauthorized".equals(error)) {
%>
  <div class="error-msg msg-box">
    You are not authorized to update staff details for the selected department.
  </div>
<% } 
else if ("true".equals(success)) { %>
  <div class="success-msg msg-box">
    Staff details updated successfully.
  </div>
<% }  

else if("invalid".equals(success)) {%>
	 <div class="error-msg msg-box">
    	A Department Must Have Only One Admin And HOD You Cannot Update Staff desigination To HOD Or Admin.
 	 </div> 
<%} %>
<% if ("true".equals(request.getParameter("fetchError"))) { %>
  <div class="error-msg msg-box">
    No staff record found with the entered Staff ID.
  </div>
<% } %>
  <h2>Update Staff Details</h2>

  <!-- Form to fetch data -->
  <form id="fetchStaffForm" class="update-form" method="post" action="FetchStaffUpdate" novalidate>
    <label for="staffId">Staff Id:</label>
    <input type="number" id="staffId" name="staffId" value="<%= staffIdParam != 0 ? String.valueOf(staffIdParam) : "" %>" required>
    <small class="error-text"></small>
    
    <button type="submit" name="fetch" value="true">Fetch</button>
  </form>

  <!-- Form to update Staff data -->
  <form id="updateStaffForm" class="update-form" action="StaffUpdate" method="post" novalidate>
    <input type="hidden" name="staffId" value="<%= staffIdParam != 0 ? String.valueOf(staffIdParam) : "" %>" required>

    <label for="staffName">Name:</label>
    <input type="text" id="staffName" name="staffName" required value="<%= fetchedName %>">
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

    <label for="staffEmail">Email:</label>
    <input type="email" id="staffEmail" name="staffEmail" required value="<%= fetchedEmail %>">
	<small class="error-text"></small>
	
    <label for="phone">Phone Number:</label>
    <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required placeholder="10-digit number" value="<%= fetchedPhone %>">
	<small class="error-text"></small>
	
	<label for="desigination">Desigination:</label>
    <select id="desigination" name="desigination" required>
      <option value="" disabled <%= fetchedDno.isEmpty() ? "selected" : "" %>>Select Desigination</option>
      <% String[] desigination = {"Admin", "HOD", "Professor"}; 
         for(String d : desigination) { %>
        <option value="<%= d %>" <%= d.equals(fetchedDesigination) ? "selected" : "" %>><%= d %></option>
      <% } %>
    </select>
	<small class="error-text"></small>
	
    <button type="submit" id="updateBtn" <%= staffIdParam == 0 ? "disabled" : "" %>>Update</button>
  </form>
</div>
</div>
<script src="jscodes/AdminMenu.js"></script>
<script src="jscodes/ManageStaff.js"></script>
</body>
</html>
