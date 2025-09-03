<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Student Login</title>
  <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
  <link rel="stylesheet" href="csscodes/StudentLogin.css"/>
</head>
<body>
  <div class="login-container">
  
  <%
    // Prevent browser from going back after logout
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP 1.1
    response.setHeader("pragma","no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxy Server
  %>
  
  <!-- Error Message from Server -->
  <% 
    String error = request.getParameter("error");
    if ("unauthorized".equals(error)) {
  %>
    <div class="error-msg" id="errorMsg">
      Incorrect ID or Password.
    </div>
  <% } %>

	<img src="<%= request.getContextPath() %>/Image/favicon.png" alt="Student Logo" class="page-logo"/>

  <!-- Student Login Form --> 
  <h2>Student Login</h2>
  <form id="loginForm" action="StudentLogin" method="post" novalidate>
    <div class="input-group">
      <label for="id">Student ID</label>
      <input type="number" id="id" name="id" required />
      <span class="error-text"></span>
    </div>
    <div class="input-group">
      <label for="password">Password</label>
      <input type="password" id="password" name="password" required />
      <span class="error-text"></span>
    </div>
    <button type="submit">Login</button>    
  </form>
  </div>

  <script src="jscodes/Login.js"></script>
</body>
</html>
