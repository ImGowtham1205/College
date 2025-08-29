<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request To Admin</title>
<link rel="stylesheet" href="csscodes/StaffRequest.css" />
</head>
<body>
<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    String name = "";

  ///If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if (session.getAttribute("Hodid") == null) {
		response.sendRedirect("HodLogin.jsp");
		return;
	}
	
		//Read The HOD ID from session
		int hodid=(int)session.getAttribute("Hodid");
		FetchStaff fh=new FetchStaff();
		
		//Getting Hod Name By Calling fetchName() Method Using HOD ID As Arugment
		name=fh.fetchName(hodid);
%>

 <!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <h2>Welcome, <%=name %></h2>
  </div>
  <div class="nav-right">
  	<a href="HodWelcome.jsp">Home Page</a>
    <a href="ChangeHodPassword.jsp">Change Password</a>
    <a href="ViewAssignSubject.jsp">View Assign Subject</a>
    <a href="UpdateSubject.jsp">Update Assigned Subject</a>
    <a href="DeleteSubject.jsp">Delete Assigned Subject</a>
    <form action="HodLogout" method="post"><button class="logout-btn">Logout</button></form>
  </div>
</nav>


 <!-- Messages -->
<%
String success = request.getParameter("success");

if ("success".equals(request.getParameter("success"))) { %>
  <div class="success-msg">
     Request Sent Successfully.
  </div>
<% } %>

<!-- Form For Sending Request To Admin -->
<div class="form-wrapper">
    <div class="register-container">
        <h2>Request To Admin</h2>
        <form action="HodRequest" method="post">
            <div class="input-group">
                <label for="request">Request</label>
                <textarea id="request" name="request" rows="5" required></textarea>
            </div>
            <button type="submit">Send</button>
        </form>
    </div>
</div>

</body>
</html>
