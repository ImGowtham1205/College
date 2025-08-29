<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Staff Welcome</title>
  <link rel="stylesheet" href="csscodes/HodWelcome.css" />
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
		
		//Getting Staffs Who Are Handle Subject By Calling get3rdYearStaff(),get2ndYearStaff(),get1stYearStaff() Method
		FetchAssignedStaff fas=new FetchAssignedStaff();
		List<FetchAssignStaff>list3rdyear=fas.get3rdYearStaff(sid);
		List<FetchAssignStaff>list2ndyear=fas.get2ndYearStaff(sid);
		List<FetchAssignStaff>list1styear=fas.get1stYearStaff(sid);
	%>
	
 <!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <h2>Welcome, <%=name %></h2>
  </div>
  <div class="nav-right">
    <a href="ChangeStaffPassword.jsp">Change Password</a>
    <a href="StaffRequest.jsp">Update Personal info</a>
    <a href="UpdateAttendance.jsp">Update Attendance</a>
    <form action="StaffLogout" method="post"><button class="logout-btn">Logout</button></form>
  </div>
</nav>

 <!-- It Checks That Any Subject Assigned For That Particular If Assigend Then Display The Assigned Subject Else It Display "No Subject Assigned" Message-->
	
<div class="container">
  <% if ((list3rdyear == null || list3rdyear.isEmpty()) && (list2ndyear == null || list2ndyear.isEmpty()) && (list1styear == null || list1styear.isEmpty())) { %>
      <h3>No Subjects Assigned For You :</h3>
  <% } else { %>
      <h3>Assigned Subjects For You :</h3>
      
        <% 
        //Displaying 3rd Year Assigned Subjects
        for (FetchAssignStaff sub : list3rdyear) {
            String subject = sub.getSubject();
            String code=sub.getCode();
            String sname=sub.getName();
            int id=sub.getId();
            int year=sub.getYear();
            int sem=sub.getSem();
        %>
       		<div class="subject-card" onclick="putAttendance('<%= subject %>', '<%= code %>' ,'<%= sem %>', '<%= year %>')">
  			<%= subject %><br>
    		Course Code : <%= code %><br>
    		Staff Name : <%= sname %><br>
    		Staff Id : <%= id %><br>
    		Year : <%= year %><br>
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
            int year=sub.getYear();
            int sem=sub.getSem();
        %>  
           <div class="subject-card" onclick="putAttendance('<%= subject %>', '<%= code %>' ,'<%= sem %>' , '<%= year %>')">
  			<%= subject %><br>
    		Course Code : <%= code %><br>
    		Staff Name : <%= sname %><br>
    		Staff Id : <%= id %><br>
    		Year : <%= year %><br>
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
            int year=sub.getYear();
            int sem=sub.getSem();
  			%> 
  			<div class="subject-card" onclick="putAttendance('<%= subject %>', '<%= code %>' ,'<%= sem %>' , '<%= year %>')">
  			<%= subject %><br>
    		Course Code : <%= code %><br>
    		Staff Name : <%= sname %><br>
    		Staff Id : <%= id %><br>
    		Year : <%= year %><br>
    		Semester : <%= sem %>
  			</div>
  			<br>
  			<%} %> 			
  <% }%>
</div>

<!-- This Is Function Is Used For When The Staff Click Their Assigned Subject Name It Will Redirect To Mark Attendance For The Clicked Subject-->
  	 <script>
  function putAttendance(subjectName, code ,sem,year) {
    window.location.href = "PutAttendance.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code) + "&sem=" + encodeURIComponent(sem) + "&year=" + encodeURIComponent(year);
  }
</script> 
  </body>
</html>