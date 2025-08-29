<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request To Admin</title>
<link rel="stylesheet" href="csscodes/Request.css" />
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
    
  //Getting Student Name By Calling fetchName() Method Using Student RollNo As Arugment
    FetchStudent sn = new FetchStudent();
    name = sn.fetchName(rollno); 
%>

<!-- Navigation Bar -->
<nav>
    <div class="nav-left">Welcome, <%= name %></div>
    <div class="nav-links">
    	<a href="StudentWelcome.jsp">Home</a>
        <a href="PersonalInfo.jsp">Personal Info</a>
        <a href="CourseDetails.jsp">Course Details</a>
        <a href="ChangePassword.jsp">Change Password</a>
        <a href="ViewAttendance.jsp">View Attendance</a>
        <form action="Logout" method="post">
            <button class="logout-btn">Logout</button>
        </form>
    </div>
</nav>

 <!-- Messages -->
<%
String success = request.getParameter("success");

if ("success".equals(request.getParameter("success"))) { %>
  <div class="success-msg">
     Request Sent Successfully.
  </div>
<% } %>

<!-- Form For Sending Request To Admin -->
<div class="form-wrapper">
    <div class="register-container">
        <h2>Request To Admin</h2>
        <form action="StudentRequest" method="post">
            <div class="input-group">
                <label for="request">Request</label>
                <textarea id="request" name="request" rows="5" required></textarea>
            </div>
            <button type="submit">Send</button>
        </form>
    </div>
</div>

</body>
</html>
