<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request To Admin</title>
<link rel="stylesheet" href="csscodes/StaffRequest.css" />
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
</head>
<body>
<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    String name = "";

  //If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
  	if (session.getAttribute("sid") == null) {
  		response.sendRedirect("StaffLogin.jsp");
  		return;
  	}

  //Read The Staff ID from session 
  		int sid=(int)session.getAttribute("sid");
    
  	//Getting Staff Name By Calling fetchName() Method Using Staff ID As Arugment
    FetchStaff sn = new FetchStaff();
    name = sn.fetchName(sid); 
    
    //Getting Staff By Calling getIndividualStaffRequest()Method
    FetchRequest fr=new FetchRequest();
    List<StaffRequestForAdmin> list=fr.getIndividualStaffRequest(sid);
%>

<!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
  <button class="hamburger" onclick="toggleSidebar()">☰</button>
  <h2>Welcome, <%=name %></h2>
</div>
  
  <div class="nav-right">
    <!-- Hamburger menu button -->
    <form action="StaffLogout" method="post">
      <button class="logout-btn">Logout</button>
    </form>
  </div>
</nav>

  <div id="sidebar" class="sidebar">
  	<a href="javascript:void(0)" class="closebtn" onclick="toggleSidebar()">×</a>
   	<a href="StaffWelcome.jsp">Home Page</a>
    <a href="ChangeStaffPassword.jsp">Change Password</a>
    <a href="UpdateAttendance.jsp">Update Attendance</a>
  </div>


 <!-- Messages -->
<%
String success = request.getParameter("success");

if ("success".equals(request.getParameter("success"))) { %>
  <div class="success-msg" id="successMsg">
     Request Sent Successfully.
  </div>
<% } %>

<!-- Form For Sending Request To Admin -->
<div class="form-wrapper">
    <div class="register-container">
        <h2>Request To Admin</h2>
        <form action="StaffRequest" method="post" onsubmit="return validateRequest()">
            <div class="input-group">
                <label for="request">Request</label>
                <textarea id="request" name="request" rows="5"></textarea>
                <small id="charCount" class="char-count">0 / 500 characters</small>
        		<small id="errorMsg" class="error-msg"></small>
            </div>
            <button type="submit">Send</button>
        </form>
    </div>
</div>
<% if((list==null ||list.isEmpty())) {%>
	<h3>You Didn't Send Any Requst To Admin</h3>
<%} 
else { %>
	<h3>Your Request : </h3>
	<table>
        <thead>
            <tr>
                <th>Sno</th>
                <th>Request</th>
                <th>Progress</th>
            </tr>
        </thead>
        <tbody>
        <%  int i=0;
        	for(StaffRequestForAdmin s:list) { 
            String req=s.getRequest();
            String progress=s.getStatus();
            i++;
        %>
            <tr>
                <td data-label="Sno"><%=i %></td>
                <td data-label="Request"><%=req %></td>
                <td data-label="Progress"><%=progress %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
<%} %>
<script src="jscodes/HodMenu.js"></script>
<script src="jscodes/Task.js"></script>
</body>
</html>
