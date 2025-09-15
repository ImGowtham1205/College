<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>
    
<% 
	FetchAssignStaff fas=new FetchAssignStaff();
	@SuppressWarnings("unchecked")
	List<FetchAssignStaff> semlist = (List<FetchAssignStaff>) request.getAttribute("subject");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Assign Subject</title>
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
<link rel="stylesheet" href="csscodes/HodWelcome.css" />
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
		FetchStaff fh=new FetchStaff();
		
		//Getting Hod Name By Calling fetchName() Method Using HOD ID As Arugment
		name=fh.fetchName(hodid);
%>

<!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
  <button class="hamburger" onclick="toggleSidebar()">☰</button>
  <h2>Welcome, <%=name %></h2>
</div>
  

  <div class="nav-right">
    <!-- Hamburger menu button -->
    <form action="HodLogout" method="post">
      <button class="logout-btn">Logout</button>
    </form>
  </div>
</nav>

  <div id="sidebar" class="sidebar">
  	<a href="javascript:void(0)" class="closebtn" onclick="toggleSidebar()">×</a>
    <a href="HodWelcome.jsp">Home Page</a>
    <a href="ChangeHodPassword.jsp">Change Password</a>
    <a href="HodRequest.jsp">Update Personal Info</a>
    <a href="UpdateSubject.jsp">Update Assigned Subject</a>
    <a href="DeleteSubject.jsp">Delete Assigned Subject</a>
  </div>

<!-- Message -->
<% String fetch=request.getParameter("fetch"); %>
<% if("error".equals(fetch)){ %>
  <div class="error-msg" id="serverMsg">Select valid Semester And Year</div>
<% } %>

<!-- Form For Fetch The Selected Sem Subjects -->
<form id="hodForm" class="hod-form" method="post" action="SelectAssginedSubject" novalidate>
  <div class="form-group">
    <label for="year">Year</label>
    <select id="year" name="year">
      <option value="" disabled selected>Select Year</option>
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <div class="field-error" id="yearError" aria-live="polite"></div>
  </div>

  <div class="form-group">
    <label for="sem">Semester</label>
    <select id="sem" name="sem">
      <option value="" disabled selected>Select Semester</option>
      <option value="1">1</option>
      <option value="3">3</option>
      <option value="5">5</option>
    </select>
    <div class="field-error" id="semError" aria-live="polite"></div>
  </div>

  <div class="form-action">
    <button type="submit" class="fetch-btn">Fetch</button>
  </div>
</form>


 <!-- It Checks That Hod Assigned Any Subjects To Staffs If Assigend Then Display Staffs Who Are Handle Subject 
 Else It Display "You Didn't Assign Subjects To Any Staff" Message-->
 
	<div class="container">
  <% 
    if (semlist != null) {
        if (semlist.isEmpty()) { 
  %>
          <h3>You Didn't Assign Subjects To Any Staff.</h3>
  <%
        } else {
  %>
          <h3>Assigned Subjects For Selected Semester</h3>
          <% for (FetchAssignStaff sub : semlist) {
              String subject = sub.getSubject(); 
              String code = sub.getCode();
              String sname=sub.getName();
              int id = sub.getId();
              int sem = sub.getSem();
          %>
              <div class="subject-card">
                  <%= subject %><br>
                  Course Code : <%=code %><br>
                  Staff Name : <%= sname %><br>
                  Staff Id : <%= id %><br>
                  Semester : <%= sem %>
              </div>
          <% } %>
  <%
        }
    } else { 
  %>
        <h3>No subjects loaded yet. Please select Year And Semester.</h3>
  <% } %>
</div>
	
<script src="jscodes/HodMenu.js"></script> 
<script src="jscodes/FetchValidation.js"></script>
</body>
</html>