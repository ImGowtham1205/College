<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>
    
<%
	//Read The Values from The AssignSubject Function
    String subject = request.getParameter("subject");
    String code = request.getParameter("code"); 
    String staffname=request.getParameter("staffname");
    String staffid=request.getParameter("staffid");
    String sem=request.getParameter("sem");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Assign Subject</title>
<link rel="stylesheet" href="csscodes/UpdateAssignSubject.css" />
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
</head>
<body>
	<%! String name,hodname;%>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	///If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if (session.getAttribute("Hodid") == null) {
		response.sendRedirect("HodLogin.jsp");
		return;
	}
	
	//Read The HOD ID from session 
	String id=session.getAttribute("Hodid").toString();
	int hodid=Integer.parseInt(id);
	
	//Getting Hod Name By Calling fetchName() Method Using HOD ID As Arugment
	FetchStaff fh=new FetchStaff();
	hodname=fh.fetchName(hodid);
	
	//Getting Staff Name For The Particular Department By Calling getStaffName() Method using Student RollNo As Arugment
	FetchStaffName fsn=new FetchStaffName();
	List<StaffName> list=fsn.getStaffName(hodid); 
	%>
	
 <!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <button class="hamburger" onclick="toggleSidebar()">☰</button>
    <span class="welcome-text">Welcome, <%= hodname %></span>
  </div>
  
  <div class="nav-right">
    <form action="HodLogout" method="post">
      <button class="logout-btn">Logout</button>
    </form>
  </div>
</nav>
 
  <div id="sidebar" class="sidebar">
    <a href="javascript:void(0)" class="closebtn" onclick="toggleSidebar()">×</a>
    <a href="HodWelcome.jsp">Home Page</a>
    <a href="ChangeHodPassword.jsp">Change Password</a>
    <a href="HodRequest.jsp">Update Personal Info</a>
    <a href="ViewAssignSubject.jsp">View Assign Subject</a>
    <a href="UpdateSubject.jsp">Update Assigned Subject</a>
    <a href="DeleteSubject.jsp">Delete Assigned Subject</a>
  </div>

<!-- Form For Update The Selected Assigned Subject To The Staff -->
<h2>Update Assign Subject</h2>
<!-- Messages -->
<%
	String status=request.getParameter("status");
	if("match".equals(status)){
%>
		<div class="error-msg">
   			Already Assigned Subject To The Staff...
  		</div>
<%}  else if("success".equals(status)) {%>

	<div class="success-msg">
   			Update Successfully...
  	</div>
<%} %>
<form id="assignSubjectForm" action="UpdateAssignSubject" method="post" novalidate>
<label for="subject">Subject Name:</label>
<input type="text" name="subject" id="subject" value="<%=subject%>" readonly>

<label for="code">Subject Code:</label>
<input type="text" name="code" id="code" value="<%=code%>" readonly>

<label for="sname">Current Staff Name For Subject:</label>
<input type="text" name="sname" id="sname" value="<%=staffname%>" readonly>

<label for="subject">Current Staff Id For Subject:</label>
<input type="text" name="sid" id="sid" value="<%=staffid%>" readonly>

<input type="hidden" name="sem" value="<%= sem%>">

<label for="staff">Update Staff :</label>
      <select name="staffname" id="staff" required>
        <option value="">-- Select Staff --</option>
        <%for(StaffName staff: list) {
        	name=staff.getName();
        	int no=staff.getId();
        %>
        <option value="<%=no%>"><%=name %></option>
        <% }%>
      </select>
      <button type="submit">Update Staff</button>
</form>
<script src="jscodes/HodMenu.js"></script> 
<script src="jscodes/AssignStaffValidation.js"></script>
</body>
</html>