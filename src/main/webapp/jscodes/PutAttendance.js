// This Is Function Is Used For When The Staff Click Their Assigned Subject Name It Will Redirect To Mark Attendance For The Clicked Subject
function putAttendance(subjectName, code ,sem,year) {
    window.location.href = "PutAttendance.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code) + "&sem=" + encodeURIComponent(sem) + "&year=" + encodeURIComponent(year);
  }