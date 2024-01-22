package js.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/logins";
        String username = "root";
        String password = "root";
        String updateSQL = "UPDATE Vendor SET vendorName=?, BankName=?, AddressLine1=?, AddressLine2=?, City=?, Country=?, ZipCode=? WHERE BankAccountNum=?";

        // Retrieve parameters from the request
        String vendorName = request.getParameter("vendorName");
        String bankToUpdate = request.getParameter("bankToEdit");
        String bankName = request.getParameter("bankName");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        int zipCode = Integer.parseInt(request.getParameter("zipCode"));

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {

            // Set parameters for the update query
            updateStatement.setString(1, vendorName);
            updateStatement.setString(2, bankName);
            updateStatement.setString(3, addressLine1);
            updateStatement.setString(4, addressLine2);
            updateStatement.setString(5, city);
            updateStatement.setString(6, country);
            updateStatement.setInt(7, zipCode);
            updateStatement.setString(8, bankToUpdate);

            //  update query
            int rowsAffected = updateStatement.executeUpdate();

            // Output success or failure 
            PrintWriter out = response.getWriter();
            if (rowsAffected > 0) {
            	
                // Style the success message and center the content
                out.println("<div style='text-align: center; background-color: blue; color: white; padding: 20px;'>");
                out.println("<p>Data updated successfully.</p>");

                // Style the button
                out.println("<button style='background-color: white; color: blue; padding: 10px; border: none;' type='button' onclick='window.location.href=\"" + request.getContextPath() + "/display?id=" + bankToUpdate + "\"'>View Data</button>");
                out.println("</div>");
            } else {
            	 out.println("<div style='text-align: center; background-color: blue; color: white; padding: 20px;'>");
                 out.println("<p>Failed to update data.</p>");
                 out.println("</div>");            }


        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error occurred while updating data: " + e.getMessage() + "</p>");
        }
    }
}
