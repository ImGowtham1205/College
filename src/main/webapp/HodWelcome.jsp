<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>
    
<% 
	FetchCourse fc=new FetchCourse();
	@SuppressWarnings("unchecked")
	List<CourseName> semlist = (List<CourseName>) request.getAttribute("subject");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>HOD Dashboard</title>
  <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
  <link rel="stylesheet" href="csscodes/HodWelcome.css" />
</head>
<body>
	<%! String name=" "; %>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	if (session.getAttribute("Hodid") == null) {
		response.sendRedirect("HodLogin.jsp");
		return;
	}
	
	int hodid=(int)session.getAttribute("Hodid");
	FetchStaff fh=new FetchStaff();
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

<!-- Sidebar -->
<div id="sidebar" class="sidebar">
  <a href="javascript:void(0)" class="closebtn" onclick="toggleSidebar()">×</a>
  <a href="ChangeHodPassword.jsp">Change Password</a>
  <a href="HodRequest.jsp">Update Personal Info</a>
  <a href="ViewAssignSubject.jsp">View Assign Subject</a>
  <a href="UpdateSubject.jsp">Update Assigned Subject</a>
  <a href="DeleteSubject.jsp">Delete Assigned Subject</a>
</div>

<!-- Message -->
<% String fetch=request.getParameter("fetch"); %>
<% if("error".equals(fetch)){ %>
  <div class="error-msg" id="serverMsg">Select valid Semester And Year</div>
<% } %>

<!-- Form For Fetch The Selected Sem Subjects -->
<form id="hodForm" class="hod-form" method="post" action="SelectedSem" novalidate>
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

<!-- Displaying Selected Semester Papers -->
<div class="container">
  <div class="subject-grid">
    <% if (semlist != null) { %>
      <h3>Selected Semester Papers</h3>
      <% for (CourseName course : semlist) {
        String subjectName = course.getCoursename(); 
        String code = course.getCoursecode();
        int sem = course.getSem();
        int year = course.getYear();
      %>
        <div class="subject-card" onclick="assignSubject('<%= subjectName %>', '<%= code %>' ,'<%= year %>' ,'<%= sem %>')">
            <%= subjectName %><br>
            Course Code : <%=code %><br>
            Semester : <%= sem %><br>
            Year : <%= year %>
        </div>
      <% } %>
    <% } else { %>
      <h3>No subjects loaded yet. Please select Year And Semester.</h3>
    <% } %>
  </div>
</div>

<script src="jscodes/HodMenu.js"></script> 
<script src="jscodes/AssignSubject.js"></script> 
<script src="jscodes/FetchValidation.js"></script>
</body>
</html>
