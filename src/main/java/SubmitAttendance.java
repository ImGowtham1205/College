import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.DaoClass.FetchStudent;
import com.DaoClass.FetchStaff;
import com.DaoClass.PutAttendance;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SubmitAttendance")
public class SubmitAttendance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    PutAttendance pa = new PutAttendance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int sid = (int) session.getAttribute("sid");

        // Read values from form
        String subject = request.getParameter("subject");
        String code = request.getParameter("code");
        String date = request.getParameter("date");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        String time = beginTime.concat("-" + endTime);
        String rollNumberList = request.getParameter("rollNumbers");
        String[] rollNumbers = rollNumberList.split(",");
        int sem = Integer.parseInt(request.getParameter("sem"));
        String s = request.getParameter("sem");
        String year = request.getParameter("year");

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // DB connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "test");
            con.setAutoCommit(false); // start transaction

            // Fetch Staff Name By Calling fetchName Method Using StaffId As Argument
            FetchStaff staff = new FetchStaff();
            String staff_name = staff.fetchName(sid);

            for (String no : rollNumbers) {
                if (no != null && !no.trim().isEmpty()) {
                    int rollno = Integer.parseInt(no.trim());
                    String status = request.getParameter("status" + rollno);

                    // Get PreparedStatement for correct table (same for students of this batch)
                    if (ps == null) {
                        String table = pa.getAttendanceTable(new FetchStudent().fetchDno(rollno), sem);
                        String qry = "INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?,?,?)";
                        ps = con.prepareStatement(qry);
                    }

                    // Fill values for each student
                    String student_name = new FetchStudent().fetchName(rollno);

                    ps.setString(1, date);
                    ps.setString(2, subject);
                    ps.setString(3, code);
                    ps.setInt(4, rollno);
                    ps.setString(5, student_name);
                    ps.setString(6, status);
                    ps.setInt(7, sid);
                    ps.setString(8, staff_name);
                    ps.setString(9, time);

                    ps.addBatch(); // add to batch
                }
            }

            if (ps != null) {
                ps.executeBatch(); // execute all inserts together
            }

            con.commit(); // commit only if all succeed

            request.getRequestDispatcher("PutAttendance.jsp?status=success&subject=" + subject + "&code=" + code + "&sem=" + s + "&year=" + year).forward(request, response);

        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback(); // rollback all if any failure
                } catch (Exception ex) {
                }
            }
            throw new ServletException("Error while saving attendance", e);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } catch (Exception e) {
            }
        }
    }
}
