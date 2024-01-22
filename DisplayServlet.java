package js.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/display")
public class DisplayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/logins";
        String username = "root";
        String password = "root";
        String selectAllSQL = "SELECT * FROM Vendor";

        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement selectAllStatement = connection.prepareStatement(selectAllSQL)) {

            ResultSet rs = selectAllStatement.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Updated Data's </title>");
            out.println("<style>");
            out.println("body { background-color: rgb(0, 0, 255); color: black; text-align: center; }");
            out.println("h2 { color: white; }");
            out.println("table { border-collapse: collapse; width: 50%; margin: auto; background-color: white; }");
            out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Data Display</h2>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>vendorName</th>");
            out.println("<th>BankAccountNum</th>");
            out.println("<th>BankName</th>");
            out.println("<th>AddressLine1</th>");
            out.println("<th>AddressLine2</th>");
            out.println("<th>City</th>");
            out.println("<th>Country</th>");
            out.println("<th>ZipCode</th>");

            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("vendorName") + "</td>");
                out.println("<td>" + rs.getString("BankAccountNum") + "</td>");
                out.println("<td>" + rs.getString("BankName") + "</td>");
                out.println("<td>" + rs.getString("AddressLine1") + "</td>");
                out.println("<td>" + rs.getString("AddressLine2") + "</td>");
                out.println("<td>" + rs.getString("City") + "</td>");
                out.println("<td>" + rs.getString("Country") + "</td>");
                out.println("<td>" + rs.getString("ZipCode") + "</td>");
                // Edit link
                out.println("<td><a href='" + request.getContextPath() + "/edit?BankAccountNum=" + rs.getString("BankAccountNum") + "'>Edit</a></td>");
                // Delete link
                out.println("<td><a href='" + request.getContextPath() + "/delete?BankAccountNum=" + rs.getString("BankAccountNum") + "'>Delete</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='" + request.getContextPath() + "/create.jsp'>Add vendor</a>");
            out.println("<br><a href='" + request.getContextPath() + "/LogoutServlet'>Logout</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error occurred while fetching data.</p>");
        }
    }
}
