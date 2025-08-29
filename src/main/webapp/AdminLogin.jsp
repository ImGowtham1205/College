<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Login</title>
  <link rel="stylesheet" href="csscodes/StudentLogin.css" />
</head>
<body>

	<%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
	response.setHeader("pragma","no-cache");//HTTP 1.0
	response.setHeader("Expires", "0");//Proxy Server
	%>
  <div class="login-container">
  
  <!-- Messages -->
  <% 
  String error = request.getParameter("error");
  if ("unauthorized".equals(error)) {
  %>
  	<div class="error-msg">
    Incorrect ID Or Password .
  </div>
  <%} %>
  
  <!-- Admin Login Form -->
    <h2>Admin Login</h2>
    <form action="AdminLogin" method="post">
      <div class="input-group">
        <label for="Admin-id">Admin ID</label>
        <input type="number" id="Admin-id" name="Admin-id" required />
      </div>
      <div class="input-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
  </div>
</body>
</html>    