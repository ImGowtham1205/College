<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.FetchStudent"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal Information</title>
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
    <link rel="stylesheet" href="csscodes/StudentWelcome.css" />
</head>
<body>
	<%! String name=" ",dname=" ",gender=" ",blood=" ",phone=" ",mail=" ",address=" "; 
		int dno,sem,year;
	%>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
	response.setHeader("pragma","no-cache");//HTTP 1.0
	response.setHeader("Expires", "0");//Proxy Server
	
	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if(session.getAttribute("Rollno")==null){
		response.sendRedirect("StudentLogin.jsp");
		return;
	}
	
		//Read The Student Rollno from session   
		String id=session.getAttribute("Rollno").toString();
		int rollno=Integer.parseInt(id);
		
		 /*Getting Student Name ,Departmant Name & Number,Gender,Blood Group,Phone Number,Mail,Address,Semester & Year
		 By Calling fetchName(),fetchDno(),fetchDname(),fetchGender(),fetchBlood(),fetchPhone(),fetchMail(),fetchAddress()
		 ,fetchSem() & fetchYear() Method
		 Using Student RollNo As Arugment*/
	     FetchStudent fs=new FetchStudent();
	     name=fs.fetchName(rollno);
	     dno=fs.fetchDno(rollno);
	     dname=fs.fetchDname(rollno);
	     gender=fs.fetchGender(rollno);
	     blood=fs.fetchBlood(rollno);
	     phone=fs.fetchPhone(rollno);
	     mail=fs.fetchMail(rollno);
	     address=fs.fetchAddress(rollno);
	     sem=fs.fetchSem(rollno);
	     year=fs.fetchYear(rollno);
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
      <a href="CourseDetails.jsp">Course Details</a>
      <a href="ChangePassword.jsp">Change Password</a>
      <a href="ViewAttendance.jsp">View Attendance</a>
      <a href="StudentRequest.jsp">Update Personal Info</a>
    </div>
 
<!-- Display Student Personal Details-->
  <div class="container">
    <div class="section">
      <h2>Personal Information</h2>
      <div class="info-grid">
        <div class="info-item">Name: <%= name %></div>
        <div class="info-item">Roll Number : <%= rollno %></div>
        <div class="info-item">Department : <%= dname %></div>
        <div class="info-item">Department Number : <%= dno %></div>
        <div class="info-item">Gender: <%= gender %></div>
        <div class="info-item">Blood Group: <%= blood %></div>
        <div class="info-item">Phone: <%= phone %></div>
        <div class="info-item">Email: <%= mail %></div>
        <div class="info-item">Address: <%= address %></div>
        <div class="info-item">Semester: <%= sem %></div>
        <div class="info-item">Year : <%= year %></div>
      </div>
    </div>
    </div>
    <script src="jscodes/Menu.js"></script>
</body>
</html>    