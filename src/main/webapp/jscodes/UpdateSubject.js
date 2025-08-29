// This Is Function Is Used For When The HOD Click Any Subject Name It Will Redirect For To Change Staff For Selected Subject
function updateSubject(subjectName, code,sname,sid,sem) {
    window.location.href = "UpdateAssignSubject.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code) +"&staffname=" +encodeURIComponent(sname) +"&staffid="+encodeURIComponent(sid) +"&sem="+encodeURIComponent(sem);
  }