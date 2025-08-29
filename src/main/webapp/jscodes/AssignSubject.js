// This Is Function Is Used For When The HOD Click Any Subject Name It Will Redirect To Assign Selected Subject To Staff
function assignSubject(subjectName, code,year,sem) {
    window.location.href = "AssignSubject.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code) + "&year=" + encodeURIComponent(year) + "&sem=" + encodeURIComponent(sem);
}	