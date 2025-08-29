<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

String fetchedName = "", fetchedEmail = "", fetchedPhone = "", fetchedDesigination = "", fetchedDno = "";
int staffIdParam =0;

//Read values from session
if (session.getAttribute("staffid") != null)
 staffIdParam = (int) session.getAttribute("staffid");
if (session.getAttribute("Name") != null)
 fetchedName = session.getAttribute("Name").toString();
if (session.getAttribute("Mail") != null)
 fetchedEmail = session.getAttribute("Mail").toString();
if (session.getAttribute("Phone") != null)
 fetchedPhone = session.getAttribute("Phone").toString();
if (session.getAttribute("Desigination") != null)
 fetchedDesigination = session.getAttribute("Desigination").toString();
if (session.getAttribute("Dno") != null)
 fetchedDno = session.getAttribute("Dno").toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Delete Staff Details</title>
  <link rel="stylesheet" href="csscodes/UpdatePage.css" />
</head>
<body>

<!-- Navigation Bar -->
<nav>
  <div class="nav-links">
    <a href="AdminWelcome.jsp">Admin Welcome Page</a>
    <a href="AddStudent.jsp">Add Student Details</a>
    <a href="StudentUpdate.jsp">Update Student Details</a>
    <a href="StaffUpdate.jsp">Update Staff Details</a>
    <a href="StudentDelete.jsp">Delete Student Record</a>	
    <a href="ChangeAdminPassword.jsp">Change Password</a>
  </div>
  <form class="logout-form" action="AdminLogout" method="post">
    <button type="submit">Logout</button>
  </form>
</nav>

<!-- Messages -->
<%
String error = request.getParameter("error");
String success = request.getParameter("success");

if ("unauthorized".equals(error)) {
%>

  <div class="error-msg">
    You are not authorized to update staff details for the selected department.
  </div>
  
<% } else if ("true".equals(success)) { %>

  <div class="success-msg">
    Staff details updated successfully.
  </div> 
  
<% }
	else if("invalid".equals(success)){
%>

	 <div class="error-msg">
    	A Department Contains Atleast One Admin And HOD You Cannot Delete This Staff.
 	 </div> 
 	 	 
<%} 
	else if("handle".equals(success)){
%>

	<div class="error-msg">
    	You Can't Delete This Staff Record Because The Staff Handle Subject.
 	 </div> 

<%} %>
<% if ("true".equals(request.getParameter("fetchError"))) { %>
  <div class="error-msg">
    No staff record found with the entered Staff ID.
  </div>
  
<% } %>

<div class="container">
  <h2>Delete Staff Details</h2>

  <!-- Form to fetch data -->
  <form class="update-form" method="post" action="FetchStaffDelete">
    <label for="staffId">Staff Id:</label>
    <input type="number" id="staffId" name="staffId" value="<%= staffIdParam != 0 ? String.valueOf(staffIdParam) : "" %>" required>
    <button type="submit" name="fetch" value="true">Fetch</button>
  </form>

  <!-- Form to update data -->
  <form class="update-form" action="StaffDelete" method="post">
    <input type="hidden" name="staffId" value="<%= staffIdParam != 0 ? String.valueOf(staffIdParam) : "" %>" required>

    <label for="staffName">Name:</label>
    <input type="text" id="staffName" name="staffName" required value="<%= fetchedName %>">

    <label for="dno">Department Number:</label>
    <select id="dno" name="dno" required>
      <option value="" disabled <%= fetchedDno.isEmpty() ? "selected" : "" %>>Select Department Number</option>
      <% String[] dnos = {"5", "10", "15", "20", "25", "30", "35", "40", "45"}; 
         for(String d : dnos) { %>
        <option value="<%= d %>" <%= d.equals(fetchedDno) ? "selected" : "" %>><%= d %></option>
      <% } %>
    </select>

    <label for="staffEmail">Email:</label>
    <input type="email" id="staffEmail" name="staffEmail" required value="<%= fetchedEmail %>">

    <label for="phone">Phone Number:</label>
    <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required placeholder="10-digit number" value="<%= fetchedPhone %>">

   <label for="desigination">Desigination:</label>
    <select id="desigination" name="desigination" required>
      <option value="" disabled <%= fetchedDno.isEmpty() ? "selected" : "" %>>Select Desigination</option>
      <% String[] desigination = {"Admin", "HOD", "Professor"}; 
         for(String d : desigination) { %>
        <option value="<%= d %>" <%= d.equals(fetchedDesigination) ? "selected" : "" %>><%= d %></option>
      <% } %>
    </select>

    <button type="submit">Delete</button>
  </form>
</div>

</body>
</html>