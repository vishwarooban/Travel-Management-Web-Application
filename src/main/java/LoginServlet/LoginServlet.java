package LoginServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Allblue",
                    "root",
                    "vishwa0308");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO login(email,mobile,password) VALUES(?,?,?)");

            ps.setString(1, email);
            ps.setString(2, mobile);
            ps.setString(3, password);

            ps.executeUpdate();

            con.close();

            // ✅ Redirect to home page
            response.sendRedirect("home.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
