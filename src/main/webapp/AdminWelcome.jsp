<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.AdminName"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Welcome Page</title>
  <link rel="stylesheet" href="AdminWelcome.css" />
  <style>
    
  </style>
</head>
<body>
  <%! int aid; 
  	  String name="";	
  %>
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
		AdminName an=new AdminName();
		name=an.fetchName(aid);
	%>
   
   <!-- Navigation Bar -->
  <nav>
    <div class="nav-links">
      <a href="StudentUpdate.jsp">Update Student Details</a>
      <a href="StaffUpdate.jsp">Update Staff Details</a>
      <a href="StudentDelete.jsp">Delete Student Record</a>
      <a href="StaffDelete.jsp">Delete Staff Record</a>
    </div>
    <form class="logout-form" action="AdminLogout" method="post">
      <button type="submit">Logout</button>
    </form>
  </nav>

  <div class="admin-container">
    <h1>Welcome, <%=name %></h1>
  </div>		
</body>
</html>
    