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
@WebServlet("/callprg1")
public class Prgram1 extends HttpServlet {
    private static Connection con;
    private static PreparedStatement sat;

    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/logins";
        String username = "root";
        String password = "root";
        String insertSQL = "INSERT INTO Vendor(vendorName, BankAccountNum, BankName, AddressLine1, AddressLine2, City, Country, ZipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String vendorName = req.getParameter("vendorName");
        String BankAccountNum = req.getParameter("BankAccountNum");
        String BankName = req.getParameter("BankName");
        String AddressLine1 = req.getParameter("AddressLine1");
        String AddressLine2 = req.getParameter("AddressLine2");
        String City = req.getParameter("City");
        String Country = req.getParameter("Country");
        String ZipCode = req.getParameter("ZipCode");

        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Data Table</title>");
        out.println("<style>");
        out.println("body { background-color: rgb(0, 0, 255); color: black; text-align: center; }");
        out.println(".hello-message { font-size: 1.2em; color: white;}");
        out.println(".success-message { color: white; font-size: 1.2em; }"); // Change color and increase font size

        out.println("table {");
        out.println("    border-collapse: collapse;");
        out.println("    width: 50%;");
        out.println("    margin: auto;"); // Center the table
        out.println("    background-color: rgb(255, 255, 255); }"); // Set the table color to white
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");


        out.println("<div class='hello-message'>Hello " + vendorName + " Check your Details</div>");
        out.print("<fieldset>");
        out.println("<div>");
        out.println("<table border='1'>");
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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            sat = con.prepareStatement(insertSQL);
            sat.setString(1, vendorName);

            int parsedBankAccountNum = 0;
            if (BankAccountNum != null && !BankAccountNum.isEmpty()) {
                try {
                    parsedBankAccountNum = Integer.parseInt(BankAccountNum);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            sat.setInt(2, parsedBankAccountNum);
            sat.setString(3, BankName);
            sat.setString(4, AddressLine1);
            sat.setString(5, AddressLine2);
            sat.setString(6, City);

            sat.setString(7, Country);
            int parsedZipCode = 0;
            if (ZipCode != null && !ZipCode.isEmpty()) {
                try {
                    parsedZipCode = Integer.parseInt(ZipCode);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            sat.setInt(8, parsedZipCode);

            int rowsAffected = sat.executeUpdate();
            if (rowsAffected > 0) {
                out.println("<p class='success-message'>Data inserted successfully!</p>");
            } else {
                out.println("<p class='success-message'>Failed to insert data.</p>");

            }

            sat = con.prepareStatement("SELECT * FROM Vendor");
            ResultSet rs = sat.executeQuery();
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("vendorName") + "</td>");
                out.println("<td>" + rs.getInt("BankAccountNum") + "</td>");
                out.println("<td>" + rs.getString("BankName") + "</td>");
                out.println("<td>" + rs.getString("AddressLine1") + "</td>");
                out.println("<td>" + rs.getString("AddressLine2") + "</td>");
                out.println("<td>" + rs.getString("City") + "</td>");
                out.println("<td>" + rs.getString("Country") + "</td>");
                out.println("<td>" + rs.getInt("ZipCode") + "</td>");
                out.println("<td><a href='" + req.getContextPath() + "/edit?BankAccountNum=" + rs.getInt("BankAccountNum") + "'>Edit</a></td>");
                out.println("<td><a href='" + req.getContextPath() + "/delete?BankAccountNum=" + rs.getInt("BankAccountNum") + "'>Delete</a></td>");
                out.println("</tr>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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

        out.println("</table>");
        out.println("</div>");
        out.println("</fieldset>");
        out.println("</body>");
        out.println("</html>");
    }
}
