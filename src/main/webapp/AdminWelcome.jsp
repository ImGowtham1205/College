<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Welcome Page</title>
  <link rel="stylesheet" href="AdminWelcome.css" />
</head>
<body>
  <%! int aid; 
      String aname="";    
  %>
   <%
 //If The User Logout Their Session After Click The Backwards Button It Will Not Again Redirect To The Previous Web Page Activity
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("pragma","no-cache");
    response.setHeader("Expires", "0");

  //If The User Enter Their Login credentials In The Login Form Then Only The User Are Allow To Access This Page
    if(session.getAttribute("Adminid")==null){
        response.sendRedirect("AdminLogin.jsp");
        return;
    }

	//Read The Admin Id  from session
    String id=session.getAttribute("Adminid").toString();
    aid=Integer.parseInt(id);

    //Getting Admin Name By Calling fetchName() Method Using Admin ID As Arugment
    FetchStaff fs=new FetchStaff();
    aname=fs.fetchName(aid);

    ////Getting Student & Staff  Request By Calling getStudentRequest() getStaffRequest() & Method
    FetchRequest fr=new FetchRequest();
    List<StudentRequestForAdmin> studentlist=fr.getStudentRequest(aid);
    List<StaffRequestForAdmin> stafflist=fr.getStaffRequest(aid);
   %>
   
   <!-- Navigation Bar -->
  <nav>
    <div class="nav-links">
      <a href="AddStudent.jsp">Add Student Details</a>    
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

  <div class="admin-container">
    <h1>Welcome, <%= aname %></h1>
  </div>    
  
 <!-- It Checks That Any Request Assigned For Admin If Assigend Then Display The Assigned Request Else It Display "No Request Assigned" Message-->
  <div class="container">
    <% if ((studentlist == null || studentlist.isEmpty()) && (stafflist == null || stafflist.isEmpty())) { %>
        <h3>No Request Available At This Time...</h3>
    <% } else { %>
        <h3>Assigned Request For You:</h3>
        <% 
        //Student Requests
        for (StudentRequestForAdmin student: studentlist) {
        %>
            <div class="subject-card" data-type="student" data-reqid="<%= student.getReqid() %>" data-rollno="<%= student.getRollno() %>" 
            data-name="<%= student.getName().replace("\"", "&quot;").replace("'", "\\'") %>" 
            data-request="<%= student.getRequest().replace("\"", "&quot;").replace("'", "\\'") %>"
            onclick="handleRequestClick(this)">
            
                Request Id: <%= student.getReqid() %><br>
                Roll No: <%= student.getRollno() %><br>
                Student Name: <%= student.getName() %><br>
                Request: <%= student.getRequest() %><br>
            </div>
        <% } %>

        <% 
        // Staff Requests
        for (StaffRequestForAdmin staff: stafflist) {
        %>
            <div class="subject-card"  data-type="staff" data-reqid="<%= staff.getReqid() %>" data-staffid="<%= staff.getStaffId() %>"
                 data-name="<%= staff.getName().replace("\"", "&quot;").replace("'", "\\'") %>"
                 data-request="<%= staff.getRequest().replace("\"", "&quot;").replace("'", "\\'") %>"
                 onclick="handleRequestClick(this)">
                 
                Request Id: <%= staff.getReqid() %><br>
                Staff Id: <%= staff.getStaffId() %><br>
                Staff Name: <%= staff.getName() %><br>
                Request: <%= staff.getRequest() %><br>
            </div>
        <% } %>   
    <% } %>
  </div>    

<!-- This Function Is Use For Redirect To Request Complete Page-->
<script>
function handleRequestClick(el) {
    const type = el.dataset.type;
    const reqid = el.dataset.reqid;
    const name = el.dataset.name;
    const request = el.dataset.request;

    if (type === "student") {
        const rollno = el.dataset.rollno;
        window.location.href = "UpdateStudentRequest.jsp?reqid=" + encodeURIComponent(reqid) + "&rollno=" + encodeURIComponent(rollno) + "&name=" + encodeURIComponent(name) +"&request=" + encodeURIComponent(request);
    } 
    else if (type === "staff") {
        const staffid = el.dataset.staffid;
        window.location.href = "UpdateStaffRequest.jsp?reqid=" + encodeURIComponent(reqid) +"&staffid=" + encodeURIComponent(staffid) + "&name=" + encodeURIComponent(name) +"&request=" + encodeURIComponent(request);
    }
}
</script>

</body>
</html>