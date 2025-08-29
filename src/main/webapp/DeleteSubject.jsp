<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Delete Assign Subject</title>
  <link rel="stylesheet" href="csscodes/HodWelcome.css" />
</head>
<body>
	<%! String name=" "; %>
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
		int hodid=(int)session.getAttribute("Hodid");
		FetchStaff fh=new FetchStaff();
		
		//Getting Hod Name By Calling fetchName() Method Using HOD ID As Arugment
		name=fh.fetchName(hodid);
		
		//Getting Staffs Who Are Handle Subject By Calling get3rdYearStaff(),get2ndYearStaff(),get1stYearStaff() Method
		FetchAssignedStaff fas=new FetchAssignedStaff();
		List<FetchAssignStaff>list3rdyear=fas.get3rdYearStaff(hodid);
		List<FetchAssignStaff>list2ndyear=fas.get2ndYearStaff(hodid);
		List<FetchAssignStaff>list1styear=fas.get1stYearStaff(hodid);
%>

 <!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <h2>Welcome, <%=name %></h2>
  </div>
  <div class="nav-right">
  	<a href="HodWelcome.jsp">Home Page</a>
    <a href="ChangeHodPassword.jsp">Change Password</a>
    <a href="HodRequest.jsp">Update Personal Info</a>
    <a href="ViewAssignSubject.jsp">View Assign Subject</a>
    <a href="UpdateSubject.jsp">Update Assigned Subject</a>
    <form action="HodLogout" method="post"><button class="logout-btn">Logout</button></form>
  </div>
</nav>
  
  <div class="container">
  <% if ((list3rdyear == null || list3rdyear.isEmpty()) && (list2ndyear == null || list2ndyear.isEmpty()) && (list1styear == null || list1styear.isEmpty())) { %>
      <h3>You Didn't Assign Subjects To Any Staff</h3>
  <% } else { %>
      <h3>Click Subject Name To Delete Assign Subject To Selected Staff:</h3>
      
        <% 
        //Displaying 3rd Year Assigned Subjects
        for (FetchAssignStaff sub : list3rdyear) {
            String subject = sub.getSubject();
            String code=sub.getCode();
            String sname=sub.getName();
            int id=sub.getId();
            int sem=sub.getSem();
        %>
       		<div class="subject-card" onclick="deleteSubject('<%= subject %>', '<%= code %>' ,'<%= sname %>', '<%= id %>' ,'<%= sem %>')">
  			<%= subject %><br>
    		Course Code : <%= code %><br>
    		Staff Name : <%= sname %><br>
    		Staff Id : <%= id %><br>
    		Semester : <%= sem %>
  			</div>
  			<br>
        <%   
           }
        
        	//Displaying 2nd Year Assigned Subjects
        	for (FetchAssignStaff sub : list2ndyear) {
            String subject = sub.getSubject();
            String code=sub.getCode();
            String sname=sub.getName();
            int id=sub.getId();
            int sem=sub.getSem();
        %>  
           <div class="subject-card" onclick="deleteSubject('<%= subject %>', '<%= code %>' ,'<%= sname %>', '<%= id %>' ,'<%= sem %>')">
  			<%= subject %><br>
    		Course Code : <%= code %><br>
    		Staff Name : <%= sname %><br>
    		Staff Id : <%= id %><br>
    		Semester : <%= sem %>
  			</div>
  			<br>
  			<%} 
        	//Displaying 1st Year Assigned Subjects
        	for (FetchAssignStaff sub : list1styear) {
            String subject = sub.getSubject();
            String code=sub.getCode();
            String sname=sub.getName();
            int id=sub.getId();
            int sem=sub.getSem();
  			%> 
  			<div class="subject-card" onclick="deleteSubject('<%= subject %>', '<%= code %>' ,'<%= sname %>', '<%= id %>' ,'<%= sem %>')">
  			<%= subject %><br>
    		Course Code : <%= code %><br>
    		Staff Name : <%= sname %><br>
    		Staff Id : <%= id %><br>
    		Semester : <%= sem %>
  			</div>
  			<br>
  			<%} %> 			
  <% }%>
</div>
  <script src="jscodes/DeleteSubject.js"></script> 	
  </body>
</html>