<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request To Admin</title>
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
<link rel="stylesheet" href="csscodes/Request.css"/>
</head>
<body>
<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    String name = "";

  	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
    if(session.getAttribute("Rollno") == null) {
        response.sendRedirect("StudentLogin.jsp");
        return;
    }

  	//Read The Student Rollno from session 
    String id = session.getAttribute("Rollno").toString();
    int rollno = Integer.parseInt(id);
    
    //Getting Student Name & year By Calling fetchName(), fetchyear() Method Using Student RollNo As Arugment
    FetchStudent fs = new FetchStudent();
    name = fs.fetchName(rollno); 
    
  //Getting Student By Calling getIndividualStudentRequest()Method
  FetchRequest fr=new FetchRequest();
  List<StudentRequestForAdmin> list=fr.getIndividualStudentRequest(rollno);
%>

<!-- Overlay for sidebar -->
<div id="overlay" onclick="closeSidebar()"></div>

<!-- Navigation Bar -->
<nav>
    <div class="nav-left-group">
        <!-- Hamburger Icon -->
        <div class="menu-toggle" id="menuToggle" onclick="toggleMenu()">☰</div>
        <div class="nav-left">Welcome, <%= name %></div>
    </div>

    <!-- Logout always on right -->
    <form action="Logout" method="post" class="logout-form">
        <button class="logout-btn">Logout</button>
    </form>
</nav>  
    <div class="sidebar" id="sidebarMenu">
    	<a href="StudentWelcome.jsp">Home</a>
        <a href="PersonalInfo.jsp">Personal Info</a>
        <a href="CourseDetails.jsp">Course Details</a>
        <a href="ChangePassword.jsp">Change Password</a>
        <a href="ViewAttendance.jsp">View Attendance</a>
    </div>


 <!-- Messages -->
<%
String success = request.getParameter("success");

if ("success".equals(success)) { %>
  <div class="success-msg" id="successMsg">
     Request Sent Successfully.
  </div>
<% } %>

<!-- Form For Sending Request To Admin -->
<div class="form-wrapper">
    <div class="register-container">
        <h2>Request To Admin</h2>
        <form action="StudentRequest" method="post" onsubmit="return validateRequest()">
    <div class="input-group">
        <label for="request">Request</label>
        <textarea id="request" name="request" rows="5"></textarea>
        <small id="charCount" class="char-count">0 / 500 characters</small>
        <small id="errorMsg" class="error-msg"></small>
    </div>	
    <button type="submit">Send</button>
</form>
        
    </div>
</div>
<% if((list==null ||list.isEmpty())) {%>
	<h3>You Didn't Send Any Requst To Admin</h3>
<%} 
else { %>
	<h3>Your Request : </h3>
	<table>
        <thead>
            <tr>
                <th>Sno</th>
                <th>Request</th>
                <th>Progress</th>
            </tr>
        </thead>
        <tbody>
        <%  int i=0;
        	for(StudentRequestForAdmin s:list) { 
            String req=s.getRequest();
            String progress=s.getStatus();
            i++;
        %>
            <tr>
                <td data-label="Sno"><%=i %></td>
                <td data-label="Request"><%=req %></td>
                <td data-label="Progress"><%=progress %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
<%} %>
<script src="jscodes/RequestMenu.js"></script>
<script src="jscodes/Task.js"></script>
</body>
</html>
