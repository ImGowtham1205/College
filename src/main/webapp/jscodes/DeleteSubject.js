// This Is Function Is Used For When The HOD Click Any Subject Name It Will Redirect For To Delete Staff For Selected Subject
function deleteSubject(subjectName, code,sname,sid,sem) {
   window.location.href = "DeleteAssignSubject.jsp?subject=" + encodeURIComponent(subjectName) + "&code=" + encodeURIComponent(code) +"&staffname=" +encodeURIComponent(sname) +"&staffid="+encodeURIComponent(sid)+"&sem="+encodeURIComponent(sem);
 }