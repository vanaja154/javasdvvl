package babuphotoshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        String event = request.getParameter("event");
        String message = request.getParameter("message");

        // JDBC setup
        String url = "jdbc:mysql://localhost:3306/babustudios";
        String username = "root";
        String password = "root";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            con = DriverManager.getConnection(url, username, password);

            // SQL query to insert data into the database
            String query = "INSERT INTO schedules (name, mobile, email, event_date, event, message) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);

            // Set parameters
            pstmt.setString(1, name);
            pstmt.setString(2, mobile);
            pstmt.setString(3, email);
            pstmt.setString(4, date);
            pstmt.setString(5, event);
            pstmt.setString(6, message);

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();

            // Respond back to the user
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rowsAffected > 0) {
                out.println("<h3>Thank you for scheduling your photoshoot! We'll contact you soon.</h3>");
            } else {
                out.println("<h3>Failed to schedule. Please try again later.</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while scheduling.");
        } finally {
            // Close the connection and statement
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
