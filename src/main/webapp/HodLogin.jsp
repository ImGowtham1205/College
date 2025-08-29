<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Hod Login</title>
  <link rel="stylesheet" href="csscodes/StudentLogin.css"/>
</head>
<body>
  <div class="login-container">
  
  <%
	//If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
  	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
	response.setHeader("pragma","no-cache");//HTTP 1.0
	response.setHeader("Expires", "0");//Proxy Server
  %>
  
  <!-- Messages -->
  <% 
  String error = request.getParameter("error");
  if ("unauthorized".equals(error)) {
  %>
  	<div class="error-msg">
    Incorrect ID Or Password .
  </div>
  <%} %>
  
   <!-- HOD Login Form -->
    <h2>Hod Login</h2>
    <form action="HodLogin" method="post">
      <div class="input-group">
        <label for="hod-id">HOD ID</label>
        <input type="number" id="hod-id" name="hod-id" required />
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