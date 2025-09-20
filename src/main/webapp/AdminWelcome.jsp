<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Welcome Page</title>
  <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
  <link rel="stylesheet" href="csscodes/AdminWelcome.css" />
</head>
<body>
  <%! int aid; 
      String aname="";    
  %>
   <%
 //If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("pragma","no-cache");
    response.setHeader("Expires", "0");

  //If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
    if(session.getAttribute("Adminid")==null){
        response.sendRedirect("AdminLogin.jsp");
        return;
    }

	//Read The Admin Id  from session
    String id=session.getAttribute("Adminid").toString();
    aid=Integer.parseInt(id);

    //Getting Admin Name By Calling fetchName() Method Using Admin ID As Arugment
    FetchStaff fs=new FetchStaff();
    aname=fs.fetchName(aid);

    //Getting Student & Staff Request By Calling get3rdYearStudentRequest(),get2ndYearStudentRequest(),get1stYearStudentRequest()&getStaffRequest()Method
    FetchRequest fr=new FetchRequest();
    List<StudentRequestForAdmin> student3rdyearlist=fr.get3rdYearStudentRequest(aid);
    List<StudentRequestForAdmin> student2ndyearlist=fr.get2ndYearStudentRequest(aid);
    List<StudentRequestForAdmin> student1styearlist=fr.get1stYearStudentRequest(aid);
    List<StaffRequestForAdmin> stafflist=fr.getStaffRequest(aid);
   %>
   
<!-- Navigation Bar -->
<nav>
  <!-- Hamburger Menu -->
  <div class="menu-toggle" onclick="toggleSidebar(this)">
      <span></span>
      <span></span>
      <span></span>
  </div>

  <!-- Welcome Text -->
  <div class="nav-welcome">
    Welcome, <%= aname %>
  </div>

  <!-- Logout Button -->
  <div class="nav-right">
    <form class="logout-form" action="AdminLogout" method="post">
      <button type="submit" class="logout-btn">Logout</button>
    </form>
  </div>
</nav>


<div class="sidebar" id="sidebar">
    <a href="AddStudent.jsp">Add Student Details</a>    
    <a href="StudentUpdate.jsp">Update Student Details</a>
    <a href="StaffUpdate.jsp">Update Staff Details</a>
    <a href="StudentDelete.jsp">Delete Student Record</a>
    <a href="StaffDelete.jsp">Delete Staff Record</a>
    <a href="ChangeAdminPassword.jsp">Change Password</a>
    <a href="AddCourse.jsp">Add Course</a>
    <a href="UpdateCourse.jsp">Update Course details</a>
    <a href="DeleteCourse.jsp">Delete Course details</a>
</div>

   <div class="main-content">  
 <!-- It Checks That Any Request Assigned For Admin If Assigend Then Display The Assigned Request Else It Display "No Request Assigned" Message-->
  <div class="container">
    <% if ((student3rdyearlist == null || student3rdyearlist.isEmpty()) && (student2ndyearlist == null || student2ndyearlist.isEmpty())&&(student1styearlist == null || student1styearlist.isEmpty()) && (stafflist == null || stafflist.isEmpty())) { %>
        <h3>No Request Available At This Time...</h3>
    <% } else { %>
        <h3>Assigned Request For You:</h3>
        <% 
        //Student Requests For 3rd Year
        for (StudentRequestForAdmin student: student3rdyearlist) {
        %>
            <div class="subject-card" data-type="student" data-reqid="<%= student.getReqid() %>" data-rollno="<%= student.getRollno() %>" 
            data-name="<%= student.getName().replace("\"", "&quot;").replace("'", "\\'") %>" 
            data-request="<%= student.getRequest().replace("\"", "&quot;").replace("'", "\\'") %>"
            onclick="handleRequestClick(this)">
            
                Request Id: <%= student.getReqid() %><br>
                Roll No: <%= student.getRollno() %><br>
                Student Name: <%= student.getName() %><br>
                Request: <%= student.getRequest() %><br>
            </div>
            <br>
        <% } %>
		<% 
        //Student Requests For 2nd Year
        for (StudentRequestForAdmin student: student2ndyearlist) {
        %>
            <div class="subject-card" data-type="student" data-reqid="<%= student.getReqid() %>" data-rollno="<%= student.getRollno() %>" 
            data-name="<%= student.getName().replace("\"", "&quot;").replace("'", "\\'") %>" 
            data-request="<%= student.getRequest().replace("\"", "&quot;").replace("'", "\\'") %>"
            onclick="handleRequestClick(this)">
            
                Request Id: <%= student.getReqid() %><br>
                Roll No: <%= student.getRollno() %><br>
                Student Name: <%= student.getName() %><br>
                Request: <%= student.getRequest() %><br>
            </div>
            <br>
        <% } %>
		<% 
        //Student Requests For 1st Year
        for (StudentRequestForAdmin student: student1styearlist) {
        %>
            <div class="subject-card" data-type="student" data-reqid="<%= student.getReqid() %>" data-rollno="<%= student.getRollno() %>" 
            data-name="<%= student.getName().replace("\"", "&quot;").replace("'", "\\'") %>" 
            data-request="<%= student.getRequest().replace("\"", "&quot;").replace("'", "\\'") %>"
            onclick="handleRequestClick(this)">
            
                Request Id: <%= student.getReqid() %><br>
                Roll No: <%= student.getRollno() %><br>
                Student Name: <%= student.getName() %><br>
                Request: <%= student.getRequest() %><br>
            </div>
            <br>
        <% } %>
		<br>
        <% 
        // Staff Requests
        for (StaffRequestForAdmin staff: stafflist) {
        %>
            <div class="subject-card"  data-type="staff" data-reqid="<%= staff.getReqid() %>" data-staffid="<%= staff.getStaffId() %>"
                 data-name="<%= staff.getName().replace("\"", "&quot;").replace("'", "\\'") %>"
                 data-request="<%= staff.getRequest().replace("\"", "&quot;").replace("'", "\\'") %>"
                 onclick="handleRequestClick(this)">
                 
                Request Id: <%= staff.getReqid() %><br>
                Staff Id: <%= staff.getStaffId() %><br>
                Staff Name: <%= staff.getName() %><br>
                Request: <%= staff.getRequest() %><br>
            </div>
        <% } %>   
    <% } %>
  </div>    
</div>
<script src="jscodes/Request.js"></script>
<script src="jscodes/AdminMenu.js"></script>
</body>
</html>