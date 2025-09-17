<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.DaoClass.*" %>    
 
 <%! String aname;
	int aid;
%>       
<%
	//Read The Values from The Update Staff Request Function
    String reqid = request.getParameter("reqid");
    String staffid = request.getParameter("staffid");
    String name = request.getParameter("name");
    String req = request.getParameter("request");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff Request</title>
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
	
	//Read The Admin Id  from session
		String id=session.getAttribute("Adminid").toString();
		aid=Integer.parseInt(id);

    //Getting Admin Name By Calling fetchName() Method Using Admin ID As Arugment
	FetchStaff fs=new FetchStaff();
	aname=fs.fetchName(aid);
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
      <a href="AdminWelcome.jsp">Admin Welcome Page</a>
      <a href="AddStudent.jsp">Add Student Details</a>	
      <a href="StudentUpdate.jsp">Update Student Details</a>
      <a href="StaffUpdate.jsp">Update Staff Details</a>
      <a href="StudentDelete.jsp">Delete Student Record</a>
      <a href="StaffDelete.jsp">Delete Staff Record</a>
      <a href="ChangeAdminPassword.jsp">Change Password</a>
    </div>
   
  
  <!-- Display Request Details-->
    <div class="info">
     <div class="main-content">
    <!-- Messages -->
  <% 
  String change = request.getParameter("change");
  if ("success".equals(change)) {
  %>
  	<div class="success-msg msg-box">
    	Request Updated Successfully...
  </div>
  <%} 
  else if("fail".equals(change)){
  %>
  <div class="error-msg msg-box">
    	Select Complete Or Reject To Update Request Log...
  </div>
  <%} 
  else if("reject".equals(change)){
  %>
  <div class="success-msg msg-box">
    	 Request Rejected Successfully...
  </div>
   <%} %> 
    <form action="UpdateStaffRequest" method="post">
    <!-- Hidden Form To Access The Values In UpdateStaffRequest Servlet -->
    	<input type="hidden" name="id" value="<%=reqid%>">
    	<input type="hidden" name="name" value="<%=name%>">
    	<input type="hidden" name="staffid" value="<%=staffid%>">
    	<input type="hidden" name="request" value="<%=req%>">
    	
        <h2><%= name %> Update Request Details</h2>
        <p><b>StaffId</b>: <%=staffid %></p>
        <p><b>Request Id </b>: <%=reqid %></p>
        <p><b>Request</b>: <%=req %></p>
        <label for="Pending">Pending</label>
        <input type="radio" name="status" id="pending" value="pending" checked>
        <label for="complete">Complete</label>
        <input type="radio" name="status" id="complete" value="completed">
        <label for="reject">Reject</label>
        <input type="radio" name="status" id="reject" value="rejected">
        <button type="submit" class="submit">Submit</button>
     </form>   
    </div>
    </div>
    <script src="jscodes/AdminMenu.js"></script>
    <script src="jscodes/DisapperMessage.js"></script>
</body>
</html>