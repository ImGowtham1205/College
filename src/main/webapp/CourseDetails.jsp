<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.sql.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Course Details</title>
  <link rel="stylesheet" href="StudentWelcome.css" />
</head>
<body>

<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies

    String name = "", cname = "", ccode = "";
    int sem,credits,year;

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
    
    //Getting All Semester Papers By Calling getAllCourseName() Method using Student RollNo As Arugment
    FetchCourse fc=new FetchCourse();
   	List<CourseName> list=fc.getAllCourseName(rollno);
%>

<!-- Navigation Bar-->
<nav>
    <div class="nav-left">Welcome, <%= name %></div>
    <div class="nav-links">
        <a href="StudentWelcome.jsp">Home</a>
        <a href="PersonalInfo.jsp">Personal Info</a>
        <a href="ChangePassword.jsp">Change Password</a>
        <a href="ViewAttendance.jsp">View Attendance</a>
        <a href="StudentRequest.jsp">Update Personal Info</a>
        <form action="Logout" method="post"><button class="logout-btn">Logout</button></form>
    </div>
</nav>

<!-- Display The All Semester Papers Dynamically Based On The Student Department-->
<div class="container">
    <div class="section">
        <h2>Course Details</h2>
        <div class="info-grid">
        <% for(CourseName course : list){
        cname=course.getCoursename();
        ccode=course.getCoursecode();
        sem=course.getSem();
        credits=course.getCredits();
        year=course.getYear();
        %>
            <div class="info-item">
               <h5><%= cname %></h5>
               <p>Course Code : <%= ccode %></p>
               <p>Semester : <%= sem %></p>
               <p>Year : <%= year %></p>
               <p>Credits : <%= credits %></p>
            </div>
         <% }%>           
        </div>
    </div>
</div>
</body>
</html>
