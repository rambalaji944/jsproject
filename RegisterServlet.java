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
//... (previous imports)
//... (previous imports)

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
 private static final String JDBC_USER = "root";
 private static final String JDBC_PASSWORD = "root";

 static {
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();

     String username = request.getParameter("username");
     String password = request.getParameter("password");

     try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
         String insertUserSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
         try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
             preparedStatement.setString(1, username);
             preparedStatement.setString(2, password);
             preparedStatement.executeUpdate();
         }

         out.println("<div style='text-align: center; background-color: blue; color: white; padding: 20px;'>");
         out.println("Registration successful! Welcome, " + username);
         out.println("</div>");

         out.println("<br><div style='text-align: center;'><a href='" + request.getContextPath() + "/login.jsp' style='display: inline-block; padding: 10px 20px; background-color: #3498db; color: #ffffff; text-decoration: none; border-radius: 5px;'>Login</a></div>");

     } catch (SQLException e) {
         e.printStackTrace();
         out.println("Registration failed due to a database error.");
     }
 }
}
