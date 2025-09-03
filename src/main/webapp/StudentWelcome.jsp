<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.sql.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Student Welcome Page</title>
  <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
  <link rel="stylesheet" href="csscodes/StudentWelcome.css" />
</head>
<body>

<%
	// If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Expires", "0"); 

   // If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
    if(session.getAttribute("Rollno") == null) {
        response.sendRedirect("StudentLogin.jsp");
        return; 
    }

   // Read The Student Rollno from session 
    String id = session.getAttribute("Rollno").toString();
    int rollno = Integer.parseInt(id);

  	// Getting Student Name By Calling fetchName() Method Using Student RollNo As Arugment
    FetchStudent sn = new FetchStudent();
    String name = sn.fetchName(rollno);

  	// Getting Current Semester Papers By Calling getCourseNameByStudent() Method using Student RollNo As Arugment
    FetchCourse fc = new FetchCourse();
    List<CourseName> list = fc.getCourseNameByStudent(rollno);
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
    <a href="PersonalInfo.jsp">Personal Info</a>
    <a href="CourseDetails.jsp">Course Details</a>
    <a href="ChangePassword.jsp">Change Password</a>
    <a href="ViewAttendance.jsp">View Attendance</a>
    <a href="StudentRequest.jsp">Update Personal Info</a>
</div>

<!-- Displaying Student Dashboard -->
<div class="container">
    <div class="section">
        <h2>Student Dashboard</h2>
        <div class="info-grid">
            <% for(CourseName course : list) {
                String cname = course.getCoursename();
                String ccode = course.getCoursecode();
            %>
            <div class="info-item" onclick="viewAttendance('<%= cname %>', '<%= ccode %>')">
                <h5><%= cname %></h5>
                <p>Course Code: <%= ccode %></p>
            </div>
            <% } %>
        </div>
    </div>
</div>

<script src="jscodes/ViewAttendance.js"></script>
<script src="jscodes/Menu.js"></script>

</body>
</html>
