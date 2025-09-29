<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/Image/favicon.png">
<title>RKMVC PORTA</title>

<!-- Link external CSS -->
<link rel="stylesheet" href="csscodes/Index.css">
</head>
<body>
    <header>
        <h1>RKMVC COLLEGE PORTAL</h1>
    </header>

    <div class="container">
        <div class="card student">
            <img src="<%= request.getContextPath() %>/Image/favicon.png" alt="Student Logo" />
            <a href="StudentLogin.jsp">Student Login</a>
        </div>
        <div class="card staff">
            <img src="<%= request.getContextPath() %>/Image/favicon.png" alt="Staff Logo" />
            <a href="StaffLogin.jsp">Staff Login</a>
        </div>
        <div class="card hod">
            <img src="<%= request.getContextPath() %>/Image/favicon.png" alt="HOD Logo" />
            <a href="HodLogin.jsp">HOD Login</a>
        </div>
        <div class="card admin">
            <img src="<%= request.getContextPath() %>/Image/favicon.png" alt="Admin Logo" />
            <a href="AdminLogin.jsp">Admin Login</a>
        </div>
    </div>

    <footer>
        &copy; 2025 RKMVC College Portal | All Rights Reserved
    </footer>
</body>
</html>
