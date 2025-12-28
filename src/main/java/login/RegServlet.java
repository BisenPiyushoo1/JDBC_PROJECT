package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@WebServlet("/Register")


public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL="jdbc:mysql://localhost:3306/userdb";
    public static final String Password="piyush";
    public static final String username="root";
    Connection connection;
       
   
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		try {
			connection= DriverManager.getConnection(URL,username,Password);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("Register.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String uname = request.getParameter("uname");
        String paswrd = request.getParameter("paswrd");

        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO userinfo(fname,lname,uname,paswrd) VALUES(?,?,?,?)"
            );

            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, uname);
            ps.setString(4, paswrd);

            int row = ps.executeUpdate();

            if (row > 0) {
                response.sendRedirect("success.html");
            } else {
                response.sendRedirect("error.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
