package ContactServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // ================= DATABASE CONFIG =================

    // ---------- Railway Database ----------
    private static final String HOST = System.getenv("MYSQLHOST");
    private static final String PORT = System.getenv("MYSQLPORT");
    private static final String DATABASE = System.getenv("MYSQLDATABASE");
    private static final String USER = System.getenv("MYSQLUSER");
    private static final String PASSWORD = System.getenv("MYSQLPASSWORD");

    // ===================================================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        Connection con = null;
        PreparedStatement ps = null;

        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect Database
            String url =
            	    "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE +
            	    "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            	con = DriverManager.getConnection(url, USER, PASSWORD);

            String sql =
                    "INSERT INTO contact(name,email,phone,message) VALUES(?,?,?,?)";

            ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, message);

            // Console Output
            System.out.println("========= CONTACT =========");
            System.out.println("Name    : " + name);
            System.out.println("Email   : " + email);
            System.out.println("Phone   : " + phone);
            System.out.println("Message : " + message);

            int rows = ps.executeUpdate();

            System.out.println("Rows Inserted : " + rows);
            System.out.println("===========================");

            // Redirect to Home Page
            response.sendRedirect(request.getContextPath() + "/home.html");

        }
        catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html");
            response.getWriter().println("<h2>Database Error</h2>");
            response.getWriter().println("<pre>");
            e.printStackTrace(response.getWriter());
            response.getWriter().println("</pre>");

        }
        finally {

            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}