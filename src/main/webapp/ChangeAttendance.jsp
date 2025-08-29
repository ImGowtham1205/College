<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*, java.util.*, java.text.SimpleDateFormat, java.text.DateFormat" %>
    
 <%
//Read The Values from The putAttedance Function
 	String subject = request.getParameter("subject");
 	String code = request.getParameter("code");
 	String year= request.getParameter("year");
 %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Attendance</title>
<link rel="stylesheet" href="csscodes/ChangeAttendance.css" />
</head>
<body>

	<%! String name;%>
	<%!
		//Method For To Convert Date To Sql Date
		private static java.sql.Date convert(java.util.Date udate){
		java.sql.Date sdate=new java.sql.Date(udate.getTime());
		return sdate;
	}
	%>
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
		
		//Declaring Util.Date & Convert To Sql Date & Assign The Sql Date In formattedDate Variable
		java.util.Date date = new java.util.Date();
	    java.sql.Date sdate = convert(date);
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    String formattedDate = df.format(date);
	    
	    int y=Integer.parseInt(year);
	    
	    // Getting Students Details For Mark Attendance To The Selected Subject By Calling fetchStudent() Method
	    FetchStudentRecord fsr=new FetchStudentRecord();
	    List<SetStudent> list=fsr.fetchStudent(sid,y);
	%>

 <!-- Navigation Bar-->
<nav class="navbar">
  <div class="nav-left">
    <h2>Welcome, <%=name %></h2>
  </div>
  <div class="nav-right">
    <a href="StaffWelcome.jsp">Home Page</a>
    <a href="ChangeStaffPassword.jsp">Change Password</a>
    <a href="StaffRequest.jsp">Update Personal info</a>
    <a href="UpdateAttendance.jsp">Update Attendance</a>
    <form action="StaffLogout" method="post"><button class="logout-btn">Logout</button></form>
  </div>
</nav>

<div class="attendance-form">

<!-- Messages -->
<% String time = request.getParameter("time");
	if("invalid".equals(time)){
%>
	<div class="error-msg">
   		Invalid Time Duration For Update Attendance...
  </div>
<%} 
	else if("not".equals(time)){
%>
	<div class="error-msg">
   		For Selected Time You Didn't Marked Attendance...
  </div>
<%} %>
<%
	String status=request.getParameter("status");
	if("success".equals(status)){
%>
	<div class="success-msg">
   		Attendance Update Successfully ...
  </div>
<%} %>

<!-- Form For Update Attendance To Selected Student -->
  <form  method="post" action="ChangeAttendance">   	
    <div class="form-row">
    	<h3>Update Attendance</h3>
      <label for="date">Date:</label>
      <select name="date" id="date" required>
        <option value="">Select Date</option>
        <option value="<%=formattedDate%>"><%=formattedDate%></option>
      </select>

      <label for="beginTime">Begin Time:</label>
      <select name="beginTime" id="beginTime" required>
        <option value="">Begin Time</option>
        <option value="08:30">08:30 AM</option>
        <option value="09:15">09:15 AM</option>
        <option value="10:15">10:15 AM</option>
        <option value="11:00">11:00 AM</option>
        <option value="11:45">11:45 AM</option>
      </select>

      <label for="endTime">End Time:</label>
      <select name="endTime" id="endTime" required>
        <option value="">End Time</option>
        <option value="09:15">09:15 AM</option>
        <option value="10:00">10:00 AM</option>
        <option value="11:00">11:00 AM</option>
        <option value="11:45">11:45 AM</option>
        <option value="12:30">12:30 PM</option>
      </select>

	<label for="subject">Subject Name:</label>
	<input type="text" name="subject" id="subject" value="<%=subject%>" readonly>

	<label for="code">Subject Code:</label>
	<input type="text" name="code" id="code" value="<%=code%>" readonly>	

	<label for="student">Select Student :</label>
      <select name="studentname" id="student" required>
        <option value="">-- Select Student --</option>
        <%for(SetStudent s: list) {
        	int no=s.getRollno();
        %>
        <option value="<%=no%>"><%=no %></option>
        <% }%>
      </select>

		<label for="attendance">Attendance :</label>
	<div class="radio-group">
  		<label class="radio-option">
    	<input type="radio" name="attendance" value="Present"> Present
  	</label>
 		 <label class="radio-option">
    	<input type="radio" name="attendance" value="Absent"> Absent
  </label>
 	</div>	
 	
 	<input type="hidden" name="year" value="<%= year %>">
 	
      <button type="submit" class="fetch-btn" name="fetch">Update Attendance</button>
    </div>
  </form>   
</div>

</body>
</html>