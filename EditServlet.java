package js.project;

import java.io.IOException;
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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/logins";
        String username = "root";
        String password = "root";
        String selectSQL = "SELECT * FROM Vendor WHERE BankAccountNum = ?";

        String bankToEdit = request.getParameter("BankAccountNum");

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {

            selectStatement.setString(1, bankToEdit);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                String vendorName = rs.getString("vendorName");
                String bankName = rs.getString("BankName");
                String addressLine1 = rs.getString("AddressLine1");
                String addressLine2 = rs.getString("AddressLine2");
                String city = rs.getString("City");
                String country = rs.getString("Country");
                int zipCode = rs.getInt("ZipCode");
                request.setAttribute("bankToEdit", bankToEdit);
                request.setAttribute("vendorName", vendorName);
                request.setAttribute("bankName", bankName);
                request.setAttribute("addressLine1", addressLine1);
                request.setAttribute("addressLine2", addressLine2);
                request.setAttribute("city", city);
                request.setAttribute("country", country);
                request.setAttribute("zipCode", zipCode);
                request.getRequestDispatcher("/edit.jsp").forward(request, response);                // Forward 

            } else {
                response.getWriter().println("<p>Entry not found for editing.</p>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error occurred while retrieving data for editing.</p>");
        }
    }
}
