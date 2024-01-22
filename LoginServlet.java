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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
            String selectUserSQL = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    
                    

                    out.println("<div style='text-align: center; background-color: blue; color: white; padding: 20px;'>");
                	out.println("Login successful! Welcome, " + username);
                    out.println("</div>");
                  
                    out.println("<div style='text-align: center;'>");
                    out.println("<button onclick='location.href=\"" + request.getContextPath() + "/create.jsp\"'>Create Vendor</button>");
                    out.println("<br>");
                    out.println("<button onclick='location.href=\"" + request.getContextPath() + "/LogoutServlet\"'>Logout</button>");
                    out.println("</div>");
                 

                } else {
                    out.println("Login failed. Please check your username and password.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Login failed due to a database error.");
        }

    }
}
