<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<%
    // Read The Values from The View Attendance Function
    String subject = request.getParameter("subject");
    String code = request.getParameter("code");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Attendance</title>
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
<link rel="stylesheet" href="csscodes/ViewPercentage.css" />
</head>
<body>
<%! String name, dep; %>

<%
    // Prevent back button after logout
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    // Only allow access if user logged in
    if(session.getAttribute("Rollno") == null) {
        response.sendRedirect("StudentLogin.jsp");
        return; 
    }

    // Read Rollno from session
    String id=session.getAttribute("Rollno").toString();
    int rollno=Integer.parseInt(id);

    // Fetch student name and department
    FetchStudent fs=new FetchStudent();
    name=fs.fetchName(rollno);
    dep=fs.fetchDname(rollno);

    // Fetch attendance details
    FetchAttendanceDetails fad=new FetchAttendanceDetails();
    List<Attendance> list = fad.getAttendanceDetails(rollno, code);
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
    <!-- Sidebar Menu -->
    <div class="sidebar" id="sidebarMenu">
        <a href="StudentWelcome.jsp">Home</a>
        <a href="PersonalInfo.jsp">Personal Info</a>
        <a href="CourseDetails.jsp">Course Details</a>
        <a href="ChangePassword.jsp">Change Password</a>
        <a href="ViewAttendance.jsp">View Attendance</a>
        <a href="StudentRequest.jsp">Update Personal Info</a>
    </div>

<!-- Main Content -->
<div class="container">
    <!-- Info Card -->
    <div class="info">
        <h2>Attendance Details (Class-wise)</h2>
        <p><b>Programme Name</b>: <%=dep %></p>
        <p><b>Course</b>: <%= subject %> (<%= code %>)</p>
        <p><b>Student Name</b>: <%=name %></p>
        <p><b>Student RollNo</b>: <%=rollno %></p>
    </div>

    <!-- Attendance Table -->
<div class="table-wrapper">
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>Teacher</th>
                <th>Attendance</th>
            </tr>
        </thead>
        <tbody>
        <% for(Attendance a:list) { 
            String date = a.getDate();
            String time = a.getTime();
            String staff = a.getStaff_name();
            String attendance = a.getAttendance();
        %>
            <tr>
                <td data-label="Date"><%=date %></td>
                <td data-label="Time"><%=time %></td>
                <td data-label="Teacher"><%=staff %></td>
                <td data-label="Attendance"><%=attendance %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>
</div>    
<script src="jscodes/Menu.js"></script>
</body>
</html>
