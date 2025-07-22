<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Student Registration</title>
  <link rel="stylesheet" href="AddStudent.css" />
</head>
<body>

  <div class="register-container">
   <!-- Messages -->
<%
String success = request.getParameter("success");

if ("true".equals(request.getParameter("success"))) { %>
  <div class="success-msg">
     student Record Added Successfully.
  </div>
<% } %>
  	
  	 <!-- Form For Add The Student Record -->
    <h2>Student Registration</h2>
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
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required />
      </div>  

      <button type="submit">Register</button>
      <p class="login-link">Already have an account? <a href="StudentLogin.jsp">Login here</a></p>
    </form>
  </div>
</body>
</html>
    