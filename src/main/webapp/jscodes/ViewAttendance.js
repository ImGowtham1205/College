//This Is Function Is Used For When The Student Click Any Subject Name It Will Redirect To See Their Attendance Details For The Clicked Subject
function viewAttendance(subjectName, code) {
    window.location.href = "ViewPercentage.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code);
  }