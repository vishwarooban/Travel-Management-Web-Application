package LoginServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // ================= DATABASE CONFIG =================

    // ---------- Railway Database ----------
    private static final String HOST = System.getenv("MYSQLHOST");
    private static final String PORT = System.getenv("MYSQLPORT");
    private static final String DATABASE = System.getenv("MYSQLDATABASE");
    private static final String USER = System.getenv("MYSQLUSER");
    private static final String PASSWORD = System.getenv("MYSQLPASSWORD");

    // ====================================================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

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
                    "INSERT INTO login(email,mobile,password) VALUES(?,?,?)";

            ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, mobile);
            ps.setString(3, password);

            // Console Output
            System.out.println("========= LOGIN =========");
            System.out.println("Email    : " + email);
            System.out.println("Mobile   : " + mobile);
            System.out.println("Password : " + password);

            int rows = ps.executeUpdate();

            System.out.println("Rows Inserted : " + rows);
            System.out.println("=========================");

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