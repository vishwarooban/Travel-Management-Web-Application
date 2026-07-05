package ContactServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Allblue",
                    "root",
                    "vishwa0308");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO contact(name,email,phone,message) VALUES(?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, message);

            // Print received values
            System.out.println("Name = " + name);
            System.out.println("Email = " + email);
            System.out.println("Phone = " + phone);
            System.out.println("Message = " + message);

            // Execute INSERT
            int rows = ps.executeUpdate();

            // Print result
            System.out.println("Rows inserted = " + rows);

            con.close();

            // Redirect to home page
            response.sendRedirect(request.getContextPath() + "/home.html");

        } catch (Exception e) {
            e.printStackTrace();

            response.setContentType("text/html");
            response.getWriter().println("<h2>Error occurred</h2>");
            response.getWriter().println("<pre>");
            e.printStackTrace(response.getWriter());
            response.getWriter().println("</pre>");
        }
    }
}