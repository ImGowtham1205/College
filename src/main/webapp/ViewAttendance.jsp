<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Attendance</title>
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
<link rel="stylesheet" href="csscodes/ViewAttendance.css" />
</head>
<body>
<%! 
    float overallpresent, overallclass, totalpercent; 
%>
<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies

    String name = "", cname = "", ccode = "";

    //If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
    if (session.getAttribute("Rollno") == null) {
        response.sendRedirect("StudentLogin.jsp");
        return;
    }
	
  	//Read The Student Rollno from session  
    String id = session.getAttribute("Rollno").toString();
    int rollno = Integer.parseInt(id);

  	//Getting Student Name By Calling fetchName() Method Using Student RollNo As Arugment
    FetchStudent sn = new FetchStudent();
    name = sn.fetchName(rollno);

  	/*Getting Current Semester Papers By Calling getCourseNameByStudent() Method using Student RollNo As Arugment
  	  For View Their Attendance Percentage For The Individul Attendance Percentage */
    FetchCourse fc = new FetchCourse();
    List<CourseName> list = fc.getCourseNameByStudent(rollno);

  /*Getting Student OverAll Class Attended And OverAll Class Held 
  	By Calling getOverAllPresent() And getOverAllClass() Method Using Student RollNo As Arugment*/
    Percentage p = new Percentage();
    overallpresent = p.getOverAllPresent(rollno);
    overallclass = p.getOverAllClass(rollno);

    //If The OverAll Class Held Is Greater Than Zero Then Calculate The Attendance Percentage If Not Then Assign The Percentage As 0 
    if (overallclass > 0) 
        totalpercent = (overallpresent / overallclass) * 100;
     else 
        totalpercent = 0;
    
    String percent = String.format("%.0f", totalpercent);
    
    //It Checks For Dynamic Styling For Overall Percentage
    String overallClass = "good";
    if (totalpercent >= 51 && totalpercent <75) overallClass = "medium";
    else if (totalpercent <= 50) overallClass = "low";
%>

<!-- Overlay for sidebar -->
<div id="overlay" onclick="closeSidebar()"></div>

<!-- Navigation Bar-->
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
        <a href="StudentRequest.jsp">Update Personal Info</a>
    </div>


<div class="container">
    <div class="section">
        <h2>Current Semester Attendance Details :</h2>
        <!-- Display The Student OverAll Attendance Percentage-->
        <h2 class="attendance-percent <%= overallClass %>">Your OverAll Percentage : <%= percent %>%</h2>
        
        <!-- Display The Student Individual Subject Attendance Percentage-->
        <div class="info-grid">
        <% for (CourseName course : list) {
                cname = course.getCoursename();
                ccode = course.getCoursecode();
				
                /*Getting Student Individual Class Attended And Individual Class Held 
              	By Calling classAttendedBySubject() And getOverAllClassBySubject() Method Using Student RollNo As Arugment*/
                
                float present = p.classAttendedBySubject(rollno, ccode);
                String present1 = String.format("%.0f", present);
                float attend = p.getOverAllClassBySubject(rollno, ccode);
                String attend1 = String.format("%.0f", attend);
				
              //If The Individual Class Held Is Greater Than Zero Then Calculate The Attendance Percentage If Not Then Assign The Percentage As 0 
                float total = (attend > 0) ? (present / attend) * 100 : 0;
                String total1 = String.format("%.2f", total);
                
                //It Checks For Dynamic Styling For Individual Percentage
                String percentClass = "good";
                if (total >=50 && total <75) percentClass = "medium";
                else if (total < 50) percentClass = "low";   
        %>
            <div class="info-item" onclick="viewAttendance('<%= cname %>', '<%= ccode %>')">
                <h5><%= cname %></h5>
                <p>Course Code : <%= ccode %></p>
                <p>Total Hours Attended : <%= present1 %></p>
                <p>Total Hours Held : <%= attend1 %></p>
                <p class="attendance-percent <%= percentClass %>">Percentage : <%= total1 %>%</p>
            </div>
        <% } %>
        </div>
    </div>
</div>
<script src="jscodes/ViewAttendance.js"></script> 
<script src="jscodes/Menu.js"></script>
</body>
</html>
