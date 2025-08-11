<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Staff Welcome</title>
  <link rel="stylesheet" href="HodWelcome.css" />
</head>
<body>
	<%! String name=" ",code,subject; %>
	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	//If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
	if (session.getAttribute("sid") == null) {
		response.sendRedirect("StaffLogin.jsp");
		return;
	}
	
		//Read The Staff ID from session 
		int sid=(int)session.getAttribute("sid");
		
		//Getting Staff Name By Calling fetchName() Method Using Staff ID As Arugment
		FetchStaff fs=new FetchStaff();
		name=fs.fetchName(sid);
		
		//Getting Assigned Subject For particular Staff By Calling getAssignedSubject() Method using Staff ID As Arugment
		FetchAssignSubject fas=new FetchAssignSubject();
		List<FetchSubject>list=fas.getAssignedSubject(sid);
	%>
	
 <!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <h2>Welcome, <%=name %></h2>
  </div>
  <div class="nav-right">
    <a href="ChangeStaffPassword.jsp">Change Password</a>
    <a href="StaffRequest.jsp">Update Personal info</a>
    <form action="StaffLogout" method="post"><button class="logout-btn">Logout</button></form>
  </div>
</nav>

 <!-- It Checks That Any Subject Assigned For That Particular If Assigend Then Display The Assigned Subject Else It Display "No Subject Assigned" Message-->
	<div class="container">
  <% if (list == null || list.isEmpty()) { %>
      <h3>No Subject Assigned</h3>
  <% } else { %>
      <h3>Assigned Subjects For You:</h3>
      
        <% for (FetchSubject sub : list) {
             subject = sub.getSubject();
             code=sub.getCode();
        %>
       		<div class="subject-card" onclick="putAttendance('<%= subject %>', '<%= code %>')">
  			<%= subject %><br>
    		Course Code : <%= code %>	
  			</div>
        <%   
           }
        %>      
  <% } %>
</div>

<!-- This Is Function Is Used For When The Staff Click Their Assigned Subject Name It Will Redirect To Mark Attendance For The Clicked Subject-->
  	 <script>
  function putAttendance(subjectName, code) {
    window.location.href = "PutAttendance.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code);
  }
</script> 
  </body>
</html>