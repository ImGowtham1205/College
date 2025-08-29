<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//Read The Values from The Update Student Request Function
    String reqid = request.getParameter("reqid");
    String rollno = request.getParameter("rollno");
    String name = request.getParameter("name");
    String req = request.getParameter("request");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Request</title>
<link rel="stylesheet" href="csscodes/UpdateStudentRequest.css" />
</head>
<body>

<%
//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
	response.setHeader("pragma","no-cache");//HTTP 1.0
	response.setHeader("Expires", "0");//Proxy Server
	
	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if(session.getAttribute("Adminid")==null){
		response.sendRedirect("AdminLogin.jsp");
		return;
	}
%>

 <!-- Navigation Bar -->
  <nav>
    <div class="nav-links">
      <a href="AdminWelcome.jsp">Admin Welcome Page</a>
      <a href="AddStudent.jsp">Add Student Details</a>	
      <a href="StudentUpdate.jsp">Update Student Details</a>
      <a href="StaffUpdate.jsp">Update Staff Details</a>
      <a href="StudentDelete.jsp">Delete Student Record</a>
      <a href="StaffDelete.jsp">Delete Staff Record</a>
      <a href="ChangeAdminPassword.jsp">Change Password</a>
    </div>
    <form class="logout-form" action="AdminLogout" method="post">
      <button type="submit">Logout</button>
    </form>
  </nav>
  
  <!-- Display Request Details-->
    <div class="info">
    
    <!-- Messages -->
  <% 
  String change = request.getParameter("change");
  if ("success".equals(change)) {
  %>
  	<div class="success-msg">
    	Update Request Successfully...
  </div>
  <%} 
  else if("fail".equals(change)){
  %>
  		<div class="error-msg">
    	First Select Complete To Update...
  </div>
  <%} %>
    
    <form action="UpdateStudentRequest" method="post">
    <!-- Hidden Form To Access The Values In UpdateStudentRequest Servlet -->
    	<input type="hidden" name="id" value="<%=reqid%>">
    	<input type="hidden" name="name" value="<%=name%>">
    	<input type="hidden" name="rollno" value="<%=rollno%>">
    	<input type="hidden" name="request" value="<%=req%>">
    	
        <h2><%= name %> Update Request Details</h2>
        <p><b>RollNo</b>: <%=rollno %></p>
        <p><b>Request Id </b>: <%=reqid %></p>
        <p><b>Request</b>: <%=req %></p>
        <label for="Pending">Pending</label>
        <input type="radio" name="status" id="pending" value="pending" checked>
        <label for="complete">Complete</label>
        <input type="radio" name="status" id="complete" value="complete">
        <button type="submit" class="submit">Submit</button>
     </form>   
    </div>
</body>
</html>