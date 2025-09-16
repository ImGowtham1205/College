<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*, java.util.*, java.text.SimpleDateFormat, java.text.DateFormat" %>
    
<%
    // Read The Values from The putAttedance Function
    String subject = request.getParameter("subject");
    String code = request.getParameter("code");
    String sem=request.getParameter("sem");
    String year=request.getParameter("year");
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Put Attendance</title>
<link rel="stylesheet" href="csscodes/PutAttendance.css" />
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
 <script src="jscodes/SelectAllAttendance.js"></script> 
</head>
<body>

<%! String name; %>
<%!
  // Method To Convert Date To Sql Date
  private static java.sql.Date convert(java.util.Date udate){
    java.sql.Date sdate=new java.sql.Date(udate.getTime());
    return sdate;
  }
%>
<%
  //If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
  response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setHeader("Expires", "0");

  // If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
  if (session.getAttribute("sid") == null) {
    response.sendRedirect("StaffLogin.jsp");
    return;
  }

  // Read The Staff ID from session 
  int sid=(int)session.getAttribute("sid");
  
 // Getting Staff Name By Calling fetchName() Method Using Staff ID As Arugment
  FetchStaff fs=new FetchStaff();
  name=fs.fetchName(sid);

  // Declaring Util.Date & Convert To Sql Date & Assign The Sql Date In formattedDate Variable
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
    <a href="StaffRequest.jsp">Update Personal info</a>
    <a href="UpdateAttendance.jsp">Update Attendance</a>
  </div>

<div class="attendance-form">
<!-- Messages-->
 <%
    String time = request.getParameter("time");
    String statusMsg = request.getParameter("status");

    if ("invalid".equals(time)) {
%>
        <div class="error-msg" id="serverMsg">Invalid Time Duration For Mark Attendance...</div>
<%
    } else if ("taken".equals(time)) {
%>
        <div class="error-msg" id="serverMsg">For This Hour Attendance Already Marked...</div>
<%
    } else if ("mark".equals(time)) {
%>
        <div class="error-msg" id="serverMsg">For This Hour You Already Mark Attendance In Another Class...</div>
<%
    }
    if ("success".equals(statusMsg)) {
%>
        <div class="success-msg" id="serverMsg">Attendance Entered Successfully ...</div>
<%
    }
%>
 
<!-- Time Selection Form -->
  <form method="post" action="CheckTime" id="timeForm" novalidate>
    <!-- Hidden Form To Access The Values In CheckTime Servlet -->
    <input type="hidden" name="subject" value="<%= subject%>">
    <input type="hidden" name="code" value="<%= code%>">
    <input type="hidden" name="date" value="<%= formattedDate %>">
    <input type="hidden" name="sem" value="<%= sem %>">
    <input type="hidden" name="year" value="<%= year %>">

    <div class="form-row">
  <div class="form-group">
    <label for="date">Date:</label>
    <select name="date" id="date">
      <option value="">Select Date</option>
      <option value="<%=formattedDate%>"><%=formattedDate%></option>
    </select>
      <div class="field-error" aria-live="polite"></div>
  </div>

  <div class="form-group">
    <label for="beginTime">Begin Time:</label>
    <select name="beginTime" id="beginTime">
      <option value="">Begin Time</option>
      <option value="08:30">08:30 AM</option>
      <option value="09:15">09:15 AM</option>
      <option value="10:15">10:15 AM</option>
      <option value="11:00">11:00 AM</option>
      <option value="11:45">11:45 AM</option>
    </select>
      <div class="field-error" aria-live="polite"></div>
  </div>

  <div class="form-group">
    <label for="endTime">End Time:</label>
    <select name="endTime" id="endTime">
      <option value="">End Time</option>
      <option value="09:15">09:15 AM</option>
      <option value="10:00">10:00 AM</option>
      <option value="11:00">11:00 AM</option>
      <option value="11:45">11:45 AM</option>
      <option value="12:30">12:30 PM</option>
    </select>
     <div class="field-error" aria-live="polite"></div>
  </div>

  <div class="form-group button-group">
    <button type="submit" class="fetch-btn" name="fetch">Fetch</button>
  </div>
</div>
  </form>

  <!-- Attendance Form -->
  <%
    if ("yes".equals(request.getAttribute("fetch")) && time == null) {
      String beginTime = request.getParameter("beginTime");
      String endTime = request.getParameter("endTime");
      String selectedDate = request.getParameter("date");
  %>

  <form action="SubmitAttendance" method="post">
    <!-- Hidden for SubmitAttendance -->
    <input type="hidden" name="subject" value="<%= subject%>">
    <input type="hidden" name="code" value="<%= code%>">
    <input type="hidden" name="date" value="<%= selectedDate %>">
    <input type="hidden" name="beginTime" value="<%= beginTime %>">
    <input type="hidden" name="endTime" value="<%= endTime %>">
    <input type="hidden" name="sem" value="<%= sem %>">
    <input type="hidden" name="year" value="<%= year %>">

    <%
   // To Stores Student Rollno In rollNumbers Hidden form To Access The Values In SubmitAttendance Servlet
      StringBuilder rollBuilder = new StringBuilder();
      for (int i = 0; i < list.size(); i++) {
        rollBuilder.append(list.get(i).getRollno());
        if (i < list.size() - 1) rollBuilder.append(",");
      }
    %>
    <input type="hidden" id="rollNumbers" name="rollNumbers" value="<%= rollBuilder.toString() %>">

    <h2 class="dis">Student Records</h2>

    <table>
      <thead>
        <tr>
          <th>Roll No</th>
          <th>Student Name</th>
          <th>
            Status <br>
            <!-- Select-all controls -->
            <input type="radio" name="selectAll" value="Present" onclick="setAll('Present')"> Present
            <input type="radio" name="selectAll" value="Absent" onclick="setAll('Absent')"> Absent
          </th>
        </tr>
      </thead>
      <tbody>
      <%
        for (SetStudent s: list) {
          int id = s.getRollno();
          String sname = s.getName();
      %>
        <tr>
          <td><%= id %></td>
          <td><%= sname %></td>
          <td>
            <input type="radio" name="status<%= id %>" value="Present" required> Present
            <input type="radio" name="status<%= id %>" value="Absent"> Absent
          </td>
        </tr>
      <% } %>
      </tbody>
    </table>

    <div style="text-align: right; margin-top: 15px;">
      <button type="submit" class="fetch-btn">Submit Attendance</button>
    </div>
  </form>
  <% } %>

</div>
 <script src="jscodes/HodMenu.js"></script>
 <script src="jscodes/AttendanceValidation.js"></script>
</body>
</html>
