<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Student Registration</title>
  <link rel="stylesheet" href="csscodes/AddStudent.css" />
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
  %>
  
   <!-- Navigation Bar -->
  <nav>
    <div class="nav-links">
      <a href="AdminWelcome.jsp">Admin Welcome Page</a>
      <a href="StudentUpdate.jsp">Update Student Details</a>
      <a href="StaffUpdate.jsp">Update Staff Details</a>
      <a href="StudentDelete.jsp">Delete Student Record</a>
      <a href="StaffDelete.jsp">Delete Staff Record</a>
      <a href="ChangeAdminPassword.jsp">Change Password</a>
    </div>
    <form class="logout-form" action="AdminLogout" method="post">
      <button type="submit">Logout</button>
    </form>
  </nav>  
  
  <div class="register-container">
   <!-- Messages -->
<%
String success = request.getParameter("success");

if ("true".equals(success)) { %>
  <div class="success-msg">
     student Record Added Successfully.
  </div>
<% }  else if("unauthorized".equals(success)) {%>
  	 <div class="error-msg">
      You are not authorized to Add student Record for the selected department.
  </div>
<%}   else if("false".equals(success)) {%>
	<div class="error-msg">
      Select Valid Semester For Add Student Record.
  </div>
<%} %>
  	 <!-- Form For Add The Student Record -->
    <h2>Add Student Record</h2>
    <form action="AddStudent" method="post">
      <div class="input-group">
        <label for="fullname">Full Name</label>
        <input type="text" id="fullname" name="fullname" required />
      </div>

      <div class="input-group">
        <label>Gender</label>
        <div class="radio-group">
          <label><input type="radio" name="gender" value="Male" required /> Male</label>
          <label><input type="radio" name="gender" value="Female" /> Female</label>
        </div>
      </div>

      <div class="input-group">
        <label for="department">Department</label>
        <select id="department" name="department" required>
          <option value="" disabled selected>Select Department</option>
          <option value="B.com(ISM)">B.com(ISM)</option>
          <option value="B.com(General)">B.com(General)</option>
          <option value="B.com(Coporate Secretaryship)">B.com(Coporate Secretaryship)</option>
          <option value="B.com(A&F)">B.com(A And F)</option>
          <option value="B.com(Bank Management)">B.com(Bank Management)</option>
          <option value="B.com(CA)">B.com(CA)</option>
          <option value="BBA">BBA</option>
          <option value="B.Sc(Computer Science)">B.Sc(Computer Science)</option>
          <option value="BCA">BCA</option>
        </select>
      </div>

      <div class="input-group">
        <label for="blood-group">Blood Group</label>
        <select id="blood-group" name="blood-group" required>
          <option value="" disabled selected>Select Blood Group</option>
          <option value="A+ve">A+ve</option>
          <option value="A-ve">A-ve</option>
          <option value="B+ve">B+ve</option>
          <option value="B-ve">B-ve</option>
          <option value="O+ve">O+ve</option>
          <option value="O-ve">O-ve</option>
          <option value="AB+ve">AB+ve</option>
          <option value="AB-ve">AB-ve</option>
        </select>
      </div>

      <div class="input-group">
        <label for="phone">Phone Number</label>
        <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required placeholder="10-digit number" />
      </div>

      <div class="input-group">
        <label for="address">Address</label>
        <textarea id="address" name="address" rows="3" required></textarea>
      </div>

      <div class="input-group">
        <label for="email">Email Address</label>
        <input type="email" id="email" name="email" required />
      </div>

	<div class="input-group">
        <label for="year">Year</label>
        <select id="year" name="year" required>
          <option value="" disabled selected>Select Year</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">2</option>
        </select>
      </div>
	
	<div class="input-group">
        <label for="sem">sem</label>
        <select id="sem" name="sem" required>
          <option value="" disabled selected>Select Semester</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">2</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
        </select>
      </div>

      <button type="submit">Add Record</button>
    </form>
  </div>
</body>
</html>
    