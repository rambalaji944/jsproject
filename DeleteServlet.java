package js.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection con;
    private static PreparedStatement sat;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/logins";
        String username = "root";
        String password = "root";
        String SQL = "DELETE FROM vendor WHERE BankAccountNum = ?";

        try {
            con = DriverManager.getConnection(url, username, password);
            sat = con.prepareStatement(SQL);

            // Assuming the ID is passed as a parameter in the request
            int BankAccountNum = Integer.parseInt(request.getParameter("BankAccountNum"));

            sat.setInt(1, BankAccountNum);
            int rowsAffected = sat.executeUpdate();

            response.setContentType("text/html");

            if (rowsAffected > 0) {
                // Successful deletion
            	response.getWriter().println("<div style='text-align: center; background-color: blue; color: white; padding: 20px;'>");
            	response.getWriter().println("<p>Data deleted successfully.</p>");
            	response.getWriter().println("</div>");
            } else {
                // No matching record found
                response.getWriter().println("<html><body style='background-color: red; color: white; padding: 20px; margin: 20px; border: 2px solid white;'>");
                response.getWriter().println("<p>No record found with Bank Account Number " + BankAccountNum + "</p>");
                response.getWriter().println("</body></html>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
            response.getWriter().println("<html><body style='background-color: red; color: white; padding: 20px; margin: 20px; border: 2px solid white;'>");
            response.getWriter().println("<p>Failed to delete record. Check the server logs for details.</p>");
            response.getWriter().println("</body></html>");
        } finally {
            try {
                if (sat != null) {
                    sat.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
