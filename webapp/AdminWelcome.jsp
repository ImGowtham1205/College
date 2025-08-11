<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.DaoClass.*,java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Welcome Page</title>
  <link rel="stylesheet" href="AdminWelcome.css" />
  <style>
    
  </style>
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
		
		 //Getting Admin Name By Calling fetchName() Method Using Admin ID As Arugment
		AdminName an=new AdminName();
		name=an.fetchName(aid);
		
		//Getting Student Request By Calling getStudentRequest() Method
		FetchRequest fr=new FetchRequest();
		List<StudentRequestForAdmin> studentlist=fr.getStudentRequest(aid);
		
		//Getting Student Request By Calling getStudentRequest() Method
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
    <h1>Welcome, <%=name %></h1>
  </div>	
  
  <!-- It Checks That Any Subject Assigned For That Particular If Assigend Then Display The Assigned Subject Else It Display "No Subject Assigned" Message-->
	<div class="container">
  <% if ((studentlist == null || studentlist.isEmpty()) && (stafflist == null || stafflist.isEmpty())) { %>
      <h3>No Request Availabe At This Time...</h3>
  <% } else { %>
      <h3>Assigned Request For You:</h3>
      
        <% 
      //Displaying Student Request 
        for (StudentRequestForAdmin student: studentlist) {
             int reqid=student.getReqid();
             int rollno=student.getRollno();
             String sname=student.getName();
             String req=student.getRequest();
        %>
       		<div class="subject-card" onclick="updateStudentRequest('<%= reqid %>', '<%= rollno %>' , '<%= sname %>'  ,'<%= req %>')">
  			Request Id :<%=  reqid%><br>
    		Roll No : <%=  rollno%>	<br>
    		Student Name :<%= sname %><br>
    		Request : <%= req %><br>
  			</div>
        <%   
           }
      	
      //Displaying Staff & Hod Request 
        	for(StaffRequestForAdmin staff: stafflist){
        	int sreqid=staff.getReqid();
        	int staffid=staff.getStaffId();
        	String name=staff.getName();
        	String sreq=staff.getRequest();
        %>   
        <div class="subject-card" onclick="updateStaffRequest('<%= sreqid %>', '<%= staffid %>' , '<%= name %>'  ,'<%= sreq %>')">
  			Request Id :<%=  sreqid%><br>
    		Staff Id : <%=  staffid%>	<br>
    		Staff Name :<%= name %><br>
    		Request : <%= sreq %><br>
  			</div>
        <%} %>   
  <% } %>
</div>	

<!-- This Function Is Use For Redirect To UpdateStudentRequest.jsp Page-->
<script>
    function updateStudentRequest(reqid, rollno,name,req) {
        window.location.href = "UpdateStudentRequest.jsp?reqid=" + encodeURIComponent(reqid) + "&rollno=" + encodeURIComponent(rollno) + "&name=" + encodeURIComponent(name) + "&request=" + encodeURIComponent(req);
    }
    
    <!-- This Function Is Use For Redirect To UpdateStaffRequest.jsp Page-->
    function updateStaffRequest(reqid, staffid,name,req) {
        window.location.href = "UpdateStaffRequest.jsp?reqid=" + encodeURIComponent(reqid) + "&staffid=" + encodeURIComponent(staffid) + "&name=" + encodeURIComponent(name) + "&request=" + encodeURIComponent(req);
    }
</script>

</body>
</html>
    