<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.DaoClass.*,java.util.*"
%>

<%
	//Read The Values from The AssignSubject Function
    String subject = request.getParameter("subject");
    String code = request.getParameter("code");
    String year=request.getParameter("year");
    String sem=request.getParameter("sem");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Assign Subject</title>
  <link rel="stylesheet" href="csscodes/AssignSubject.css" />
  <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
</head>
<body>
	<%! String name,hodname;%>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
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
  <h2>Welcome, <%=hodname %></h2>
</div>
  

  <div class="nav-right">
    <!-- Hamburger menu button -->
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
  
<!-- Messages -->
  <div class="container">
  <% String status = request.getParameter("status");
  if("success".equals(status)){
  %>
  	<div class="success-msg">
   		<%=subject %> Assigned Successfully...
  </div>
  <%} 
  else if("exists".equals(status)) {
  %>
  	<div class="error-msg">
   		<%=subject %> Already Assigned...
  </div>
 <%} 
  else if("assign".equals(status)){
 %>
 	<div class="error-msg">
   		 Already Assigned Subject To The Staff...
  </div>
 <%} %>
 	
 <!-- Form For Assign The Selected Subject To The Staff -->
    <h2>Assign Subject: <span><%= subject %></span></h2>

    <form id="assignSubjectForm" action="AssignSubject" method="post" novalidate>
      <!-- Hidden field to pass subject -->
      <input type="hidden" name="subject" value="<%= subject %>"/>
      <input type="hidden" name="code" value="<%= code %>"/>
	  <input type="hidden" name="year" value="<%= year %>"/>	
	  <input type="hidden" name="sem" value="<%= sem %>"/>
	 	
      <label for="staff">Select Staff Member:</label>
      <select name="staffname" id="staff" required>
        <option value="">-- Select Staff --</option>
        <%for(StaffName staff: list) {
        	name=staff.getName();
        	int no=staff.getId();
        %>
        <option value="<%=no%>"><%=name %></option>
        <% }%>
      </select>

      <button type="submit">Assign Subject</button>
    </form>
  </div>
<script src="jscodes/HodMenu.js"></script> 
<script src="jscodes/AssignStaffValidation.js"></script>
</body>
</html>
