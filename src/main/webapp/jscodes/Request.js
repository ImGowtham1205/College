//This Function Is Use For Redirect To Request Complete Page
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